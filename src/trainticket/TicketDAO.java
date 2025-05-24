package train_ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    public boolean bookTicket(Ticket ticket) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO booktic (userid, train_number, journey_date, status) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, ticket.getUserId());
            stmt.setString(2, ticket.getTrainNumber());
            stmt.setDate(3, new java.sql.Date(ticket.getJourneyDate().getTime()));
            stmt.setString(4, "Booked");
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cancelTicket(int ticketId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE booktic SET status = ? WHERE ticketid = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, "Cancelled");
            stmt.setInt(2, ticketId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Ticket> getTicketsByUserId(int userId) {
        List<Ticket> booktic = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM booktc WHERE userid = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setTicketId(rs.getInt("ticketid"));
                ticket.setUserId(rs.getInt("userid"));
                ticket.setTrainNumber(rs.getString("train_number"));
                ticket.setJourneyDate(rs.getDate("journey_date"));
                ticket.setStatus(rs.getString("status"));
                booktic.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booktic;
    }
}

