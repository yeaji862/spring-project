# 🐕 반려동물 SNS <Pets>

`인원수: 1명 / 기간: 2023.03.21 ~ 2023.04.05`

http://www.projectk.r-e.kr:8080


> 실제로 강아지를 키우고 있는데 사람이 주가 아닌 반려동물이 주가 되는 SNS를 만들어 반려동물들의 일상을 서로 공유하고 확인 할 수 있는 사이트를 제작하고 싶다는 목적으로 사이트를 만들게 되었습니다
> 
<br>



## 📌 사용한 기술 스택

**FRONT** <br>
<img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> <img src="https://img.shields.io/badge/thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=black"> <img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=black"> <img src="https://img.shields.io/badge/AJAX-005F0F?style=for-the-badge&logo=logoColor=black">

**BACK** <br>
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/MyBatis-6DB33F?style=for-the-badge&logo=&logoColor=white"> 

**API**  <br>
<img src="https://img.shields.io/badge/googleLogin-4285F4?style=for-the-badge&logo=google&logoColor=white"> <img src="https://img.shields.io/badge/coolSMS-4285F4?style=for-the-badge&logo=&logoColor=white">

**TOOL** <br>
<img src="https://img.shields.io/badge/intellijidea-000000?style=for-the-badge&logo=intellijidea&logoColor=white"> <img src="https://img.shields.io/badge/visualstudiocode-007ACC?style=for-the-badge&logo=visualstudiocode&logoColor=white">

<br>

## 👩🏻‍💻 담당기능

- Security config 파일을 생성하여 해당 프로젝트의 보안기능 추가
- oauth 로그인을 사용하여 해당 유저의 회원넘버를 받아 데이터에 저장
- coolSMS API를 활용하여 로그인/비밀번호 찾기 시에 전화번호 인증 기능 추가
- 회원간의 팔로우 팔로잉 기능 / 회원의 게시물 관리 / 좋아요 / 댓글 대댓글 / 프로필, 회원 검색 기능, 페이지 제작
- AWS EC2로 프로젝트 배포
- spring → spring boot 를 사용하여 jar파일로 배포
- jsp → html로 바꿔 Thymeleaf 활용
- myBatis는 기존 xml파일만 사용하는 것이 아닌 인터페이스를 활용하여 사용

<br>

## 추가 할 기능

1. **신고 기능** - 반려동물이 주인 sns 이다보니 동물 사진 외의 게시물은 신고 기능을 추가하여 사용자 목적에 맞는 게시물을 제공할 수 있게끔 신고 기능 추가 예정
2. **관리자 페이지** - 신고 기능과 전반적인 사이트 운영 관리에 필요한 관리자 페이지를 추가 예정
3. **JPA로 마이그레이션**
