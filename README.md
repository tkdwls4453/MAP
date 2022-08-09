# **여행 계획 서비스 - MAP 🗺️**

> 💡
*"다른 사람들은 어디가지?"*  궁금해 하는 사람들을 위한 여행계획 서비스 **MAP(Make A Plan)** 입니다.
 
<br></br>
## 1. 프로젝트 소개 & 참여 인원
<details>
    <summary>🧑‍💻팀원 소개 </summary>

| [이광민](https://github.com/leawvc) | [강문정](https://github.com/dev-kmj) | [윤상진](https://github.com/tkdwls4453) |
| :---: | :---: | :---: | 
| <img src="https://user-images.githubusercontent.com/74237301/181447155-f76af3e4-2177-4bba-9276-10ded4eb5385.gif" width="150" height="150"> | <img src="https://avatars.githubusercontent.com/u/74237301?v=4" width="150" height="150"> | <img src="https://user-images.githubusercontent.com/74237301/181447271-7690456e-a29b-4d01-92ac-d04efdb81367.png" width="150" height="150"> |
| 팀장, 개발자 | 개발자 | 개발자 | 
</details>

---
<details>
   <summary>🏝️ 프로젝트 설명</summary>

<p>여행 계획을 세우기 어려워하는 사람들을 위한 서비스입니다.</p>
코로나 규제가 많이 완화되면서 많은 사람들이 그동안 하지 못했던 여행이나 
야외 데이트를 많이 하고 있는 요즘, 여행 게획을 세우기 힘들어 하는 사람들을 위한 
커뮤니티 서비스를 기획하게 되었습니다. <br>
MAP(make a plan)은 이름에서도 알 수 있듯이 내가 세운 계획을 지도에 직관적으로 나타낼 수 있고 
다른 사용자들과 여행 계획을 공유하며 자유롭게 소통할 수 있게 해줍니다.
</details>


***


<br></br>
## 2. 사용 기술
<details>
  <summary> 사용 기술 </summary>
 
 * Java 11
 * Spring Boot 2.7.1
 * Gradle
 * Spring Data JPA
 * Spring Security
 * RDS
 * AWS EC2 
 * AWS S3
 * AWS ElasticBeanstalk
 * Github Action
 * Kakao Maps API
 * Kakao Login REST API
 
</details>

***

<br></br>
## 3. 내가 맡은 기능

1. JWT를 이용한 로그인 기능 
2. 이미지 파일 S3 업로드 기능
3. 여행 일정 CRUD 기능
4. CI/CD 환경 구축

<br></br>
## 4. ERD

<details>
   <summary>ERD 이미지</summary>
<img src="https://user-images.githubusercontent.com/74237301/181441959-25dabbf7-0e35-40c1-ad3b-817b8066833d.JPG">
</details>

***

<br></br>
## 5. 시스템 아키텍처  
<details>
   <summary>아키텍처 이미지</summary>
<img src="https://user-images.githubusercontent.com/74237301/181436941-8ef4f212-3497-432c-a297-bc6ed94ff246.JPG">
</details>

<br></br>
## 6. 트러블 슈팅
### 5.1. Gitub Action과 ElasticBeanstalk 연동 문제
- 이번 프로젝트는 기본 기능들만 우선 구현하고 빠르게 배포 후 필요한 기능들을 추가하는 방향으로 진행햐였습니다. 개발과 배포가 빈번히 일어날 것으로 예상하여 Github Action과 ElasticBeanstalk를 이용하여 CI/CD 환경을 구축하고자 했습니다.
- https://wonit.tistory.com/597 블로그 글을 참고하여 EB에 배포했지만 EB 상태가 정상적이지 않아 정상 작동하지 않는 문제가 생겼습니다. 이유는 health cheak가 제대로 이루어 지지 않고 있었습니다.
- EB의 상태검사 경로를 actuator/health 로 지정하고 spring에서 acuator를 빌드하여 사용하려 했지만 swagger와 충돌 이슈가 생겨 actuator/health 경로의 controller를 만들어 200 상태코드를 반환하도록 하여 해결하였다.





