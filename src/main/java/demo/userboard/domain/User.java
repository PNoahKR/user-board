package demo.userboard.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id; //primary key

    @Column(name = "name", length = 30, nullable = false)
    private String name; //회원 이름

    @Column(name = "nickname", length = 5, unique = true, nullable = false)
    private String nickname; //회원 닉네임(중복x)

    @Column(name = "email", length = 30, unique = true, nullable = false)
    private String email; //회원 이메일(로그인 아이디 중복x)

    @Column(name = "password", length = 20, nullable = false)
    private String password; //회원 비밀번호

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender; //회원 성별(M,W)

    @Column(name = "age")
    private Integer age; //회원 나이

    @Builder
    public User(String name, String nickname, String email, String password, Gender gender, Integer age) {

        Assert.hasText(name, "이름을 입력해주세요.");
        Assert.hasText(nickname, "닉네임을 입력해주세요.");
        Assert.hasText(email, "이메일을 입력해주세요.");
        Assert.hasText(password, "비밀번호를 입력해주세요.");

        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.age = age;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}