package vn.techmaster.demothymeleafspringsecurity.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "token_confirms")
public class TokenConfirm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    private String token;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    private Date createdAt;
    private Date expiredAt;
    private Date confirmedAt;

    public enum TokenType {
        REGISTRATION, FORGOT_PASSWORD
    }

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }
}
