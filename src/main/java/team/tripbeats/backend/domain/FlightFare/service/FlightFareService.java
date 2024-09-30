package team.tripbeats.backend.domain.FlightFare.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.tripbeats.backend.domain.FlightFare.dto.FareRequestDto;
import team.tripbeats.backend.domain.FlightFare.dto.RoundFareRequestDto;
import team.tripbeats.backend.domain.FlightFare.entity.FlightFare;
import team.tripbeats.backend.domain.FlightFare.repository.FlightFareRepository;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FlightFareService {

    private final FlightFareRepository flightFareRepository;

    @PostConstruct
    public void init() {
        String csvFilePath = "src/main/resources/flight_fare.csv";  // CSV 파일 경로 설정
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] line;
            List<FlightFare> flightFares = new ArrayList<>();
            reader.readNext(); // 헤더 줄 건너뛰기
            while ((line = reader.readNext()) != null) {
                if (line.length < 8) {
                    continue; // 데이터가 부족한 줄 건너뛰기
                }

                FlightFare flightFare = new FlightFare();
                flightFare.setYear(Integer.parseInt(line[0].trim()));
                flightFare.setMonth(Integer.parseInt(line[1].trim()));
                flightFare.setDay(Integer.parseInt(line[2].trim()));
                flightFare.setWeekday(line[3].trim());
                flightFare.setTimeLabel(line[4].trim());
                flightFare.setRoute(line[5].trim());
                flightFare.setAirline(line[6].trim());
                flightFare.setPredFare(Integer.parseInt(line[7].trim()));

                flightFares.add(flightFare);
            }
            flightFareRepository.deleteAll();  // 기존 데이터를 삭제
            flightFareRepository.saveAll(flightFares);  // 새 데이터를 저장
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    @Transactional(readOnly = true)
    public Map<String, Integer> getFareByStartAirportAndTime(FareRequestDto fareRequestDto) {
        String route = fareRequestDto.getStartAirport() + "->제주";

        // 해당 경로와 시간대에 맞는 모든 항공편 조회
        List<FlightFare> fares = flightFareRepository.findByRouteAndTimeLabel(route, fareRequestDto.getDepartureTime());

        return fares.stream()
                .collect(Collectors.toMap(
                        fare -> String.format("%d-%02d-%02d", fare.getYear(), fare.getMonth(), fare.getDay()),  // 날짜
                        FlightFare::getPredFare,
                        (oldValue, newValue) -> oldValue,
                        TreeMap::new
                ));
    }

    @Transactional(readOnly = true)
    public int getRoundTripFare(RoundFareRequestDto request) {
        String startRoute = request.getStartAirport() + "->제주";
        String returnRoute = "제주->" + request.getEndAirport();

        // 출발일 항공편 가격 조회
        String[] startDateParts = request.getStartDate().split("-");
        int startYear = Integer.parseInt(startDateParts[0]);
        int startMonth = Integer.parseInt(startDateParts[1]);
        int startDay = Integer.parseInt(startDateParts[2]);

        FlightFare outboundFare = flightFareRepository.findByYearAndMonthAndDayAndRouteAndTimeLabel(
                        startYear, startMonth, startDay, startRoute, request.getDepartureTime())
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No outbound flight found for the given criteria"));

        // 돌아오는 날의 항공편 가격 조회
        String[] endDateParts = request.getEndDate().split("-");
        int endYear = Integer.parseInt(endDateParts[0]);
        int endMonth = Integer.parseInt(endDateParts[1]);
        int endDay = Integer.parseInt(endDateParts[2]);

        FlightFare returnFare = flightFareRepository.findByYearAndMonthAndDayAndRouteAndTimeLabel(
                        endYear, endMonth, endDay, returnRoute, request.getReturnTime())
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No return flight found for the given criteria"));

        // 왕복 가격 합산
        return outboundFare.getPredFare() + returnFare.getPredFare();
    }
}
