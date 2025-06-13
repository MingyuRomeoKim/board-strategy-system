# 사전과제

## 구동 방법

```shell

git clone https://github.com/MingyuRomeoKim/board-strategy-system.git
cd board-strategy-system
docker-compose up -d --build
```

## 프로젝트 설계
- 리스트 만 구현하라고 적혀있어서 리스트만 구현하였으나, 404 내용을 담기 위하여 detail도 함께 담았습니다.
- H2 Inmemory 사용하라 하였지만, Test Profile에서 Inmemory로 하고 Docker에서 H2를 별도로 사용하였습니다.
- Redis를 통한 간단한 캐싱 적용하였습니다.

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
