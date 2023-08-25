package sql.connect;

import java.sql.*;
import java.math.BigDecimal;

public class SQL_Connect {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@192.168.18.6:1521:xe";
        String username = "project1";
        String password = "project1";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT p.*, u.user_id, c.title, r.count, r.totalPrice " +
                           "FROM PAYMENT p " +
                           "JOIN Users u ON p.user_id = u.user_id " +
                           "JOIN ConcertInfo c ON p.reservation_id = c.concert_id " +
                           "JOIN RESERVATION r ON p.reservation_id = r.book_id";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    long paymentId = resultSet.getLong("payment_id");
                    String userId = resultSet.getString("user_id");
                    // 나머지 필요한 데이터도 가져오세요
                    String title = resultSet.getString("title");
                    int count = resultSet.getInt("count");
                    BigDecimal totalPrice = resultSet.getBigDecimal("totalPrice");

                    // 필요한 작업 수행
                    System.out.println("Payment ID: " + paymentId);
                    System.out.println("User ID: " + userId);
                    System.out.println("Concert Title: " + title);
                    System.out.println("Reservation Count: " + count);
                    System.out.println("Total Price: " + totalPrice);
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}