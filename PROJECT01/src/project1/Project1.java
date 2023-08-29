package project1;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import conInfo_vo.ConInfoVO;
import coninfo.dao.ConInfoDAO;
import hall_info.Hall_infoDAO;
import hall_info.Hall_infoVO;
import pay.dao.PayDao;
import pay.vo.Pay_VO;
import reservation.ReservationDAO;
import reservation.ReservationVO;
import seat_info.Seat_infoDAO;
import seat_info.Seat_infoVO;
import users.UsersDAO;
import users.UsersVO;

public class Project1 {
	//필드-------------------------------------------------------------
	private Scanner scan = new Scanner(System.in); //스캐너 선언
	private static final String CONCERT_INFO = "CONCERT";
	private static final String RESERVATION = "RESERVATION";
	private static final String MY = "MYPAGE";
	private static final String QUESTION = "Q&A";
	private static final String LOGIN = "LOGIN";
	private static final String SIGNUP = "SIGNUP";
	private static final String MANAGER = "ADMINMODE";
	
	private static final String MANAGERPW = "project1"; //관리자모드 패스워드

	// reservedSeats 변수 선언 및 초기화 예시
	HashSet<String> reservedSeats = new HashSet<>();

	// formatNumber 메소드 구현 예시
	private static String formatNumber(int number) {
	    if (number < 10) {
	        return " " + number;
	    } else {
	        return Integer.toString(number);
	    }
	}
	
	
	//생성자------------------------------------------------------------
	
