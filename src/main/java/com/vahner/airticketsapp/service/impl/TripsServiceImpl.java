package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.dto.TripsDto;
import com.vahner.airticketsapp.entity.Trips;
import com.vahner.airticketsapp.entity.enums.TripsType;
import com.vahner.airticketsapp.exception.TripsNotFoundException;
import com.vahner.airticketsapp.mapper.TripsMapper;
import com.vahner.airticketsapp.repository.TripsRepository;
import com.vahner.airticketsapp.service.interf.TripsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class TripsServiceImpl implements TripsService {

    private final TripsRepository tripsRepository;
    private final TripsMapper tripsMapper;

    /**
     * Получение информации о поездке по её UUID.
     *
     * @param uuid поездки.
     * @return DTO с информацией о поездке.
     */
    @Override
    public TripsDto getTripsById(String uuid) {
        Trips trips = tripsRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new TripsNotFoundException("Trips not found"));

        return tripsMapper.toDto(trips);
    }

    /**
     * Получение списка всех поездок.
     *
     * @return Список DTO с информацией о поездках.
     */
    @Override
    public ResponseEntity<List<TripsDto>> findAll() {
        log.info("Finding all trips");
        List<TripsDto> tripsDto = tripsMapper.toDtoList(tripsRepository.findAll());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tripsDto);
    }

    /**
     * Создание новой поездки.
     *
     * @param tripsDto данные для создания поездки.
     * @return DTO с информацией о созданной поездке.
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseEntity<TripsDto> create(TripsDto tripsDto) {
       log.info("Creating a new trip: {}", tripsDto);

        TripsDto createdTripsDto = tripsMapper.toTripsEntity(tripsDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdTripsDto);
    }

    /**
     * Удаление поездки с указанным UUID.
     *
     * @param uuid поездки для удаления.
     */
    public void deleteTrips(String uuid) {
        log.info("Deleting trip with ID: {}", uuid);
        tripsRepository.deleteById(UUID.fromString(uuid));
    }

    /**
     * Обновление информации о поездке с указанным UUID.
     *
     * @param uuid поездки для обновления.
     * @param tripsDto Новые данные для поездки.
     * @return DTO с обновленной информацией о поездке.
     */
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public TripsDto updateTrips(String uuid, TripsDto tripsDto) {
        Trips existingTrips = tripsRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new TripsNotFoundException("Trips not found"));

        existingTrips.setNameTrips(tripsDto.getNameTrips());
        existingTrips.setFlightTime(LocalDateTime.parse(tripsDto.getFlightTime()));
        existingTrips.setTripsType(TripsType.valueOf(tripsDto.getTripsType()));

        Trips updateTrips = tripsRepository.save(existingTrips);
        return tripsMapper.toDto(updateTrips);
    }
}