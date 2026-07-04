import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FeeDetails extends JFrame {
	

	    private JTable PaidTable, unpaidTable, partiallyPaidTable;
	    private DefaultTableModel PaidModel, unpaidModel, partiallyPaidModel;
	    private JTabbedPane tabbedPane;
	    private JTextField searchField;
	    
	    public FeeDetails() {
	        setTitle("Student Payment Details");
	        setSize(1000, 600); 
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	        
	        
	      

	        PaidModel = new DefaultTableModel(new String[]{"Student ID", "Name", "Amount"," Payment Status","Course"}, 0);
	        unpaidModel = new DefaultTableModel(new String[]{"Student ID", "Name", "Amount"," Payment Status","Course"}, 0);
	        partiallyPaidModel = new DefaultTableModel(new String[]{"Student ID", "Name", "Amount"," Payment Status","Course"}, 0);

	       
	        PaidTable = new JTable(PaidModel);
	        unpaidTable = new JTable(unpaidModel);
	        partiallyPaidTable = new JTable(partiallyPaidModel);
	        
	        tabbedPane = new JTabbedPane();
	        tabbedPane.addTab("Paid", createTablePanel(PaidTable));
	        tabbedPane.addTab("Unpaid", createTablePanel(unpaidTable));
	        tabbedPane.addTab("Partially Paid", createTablePanel(partiallyPaidTable));
	        
	        JPanel searchPanel = new JPanel(new BorderLayout());
	        searchField = new JTextField();
	        JButton searchButton = new JButton("Search");
	        searchButton.addActionListener(e -> searchStudent());

	        searchPanel.add(searchField, BorderLayout.CENTER);
	        searchPanel.add(searchButton, BorderLayout.EAST);
	        searchPanel.setBorder(BorderFactory.createTitledBorder("Search by Student ID or Name"));

	        setLayout(new BorderLayout());
	        add(searchPanel, BorderLayout.NORTH);
	        add(tabbedPane, BorderLayout.CENTER);

	       
	        loadDataFromDatabase();
	    }

	    private JPanel createTablePanel(JTable table) {
	        JPanel panel = new JPanel(new BorderLayout());
	        panel.add(new JScrollPane(table), BorderLayout.CENTER);
	        return panel;
	    }

	    private void loadDataFromDatabase() {
	        String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
	        String user = "system"; 
	        String password = "root"; 
	        try (Connection conn = DriverManager.getConnection(url, user, password)) {
	            String query = "SELECT studentid, name, paymentstatus,amount,course FROM studentdetails";
	            PreparedStatement pstmt = conn.prepareStatement(query);
	            ResultSet rs = pstmt.executeQuery();

	            while (rs.next()) {
	                String studentId = rs.getString("studentid");
	                String name = rs.getString("name");
	                String paymentStatus = rs.getString("paymentstatus");
	                String amount = rs.getString("amount");
	                String Course=rs.getString("course");

	                if ("Paid".equals(paymentStatus)) {
	                    PaidModel.addRow(new Object[]{studentId, name, amount});
	                } else if ("Unpaid".equals(paymentStatus)) {
	                    unpaidModel.addRow(new Object[]{studentId, name, amount});
	                } else if ("Partially Paid".equals(paymentStatus)) {
	                    partiallyPaidModel.addRow(new Object[]{studentId, name, amount});
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Error loading data from database", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }


	    private void searchStudent() {
	        String searchText = searchField.getText().trim();
	        if (searchText.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Please enter a Student ID or Name", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
	        String user = "system"; 
	        String password = "root"; 
	        try (Connection conn = DriverManager.getConnection(url, user, password)) {
	            String query = "SELECT studentid, name, paymentstatus,amount,course FROM studentdetails WHERE student_id = ? OR name LIKE ?";
	            PreparedStatement pstmt = conn.prepareStatement(query);
	            pstmt.setString(1, searchText);
	            pstmt.setString(2, "%" + searchText + "%");
	            ResultSet rs = pstmt.executeQuery();

	           
	            PaidModel.setRowCount(0);
	            unpaidModel.setRowCount(0);
	            partiallyPaidModel.setRowCount(0);

	            boolean found = false;
	            while (rs.next()) {
	                found = true;
	                int studentId = rs.getInt("student_id");
	                String name = rs.getString("name");
	                String paymentStatus = rs.getString("paymentstatus");
	                double amount = rs.getDouble("amount");

	              
	                if ("Paid".equalsIgnoreCase(paymentStatus)) {
	                    PaidModel.addRow(new Object[] { studentId,
                                                        name,
                                                        amount,
                                                     paymentStatus,
                                                        Course });
	                } else if ("Unpaid".equalsIgnoreCase(paymentStatus)) {
	                    unpaidModel.addRow(new Object[] { studentId,
                                                        name,
                                                        amount,
                                                     paymentStatus,
                                                        Course });
	                } else if ("Partially Paid".equalsIgnoreCase(paymentStatus)) {
	                    partiallyPaidModel.addRow(new Object[] { studentId,
                                                        name,
                                                        amount,
                                                     paymentStatus,
                                                        Course });
	                }
	            }

	            if (!found) {
	                JOptionPane.showMessageDialog(this, "No student found with the given ID or Name", "Info", JOptionPane.INFORMATION_MESSAGE);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Error searching student in database", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }

	   
	    
	}

