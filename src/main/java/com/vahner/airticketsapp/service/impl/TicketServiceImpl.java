package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.dto.TicketDto;
import com.vahner.airticketsapp.entity.Passenger;
import com.vahner.airticketsapp.entity.Ticket;
import com.vahner.airticketsapp.exception.ErrorMessage;
import com.vahner.airticketsapp.exception.PassengerNotFoundException;
import com.vahner.airticketsapp.exception.TicketNotFoundException;
import com.vahner.airticketsapp.generator.QRCodeGenerator;
import com.vahner.airticketsapp.mapper.TicketMapper;
import com.vahner.airticketsapp.repository.PassengerRepository;
import com.vahner.airticketsapp.repository.TicketRepository;
import com.vahner.airticketsapp.service.interf.TicketService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final PassengerRepository passengerRepository;
    private final JavaMailSender mailSender;
    private final QRCodeGenerator qrCodeGenerator;


    @Override
    public TicketDto getTicketById(String id) {
        log.info("Retrieving ticket by ID: {}", id);
        Ticket ticket = ticketRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new TicketNotFoundException(ErrorMessage.M_TICKET_NOT_FOUND + id));
        return ticketMapper.toDto(ticket);
    }

    @Override
    public List<TicketDto> getAllTickets() {
        log.info("Retrieving all tickets");
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(ticketMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TicketDto createTicket(TicketDto ticketDto) {
        log.info("Creating new ticket");
        Ticket ticket = ticketMapper.toEntity(ticketDto);

        Passenger passenger = passengerRepository.findById(UUID.fromString(ticketDto.getPassengerId()))
                .orElseThrow(() -> new PassengerNotFoundException(ErrorMessage.M_PASSEGER_NOT_FOUND + id));

        try {
            byte[] qrCodeImage = qrCodeGenerator.generateQRCodeImage("Ticket info here", 200, 200);
            sendEmailWithAttachment(passenger.getEmail(), "Your ticket QR Code", qrCodeImage);
        } catch (Exception e) {
            log.error("Error generating or sending QR Code", e);
            throw new RuntimeException("Error generating or sending QR Code", e);
        }


        ticket = ticketRepository.save(ticket);
        return ticketMapper.toDto(ticket);
    }

    @Override
    public void sendEmailWithAttachment(String to, String subject, byte[] attachment) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText("Find your ticket QR code attached.");
            helper.addAttachment("QRCode.png", new ByteArrayResource(attachment));

            mailSender.send(message);
            log.info("Email with QR code sent to: {}", to);
        } catch (MessagingException e) {
            log.error("Failed to send email with QR code to {}", to, e);
            throw new RuntimeException("Failed to send email with QR code", e);
        }
    }

    @Override
    @Transactional
    public void updateTicket(String id, TicketDto ticketDto) {
        log.info("Updating ticket with ID: {}", id);
        Ticket ticket = ticketRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new TicketNotFoundException(ErrorMessage.M_TICKET_NOT_FOUND + id));
        ticketMapper.updateEntityFromDto(ticketDto, ticket);
        ticketRepository.save(ticket);
    }

    @Override
    @Transactional
    public void deleteTicketById(String id) {
        log.info("Deleting ticket with ID: {}", id);
        Ticket ticket = ticketRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new TicketNotFoundException(ErrorMessage.M_TICKET_NOT_FOUND + id));
        ticketRepository.delete(ticket);
    }
}