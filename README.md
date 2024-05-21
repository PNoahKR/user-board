# 게시판 프로젝트

## 프로젝트 개요

- 간단한 회원가입 및 로그인 기능과 게시판 기능을 제공하는 웹어플리케이션 개인 프로젝트입니다.
- 프로젝트 기간 : 2024.04.08~ 2024.05.17

## 📚 기술 스택

- 언어 : JAVA
- 프레임워크 : Spring boot, Spring data JPA, Spring Security, Spring Web, Spring Validation, Lombok
- 데이터베이스 : Mysql

## 📖 주요기능

<details>
<summary><strong>User</strong></summary>

### User

- 회원가입 : 이름, 닉네임, 나이, 성별, 이메일, 비밀번호를 입력해 가입
- 로그인 : 이메일과 비밀번호를 통해 로그인
- 로그아웃 : 로그인되 사용자 로그아웃
- 내 정보 수정 : 닉네임, 비밀번호 수정

</details>

<p></p>

<details>
<summary><strong>Board</strong></summary>

### Board

- 게시글 작성 : 로그인한 사용자가 게시글 작성
- 게시글 조회 : 목록 조회, 상세 조회
- 게시글 수정 및 삭제 : 본인 게시글 수정 및 삭제

</details>

## 📝 요구사항

<details>
<summary><strong>User</strong></summary>

### User

#### 회원가입

- 이름, 닉네임, 나이, 성별, 이메일, 비밀번호는 필수 값으로 한다.
- 이메일은 중복으로 등록될 수 없다.
- 비밀번호는 단방향 암호화로 저장한다.
- 비밀번호는 4자 이상 입력해야한다.

#### 로그인

- 이메일의 존재여부를 체크한다.
- 비밀번호를 체크한다.
- 로그인은 Session을 사용하여 로그인 상태를 확인한다.

#### 로그아웃

- 로그인돼 있는 정보를 로그아웃 처리한다.

#### 내 정보 수정

- 닉네임과 비밀번호를 수정할 수 있다.
- 비밀번호는 회원가입때 체크하는 로직과 동일하다.
- 수정 완료 시 수정된 내용이 즉시 반영된다.

</details>

<p></p>

<details>
<summary><strong>Board</strong></summary>

### Board

#### 게시글 작성

- 로그인 한 사용자만 게시글을 작성할 수 있다.
- 게시글 작성 시 제목과 본문을 입력할 수 있다.
- 제목과 본문은 빈값이면 안된다.

#### 게시글 조회

- 게시글 목록을 조회할 수 있다.
- 게시글 목록은 페이징 처리한다.
- 게시글 목록은 한 페이지당 10개씩 노출한다.
- 게시글 상세 내용을 조회할 수 있다.
- 게시글 상세내용은 작성자의 닉네임, 작성시간, 수정시간, 제목, 본문이 노출된다.

#### 게시글 수정 및 삭제

- 게시글 수정 및 삭제는 작성자 본인만 할 수 있다.
- 게시글 수정은 제목과 본문을 수정할 수 있다.
- 게시글 수정 시 제목과 본문은 빈값이면 안된다.
- 게시글 삭제는 본인만 할 수 있다.
- 게시글 삭제는 Soft Delete로 처리한다.

</details>

## 💻 구현

1. ERD 설계

<details>
<summary><strong>ERD</strong></summary>
<img width="445" alt="UserBoard_ERD" src="https://github.com/PNoahKR/user-board/assets/156992925/721aa737-4769-4370-a89d-5e7924d59b58">

