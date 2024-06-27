package team.tripbeats.backend.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpotService {

    private final SpotRepository spotRepository;

    @PostConstruct
    public void init() {
        String csvFilePath = "src/main/resources/place.csv";
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] line;
            List<Spot> spots = new ArrayList<>();
            reader.readNext(); // Skip header line
            while ((line = reader.readNext()) != null) {
                if (line.length < 12) {
                    continue; // Skip invalid lines
                }

                Spot spot = new Spot();
                spot.setVisitAreaNm(line[0].trim());
                spot.setRadNmAddr(line[1].trim());
                spot.setVisitAreaTypeCd(Integer.parseInt(line[2].trim()));
                spot.setHashtags(line[3].trim());
                spot.setTime(line[4].trim());
                spot.setContact(line[5].trim());
                spot.setParking(line[6].trim().equals("가능"));
                spot.setPetAccess(line[7].trim().equals("불가능"));
                spot.setDescription(line[8].trim());
                spot.setLatitude(Double.parseDouble(line[9].trim()));
                spot.setLongitude(Double.parseDouble(line[10].trim()));
                spot.setImageUrl(line[11].trim().length() > 2048 ? line[11].trim().substring(0, 2048) : line[11].trim());

                spots.add(spot);
            }
            spotRepository.saveAll(spots);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Spot> getAllSpots() {
        return spotRepository.findAll();
    }

    public Spot getSpotById(Long id) {
        return spotRepository.findById(id).orElse(null);
    }

    public SpotDto convertSpotToDto(Spot spot) {
        return SpotDto.builder()
                .id(spot.getId())
                .visitAreaNm(spot.getVisitAreaNm())
                .radNmAddr(spot.getRadNmAddr())
                .visitAreaTypeCd(spot.getVisitAreaTypeCd())
                .hashtags(spot.getHashtags())
                .time(spot.getTime())
                .contact(spot.getContact())
                .parking(spot.isParking())
                .petAccess(spot.isPetAccess())
                .description(spot.getDescription())
                .latitude(spot.getLatitude())
                .longitude(spot.getLongitude())
                .imageUrl(spot.getImageUrl())
                .build();
    }
}