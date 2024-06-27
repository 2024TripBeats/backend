package team.tripbeats.backend.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team.tripbeats.backend.Dto.CurationDto;
import team.tripbeats.backend.Dto.MusicDto;
import team.tripbeats.backend.Dto.TripDayOutputDto;
import team.tripbeats.backend.Dto.TripDaySpotOutputDto;
import team.tripbeats.backend.Dto.TripInputDto;
import team.tripbeats.backend.Dto.TripOutputDto;
import team.tripbeats.backend.entity.Account;
import team.tripbeats.backend.entity.Spot;
import team.tripbeats.backend.entity.Music;
import team.tripbeats.backend.entity.Trip;
import team.tripbeats.backend.entity.TripDay;
import team.tripbeats.backend.entity.TripDaySpot;
import team.tripbeats.backend.repository.MusicRepository;
import team.tripbeats.backend.repository.SpotRepository;
import team.tripbeats.backend.repository.TripRepository;
import team.tripbeats.backend.repository.AccountRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final SpotRepository spotRepository;
    private final MusicRepository musicRepository;
    private final AccountRepository accountRepository;

    public Optional<Trip> getTripById(Integer id) {
        return tripRepository.findById(id);
    }

    public List<Integer> getTripIdsByAccountId(Long accountId) {
        return tripRepository.findByAccountId(accountId).stream()
                .map(Trip::getId)
                .collect(Collectors.toList());
    }

    @Transactional
    public Trip saveTrip(TripInputDto tripInputDto) {
        Account account = accountRepository.findById(Long.parseLong(tripInputDto.getAccountId()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid account ID: " + tripInputDto.getAccountId()));

        Trip trip = Trip.builder()
                .account(account)
                .tripName(tripInputDto.getTripName())
                .period(tripInputDto.getPeriod())
                .build();

        List<TripDay> tripDays = tripInputDto.getTripDays().stream().map(tripDayInputDto -> {
            TripDay tripDay = TripDay.builder()
                    .trip(trip)
                    .dayNumber(tripDayInputDto.getDayNumber())
                    .build();

            List<TripDaySpot> tripDaySpots = tripDayInputDto.getTripDaySpots().stream().map(tripDaySpotInputDto -> {
                Spot spot = spotRepository.findById(tripDaySpotInputDto.getSpotId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid spot ID: " + tripDaySpotInputDto.getSpotId()));

                Music music = musicRepository.findById(tripDaySpotInputDto.getMusicId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid music ID: " + tripDaySpotInputDto.getMusicId()));

                return TripDaySpot.builder()
                        .tripDay(tripDay)
                        .spot(spot)
                        .visitOrder(tripDaySpotInputDto.getVisitOrder())
                        .music(music)
                        .build();
            }).collect(Collectors.toList());

            tripDay.setTripDaySpots(tripDaySpots);
            return tripDay;
        }).collect(Collectors.toList());

        trip.setTripDays(tripDays);
        return tripRepository.save(trip);
    }

    public TripOutputDto convertToDto(Trip trip) {
        return TripOutputDto.builder()
                .accountId(String.valueOf(trip.getAccount().getId()))
                .tripName(trip.getTripName())
                .period(trip.getPeriod())
                .tripDays(trip.getTripDays().stream().map(this::convertToOutputDto).collect(Collectors.toList()))
                .build();
    }

    private TripDayOutputDto convertToOutputDto(TripDay tripDay) {
        return TripDayOutputDto.builder()
                .dayNumber(tripDay.getDayNumber())
                .tripDaySpots(tripDay.getTripDaySpots().stream().map(this::convertToOutputDto).collect(Collectors.toList()))
                .build();
    }

    private TripDaySpotOutputDto convertToOutputDto(TripDaySpot tripDaySpot) {
        MusicDto musicDto = MusicDto.builder()
                .id(tripDaySpot.getMusic().getId())
                .name(tripDaySpot.getMusic().getName())
                .url(tripDaySpot.getMusic().getUrl())
                .build();

        return TripDaySpotOutputDto.builder()
                .spotId(tripDaySpot.getSpot().getId())
                .visitOrder(tripDaySpot.getVisitOrder())
                .musicDto(musicDto)
                .build();
    }
}