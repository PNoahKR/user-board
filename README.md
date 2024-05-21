# 게시판 프로젝트

## 프로젝트 개요

- 간단한 회원가입 및 로그인 기능과 게시판 기능을 제공하는 웹어플리케이션 개인 프로젝트입니다.
- 프로젝트 기간 : 2024.04.08~ 2024.05.17

## 📚 기술 스택

- 언어 : JAVA
- 프레임워크 : Spring boot
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