	//메소드------------------------------------------------------------
	public void startProject1() {
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
		LocalDate nowDate = LocalDate.now(); //현재 날짜, 시간 입력할 때 사용할 변수

		int intscanner; //숫자 스캐너
		String sscanner; //문자열 스캐너
		
		//예매하기 사용 변수(김하은)-----------------------------------------------------------
		int book_id;
		String user_id = null;
		int concert_id;
		int hall_id;
		int seat_id;
		int count;
		String seat = null;
		int totalprice;
		String paymentMethod = null;
		String createDate = nowDate.toString();
		String status = "예매완료";
		
		String concertscanner; //예매하기 - 콘서트 번호 입력 값
		int countscanner; //예매하기 - 콘서트 예매 인원 수 값
		String seatscanner = null; //예매하기 - 예매할 좌석 수 값 ------------------ 이 부분 수정함
		String paymentMethodscanner; //예매하기 - 결제방법 번호 입력 값
		
		HashSet<String> inputSet = new HashSet<>();

		//-----------------------------------------------------------------------

		//마이페이지 사용 변수---------------------------------------------------------
		String useridscanner;
		
		//-----------------------------------------------------------------------

		//문의사항 사용 변수----------------------------------------------------------

		
		//-----------------------------------------------------------------------

		//로그인 사용 변수------------------------------------------------------------

		
		//-----------------------------------------------------------------------
		
		//회원가입 사용 변수------------------------------------------------------------

		
		//-----------------------------------------------------------------------
		
		//관리자모드 사용 변수------------------------------------------------------------

		
		//-----------------------------------------------------------------------

		
		while (true) {
			System.out.println("----------------------------GIMIBAK----------------------------");
			System.out.println("1." + CONCERT_INFO + " 2." + RESERVATION + " 3." + MY + " 4." + 
					QUESTION + " 5." + LOGIN + " 6." + SIGNUP + " 7." + MANAGER + " 0.종료");
			System.out.println("---------------------------------------------------------------");
			System.out.println("원하는 서비스의 번호를 입력하시오.");
			System.out.print("번호 입력 : ");
			select = scan.nextInt();
			scan.nextLine();
			System.out.println();
			System.out.println("---------------------------------------------------------------");
			
			//콘서트---------------------------------------------------------------------------------
			// 콘서트 정보 조회
			if (select == 1) {
				while (true) {
				    // 메뉴 출력 & 사용자 선택
				    System.out.println("1. 콘서트 정보 조회");
				    System.out.println("0. 이전 메뉴로 돌아가기");
				    System.out.print("번호 입력 : ");
				    int choice1 = scan.nextInt();
				    scan.nextLine();

				    if (choice1 == 1) {
				        // 콘서트 정보 출력
				        System.out.println("<콘서트 INFORMATION>");
				        System.out.println("번호\t콘서트명\t\t\t\t장르\t러닝타임\t날짜\t\t\t장소\t\t시간");
						for (ConInfoVO vo : clist) {
						    System.out.println(String.format("%-1s\t%-24s\t%-5s\t%-5s\t%-10s\t%-20s\t%-10s",
				                    vo.getConcert_id(), vo.getTitle(), vo.getGenre(),
				                    vo.getRunning_time(), vo.getConcert_date().substring(0, 10), vo.getLocation(),
				                    vo.getTime()));
				        }
				        System.out.println("---------------------------------------------------------------");
				    } else if (choice1 == 0) {
				        // 이전 메뉴로 돌아가기
				        break;
				    } else {
				        System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
				    }
				
			    }
			}
			
			//예약하기---------------------------------------------------------------------------------
			if (select == 2) { 
				while (true) {
					// 1. 콘서트 선택
					System.out.println();
					System.out.println("<콘서트 INFORMATION>");
					System.out.println("번호\t콘서트명\t\t\t\t장르\t러닝타임\t날짜\t\t\t장소\t\t\t시간");
					for (ConInfoVO vo : clist) {
					    System.out.println(String.format("%-1s\t%-24s\t%-5s\t%-5s\t%-10s\t%-20s\t%-20s",
					                      vo.getConcert_id(), vo.getTitle(), vo.getGenre(),
					                      vo.getRunning_time(), vo.getConcert_date().substring(0,10), vo.getLocation(), 
					                      vo.getTime()));
					}
					System.out.println("---------------------------------------------------------------");
					System.out.println("관람할 콘서트의 번호를 입력하시오.");

					while (true) {
						System.out.print("콘서트 번호 입력 : ");
						concertscanner = scan.nextLine();
						
						if (Integer.parseInt(concertscanner) <= 99) {
			                // 입력값이 1부터 10 사이인 경우
			                break; // 올바른 입력이므로 반복문 종료
			            } else {
			                // 입력값이 범위를 벗어난 경우
			                System.out.println("잘못된 입력입니다. 다시 입력하세요.");
			            }
					}
					ConInfoVO id = conInfoDAO.selectOne(concertscanner);
					System.out.println();
					
					// 2. 인원 선택하기
					System.out.println("관람할 인원을 입력하시오.(최대 5명)");
					while (true) {
						System.out.print("인원 입력 : ");
						countscanner = scan.nextInt();
						scan.nextLine();
						
						 if (countscanner < 6) {
				                break;
						 } else {
				                System.out.println("인원은 최대 5명까지 가능합니다. 다시 입력해주세요.");
				           }
					}
					System.out.println();

					// 3. 좌석 선택하기
//					List<Seat_infoVO> slist = seatInfoDAO.selectAll(); 
					for (Seat_infoVO vo : slist) {
					    if(vo.getHall_id() == id.getHall_id()) {
					    	System.out.println(vo.getSeat_id() + "." + vo.getSeat_no() + " ");
					    }
						
					}
					System.out.println("원하는 좌석의 번호를 입력하시오.");
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
						
					    int size = 4; // 각 열의 크기
					    int rows = 4; // 행의 개수
					    int startingNumber = 1;
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
					    }
					    System.out.println();
						
					 // 5. 선택한 좌석 확인하기
					    System.out.println("선택하신 좌석을 확인하시겠습니까? (Y/N)");
					    String confirm = scan.nextLine();
					    if (confirm.equalsIgnoreCase("Y")) {
					        System.out.println("선택하신 좌석 목록:");
					        int size1 = 4; // 각 열의 크기
						    int rows1 = 4; // 행의 개수
						    int startingNumber1 = 1;
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
						                seatDisplay = " X ";
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
					        int size1 = 4; // 각 열의 크기
					        int rows1 = 4; // 행의 개수
					        int startingNumber1 = 1;
					        for (int row = 0; row < rows1; row++) {
					            for (int col = 0; col < size1; col++) {
					                int num = startingNumber1 + col + (row * size1);
					                System.out.print("┌────┐");
					            }
					            System.out.println("");

					            for (int col = 0; col < size1; col++) {
					                int num = startingNumber1 + col + (row * size1);
					                String seatDisplay;
					                if (inputSet.contains(formatNumber(num))) {
					                    seatDisplay = " X ";
					                } else if (inputSet.contains(seatscanner) && num == Integer.parseInt(seatscanner)) {
					                    seatDisplay = " X "; // 선택한 좌석 위치에 'X' 출력
					                } else {
					                    seatDisplay = " " + formatNumber(num) + " ";
					                }
					                System.out.print("│" + seatDisplay + "│");
					            }
					            System.out.println("");

					            for (int col = 0; col < size1; col++) {
					                System.out.print("└────┘");
					            }
					            System.out.println("");
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
						System.out.println();
						System.out.println("<로그인>");
//						while (true) {
//							System.out.print("아이디 : ");
//							useridscanner = scan.nextLine();
//	
//							System.out.print("비밀번호 : ");
//							sscanner = scan.nextLine();
//							
//							//GKAJT 아이디
//							//GKAJT 비밀번호
//							
//							user = usersDAO.selectOne(useridscanner);
//							
//							if (user != null && user.getUser_id().equalsIgnoreCase(useridscanner) &&
//									user.getpassword().equalsIgnoreCase(sscanner)) {
//								System.out.println(useridscanner + "님 환영합니다.");
//							 	break; // 비밀번호가 맞으면 첫 번째 while 루프도 종료
//							} else {
//				            // 올바르지 않은 비밀번호를 입력한 경우
//							System.out.println();
//				            System.out.println("아이디 또는 비밀번호가 올바르지 않습니다. 다시 입력하세요.");
//							}
//						}
							
						System.out.println();
						System.out.println("---------------------------------------------------------------");
					}
					if(intscanner == 2) {
						System.out.println("<비회원 RESERVATION>");
						while (true) {
							System.out.print("전화번호 입력(010-0000-0000) : ");
							sscanner = scan.nextLine();
							
							if (sscanner.matches("\\d{3}-\\d{4}-\\d{4}")) {
			                    break; // 올바른 형식이므로 반복문 종료
			                } else {
			                    System.out.println("잘못된 형식입니다. 다시 입력하세요.");
			                }
						}
						System.out.println();
						System.out.print("티켓수령지 입력 : ");
						sscanner = scan.nextLine();
						
						System.out.println();
						System.out.println("---------------------------------------------------------------");
					}
					
					
					// 5. 결제하기
					// 결제 정보 입력 받기
					System.out.println("<결제하기 PAYMENT>");
					System.out.print("사용자 아이디 입력 : ");
					String userId = scan.nextLine();
					System.out.print("결제 방법 선택 (1.카드결제/2.계좌이체) : ");
					String paymentMethod1 = scan.nextLine();

					// 결제 정보 생성 및 저장
					PayDao paymentDao = new PayDao(); // Pay_Dao 객체 생성
					Pay_VO payment = new Pay_VO(0, userId, paymentMethod1, 0, createDate, 0); // Pay_VO 객체 생성
					payment.setUser_id(userId);
					payment.setPayment_method(paymentMethod1); // 변수명을 paymentMethod1로 수정

					// 예약 정보 출력 (예약 번호와 가격)
					System.out.print("예약 번호 입력 : ");
					int reservationId = scan.nextInt();
					scan.nextLine();
					ReservationVO reservation = reservationDAO.selectOne(Integer.toString(reservationId)); // 정수를 문자열로 변환하여 전달

					if (reservation != null) {
					    payment.setTotal_price(reservation.getTotalprice());
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
					
					// 6. 예매완료
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
					
			
			//마이페이지---------------------------------------------------------------------------------
			if (select == 3) { 
				
				// 1. 로그인했으면 바로 2번으로 아니면 1번에서 로그인시키기
				System.out.println();
				System.out.println("<로그인>");
					
					while (true) {
						System.out.print("아이디 : ");
						useridscanner = scan.nextLine();
	
						System.out.print("비밀번호 : ");
						sscanner = scan.nextLine();
						
						//GKAJT 아이디
						//GKAJT 비밀번호
						
						UsersVO user = usersDAO.selectOne(useridscanner);
						
						System.out.println();
						System.out.println("---------------------------------------------------------------");
						
						if (user != null && user.getUser_id().equalsIgnoreCase(useridscanner) &&
								user.getpassword().equalsIgnoreCase(sscanner)) {
							System.out.println(useridscanner + "님 환영합니다.");
							System.out.println();
							System.out.println("---------------------------------------------------------------");
	
							while (true) {
								// 2. 원하는 메뉴 선택하기
								System.out.println("1.예매내역 조회 2.문의내역 조회 3.처음으로 돌아가기");
								System.out.println("---------------------------------------------------------------");
								System.out.print("번호 입력 : ");
								intscanner = scan.nextInt();
								scan.nextLine();
								System.out.println();
								System.out.println("---------------------------------------------------------------");
								
			
								// 2-1. 예매내역 조회
								if (intscanner == 1) {
								System.out.println("<" + useridscanner + "님의 예매내역>");
								System.out.println("---------------------------------------------------------------");

								System.out.println("예약ID\t사용자ID\t콘서트ID\t홀ID\t좌석번호\t\t예약날짜\t\t상태");
								
								for (ReservationVO vo : rlist) {		
									if (vo.getUser_id() != null && vo.getUser_id().equalsIgnoreCase(useridscanner)) {
								               System.out.println(String.format("%-1s\t%-7s\t%-5s\t%-5s\t%-10s\t%-20s",
								                        vo.getBook_id(), vo.getUser_id(), vo.getConcert_id(),
								                        vo.getHall_id(), vo.getSeat(), vo.getCreateDate(),
								                        vo.getStatus()));
									}
								}
								System.out.println();
								System.out.println("---------------------------------------------------------------");
			
								}
								
								// 2-2. 문의내역 조회
								if (intscanner == 2) {
								
								}
								
								
								// 6. 처음으로 돌아가기
								if (intscanner == 3) {
									System.out.println();
									break;
								} 
							}
						 	 break; // 비밀번호가 맞으면 첫 번째 while 루프도 종료
						} else {
			            // 올바르지 않은 비밀번호를 입력한 경우
			            System.out.println("아이디 또는 비밀번호가 올바르지 않습니다. 다시 입력하세요.");
						}
					}
			}
			
			//문의사항---------------------------------------------------------------------------------
			if (select == 4) { 
			
				
			}
			
			//로그인---------------------------------------------------------------------------------
			if (select == 5) { 
				
				
			}
			 
			if (select == 6) {
				
			}
			
			// 관리자모드---------------------------------------------------------------------------------
			// 7번 관리자모드에서 콘서트 정보 수정 기능 추가
			if (select == 7) {
				System.out.println("관리자 비밀번호를 입력하세요.");

				while (true) {
					// 1. 관리자 비밀번호 입력
					System.out.print("비밀번호 : ");
					sscanner = scan.nextLine(); //비밀번호 맞으면 다음으로 넘어가게 하기
					System.out.println();
					System.out.println("---------------------------------------------------------------");
					
					System.out.println("관리자 모드로 변경되었습니다.");
					System.out.println("---------------------------------------------------------------");
					
					if (MANAGERPW.equalsIgnoreCase(sscanner)) {
						while (true) {
							// 2. 원하는 메뉴 선택하기
							
							System.out.println("<관리자 모드>");
							System.out.println("1.고객정보 조회 2.고객 결제정보 조회 3.고객 예매정보 조회 4.콘서트 정보 수정 5.관리자모드 종료");
							System.out.print("번호 입력 : ");
							select = scan.nextInt();
							scan.nextLine();
							System.out.println("---------------------------------------------------------------");
							
							//2-1. 고객정보 조회 (users select all)
							if (select == 1) {
								
							}
							
							//2-2. 고객 결제정보 조회 (payment select all)
							if (select == 2) {
								
							}
		
						
							//2-3. 고객 예매 정보 조회 (reservation select all)
							if (select == 3) {
								System.out.println("<고객 예매정보>");
		
								System.out.println("예약ID\t사용자ID\t콘서트ID\t홀ID\t좌석ID\t인원수\t좌석번호\t\t예약날짜\t\t상태");
								for (ReservationVO vo : rlist) {
									 System.out.println(String.format("%-1s\t%-7s\t%-5s\t%-5s\t%-5s\t%-5s\t%-10s\t%-10s\t%-20s",
						                      vo.getBook_id(), vo.getUser_id(), vo.getConcert_id(),
						                      vo.getHall_id(), vo.getSeat_id(), vo.getCount(),
						                      vo.getSeat(), vo.getCreateDate(), 
						                      vo.getStatus()));
								}
								
								System.out.println();
								System.out.println("---------------------------------------------------------------");
		
							}
							
							//2-4. 콘서트 정보 수정 (concert_info insert, update, delete)
							if (select == 4) {
							    System.out.println("1. 콘서트 정보 수정");
							    System.out.println("2. 콘서트 정보 삭제");
							    System.out.println("3. 이전 메뉴로 돌아가기");
							    System.out.print("번호 입력 : ");
							    int adminChoice = scan.nextInt();
							    scan.nextLine(); // 개행 문자 처리
							    
							    
							    if (adminChoice == 1) {
							        // 콘서트 정보 수정
							        System.out.print("수정할 콘서트의 번호 입력: ");
							        int concertNumber = scan.nextInt();
							        scan.nextLine(); // 개행 문자 처리
							        
							        ConInfoVO selectedConcert = conInfoDAO.selectOne(Integer.toString(concertNumber));
							        if (selectedConcert != null) {
							            System.out.println("수정할 필드를 선택하세요.");
							            System.out.println("1. 제목 2. 장르 3. 러닝타임 4. 날짜 5. 장소 6. 시간");
							            System.out.print("번호 입력: ");
							            int fieldChoice = scan.nextInt();
							            scan.nextLine(); // 개행 문자 처리
							            
							            switch (fieldChoice) {
							                case 1:
							                    System.out.print("새로운 제목 입력: ");
							                    String newTitle = scan.nextLine();
							                    selectedConcert.setTitle(newTitle);
							                    break;
							                case 2:
							                    System.out.print("새로운 장르 입력: ");
							                    String newGenre = scan.nextLine();
							                    selectedConcert.setGenre(newGenre);
							                    break;
							                case 3:
							                    System.out.print("새로운 러닝타임 입력: ");
							                    int newRunningTime = scan.nextInt();
							                    selectedConcert.setRunning_time(newRunningTime);
							                    break;
							                case 4:
							                    System.out.print("새로운 날짜 입력 (YYYY-MM-DD): ");
							                    String newConcertDate = scan.nextLine();
							                    selectedConcert.setConcert_date(newConcertDate);
							                    break;
							                case 5:
							                    System.out.print("새로운 장소 입력: ");
							                    String newLocation = scan.nextLine();
							                    selectedConcert.setLocation(newLocation);
							                    break;
							                case 6:
							                    System.out.print("새로운 시간 입력: ");
							                    String newTime = scan.nextLine();
							                    selectedConcert.setTime(newTime);
							                    break;
							                default:
							                    System.out.println("잘못된 선택입니다.");
							            }
							            
							            
							            // 수정된 콘서트 정보를 데이터베이스에 업데이트
							            int updateResult = conInfoDAO.update(selectedConcert);
							            if (updateResult > 0) {
							                System.out.println("콘서트 정보가 수정되었습니다.");
							            } else {
							                System.out.println("콘서트 정보 수정에 실패하였습니다.");
							            }
							        } else {
							            System.out.println("해당 번호의 콘서트 정보가 없습니다.");
							        }
							    }
							    
							    if (adminChoice == 2) {
							        System.out.print("삭제할 콘서트의 번호 입력: ");
							        int concertNumber = scan.nextInt();
							        scan.nextLine(); // 개행 문자 처리
							        
							        ConInfoVO selectedConcert = conInfoDAO.selectOne(Integer.toString(concertNumber));
							        if (selectedConcert != null) {
							            // 콘서트 정보를 데이터베이스에서 삭제
							            int deleteResult = conInfoDAO.delete(selectedConcert);
							            if (deleteResult > 0) {
							                System.out.println("콘서트 정보가 삭제되었습니다.");
							            } else {
							                System.out.println("콘서트 정보 삭제에 실패하였습니다.");
							            }
							        } else {
							            System.out.println("해당 번호의 콘서트 정보가 없습니다.");
							        }

							        // "콘서트 정보 삭제" 작업이 완료되었으므로 select 값을 변경
							        select = 0; // 0은 종료를 나타내는 값으로 설정
							    }
							
							    
							    if (select == 5) {
									System.out.println("관리자 모드가 종료되었습니다.");
									System.out.println("---------------------------------------------------------------");
									System.out.println();
									System.out.println();
									break;
								} 
							    
							
							// 7번 관리자모드에서 콘서트 정보 삭제 기능 추가
						    if (select == 5) {
						        System.out.println("1. 콘서트 정보 수정");
						        System.out.println("2. 콘서트 정보 삭제");
						        System.out.print("번호 입력 : ");
						        int adminChoice2 = scan.nextInt();
						        scan.nextLine(); // 개행 문자 처리
						        
						        if (select == 2) {
						            System.out.print("삭제할 콘서트의 번호 입력: ");
						            int concertNumber = scan.nextInt();
						            scan.nextLine(); // 개행 문자 처리
						            
						            ConInfoVO selectedConcert = conInfoDAO.selectOne(Integer.toString(concertNumber));
						            if (selectedConcert != null) {
						                // 콘서트 정보를 데이터베이스에서 삭제
						                int deleteResult = conInfoDAO.delete(selectedConcert);
						                System.out.println("콘서트 정보가 삭제되었습니다.");
						            } else {
						            	System.out.println("콘서트 정보 삭제에 실패하였습니다.");
						            }
						         } else {
						                System.out.println("해당 번호의 콘서트 정보가 없습니다.");
						          
						        }
							  }
							}
						}
						 break; // 비밀번호가 맞으면 첫 번째 while 루프도 종료
			        } else {
			            // 올바르지 않은 비밀번호를 입력한 경우
			            System.out.println("비밀번호가 올바르지 않습니다. 다시 입력하세요.");
			        }
				}
			}
			
			//종료---------------------------------------------------------------------------------
			if (select == 0) { 
				System.out.print("감사합니다. 안녕히가시오.");
				break;
			}
		}
	}	
}