package coninfo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conInfo_vo.ConInfo_VO;

public class ConInfo_DAO {
    private static final String DRIVER = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@192.168.18.6:1521:xe";
    private static final String USER = "project1";
    private static final String PASSWORD = "project1";

    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // SELECTALL -----------------------------------------------------------
    public List<ConInfo_VO> selectAll() {
        List<ConInfo_VO> list = new ArrayList<>();

        try {
            // 1. DB 연결
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // 2. SQL 문 실행
            String sql = "SELECT CONCERT_ID, TITLE, GENRE, RUNNING_TIME, CONCERT_DATE, LOCATION, TIME, HALL_ID FROM CONCERT_INFO ORDER BY CONCERT_ID";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            // 3. 결과 처리
            while (rs.next()) {
                ConInfo_VO vo = new ConInfo_VO(
                        rs.getInt("Concert_id"),
                        rs.getString("Title"),
                        rs.getString("Genre"),
                        rs.getInt("Running_time"),
                        rs.getString("Concert_date"),
                        rs.getString("Location"),
                        rs.getString("Time"),
                		rs.getInt("Hall_id"));
                list.add(vo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 4. 자원 반납
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            close();
        }

        return list;
    }
    // SELECTONE 추가 (28일(월))
    public ConInfo_VO selectOne(String concertscanner) {
        ConInfo_VO vo = null;

        try {
            // 1. DB 연결
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // 2. SQL 문 실행
            String sql = "SELECT CONCERT_ID, TITLE, GENRE, RUNNING_TIME, CONCERT_DATE, LOCATION, TIME, HALL_ID FROM CONCERT_INFO WHERE CONCERT_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(concertscanner));

            // 3. 결과 처리
            rs = pstmt.executeQuery();

            if (rs.next()) {
                vo = new ConInfo_VO(
                        rs.getInt("Concert_id"),
                        rs.getString("Title"),
                        rs.getString("Genre"),
                        rs.getInt("Running_time"),
                        rs.getString("Concert_date"),
                        rs.getString("Location"),
                        rs.getString("Time"),
                		rs.getInt("Hall_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 4. 자원 반납
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            close();
        }

        return vo;
    }
    // INSERT -------------------------------------
    public int insert(ConInfo_VO vo) {
        int result = 0;

        try {
            // 1. DB 연결
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "INSERT INTO CONCERT_INFO (CONCERT_ID, TITLE, GENRE, RUNNING_TIME, CONCERT_DATE, LOCATION, TIME, HALL_ID) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, vo.getConcert_id());
            pstmt.setString(2, vo.getTitle());
            pstmt.setString(3, vo.getGenre());
            pstmt.setInt(4, vo.getRunning_time());
            pstmt.setString(5, vo.getConcert_date());
            pstmt.setString(6, vo.getLocation());
            pstmt.setString(7, vo.getTime());
            pstmt.setInt(8,  vo.getHall_id());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("[예외발생] 작업중 예외가 발생 : " + e.getMessage());
            result = -1;
        } finally {
            close();
        }

        return result;
    }
    
    // UPDATE ------------------------------------------
    public int update(ConInfo_VO vo) {
    	int result = 0;
    	try {
			conn = DriverManager.getConnection(URL, USER,PASSWORD);
			
	    	StringBuilder sql = new StringBuilder(); // 테이블 이름 수정
	    	sql.append("UPDATE CONCERT_INFO ");
	    	sql.append("   SET CONCERT_ID = ? ");
	    	sql.append("   	 , TITLE = ? ");
	    	sql.append("   	 , GENRE = ? ");
	    	sql.append("   	 , RUNNING_TIME = ? ");
	    	sql.append("   	 , CONCERT_DATE = ? ");
	    	sql.append("   	 , LOCATION = ? ");
	    	sql.append("   	 , TIME = ? "); // 컬럼 이름 수정
	    	sql.append("     , HALL_ID = ? ");
	    	sql.append(" WHERE CONCERT_ID = ?");
			
	    	
	    	pstmt = conn.prepareStatement(sql.toString());

	    	int i = 1;
	    	pstmt.setInt(i++, vo.getConcert_id());
            pstmt.setString(i++, vo.getTitle());
            pstmt.setString(i++, vo.getGenre());
            pstmt.setInt(i++, vo.getRunning_time());
            pstmt.setString(i++, vo.getConcert_date());
            pstmt.setString(i++, vo.getLocation());
            pstmt.setString(i++, vo.getTime());
            pstmt.setInt(i++,  vo.getHall_id()); //추가된 부분
            pstmt.setInt(i++,  vo.getConcert_id()); // WHERE 조건에 사용할 값
			
			result = pstmt.executeUpdate();
	    	
	    	
    	} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("[예외발생] 작업중 예외가 발생 : " + e.getMessage());
			result = -1;
		} finally {
			//5. 클로징 처리에 의한 자원 반납
			close();
		}
    	
		return result;
    }
    
    // DELETE ------------------------------------------
    public int delete(ConInfo_VO vo) {
        int result = 0;

        try {
            // 1. DB 연결
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "DELETE FROM CONCERT_INFO WHERE CONCERT_ID = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, vo.getConcert_id());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("[예외발생] 작업중 예외가 발생 : " + e.getMessage());
            result = -1;
        } finally {
            close();
        }

        return result;
    }

    private void close() {
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}