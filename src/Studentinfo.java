
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class studentinfo extends JFrame implements ActionListener {

    JComboBox<String> studentDropdown;
    JButton fetchButton, backButton;
    JTable studentTable;
    DefaultTableModel tableModel;
    Connection con;

    public studentinfo() {
        setTitle("Fetch Student Details");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);  

        
        ImageIcon bgImg = new ImageIcon("assets/bg2.jpg"); 
        Image image = bgImg.getImage().getScaledInstance(800, 500, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(image));
        background.setBounds(0, 0, 800, 500);
        add(background);

   
        JLabel idLabel = new JLabel("Select Student ID:");
        idLabel.setForeground(Color.WHITE);
        idLabel.setFont(new Font("Arial", Font.BOLD, 14));
        idLabel.setBounds(250, 20, 150, 25);
        background.add(idLabel);

        studentDropdown = new JComboBox<>();
        studentDropdown.setBounds(400, 20, 120, 25);
        background.add(studentDropdown);

        fetchButton = new JButton("Fetch");
        fetchButton.setBounds(530, 20, 80, 25);
        fetchButton.addActionListener(this);
        background.add(fetchButton);

       
        String[] columnNames = {"Name", "Email", "Phone", "Age", "Course", "Amount", "Payment"};
        tableModel = new DefaultTableModel(columnNames, 0);
        studentTable = new JTable(tableModel);
        studentTable.setFont(new Font("SansSerif", Font.PLAIN, 14));
        studentTable.setRowHeight(25);
        studentTable.setGridColor(Color.LIGHT_GRAY);
        studentTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBounds(100, 80, 600, 200);
        background.add(scrollPane);

     
        backButton = new JButton("Back");
        backButton.setBounds(350, 400, 100, 30);
        backButton.addActionListener(e -> {
            this.dispose();
           new EmployeePortal().setVisible(true); 
        });
        background.add(backButton);

      
        fetchStudentIDs();

        setVisible(true);
    }

    private void fetchStudentIDs() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT studentid FROM studentdetails");
            while (rs.next()) {
                studentDropdown.addItem(rs.getString("studentid"));
            }
          rs.close();
         
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading student IDs: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fetchButton) {
            fetchStudentDetails();
        }
    }

    private void fetchStudentDetails() {
        String studentID = (String) studentDropdown.getSelectedItem();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM studentdetails WHERE studentid = ?");
            ps.setString(1, studentID);
            ResultSet rs = ps.executeQuery();

            tableModel.setRowCount(0); // Clear old data

            if (rs.next()) {
                String[] row = {
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phonenumber"),
                        rs.getString("age"),
                        rs.getString("course"),
                        rs.getString("amount"),
                        rs.getString("paymentstatus")
                };
                tableModel.addRow(row);
            } else {
                JOptionPane.showMessageDialog(this, "No student found with ID: " + studentID);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error fetching data: " + ex.getMessage());
        }
    }

    
}
