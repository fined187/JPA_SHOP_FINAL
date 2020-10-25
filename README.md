# JPA - SHOP PROJECT

<pre> 개발 framework : spring boot 2.3.2.RELEASE(JPA)
빌드 : mvn install
실행 : java - jar </pre>
<br>

- spring boot, JPA를 이용하여 기본적인 회원관리, 주문, 배송 등을 관리하는 서비스.

- Mapper interface를 활용하여 DTO의 접근을 관리
</br>
---

## 2. Installation

- Open Terminal and typing ```cd Downloads/h2/bin``` 

- ```./h2.sh``` to start h2

- Type ```<dependency>
            <groupId>org.mapstruct</groupId>
             <artifactId>mapstruct</artifactId>
             <version>1.3.1.Final</version>
             <optional>true</optional>
            </dependency>``` 
  in Pom.xml to add maven dependency.
  
## 3. Order API
- Request Format
```java
    OrderId : 1
    
```
  
## 3. How to use
- Build the Project and Run JpaShopApplication

- Test Using Postman
---
    - CreateMember

    @PostMapping("/v1/members")
        ResponseEntity<?> createMember(@RequestBody @Valid MemberDTO memberDTO) {
    
           URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(memberService.join(memberDTO))
                    .toUri();
    
            System.out.println(location.toString());
    
            return ResponseEntity.created(location).build();
        }

    - Search Item
    
    @GetMapping("/v1/items/{id}")
        ResponseEntity<?> getItem(@PathVariable("id") Long id) {
            ItemDTO findItem = itemService.getItem(id);

            return ResponseEntity.ok().body(ApiResponse.success(findItem));
        }
---

- Results
    - Member
<img width="561" alt="스크린샷 2020-10-10 오후 4 20 21" src="https://user-images.githubusercontent.com/60992433/95648814-0faac280-0b15-11eb-9488-1615dd50e6b7.png">

    - Item
<img width="598" alt="스크린샷 2020-10-10 오후 4 35 04" src="https://user-images.githubusercontent.com/60992433/95649088-c9eef980-0b16-11eb-9877-698fccaaa34c.png">









 
