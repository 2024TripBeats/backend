package team.tripbeats.backend.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team.tripbeats.backend.Dto.TripDayDto;
import team.tripbeats.backend.Dto.TripDaySpotDto;
import team.tripbeats.backend.Dto.TripDto;
import team.tripbeats.backend.entity.Spot;
import team.tripbeats.backend.entity.Trip;
import team.tripbeats.backend.entity.TripDay;
import team.tripbeats.backend.entity.TripDaySpot;
import team.tripbeats.backend.repository.SpotRepository;
import team.tripbeats.backend.repository.TripRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final SpotRepository spotRepository;

    public Optional<Trip> getTripById(Integer id) {
        return tripRepository.findById(id);
    }

    @Transactional
    public Trip saveTrip(TripDto tripDto) {
        Trip trip = Trip.builder()
                .userId(tripDto.getUserId())
                .tripName(tripDto.getTripName())
                .period(tripDto.getPeriod())
                .build();

        List<TripDay> tripDays = tripDto.getTripDays().stream().map(tripDayDto -> {
            TripDay tripDay = TripDay.builder()
                    .trip(trip)
                    .dayNumber(tripDayDto.getDayNumber())
                    .build();

            List<TripDaySpot> tripDaySpots = tripDayDto.getTripDaySpots().stream().map(tripDaySpotDto -> {
                Spot spot = spotRepository.findById(tripDaySpotDto.getSpotId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid spot ID: " + tripDaySpotDto.getSpotId()));

                return TripDaySpot.builder()
                        .tripDay(tripDay)
                        .spot(spot)
                        .visitOrder(tripDaySpotDto.getVisitOrder())
                        .build();
            }).collect(Collectors.toList());

            tripDay.setTripDaySpots(tripDaySpots);
            return tripDay;
        }).collect(Collectors.toList());

        trip.setTripDays(tripDays);
        return tripRepository.save(trip);
    }

    public TripDto convertToDto(Trip trip) {
        return TripDto.builder()
                .userId(trip.getUserId())
                .tripName(trip.getTripName())
                .period(trip.getPeriod())
                .tripDays(trip.getTripDays().stream().map(this::convertToDto).collect(Collectors.toList()))
                .build();
    }

    private TripDayDto convertToDto(TripDay tripDay) {
        return TripDayDto.builder()
                .dayNumber(tripDay.getDayNumber())
                .tripDaySpots(tripDay.getTripDaySpots().stream().map(this::convertToDto).collect(Collectors.toList()))
                .build();
    }

    private TripDaySpotDto convertToDto(TripDaySpot tripDaySpot) {
        return TripDaySpotDto.builder()
                .spotId(tripDaySpot.getSpot().getId())
                .visitOrder(tripDaySpot.getVisitOrder())
                .build();
    }
}