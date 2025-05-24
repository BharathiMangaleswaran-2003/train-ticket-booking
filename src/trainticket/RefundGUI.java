package train_ticket;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefundGUI extends JFrame {
    private JTextField ticketIdField;
    private JTextField refundAmountField;
    private JButton refundButton;

    public RefundGUI() {
        setTitle("IRCTC Refund");
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

        JLabel ticketIdLabel = new JLabel("Ticket ID");
        ticketIdLabel.setBounds(10, 20, 80, 25);
        panel.add(ticketIdLabel);

        ticketIdField = new JTextField(20);
        ticketIdField.setBounds(100, 20, 165, 25);
        panel.add(ticketIdField);

        JLabel refundAmountLabel = new JLabel("Refund Amount");
        refundAmountLabel.setBounds(10, 50, 100, 25);
        panel.add(refundAmountLabel);

        refundAmountField = new JTextField(20);
        refundAmountField.setBounds(120, 50, 145, 25);
        panel.add(refundAmountField);

        refundButton = new JButton("Refund");
        refundButton.setBounds(10, 80, 80, 25);
        panel.add(refundButton);

        refundButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RefundDAO refundDAO = new RefundDAO();
                Refund refund = new Refund();
                refund.setTicketId(Integer.parseInt(ticketIdField.getText()));
                refund.setRefundAmount(Double.parseDouble(refundAmountField.getText()));
                refund.setRefundDate(new java.util.Date());
                if (refundDAO.addRefund(refund)) {
                    JOptionPane.showMessageDialog(null, "Refund Processed Successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Refund Failed");
                }
            }
        });
    }
}
