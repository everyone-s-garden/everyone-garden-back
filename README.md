# everyone-garden-back
## 로그인 구조
![image](https://user-images.githubusercontent.com/108210958/235381155-e39fd5c6-124f-4eac-9f0b-fdab67ee659b.png)

**Kakao**

Request 

```jsx
GET /auth/kakao HTTP 1.1

Authorization:  access token

Accept :  */*

Connection : keep-alive

Content-Type : application/json
```

Response

```jsx
HTTP/1.1 : 200OK
Content-Type : application/json

{
    "appToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNzcxMzc0MTA0Iiwicm9sZSI6IlJPTEVfVVNFUiIsImV4cCI6MTY4MzI4NTc0NH0.o0Um132beR0w8sPSU7Hx5VMhv5mqxlR5zGwWnXrS1Bg",
    "isNewMember": true
}
```

**Google**

Request 

```jsx
GET /auth/google HTTP 1.1

Authorization:  access token

Accept :  */*

Connection : keep-alive

Content-Type : application/json
```

Response

```jsx
HTTP/1.1 : 200OK
Content-Type : application/json

{
    "appToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNzcxMzc0MTA0Iiwicm9sZSI6IlJPTEVfVVNFUiIsImV4cCI6MTY4MzI4NTc0NH0.o0Um132beR0w8sPSU7Hx5VMhv5mqxlR5zGwWnXrS1Bg",
    "isNewMember": true
}
```

**Refresh**

Request 

```jsx
GET /auth/refresh HTTP 1.1

Authorization:  Bearer appToken

Accept : */*

Connection : keep-alive

Content-Type : application/json
```

Response

```jsx
HTTP/1.1 : 200OK
Content-Type : application/json

{
    "appToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNzcxMzc0MTA0Iiwicm9sZSI6IlJPTEVfVVNFUiIsImV4cCI6MTY4MzI4NjI2N30.RBpZ9sFgC1uak1WpAA0hwHGshxMj8xXnnWvB4aB4hsM",
    "isNewMember": null
}

```

- 만료에 대한 응답 

---

```jsx
상태코드 : 403 forbidden
```
