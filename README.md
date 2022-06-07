## 사용한 언어, 프레임워크, DB, AWS 서비스

- Main Language : Java 17 (LTS)
- Framework : Spring Boot 2.7.0 (LTS)
- Cloud Computer : AWS EC2 t2.micro free tier
- Database : AWS DynamoDB
- View Template : Thymeleaf

<br/>

## 서비스 주소

http://ec2-3-39-177-219.ap-northeast-2.compute.amazonaws.com:8080/

<br/>

## EC2 Setup

<img width="720" alt="스크린샷" src="https://user-images.githubusercontent.com/75058239/172375599-2ef4c0bb-195d-4483-ae62-dab318cb099f.png">

Ubuntu 의 t2.micro free tier 로 EC2 인스턴스를 생성했습니다.

<img width="540" alt="스크린샷" src="https://user-images.githubusercontent.com/75058239/172375771-77ae1199-d0aa-48d6-92b3-6b5ee3f517d1.png">

터미널 상에서 인스턴스에 연결할 수 있도록 키 페어도 생성했습니다.

<img width="720" alt="스크린샷" src="https://user-images.githubusercontent.com/75058239/172375924-f4ed9bb2-9a0b-498e-8f15-5c7f1c98be15.png">

DynamoDB와 연결할 수 있도록 IAM Role 을 부여하겠습니다.

<img width="720" alt="스크린샷" src="https://user-images.githubusercontent.com/75058239/172377835-cefe5fc5-59de-4fe6-ba2b-84416e665ddb.png">

여기에서 '새 IAM 역할 생성'을 눌러서 진입합니다.

<img width="720" alt="스크린샷" src="https://user-images.githubusercontent.com/75058239/172376125-466fca6e-ead4-4b0b-b5c8-9d455e156dce.png">

'역할 만들기' 버튼을 눌러서 새 역할을 생성해줍니다.

<img width="720" alt="스크린샷" src="https://user-images.githubusercontent.com/75058239/172376179-796a3ebe-5b37-42a0-9961-ce05b0087682.png">

EC2 를 위한 Role 로 생성합니다.

<img width="720" alt="스크린샷" src="https://user-images.githubusercontent.com/75058239/172376254-9f62aed7-6865-4ea7-9395-96d54aa9c152.png">

검색창에 'Dynamo' 를 검색한 뒤 가장 상위의 'AmazonDynamoDBFullAccess' 를 체크해서 부여해줍니다.

이후에는 적절한 이름을 붙여서 생성해주었습니다.

<img width="720" alt="스크린샷" src="https://user-images.githubusercontent.com/75058239/172376473-068a4b0c-9ac9-4337-8f0e-4338e76663cf.png">

인스턴스가 작동 상태에 있는 것을 확인한 후, 앞서 만든 키 페어와 함께 위 연결 방법을 사용해서 연결했습니다.

<img width="540" alt="스크린샷" src="https://user-images.githubusercontent.com/75058239/172376567-a1f741c6-49f3-4ccc-a07f-774ffdd761ec.png">

제가 만든 레포지토리를 clone 받았습니다.

<img width="450" alt="스크린샷" src="https://user-images.githubusercontent.com/75058239/172376681-da90920d-9adc-4106-900a-bd61ba3a29e4.png">

셸 스크립트를 통해서 실행할 수 있도록 deploy 라는 디렉토리를 만들고 `deploy.sh` 파일을 생성했습니다.

<img width="270" alt="스크린샷" src="https://user-images.githubusercontent.com/75058239/172376749-64709be2-723b-4b1f-8bc6-680557564f79.png">

이후에는 셸 스크립트를 실행하는 것으로 간단하게 배포할 수 있게 되었습니다!

<br/>

## REST API 구현

❖ GitBook 을 활용해서 정리해두었습니다!

https://nmin1124.gitbook.io/super-chicken/

<br/>

## Thymeleaf 로 구현한 간단한 화면 렌더링

### Home 화면

<img width="360" alt="스크린샷" src="https://user-images.githubusercontent.com/75058239/172376859-75674f35-b921-463c-ae08-da8c47457654.png">

- 이벤트에 유저를 등록할 수 있는 화면
- 이름, 이메일, 전화번호, 개인정보 수집 4가지 값을 받아서 등록  
- 편의상 admin page 로 진입할 수 있도록 링크 생성
  - 실재하는 이벤트 페이지라면 인증과 인가를 구현해서 관리자로 로그인한 경우에만 보일 수 있도록 하게 될 것

