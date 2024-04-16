package demo.userboard.domain;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class User {

    private Long id; //primary key
    private String name; //회원 이름
    private String nickname; //회원 닉네임(중복x)
    private String email; //회원 이메일(로그인 아이디 중복x)
    private String password; //회원 비밀번호
    private Gender gender; //회원 성별(M,W)
    private Integer age; //회원 나이
    private LocalDateTime createdAt; //생성일시
    private LocalDateTime modifiedAt; //수정일시

    public User() {
    }

    public User(String name, String nickname, String email, String password, Gender gender, Integer age) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.age = age;
    }
}
