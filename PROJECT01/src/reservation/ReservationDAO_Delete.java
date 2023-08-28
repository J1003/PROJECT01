package reservation;

import java.util.List;

public class ReservationDAO_Delete {

	public static void main(String[] args) {
		ReservationDAO dao = new ReservationDAO();
		
		System.out.println("<데이터 조회하기>");
		List<ReservationVO> list = dao.selectAll();
		
		for (ReservationVO vo : list) {
			System.out.println(vo);
		}

		ReservationVO member = new ReservationVO(2, "kimha9494", 3, 4, 5, "23번, 24번", 100000, "현장결제", "2023-08-29", "정상");
		
		System.out.println("-------------------------");
		System.out.println("<데이터 삭제하기>");
		System.out.println("삭제할 정보 : " + member);
		int count = dao.delete(member.getBook_id());
		System.out.println("삭제데이터 건수 : " + count);

		

	}

}
