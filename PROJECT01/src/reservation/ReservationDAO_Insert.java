package reservation;

import java.time.LocalDate;
import java.util.List;

import conInfo_vo.ConInfo_VO;
import coninfo.dao.ConInfo_DAO;



public class ReservationDAO_Insert {

	public static void main(String[] args) {
		ReservationDAO reservationDAO = new ReservationDAO();
		// 필요한 부모테이블 정보 : user_id, concert_id, hall_id
		ConInfo_DAO conInfoDAO = new ConInfo_DAO(); 
		LocalDate nowDate = LocalDate.now();
		
		int book_id;
		int user_id;
		int concert_id;
		int hall_id;
		int count;
		String seat = null;
		int totalprice;
		String paymentMethod = null;
		String createDate = nowDate.toString();
		String status = "정상";
		
		System.out.println("<데이터 조회하기>");
//		List<ConInfo_VO> ulist = conInfoDAO.selectAll();
		List<ConInfo_VO> clist = conInfoDAO.selectAll();
		for (ConInfo_VO vo : clist) {
			System.out.println(vo);
		}
		System.out.println("-------------------------");
//		List<ConInfo_VO> hlist = conInfoDAO.selectAll();
		List<ReservationVO> rlist = reservationDAO.selectAll();
		for (ReservationVO vo : rlist) {
			System.out.println(vo);
		}
		
		System.out.println("-------------------------");
		System.out.println("<데이터 입력하기>");

		for (ConInfo_VO vo : clist) {
			if (vo.getConcert_id() == 1) {
				ConInfo_VO a = conInfoDAO.selectOne("1");
				ReservationVO reservation = new ReservationVO (21, "GKAJT", a.getConcert_id(), 12, 5, "33번, 34번", 200000, "카드결제", nowDate.toString(), "정상");
				System.out.println(reservation);
				reservationDAO.insert(reservation);
				System.out.println("<데이터 입력 완료>");
				
			    break;
			}
		}
		
		//쌤-------------------------------------
//		System.out.println("=================");
//		MemberVO vo = new MemberVO(3, "kimha94", 4, 5, 6, "35번, 36번", 300000, "카드결제", "2023-08-29", "정상");
//		int insertCount =  dao.insert(vo);
//		System.out.println("입력건수 : " + insertCount);
//		list = dao.selectAll();
//		
//		for (MemberVO mb : list) {
//			System.out.println(mb);
//		}
	}

}
