import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.sql.*;

import javax.swing.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GenerateInvoice extends JFrame implements ActionListener {

    JTextField txtStudentId;
    JLabel lblName, lblCourse, lblAmount, lblStatus;

    JButton btnFetch;
    JButton btnGenerate;
    JButton btnBack;

    String name;
    String course;
    String amount;
    String paymentStatus;

    Connection con;

    public GenerateInvoice() {

        setTitle("Generate Invoice");
        setSize(700,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel title = new JLabel("Generate Invoice");
        title.setFont(new Font("Arial",Font.BOLD,22));
        title.setBounds(240,20,250,30);
        add(title);

        JLabel sid = new JLabel("Student ID");
        sid.setBounds(70,80,100,25);
        add(sid);

        txtStudentId = new JTextField();
        txtStudentId.setBounds(170,80,180,25);
        add(txtStudentId);

        btnFetch = new JButton("Fetch");
        btnFetch.setBounds(380,80,100,25);
        btnFetch.addActionListener(this);
        add(btnFetch);

        lblName = new JLabel("Name : ");
        lblName.setBounds(70,150,500,25);
        add(lblName);

        lblCourse = new JLabel("Course : ");
        lblCourse.setBounds(70,190,500,25);
        add(lblCourse);

        lblAmount = new JLabel("Amount : ");
        lblAmount.setBounds(70,230,500,25);
        add(lblAmount);

        lblStatus = new JLabel("Payment Status : ");
        lblStatus.setBounds(70,270,500,25);
        add(lblStatus);

        btnGenerate = new JButton("Generate PDF");
        btnGenerate.setBounds(170,350,150,35);
        btnGenerate.addActionListener(this);
        add(btnGenerate);

        btnBack = new JButton("Back");
        btnBack.setBounds(360,350,100,35);
        btnBack.addActionListener(this);
        add(btnBack);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btnFetch)
        {
            fetchStudent();
        }

        if(e.getSource()==btnGenerate)
        {
            generatePDF();
        }

        if(e.getSource()==btnBack)
        {
            dispose();
            new EmployeePortal().setVisible(true);
        }

    }

    private void fetchStudent()
    {

        try
        {

            Class.forName("oracle.jdbc.driver.OracleDriver");

            con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "system",
                    "root");

            PreparedStatement ps=con.prepareStatement(

                    "SELECT name,course,amount,paymentstatus FROM studentdetails WHERE studentid=?"

            );

            ps.setString(1,txtStudentId.getText());

            ResultSet rs=ps.executeQuery();

            if(rs.next())
            {

                name=rs.getString("name");
                course=rs.getString("course");
                amount=rs.getString("amount");
                paymentStatus=rs.getString("paymentstatus");

                lblName.setText("Name : "+name);
                lblCourse.setText("Course : "+course);
                lblAmount.setText("Amount : "+amount);
                lblStatus.setText("Payment Status : "+paymentStatus);

            }
            else
            {
                JOptionPane.showMessageDialog(this,
                        "Student not found");
            }

            rs.close();
            ps.close();
            con.close();

        }

        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this,ex.getMessage());
        }

    }

    private void generatePDF()
    {

        if(name==null)
        {
            JOptionPane.showMessageDialog(this,
                    "Fetch Student Details First");
            return;
        }

        JFileChooser chooser=new JFileChooser();

        chooser.setSelectedFile(
                new java.io.File(
                        "Invoice_"+txtStudentId.getText()+".pdf"));

        int option=chooser.showSaveDialog(this);

        if(option==JFileChooser.APPROVE_OPTION)
        {

            try
            {

                Document document=new Document();

                PdfWriter.getInstance(document,
                        new FileOutputStream(
                                chooser.getSelectedFile()));

                document.open();

                document.add(new Paragraph("EDUCATIONAL BILLING SYSTEM"));

                document.add(new Paragraph("-------------------------------------"));

                document.add(new Paragraph("Student ID : "
                        +txtStudentId.getText()));

                document.add(new Paragraph("Student Name : "
                        +name));

                document.add(new Paragraph("Course : "
                        +course));

                document.add(new Paragraph("Amount : "
                        +amount));

                document.add(new Paragraph("Payment Status : "
                        +paymentStatus));

                document.add(new Paragraph("-------------------------------------"));

                document.add(new Paragraph("Thank You"));

                document.close();

                JOptionPane.showMessageDialog(this,
                        "Invoice Generated Successfully");

            }

            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage());
            }

        }

    }

}