<img width="420" alt="스크린샷" src="https://user-images.githubusercontent.com/75058239/172376938-8d3c71c2-303c-4289-97b1-b26f964ed0f2.png">

<img width="420" alt="스크린샷" src="https://user-images.githubusercontent.com/75058239/172376973-e025771e-6c03-4f5f-800c-f6c9a44eeee1.png">

<img width="420" alt="스크린샷" src="https://user-images.githubusercontent.com/75058239/172377010-87268ae8-e405-4ccc-bc84-fa4b3c62534e.png">

- 등록 성공, 양식 불충분, 이메일 중복까지 3가지 결과가 나타날 수 있음
- 3가지 결과를 얼럿 창을 통해 알려줌

### Admin Page

<img width="420" alt="스크린샷" src="https://user-images.githubusercontent.com/75058239/172377072-2a935583-ee57-4b03-aa70-e5f77c6b2e72.png">

- 관리자 페이지
- 전체 조회, 혹은 국가 코드별 조회 2가지 방식 중에 선택해서 이벤트 등록자들 조회

### Submitter List Page

<img width="420" alt="스크린샷" src="https://user-images.githubusercontent.com/75058239/172377120-157176a8-936e-49a9-a809-f706a64e1d06.png">

- Admin 페이지에서 선택한 조건대로 등록자들 조회
- 수천만명이 접속할 것으로 가정하기 때문에 100개씩 페이징할 수 있도록 구현

<br/>

## About AWS DynamoDB

### 선택 이유

사실 저도 DynamoDB 를 사용해 본 게 처음입니다.  
하지만 과제를 받고, NoSQL 을 활용해서 이를 해결하면 수평적 확장에 유리하겠다는 생각이 들었고, 그래서 조사해본 결과, AWS DynamoDB 를 알게 되었습니다.  
[Amazon DynamoDB 는 어떤 규모에서도 10밀리초 미만의 성능을 제공하는 키-값 및 문서 데이터베이스이며, DynamoDB는 하루에 10조 개 이상의 요청을 처리할 수 있고, 초당 2,000만 개 이상의 피크 요청을 지원할 수 있다](https://aws.amazon.com/ko/dynamodb/) 는 말에, 수천만 건의 데이터를 처리해야 하는 이번 과제에도 적합하다고 생각했습니다.

### free tier 와의 타협

<img width="630" alt="스크린샷" src="https://user-images.githubusercontent.com/75058239/172377288-c6419a69-c3b9-4d23-bb6a-c27a6f806f46.png">

DynamoDB 를 다루기 위해 중요한 부분인 읽기 용량 단위와 쓰기 용량 단위가 있는데, 이 둘은 모두 1로 설정해두었습니다.

<img width="720" alt="스크린샷" src="https://user-images.githubusercontent.com/75058239/172377364-f9f427a9-eb88-4167-8926-cf95610d21fa.png">

<img width="720" alt="스크린샷" src="https://user-images.githubusercontent.com/75058239/172377387-eda0487b-cc98-414d-84ee-efcddab56b02.png">


수천만 데이터를 다루기에 1이라는 단위는 부적합했지만, free tier 와 협상하는 과정에서 어쩔 수 없이 1이라고 설정해두었습니다.  
하지만 만약 실재하는 이벤트라면 AWS 콘솔을 통해 손쉽게 단위를 수정할 수 있음을 확인했습니다.

### TTL 비활성화

<img width="630" alt="스크린샷" src="https://user-images.githubusercontent.com/75058239/172377452-aab376d8-d4aa-44bd-bce5-12d906cec342.png">

처음에는 이벤트성 페이지라는 것을 감안하여, 데이터에 TTL 을 적용해서 이벤트가 끝난 시점에는 알아서 삭제되도록 할까도 생각해봤습니다.  
하지만 다시 조사를 해보니 DynamoDB 는 차라리 그때그때 테이블을 삭제하고 생성하는 것이 더욱 편리하며, 또 TTL 을 적용하게 되면 삭제되는 시기가 대개 겹치게 되므로 삭제에 대한 트래픽 급증으로 요금이 크게 부과될 수도 있다고 하여 그만두게 되었습니다.  
대신에 이벤트가 끝나고 스프링 실행을 종료할 때, PreDestroy 로 테이블 자체가 자동 삭제되도록 해두었습니다.