```bash
CREATE TABLE `board` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `content` text NOT NULL,
  `user_id` int DEFAULT NULL,
  `created_at` datetime DEFAULT (now()),
  `modified_at` datetime DEFAULT (now()),
  `status` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `board_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `nickname` varchar(5) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(60) NOT NULL COMMENT 'Bcrypt',
  `gender` varchar(6) DEFAULT NULL COMMENT 'female or male',
  `age` int DEFAULT NULL,
  `created_at` datetime DEFAULT (now()),
  `modified_at` datetime DEFAULT (now()),
  PRIMARY KEY (`id`),
  UNIQUE KEY `nickname` (`nickname`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```

</details>

2. API 설계

<details>
<summary><strong>사용자 API</strong></summary>

### 사용자 API

#### 회원가입

- **Endpoint**: `POST /user`
- **Request Body**:
    ```json
    {
      "name" : "테스트", 
      "nickname" : "test", 
      "email" : "test@test.kr", 
      "password" : "password", 
      "gender" : "M", 
      "age" : 20
  }
    ```
- **Response**:
    ```json
    {
      "status": 200,
      "message": "success",
      "data": {
        "message": "성공",
        "id": 1,
        "statusCode": 200
      }
    }
    ```

#### 로그인

- **Endpoint**: `POST /login`
- **Request Body**:
    ```json
    {
      "email": "test@test.kr",
      "password": "password"
    }
    ```
- **Response**:
    ```json
    {
      "status": 200,
      "message": "success",
      "data": 1
    }
    ```
- **Authentication**: 세션이 생성되고 사용자 ID가 세션에 저장됨

#### 사용자 정보 조회

- **Endpoint**: `GET /user/info`
- **Response**:
    ```json
    {
      "status": 200,
      "message": "success",
      "data": {
        "name" : "테스트", 
        "email" : "test@test.kr",      
        "nickname" : "test", 
        "gender" : "M", 
        "age" : 20
      }
    }
    ```
- **Authentication**: 세션을 통해 인증된 사용자만 접근 가능

#### 사용자 정보 수정

- **Endpoint**: `PUT /user/info`
- **Request Body**:
    ```json
    {
      "nickname": "test2",
      "password": "00000"
    }
    ```
- **Response**:
    ```json
    {
      "status": 200,
      "message": "success",
      "data": 1
    }
    ```
- **Authentication**: 세션을 통해 인증된 사용자만 접근 가능

#### 로그아웃

- **Endpoint**: `POST /logout`
- **Response**:
    ```json
    {
      "status": 200,
      "message": "success",
      "data": null
    }
    ```
- **Authentication**: 세션이 만료되고 사용자는 로그아웃됨

</details>

<p></p>

<details>
<summary><strong>게시판 API</strong></summary>

### 게시판 API

#### 모든 게시글 목록 조회

- **Endpoint**: `GET /boards`
- **Parameters**:
    - `page` (optional): 페이지 번호, 기본값은 1
    - `size` (optional): 페이지 크기, 기본값은 10
- **Response**:
    ```json
    {
      "status": 200,
      "message": "success",
      "data": {
        "content": [
            {
                "boardId": 2,
                "title": "테스트제목2",
                "writer": "작성자2"
            },
            {
                "boardId": 1,
                "title": "테스트제목1",
                "writer": "작성자1"
            }
          ],
          "paging": {
               "pageNumber": 1,
               "pageSize": 10,
               "hasNext": false,
               "numberOfElements": 2
          }
      }
    }
    ```

#### 게시글 상세 조회

- **Endpoint**: `GET /board/{boardId}`
- **Parameters**:
    - `boardId`: 게시글 ID
- **Response**:
    ```json
    {
      "status": 200,
      "message": "success",
      "data": {
        "title": "테스트제목1",
        "createdAt": "2024-05-21T00:00:00",
        "modifiedAt": "2024-05-21T00:00:00",
        "userNickname": "작성자1",
        "content": "테스트게시물입니다."
      }
    }
    ```

#### 게시글 작성

- **Endpoint**: `POST /board`
- **Request Body**:
    ```json
    {
      "title": "테스트제목3",
      "content": "이것도 테스트 게시물입니다."
    }
    ```
- **Response**:
    ```json
    {
      "status": 200,
      "message": "success",
      "data": 3
    }
    ```
- **Authentication**: 세션을 통해 인증된 사용자만 접근 가능

#### 게시글 수정

- **Endpoint**: `PUT /board/{boardId}`
- **Request Body**:
    ```json
    {
      "title": "updated 테스트제목3",
      "content": "Updated content"
    }
    ```
- **Response**:
    ```json
    {
      "status": 200,
      "message": "success",
      "data": 3
    }
    ```
- **Authentication**: 세션을 통해 인증된 사용자만 접근 가능

#### 게시글 삭제

- **Endpoint**: `DELETE /board/{boardId}`
- **Response**:
    ```json
    {
      "status": 200,
      "message": "success",
      "data": 3
    }
    ```
- **Authentication**: 세션을 통해 인증된 사용자만 접근 가능

</details>

## 🧑‍💻 프로젝트 진행 중 고려한 사항들

### 1. 패키지 구조
<details>
<summary>패키지 구조</summary>
객체지향적인 패키지 구조로 계층형 패키지 형태로 설계해 구조를 간단하게 파악할 수 있도록 만들었습니다.

---

- **controller** : 웹 요청을 처리하는 컨트롤러
- **dto** : 데이터 전송 객체
    - **request** : 클라이언트 요청 데이터를 담는 객체
    - **response** : 서버 응답 데이터를 담는 객체
- **domain** : JPA 엔티티 클래스
- **global** : 프로젝트 전역에 사용되는 구성 요소
    - **annotation** : 커스텀 어노테이션
    - **common** : 공통 응답처리 및 유티리티
    - **core** : config, 예외 처리 등 핵심 요소
- **repository** : 데이터베이스 접근을 담당하는 리포지토리
- **service** : 비즈니스 로직을 담당하는 서비스 클래스
</details>

### 2. 공통 응답 및 예외 처리

<details>
<summary>공통 응답</summary>

#### 공통 응답

모든 API의 응답은 'CommonResponse' 객체를 통해 반환하도록 설계했으며, 성공과 실패에 따라 API 응답이 일관될 수 있도록 'ApiResponseUtil'을 통해 메서드를 제공합니다.

**CommonResponse**

```java

@Getter
public class CommonResponse<T> {

    private final Integer status;
    private final String message;
    private final T data;

    public CommonResponse(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public CommonResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }
}
```

- status: HTTP 상태 코드
- message: 응답 메세지 (성공시 'success' 반환, 실패시 오류 메세지 반환)
- data: 응답 데이터 (없을시 null)

---

**ApiResponseUtil**

```java
public class ApiResponseUtil {

    public static <T> CommonResponse<T> success() {
        return new CommonResponse<>(SUCCESS.getStatus().value(), SUCCESS.getMessage(), null);
    }

    public static <T> CommonResponse<T> success(T response) {
        return new CommonResponse<>(SUCCESS.getStatus().value(), SUCCESS.getMessage(), response);
    }

    public static <T> CommonResponse<T> failure(CustomErrorCode errorCode) {
        return new CommonResponse<>(errorCode.getStatus().value(), errorCode.getMessage());
    }

    public static <T> CommonResponse<T> failure(Integer statusCode, String message) {
        return new CommonResponse<>(statusCode, message);
    }
}
```

</details>
<p></p>
<details>
<summary>예외 처리</summary>

#### 예외 처리

해당 어플리케이션에서 발생한는 예외는 'GlobalExceptionHandler' 클래스를 통해 응답을 반환하고, 로그를 남기며 특정 예외를 커스텀 처리할 수 있도록 합니다.

**GlobalExceptionHandler**

```java

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    // 추가해야 하는 Exception 추가 정의

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<CommonResponse<Object>> handleException(Exception exception) {
        log.error("Exception", exception);

        return toErrorResponseEntity(SERVER_ERROR);
    }

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<CommonResponse<Object>> handleBadRequestException(CustomException exception) {
        log.warn("CustomException", exception);

        return toErrorResponseEntity(exception.getErrorCode());
    }

    protected ResponseEntity<CommonResponse<Object>> toErrorResponseEntity(CustomErrorCode errorCode) {

        return ResponseEntity
                .status(errorCode.getStatus().value())
                .body(ApiResponseUtil.failure(errorCode));
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        log.error("Exception", exception);
        CommonResponse<Object> response = ApiResponseUtil.failure(statusCode.value(), exception.getMessage());
        return ResponseEntity.status(statusCode).body(response);
    }
}
```

---
'CustomException'은 'RuntimeException'을 상속받아 커스텀 예외를 정의하고, 'CustomErrorCode'를 포함해 예외 발생시 특정 코드에 대한 정보를 제공합니다.

**CustomException**

```java

