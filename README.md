# JPA - SHOP PROJECT

---
- 기본적인 회원관리와 상품관리 및 상품의 주문 배송에 대해 서비스를 제공하는 프로젝트.
---

<pre>
- language : java, jdk 1.8
- framework : spring boot 2.3.2
- ORM : JPA(spring-data-jpa)
- DBMS : H2
- Build Tool : maven
- Develop Tool : Intellij IDEA
</pre>
<br>

## 2. MAVEN BUILD

- maven 설치 : mvn install

- Compile : mvn compile

- java -jar jpa-shop.jar

## 3. ERD

- URL : https://aquerytool.com:443/aquerymain/index/?rurl=b85e08f2-786d-45c2-a9ee-cd87c37237fd&

- Password : b0x5m5
  
## 4. 각 서비스별 URL 및 JSON FORMAT

### 4-1. MeberController
- 회원 조회
```
    @GetMapping("/v1/members/{id}")
        ResponseEntity<MemberDTO> getMember(@PathVariable(name = "id") Long id) {
            return ResponseEntity.ok()
                    .body(memberService.getMember(id));
        }

```

- 회원 가입
```
 @PostMapping("/v1/members")
    ResponseEntity<?> createMember(@RequestBody @Valid MemberDTO memberDTO) {

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(memberService.join(memberDTO))
                .toUri();

        System.out.println(location.toString());

        return ResponseEntity.created(location).build();
    }
```

### 4-2. ItemController

- 상품 조회
```
@GetMapping("/v1/items/{id}")
    ResponseEntity<?> getItem(@PathVariable("id") Long id) {
        ItemDTO findItem = itemService.getItem(id);

        return ResponseEntity.ok().body(ApiResponse.success(findItem));
    }

```

- 상품 등록

```
    @PostMapping("/v1/items")
    ResponseEntity<?> createItem(@RequestBody ItemDTO itemDTO) {

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(itemService.registerItem(itemDTO))
                .toUri();

        return ResponseEntity.created(location).build();
    }
```

- 상품 수정

```
    @PutMapping("/v1/items/{id}")
    ResponseEntity<ItemDTO> updateItem(
            @PathVariable("id") Long id, @RequestBody ItemDTO itemDTO) {

        itemService.update(id, itemDTO);

        return ResponseEntity.ok().body(null);
    }
```

### 4-3. OrderController

- 주문 등록

```
    @PostMapping("/v1/orders")
    ResponseEntity<?> order(@RequestBody OrderDTO orderDTO) {
        orderService.order(orderDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(orderService.order(orderDTO))
                .toUri();

        return ResponseEntity.created(location).build();
    }
```

- 주문 조회 

```
    @GetMapping("/v1/members/{id}")
    ResponseEntity<MemberDTO> getMember(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok()
                .body(memberService.getMember(id));
    }

```
## 5. TODO

- Spring Security 적용

- JWT Token 사용

- MySql 변경







 
