package team.tripbeats.backend.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.tripbeats.backend.Dto.SpotDto;
import team.tripbeats.backend.entity.Spot;
import team.tripbeats.backend.repository.SpotRepository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
                if (line.length < 10) {
                    continue; // Skip invalid lines
                }

                Spot spot = new Spot();
                spot.setId(line[0].trim());
                spot.setVisitAreaNm(line[1].trim());
                spot.setRadNmAddr(line[2].trim());
                spot.setVisitAreaTypeCd(line[3].trim());
                spot.setHashtags(line[4].trim());
                spot.setTime(line[5].trim());
                spot.setContact(line[6].trim());
                spot.setInfo(line[7].trim());
                spot.setFare(line[8].trim());
                spot.setDescription(line[9].trim());
                spot.setImageUrl(line[10].trim());

                spots.add(spot);
            }
            spotRepository.deleteAll();
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

    public Spot getSpotByVisitAreaNm(String visitAreaNm) {
        return spotRepository.findByVisitAreaNm(visitAreaNm).orElse(null);
    }

    public SpotDto convertSpotToDto(Spot spot) {
        return SpotDto.builder()
                .spotId(spot.getId())
                .visitAreaNm(spot.getVisitAreaNm())
                .radNmAddr(spot.getRadNmAddr())
                .visitAreaTypeCd(spot.getVisitAreaTypeCd())
                .hashtags(spot.getHashtags())
                .time(spot.getTime())
                .contact(spot.getContact())
                .info(spot.getInfo())
                .fare(spot.getFare())
                .description(spot.getDescription())
                .imageUrl(spot.getImageUrl())
                .build();
    }

    public Spot getSpotById(String placeId) {
        return spotRepository.findById(placeId).orElse(null);
    }
}