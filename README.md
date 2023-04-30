## v1 로그인 설계 (현재 폴터)

프런트에서 리소스 서버에 access token과 id token을 가지고 접근해서 유저 정보를 받아서 백엔드에 전달하는 방법-> 이를 DB에 저장하고 JWT 토큰 반환

## v2 로그인 설계 (auth_v2_byeol)
프런트에서 access token과 id token 백엔드에 전달, 백엔드가 이 두가지를 가지고 리소스 서버에 접근하여 유저 정보를 받고 DB에 저장, JWT 토큰 반환
(프런트분들 궁금하신 사항 있으시면 슬랙으로 말씀 부탁드립니다.)

![image](https://user-images.githubusercontent.com/108210958/235381155-e39fd5c6-124f-4eac-9f0b-fdab67ee659b.png)

