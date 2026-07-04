import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class EmployeeDetails extends JFrame {

    JTable table;
    DefaultTableModel model;

    public EmployeeDetails() {

        setTitle("Employee Details");
        setSize(900,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        model = new DefaultTableModel();

        model.addColumn("Username");
        model.addColumn("Name");
        model.addColumn("Age");
        model.addColumn("Position");
        model.addColumn("Phone Number");
        model.addColumn("Address");

        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        loadEmployees();
    }

    private void loadEmployees() {

        try {

            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "system",
                    "root");

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from employeedetails");

            while(rs.next())
            {

                model.addRow(new Object[]{

                        rs.getString("username"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("position"),
                        rs.getString("phonenum"),
                        rs.getString("address")

                });

            }

        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }
}
