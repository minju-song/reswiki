# 🍽️ 맛집위키 백엔드

## 📅 개발 기간
2024.10 ~ 2024.12  
(초기 진행 후 기획 변경, 12월부터 재개발)

## 🛠️ 스택
- **환경**: 
  - ![IntelliJ](https://img.shields.io/badge/IDE-IntelliJ-orange)
  - ![Git](https://img.shields.io/badge/Version%20Control-Git-black)
  - ![GitHub](https://img.shields.io/badge/Repository-GitHub-181717)
  - ![DBeaver](https://img.shields.io/badge/DB%20Tool-DBeaver-blue)
- **개발**: 
  - ![Java](https://img.shields.io/badge/Language-Java-red)
  - ![Spring Boot](https://img.shields.io/badge/Framework-Spring%20Boot-green)
  - ![Postman](https://img.shields.io/badge/API%20Tool-Postman-ff6c37)
- **데이터베이스**: 
  - ![Redis](https://img.shields.io/badge/DB-Redis-dc382d)
  - ![MariaDB](https://img.shields.io/badge/DB-MariaDB-003545)
- **클라우드**: 
  - ![CloudType](https://img.shields.io/badge/Cloud-CloudType-00C4E0)

## 📄 도출 문서
- [요구사항 명세서](https://www.notion.so/1407cdc4a1c380faaf0dda3addf9e844?pvs=4)
- [DB 설계서](https://www.notion.so/DB-bb82d002d0de42c2a6928d844d76d4d5?pvs=4) 
- [API 명세서](https://www.notion.so/API-486f0e07085b488d894e18d6671b601d?pvs=4)
- [API 문서](https://documenter.getpostman.com/view/34639101/2sAYHxmibi)

## 🌟 특징
1. **Full-Mapping 전략** : 요청 DTO와 응답 DTO를 Record를 활용하여 개별적으로 매핑함으로써 코드의 가독성과 유지보수성을 향상시킴.
2. **권한 및 토큰** : JWT와 Spring Security를 활용하여 사용자 권한 관리 및 안전한 토큰 기반 인증을 구현.
3. **AOP활용** : 응답 형태를 통일화하여 클라이언트에게 일관된 데이터를 제공, 코드 중복을 줄이고 유지보수성을 높임.
4. **전역 예외 처리** : 애플리케이션 전역에서 발생하는 예외를 통합적으로 처리하여 사용자에게 명확하고 일관된 오류 메시지를 전달.
5. **장소 검색 기능** : Google Places API를 통해 사용자가 원하는 맛집 정보를 손쉽게 검색할 수 있는 기능을 제공.

## 📸 이미지 첨부
- DB 설계서 다이어그램: (여기에 이미지 첨부)
