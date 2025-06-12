# 사전과제

## 프로젝트 설계

### 전체 프로젝트 구조
```
board-strategy-system/
├── backend/             # Spring Boot 프로젝트
│   ├── build.gradle
│   └── src/...
├── frontend/            # React 프로젝트
│   ├── package.json
│   └── src/...
├── docker/              # 프로젝트 기동 등
│   ├── backend.Dockerfile
│   └── frontend.Dockerfile
├── docker-compose.yml
├── README.md
└── .gitignore
```

### 위 구조로 설계한 이유
1. 관심사의 분리 (Separation of Concerns)
   1. backend/와 frontend/를 분리함으로써, 각각의 빌드 도구(Gradle, Node/NPM)를 충돌 없이 운영할 수 있습니다. 
   2. 유지보수, 디버깅, 테스트 모두 명확히 구분됩니다.

2. 도커 기반 분리 배포에 유리 
   1. 각 서비스를 컨테이너 단위로 나누어 관리 가능합니다. 
   2. docker-compose.yml에서 frontend, backend를 각각 서비스로 설정하면 독립적으로 실행됩니다.

3. Git repo에서 명확한 구조 제공 
   1. 채용 담당자나 평가자가 코드를 열었을 때 구조를 빠르게 파악 가능할 것이라 생각합니다.
