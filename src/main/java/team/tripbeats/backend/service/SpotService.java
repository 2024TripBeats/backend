package team.tripbeats.backend.service;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import team.tripbeats.backend.Dto.CurationDto;
import team.tripbeats.backend.Dto.MusicDto;
import team.tripbeats.backend.Dto.SpotDto;
import team.tripbeats.backend.entity.Spot;
import team.tripbeats.backend.repository.SpotRepository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SpotService {

    private final SpotRepository spotRepository;

    @PostConstruct
    public void init() {
        String line;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new ClassPathResource("place.csv").getInputStream()))) {
            // 헤더 라인 건너뛰기
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 12) {
                    System.err.println("Skipping incomplete line: " + line);
                    continue;
                }
                try {
                    Spot spot = Spot.builder()
                            .visitAreaNm(data[0])
                            .radNmAddr(data[1])
                            .visitAreaTypeCd(parseIntSafe(data[2]))
                            .hashtags(data[3])
                            .time(data[4])
                            .연락처(data[5])
                            .주차(data[6])
                            .동물출입(data[7])
                            .소개글(data[8])
                            .lat(parseDoubleSafe(data[9]))
                            .lng(parseDoubleSafe(data[10]))
                            .imageUrl(data[11])
                            .build();

                    spotRepository.save(spot);
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing number in line: " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int parseIntSafe(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing integer value: " + value);
            return 0;
        }
    }

    private double parseDoubleSafe(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing double value: " + value);
            return 0.0;
        }
    }

    public Optional<Spot> getSpotById(Long id) {
        return spotRepository.findById(id);
    }

    public SpotDto convertSpotToDto(Spot spot) {
        return SpotDto.builder()
                .id(spot.getId())
                .visitAreaNm(spot.getVisitAreaNm())
                .radNmAddr(spot.getRadNmAddr())
                .visitAreaTypeCd(spot.getVisitAreaTypeCd())
                .hashtags(spot.getHashtags())
                .time(spot.getTime())
                .연락처(spot.get연락처())
                .주차(spot.get주차())
                .동물출입(spot.get동물출입())
                .소개글(spot.get소개글())
                .lat(spot.getLat())
                .lng(spot.getLng())
                .imageUrl(spot.getImageUrl())
                .build();
    }
}