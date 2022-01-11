# EZEN PC ZONE

### 시연영상 ( [바로가기](https://www.youtube.com/watch?v=xJC-KUXr47k) )

## 1. 개요
### 1.1. 개발 목적
- 피시방 이용자 및 관리자의 편의성 증진

### 1.2. Target
- 피시방 이용자 (손님)
- 피시방 관리자 (피시방 대표, 아르바이트생)

## 2. 개발 일정
- 기간 : 2021.11.05 ~ 2021.11.21 (총 17일)
- History

|날짜|내용|
|----|----|
|2021.11.05|주제 선정 <br>화면 구조도 초안 작성<br>Front 초안 제작(Scenebuilder)|
|2021.11.08|Logo 구상 및 제작<br>화면 구조도 1차 수정<br>controller 구조도 제작</br>|
|2021.11.09|Database 설계|
|2021.11.10|Scenebuilder 화면전환 구현|
|2021.11.11|Scenebuilder fx:id 명명<br>Database schema 및 table 생성</br>|
|2021.11.12 ~ 2021.11.17|기능 구현(Back-end)|
|2021.11.18 ~ 2021.11.21|프로젝트 연결 및 디버깅|

## 3. 개발인원
- 장용범 (총 1명)

## 4. 개발 환경
- 운영체제 : Windows10
- Front-end : JavaFx, SceneBuilder
- Back-end : Java, Eclipse
- Database : MySQL
- Version Control : Git

## 5. 화면 구조도
<details>
<summary>여기를 눌러주세요</summary>
<div markdown="1">       

