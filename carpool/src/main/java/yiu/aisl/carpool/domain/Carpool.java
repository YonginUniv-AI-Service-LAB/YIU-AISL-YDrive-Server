package yiu.aisl.carpool.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Carpool")
public class Carpool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carpoolNum;

    @Column(nullable = false)
    private String start;

    @Column(nullable = false)
    private String end;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;

    @Column(nullable = false)
    private int memberNum;

    @Column(nullable = false, name="User")
    private String email;

    @Column(nullable = false)
    private int checkNum;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
}