package train_ticket;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancellationGUI extends JFrame {
    private JTextField ticketIdField;
    private JButton cancelButton;

    public CancellationGUI() {
        setTitle("IRCTC Cancellation");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel ticketIdLabel = new JLabel("Ticket ID");
        ticketIdLabel.setBounds(10, 20, 80, 25);
        panel.add(ticketIdLabel);

        ticketIdField = new JTextField(20);
        ticketIdField.setBounds(100, 20, 165, 25);
        panel.add(ticketIdField);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(10, 50, 80, 25);
        panel.add(cancelButton);

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TicketDAO ticketDAO = new TicketDAO();
                int ticketId = Integer.parseInt(ticketIdField.getText());
                if (ticketDAO.cancelTicket(ticketId)) {
                    JOptionPane.showMessageDialog(null, "Ticket Cancelled Successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Cancellation Failed");
                }
            }
        });
    }
}

