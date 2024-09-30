package team.tripbeats.backend.domain.tripRecommand.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team.tripbeats.backend.domain.tripRecommand.dto.TripRequestDTO;
import team.tripbeats.backend.domain.tripRecommand.dto.TripResponseDTO;
import team.tripbeats.backend.domain.tripRecommand.entity.Trip;
import team.tripbeats.backend.domain.tripRecommand.repository.TripRepository;
import team.tripbeats.backend.entity.Account;
import team.tripbeats.backend.repository.AccountRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final AccountRepository accountRepository;

    public TripResponseDTO getTripById(Long id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Trip not found with id: " + id));

        return convertToResponseDTO(trip);
    }


    public List<Long> getTripIdsByAccountId(Long accountId) {
        return tripRepository.findByAccountId(accountId).stream()
                .map(Trip::getId)
                .collect(Collectors.toList());
    }

    @Transactional
    public Trip saveTrip(TripRequestDTO tripRequestDTO) {
        Account account = accountRepository.findById(tripRequestDTO.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid account ID: " + tripRequestDTO.getAccountId()));

        Trip trip = Trip.builder()
                .account(account)
                .tripName(tripRequestDTO.getTripName())
                .tripData(tripRequestDTO.getTripData())
                .build();

        return tripRepository.save(trip);
    }

    public TripResponseDTO convertToResponseDTO(Trip trip) {
        return TripResponseDTO.builder()
                .tripId(trip.getId())
                .accountId(trip.getAccount().getId())
                .tripName(trip.getTripName())
                .tripData(trip.getTripData())
                .build();
    }
}
