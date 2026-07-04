import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Enterypage extends JFrame implements ActionListener {
    JButton enterButton;
    ImageIcon ii, buttonIcon;

    public Enterypage() {
        setTitle("Entry Page");

        // Load the background image
        ii = new ImageIcon("assets/Home Page.png");
        
        // Load and resize the button icon
        ImageIcon originalIcon = new ImageIcon("assets/enter_button2.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        buttonIcon = new ImageIcon(scaledImage);

        // Custom panel with background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // Create button with image
        enterButton = new JButton(buttonIcon);
        styleButton(enterButton);

        // Add button to a panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0)); // Center with spacing
        buttonPanel.add(enterButton);

        // Add button panel to the background panel with adjusted position
        JPanel containerPanel = new JPanel(new GridBagLayout());
        containerPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 100, 0); // Move button up
        containerPanel.add(buttonPanel, gbc);
        backgroundPanel.add(containerPanel, BorderLayout.SOUTH);

        setContentPane(backgroundPanel);

        // Frame settings
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Glow effect
        button.setBorder(BorderFactory.createLineBorder(new Color(173, 216, 230), 10, true));

        // Hover effect - change glow color and add shadow
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(135, 206, 250), 10, true),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)
                ));
                button.setBorderPainted(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBorder(BorderFactory.createLineBorder(new Color(173, 216, 230), 10, true));
                button.setBorderPainted(false);
            }
        });

        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enterButton) {
            dispose();
            HomePage hp = new HomePage();
            hp.setTitle("Home Page");
            hp.setSize(1000, 600);
            hp.setLocationRelativeTo(null);
            hp.setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Enterypage::new);
    }
}
