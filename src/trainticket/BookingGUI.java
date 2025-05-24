package train_ticket;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class BookingGUI extends JFrame {
    private JTextField trainNumberField;
    private JDateChooser journeyDateChooser;
    private JButton bookButton;
    private int userId;

    public BookingGUI(int userId) {
        this.userId = userId;
        setTitle("IRCTC Booking");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel trainNumberLabel = new JLabel("Train Number");
        trainNumberLabel.setBounds(10, 20, 80, 25);
        panel.add(trainNumberLabel);

        trainNumberField = new JTextField(20);
        trainNumberField.setBounds(100, 20, 165, 25);
        panel.add(trainNumberField);

        JLabel journeyDateLabel = new JLabel("Journey Date");
        journeyDateLabel.setBounds(10, 50, 80, 25);
        panel.add(journeyDateLabel);

        journeyDateChooser = new JDateChooser();
        journeyDateChooser.setBounds(100, 50, 165, 25);
        panel.add(journeyDateChooser);

        bookButton = new JButton("Book");
        bookButton.setBounds(10, 80, 80, 25);
        panel.add(bookButton);

        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TicketDAO ticketDAO = new TicketDAO();
                Ticket ticket = new Ticket();
                ticket.setUserId(userId);
                ticket.setTrainNumber(trainNumberField.getText());
                ticket.setJourneyDate(journeyDateChooser.getDate());
                if (ticketDAO.bookTicket(ticket)) {
                    JOptionPane.showMessageDialog(null, "Ticket Booked Successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Booking Failed");
                }
            }
        });
    }
}
