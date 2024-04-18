package demo.userboard.domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; //primary key

    @Column(name = "name")
    private String name; //회원 이름

    @Column(name = "nickname")
    private String nickname; //회원 닉네임(중복x)

    @Column(name = "email")
    private String email; //회원 이메일(로그인 아이디 중복x)

    @Column(name = "password")
    private String password; //회원 비밀번호

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender; //회원 성별(M,W)

    @Column(name = "age")
    private Integer age; //회원 나이

    @Column(name = "created_at")
    private LocalDateTime createdAt; //생성일시

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt; //수정일시

    @Builder
    public User(String name, String nickname, String email, String password, Gender gender, Integer age) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.age = age;
    }

    public void updateUserInfo(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }
}