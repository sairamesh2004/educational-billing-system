import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class AdminPortal extends JFrame implements ActionListener{
JButton jb1,jb2,jb3,jb4;
	
	public AdminPortal() {
	
		jb1= new JButton("All Students Details");
		jb2=new JButton("InVoice Records");
		jb3= new JButton("Fee Details");
		jb4=new JButton("Employee Details");
		setLayout(null);
		
		jb1.setBounds(250,100, 150, 70);
		jb2.setBounds(450,100, 150, 70);
		jb3.setBounds(250,200, 150,70);
		jb4.setBounds(450, 200,150, 70);
		add(jb1);
		add(jb2);
		add(jb3);
		add(jb4);
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent Ae) {
		if(Ae.getSource().equals(jb1)) 
		{
			new AdminPortal();
			dispose();
			AllStudentDetails asd=new AllStudentDetails();
			asd.setTitle("All Student Details");
			asd.setSize(1000, 600);
			asd.setVisible(true);
		}
		if(Ae.getSource().equals(jb2)) {
			new AdminPortal();
			dispose();
			InVoiceRecord iv = new InVoiceRecord();
			iv.setTitle("InVoice Record");
			iv.setSize(1000, 600);
			iv.setVisible(true);
			
		}
		if (Ae.getSource().equals(jb3)) {
			new AdminPortal();
			dispose();
			FeeDetails fd=new FeeDetails();
			fd.setTitle("Fee Details");
			fd.setSize(1000,600);
			fd.setVisible(true);
			
		}
		if (Ae.getSource().equals(jb4)) {
			EmployeeDetails ed=new EmployeeDetails();
			ed.setTitle("Employee Details");
			ed.setSize(1000,600);
			ed.setVisible(true);
			
		}
		
	}

	}
