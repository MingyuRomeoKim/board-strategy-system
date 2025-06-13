# 사전과제 - BackEnd

Spring boot 기반 RestAPI 어플리케이션

## 프로젝트 설계

### backend 프로젝트 구조

```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/mingyu/homework/
│   │   │   ├── api/
│   │   │   │   ├── v1/
│   │   │   │   │   ├── controller/                # REST API 컨트롤러
│   │   │   │   │   │   └── PostController.java
│   │   │   │   │   ├── dto/                       # 요청/응답 DTO
│   │   │   │   │   │   ├── request/
│   │   │   │   │   │   └── response/
│   │   │   │   │   ├── entity/                    # JPA Entity
│   │   │   │   │   │   └── Post.java
│   │   │   │   │   ├── repository/                # JPA Repository
│   │   │   │   │   │   └── PostRepository.java
│   │   │   │   │   ├── service/                   # 비즈니스 로직
│   │   │   │   │   │   ├── PostService.java
│   │   │   │   │   │   └── strategy/              # 게시글 로딩 전략 (전략 패턴)
│   │   │   │   │   │       ├── LoadStrategy.java
│   │   │   │   │   │       ├── PagingStrategy.java
│   │   │   │   │   │       └── InfiniteScrollStrategy.java
│   │   │   │   │   └── config/                    # Redis, Caching, Swagger 설정
│   │   │   │   │       ├── RedisCacheConfig.java
│   │   │   │   │       └── SwaggerConfig.java (선택)
│   │   │   └── HomeworkApplication.java           # Spring Boot 메인 클래스
│   │   └── resources/
│   │       ├── application.yml                    # 공통 설정
│   │       ├── application-dev.properties         # dev 전용 설정 (도커용)
│   │       ├── application-test.properties        # 테스트 전용
│   │       └── static/ or templates/              # 정적 파일 or 타임리프 사용시
│
│   ├── test/
│   │   └── java/com/mingyu/homework/
│   │       ├── api/v1/service/                    # PostService 테스트
│   │       └── api/v1/controller/                 # 통합 테스트
│
├── build.gradle
├── settings.gradle
└── README.md
```
