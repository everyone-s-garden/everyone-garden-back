![image](https://github.com/everyone-s-garden/everyone-garden-back/assets/108210958/21d97102-2f8d-4415-b0ef-8d20119b5c42)


## 직접 사용해보기
https://everyonesgarden.vercel.app/

## 어떤 서비스인가요? 
- 소규모 텃밭을 가꾸고자 하는 사람들의 수요가 높아지고 텃밭 분양 정보의 분산으로 어려움을 겪는 사람들을 위해
- 공공 분양과 민간 분양을 한 곳에 볼 수 있도록
- 그리고 쉬운 분양을 도와주는 서비스 입니다.
## 멤버 구성 및 개발기간
- 멤버 구성
  - 백앤드 2명, 프론트 2명, 디자이너 1명
- 개발기간 : 2023년 3월 ~ 2023년 6월
## 주요 기능
- `Spring Security`를 이용한 OAuth2.0 로그인 기능 구현
- 현재 위치 기준, 공공 및 민간 분양 목록 제공
- 현재 위치 미제공시 검색을 통한 목록 제공
- `Spring Batch`를 이용한 공공 텃밭 DB 구축
- reverse geocoding API, 공공 API를 이용하여 현재 위치 기준 날씨 데이터 제공
- 허위 매물에 대한 신고 기능 제공하여 신고 누적 점수가 넘어갈 시 soft delete를 이용하여 게시글 목록에서 제외
- 공공 API를 이용한 월별 추천 작물 정보 제공
- Validation을 통한 분양 및 본인이 가꾸는 텃밭 게시글 등록, 수정
- `S3`를 이용한 이미지 업로드

## 사용 기술
### 협업툴
![Slack](https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white)
![Notion](https://img.shields.io/badge/Notion-%23000000.svg?style=for-the-badge&logo=notion&logoColor=white)
![Figma](https://img.shields.io/badge/figma-%23F24E1E.svg?style=for-the-badge&logo=figma&logoColor=white)
### DB
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
![jpa](https://img.shields.io/badge/JPA-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
### Server
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
![springboot](https://img.shields.io/badge/Springboot-6DB33F?style=for-the-badge&logo=SpringBoot&logoColor=white)
![springsecurity](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)

### DevOps

![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white)
- EC2
- RDS (MySQL)
- S3
