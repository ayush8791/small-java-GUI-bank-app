import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.text.*;
public class Credit implements ActionListener 
{
JLabel name,pic,bnk;
JButton submit,back;
JPanel p,p2,p3;
JTextField l1,l3,l4,l5;
static Connection con;
static ResultSet rs;
static Statement st;
static PreparedStatement pst;
static String tbn3,cust3;
static int tran,cred,deb,bal;
static ImageIcon i;
Container c;
public Credit(String tbn2,String cust2,Container c1)
{
try
{
System.out.println("entered credit's constructor");
Class.forName("oracle.jdbc.driver.OracleDriver");
System.out.println("setting driver class for credit panel in its constructor");
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","DATABSE","oracle");
System.out.println("now creating statement obj for accessing data in credit's constructor");
st=con.createStatement();
System.out.println("making a resultset obj for gettin rows for credit panel in its constructor");
rs=st.executeQuery("select * from "+tbn2);
System.out.println("rs intialized for credit panel (in its constructor)");
}
catch(Exception unk)
{
System.out.println("exception found while connecting to data base inside credit's constructor");
}
cust3=cust2;
i=new ImageIcon("C:\\java\\project bank\\bank.jpg");
bnk=new JLabel("Digi banking");
bnk.setFont(new Font("ELEPHANT",Font.PLAIN,16));
bnk.setForeground(Color.BLACK);
c=c1;
tbn3=tbn2;
System.out.println(tbn3);
p3=new JPanel();
pic=new JLabel(i);
c.add(p3);
p3.setBounds(0,0,1650,1080);
p3.setLayout(null);
p3.add(pic);
p3.add(bnk);
bnk.setBounds(50,600,150,50);
pic.setBounds(0,0,i.getIconWidth(),i.getIconHeight());
name=new JLabel("Please enter the amount to be credited");
name.setBounds(250,200,600,80);
name.setFont(new Font("Lucida Fax",Font.PLAIN,24));
name.setForeground(Color.BLACK);
back=new JButton("Back");
submit=new JButton("Submit");
submit.setBounds(350,295,80,30);
back.setBounds(435,295,80,30);
pic.add(submit);
pic.add(back);
pic.add(name);
l3=new JTextField();
l3.setBounds(350,260,150,30);
pic.add(l3);
submit.addActionListener(this);
back.addActionListener(this);
System.out.println("exiting credits constructor");
}
public JPanel halt()
{
System.out.println("in halt of credit panel");
return p3;
}
public void actionPerformed(ActionEvent e)
{

System.out.println("entering credit panels actionperformed");
String a =(String)e.getActionCommand();
if(a.equalsIgnoreCase("Submit"))
{
System.out.println("value of tbn is"+tbn3);
System.out.println("submit button clicked with value -"+l3.getText());
try
{
rs=st.executeQuery("select * from "+tbn3+" order by TRANSID");
System.out.println("in outer try of credit panel");
tran=1;
bal=0;
while(rs.next())
{tran=rs.getInt(1)+1;
bal=rs.getInt(4);
System.out.println(rs.getInt(1)+" - "+rs.getString(2));
}

System.out.println("attempting to enter prepared statement of credit panel inside action performed "+tran+" & "+bal);
java.util.Date mydate = new java.util.Date();
Timestamp time=new Timestamp(mydate.getTime());
SimpleDateFormat inst =new SimpleDateFormat("dd-MMM-yyyy KK:mm:ss");
String finaltime=inst.format(time);
try
{
cred =Integer.parseInt(l3.getText());
bal=bal+cred;
deb=0;
}
catch(Exception credex)
{
JOptionPane.showMessageDialog(c,"invalid amount");
l3.setText("");
}

System.out.println("string instantiated");
String sk=new String("INSERT INTO "+tbn3+" VALUES( "+tran+", "+cred+", "+deb+", "+bal+", '"+finaltime+"')");
System.out.println(sk);
st.executeUpdate(sk);
System.out.println("successfully credited");
JOptionPane.showMessageDialog(c,"successfully credited!!\nBalance: Rs."+bal);
l3.setText("");

}
catch(Exception exc)
{
System.out.println("exception in outer catch of credit panel");
JOptionPane.showMessageDialog(c,"something went wrong!!");
System.out.println(exc);
}
}
if(a.equalsIgnoreCase("Back"))
{
try
{
st.close();
System.out.println("statement object closed");
rs.close();
System.out.println("resultset object closed");
con.close();
System.out.println("connection closed");
}
catch(Exception conclose)
{
System.out.println("connection not closed");
}
System.out.println("back button of credit panel is clicked");
p3.removeAll();
c.removeAll();
System.out.println("again accessing bankApptab2");
bankApptab2 tb3=new bankApptab2(cust3,tbn3,c);
p2=tb3.halt();
c.add(p2);
p2.setBounds(0,0,1655,1085);
c.setSize(1660,1090);
c.setVisible(true);
}

}
}