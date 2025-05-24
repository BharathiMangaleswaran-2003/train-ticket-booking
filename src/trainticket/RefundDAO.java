package train_ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RefundDAO {
    public boolean addRefund(Refund refund) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO refunds (ticket_id, refund_amount, refund_date) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, refund.getTicketId());
            stmt.setDouble(2, refund.getRefundAmount());
            stmt.setDate(3, new java.sql.Date(refund.getRefundDate().getTime()));
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
