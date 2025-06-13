# 사전과제 - FrontEnd

React 기반 사용자 어플리케이션

## 프로젝트 설계

### frontend 프로젝트 구조

```
frontend/
├── public/
├── src/
│   ├── api/                    # Axios API 호출 정의
│   │   └── posts.ts
│
│   ├── components/             # 공통 UI 컴포넌트
│   │   ├── PostCard.tsx        # 게시글 카드 (MUI Card)
│   │   └── StrategyToggle.tsx  # 전략 선택 UI (Toggle)
│
│   ├── pages/                  # 페이지 단위 컴포넌트
│   │   └── PostListPage.tsx    # 게시글 리스트 페이지
│
│   ├── strategy/               # 게시글 로딩 전략 hooks
│   │   ├── usePagingStrategy.ts     # 페이징 방식
│   │   ├── useInfiniteScroll.ts     # 무한스크롤 방식
│   │   └── useLoadStrategy.ts       # 전략 선택 추상화 Hook
│
│   ├── types/                  # 타입 정의
│   │   └── post.ts             # 게시글(Post) 타입 정의
│
│   ├── App.tsx
│   ├── index.tsx
│   └── main.css (필요시 스타일)
│
├── package.json
├── tsconfig.json
└── README.md
```
