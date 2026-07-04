import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmplyeeLogin extends JFrame implements ActionListener {

    JLabel jl2, jl3;
    JButton loginBtn, backBtn;
    JTextField jf1;
    JPasswordField jp1;
    String uname, upwd;
    static String dname;
    String dPwd;

    public static String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        EmplyeeLogin.dname = dname;
    }

    public EmplyeeLogin() {
        setTitle("Employee Login");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

       
        ImageIcon bgIcon = new ImageIcon("assets/Student.png");
        JLabel bgLabel = new JLabel(bgIcon);
        bgLabel.setBounds(0, 0, 1000, 600);
        add(bgLabel);

      
        ImageIcon icon = new ImageIcon("assets/padlock.png");
        JLabel logoLabel = new JLabel(icon);
        logoLabel.setBounds(450, 50, 100, 100);
        bgLabel.add(logoLabel);

        
        JPanel cardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(255, 255, 255, 150));
                g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
            }
        };
        cardPanel.setLayout(null);
        cardPanel.setBounds(300, 180, 400, 300);
        cardPanel.setOpaque(false);
        bgLabel.add(cardPanel);

        // Create labels and text fields
        jl2 = new JLabel("Username:");
        jl3 = new JLabel("Password:");
        jl2.setFont(new Font("Arial", Font.BOLD, 16));
        jl3.setFont(new Font("Arial", Font.BOLD, 16));
        jl2.setForeground(Color.DARK_GRAY);
        jl3.setForeground(Color.DARK_GRAY);

        jf1 = new JTextField();
        jp1 = new JPasswordField();
        loginBtn = new JButton("Login");

       
        ImageIcon backIcon = new ImageIcon(new ImageIcon("assets/left-arrow -1.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));


        backBtn = new JButton(backIcon);
        backBtn.setBorder(BorderFactory.createEmptyBorder());  
        backBtn.setContentAreaFilled(false); 
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

       
        jl2.setBounds(50, 50, 100, 25);
        jf1.setBounds(150, 50, 200, 30);
        jl3.setBounds(50, 100, 100, 25);
        jp1.setBounds(150, 100, 200, 30);
        loginBtn.setBounds(100, 180, 200, 40);
        backBtn.setBounds(20, 20, 50, 50); 

       
        jf1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        jp1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

       
        styleButton(loginBtn);

        
        cardPanel.add(jl2);
        cardPanel.add(jl3);
        cardPanel.add(jf1);
        cardPanel.add(jp1);
        cardPanel.add(loginBtn);

        
        bgLabel.add(backBtn);

       
        loginBtn.addActionListener(this);
        backBtn.addActionListener(this);
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(0, 123, 255));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

       
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 102, 204));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 123, 255));
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent Ae) {
        if (Ae.getSource().equals(loginBtn)) {
            uname = jf1.getText();
            upwd = new String(jp1.getPassword());

            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");

                System.out.println("Entered Username = [" + uname + "]");

                PreparedStatement psmt = con.prepareStatement(
                    "SELECT USERNAME, PWD FROM employeedetails WHERE USERNAME = ?"
                );

                psmt.setString(1, uname);

                ResultSet rs = psmt.executeQuery();

                if (rs.next()) {
                  if (rs.next()) {

                    dname = rs.getString("USERNAME");
                    dPwd = rs.getString("PWD");

                    System.out.println("Found User!");
                    System.out.println("DB Username = " + dname);
                    System.out.println("DB Password = " + dPwd);
}
                } else {
                    System.out.println("No user found.");
                }
                System.out.println("Database Username: " + dname);
                System.out.println("Database Password: " + dPwd);

                System.out.println("Entered Username: " + uname);
                System.out.println("Entered Password: " + upwd);

                JFrame j = new JFrame();
                if (uname.equals(dname) && upwd.equals(dPwd)) {
                    JOptionPane.showMessageDialog(j, "Login Successful");
                    EmployeePortal ep = new EmployeePortal();
                    ep.setTitle("Employee Portal");
                    ep.setSize(1000, 600);
                    ep.setVisible(true);
                    dispose(); // Close login frame
                } else {
                    JOptionPane.showMessageDialog(j, "Login Failed");
                }
              rs.close();
              psmt.close();
              con.close();

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        // Back button logic
        if (Ae.getSource().equals(backBtn)) {
            dispose(); // Close the login frame
            new HomePage().setVisible(true); // Redirect to another frame (MainMenu)
        }
    }

    
}
