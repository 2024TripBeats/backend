package team.tripbeats.backend.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.tripbeats.backend.entity.Spot;
import team.tripbeats.backend.repository.SpotRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SpotService {

    private final SpotRepository spotRepository;

    public Optional<Spot> getSpotById(Integer id) {
        return spotRepository.findById(id);
    }
}
