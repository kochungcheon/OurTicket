package ko.ourticket.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;
    @Column(unique = true, nullable = false)
    private String nickName;
    @CreatedDate
    private LocalDateTime createdDateTime;
    @Column(nullable = false)
    private String password;
    private Long accountId;

    protected Member(){
    }

    private Member(final String nickName, final String password) {
        this.nickName = nickName;
        this.password = password;
    }

    public static Member of(final String nickName, final String password) {
        return new Member(nickName, password);
    }
}
