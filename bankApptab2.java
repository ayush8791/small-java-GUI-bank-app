import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class bankApptab2 implements ActionListener
{
static JLabel bnk,name,pic;
static JButton credit,debit,acc,trans,help,exit;
static ImageIcon i1;
static JPanel p2,p3;
static String tbn1,cust1;
Container c;
public bankApptab2(String cust,String tbn,Container c1)
{
c=c1;
cust1=cust;
tbn1=tbn;
System.out.println("entered constructor of bankApptab2");
p2=new JPanel();
credit =new JButton("Credit");
debit =new JButton("Debit");
acc =new JButton("Account info");
trans =new JButton("Transactions");
help =new JButton("Help");
exit =new JButton("Signout");
bnk=new JLabel("Digi banking");
bnk.setFont(new Font("ELEPHANT",Font.PLAIN,16));
bnk.setForeground(Color.BLACK);
System.out.println("loading resource for our first panel pic");
i1=new ImageIcon("C:\\java\\project bank\\bank.jpg");
System.out.println("loaded! now adding it to the panel of bankApptab2");
pic=new JLabel(i1);
p2=new JPanel();
p2.setLayout(null);
System.out.println("adding pic to first panel of bankApptab2");
p2.add(pic);
System.out.println("pic added!!");
pic.setBounds(0,0,i1.getIconWidth()-200,i1.getIconHeight()-200);

pic.add(credit);
System.out.println(cust1+" this text is added as label now of bankApptab2");
name=new JLabel(cust1);
name.setBounds(20,20,400,40);
name.setFont(new Font("Lucida Fax",Font.PLAIN,18));
name.setForeground(Color.BLACK);
pic.add(name);
System.out.println("adding buttons to pannel");
credit.setBounds(520,240,130,20);
pic.add(debit);
debit.setBounds(520,265,130,20);
pic.add(acc);
acc.setBounds(520,290,130,20);
pic.add(trans);
trans.setBounds(520,315,130,20);
pic.add(help);
help.setBounds(520,340,130,20);
pic.add(exit);
exit.setBounds(520,365,130,20);
pic.add(bnk);
bnk.setBounds(50,600,150,50);
p2.setBounds(0,0,1650,1080);
System.out.println("buttons added!!");
System.out.println("buttons about to get thier functionality");
credit.addActionListener(this);
debit.addActionListener(this);
acc.addActionListener(this);
trans.addActionListener(this);
exit.addActionListener(this);
help.addActionListener(this);
System.out.println("no errors in constructor of bankApptab2");
}
public JPanel halt()
{
System.out.println("in halt of bankApptab2");
return p2;
}
public void actionPerformed(ActionEvent e)
{
System.out.println("entered actionperformed method of bankApptab2");
String a =(String)e.getActionCommand();
if(a.equals("Credit"))
{
try
{
Credit c1=new Credit(tbn1,cust1,c);
p3=c1.halt();
c.removeAll();
p2.removeAll();
c.add(p3);
p3.setBounds(0,0,1650,1080);
p3.setOpaque(true);
}
catch(Exception dbex)
{
System.out.println("exception found in bankApptab2 while instantiating the credit class object");
}
}
if(a.equals("Debit"))
{
try
{
debit d1=new debit(tbn1,cust1,c);
p3=d1.halt();
c.removeAll();
p2.removeAll();
c.add(p3);
p3.setBounds(0,0,1650,1080);
p3.setOpaque(true);
}
catch(Exception dbex)
{
System.out.println("exception found in bankApptab2 while instantiating the debit class object");
}}
if(a.equals("Transactions"))
{
try
{
Transaction t1=new Transaction(tbn1,cust1,c);
p3=t1.halt();
c.removeAll();
p2.removeAll();
c.add(p3);
p3.setBounds(0,0,1650,1080);
p3.setOpaque(true);
}
catch(Exception dbex)
{
System.out.println("exception found in bankApptab2 while instantiating the transaction class object");
}}
if(a.equals("Help"))
{
try
{
JOptionPane.showMessageDialog(p2,"please visit:www.digibank.com for more info\n\tor\t\n call us at: 0121-999999\n email id: help@digibank.com  ");
}
catch(Exception dbex)
{
System.out.println("help button not working");
}}
if(a.equals("Account info"))
{
try
{
	String customer=cust1.substring(7);
JOptionPane.showMessageDialog(p2,"Sorry for inconvinience "+customer+"\nplease visit:www.digibank.com for more info\n\tor\t\n call us at: 0121-999999\n email id: help@digibank.com  ");
}
catch(Exception dbex)
{
System.out.println("help button not working");
}}
if(a.equals("Signout"))
{
try
{
System.exit(0);
}
catch(Exception dbex)
{
System.out.println("exit button not working");
}
}
}
}


