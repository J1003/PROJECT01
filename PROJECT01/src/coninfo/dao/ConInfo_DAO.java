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

    // SELECT -----------------------------------------------------------
    public List<ConInfo_VO> selectAll() {
        List<ConInfo_VO> list = new ArrayList<>();

        try {
            // 1. DB 연결
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // 2. SQL 문 실행
            String sql = "SELECT CONCERT_ID, TITLE, GENRE, RUNNING_TIME, CONCERT_DATE, LOCATION, TIME FROM RESERVATION ORDER BY CONCERT_ID";
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
                		rs.getString("Time"));
                list.add(vo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 4. 자원 반납
            close();
        }

        return list;
    }

    // INSERT -------------------------------------
    public int insert(ConInfo_VO vo) {
        int result = 0;

        try {
            // 1. DB 연결
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "INSERT INTO CONCERT_INFO (CONCERT_ID, TITLE, GENRE, RUNNING_TIME, CONCERT_DATE, LOCATION, TIME) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, vo.getConcert_id());
            pstmt.setString(2, vo.getTitle());
            pstmt.setString(3, vo.getGenre());
            pstmt.setInt(4, vo.getRunning_time());
            pstmt.setString(5, vo.getConcert_date());
            pstmt.setString(6, vo.getLocation());
            pstmt.setString(7, vo.getTime());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("[예외발생] 작업중 예외가 발생 : " + e.getMessage());
            result = -1;
        } finally {
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