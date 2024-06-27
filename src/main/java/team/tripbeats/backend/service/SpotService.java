package team.tripbeats.backend.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.tripbeats.backend.Dto.CurationDto;
import team.tripbeats.backend.Dto.MusicDto;
import team.tripbeats.backend.Dto.SpotDto;
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

    public SpotDto convertSpotToDto(Spot spot) {
        MusicDto musicDto = MusicDto.builder()
                .id(spot.getMusic().getId())
                .name(spot.getMusic().getName())
                .url(spot.getMusic().getUrl())
                .build();

        CurationDto curationDto = CurationDto.builder()
                .id(spot.getCuration().getId())
                .name(spot.getCuration().getName())
                .url(spot.getCuration().getUrl())
                .build();

        return SpotDto.builder()
                .id(spot.getId())
                .imageUrl(spot.getImageUrl())
                .placeName(spot.getPlaceName())
                .location(spot.getLocation())
                .description(spot.getDescription())
                .music(musicDto)
                .curation(curationDto)
                .build();
    }
}
