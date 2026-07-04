import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HomePage extends JFrame implements ActionListener {
    JButton jb1, jb2;
    JLabel arrowLabel1, arrowLabel2;
    ImageIcon arrowIcon, backgroundIcon, buttonIcon;

    public HomePage() {
        setTitle("Home Page");
        setSize(1000, 600); // Updated frame size
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load the background image
        backgroundIcon = new ImageIcon("assets/Student.png");

        // Load and resize the arrow icon
        ImageIcon originalArrowIcon = new ImageIcon("assets/arrow.png");
        Image arrowScaledImage = originalArrowIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        arrowIcon = new ImageIcon(arrowScaledImage);

        // Load and resize the button background icon
        ImageIcon originalButtonIcon = new ImageIcon("assets/button_background.png");
        Image buttonScaledImage = originalButtonIcon.getImage().getScaledInstance(150, 50, Image.SCALE_SMOOTH);
        buttonIcon = new ImageIcon(buttonScaledImage);

        // Custom panel with background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);

        // Create buttons with the button background image
        jb1 = new JButton("Employee Login", buttonIcon);
        jb2 = new JButton("Admin Login", buttonIcon);

        // Create arrow labels
        arrowLabel1 = new JLabel(arrowIcon);
        arrowLabel2 = new JLabel(arrowIcon);

        // Calculate positions to center the buttons
        int buttonWidth = 150;
        int buttonHeight = 50;
        int arrowWidth = 30;
        int arrowHeight = 30;
        int centerX = (1000 - buttonWidth) / 2;
        int centerY1 = (600 / 2) - 100; // First button position
        int centerY2 = (600 / 2) + 50;  // Second button position

        // Set layout and button positions
        arrowLabel1.setBounds(centerX - arrowWidth - 10, centerY1 + (buttonHeight - arrowHeight) / 2, arrowWidth, arrowHeight);
        jb1.setBounds(centerX, centerY1, buttonWidth, buttonHeight);
        arrowLabel2.setBounds(centerX - arrowWidth - 10, centerY2 + (buttonHeight - arrowHeight) / 2, arrowWidth, arrowHeight);
        jb2.setBounds(centerX, centerY2, buttonWidth, buttonHeight);

        // Style buttons
        styleButton(jb1);
        styleButton(jb2);

        // Add components to background panel
        backgroundPanel.add(arrowLabel1);
        backgroundPanel.add(jb1);
        backgroundPanel.add(arrowLabel2);
        backgroundPanel.add(jb2);

        setContentPane(backgroundPanel);

        jb1.addActionListener(this);
        jb2.addActionListener(this);
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setForeground(Color.BLUE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setForeground(Color.BLACK);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent Ae) {
        if (Ae.getSource().equals(jb1)) {
        	dispose();
            EmplyeeLogin el = new EmplyeeLogin();
            el.setTitle("Employee Login");
            el.setSize(1000, 600);
            el.setLocationRelativeTo(null);
            el.setVisible(true);
        }
        if (Ae.getSource().equals(jb2)) {
        	dispose();
            AdminLogin al = new AdminLogin();
            al.setTitle("Admin Login");
            al.setSize(1000, 600);
            al.setLocationRelativeTo(null);
            al.setVisible(true);
        }
    }
}
