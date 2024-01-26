# <strong>BackOfficeProject</strong>


## 🎁 프로젝트 개요


- **개발 기간** : 24.01.22 ~ 24.01.29 (1주)
- **개발 환경** : Kotlin, Spring Boot, Supabase, PostgreSql
- **프로젝트 이름** : BackOfficeProject
- **프로젝트 설명 :** 백오피스 프로젝트


## 👩 Team B05

- <strong>장준혁</strong>
    - 역할 - 보드 CRUD 및 시큐리티 부분
- <strong>주형근</strong>
    - 역할 - 포스트 CRUD
- <strong>김현주</strong>
    - 역할 - 코멘트 CRUD
- <strong>이시원</strong>
    - 역할 - 팔로우, 좋아요 기능



## **📚기술스택**

### **Backend**

- Spring Boot: 3.2.2

### **DB**

- SupaBase(postgreSQL): [https://supabase.com/dashboard/projects]

## 🎈 주요기능

### 보드 기능
- 보드 CRUD
  - 인증/인가
    - 일반 유저는 CRUD 불가
    - 보드는 포스트의 상위객체
### 게시글 CRUD
- 게시글 CRUD
  - 인증/인가
    - 로그인이 되어 있어야 게시글 작성 가능
    - 본인의 게시글만 수정/삭제 가능
### 댓글 CRUD
- 댓글 CRUD
  - 인증/인가
    - 로그인이 되어 있어야 댓글 작성 가능
    - 본인의 댓글만 수정/삭제 가능
### 좋아요 기능
- 좋아요 기능
  - 인증/인가
    - 본인의 게시글에 좋아요 불가능
### 팔로우 기능
- 팔로우 기능
  - 인증/인가
    - 본인의 팔로우만 조회,추가,삭제 가능 
### 회원가입/로그인
- 회원가입/로그인
  - JWT Token 기반 인증/인가
    - Spring Security 활용