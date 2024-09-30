package team.tripbeats.backend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "spot")
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "visit_area_nm")
    private String visitAreaNm;

    @Column(name = "rad_nm_addr")
    private String radNmAddr;

    @Column(name = "visit_area_type_cd")
    private int visitAreaTypeCd;

    @Column(name = "hashtags")
    private String hashtags;

    @Column(name = "time")
    private String time;

    @Column(name = "contact")
    private String contact;

    @Column(name = "parking")
    private boolean parking;

    @Column(name = "pet_access")
    private boolean petAccess;

    @Column(name = "description", length = 1024)
    private String description;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "image_url", length = 4096)
    private String imageUrl;
}
