# 아기의 안전을 위한 스마트요람 Bazzi

아기가 태어난 후 부모는 필수적으로 아기의 침대 또는 요람을 구입하게 된다. 24시간 아기의 상태를 신경 써야 하는 부모는 아기를 중심으로 행동반경이 넓지 않게 된다.

이때, 스마트 요람 'Bazzi'는 부모의 눈과 손을 대신해 주는 역할을 한다. Bazzi를 통해 실시간 모니터링과 체온&맥박 상태 등 아기의 상태를 APP을 통해 확인할 수 있기 때문이다.<br><br>

## Member

**문다은** : 안드로이드 APP 제작<br>
권영인 : 리즈베리파이 및 아두이노<br>
나새영 : 라즈베리파이 및 아두이노<br>
김혜선 : 안드로이드 APP 제작<br><br>

## Develop Period

![26](https://user-images.githubusercontent.com/64301855/145396638-c37bec6f-9f89-462f-bea1-8588c795d7c7.JPG)
<br>

## Techique

- **Raspberry PI 4**

	- 아기의 모습을 **실시간으로 모니터링**하기 위해 카메라 모듈을 사용하여 웹으로 화면을 하였다.

	- 디스플레이 모듈에서 동요나 영상이 나오면서 아기의 관심을 끌고, 위쪽에 달린 온도 센서가 체온을 잘 측정할 수 있도록 도와준다.

- **Arduino**

	- **맥박 센서**를 부착하여 **아기의 심장 BPM을 측정**하여 데이터베이스에 값을 저장한다.

	- 디스플레이 위에 **온도 센서**를 부착하여 **아기의 체온을 측정**하여 데이터베이스에 값을 저장한다.

- **Android**

	- Android Studio로 개발한 APP에서 요람에서 제공하는 아기의 상태를 확인할 수 있다.

	- **회원가입&로그인 기능**을 통해 데이터를 저장하며 사용할 수 있다.

	- 한 화면에서 카메라 모듈이 웹에 전송한 **실시간 화면**과 **아기의 맥박과 체온 값**을 볼 수 있다.

	--  날짜별로 아기의 키와 몸무게를 입력하면 성장 속도를 한눈에 볼 수 있는 **성장 그래프**를 생성한다.

	- **일기장** 기능을 통해 아기와 함께한 하루를 기록할 수 있다.

	- 날마다 다른 수유량을 날짜, 시간과 함께 저장하여 리스트 형식으로 볼 수 있다.

- **phpMyAdmin**

	- MySQL **데이터 데이스를 관리**하는 용도로 사용하였다.

	- bazzi라는 데이터베이스를 생성하여 전체 데이터를 관리하였다.

 > ⓐ 회원가입한 이용자의 아이디, 비밀번호(암호화)의 값을 Login 테이블 userID, userPassword열에 저장
 > 
 > ⓑ 아기의 체온, 맥박 값을 mysql 테이블 temp2, bpm2열에 저장
 > 
 > ⓒ 그래프 작성을 위해 사용자가 입력한 아기의 키, 몸무게 값과 날짜를 babyGraph테이블 babyCM, babyKG, babyDate열에 저장

- **PHP**

	- APP과 phpMyAdmin을 연결하는데 사용하였다.

	- APP에서 입력한 데이터를 MySQL에 저장할 때 사용하였다.

	- 데이터베이스에 있는 값을 APP에 나타내기 위해서 **JSON 형태**로 웹 상에 나타나게 하였고, 그 값을 읽을 때 사용하였다.<br><br>
  
## Development(APP)

<br>

**▶ APP 아이콘**

![8](https://user-images.githubusercontent.com/64301855/145397023-19483243-98af-4b51-9019-c73ff31e2a52.jpg)<br>

**▶ App 메인화면, Navigation Drawer**

| 메인화면 | Navigation | 아기 정보 수정 및 등록 화면 |
|:-------:|:--------:|:--------:|
| ![9](https://user-images.githubusercontent.com/64301855/145397026-d67ee371-14dc-402d-a79d-d1bbe19b36e2.jpg) | ![10](https://user-images.githubusercontent.com/64301855/145397028-04213fc2-c1b7-4003-bf8d-f6c794a1742d.jpg) | ![11](https://user-images.githubusercontent.com/64301855/145397029-e2dae281-9cc7-4d46-b957-24c471d92978.jpg) |

<br>

**▶ App과 데이터베이스 연동**

![12](https://user-images.githubusercontent.com/64301855/145397031-16e94fde-2f44-4dd5-8fe4-0e30d5f1a811.jpg)<br>

**▶ App 기능 화면**

| 아기 상태 화면 | 알림 화면 | 디스플레이 화면 | 수유 화면 |
|:--------:|:--------:|:--------:|:--------:|
| ![13](https://user-images.githubusercontent.com/64301855/145397033-2a815e75-34f4-4ea9-b1f4-e0db163c8d79.jpg) | ![14](https://user-images.githubusercontent.com/64301855/145397034-62fd9a1b-2e28-4485-b740-78afaafcda20.jpg) | ![15](https://user-images.githubusercontent.com/64301855/145397035-ac64075d-5c62-4616-a176-627c7c9bb928.jpg) |  ![16](https://user-images.githubusercontent.com/64301855/145397037-83aa460c-b36d-4deb-bbfb-28249ffd22f1.jpg)  |

<br>

| 일기장 리스트 화면 | 작성 화면 | 성장 그래프 화면 |
|:-------:|:-------:|:-------:|
| ![17](https://user-images.githubusercontent.com/64301855/145397038-2a61b526-78b9-4760-9d0e-2e82d5370d77.jpg) | ![18](https://user-images.githubusercontent.com/64301855/145397039-19894159-9d4a-41f8-a742-3ebf7304bb6e.jpg) | ![19](https://user-images.githubusercontent.com/64301855/145397043-b12ba5a9-3ba7-49b0-a4e4-aa1571697b89.jpg) |

<br>

**▶ App 사용 방법 화면**

|![20](https://user-images.githubusercontent.com/64301855/145397044-3cde195d-e945-48ce-8fc4-8f7a059cbbf7.jpg)|![21](https://user-images.githubusercontent.com/64301855/145397046-d3712932-78a7-4c27-9652-cec61a04928e.jpg)|![22](https://user-images.githubusercontent.com/64301855/145397048-a2313e04-7a2b-4e68-a5c3-43e3faed2011.jpg)|
|:----:|:----:|:----:|
|![23](https://user-images.githubusercontent.com/64301855/145397049-5fd95006-8e45-4ac1-a7f9-9adb868226d7.jpg)|![24](https://user-images.githubusercontent.com/64301855/145397052-879fc841-15d5-416a-b114-a3987a06c880.jpg)|![25](https://user-images.githubusercontent.com/64301855/145397054-fbc983ea-162f-437f-bcd0-82874f407390.jpg)|


<br><br>

## Appearance of the work

<br>

| img | contents |
|:----:|----|
|<img src="https://user-images.githubusercontent.com/64301855/145397058-e2504be8-ec28-4a10-9f1c-f01a1ced1f74.jpg" width="350px" height="280px">|- 요람 외부 모습<br> - 요람의 옆면에 같은 색의 천을 덧대어 디스플레이를 장착할 거치대를 고정|
|<img src="https://user-images.githubusercontent.com/64301855/145397062-23f97c1a-91c6-40a4-8561-136bf198df3d.jpg" width="350px" height="280px">|- 위쪽에서 본 전체적인 요람 내부 모습|
|<img src="https://user-images.githubusercontent.com/64301855/145397066-4c18c475-8730-49a9-a92e-1ef5b08d3b1f.jpg" width="350px" height="280px">|- 라즈베리파이에 부착된 유선 스피커를 통해 영상의 소리가 출력|
|<img src="https://user-images.githubusercontent.com/64301855/145397069-e2870492-407e-4a2f-9294-d295175d50cb.jpg" width="350px" height="280px">|- 디스플레이 위쪽에 부착된 카메라를 통해 아기의 모습을 실시간으로 모니터링 가능<br>- 디스플레이로 영상을 송출하여 아기의 관심을 끌어주면서 아기의 이마가 온도 센서를 향해 있게 되면서 체온 측정에 도움을 줌|
|<img src="https://user-images.githubusercontent.com/64301855/145397017-0dd05299-ef43-4139-9325-fb59c09e7621.jpg" width="350px" height="280px">|- 요람 안쪽에 흰색 천을 덧대어 시리얼 통신 선과 온도 센서의 선을 가려줌과 동시에 소리감지 센서의 필요 부분만 빼줌|
|<img src="https://user-images.githubusercontent.com/64301855/145397020-4ef585a7-0e9b-4e3b-9c18-3a3fedaf7bee.jpg" width="350px" height="280px">|- 아기가 소리를 내면 센서에서 인식<br>- 아기의 발목 부분에 맥박 센서를 부착하여 맥박 측정|