@Getter
public class CustomException extends RuntimeException {
    private final CustomErrorCode errorCode;

    public CustomException(CustomErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
```

---
'CustomErrorCode'를 통해 특정 에러코드를 정의하고, 응답 코드는 HTTP 상태 코드와 메세지를 정의합니다.

**CustomErrorCode**

```java

@Getter
public enum CustomErrorCode {
    // 공통 응답 코드
    SUCCESS(OK, "success"),
    SERVER_ERROR(INTERNAL_SERVER_ERROR, "server error"),
    // 사용자 정의 코드
    LOGIN_FAIL(HttpStatus.BAD_REQUEST, "로그인 실패"),
    NOT_FOUND_LOGIN_INFO(HttpStatus.NOT_FOUND, "계정 정보를 찾을 수 없습니다."),
    NOT_FOUND_BOARD_INFO(HttpStatus.NOT_FOUND, "게시물 정보를 찾을 수 없습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증 실패"),
    DUPLICATE_VALUE(HttpStatus.CONFLICT, "중복 오류"),
    INVALID_FORMAT(HttpStatus.BAD_REQUEST, "유효하지 않은 입력입니다.");

    private final HttpStatus status;
    private final String message;

    CustomErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
```

</details>

### 3. 로그인 인증 방식

<details>
<summary>세션 인증 방식</summary>
로그인 인증방식은 이번 프로젝트에서 세션 기반 인증 방식을 사용하기로 했다.

세션 방식을 사용하면서 세션 로그인 체크 로직에 대한 부분이 중복적으로 들어가게 됐고 이를 공통화하는 방안으로 ArgumentResolver를 사용해 구현하게됐다.

**SessionAuth**

```java

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface SessionAuth {
}
```

로그인 체크를 위한 커스텀 어노테이션

---
**SessionLoginInfo**

```java

@Getter
@AllArgsConstructor
public class SessionLoginInfo {

    private Long id;
    private String nickname;
    private String email;

    public static SessionLoginInfo from(User user) {
        return new SessionLoginInfo(
                user.getId(),
                user.getNickname(),
                user.getEmail()
        );
    }
}
```

로그인 정보가 필요한 API에 전달하기 위한 로그인 정보 DTO 객체

---
**SessionLoginArgumentResolver**

```java

@Slf4j
@Component
@RequiredArgsConstructor
public class SessionLoginArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;
    private final UserRepository userRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAnnotation = parameter.hasParameterAnnotation(SessionAuth.class);
        boolean isLongInfo = SessionLoginInfo.class.isAssignableFrom(parameter.getParameterType());

        return hasAnnotation && isLongInfo;

    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (ObjectUtils.isEmpty(httpSession.getAttribute(LOGIN_USER))) {
            throw new CustomException(CustomErrorCode.UNAUTHORIZED);
        }

        Long userId = (Long) httpSession.getAttribute(LOGIN_USER);

        return userRepository.findById(userId)
                .map(SessionLoginInfo::from)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_LOGIN_INFO));
    }
}
```

API에 파라미터가 SessionAuth 어노테이션을 가지고 있고, SessionLoginInfo를 가지고 있으면 해당 리졸버가 동작합니다

---
**WebConfig**

```java

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final SessionLoginArgumentResolver sessionLoginArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(sessionLoginArgumentResolver);
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
    }
}
```

Spring에서 제공해주고 있는 HandlerMethodArgumentResolver에 SessionLoginArgumentResolver를 추가하기 위한 로직입니다.

</details>

### 4. 패스워드 암호화

<details>
<summary>패스워드 암호화</summary>

**PasswordConverter**

```java

