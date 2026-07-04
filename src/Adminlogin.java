import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class AdminLogin extends JFrame implements ActionListener {

	JLabel jl1,jl2,jl3;
	JButton jb;
	JTextField jf1,jf2;
	JPasswordField jp1;
	String aname;
	String apwd;
	String defaultname="admin";
	String defaultpwd="admin";
	
	public AdminLogin() {
	  
		jl1=new JLabel("Admin LOGIN");
		jl2= new JLabel("Username:");
		jl3= new JLabel("Password:");
		
		jb = new JButton("Login");
		
		jf1= new JTextField();
		jp1= new JPasswordField();
		
		setLayout(null);
		
		jl1.setBounds(350, 100, 100, 100);
		jl2.setBounds(270, 190, 200, 50);
		jl3.setBounds(270,230, 200,50);
		jf1.setBounds(350, 210, 100,20);
		jp1.setBounds(350, 250, 100,20);
		jb.setBounds(350,300, 70, 30);
		
		add(jb);
		add(jf1);
		add(jp1);
		add(jl1);
		add(jl2);
		add(jl3);
		
		jb.addActionListener(this);
		
		
			

}

	@Override
	public void actionPerformed(ActionEvent Ae) {
		
		if (Ae.getSource().equals(jb)) 
		{
			aname=jf1.getText();
			apwd=jp1.getText();
			JFrame j= new JFrame(); 
			try {
				
				if(aname.equals(defaultname) && apwd.equals(defaultpwd))
				{
					JOptionPane.showMessageDialog(j,"Admin Login Sucessful");
          dispose();
					AdminPortal ap = new AdminPortal();
					ap.setTitle("Admin Portal");
					ap.setSize(1000,600);
					ap.setVisible(true);
				}
				else
				{
					JOptionPane.showMessageDialog(j,"Admin Login Failed");
				}
				
			} catch (Exception e) {
			 System.out.println(e);
			}
		}}}
