import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class updatestudentdetails extends JFrame implements ActionListener {
    JComboBox<String> studentDropdown;
    JTextField jname, jemail, jphnNum, jage, jcourse, jamount, jpaymentstatus;
    JButton updateButton, fetchButton,jbBack;
    Connection con;
    JLabel background;

    public updatestudentdetails() {
        setTitle("Update Student Info");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setContentPane(new JLabel(new ImageIcon("assets/Student.png"))); // Set background image
        setLayout(new BorderLayout());
        
        ImageIcon backIcon = new ImageIcon("assets/left-arrow.png"); 
        Image backImg = backIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Resize the image
        jbBack = new JButton(new ImageIcon(backImg));
        jbBack.setBorder(BorderFactory.createEmptyBorder());  
        jbBack.setContentAreaFilled(false);  
        jbBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbBack.setBounds(30,20, 50, 50);
        add(jbBack);
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        studentDropdown = new JComboBox<>();
        fetchStudentIDs();
        fetchButton = new JButton("Fetch Details");
        fetchButton.addActionListener(this);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Select Student ID:"), gbc);
        gbc.gridx = 1;
        panel.add(studentDropdown, gbc);
        gbc.gridx = 2;
        panel.add(fetchButton, gbc);
        
        addField(panel, "Name:", jname = new JTextField(), 1, gbc);
        addField(panel, "Email:", jemail = new JTextField(), 2, gbc);
        addField(panel, "Phone Number:", jphnNum = new JTextField(), 3, gbc);
        addField(panel, "Age:", jage = new JTextField(), 4, gbc);
        addField(panel, "Course:", jcourse = new JTextField(), 5, gbc);
        addField(panel, "Amount:", jamount = new JTextField(), 6, gbc);
        addField(panel, "Payment Status:", jpaymentstatus = new JTextField(), 7, gbc);
        
        updateButton = new JButton("Update Info");
        updateButton.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 8;
        panel.add(updateButton, gbc);
        
        add(panel, BorderLayout.CENTER);
        setVisible(true);
        jbBack.addActionListener(this);
    }

    private void addField(JPanel panel, String label, JTextField field, int y, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(field, gbc);
        gbc.gridwidth = 1;
    }

    private void fetchStudentIDs() {
        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT studentid FROM studentdetails");
            while (rs.next()) {
                studentDropdown.addItem(rs.getString("studentid"));
            }
          rs.close();
         stmt.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error fetching student IDs");
        }
    }

    @Override
    public void actionPerformed(ActionEvent Ae) {
        if (Ae.getSource() == fetchButton) {
            fetchStudentDetails();
        } else if (Ae.getSource() == updateButton) {
            updateStudentInfo();
        }
        else if(Ae.getSource().equals(jbBack))
        {
        	 dispose(); // Close the login frame
             new EmployeePortal().setVisible(true);
        }
    }

    private void fetchStudentDetails() {
        String studentID = (String) studentDropdown.getSelectedItem();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM studentdetails WHERE studentid = ?");
            ps.setString(1, studentID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                jname.setText(rs.getString("name"));
                jemail.setText(rs.getString("email"));
                jphnNum.setText(rs.getString("phonenum"));
                jage.setText(rs.getString("age"));
                jcourse.setText(rs.getString("course"));
                jamount.setText(rs.getString("amount"));
                jpaymentstatus.setText(rs.getString("paymentstatus"));
            }
          rs.close();
          ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error fetching student details");
            System.out.println(e);
        }
    }

    private void updateStudentInfo() {
        String studentID = (String) studentDropdown.getSelectedItem();
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE studentdetails SET name=?, email=?, phonenum=?, age=?, course=?, amount=?, paymentstatus=? WHERE studentid=?");
            ps.setString(1, jname.getText());
            ps.setString(2, jemail.getText());
            ps.setString(3, jphnNum.getText());
            ps.setString(4, jage.getText());
            ps.setString(5, jcourse.getText());
            ps.setString(6, jamount.getText());
            ps.setString(7, jpaymentstatus.getText());
            ps.setString(8, studentID);
            
            int rowsUpdated = ps.executeUpdate();
            ps.close();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Student details updated successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Update failed");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating student details");
        }
        
    }
    
}
