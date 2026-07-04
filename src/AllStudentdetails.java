import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class AllStudentDetails extends JFrame {

	
	    private JTable table;
	    private DefaultTableModel tableModel;
	    private JTextField searchField;
	    private JButton searchButton;

	    private Connection connection;

	    public AllStudentDetails() {
	        setTitle("Student Details");
	        setSize(600, 400);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);

	        
	        // Initialize components
	        tableModel = new DefaultTableModel();
	        tableModel.addColumn("Student ID");
	        tableModel.addColumn("Name");
	        tableModel.addColumn("Age");
	        tableModel.addColumn("Email");
	        tableModel.addColumn("Phone Number");
	        tableModel.addColumn("Gender");
	        tableModel.addColumn("Language");
	        tableModel.addColumn("ZipCode");
	        tableModel.addColumn("About");
	        tableModel.addColumn("Course");

	        table = new JTable(tableModel);
	        JScrollPane scrollPane = new JScrollPane(table);

	        searchField = new JTextField(20);
	        searchButton = new JButton("Search");
	        searchButton.setPreferredSize(new Dimension(90, 30)); 
	        searchButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                searchStudent();
	            }
	        });

	        JPanel searchPanel = new JPanel();
	        searchPanel.add(new JLabel("Search by ID:"));
	        searchPanel.add(searchField);
	        searchPanel.add(searchButton);
	        
	        searchButton.setBackground(new Color(0, 120, 215));
	        searchButton.setForeground(Color.WHITE);
	        searchButton.setFont(new Font("Arial", Font.BOLD, 12));
	        searchButton.setIcon(new ImageIcon("assets/search_icon.png")); 

	        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	        

	       
	        setLayout(new BorderLayout());
	        add(searchPanel, BorderLayout.NORTH);
	        add(scrollPane, BorderLayout.CENTER);

	       
	        connectToDatabase();

	        
	        loadAllStudents();
	    }

	    private void connectToDatabase() {
	        try {
	            Class.forName("oracle.jdbc.OracleDriver");

	            String url = "jdbc:oracle:thin:@localhost:1521:XE";
	            String username = "system";
	            String password = "root";

	            connection = DriverManager.getConnection(url, username, password);

	            System.out.println("Database Connected!");

	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Oracle JDBC Driver not found.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Failed to connect to database.");
	        }
	    }	

	    private void loadAllStudents() {
	        try {
	            Statement statement = connection.createStatement();
	            ResultSet resultSet = statement.executeQuery("SELECT * FROM studentdetails");

	            tableModel.setRowCount(0); 

	            while (resultSet.next()) {
	                String id = resultSet.getString("studentid");
	                String name = resultSet.getString("name");
	                int age = resultSet.getInt("age");
	                String Email = resultSet.getString("Email");
	                String PhoneNumber = resultSet.getString("PhoneNum");
	                String Gender = resultSet.getString("Gender");
	                String Lang = resultSet.getString("language");
	                String Zipcode = resultSet.getString("Zipcode");
	                String abt = resultSet.getString("ABOUT");
	                String Course = resultSet.getString("course");

	                tableModel.addRow(new Object[]{id, name, age, Email,PhoneNumber,Gender,Lang,Zipcode,abt,Course});
	            }
            resultSet.close();
            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Failed to load student details.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }

	    private void searchStudent() {
	        String searchId = searchField.getText().trim();
	        if (searchId.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Please enter a student ID.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM studentdetails WHERE studentid = ?");
	            preparedStatement.setString(1, searchId);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            tableModel.setRowCount(0); 

	            if (resultSet.next()) {
	            	 String id = resultSet.getString("studentid");
		                String name = resultSet.getString("name");
		                int age = resultSet.getInt("age");
		                String Email = resultSet.getString("Email");
		                String PhoneNumber = resultSet.getString("PhoneNum");
		                String Gender = resultSet.getString("Gender");
		                String Lang = resultSet.getString("language");
		                String Zipcode = resultSet.getString("Zipcode");
		                String abt = resultSet.getString("ABOUT");
		                String Course = resultSet.getString("course");

		                tableModel.addRow(new Object[]{id, name, age, Email,PhoneNumber,Gender,Lang,Zipcode,abt,Course});
	            } else {
	                JOptionPane.showMessageDialog(this, "No student found with ID: " + searchId, "Not Found", JOptionPane.INFORMATION_MESSAGE);
	            }
            resultSet.close();
            preparedStatement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Failed to search for student.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }

	        
	    }
	


