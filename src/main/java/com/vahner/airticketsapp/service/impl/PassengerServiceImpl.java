package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.dto.PassengerDto;
import com.vahner.airticketsapp.dto.ShortPassengerDto;
import com.vahner.airticketsapp.entity.Passenger;
import com.vahner.airticketsapp.exception.ErrorMessage;
import com.vahner.airticketsapp.exception.PassengerNotFoundException;
import com.vahner.airticketsapp.mapper.PassengerMapper;
import com.vahner.airticketsapp.repository.PassengerRepository;
import com.vahner.airticketsapp.service.interf.PassengerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;

    /**
     * Получение пассажира по его ID.
     *
     * @param id ID пассажира.
     * @return DTO пассажира.
     */
    public PassengerDto getPassengerById(String id) {
        log.info("Retrieving passenger by ID: {}", id);
        Passenger passenger = passengerRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new PassengerNotFoundException(String.format(ErrorMessage.M_PASSEGER_NOT_FOUND, id)));
        return passengerMapper.toDto(passenger);
    }

    /**
     * Получение списка всех пассажиров.
     *
     * @return Список DTO всех пассажиров.
     */
    public List<ShortPassengerDto> getAllPassengers() {
        log.info("Retrieving all passengers");
        return passengerRepository.findAll().stream()
                .map(passengerMapper::toShortDto)
                .collect(Collectors.toList());
    }

    /**
     * Создание нового пассажира.
     *
     * @param passengerDto DTO для создания пассажира.
     * @return DTO созданного пассажира.
     */
    @Override
    @Transactional
    public PassengerDto createPassenger(PassengerDto passengerDto) {
        log.info("Creating new passenger: {}", passengerDto);
        Passenger passenger = passengerMapper.toEntity(passengerDto);
        Passenger savedPassenger = passengerRepository.save(passenger);
        return passengerMapper.toDto(savedPassenger);
    }

    /**
     * Обновление информации о пассажире.
     *
     * @param id ID пассажира для обновления.
     * @param passengerDto DTO с новыми данными для пассажира.
     */
    @Override
    @Transactional
    public void updatePassenger(String id, PassengerDto passengerDto) {
        log.info("Updating passenger with ID {}: {}", id, passengerDto);
        Passenger existingPassenger = passengerRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new PassengerNotFoundException(String.format(ErrorMessage.M_PASSEGER_NOT_FOUND, id)));
        passengerMapper.updateEntity(passengerDto, existingPassenger);
        passengerRepository.save(existingPassenger);
    }

    /**
     * Удаление пассажира по ID.
     *
     * @param id ID пассажира для удаления.
     */
    @Override
    @Transactional
    public void deletePassengerById(String id) {
        log.info("Deleting passenger with ID: {}", id);
        Passenger passenger = passengerRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new PassengerNotFoundException(String.format(ErrorMessage.M_PASSEGER_NOT_FOUND, id)));
        passengerRepository.delete(passenger);
    }

    @Override
    public List<PassengerDto> getPassengersByAccountId(String accountId) {
        List<Passenger> passengers = passengerRepository.findByAccountId((UUID.fromString(accountId)));
        return passengers.stream().map(passengerMapper::toDto).collect(Collectors.toList());
    }
}
