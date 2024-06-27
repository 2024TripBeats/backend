package team.tripbeats.backend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {

    @Id
    private Long id;
    private String kakaoName;

    private String email;
    private Long phoneNumber;
    private String gender;
    private String age;

    private String distance;
    private String activityLevel;
    private String Scene;
    private int openness;

    @ManyToMany
    @JoinTable(
            name = "account_music_genres",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "music_genre_id")
    )
    private List<MusicGenre> musicGenres;
    private int genreOpenness;

    @ManyToMany
    @JoinTable(
            name = "account_music_tags",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "music_tag_id")
    )
    private List<MusicTag> musicTags;
    private int tagOpenness;

    @ManyToMany
    @JoinTable(
            name = "account_travel_spots",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "travel_spot_id")
    )
    private List<TravelSpot> travelSpots;

    private boolean doneSurvey;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trip> trips;
}
