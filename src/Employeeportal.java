
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EmployeePortal extends JFrame implements ActionListener {
    JLabel welcomeLabel;
    JButton studentRegBtn, updateDetailsBtn, studentInfoBtn, generateInvoiceBtn;
    private Image backgroundImage;

    EmployeePortal() {
        setTitle("Employee Portal");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        backgroundImage = new ImageIcon("assets/Student.png").getImage();
      
        
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BorderLayout());

        
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);

        welcomeLabel = new JLabel("Welcome " + EmplyeeLogin.getDname(), JLabel.CENTER);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        welcomeLabel.setForeground(Color.WHITE);

       
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0)); 

        headerPanel.add(welcomeLabel, BorderLayout.CENTER);

       
        JPanel buttonPanel = new JPanel(new GridLayout(1,4,20,20));
        buttonPanel.setOpaque(false);

        
        studentRegBtn = createButton("assets/personal-file.png", "Student Registration");
        updateDetailsBtn = createButton("assets/contact-form.png", "Update Student Details");
        studentInfoBtn = createButton("assets/student info.png", "Student Info");
        generateInvoiceBtn = createButton("assets/receipt.png", "Generate Invoice");

        buttonPanel.add(studentRegBtn);
        buttonPanel.add(updateDetailsBtn);
        buttonPanel.add(studentInfoBtn);
        buttonPanel.add(generateInvoiceBtn);

      
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    private JButton createButton(String imagePath, String text) {
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image scaledImage = originalIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JButton button = new JButton(text, scaledIcon);
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(this);

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == studentRegBtn) {
        	dispose();
            StudentRegistration sr = new StudentRegistration();
            sr.setTitle("Student Registration");
            sr.setSize(1000, 600);
            sr.setVisible(true);
        } else if (e.getSource() == updateDetailsBtn) {
        	dispose();
        	updatestudentdetails up = new updatestudentdetails();
            up.setTitle("Update Student Details");
            up.setSize(1000, 600);
            up.setVisible(true);
        } else if (e.getSource() == studentInfoBtn) {
        	dispose();
            studentinfo si = new studentinfo();
            si.setSize(1000, 600);
            si.setVisible(true);
        } else if (e.getSource() == generateInvoiceBtn) {
        	dispose();
            GenerateInvoice gi = new GenerateInvoice();
            gi.setSize(1000, 600);
            gi.setVisible(true);
        }
    }
}
