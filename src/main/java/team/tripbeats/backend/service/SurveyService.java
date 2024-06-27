package team.tripbeats.backend.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import team.tripbeats.backend.Dto.SurveyDto;
import team.tripbeats.backend.entity.Account;
import team.tripbeats.backend.entity.MusicGenre;
import team.tripbeats.backend.entity.MusicTag;
import team.tripbeats.backend.entity.TravelSpot;
import team.tripbeats.backend.repository.AccountRepository;
import team.tripbeats.backend.repository.MusicGenreRepository;
import team.tripbeats.backend.repository.MusicTagRepository;
import team.tripbeats.backend.repository.TravelSpotRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurveyService {

    private final AccountRepository accountRepository;
    private final MusicGenreRepository musicGenreRepository;
    private final MusicTagRepository musicTagRepository;
    private final TravelSpotRepository travelSpotRepository;

    public SurveyService(AccountRepository accountRepository, MusicGenreRepository musicGenreRepository, MusicTagRepository musicTagRepository, TravelSpotRepository travelSpotRepository) {
        this.accountRepository = accountRepository;
        this.musicGenreRepository = musicGenreRepository;
        this.musicTagRepository = musicTagRepository;
        this.travelSpotRepository = travelSpotRepository;
    }

    @Transactional
    public Account saveSurvey(SurveyDto surveyDto) {
        Account account = accountRepository.findById(surveyDto.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid account ID"));

        account.setEmail(surveyDto.getEmail());
        account.setPhoneNumber(surveyDto.getPhoneNumber());
        account.setGender(surveyDto.getGender());
        account.setAge(surveyDto.getAge());
        account.setDistance(surveyDto.getDistance());
        account.setActivityLevel(surveyDto.getActivityLevel());
        account.setScene(surveyDto.getScene());
        account.setOpenness(surveyDto.getOpenness());
        account.setGenreOpenness(surveyDto.getGenreOpenness());
        account.setTagOpenness(surveyDto.getTagOpenness());

        List<MusicGenre> musicGenres = surveyDto.getMusicGenres().stream()
                .map(genre -> {
                    MusicGenre musicGenre = musicGenreRepository.findByName(genre)
                            .orElseGet(() -> musicGenreRepository.save(MusicGenre.builder().name(genre).build()));
                    return musicGenre;
                })
                .collect(Collectors.toList());

        List<MusicTag> musicTags = surveyDto.getMusicTags().stream()
                .map(tag -> {
                    MusicTag musicTag = musicTagRepository.findByName(tag)
                            .orElseGet(() -> musicTagRepository.save(MusicTag.builder().name(tag).build()));
                    return musicTag;
                })
                .collect(Collectors.toList());

        List<TravelSpot> travelSpots = surveyDto.getTravelSpots().stream()
                .map(spot -> {
                    TravelSpot travelSpot = travelSpotRepository.findBySpotName(spot)
                            .orElseGet(() -> travelSpotRepository.save(TravelSpot.builder().spotName(spot).build()));
                    return travelSpot;
                })
                .collect(Collectors.toList());

        account.setMusicGenres(musicGenres);
        account.setMusicTags(musicTags);
        account.setTravelSpots(travelSpots);

        account.setDoneSurvey(true);

        return accountRepository.save(account);
    }

    public SurveyDto convertToDto(Account account) {
        List<String> musicGenres = account.getMusicGenres().stream()
                .map(MusicGenre::getName)
                .collect(Collectors.toList());

        List<String> musicTags = account.getMusicTags().stream()
                .map(MusicTag::getName)
                .collect(Collectors.toList());

        List<String> travelSpots = account.getTravelSpots().stream()
                .map(TravelSpot::getSpotName)
                .collect(Collectors.toList());

        return SurveyDto.builder()
                .accountId(account.getId())
                .email(account.getEmail())
                .phoneNumber(account.getPhoneNumber())
                .gender(account.getGender())
                .age(account.getAge())
                .distance(account.getDistance())
                .activityLevel(account.getActivityLevel())
                .scene(account.getScene())
                .openness(account.getOpenness())
                .genreOpenness(account.getGenreOpenness())
                .tagOpenness(account.getTagOpenness())
                .musicGenres(musicGenres)
                .musicTags(musicTags)
                .travelSpots(travelSpots)
                .build();
    }
}
