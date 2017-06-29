import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;
class bankApp extends JFrame implements ActionListener
{
boolean log=false;
JLabel bnk,user,pass,wlcm,pic1;
JButton login,cncl;
JPanel p,p2;
JTextField l1;
JPasswordField l2;
static Container c;
static Connection con;
static ResultSet rs;
static Statement st;
public bankApp()
{
c=getContentPane();
login =new JButton("Login");
cncl =new JButton("Cancel");
bnk=new JLabel("Digi banking");
p=new JPanel();
ImageIcon j=new ImageIcon("C:\\java\\project bank\\ball.png");
pic1=new JLabel(j);
add(p);
p.add(pic1);
p.setBounds(0,0,1640,1070);
p.setLayout(null);
pic1.setBounds(0,0,j.getIconWidth()-400,j.getIconHeight()-400);
bnk.setFont(new Font("ELEPHANT",Font.PLAIN,16));
bnk.setForeground(Color.RED);
wlcm=new JLabel(" WELCOME TO DIGI BANKING PORTAL!");
wlcm.setFont(new Font("ELEPHANT",Font.PLAIN,30));
wlcm.setForeground(Color.RED);
user=new JLabel(" User name");
user.setFont(new Font("Lucida Fax",Font.PLAIN,16));
pass=new JLabel(" Password");
pass.setFont(new Font("Lucida Fax",Font.PLAIN,16));
l1=new JTextField(15);
l2=new JPasswordField(15);
l2.setEchoChar('*');
pic1.add(wlcm);
wlcm.setBounds(60,40,850,50);
pic1.add(user);
user.setBounds(400,250,130,40);
pic1.add(l1);
l1.setBounds(520,250,150,30);
pic1.add(pass);
pass.setBounds(400,280,130,40);
pic1.add(l2);
l2.setBounds(520,285,150,30);
pic1.add(login);
login.setBounds(488,330,80,30);
pic1.add(cncl);
cncl.setBounds(588,330,80,30);
pic1.add(bnk);
bnk.setBounds(50,600,150,50);
setLayout(null);
setVisible(true);
setSize(1650,1080);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
login.addActionListener(this);
cncl.addActionListener(this);
}
public static void main(String arr[]) throws Exception
{
new bankApp();
System.out.println("entering bankApp main block for very first database connection");
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","DATABSE","oracle");
st=con.createStatement();
rs=st.executeQuery("select * from LOGIN");
System.out.println("exiting main block of bankApp connection close");
}
public void actionPerformed(ActionEvent e)
{
String custwelcome;
String a =(String)e.getActionCommand();
if(a.equals("Login"))
{
try{

while(rs.next())
{
System.out.println("entered loop for checking");
System.out.println(rs.getInt(1)+" - "+rs.getString(2)+" - "+rs.getString(4));
String s=rs.getString(2);
String pass=rs.getString(3);
String gender=rs.getString(4);
if(s.equalsIgnoreCase(l1.getText()) && pass.equals(l2.getText()) )
{
if(gender.equals("F"))
{
	System.out.println("the retrieved username is Male");
custwelcome=new String("Welcome Mrs."+rs.getString(2));
}
else
{
	System.out.println("the retrieved username is Female");
custwelcome=new String("Welcome Mr."+rs.getString(2));
}
String tbn=s.toUpperCase().concat("TABLE");
System.out.println("about to make the object for bankApptab2");
bankApptab2 tb2=new bankApptab2(custwelcome,tbn,c);
System.out.println("halt will now return me a panel");
p2=tb2.halt();
System.out.println("halt have given the panel");
c.removeAll();
p.removeAll();
c.add(p2);
p2.setBounds(0,0,1650,1080);
c.setSize(1655,1085);
c.setVisible(true);
log=true;
rs.close();
con.close();
}
}

}
catch(Exception ex)
{
System.out.println(ex);
}
if(log)
{System.out.println("details matched!!");
}
else
{
JOptionPane.showMessageDialog(this,"Wrong details!!");
l1.setText("");
l2.setText("");
}}
if(a.equals("Cancel"))
{
System.exit(0);
}
}
}