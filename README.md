# WhereECO Backend

## 개발환경

- DB: MariaDB
- Spring Boot 2.6.x
- build tool : gradle
- JDK 11
- Spring Data JPA

## 파일/폴더 안내

- /controller : 웹과 통신하기 위한 컨트롤러와 DTO
- /controller/api : 모바일 앱과 통신하기 위한 컨트롤러와 DTO
- /domain : 사용자, 인증 토큰, 제로웨이스트샵 주소 등 서비스 핵심 구성요소의 집합
- /global/config : 애플리케이션 전체에 적용되는 환경설정 자료
- /global/interceptor : 인터셉터의 모음. 현재(22-10-18)는 웹과 앱의 인증여부를 체크하는 3종류의 인터셉터가 있음.
- /global/error : 에러 발생 시의 응답 양식을 설정. 현재 비활성화


## 스크린 샷
<img width="330" src ="https://user-images.githubusercontent.com/72500673/196348330-bc481a05-6411-4543-89e1-e3ad67cd63ee.png"><img width="330" src ="https://user-images.githubusercontent.com/72500673/196348369-4e5a7e1d-085c-4700-a38f-3565a5e48fcd.png">

<img width="330" src ="https://user-images.githubusercontent.com/72500673/196348411-fbad4962-08a1-4d8f-aad4-911edd2c7347.png"><img width="330" src ="https://user-images.githubusercontent.com/72500673/196348451-a736fa36-49f0-430c-b087-8abdbc7f3a99.png">

<img width="330" src ="https://user-images.githubusercontent.com/72500673/196348531-1a1edf20-2d55-4e5d-bb8c-425f5d67131c.png">
