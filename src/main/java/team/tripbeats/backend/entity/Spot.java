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
    private String id;

    @Column(name = "visit_area_nm")
    private String visitAreaNm;

    @Column(name = "rad_nm_addr")
    private String radNmAddr;

    @Column(name = "visit_area_type_cd")
    private String visitAreaTypeCd;

    @Column(name = "hashtags", columnDefinition = "TEXT")
    private String hashtags;

    @Column(name = "time")
    private String time;

    @Column(name = "contact")
    private String contact;

    @Column(name = "info", columnDefinition = "TEXT")
    private String info;

    @Column(name = "fare", columnDefinition = "TEXT")
    private String fare;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url", columnDefinition = "TEXT")
    private String imageUrl;
}
