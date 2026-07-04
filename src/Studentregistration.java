import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;
import javax.swing.border.Border;

public class StudentRegistration extends JFrame implements ActionListener, ItemListener {
    JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7, jl8, jl12, jl13, jl14, jl15;
    JButton jb1,jbBack;
    JComboBox<String> jc1;
    JRadioButton jr1, jr2, jr3;
    JTextArea jta;
    JTextField jstudentid, jname, jemail, jphnNum, jzipcode, jcourse, jamount, jpaymentstatus, jage;
    ButtonGroup genderGroup;
    String studentid, Name, Email, age, Pnum, Gender, Language, Zipcode, abt, Course, amount, Paymentstatus;

    public StudentRegistration() {
        setTitle("Student Registration");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel with a background image
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon bgIcon = new ImageIcon("assets/Student.png");
                g.drawImage(bgIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        ImageIcon backIcon = new ImageIcon("assets/left-arrow.png"); 
        Image backImg = backIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Resize the image
        jbBack = new JButton(new ImageIcon(backImg));
        jbBack.setBorder(BorderFactory.createEmptyBorder());  
        jbBack.setContentAreaFilled(false);  
        jbBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbBack.setBounds(30,20, 50, 50);
        add(jbBack);


        panel.setLayout(new GridBagLayout());
        panel.setOpaque(false);
        add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Labels and Fields
        jl12 = createLabel("Student ID:");
        jstudentid = createTextField();
        addComponent(panel, jl12, jstudentid, gbc, 0);

        jl1 = createLabel("Name:");
        jname = createTextField();
        addComponent(panel, jl1, jname, gbc, 1);

        jl3 = createLabel("Age:");
        jage = createTextField();
        addComponent(panel, jl3, jage, gbc, 2);

        jl2 = createLabel("Email:");
        jemail = createTextField();
        addComponent(panel, jl2, jemail, gbc, 3);

        jl4 = createLabel("Phone Number:");
        jphnNum = createTextField();
        addComponent(panel, jl4, jphnNum, gbc, 4);

        jl5 = createLabel("Gender:");
        JPanel genderPanel = new JPanel(new FlowLayout());
        genderPanel.setOpaque(false);
        jr1 = new JRadioButton("Male");
        jr2 = new JRadioButton("Female");
        jr3 = new JRadioButton("Other");
        genderGroup = new ButtonGroup();
        genderGroup.add(jr1);
        genderGroup.add(jr2);
        genderGroup.add(jr3);
        genderPanel.add(jr1);
        genderPanel.add(jr2);
        genderPanel.add(jr3);
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(jl5, gbc);
        gbc.gridx = 1;
        panel.add(genderPanel, gbc);

        jl6 = createLabel("Language:");
        String[] languages = {"Select language", "Telugu", "Hindi", "English"};
        jc1 = new JComboBox<>(languages);
        jc1.addItemListener(this);
        addComponent(panel, jl6, jc1, gbc, 6);

        jl7 = createLabel("Zip Code:");
        jzipcode = createTextField();
        addComponent(panel, jl7, jzipcode, gbc, 7);

        jl8 = createLabel("About:");
        jta = new JTextArea(3, 20);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        jta.setBorder(border);
        jta.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(jl8, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(new JScrollPane(jta), gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        jl15 = createLabel("Course:");
        jcourse = createTextField();
        addComponent(panel, jl15, jcourse, gbc, 9);

        jl14 = createLabel("Amount:");
        jamount = createTextField();
        addComponent(panel, jl14, jamount, gbc, 10);

        jl13 = createLabel("Payment Status:");
        jpaymentstatus = createTextField();
        addComponent(panel, jl13, jpaymentstatus, gbc, 11);

        jb1 = new JButton("Register");
        jb1.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 12;
        panel.add(jb1, gbc);

        jr1.addActionListener(new GenderActionListener());
        jr2.addActionListener(new GenderActionListener());
        jr3.addActionListener(new GenderActionListener());
        jbBack.addActionListener(this);

        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 12));
        return textField;
    }

    private void addComponent(JPanel panel, JComponent label, JComponent field, GridBagConstraints gbc, int y) {
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(label, gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    @Override
    public void itemStateChanged(ItemEvent Ie) {
        if (Ie.getSource().equals(jc1)) {
            Language = (String) jc1.getSelectedItem();
        }
    }

    class GenderActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(jr1)) {
                Gender = "Male";
            } else if (e.getSource().equals(jr2)) {
                Gender = "Female";
            } else if (e.getSource().equals(jr3)) {
                Gender = "Other";
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent Ae) {
        if (Ae.getSource().equals(jb1)) {
            studentid = jstudentid.getText();
            Name = jname.getText();
            Email = jemail.getText();
            age = jage.getText();
            Pnum = jphnNum.getText();
            Zipcode = jzipcode.getText();
            abt = jta.getText();
            Course = jcourse.getText();
            amount = jamount.getText();
            Paymentstatus = jpaymentstatus.getText();

            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
                System.out.println("Connection Successful: " + con);
                PreparedStatement psmt = con.prepareStatement("insert into studentdetails values(?,?,?,?,?,?,?,?,?,?,?,?)");
                psmt.setString(1, Name);
                psmt.setString(2, Email);
                psmt.setString(3, Pnum);
                psmt.setString(4, Gender);
                psmt.setString(5, Language);
                psmt.setString(6, Zipcode);
                psmt.setString(7, abt);
                psmt.setString(8, age);
                psmt.setString(9, studentid);
                psmt.setString(10, Paymentstatus);
                psmt.setString(11, amount);
                psmt.setString(12, Course);

                int i = psmt.executeUpdate();
              psmt.close();
              con.close();
                System.out.println(i);
                JFrame j = new JFrame();

                if (i == 1) {
                    JOptionPane.showMessageDialog(j, "Registration Successful");
                    dispose(); 
                    new EmployeePortal().setVisible(true); 
                } else {
                    JOptionPane.showMessageDialog(j, "Registration Failed");
                
              }
               

            } catch (Exception e) {
                System.out.println(e);
            }
           
        }
        else if(Ae.getSource().equals(jbBack))
        {
        	 dispose(); 
             new EmployeePortal().setVisible(true);
        }
    }

 
}
