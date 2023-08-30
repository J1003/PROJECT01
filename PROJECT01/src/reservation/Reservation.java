package reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import conInfo_vo.ConInfoVO;
import coninfo.dao.ConInfoDAO;
import hall_info.Hall_infoDAO;
import hall_info.Hall_infoVO;
import pay.dao.PayDao;
import pay.vo.Pay_VO;
import seat_info.Seat_infoDAO;
import seat_info.Seat_infoVO;
import users.UsersDAO;
import users.UsersVO;


public class Reservation {
	
	
	//Reservation 클래스 바로 위에 formatNumber 메소드를 추가합니다.
	private static String formatNumber(int num) {
	 return (num < 10) ? " " + num : Integer.toString(num);
	}
	
    public void booking() {
        Scanner scan = new Scanner(System.in);

    	ReservationDAO reservationDAO = new ReservationDAO(); //reservation 테이블 용 메소드
    	ConInfoDAO conInfoDAO = new ConInfoDAO(); //concert_info 테이블 용 메소드
    	UsersDAO usersDAO = new UsersDAO(); //users 테이블 용 메소드
    	Hall_infoDAO hallInfoDAO = new Hall_infoDAO(); //users 테이블 용 메소드
    	Seat_infoDAO seatInfoDAO = new Seat_infoDAO(); //users 테이블 용 메소드
    	
    	List<ReservationVO> rlist = reservationDAO.selectAll(); //reservation 테이블 데이터 전체 조회
    	List<ConInfoVO> clist = conInfoDAO.selectAll(); //concert_info 테이블 데이터 전체 조회
    	List<UsersVO> ulist = usersDAO.selectAll(); //users 테이블 데이터 전체 조회
    	List<Hall_infoVO> hlist = hallInfoDAO.selectAll(); //hall_info 테이블 데이터 전체 조회
    	List<Seat_infoVO> slist = seatInfoDAO.selectAll(); //seat_info 테이블 데이터 전체 조회
    	
    	int select; //메인 메뉴에서 입력한 번호 값
    	
    	LocalDate nowDate = LocalDate.now(); //현재 날짜
    	LocalTime nowTime = LocalTime.now(); //현재 시간 입력할 때 사용할 변수

    	int intscanner; //숫자 스캐너
    	String sscanner; //문자열 스캐너
    	
//    	book_id, user_id, concert_id, hall_id, phonenumber, count, seat, 
//    	price, paymentMethod, howtoget, createDate, status
    	//예매하기 삽입용 변수-----------------------------------------------------------
    	int book_id = 0;
    	String user_id = null;
    	int concert_id = 0;
    	int hall_id = 0;
    	String phonenumber = null;
    	int count = 0;
    	String seat = null;
    	String price = null;
    	String paymentMethod = null;
    	String howtoget = null;
    	String createDate = nowDate.toString();
    	String status = "예매완료";
    	
    	String[] seatPrices = new String[5]; // 최대 5개의 좌석 가격을 저장하는 배열 -------------------------------------------- 8/30 수정
    	String[] seatNumbers = new String[5]; // 최대 5개의 좌석 번호를 저장하는 배열 -------------------------------------------- 8/30 수정
    	
    	//예매하기 스캐너 변수-----------------------------------------------------
    	int concertscanner = 0; //콘서트 번호 입력 값
    	int countscanner = 0; //콘서트 예매 인원 수 값
    	String seatscanner = null; //예매할 좌석 수 값 -------------------------------------------- 8/30 수정
    	String paymentMethodscanner = null; //결제방법 번호 입력 값
    	String phonescanner = null;
    	String howtogetscanner = null;
    	
    	//이미 예약된 좌석--------------------------------------------------
    	HashMap<Integer, String> bookedseat = new HashMap<>();
    	for (Seat_infoVO vo1 : slist) {
		    for (ReservationVO vo : rlist) {
		    	if (vo.getHall_id() == vo1.getHall_id() && vo.getSeat() == vo1.getSeat_no())
		    	bookedseat.put(vo1.getSeat_id(), vo1.getSeat_no());
    		}
    	}

		while (true) {
			// 1. 콘서트 선택
			System.out.println();
			System.out.println("<콘서트 INFORMATION>");
			System.out.println("번호\t콘서트명\t\t\t\t장르\t러닝타임\t날짜\t\t\t장소\t\t\t시간");
			for (ConInfoVO vo : clist) {
			    System.out.println(String.format("%-1s\t%-24s\t%-5s\t%-5s\t%-10s\t%-20s\t%-20s",
			                      vo.getConcert_id(), vo.getTitle(), vo.getGenre(),
			                      vo.getRunning_time(), vo.getConcert_date(), vo.getLocation(), 
			                      vo.getTime()));
			}
			System.out.println("---------------------------------------------------------------");
			System.out.println("관람할 콘서트의 번호를 입력하시오.");
	
			while (true) {
				HashSet<Integer> concertamount = new HashSet<>();
		    	for (ConInfoVO vo : clist) {
		    		concertamount.add(vo.getConcert_id());
		    	}
				System.out.print("콘서트 번호 입력 : ");
				concertscanner = scan.nextInt();
				
				if (concertamount.contains(concertscanner)) {
	                break; // 올바른 입력이므로 반복문 종료
	            } else {
	                // 입력값이 범위를 벗어난 경우
	                System.out.println("잘못된 입력입니다. 다시 입력하세요.");
	            }
			}
			ConInfoVO id = conInfoDAO.selectOne(String.valueOf(concertscanner));
			System.out.println();
			
			// 2. 인원 선택하기
			System.out.println("관람할 인원을 입력하시오.(최대 5명)");
			while (true) {
				System.out.print("인원 입력 : ");
				countscanner = scan.nextInt();
				scan.nextLine();
				
				 if (countscanner < 6 && countscanner > 0) {
		                break;
				 } else {
		                System.out.println("인원은 최소 1명, 최대 5명까지 가능합니다. 다시 입력해주세요.");
		           }
			}
			System.out.println();
	
			// 3. 좌석 선택하기-------------------------------------------------------------
			
			// 3. 좌석 선택하기
			
			List<Seat_infoVO> slist1 = seatInfoDAO.selectAll(); 
			for (Seat_infoVO vo : slist1) {
			    if(vo.getHall_id() == id.getHall_id()) {
			    	System.out.println(vo.getSeat_id() + "." + vo.getSeat_no() + " ");
			    }
				
			}
			System.out.println("원하는 좌석의 번호를 입력하시오.");
				
			    HashSet<String> inputSet = new HashSet<>(); // 선택한 좌석 번호를 저장할 Set
				for (int i = 1; i < countscanner+1; i++) {	
					System.out.print("좌석 번호 입력 " + i + " : ");
					seatscanner = scan.nextLine();
				
			         if (inputSet.contains(seatscanner)) {
			            System.out.println("중복된 좌석입니다. 다른 좌석을 선택하세요.");
			            i--; // 이미 입력된 번호로 인해 i 값을 감소시켜 한 번 더 입력하도록 함
			        } else {
			            inputSet.add(seatscanner);
			        }
				 }
				// 좌석표 출력 부분
				
			    int size = 7; // 각 열의 크기
			    int rows = 10; // 행의 개수
			    int startingNumber = 1;
			    
			    char rowChar = 'a';
			    
			    for (int row = 0; row < rows; row++) {
			        for (int col = 0; col < size; col++) {
			            int num = startingNumber + col + (row * size);
			            System.out.print("┌────┐");
			        }
			        System.out.println("");

			        for (int col = 0; col < size; col++) {
		                int num = startingNumber + col + (row * size);
		                System.out.print("│ " + formatNumber(num) + " │");
		            }
		            System.out.println("");
		            
		            for (int col = 0; col < size; col++) {
		                System.out.print("└────┘");
		            }
		            System.out.println("");
		            
		            rowChar++; // 다음 행의 문자로 넘어감
			    }
			    System.out.println();
				
			 // 5. 선택한 좌석 확인하기
			    System.out.println("선택하신 좌석을 확인하시겠습니까? (Y/N)");
			    String confirm = scan.nextLine();
			    if (confirm.equalsIgnoreCase("Y")) {
			        System.out.println("선택하신 좌석 목록:");
			        
			        int size1 = 7; // 각 열의 크기
				    int rows1 = 10; // 행의 개수
				    int startingNumber1 = 1;
				    
				    char rowChar1 = 'a';
				    
				    for (int row = 0; row < rows; row++) {
				        for (int col = 0; col < size; col++) {
				            int num = startingNumber1 + col + (row * size);
				            System.out.print("┌────┐");
				        }
				        System.out.println("");

				        for (int col = 0; col < size1; col++) {
				            int num = startingNumber1 + col + (row * size1);
				            String seatDisplay;
				            if (inputSet.contains(formatNumber(num))) {
				                seatDisplay = " X  ";
				            } else if (inputSet.contains(seatscanner) && num == Integer.parseInt(seatscanner)) {
				                seatDisplay = " X  "; // 선택한 좌석 위치에 'X' 출력
				            } else {
				                seatDisplay = " " + formatNumber(num) + " ";
				            }
				            System.out.print("│" + seatDisplay + "│");
				        }
				        System.out.println("");
			            
			            for (int col = 0; col < size; col++) {
			                System.out.print("└────┘");
			            }
			            System.out.println("");

			            rowChar1++; // 다음 행의 문자로 넘어감
				    }
			    } else if (confirm.equalsIgnoreCase("N")) {
			        // 선택하지 않았거나 다시 선택하고 싶은 경우
			        System.out.println("다시 좌석을 선택해주세요.");

			        // 좌석 선택하기 부분으로 돌아가기
			        for (int i = 1; i < countscanner + 1; i++) {
			            System.out.print("좌석 번호 입력 " + i + " : ");
			            seatscanner = scan.nextLine();

			            if (inputSet.contains(seatscanner)) {
			                System.out.println("중복된 좌석입니다. 다른 좌석을 선택하세요.");
			                i--; // 이미 입력된 번호로 인해 i 값을 감소시켜 한 번 더 입력하도록 함
			            } else {
			                inputSet.add(seatscanner);
			            }
			        }

			        // 좌석표 출력 부분
			        List<String> selectedSeats = new ArrayList<>(); // 선택한 좌석 번호를 저장할 리스트
			        
			        int size1 = 7; // 각 열의 크기
			        int rows1 = 10; // 행의 개수
			        int startingNumber1 = 1;
			        
			        char rowChar1 = 'a';
			        
			        for (int row = 0; row < rows1; row++) {
			            for (int col = 0; col < size1; col++) {
			                int num = startingNumber1 + col + (row * size1);
			                System.out.print("┌─────┐"); // 8/30 - 두 자리 숫자 입력시 X 밀려나는 부분 오류 해결!!! "출력부분만(완전중요)" 위아래로 ㅡ 이거 한번 더 그어주면 돼!!!
			            }
			            System.out.println("");

			            for (int col = 0; col < size1; col++) {
			                int num = startingNumber1 + col + (row * size1);
			                String seatDisplay;
			                if (selectedSeats.contains(formatNumber(num))) { // !!8/30 변경된 부분!!! - 다 이 부분이 문제였음!!! 하지만 난 이 코드를 이해하지 못 했지 히히
			                    seatDisplay = " X  ";
			                } else if (inputSet.contains(seatscanner) && num == Integer.parseInt(seatscanner)) {
			                    seatDisplay = " X  "; // 선택한 좌석 위치에 'X' 출력
			                } else {
			                    seatDisplay = " " + formatNumber(num) + " ";
			                }
			                System.out.print("│" + seatDisplay + "│");
			            }
			            System.out.println("");

			            for (int col = 0; col < size1; col++) {
			                System.out.print("└─────┘"); // 8/30 - 두 자리 숫자 입력시 X 밀려나는 부분 오류 해결!!! "출력부분만(완전중요)" 위아래로 ㅡ 이거 한번 더 그어주면 돼!!!
			            }
			            System.out.println("");
			            

			            rowChar1++; // 다음 행의 문자로 넘어감
			        }
			    }
			    System.out.println();
								
			
			System.out.println();
			System.out.println("---------------------------------------------------------------");
			
			
	
			// 4. 회원/비회원 예매 선택하기
			System.out.println("1.회원 예매 2.비회원 예매");
			System.out.print("번호 입력 : ");
			intscanner = scan.nextInt();
			scan.nextLine();
			System.out.println("---------------------------------------------------------------");
			
			UsersVO user = null;
			
					if(intscanner == 1) {
						Scanner sc = new Scanner(System.in);
						
						while (true) {
				            System.out.print("아이디를 입력하세요 : ");
				             user_id = sc.nextLine();
			
				            // UsersDAO를 이용하여 데이터베이스에서 사용자 정보 조회
				            usersDAO = new UsersDAO();
				            user = usersDAO.selectOne(user_id);
			
				            if (user != null) {
				            	while (true) {
				                    System.out.print("비밀번호를 입력하세요: ");
				                    String password = sc.nextLine();
				                    
				                    
//				                    user.getPassword.equals(password)
				                    
				                    if (user.getPassword().equals(password)) {
				                        System.out.println("로그인에 성공했습니다.");
				                        break;
				                    } else {
				                        System.out.println("비밀번호가 일치하지 않습니다. 다시 입력하세요");
				                    }
				                }
				                break; 
				            } else {
				                System.out.println("일치하는 아이디가 없습니다. 다시 입력하세요");
				            }
				        }
							
						System.out.println();
						System.out.println("---------------------------------------------------------------");
					}
					if(intscanner == 2) {
						System.out.println("<비회원 RESERVATION>");
						while (true) {
							System.out.print("전화번호 입력(010-0000-0000) : ");
							phonescanner = scan.nextLine();
							
							if (phonescanner.matches("\\d{3}-\\d{4}-\\d{4}")) {
			                    break; // 올바른 형식이므로 반복문 종료
			                } else {
			                    System.out.println("잘못된 형식입니다. 다시 입력하세요.");
			                }
						}
			
						System.out.println();
						System.out.print("티켓수령지 입력 : ");
						howtogetscanner = scan.nextLine();
	
						System.out.println();
						System.out.println("---------------------------------------------------------------");
					}
			
	
			// 5. 결제하기
			// 결제 정보 입력 받기
			System.out.println("<결제하기 PAYMENT>");
			int gyesan = 0;
			for (int i = 0; i < seatPrices.length; i++) {  
				if(seatPrices[i] != null) {
					gyesan += Integer.parseInt(seatPrices[i]);
				}
			}
			price = Integer.toString(gyesan);
			System.out.println("총 결제 금액은 " + price + "원 입니다.");
			System.out.println("---------------------------------------------------------------");

			System.out.print("결제 방법 선택 (1.카드결제/2.계좌이체) : ");
			String paymentMethod1 = scan.nextLine();
	
			// 결제 정보 생성 및 저장
			PayDao paymentDao = new PayDao(); // Pay_Dao 객체 생성
			Pay_VO payment = new Pay_VO(0, user_id, paymentMethod1, gyesan, createDate, 0); // Pay_VO 객체 생성
			payment.setUser_id(user_id);
			payment.setPayment_method(paymentMethod1); // 변수명을 paymentMethod1로 수정
	
			// 예약 정보 출력 (예약 번호와 가격)
			System.out.print("예약 번호 입력 : ");
			int reservationId = scan.nextInt();
			scan.nextLine();
			ReservationVO reservation = reservationDAO.selectOne(Integer.toString(reservationId)); // 정수를 문자열로 변환하여 전달
	
			if (reservation != null) {
			    payment.setTotal_price(Integer.parseInt(reservation.getPrice()));
			    payment.setPayment_date(createDate);
			    payment.setBook_id(reservationId);
	
			    // 결제 정보 저장
			    int paymentResult = paymentDao.insert(payment); // paymentDao를 사용하여 insert 메소드 호출
			    if (paymentResult > 0) {
			        System.out.println("결제가 완료되었습니다.");
			    } else {
			        System.out.println("결제에 실패했습니다.");
			    }
			} else {
			    System.out.println("해당 예약 번호의 예약 정보가 없습니다.");
			}
			
//	    	book_id, user_id, concert_id, hall_id, phonenumber, count, seat, 
//	    	price, paymentMethod, howtoget, createDate, status
	    	int maxBookId = 0;
			for (ReservationVO existBook_id : reservationDAO.selectAll()) {
			    if (existBook_id.getBook_id() > maxBookId) {
			        maxBookId = existBook_id.getBook_id();
			    }
			} 
			// 6. 예매완료-----------------------------------------------------------------
			book_id = maxBookId + 1;
			user_id = null;
			concert_id = id.getConcert_id();
			hall_id = id.getHall_id(); // 입력된 콘서트 정보에서 홀 정보 가져오기
			phonenumber = phonescanner;
			count = countscanner;
//			seat
//			price			
			paymentMethod = paymentMethod1;
			howtoget = howtogetscanner;
//			createDate
			status = "예매완료";
			
			for (int i = 0; i < countscanner; i++) {  
				seat = seatNumbers[i];
				price = seatPrices[i];
				ReservationVO reservation1 = new ReservationVO (book_id, user_id, concert_id, hall_id, phonenumber, 
						count, seat, price, paymentMethod, howtoget, nowDate.toString(), status);
				int result = reservationDAO.insert(reservation1);
				
				System.out.println(result);
				
				book_id++;
				
			}
		
			
			System.out.println("성공적으로 예매 되었습니다.");
			System.out.println("추가로 예매를 원하시면 \"Y\"를 원치 않으시면 \"N\"를 입력하세요.");
			while (true) {
				System.out.print("입력 : ");
				sscanner = scan.nextLine();
				if (sscanner.equalsIgnoreCase("Y")) {
					//여기서 Y 누르면 다시 예매 탭으로 가고
					System.out.println("---------------------------------------------------------------");
					break;
				} else if (sscanner.equalsIgnoreCase("N")) {
					//N 누르면 처음으로 돌아가게 하기
					break;
				} else {
	            // Y나 N이 아닌 다른 입력인 경우
	            System.out.println("잘못된 입력입니다. \"Y\"나 \"N\"을 입력하세요.");
				}
			}
			if (sscanner.equalsIgnoreCase("N")) {
				//N 누르면 처음으로 돌아가게 하기
				System.out.println("감사합니다. 안녕히가시오.");
				System.out.println("---------------------------------------------------------------");
				System.out.println();
				System.out.println();
				break;
			} 
		}
	}
	    
}