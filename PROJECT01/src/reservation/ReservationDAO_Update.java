package reservation;

import java.util.List;

public class ReservationDAO_Update {

	public static void main(String[] args) {
		ReservationDAO dao = new ReservationDAO();
		
		System.out.println("<데이터 조회하기>");
		List<ReservationVO> list = dao.selectAll();
		
		for (ReservationVO vo : list) {
			System.out.println(vo);
		}
		
		
		System.out.println("-------------------------");
		System.out.println("<데이터 수정하기>");
		ReservationVO member = new ReservationVO(5, "kimha9493", 10, 10, 5, "23번, 24번", 100000, "현장결제", "2023-08-29", "정상");
		System.out.println("수정정보 : " + member);
		int result = dao.update(member);
		System.out.println("수정건수 : " + result);

				
		
	}

}