@Converter
@RequiredArgsConstructor
public class PasswordConverter implements AttributeConverter<String, String> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String convertToDatabaseColumn(String attribute) {

        return passwordEncoder.encode(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData;
    }
}
```

AttributeConverter를 상속받아 엔티티에 패스워드 속성을 데이터베이스에 저장할 때 암호화를 합니다.
패스워드를 데이터베이스에 암호화된 형태로 저장고, 데이터베이스에서 불러올 땐 그대로 불러오는 단반향 암호화로 구현했습니다.

---
**PasswordCrypto**

```java

@RequiredArgsConstructor
@Component
public class PasswordCrypto {

    private final PasswordEncoder passwordEncoder;

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
```

'encodePassword' 메서드로 패스워드를 암호화하고,
'matches' 메서드로 원본 패스워드와 암호화된 패스워드의 일치 여부를 확인합니다.

---
**SecurityConfig**

```java

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests(
                        (authorizeHttpRequests) -> authorizeHttpRequests.anyRequest().permitAll()
                )
                .logout((logout) -> logout.disable());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

PasswordEncoder의 BCryptPasswordEncoder을 빈으로 등록해 사용할 수 있도록 했으며,
HTTP 보안 설정에서 CSRF 보호를 비활성화하고, 모든 요청을 허용하며, 로그아웃 기능을 비활성화했습니다.

</details>