![그림1](https://user-images.githubusercontent.com/87436495/148365412-4378dfc2-398c-42bd-a3af-8b385f28f8a9.png)
</div>
</details>



## 6. 폴더 구조
- Project : Total 3EA
<details>
<summary>여기를 눌러주세요</summary>
<div markdown="1">       

![7f5db1d4-9583-4db2-8a17-a0278132a19b](https://user-images.githubusercontent.com/87436495/148635544-fea89a05-15dd-415c-a046-edcad3f96e6a.png)
</div>
</details>

## 7. Database ERD  
- Table : Total 7EA  
<details>
<summary>여기를 눌러주세요</summary>
<div markdown="1">       

![211119_erd](https://user-images.githubusercontent.com/87436495/142559102-8652b249-c012-49e9-bef6-6fde86fbe444.png)</div>
</details>


## 8. 메인기능
- 좌석선택(결제), 자리이동, PC 상태변경 (변경내용 키오스크, 고객, 관리자 프로그램 실시간 동시 연동)
- 일시정지
- 실시간 시간 차감 및 사용 시간 계산 및 화면 출력
- 고객문의(채팅)
- 상품 주문내역 실시간 연동

## 9. 주요 코드 1개
- 기능 : Kiosk 좌석 선택(결제) 시 변동사항 Customer, Admin 실시간 연동
- 순서 : 초기 화면(로그인) -> 좌석 / 시간 선택 -> 초기 화면(변동사항 반영)

1. Kiosk 로그인
<details>
<summary>여기를 눌러주세요</summary>
<div markdown="1">       

![untitled (1)](https://user-images.githubusercontent.com/87436495/148914216-6cfc59ef-38ae-4153-83a0-a984791abd0c.png)
</div>
</details>

2. Kiosk 좌석 및 시간 선택 (결제)
<details>
<summary>여기를 눌러주세요</summary>
<div markdown="1">       

![untitled (2)](https://user-images.githubusercontent.com/87436495/148922465-81419dd6-315c-4e67-b680-d44d20047e9a.png)
</div>
</details>

3. 접속현황 실시간 연동 (Kiosk)
<details>
<summary>여기를 눌러주세요</summary>
<div markdown="1">   

```
@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {
			FileInputStream input1 = new FileInputStream("src/fxml/ezen_logo.png");
			Image img1 = new Image(input1);
			imglogo.setImage(img1);

			FileInputStream input2 = new FileInputStream("src/fxml/event1.jpg");
			Image img2 = new Image(input2);
			imglogin.setImage(img2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Button[] pcbuttons = { null, btnpc_1, btnpc_2, btnpc_3, btnpc_4, btnpc_5, btnpc_6, btnpc_7, btnpc_8, btnpc_9,
				btnpc_10, btnpc_11, btnpc_12, btnpc_13, btnpc_14, btnpc_15, btnpc_16, btnpc_17, btnpc_18, btnpc_19,
				btnpc_20 };

		Label[] pcids = { null, lblid_1, lblid_2, lblid_3, lblid_4, lblid_5, lblid_6, lblid_7, lblid_8, lblid_9,
				lblid_10, lblid_11, lblid_12, lblid_13, lblid_14, lblid_15, lblid_16, lblid_17, lblid_18, lblid_19,
				lblid_20 };

		Label[] lbltimes = { null, lbltimeremaining_1, lbltimeremaining_2, lbltimeremaining_3, lbltimeremaining_4,
				lbltimeremaining_5, lbltimeremaining_6, lbltimeremaining_7, lbltimeremaining_8, lbltimeremaining_9,
				lbltimeremaining_10, lbltimeremaining_11, lbltimeremaining_12, lbltimeremaining_13, lbltimeremaining_14,
				lbltimeremaining_15, lbltimeremaining_16, lbltimeremaining_17, lbltimeremaining_18, lbltimeremaining_19,
				lbltimeremaining_20 };

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				Runnable updater = new Runnable() {

					@Override
					public void run() {

						ArrayList<Pc> pcactlist = PcDao.getPcDao().pcactivation_List();

						for (Pc temp : pcactlist) {
							if (temp.getP_activation() == 1) {
								pcbuttons[temp.getP_no()].setStyle("-fx-background-color: #93c9ff; ");
								pcids[temp.getP_no()].setText("");
								lbltimes[temp.getP_no()].setText("사용가능");

							}
							if (temp.getP_activation() == 2) {
								// 색 변경
								pcbuttons[temp.getP_no()].setStyle("-fx-background-color: #FF3333; ");
								lbltimes[temp.getP_no()].setText("사용불가");
								pcids[temp.getP_no()].setText("");
								if (temp.getM_no() != 0) {
									// id 변경
									pcids[temp.getP_no()].setText(MemberDao.getMemberDao().find_m_id(temp.getM_no()));
									// 시간 변경
									int time = TimeDao.gettimDao().time_remaintime(temp.getM_no());
									int hour = time / (60 * 60);
									int minute = time / 60 - (hour * 60);
									int second = time % 60;
									lbltimes[temp.getP_no()].setText("남은시간 " + hour + ":"
											+ String.format("%02d", minute) + ":" + String.format("%02d", second));
								}
							}
						}
					}
				};

				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ex) {
					}
					Platform.runLater(updater);
				}
			}
		});
		thread.start();
	}

	public static LoginController instance;

	public LoginController() {
		instance = this;
	}

	public static LoginController getinstance() {
		return instance;
	}
 ```
</div>
</details>
 
## 9. 보완할 점
- 다수의 인원과 1:1 동시 채팅 구현
- 일시정지 시 DB 미반영
- 제품 등록 시 사진 px 크기 고정 설정
- 사진 업로드 시 업로드 경로 제약받지 않도록 설정

## 10. 후기
- 스스로 학습하는 습관을 가져야 한다. 프로젝트 진행 중 알고있는 내용만으로는 필요한 모든 기능을 구현할 수 없다는 것을 느꼈다.
- 생각을 유연하게 가져야한다. 생각의 관점을 바꾸는 것만으로 현재 문제를 해결할 수 있는 부분이 의외로 많다는 것을 경험했다.
- 프로젝트 전 명명 및 코딩 규칙을 정하고 진행해야 한다. 차후 디버깅 등 진행 시 혼란을 야기하는 요소 중 하나임을 체감했다.
- 이번 프로젝트는 실시간 연동 기능 구현으로 인해 멀티스레드를 공부하고 많이 활용해 본 좋은 기회였다. 그러나 언어(Java)에 대한 이해도가 많이 부족함을 느꼈고 더욱 심도있게 공부할 필요성을 느꼈다.




<!-- 이번 프로젝트를 진행하면서 느낀 것은 2가지입니다.

첫째는 스스로 학습하는 것입니다. 프로젝트의 주요 기능이 멀티스레드로 시작 당시에 알고있는 내용만으로는 모든 기능을 구현할 수 없었습니다. 책과 인터넷을 통해 멀티스레드를 공부하고, 프로젝트 내에서의 상황에 대응시키기 위해 어떻게 해야하는지 계속 고민하고, 검색하고, 배웠습니다.
둘째는 생각의 유연하게 가지는 것입니다. 어렵다고 생각되는 문제 중의 일부를 단지 생각의 관점을 바꾼 것이 실마리가 되어 해결할 수 있었습니다. 이 경험은 어떤 어려운 코드를 만났을 때 Why?와 How?를 스스로 다시 생각해 볼 수 있게 했습니다.
프로젝트를 진행하면서 스스로 학습하는 습관과 스스로 문제에 대해 생각하고 해결하는 능력을 키웠습니다.



첫째는 명명 및 코딩 규칙의 필요성입니다. 프로젝트 진행 당시 파일명, 메소드명에 대해 크게 신경쓰지 않고 불규칙적으로 만들었습니다. 하지만 코드가 복잡해지고, 에러가 계속 발생하면서 디버깅을 할 때 규칙성이 없어서 혼란스러웠습니다.

- 프로젝트에서 왜 코드 형식, 명명 규칙 등에 대해 가이드를 세우는지 체감하게 되었습니다. 프로젝트 진행 시 파일명 등의 이름에 대해 별다른 중요성을 느끼지 못했습니다. 하지만 점점 복잡해지면서 
- 멀티스레드의 개념에 대해 좀 더 깊게 공부하고 활용할 수 있는 
프로젝트 진행 시 어려웠던 점
1. 3개 프로젝트 동시 연동
2. 1:1 동시 채팅
3. 실시간 시간 차감 및 사용시간 계산
처음 접근 :
현재 시간 - 로그인 시간으로 생각
db는 timestamp로 만들고 실시간으로 현재시간을 계속 업데이트 하며 동시에 로그인 시간을 빼는 것 실패

생각을 다르게 접근
날짜를 생각하지 않고 로그인 시작 시 0으로 시작
시간 차감 및 사용 시간은 시간과 시간을 빼는 것이 아니라 각자 1초 차감, 1초 증가로 별개로 계산


5. 멀티스레드 기능이 들어간 것 2개 이상을 하나의 화면에 담는 것

배운 것
1. 멀티스레드
좌석선택, 자리이동, 시간 출력, 주문, 채팅 등 실시간으로 연동하는 기술을 많이 사용해야 했습니다. 처음부터 멀티스레드의 개념,

2. 생각의 유연함의 중요성

프로젝트 진행 당시 문제 중 실시간 시간 계산 및 화면 출력 기능을 구현하는 문제는 생각의 관점을 바꾸는 것으로 해결할 수 있었습니다. <br>
처음에는 현재 시간와 로그인 시간를 빼는 것으로 가능할 것으로 생각하고 접근했습니다. 그러나 현재 시간을 실시간으로 계속 업데이트하며 동시에 DB에
업데이트하여 로그인 시간을 빼는 것을 실패했습니다.

이후 다르게 접근하여 날짜를 생각하지 않고 로그인 시 0으로 시작하는 것으로 생각했습니다. 그리고 
시간 차감 및 사용 시간은 시간 데이터를 빼는 방식이 아니라 각자 1초 차감, 1초 증가로 별개로 계산하여 DB에 업데이트하고 화면에 출력할 수 있었습니다.

이 문제 해결의 경험은 생각의 방식에 따라 어렵다고 생각되는 문제도 문제를 해결할 수 있는 실마리가 되어 문제를 해결할 수 있었다.


아쉬운 것
 -->
