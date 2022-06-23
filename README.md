# Backend
find-together (BE)

API Documentation : http://localhost:8080/swagger-ui/#/    

**Backend 실행시 필수 사항!** 
1. main/resources/application.yml 다음과 같이 변경 
```
spring:
  datasource:
    url: jdbc:mysql://118.67.128.252:3306/findtogether?serverTimezone=Asia/Seoul
    username: root
    password: 1234
    driver-class-name: com.mysql.cj.jdbc.Driver
    
file:
  upload:
    location: 자신의 주소/Frontend/public/img
```
