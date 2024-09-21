package team.tripbeats.backend.domain.post.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.tripbeats.backend.entity.Account;

import java.time.LocalDateTime;  // LocalDateTime 추가
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String category;   // 카테고리 추가
    private String location;   // 장소 추가
    private String schedule;   // 일정 추가
    private String image;      // Base64로 인코딩된 이미지

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    private LocalDateTime timestamp;  // 작성 시간, LocalDateTime으로 저장

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments;
}
