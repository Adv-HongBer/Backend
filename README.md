# HongBer 고도화 - 백엔드
___
## 2022-07-28
+ 구름 IDE를 이용한 DB 서버 세팅
  + [구름 IDE](https://goorm.co)
## 2022-07-29
+ 기본 설정파일 수정
  + jasypt 플러그인 추가 및 세팅
    + 이후 배포 시 java -jar --jasypt.encryptor.password=["password"] 의 형태로 빌드 후 컴파일하는 방식으의 스크립트 추가 적용 예정
  + 메인 클래스 네이밍 단축
  + 상수 클래스, enum 추가
  + 유틸함수 추가
  + 플러그인 추가
  + 날짜 입력 실수로 커밋 메세지 수정을 통한 머지 실행
### 2022-07-30
+ 프론트엔드 협업자와 대면 회의 진행
+ DB TABLE 생성 및 DDL 백업
+ JPA 사용을 위한 관련 Entity, Repository 생성 및 테스트 진행
  + CamelCase 사용을 위한 JPA 설정 변경 및 application.yml에서 JPA 설정을 활성화
+ jar 파일 생성을 위한 build.gradle 설정 추가
### 2022-07-31
+ application.yml에 로그파일안에 JPA 파라미터 값이 출력되도록 추가
+ logback-spring.xml 주석 코드 제거
+ JPA Auditing 추가
+ 공통 entity 추가
+ 회원 관련 entity 추가 및 repository 추가
+ 회웝가입, 로그인 controller, service, repository 추가
### 2022-08-1
+ 암호화를 위한 aop 제작, 작업의 간단화를 위한 entity 네이밍 수정, 현재 오류로 인한 디버깅 작업 진행
### 2022-08-02
+ BaseDTO 생성, 오류 메세지 파일 위치 변경, BaseDTO 생성에따른 날짜 포맷 추가, 누락 어노테이션 추가
+ 현 상황 정리 : DB삽입전 암호화 어노테이션 개발 진행, 이외 암호화 알고리즘 관련 유틸함수 개발 진행 이후 레거시 코드 정리 예정