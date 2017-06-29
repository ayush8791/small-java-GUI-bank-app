import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
public class Transaction implements ActionListener
{
JPanel p,p2;
JLabel l,lp,bnk;
JTable t;
static ImageIcon i;
String[] columnnames={"SNO.","CREDIT(RS.)","DEBIT(RS.)","BALANCE(RS.)","TIME"};
JButton back;
String tbn4,cust4;
Container c;
static Connection con;
static ResultSet rs;
static Statement st;
static PreparedStatement pst;
public Transaction(String tbn1,String cust1,Container c)
{
tbn4=tbn1;cust4=cust1;this.c=c;
try
{
System.out.println("entered credit's constructor");
Class.forName("oracle.jdbc.driver.OracleDriver");
System.out.println("setting driver class for credit panel in its constructor");
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","DATABSE","oracle");
System.out.println("now creating statement obj for accessing data in credit's constructor");
st=con.createStatement();
System.out.println("making a resultset obj for gettin rows for credit panel in its constructor");
rs=st.executeQuery("select * from "+tbn1+" order by TRANSID");
System.out.println("rs intialized for credit panel (in its constructor)");
}
catch(Exception unk)
{
System.out.println("exception found while connecting to data base inside transaction's constructor");
}
i=new ImageIcon("C:\\java\\project bank\\tablepic.jpg");
lp=new JLabel(i);
p=new JPanel();
bnk=new JLabel("Digi banking");
bnk.setFont(new Font("ELEPHANT",Font.PLAIN,18));
bnk.setForeground(Color.WHITE);
lp.add(bnk);
bnk.setBounds(50,600,150,60);
lp.setBounds(0,0,i.getIconWidth()-10,i.getIconHeight()-10);
back=new JButton("Back");
lp.add(back);
//p.setLayout(new FlowLayout());
back.setBounds(500,600,80,30);
t=new JTable();
DefaultTableModel model = new DefaultTableModel();
model.setColumnIdentifiers(columnnames);
t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
t.setFillsViewportHeight(true);
JScrollPane scroll = new JScrollPane(t);
scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
try
{
rs=st.executeQuery("select * from "+tbn1+" order by TRANSID");
String sno= "";
String cred= "";
String deb= "";
String bal= "";
String tm="";
int i = 0;
while(rs.next()) 
{
sno=rs.getString(1);
cred=rs.getString(2);
deb=rs.getString(3);
bal=rs.getString(4);
tm=rs.getString(5);
model.addRow(new Object[]{sno,cred,deb,bal,tm});
i++;
}}
catch(Exception exx)
{
	System.out.println("EXception found in transaction table filling column");
}
t.setModel(model);
p.add(lp);
lp.add(scroll);
scroll.setBounds(360,200,800,200);
p.setOpaque(true);
t.setOpaque(false);
scroll.setOpaque(false);
scroll.getViewport().setOpaque(false);
back.addActionListener(this);
c.setSize(1660,1090);
lp.setBounds(0,0,i.getIconWidth(),i.getIconHeight());
}
public JPanel halt()
{
return p;
}
public void actionPerformed(ActionEvent e)
{
String a =(String)e.getActionCommand();
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
System.out.println("back button of transaction panel is clicked");
p.removeAll();
c.removeAll();
System.out.println("again accessing bankApptab2");
bankApptab2 tb3=new bankApptab2(cust4,tbn4,c);
p2=tb3.halt();
c.add(p2);
p2.setBounds(0,0,1655,1085);
c.setSize(1660,1090);
c.setVisible(true);
}

}
}