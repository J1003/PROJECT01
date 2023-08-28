package coninfo.dao;


import java.time.LocalDate;
import java.util.List;

import conInfo_vo.ConInfo_VO;

public class ConInfo_Test {
	public static void main(String[] args) {

        // Insert
        ConInfo_DAO dao = new ConInfo_DAO();
        LocalDate nowDate = LocalDate.now();
        
        // Calculate the next Friday
//        LocalDate nextFriday = nowDate.plusDays(5 - nowDate.getDayOfWeek().getValue());
        
        // Calculate the next Monday (assuming Monday is the start of the week)
        // 숫자부분 : 현재요일에서 다음주 월요일까지의 일수
//        LocalDate nextMonday = nowDate.plusDays(7 - nowDate.getDayOfWeek().getValue());
//        // Calculate the Monday after next week
//        LocalDate nextNextMonday = nextMonday.plusWeeks(1);
        
        LocalDate nextFriday = LocalDate.of(2023, 9, 1); // 9월 1일로 고정
        LocalDate nextSaturday = nextFriday.plusDays(1);
        LocalDate nextSunday = nextFriday.plusDays(2);

        LocalDate nextNextFriday = nextFriday.plusWeeks(1);
        LocalDate nextNextSaturday = nextNextFriday.plusDays(1);
        LocalDate nextNextSunday = nextNextFriday.plusDays(2);

        LocalDate nextNextNextFriday = nextNextFriday.plusWeeks(1);
        LocalDate nextNextNextSaturday = nextNextNextFriday.plusDays(1);
        LocalDate nextNextNextSunday = nextNextNextFriday.plusDays(2);
        
        
        ConInfo_VO[] concertInfos = {
//                new ConInfo_VO(1, "예술의전당 여름음악축제", "클래식", 120, nextFriday.toString(), "", "19:00"),
//                new ConInfo_VO(2, "조성진 피아노 리사이틀", "클래식", 100, nextSaturday.toString(), "", "19:30"),
//                new ConInfo_VO(3, "유니버설발레단<돈키호테>", "발레", 90, nextSunday.toString(), "", "20:00"),
//                new ConInfo_VO(4, "BORN PINK WORLD TOUR", "케이팝", 120, nextNextFriday.toString(), "", "20:00"),
//                new ConInfo_VO(5, "Sam Smith", "팝", 70, nextNextSaturday.toString(), "", "19:30"),
//                new ConInfo_VO(6, "Charlie Puth Live in Seoul", "팝", 120, nextNextSunday.toString(), "", "19:00"),
//                new ConInfo_VO(7, "Bruno Mars", "팝", 90, nextNextFriday.toString(), "", "20:00"),
//                new ConInfo_VO(8, "김종욱 찾기", "뮤지컬", 100, nextNextNextSaturday.toString(), "", "19:30"),
//                new ConInfo_VO(9, "Les Miserables", "뮤지컬", 150, nextNextNextSaturday.toString(), "", "19:00"),
//                new ConInfo_VO(10, "MAMMA MIA!", "뮤지컬", 100, nextNextNextSunday.toString(), "", "19:30")
        		
//                new ConInfo_VO(1, "예술의전당 여름음악축제", "클래식", 120, nextFriday.toString(), "예술의전당", "19:00"),
//                new ConInfo_VO(2, "조성진의 피아노 리사이틀", "클래식", 100, nextSaturday.toString(), "콘서트홀", "19:30"),
//                new ConInfo_VO(3, "유니버설발레단<돈키호테>", "발레", 90, nextSunday.toString(), "재즈클럽", "20:00"),
//                new ConInfo_VO(4, "BORN PINK WORLD TOUR", "케이팝", 120, nextNextFriday.toString(), "올림픽공원", "20:00"),
//                new ConInfo_VO(5, "Sam Smith", "팝", 70, nextNextSaturday.toString(), "올림픽공원", "19:30"),
//                new ConInfo_VO(6, "Charlie Puth Live in Seoul", "팝", 120, nextNextSunday.toString(), "올림픽공원", "19:00"),
//                new ConInfo_VO(7, "Bruno Mars", "팝", 90, nextNextFriday.toString(), "잠실실내체육관", "20:00"),
//                new ConInfo_VO(8, "김종욱 찾기", "뮤지컬", 100, nextNextNextSaturday.toString(), "수원 경기아트센터 대극장", "19:30"),
//                new ConInfo_VO(9, "Les Miserables", "뮤지컬", 150, nextNextNextSaturday.toString(), "부산 드림씨어터", "19:00"),
//                new ConInfo_VO(10, "MAMMA MIA!", "뮤지컬", 100, nextNextNextSunday.toString(), "광주예술의전당 대극장", "19:30")
            };
        
        
            System.out.println("---- insert(vo) ----");

            for (ConInfo_VO concertInfo : concertInfos) {
                int result = dao.insert(concertInfo);
                System.out.println("입력결과 : " + result);
            }

            System.out.println("---- insert(vo) completed ----");
            
            // 출력하는 부분 추가
            List<ConInfo_VO> concertList = dao.selectAll();
            System.out.println("<콘서트 INFORMATION>");
            System.out.println("번호\t콘서트명\t\t\t\t장르\t러닝타임\t날짜\t\t\t장소\t\t시간\t\t홀아이디");
            for (ConInfo_VO vo : concertList) {
                System.out.println(String.format("%-1s\t%-24s\t%-5s\t%-5s\t%-10s\t%-20s\t%-10s\t%-10s",
                    vo.getConcert_id(), vo.getTitle(), vo.getGenre(),
                    vo.getRunning_time(), vo.getConcert_date().substring(0, 10), vo.getLocation(),
                    vo.getTime(), vo.getHall_id()));
            } 
            System.out.println("---------------------------------------------------------------");

    
//            System.out.println("---- update -----");
//            int updateResult = conInfoDAO.update(selectedConcert);
//            System.out.println("updateResult 값: " + updateResult);
//            if (updateResult > 0) {
//                System.out.println("콘서트 정보가 수정되었습니다.");
//            } else {
//                System.out.println("콘서트 정보 수정에 실패하였습니다.");
//            }
//            
            
            
        // Delete
//        System.out.println("----- delete(id) -----");
//        int deleteResult = dao.delete(concert_info);
//        System.out.println("삭제건수 : " + deleteResult);
        
            
            // 전부 삭제
//            System.out.println("----- delete(id) -----");
//
//            for (ConInfo_VO concertInfo : concertInfos) {
//                int deleteResult = dao.delete(concertInfo);
//                System.out.println("삭제건수 : " + deleteResult);
//            }
//
//            System.out.println("----- delete(id) completed -----");
		
	}
}