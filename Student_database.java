/*
                                                                   Md. Rageeb Intisar Polok


  This project is on helping Tutor to store and update necessary informations of students
  of the batches he teaches. This can help teacher to collect attendance, necessary marks of
  different examinations such as various class tests, assignments, date of absences.


  This project is can be applied on education systems those count marks in this way:
                                                                    4 class tests, each contains 20 marks
                                                                    an assignment contains 5 marks
                                                                    attendance percentage contains 5 marks
                                                                    written examination contains 210 marks.
                                                                    
                                                                    total 300 marks and it's percentage.

  But the project is designed such a way that, changing number format is easy.



                    it requires database connection.


       Huge number of variables are used in this project, and so, for easy understanding most variables are named according to their position and the class they belong to.

*/
package project2ii;


import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.math.*;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import java.util.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Student_database extends JFrame{
    
    
    static int hgt,wdt;  // hgt and wdt of entire screen
    static JPanel p1,p2,p3,p4,p5;
    static Connection con = null;
    static Statement st = null;
    static JLabel tlb;     // temporary panel
    static JLabel l3114;
    
    static String id, bat_nam;
    
    static  JPanel h2p1,h2p2,h3p1,h3p2,h1p1,h1p2;
    static JLabel l127;
    
    static JPanel p111341,p11132;
    
    static Border blackline = BorderFactory.createLineBorder(Color.black);  
    
    static JPasswordField pastf;
    static JButton pasb;
    static JLabel pasl,pasl1;
    static JPanel ppan;
    
    static JButton absButtons[];
    
    static JScrollPane jspAbs;
    
    static int ct1,ct2,ct3,ct4;
    
    static JScrollPane jsp11131;
    static Boolean jsp11131Bool = true;
    
    static Boolean Lhandle;
    
    static JPanel absPanel;
    
   // static JFrame app;
    
    static String password;
    
    static Boolean haspass = false;
    
    static Boolean Connected = false;
    
    static ResultSet rs1;
    
    static Student_database app;

      Student_database(){};
    Student_database(String S){
        super(S);
    } 
    
    public static void main (String[] args) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        haspass = false;
        
        hgt=(int)height;
        wdt=(int)width;
        
        
        
        //                                                                              database connection or creating
        try{
            Class.forName("com.mysql.jdbc.Driver");
            try{
                
                

                
                
                
                
                con= DriverManager.getConnection("jdbc:mysql://localhost:3306/polok","root","");
                st = con.createStatement();
                
                
                System.out.println("Database Connected");
                
                
                rs1 = st.executeQuery("SELECT pass FROM pswrd");
                
                if(rs1.next()){
                    password = rs1.getString("pass");
                    haspass = true;                                                          // changed from false                    ******
                    
                }
                 
                
                Connected = true;
                
                app = new Student_database("STUDENT  DATABASE");
                app.setSize(1920,1080);
                app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                
            }
            catch(Exception ex2){
                
                
                
                
                
                
                
             //   System.out.println("No database is available");
                
                con = DriverManager.getConnection("jdbc:mysql://localhost/","root","");
                st = con.createStatement();
                st.executeUpdate("CREATE DATABASE polok");
                st.close();
                con.close();
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/polok","root","");
                st = con.createStatement();
                System.out.println("Database Created");
                st.executeUpdate("CREATE TABLE pswrd (pass VARCHAR(30))");
                st.executeUpdate("CREATE TABLE number (Nmbr INTEGER)");
                System.out.println("Created table number");
              //  String s = "INSERT INTO number VALUES
                st.executeUpdate("INSERT INTO number VALUES(0)");
                System.out.println("Inserted value into number");
                st.executeUpdate("CREATE TABLE InfoTable (BatchName VARCHAR(50) not NULL, BasicId VARCHAR(6) not NULL, StudentNumber INTEGER)");
                System.out.println("Created InfoTable");
                
                Connected = true;
                
                app = new Student_database("STUDENT  DATABASE");
                app.setSize(1920,1080);
                app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              
            }
            
            finally{
                if(Connected){
                    
                    app = new Student_database("STUDENT  DATABASE");
                    app.setSize(1920,1080);
                    app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                
                    System.out.println("setall");
                rs1 = st.executeQuery("SELECT pass FROM pswrd");
                
                if(haspass){
                    
                    ppan = new JPanel();
                    
                    pasl = new JLabel("Enter Password: ");
                    pasl.setFont(new Font("Arial", Font.BOLD, 30));
                    
                    pastf = new JPasswordField();
                    pastf.setFont(new Font("Arial", 4, 30));
                    
                    pasb = new JButton("Enter");
                    pasb.setFont(new Font("Arial", Font.BOLD, 30));
                                pasb.addActionListener(new basepass());
                    
                    pasl1 = new JLabel("");
                    pasl1.setFont(new Font("Arial", 4, 25));
                    
                    
                    ppan.add(pasl);
                    ppan.add(pastf);
                    ppan.add(pasb);
                    ppan.add(pasl1);
                    ppan.setLayout(null);
                    pasl.setBounds(40,50,250,40);
                    pastf.setBounds(310,50,300,40);
                    pasb.setBounds(240,130,150,40);
                    pasl1.setBounds(200,210,350,40);
                    
                    app.add(ppan);
                    app.setLayout(null);
                    ppan.setBounds(600,350,650,280);
                    ppan.setBorder(blackline);
                    
                }
                else{
                    app.setall();
                    
                }
             
                app.setVisible(true);
                
                System.out.println("Approaching to close statement");
            }  
            }
        }
        catch(Exception ex1){
            
            app = new Student_database("STUDENT  DATABASE");
            app.setSize(1920,1080);
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
            System.out.println("Can not connect to class");
            ex1.printStackTrace();
            Connected = false;
            app.warning();
            
        }
        
        
        
        
        
      //  app.setVisible(true);
    }
    
    void warning(){                           //   works if database or class can not be connected. Requires database connectivity or jdbc class and re-opening.
        app.setVisible(true);
        
        JLabel error = new JLabel("Error!   Can not connect to class or database.");
        error.setFont(new Font("Arial", Font.BOLD, 40));
        error.setForeground(Color.red);
        
        
        
        app.add(error);
        app.setLayout(null);
        error.setBounds(500,400,900,80);
    }
    
    
    
   
    
    static class basepass implements ActionListener{
        public void actionPerformed(ActionEvent e){
            char[] bpas = pastf.getPassword();
            String bpass = String.valueOf(bpas);
            if(bpass.equals(password)){
                System.out.println("Passward is correct");
                haspass = true;
                app.remove(ppan);
           app.setall();
            }
            else{
                pasl1.setText("Incorrect Password!");
                System.out.println("Incorrect password. Correct password is: "+bpass);
            }
            
        }
     }
    
    void setall(){
        
                                                                                //  base screen
        
        
            
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        
        p1.setLayout(null);
        p2.setLayout(null);
        p3.setLayout(null);
        
        
        
        
        JButton b1,b2,b3;
        b1 = new JButton("Batches");
        b2 = new JButton("Search ID");
        b3 = new JButton("Setting");
        
        
        b1.addActionListener(new handle1());
        b2.addActionListener(new handle2());
        b3.addActionListener(new handle3());
        
        
        b1.setFont(new Font("Arial", Font.BOLD, 30));
        b2.setFont(new Font("Arial", Font.BOLD, 30));
        b3.setFont(new Font("Arial", Font.BOLD, 30));
        
        
        p1.add(b1);
        b1.setBounds(5,100,180,40);
        
        p1.add(b2);
        b2.setBounds(5,160,180,40);
        
        p1.add(b3);
        b3.setBounds(5,220,180,40);
        
        add(p1);
        add(p4);
        
        
        
        
        setLayout(null);
        p1.setBounds(1,1,192,360);
        p4.setBounds(193,1,(11*1920)/10,1080);
        
        p1.setBorder(blackline);
        p4.setBorder(blackline);
        
        p1.setBackground(Color.BLUE);
        
        
        
      //  tlb = new JLabel("Default");
       // p4.add(tlb);
        
        p4.setLayout(null);
      //  tlb.setBounds(200,200,400,30);
       // p4.setBorder(blackline);
       
       setVisible(true);
        
    }
    
    class handle1 implements ActionListener{                                              //      batches
    
        public void actionPerformed(ActionEvent e){
            if(p4!=null)
                remove(p4);
            
            p4 = new JPanel();
            add(p4);
            p4.setBounds(193,1,(11*1920)/10,1080);
            p4.setBorder(blackline);
            
            
            h1p1 = new JPanel();
            h1p2 = new JPanel();
            
            JButton b11 = new JButton("Select Batch");
                          b11.setFont(new Font("Monako",Font.BOLD,25));
                          b11.addActionListener(new handle11());
            JButton b12 = new JButton("Add Batch");
                          b12.setFont(new Font("Monako",Font.BOLD,25));
                          b12.addActionListener(new handle12());
            JButton b13 = new JButton("Remove Batch");
                          b13.setFont(new Font("Monako",Font.BOLD,25));
                          b13.addActionListener(new handle13());
                          
                                    h1p1.add(b11);
                                    h1p1.add(b12);
                                    h1p1.add(b13);
                                    
                           h1p1.setLayout(null);
                           b11.setBounds(100,50,250,40);
                           b12.setBounds(400,50,250,40);
                           b13.setBounds(700,50,250,40);
            
            p4.add(h1p1);
            p4.add(h1p2);
           
            
            p4.setLayout(null);
            
            h1p1.setBounds(1,1,1715,120);
            h1p2.setBounds(1,121,1715,960);
            h1p1.setBorder(blackline);
            h1p2.setBorder(blackline);
         
         
            
        }
        
        class handle11 implements ActionListener{                                      // batches -> select batches
            
            JPanel h111p;
            JLabel l111p;
            JScrollPane jsp;
            
            public void actionPerformed(ActionEvent e){
                
                try{
                    if(h1p2!=null){
                        p4.remove(h1p2);
                        System.out.println("h1p2 removed");
                    }
                    h1p2 = new JPanel();
                    
                    
                    System.out.println("select number");
                    rs1 = st.executeQuery("SELECT Nmbr FROM number");
                    rs1.next();
                    int n = rs1.getInt("Nmbr");
                    String arr[] = new String[n];
                    int i;
                    System.out.println("select batchname");
                    rs1 = st.executeQuery("SELECT BatchName FROM InfoTable");
                    System.out.println("batchname selected");
                    
                    JButton bt[] = new JButton[n];
                    
                    JPanel p11 = new JPanel();
                    
                    p11.setBorder(blackline);
                    
                    p11.setLayout(new GridLayout(n,1));
                //    p11.setLayout(null);
                  //  p11.setSize(240,n*50);
                    System.out.println("No problem");
                    
                    for(i=0;i<n;i++){
                        rs1.next();
                    //   System.out.println("  No problem "+i);
                        arr[i]=rs1.getString("BatchName");
                        
                    }
                    
                    for(i=0;i<n;i++){
                        System.out.println(arr[i]);
                    }
                    
                    int temp = 20;
                    
                    
                    for(i=0;i<n;i++){
                   //     System.out.println("    No problem "+i);
                        bt[i]=new JButton();
                        bt[i].setText(arr[i]);
                    //    System.out.println("        No problem "+i);
                        bt[i].setFont(new Font("Monako",Font.BOLD,20));
                        bt[i].addActionListener(new handle111());
                        bt[i].setSize(210,40);
                        
                        p11.add(bt[i]);
                        
                        
                        System.out.println("done");
                    }
                    
                    jsp = new JScrollPane(p11);
                 //   jsp.getVerticalScrollBar();
                    
                    jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
                 
                   
                    h1p2.add(jsp);
                    h1p2.setLayout(null);
                    jsp.setBounds(50,50,210,400);
                    
                    p4.add(h1p2);
                    p4.setLayout(null);
                    h1p2.setBounds(1,121,1715,960);
                    h1p2.setBorder(blackline);
                    
                }
                catch(Exception e11){
                    System.out.println("Exception in e11");
                    e11.printStackTrace();
                }
                
            }
            
            class handle111 implements ActionListener{                               // when batch is selected
                String bat_nam,level,term,dept;
                JPanel h1112p;
                JButton b1114;
                JPanel attMain;
                JScrollPane jsp1;
                
                
                public void actionPerformed(ActionEvent e){
                  //  String temp =
                  bat_nam = ((JButton) e.getSource()).getText();
                    System.out.println(bat_nam+" is clicked");
                    
                    int size = bat_nam.length();
                    int p1=0,p2=0;
                    for(int i =0;i<size;i++){
                        if(bat_nam.charAt(i)=='_'){
                            p1 = i;
                            break;
                        }
                    }
                    dept = bat_nam.substring(0,p1);
                    System.out.println("dept is: "+dept);
                    System.out.println("first loop. p1= "+p1);
                    for(int i = p1+1; i<size; i++){
                        if(bat_nam.charAt(i)=='_'){
                            p2 = i;
                            break;
                        }
                    }
                    System.out.println("secont loop. p2= "+p2);
                    
                    level = bat_nam.substring(p1+1,p2);
                     
                    System.out.println("level is: "+level);
                    
                    p1 = p2;
                    for(int i=p1+1;i<size; i++){
                        if(bat_nam.charAt(i)=='_'){
                            p2 = i;
                            break;
                        }
                            
                    }
                    
                    term = bat_nam.substring(p1+1,p2);
                    System.out.println("term is: "+term);
                   
                    
                    if(h111p!=null){
                        h1p2.remove(h111p);
                    }
                    
                     h111p = new JPanel();
                     
                     l111p = new JLabel(bat_nam);
                     l111p.setFont(new Font("Monako",Font.BOLD,30));
                     
                     JButton b1111 = new JButton("Take Attendance");
                     b1111.setFont(new Font("Monako",Font.BOLD,20));
                     b1111.addActionListener(new handle1111());
                     
                     JButton b1112 = new JButton("Add Mark");
                     b1112.setFont(new Font("Monako",Font.BOLD,20));
                     b1112.addActionListener(new handle1112());
                     
                     JButton b1113 = new JButton("Students");
                     b1113.setFont(new Font("Monako",Font.BOLD,20));
                     b1113.addActionListener(new handle1113());
                     
                     
                     b1114 = new JButton("Calculate total marks");
                     b1114.setFont(new Font("Monako",Font.BOLD,20));
                     b1114.addActionListener(new handle1114());
                     
                     
                     h111p.add(l111p);
                     h111p.add(b1111);
                     h111p.add(b1112);
                     h111p.add(b1113);
                     h111p.add(b1114);
                     
                     h111p.setLayout(null);
                     l111p.setBounds(180,40,200,40);
                     b1111.setBounds(150,150,200,40);
                     b1112.setBounds(150,220,200,40);
                     b1113.setBounds(150,290,200,40);
                     b1114.setBounds(100,360,300,40);
                     
                     h1p2.add(h111p);
                   //  p4.setLayout(null);
                     h111p.setBounds(400,100,500,500);
                     h111p.setBorder(blackline);
                    
                }
                
                class handle1111 implements ActionListener{                 //        take attendance
                    
                    JPanel attendance = new JPanel();
                    
                    public void actionPerformed(ActionEvent e){
                        
                        if(h1112p!= null){
                            try{
                                h1p2.remove(h1112p);
                            }
                            catch(Exception e1111){
                                System.out.println("e1111");
                                e1111.printStackTrace();
                            }
                        }
                        
                        attMain = new JPanel();
                        
                        int number=0;
                        
                        try{
                            String toPass = "SELECT StudentNumber FROM InfoTable WHERE BatchName = '"+bat_nam+"'";
                            rs1 = st.executeQuery(toPass);
                            if(rs1.next()){
                                number = rs1.getInt("StudentNumber");
                            }
                            
                         //   attMain.pack();
                            
                            toPass = "SELECT id, name FROM "+bat_nam;
                            rs1 = st.executeQuery(toPass);
                            
                            JLabel lAtt[] = new JLabel[number];
                            JButton bPre[] = new JButton[number];
                            JButton bAbs[] = new JButton[number];
                            int i = 0;
                            
                            for(i=0;i<number;i++){
                                rs1.next();
                                String stuName = rs1.getString("name");
                                String stuId = rs1.getString("id");
                                String forLab = "<html>"+stuId+"<br>"+stuName+"<html>";
                                String forPreB = "<html>"+stuId+"<br> present <html>";
                                String forAbsB = "<html>"+stuId+"<br> absent <html>";
                                
                                lAtt[i] = new JLabel(forLab,SwingConstants.CENTER);
                                lAtt[i].setFont(new Font("Monako",Font.PLAIN,20));
                                
                                bPre[i] = new JButton(forPreB);
                                bPre[i].setFont(new Font("Monako",Font.PLAIN,20));
                                bPre[i].setBackground(Color.green);
                                bPre[i].addActionListener(new handlePre());
                                
                                bAbs[i] = new JButton(forAbsB);
                                bAbs[i].setFont(new Font("Monako",Font.PLAIN,20));
                                bAbs[i].addActionListener(new handleAbs());
                                bAbs[i].setBackground(Color.red);
                                bAbs[i].setForeground(Color.white);
                                
                                attMain.add(lAtt[i]);
                                attMain.add(bPre[i]);
                                attMain.add(bAbs[i]);
                                
                            }
                            
                        }
                        catch(Exception ex1111){
                            System.out.println("error ex1111");
                            ex1111.printStackTrace();
                        }
                        
                        attMain.setLayout(new GridLayout(number,3));
                        
                        
                        System.out.println("check");
                        
                        
                        jsp1 = new JScrollPane(attMain);
                        
                        
                        
                    
                        jsp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
                        
                        
                        
                        
                        
                        h1p2.add(jsp1);
                        jsp1.setBounds(1000,100,500,640);
                        jsp1.setBorder(blackline);
                        System.out.println("done");
                       // h1p2.pack();
                       
                       h1p2.repaint();
                       
                    }    
                    
         
                    
                    class handlePre implements ActionListener{                              //  when student is present
                        
                        public void actionPerformed(ActionEvent e){
                            String getInfo = ((JButton) e.getSource()).getText();
                            int size = getInfo.length();
                            String id;
                            id = getInfo.substring(6,15);
                            System.out.println(id);
                            
                            String toPass = "SELECT presents FROM "+bat_nam+" WHERE id ="+id;
                            try{
                                rs1 = st.executeQuery(toPass);
                                int preNum =0;
                                if(rs1.next()){
                                    preNum = rs1.getInt("presents");
                                    preNum++;
                                    System.out.println("PreNum = "+preNum);
                                    toPass = "UPDATE "+bat_nam+" SET presents = "+preNum+" WHERE id IN ('"+id+"')";
                                    System.out.println(toPass);
                                    st.executeUpdate(toPass);
                                    toPass = "SELECT presents FROM "+bat_nam+" WHERE id ="+id;
                                    rs1 = st.executeQuery(toPass);
                                    rs1.next();
                                    preNum = rs1.getInt("presents");
                                    System.out.println("Present is: "+preNum);
                                }
                            }
                            catch(Exception exHandlePre){
                                System.out.println("error exHandlePre");
                                exHandlePre.printStackTrace();
                            }
                            
                        }
                    }
                    
                    class handleAbs implements ActionListener{                              // when student is absent
                        
                        public void actionPerformed(ActionEvent e){
                            String getInfo = ((JButton) e.getSource()).getText();
                            int size = getInfo.length();
                            String id;
                            id = getInfo.substring(6,15);
                            System.out.println(id);
                            
                            String toPass = "SELECT absents FROM "+bat_nam+" WHERE id ="+id;
                            try{
                                rs1 = st.executeQuery(toPass);
                                int absNum =0;
                                if(rs1.next()){
                                    absNum = rs1.getInt("absents");
                                    absNum++;
                                    System.out.println("AbsNum = "+absNum);
                                    toPass = "UPDATE "+bat_nam+" SET absents = "+absNum+" WHERE id IN ('"+id+"')";
                                    System.out.println(toPass);
                                    st.executeUpdate(toPass);
                                    toPass = "SELECT absents FROM "+bat_nam+" WHERE id ="+id;
                                    rs1 = st.executeQuery(toPass);
                                    rs1.next();
                                    absNum = rs1.getInt("absents");
                                    System.out.println("Absent is: "+absNum);
                                    
                                    DateFormat df = new SimpleDateFormat("dd/MM/yy");
                                    Calendar calobj = Calendar.getInstance();

                                    String date = df.format(calobj.getTime());
                                    System.out.println(date);
                                    
                                    int i;
                                
                                    System.out.println(date);
                                    
                                    toPass = "SELECT * FROM "+bat_nam+" WHERE id ="+id;
                                    rs1 = st.executeQuery(toPass);
                                    rs1.next();
                                    
                                    for(i=1;i<31;i++){
                                        String select ="absentDate"+i;
                                        String tempDate = rs1.getString(select);
                                        if(tempDate==null){
                                            
                                            System.out.println(select);
                                            toPass = "UPDATE "+bat_nam+" SET "+select+" = '"+date+"' WHERE id IN ('"+id+"')";
                                            st.executeUpdate(toPass);
                                            System.out.println("date uploaded");
                                            toPass = "SELECT "+select+" FROM "+bat_nam+" WHERE id ="+id;
                                            rs1= st.executeQuery(toPass);
                                            rs1.next();
                                            String temporary = rs1.getString(select);
                                            System.out.println(temporary);
                                            break;
                                        }
                                    }
                                }
                            }
                            catch(Exception exHandlePre){
                                System.out.println("error exHandlePre");
                                exHandlePre.printStackTrace();
                            }
                            
                        }
                    }
                    
                }
                
                class handle1112 implements ActionListener{                             //    add mark
                
                    public void actionPerformed(ActionEvent e){
                        
                        if(h1112p!=null){
                            h1p2.remove(h1112p);
                        }
                        
                        if(jsp1!=null){
                            try{
                                h1p2.remove(jsp1);
                                
                            }
                            catch(Exception e1112){
                                System.out.println("Error e1112");
                                e1112.printStackTrace();
                            }
                            
                        }
                        
                        h1112p = new JPanel();
                        
                        JLabel l11121 = new JLabel("Update Mark of: ");
                        l11121.setFont(new Font("Arial", Font.BOLD, 20));
                        
                        JButton b11121 = new JButton("CT1");
                        b11121.setFont(new Font("Arial", Font.BOLD, 20));
                        b11121.addActionListener(new handle11121());
                        JButton b11122 = new JButton("CT2");
                        b11122.setFont(new Font("Arial", Font.BOLD, 20));
                        b11122.addActionListener(new handle11121());
                        JButton b11123 = new JButton("CT3");
                        b11123.setFont(new Font("Arial", Font.BOLD, 20));
                        b11123.addActionListener(new handle11121());
                        JButton b11124 = new JButton("CT4");
                        b11124.setFont(new Font("Arial", Font.BOLD, 20));
                        b11124.addActionListener(new handle11121());
                        JButton b11125 = new JButton("Assignment");
                        b11125.setFont(new Font("Arial", Font.BOLD, 20));
                        b11125.addActionListener(new handle11121());
                        JButton b11126 = new JButton("Attendance Percentage");
                        b11126.setFont(new Font("Arial", Font.BOLD, 20));
                        b11126.addActionListener(new handle11121());
                        JButton b11127 = new JButton("Exam Mark");
                        b11127.setFont(new Font("Arial", Font.BOLD, 20));
                        b11127.addActionListener(new handle11121());
                        
                        
                        h1112p.add(l11121);
                        h1112p.add(b11121);
                        h1112p.add(b11122);
                        h1112p.add(b11123);
                        h1112p.add(b11124);
                        h1112p.add(b11125);
                        h1112p.add(b11126);
                        h1112p.add(b11127);
                        
                        h1112p.setLayout(null);
                        
                        l11121.setBounds(150,10,300,40);
                        b11121.setBounds(100,80,300,40);
                        b11122.setBounds(100,140,300,40);
                        b11123.setBounds(100,200,300,40);
                        b11124.setBounds(100,260,300,40);
                        b11125.setBounds(100,320,300,40);
                        b11126.setBounds(100,380,300,40);
                        b11127.setBounds(100,440,300,40);
                        
                        
                        
                        h1p2.add(h1112p);
                        
                        
                        
                        h1112p.setBounds(1100,110,500,500);
                        h1112p.setBorder(blackline);
                        
                        
                        h1p2.repaint();
                        
                    }
                    
                    class handle11121 implements ActionListener{                                    //         ct1
                        
                        JLabel lError;
                        
                        int number;
                        JTextField[] markTextField;
                        Boolean dble;
                        String record;
                        
                        public void actionPerformed(ActionEvent e){
                            dble = false;
                            String pressed = ((JButton) e.getSource()).getText();
                            record = "";
                            
                            if(pressed.equals("CT1")){
                                System.out.println("It is CT1");
                                record = "CT1";
                            }
                            else if(pressed.equals("CT2")){
                                System.out.println("It is CT2");
                                record = "CT2";
                            }
                            else if(pressed.equals("CT3")){
                                System.out.println("It is CT3");
                                record = "CT3";
                            }
                            else if(pressed.equals("CT4")){
                                System.out.println("It is CT4");
                                record = "CT4";
                            }
                            else if(pressed.equals("Assignment")){
                                System.out.println("It is Assignment");
                                record = "asnmnt";
                            }
                            else if(pressed.equals("Attendance Percentage")){
                                System.out.println("It is percentage");
                                record = "percentage";
                                dble = true;
                            }
                            else if(pressed.equals("Exam Mark")){
                                System.out.println("It is exam");
                                record = "examMark";
                                dble = true;
                            }
                            
                            try{
                                
                                if(h1p2!=null){
                                    p4.remove(h1p2);
                                }
                                h1p2 = new JPanel();
                                
                                
                                String toPass = "SELECT StudentNumber FROM InfoTable WHERE BatchName = ('"+bat_nam+"')";
                                rs1 = st.executeQuery(toPass);
                                rs1.next();
                                number = rs1.getInt("StudentNumber");
                                
                                int i;
                                
                                String[] id = new String[number];
                                String[] name = new String[number];
                                
                                int[] mrkI = new int[number];
                                double[] mrkD = new double[number];
                                
                                if(dble)
                                    mrkI = null;
                                else
                                    mrkD = null;
                                
                                toPass = "SELECT id, name, "+record+" FROM "+bat_nam;
                                rs1 = st.executeQuery(toPass);
                                System.out.println(toPass);
                                
                                for(i=0;i<number;i++){
                                    rs1.next();
                                    id[i] = rs1.getString("id");
                                    name[i] = rs1.getString("name");
                                    if(dble)
                                        mrkD[i] = rs1.getDouble(record);
                                    else
                                        mrkI[i] = rs1.getInt(record);
                                }
                                
                                JPanel markPanel = new JPanel();
                                markPanel.setLayout(new GridLayout(number,3));
                                
                                
                                
                                JLabel[] markL1 = new JLabel[number];
                                JLabel[] markL2 = new JLabel[number];
                                markTextField = new JTextField[number];
                                
                                for(i=0;i<number;i++){
                                    
                                    String variable = "<html>"+id[i]+"<br>"+name[i]+"<html>";
                                    markL1[i] = new JLabel(variable, SwingConstants.CENTER);
                                    markL1[i].setFont(new Font("Arial", Font.PLAIN, 20));
                                    markL1[i].setBorder(blackline);
                                    
                                    if(dble){
                                        variable = "From Mark ( "+mrkD[i]+" ), update to: ";
                                        System.out.println(mrkD[i]);
                                    }
                                    else{
                                        variable = "From Mark ( "+mrkI[i]+" ), update to: ";
                                        System.out.println(mrkI[i]);
                                    }
                                    markL2[i] = new JLabel(variable, SwingConstants.CENTER);
                                    markL2[i].setFont(new Font("Arial", Font.PLAIN, 20));
                                    markL2[i].setBorder(blackline);
                                    
                                    markTextField[i] = new JTextField();
                                    markTextField[i].setFont(new Font("Arial", Font.PLAIN, 20));
                                    
                                    markPanel.add(markL1[i]);
                                    markPanel.add(markL2[i]);
                                    markPanel.add(markTextField[i]);
                                    
                                }
                                
                                JButton update = new JButton("Update");
                                update.setFont(new Font("Arial", Font.PLAIN, 20));
                                update.addActionListener(new handle111211());
                                
                                h1p2.add(update);
                                
                                JScrollPane jsp = new JScrollPane(markPanel);
                                //   jsp.getVerticalScrollBar();
                    
                                jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
                                
                                //jsp.setLayout(null);
                                
                                
                                h1p2.add(jsp);
                                
                                lError= new JLabel();
                                lError.setFont(new Font("Arial", Font.PLAIN, 27));
                                
                                h1p2.add(lError);
                                
                                
                                
                                h1p2.setLayout(null);
                                jsp.setBounds(200,100,1000,600);
                                update.setBounds(900,750,100,50);
                                lError.setBounds(1350,400,200,40);
                                
                                
                                p4.add(h1p2);
                                h1p2.setBounds(1,121,1715,960);
                                h1p2.setBorder(blackline);
                            }
                            catch(Exception e111211){
                                System.out.println("error e111211");
                                e111211.printStackTrace();
                            }
                            
                        }
                        
                        class handle111211 implements ActionListener{                     //       update
                            
                            public void actionPerformed(ActionEvent e){
                                
                                if(lError!=null){
                                    h1p2.remove(lError);
                                }
                                
                                lError = new JLabel();
                                
                                lError.setFont(new Font("Arial", Font.PLAIN, 27));
                                
                                int errors = 0;
                                
                                int i;
                                int[] markI = new int[number];
                                double[] markD = new double[number];
                                if(dble){
                                    //                  error asbe. try catch use korte hbe.
                                    for(i=0;i<number;i++){
                                        try{
                                            markD[i] = Double.parseDouble(markTextField[i].getText());
                                        }
                                        catch(Exception e1112111){
                                            
                                            String tempS = markTextField[i].getText();
                                            int sSize = tempS.length();
                                            Boolean chk = true;
                                            int j =0;
                                            
                                            for(j=0;j<sSize;j++){
                                                if(tempS.charAt(j) != ' '){
                                                    
                                                    chk = false;
                                                    break;
                                                }
                                            }
                                            
                                            System.out.println("Error e1112111");
                                          //  e1112111.printStackTrace();
                                           // markD[i] = 0;
                                            if(sSize==0 || chk == true){
                                                markD[i] = -1;
                                            }
                                            else{
                                                markD[i] = -2;
                                                
                                                errors++;
                                                
                                                markTextField[i].setText("Error");
                                            }
                                        }
                                        
                                    }
                                    markI = null;
                                }
                                else{
                                    
                                    for(i=0;i<number;i++){
                                        try{
                                            markI[i] = Integer.parseInt(markTextField[i].getText());
                                        }
                                        catch(Exception e1112111){
                                            String tempS = markTextField[i].getText();
                                            int sSize = tempS.length();
                                            Boolean chk = true;
                                            int j =0;
                                            
                                            for(j=0;j<sSize;j++){
                                                if(tempS.charAt(j) != ' '){
                                                    
                                                    chk = false;
                                                    break;
                                                }
                                            }
                                            
                                            System.out.println("Error e1112111");
                                          //  e1112111.printStackTrace();
                                           // markD[i] = 0;
                                            if(sSize==0 || chk == true){
                                                markI[i] = -1;
                                            }
                                            else{
                                                markI[i] = -2;
                                                
                                                errors++;
                                                
                                                markTextField[i].setForeground(Color.red);
                                                markTextField[i].setText("     < E R R O R >      ");
                                            }
                                        }
                                
                                    }    
                                    
                                    
                                    
                                    markD = null;
                                }
                                
                                if(dble){
                                    for(i=0;i<number;i++){
                                        System.out.println(markD[i]);
                                    }
                                }
                                else{
                                    for(i=0;i<number;i++){
                                        System.out.println(markI[i]);
                                    }
                                }
                                
                                String err = errors+" Error(s)";
                                
                                lError.setText(err);
                                
                                h1p2.add(lError);
                                lError.setBounds(1350,400,200,40);
                                
                                
                                //  updating database;
                               
                                String[] IDs = new String[number];
                                
                                try{
                                    String toPass = "SELECT id FROM "+bat_nam;
                                    rs1 = st.executeQuery(toPass);
                                    for(i=0;i<number;i++){
                                        rs1.next();
                                        IDs[i] = rs1.getString("id");
                                    }
                                    
                                    if(dble){
                                        for(i=0;i<number;i++){
                                            if(markD[i]>0){
                                                toPass = "UPDATE "+bat_nam+" SET "+record+" = "+markD[i]+" WHERE id in ('"+IDs[i]+"')";
                                                st.executeUpdate(toPass);
                                            }
                                            
                                        }
                                    }
                                    else{
                                        for(i=0;i<number;i++){
                                            if(markI[i]>0){
                                                toPass = "UPDATE "+bat_nam+" SET "+record+" = "+markI[i]+" WHERE id in ('"+IDs[i]+"')";
                                                st.executeUpdate(toPass);
                                            }
                                            
                                        }
                                    }
                                    
                                }
                                catch(Exception e1112112){
                                    System.out.println("Error e1112112");
                                    e1112112.printStackTrace();
                                }
                                
                                
                                
                            }
                                
                                
                        }
                    }
                }
                
                class handle1113 implements ActionListener{                                                    //       students
                    
              //      JPanel p111341,p11132;
                    
                    
                    
                    public void actionPerformed(ActionEvent e){
                        
                        jsp11131Bool = true;
                        
                        p4.remove(h1p2);
                        h1p2 = new JPanel();
                        
                        try{
                         //   rs1 = st.executeQuery("Select ")
                            int stu;
                            System.out.println(bat_nam+" : is temp");
                            String temp1113 ="SELECT * FROM InfoTable WHERE BatchName = '"+bat_nam+"'";
                            rs1 = st.executeQuery(temp1113);
                            rs1.next();
                            System.out.println("No problem");
                            stu = rs1.getInt("StudentNumber");
                            System.out.println(stu+" is number");
                            
                            temp1113 = "SELECT id,name FROM "+bat_nam;
                            
                            rs1 = st.executeQuery(temp1113);
                            System.out.println("temp is passed");
                           
                           JPanel p11131 = new JPanel();
                           
                           p11131.setLayout(new GridLayout(stu+1,1,0,5));
                           
                           JPanel p11133[] = new JPanel[stu];
                           JButton b11133[] = new JButton[stu];
                           
                           for(int i=0;i<stu;i++){
                           
                               rs1.next();
                               String name = rs1.getString("name");
                               String id = rs1.getString("id");
                               
                               JLabel L = new JLabel(id,SwingConstants.CENTER);
                               L.setFont(new Font("Monako",4,20));
                               JLabel L2 = new JLabel(name,SwingConstants.CENTER);
                               L2.setFont(new Font("Monako",4,20));
                               String ins = "<html>"+id+"<br>"+name+"<html>";
                               b11133[i] = new JButton(ins);
                               b11133[i].setFont(new Font("Monako",4,20));
                               b11133[i].setHorizontalTextPosition(JButton.LEFT);
                               
                           //    b11133[i].setHorizontalTextPosition.EAST;
                               b11133[i].addActionListener(new handle11131());
                               
                           
                               
                               p11131.add(b11133[i]);
                               
                               
                           }
                           
                           
                           JPanel p11134 = new JPanel();
                           JButton b11134 = new JButton("Add Student");
                           b11134.setFont(new Font("Monako",4,25));
                           b11134.addActionListener(new handle11134());
                           
                           p11134.add(b11134);
                           p11134.setLayout(null);
                           b11134.setBounds(1,1,208,40);
                           
                           p11131.add(p11134);
                           
                           jsp11131 = new JScrollPane(p11131);
                           jsp11131.setPreferredSize(new Dimension(200,240));
                 //   jsp.getVerticalScrollBar();
                    
                           jsp11131.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
                 
                   
                            h1p2.add(jsp11131);
                            h1p2.setLayout(null);
                            jsp11131.setBounds(50,50,210,400);
                    
                            p4.add(h1p2);
                            p4.setLayout(null);
                            h1p2.setBounds(1,121,1715,960);
                            h1p2.setBorder(blackline);
                            
                            app.setVisible(true);
                           
                            
                        }
                        catch(Exception e1113){
                            System.out.println("error e1113");
                            e1113.printStackTrace();
                        }
                        
                    }
                    
                    class handle11131 implements ActionListener{                    //    selected an id
                        String id,name;
                        
                        JLabel CT1,CT2,CT3,CT4,Assignment,Exam_mark,PRESENTS, ATT_PERCENT, TOTAL_MARK, TOTAL_MARK_HUNDRED;
                        JButton Editor;
                        int ct1,ct2,ct3,ct4,asnmnt,absents,presents;
                        double exm_mrk,att_percent,total_mark,total_mark_hundred;
                        
                        
                        
                        
                        public void actionPerformed(ActionEvent e){
                            String id_name = ((JButton) e.getSource()).getText();
                            System.out.println(id_name);
                            int size = id_name.length();
                      
                            
                            id = id_name.substring(6,15);
                            name = id_name.substring(19,size-6);
                            System.out.println(id);
                            System.out.println(name);
                            
                            if(p11132!=null){
                                h1p2.remove(p11132);
                            }
                            
                            p11132 = new JPanel();
                            String toPass = "SELECT * FROM "+bat_nam+" WHERE id = "+id;
                            try{
                                rs1 = st.executeQuery(toPass);
                                rs1.next();
                                String batch = rs1.getString("Level");
                                String term = rs1.getString("Term");
                                String dept = rs1.getString("dept");
                                ct1 = rs1.getInt("CT1");
                                ct2 = rs1.getInt("CT2");
                                ct3 = rs1.getInt("CT3");
                                ct4 = rs1.getInt("CT4");
                                asnmnt = rs1.getInt("asnmnt");
                                absents = rs1.getInt("absents");
                                presents = rs1.getInt("Presents");
                                exm_mrk = rs1.getDouble("examMark");
                                total_mark = rs1.getDouble("TotalMark");
                                total_mark_hundred = (total_mark/3.0);
                                
                                att_percent = (((double)(presents*100)/(presents+absents)));
                                
                                
                                System.out.println(batch);
                                System.out.println(term);
                                System.out.println(dept);
                                System.out.println(ct1);
                                System.out.println(ct2);
                                System.out.println(ct3);
                                System.out.println(ct4);
                                System.out.println(asnmnt);
                                System.out.println(absents);
                               
                                
                                
                                
                                
                             //   String param = "Name            : "+name;
                                
                                JLabel lName = new JLabel("Name : ");
                                lName.setFont(new Font("Arial",Font.BOLD,25));
                                JLabel lName2 = new JLabel(name);
                                lName2.setFont(new Font("Arial",Font.PLAIN,25));
                                
                            //    param = "Id             : "+id;
                                JLabel lId = new JLabel("ID : ");
                                lId.setFont(new Font("Arial",Font.BOLD,25));
                                JLabel lId2 = new JLabel(id);
                                lId2.setFont(new Font("Arial",Font.PLAIN,25));
                                
                            //    param = "Level          : "+level;
                                JLabel lLevel = new JLabel("Level : ");
                                lLevel.setFont(new Font("Arial",Font.BOLD,25));
                                JLabel lLevel2 = new JLabel(level);
                                lLevel2.setFont(new Font("Arial",Font.PLAIN,25));
                                
                        //        param ="Term            : "+term;
                                JLabel lTerm = new JLabel("Term : ");
                                lTerm.setFont(new Font("Arial",Font.BOLD,25));
                                JLabel lTerm2 = new JLabel(term);
                                lTerm2.setFont(new Font("Arial",Font.PLAIN,25));
                                
                        //        param ="Department      : "+dept;
                                JLabel lDept = new JLabel("Department : ");
                                lDept.setFont(new Font("Arial",Font.BOLD,25));
                                JLabel lDept2 = new JLabel(dept);
                                lDept2.setFont(new Font("Arial",Font.PLAIN,25));
                                
                                JLabel lAbsents = new JLabel("Absents: ");
                                lAbsents.setFont(new Font("Arial",Font.BOLD,25));
                                
                           //     JLabel CT1,CT2,CT3,CT4,Assignment,Exam_mark;
                           //     JButton CTb1,CTb2,CTb3,CTb4,Assignmentb,Exam_markb;
                           
                           
                                String exm_mrkS, att_percentS, total_markS, total_mark_hundredS;
                                
                                NumberFormat formatter = new DecimalFormat("###.00");
                                
                                
                                exm_mrkS = (formatter.format(exm_mrk));
                                        
                                att_percentS = (formatter.format(att_percent));
                                        
                                total_markS = (formatter.format(total_mark));
                                        
                                total_mark_hundredS = (formatter.format(total_mark_hundred));
                                
                                
                                
                                toPass = "CT 1 : "+ct1;
                                CT1 = new JLabel(toPass);
                                CT1.setFont(new Font("Arial", Font.BOLD,20));
                                
                                toPass = "CT 2 : "+ct2;
                                CT2 = new JLabel(toPass);
                                CT2.setFont(new Font("Arial", Font.BOLD,20));
                                
                                toPass = "CT 3 : "+ct3;
                                CT3 = new JLabel(toPass);
                                CT3.setFont(new Font("Arial", Font.BOLD,20));
                                
                                toPass = "CT 4 : "+ct4;
                                CT4 = new JLabel(toPass);
                                CT4.setFont(new Font("Arial", Font.BOLD,20));
                                
                                toPass = "Assignment : "+asnmnt;
                                Assignment = new JLabel(toPass);
                                Assignment.setFont(new Font("Arial", Font.BOLD,20));
                                
                                toPass = "Exam Mark : "+exm_mrkS;
                                Exam_mark = new JLabel(toPass);
                                Exam_mark.setFont(new Font("Arial", Font.BOLD,20));
                                
                                toPass = "Presents : "+presents;
                                PRESENTS = new JLabel(toPass);
                                PRESENTS.setFont(new Font("Arial", Font.BOLD,20));
                                
                                toPass = "Attendance Percentage : "+att_percentS;
                                ATT_PERCENT = new JLabel(toPass);
                                ATT_PERCENT.setFont(new Font("Arial", Font.BOLD,20));
                                
                                toPass = "Total Mark : "+total_markS;
                                TOTAL_MARK = new JLabel(toPass);
                                TOTAL_MARK.setFont(new Font("Arial", Font.BOLD,20));
                                
                                toPass = "Total Mark in 100 : "+total_mark_hundredS;
                                TOTAL_MARK_HUNDRED = new JLabel(toPass);
                                TOTAL_MARK_HUNDRED.setFont(new Font("Arial", Font.BOLD,20));
                                TOTAL_MARK_HUNDRED.setForeground(Color.BLUE);
                                
                                
                                toPass = "Edit Marks ";
                                Editor = new JButton(toPass);
                                Editor.setFont(new Font("Arial", Font.BOLD, 20));
                                Editor.addActionListener(new handle111311());
                                
                                
                                
                                
                                absPanel = new JPanel();
                                
                                absPanel.setLayout(new GridLayout(absents,1));
                                
                                absButtons = new JButton[absents];
                                int i,j=0;
                                for(i=1;i<absents+1;i++){
                                    String colm = "absentDate"+i;
                                    
                                    System.out.println(colm);
                                    
                                    String dateOfAbs = rs1.getString(colm);
                                    System.out.println(dateOfAbs);
                                    if(dateOfAbs == null){
                                        break;
                                    }
                                    else{
                                        String param = "<html>"+colm+"<br>"+dateOfAbs+"<html>";
                                        System.out.println(param);
                                        absButtons[i-1]=new JButton(param);
                                        absButtons[i-1].setFont(new Font("Arial",Font.PLAIN,25));
                                        absButtons[i-1].addActionListener(new handleAbsList());
                                        System.out.println("Before adding");
                                        absPanel.add(absButtons[i-1]);
                                        System.out.println("Added");
                                    }
                                    
                                    
                                }
                                
                                
                                
                                
                                jspAbs = new JScrollPane(absPanel);
                    
                                jspAbs.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
                                
                                String temp = "<html>Remove<br>"+id+"<html>";
                                JButton removeId = new JButton(temp);
                                removeId.setFont(new Font("Arial", Font.BOLD, 25));
                                removeId.setBackground(Color.red);
                                removeId.setForeground(Color.white);
                                removeId.addActionListener(new IdRemover());
                                
                                
                                
                                
                                
                                p11132.add(lName);
                                p11132.add(lId);
                                p11132.add(lLevel);
                                p11132.add(lTerm);
                                p11132.add(lDept);
                                p11132.add(lAbsents);
                                p11132.add(jspAbs);
                                p11132.add(removeId);
                                
                                p11132.add(lName2);
                                p11132.add(lId2);
                                p11132.add(lLevel2);
                                p11132.add(lTerm2);
                                p11132.add(lDept2);
                                
                                p11132.add(CT1);
                                p11132.add(CT2);
                                p11132.add(CT3);
                                p11132.add(CT4);
                                p11132.add(Assignment);
                                p11132.add(Exam_mark);
                                
                                p11132.add(PRESENTS);
                                p11132.add(ATT_PERCENT);
                                p11132.add(TOTAL_MARK);
                                p11132.add(TOTAL_MARK_HUNDRED);
                                
                                p11132.add(Editor);
                                
                                
                                p11132.setLayout(null);
                                
                                lName.setBounds(100,50,200,40);
                                lId.setBounds(100,100,200,40);
                                lLevel.setBounds(100,150,200,40);
                                lTerm.setBounds(100,200,200,40);
                                lDept.setBounds(100,250,200,40);
                                
                                lName2.setBounds(300,50,300,40);
                                lId2.setBounds(300,100,300,40);
                                lLevel2.setBounds(300,150,300,40);
                                lTerm2.setBounds(300,200,300,40);
                                lDept2.setBounds(300,250,300,40);
                                lAbsents.setBounds(700,70,200,40);
                                jspAbs.setBounds(700,130,200,200);
                                removeId.setBounds(1000,190,200,100);
                                
                                CT1.setBounds(100,410,200,40);
                                CT2.setBounds(100,460,200,40);
                                CT3.setBounds(100,510,200,40);
                                CT4.setBounds(100,560,200,40);
                                Assignment.setBounds(100,610,200,40);
                                Exam_mark.setBounds(100,660,200,40);
                                
                                PRESENTS.setBounds(301,410,200,40);
                                ATT_PERCENT.setBounds(301,460,350,40);
                                
                                TOTAL_MARK.setBounds(301,510,350,40);
                                TOTAL_MARK_HUNDRED.setBounds(301,560,350,40);
                                
                                Editor.setBounds(700,600,200,40);
                                
                                
                                p11132.setBorder(blackline);
                                h1p2.add(p11132);
                               // h1p2.add(jspAbs);
                                p11132.setBounds(300,10,1425,950);
                              //  jspAbs.setBounds(1320,100,200,200);
                                
                                
                                
                                app.setVisible(true);
                                
                                
                            }
                            catch(Exception e11131){
                                System.out.println("error e11131");
                                e11131.printStackTrace();
                            }
                            
                            
                        }
                        
                        class IdRemover implements ActionListener{                                  //  removing an id from database
                            
                            JPasswordField pb;
                            JLabel inPass;
                            JButton remove;
                            JLabel clicked;
                            
                            
                            public void actionPerformed(ActionEvent e){
                                
                                if(haspass){
                                    if(p11132!=null){
                                        h1p2.remove(p11132);
                                    }
                                    p11132 = new JPanel();
                                    
                                    JLabel lpass = new JLabel("Enter Password: ");
                                    lpass.setFont(new Font("Arial", Font.PLAIN, 25));
                                    
                                    pb = new JPasswordField();
                                    pb.setFont(new Font("Arial", Font.PLAIN, 25));
                                    
                                    inPass = new JLabel("Type Password");
                                    inPass.setFont(new Font("Arial", Font.PLAIN, 25));
                                    
                                    JButton enter = new JButton("Enter");
                                    enter.setFont(new Font("Arial", Font.PLAIN, 25));
                                    enter.addActionListener(new handleIdRemover());
                                    
                                    
                                    
                                    p11132.add(lpass);
                                    p11132.add(pb);
                                    p11132.add(inPass);
                                    p11132.add(enter);
                                    
                                    p11132.setLayout(null);
                                    lpass.setBounds(200,200,200,40);
                                    pb.setBounds(400,200,200,40);
                                    enter.setBounds(370,250,100,40);
                                    inPass.setBounds(300,320,250,40);
                                    
                                    h1p2.add(p11132);
                                    p11132.setBounds(300,10,1425,900);
                                    
                                    p11132.setBorder(blackline);
                                }
                                
                                else{
                                    methodRemoveId();
                                }
                                
                                app.setVisible(true);
                                
                            }
                            
                            class handleIdRemover implements ActionListener{                     //     precess for removing id
                                
                                
                                public void actionPerformed(ActionEvent e){
                                    
                                    char[] temp = pb.getPassword();
                                    String pass = String.valueOf(temp);
                                    
                                    
                                    p11132.remove(inPass);
                                        
                                    if(pass.equals(password)){
                                        inPass = new JLabel("Correct Password");
                                        inPass.setFont(new Font("Arial", Font.PLAIN, 25));
                                        p11132.add(inPass);
                                        inPass.setBounds(300,320,250,40);
                                        
                                        methodRemoveId();
                                        
                                    }
                                    else{
                                        inPass = new JLabel("Incorrect Password");
                                        inPass.setFont(new Font("Arial", Font.PLAIN, 25));
                                        p11132.add(inPass);
                                        inPass.setBounds(300,320,250,40);
                                    }
                                    
                                }
                                
                            }
                            
                            void methodRemoveId(){                                       //   process for removing id
                                
                                if(p11132!=null){
                                    h1p2.remove(p11132);
                                }
                                p11132 = new JPanel();
                                
                                String toPass ="Sure to remove "+id+" ?";
                                JLabel sure = new JLabel(toPass);
                                sure.setFont(new Font("Arial", Font.BOLD, 25));
                                remove = new JButton("Remove");
                                remove.setFont(new Font("Arial", Font.BOLD, 25));
                                remove.addActionListener(new yesRemoveID());
                                clicked = new JLabel("");
                                clicked.setFont(new Font("Arial", Font.PLAIN, 25));
                                
                                p11132.add(sure);
                                p11132.add(remove);
                                p11132.add(clicked);
                                
                                p11132.setLayout(null);
                                sure.setBounds(100,100,400,100);
                                remove.setBounds(180,200,150,40);
                                clicked.setBounds(200,260,200,40);
                                
                                h1p2.add(p11132);
                                p11132.setBounds(300,10,1425,900);
                                p11132.setBorder(blackline);
                            }
                            
                            class yesRemoveID implements ActionListener{                          //         final process of removing id
                                
                                public void actionPerformed(ActionEvent e){
                                    
                                    String temp = "DELETE FROM "+bat_nam+" WHERE id = '"+id+"'";
                                    try{
                                        st.executeUpdate(temp);
                                        
                                        temp = "SELECT * FROM "+bat_nam+" WHERE id = '"+id+"'";
                                        rs1 = st.executeQuery(temp);
                                        if(!rs1.next()){
                                            System.out.println("removed");
                                            remove.setBounds(0,0,0,0);
                                            clicked.setText("Removed");
                                        }
                                        
                                        temp = "SELECT StudentNumber FROM InfoTable WHERE BatchName = '"+bat_nam+"'";
                                        rs1 = st.executeQuery(temp);
                                        rs1.next();
                                        int nmbrStu = rs1.getInt("StudentNumber");
                                        nmbrStu--;
                                        
                                        temp = "UPDATE InfoTable SET StudentNumber = "+nmbrStu+" WHERE BatchName in ('"+bat_nam+"')";
                                        st.executeUpdate(temp);
                                        
                                        jsp11131.setBounds(0,0,0,0);
                                    }
                                    catch(Exception yesRemoveID){
                                        System.out.println("error yesRemoveID");
                                        yesRemoveID.printStackTrace();
                                    }
                                    
                                }
                            }
                        }
                        
                        
                        
                        class handle111311 implements ActionListener{                                              //    edit mark
                            
                            JTextField tf1,tf2,tf3,tf4,tf5,tf6;
                            
                            JLabel l1,l2,l3,l4,l5,l6;
                            
                            JLabel r1, r2, r3, r4, r5, r6;
                            
                            JLabel Ct1L,Ct2L,Ct3L,Ct4L,AsnmntL,ExmL;
                            
                            JPasswordField pb;
                            JLabel inPass;
                            
                            public void actionPerformed(ActionEvent e){
                                if(p11132!=null){
                                    h1p2.remove(p11132);
                                }
                                
                                p11132 = new JPanel();
                                
                                if(haspass){
                                    JLabel lpass = new JLabel("Enter Password: ");
                                    lpass.setFont(new Font("Arial", Font.PLAIN, 25));
                                    
                                    pb = new JPasswordField();
                                    pb.setFont(new Font("Arial", Font.PLAIN, 25));
                                    
                                    inPass = new JLabel("Type Password");
                                    inPass.setFont(new Font("Arial", Font.PLAIN, 25));
                                    
                                    JButton enter = new JButton("Enter");
                                    enter.setFont(new Font("Arial", Font.PLAIN, 25));
                                    enter.addActionListener(new handle1113111());
                                    
                                    
                                    
                                    p11132.add(lpass);
                                    p11132.add(pb);
                                    p11132.add(inPass);
                                    p11132.add(enter);
                                    
                                    p11132.setLayout(null);
                                    lpass.setBounds(200,200,200,40);
                                    pb.setBounds(400,200,200,40);
                                    enter.setBounds(370,250,100,40);
                                    inPass.setBounds(300,320,250,40);
                                    
                                    
                                }
                                else{
                                    updateMark();
                                }
                                
                                
                                
                                p11132.setBorder(blackline);
                                h1p2.add(p11132);
                                p11132.setBounds(300,10,1425,950);
                                
                                
                            }
                            
                            class handle1113111 implements ActionListener{                  //          password entered. enter button.
                            
                                
                                public void actionPerformed(ActionEvent e){
                                    
                                    char[] temp = pb.getPassword();
                                    String pass = String.valueOf(temp);
                                    
                                    
                                    p11132.remove(inPass);
                                        
                                    if(pass.equals(password)){
                                        inPass = new JLabel("Correct Password");
                                        inPass.setFont(new Font("Arial", Font.PLAIN, 25));
                                        p11132.add(inPass);
                                        inPass.setBounds(300,320,250,40);
                                        
                                        updateMark();
                                        
                                    }
                                    else{
                                        inPass = new JLabel("Incorrect Password");
                                        inPass.setFont(new Font("Arial", Font.PLAIN, 25));
                                        p11132.add(inPass);
                                        inPass.setBounds(300,320,250,40);
                                    }
                                }
                            }
                            
                            void updateMark(){                                      //  updating mark
                                
                                if(p11132!=null){
                                    h1p2.remove(p11132);
                                }
                                
                                String toUse;
                                
                                p11132 = new JPanel();
                                
                                toUse = "Update CT1 mark ("+ct1+") to :";
                                Ct1L = new JLabel(toUse);
                                Ct1L.setFont(new Font("Arial", Font.PLAIN, 20));
                                
                                toUse = "Update CT2 mark ("+ct2+") to :";
                                Ct2L = new JLabel(toUse);
                                Ct2L.setFont(new Font("Arial", Font.PLAIN, 20));
                                
                                toUse = "Update CT3 mark ("+ct3+") to :";
                                Ct3L = new JLabel(toUse);
                                Ct3L.setFont(new Font("Arial", Font.PLAIN, 20));
                                
                                toUse = "Update CT4 mark ("+ct4+") to :";
                                Ct4L = new JLabel(toUse);
                                Ct4L.setFont(new Font("Arial", Font.PLAIN, 20));
                                
                                toUse = "Update Assignment mark ("+ct1+") to :";
                                AsnmntL = new JLabel(toUse);
                                AsnmntL.setFont(new Font("Arial", Font.PLAIN, 20));
                                
                                toUse = "Update Exam mark ("+ct1+") to :";
                                ExmL = new JLabel(toUse);
                                ExmL.setFont(new Font("Arial", Font.PLAIN, 20));
                                
                                
                                tf1 = new JTextField();
                                tf1.setFont(new Font("Arial", Font.PLAIN, 20));
                                JButton tf1b = new JButton("Update");
                                tf1b.setFont(new Font("Arial", Font.PLAIN, 20));
                                
                                tf2 = new JTextField();
                                tf2.setFont(new Font("Arial", Font.PLAIN, 20));
                                JButton tf2b = new JButton("Update");
                                tf2b.setFont(new Font("Arial", Font.PLAIN, 20));
                                
                                tf3 = new JTextField();
                                tf3.setFont(new Font("Arial", Font.PLAIN, 20));
                                JButton tf3b = new JButton("Update");
                                tf3b.setFont(new Font("Arial", Font.PLAIN, 20));
                                
                                tf4 = new JTextField();
                                tf4.setFont(new Font("Arial", Font.PLAIN, 20));
                                JButton tf4b = new JButton("Update");
                                tf4b.setFont(new Font("Arial", Font.PLAIN, 20));
                                
                                tf5 = new JTextField();
                                tf5.setFont(new Font("Arial", Font.PLAIN, 20));
                                JButton tf5b = new JButton("Update");
                                tf5b.setFont(new Font("Arial", Font.PLAIN, 20));
                                
                                tf6 = new JTextField();
                                tf6.setFont(new Font("Arial", Font.PLAIN, 20));
                                JButton tf6b = new JButton("Update");
                                tf6b.setFont(new Font("Arial", Font.PLAIN, 20));
                                
                                
                                JButton b1,b2,b3,b4,b5,b6;
                                
                                b1 = new JButton("Update");
                                b1.setFont(new Font("Arial", Font.PLAIN, 20));
                                b1.addActionListener(new update1());
                                b2 = new JButton("Update");
                                b2.setFont(new Font("Arial", Font.PLAIN, 20));
                                b2.addActionListener(new update2());
                                b3 = new JButton("Update");
                                b3.setFont(new Font("Arial", Font.PLAIN, 20));
                                b3.addActionListener(new update3());
                                b4 = new JButton("Update");
                                b4.setFont(new Font("Arial", Font.PLAIN, 20));
                                b4.addActionListener(new update4());
                                b5 = new JButton("Update");
                                b5.setFont(new Font("Arial", Font.PLAIN, 20));
                                b5.addActionListener(new update5());
                                b6 = new JButton("Update");
                                b6.setFont(new Font("Arial", Font.PLAIN, 20));
                                b6.addActionListener(new update6());
                                
                                
                                r1 = new JLabel();
                                r1.setFont(new Font("Arial", Font.PLAIN, 20));
                                r2 = new JLabel();
                                r2.setFont(new Font("Arial", Font.PLAIN, 20));
                                r3 = new JLabel();
                                r3.setFont(new Font("Arial", Font.PLAIN, 20));
                                r4 = new JLabel();
                                r4.setFont(new Font("Arial", Font.PLAIN, 20));
                                r5 = new JLabel();
                                r5.setFont(new Font("Arial", Font.PLAIN, 20));
                                r6 = new JLabel();
                                r6.setFont(new Font("Arial", Font.PLAIN, 20));
                                
                                
                                
                                p11132.add(Ct1L);  p11132.add(tf1);  p11132.add(b1);  p11132.add(r1);
                                p11132.add(Ct2L);  p11132.add(tf2);  p11132.add(b2);  p11132.add(r2);
                                p11132.add(Ct3L);  p11132.add(tf3);  p11132.add(b3);  p11132.add(r3);
                                p11132.add(Ct4L);  p11132.add(tf4);  p11132.add(b4);  p11132.add(r4);
                                p11132.add(AsnmntL);  p11132.add(tf5);  p11132.add(b5);  p11132.add(r5);
                                p11132.add(ExmL);  p11132.add(tf6);  p11132.add(b6);  p11132.add(r6);
                                
                                p11132.setLayout(null);
                                
                                
                                Ct1L.setBounds(100,100,350,40);  tf1.setBounds(450,100,200,40);  b1.setBounds(650,100,150,40);  r1.setBounds(820,100,150,40);
                                Ct2L.setBounds(100,150,350,40);  tf2.setBounds(450,150,200,40);  b2.setBounds(650,150,150,40);  r2.setBounds(820,150,150,40);
                                Ct3L.setBounds(100,200,350,40);  tf3.setBounds(450,200,200,40);  b3.setBounds(650,200,150,40);  r3.setBounds(820,200,150,40);
                                Ct4L.setBounds(100,250,350,40);  tf4.setBounds(450,250,200,40);  b4.setBounds(650,250,150,40);  r4.setBounds(820,250,150,40);
                                AsnmntL.setBounds(100,300,350,40);  tf5.setBounds(450,300,200,40);  b5.setBounds(650,300,150,40);  r5.setBounds(820,300,150,40);
                                ExmL.setBounds(100,350,350,40);  tf6.setBounds(450,350,200,40);  b6.setBounds(650,350,150,40);  r6.setBounds(820,350,150,40);
                                
                                
                                p11132.setBorder(blackline);
                                h1p2.add(p11132);
                                p11132.setBounds(300,10,1425,950);
                            }
                            
                            class update1 implements ActionListener{                //   update ct1 mark
                                
                                
                                
                                public void actionPerformed(ActionEvent e){
                                    
                                    String temp = tf1.getText();
                                    
                                    try{
                                        
                                        int mark = Integer.parseInt(temp);
                                    
                                        String toPass = "UPDATE "+bat_nam+" SET CT1 = "+mark+" WHERE id in ("+id+")";
                                        
                                        st.executeUpdate(toPass);
                                        
                                        toPass = "SELECT CT1 FROM "+bat_nam+" WHERE id = "+id;
                                        
                                        rs1 = st.executeQuery(toPass);
                                        rs1.next();
                                        int num = rs1.getInt("CT1");
                                        System.out.println("CT1 is "+ num);
                                        
                                        p11132.remove(Ct1L);
                                        p11132.remove(tf1);
                                        p11132.remove(r1);
                                        
                                        String toUse = "Update CT1 mark ("+num+") to :";
                                        Ct1L = new JLabel(toUse);
                                        Ct1L.setFont(new Font("Arial", Font.PLAIN, 20));
                                        
                                        toUse = "Updated ("+num+")";
                                        r1 = new JLabel(toUse);
                                        r1.setFont(new Font("Arial", Font.PLAIN, 20));
                                        
                                        tf1 = new JTextField();
                                        tf1.setFont(new Font("Arial", Font.PLAIN, 20));
                                        
                                        p11132.add(Ct1L);
                                        p11132.add(r1);
                                        p11132.add(tf1);
                                        
                                        Ct1L.setBounds(100,100,350,40);
                                        tf1.setBounds(450,100,200,40);
                                        r1.setBounds(820,100,150,40);
                                    }
                                    catch(Exception eu1){
                                        p11132.remove(r1);
                                        r1 = new JLabel("Invalid input");
                                        r1.setFont(new Font("Arial", Font.PLAIN, 20));
                                        
                                        p11132.add(r1);
                                        r1.setBounds(820,100,150,40);
                                        
                                        System.out.println("Error eu1");
                                     //   eu1.printStackTrace();
                                    }
                                    
                                }
                            }
                            
                            class update2 implements ActionListener{                //   update ct2 mark
                                
                                
                                public void actionPerformed(ActionEvent e){
                                    
                                    String temp = tf2.getText();
                                    
                                    try{
                                        
                                        int mark = Integer.parseInt(temp);
                                    
                                        String toPass = "UPDATE "+bat_nam+" SET CT2 = "+mark+" WHERE id in ("+id+")";
                                        
                                        st.executeUpdate(toPass);
                                        
                                        toPass = "SELECT CT2 FROM "+bat_nam+" WHERE id = "+id;
                                        
                                        rs1 = st.executeQuery(toPass);
                                        rs1.next();
                                        int num = rs1.getInt("CT2");
                                        System.out.println("CT2 is "+ num);
                                        
                                        p11132.remove(Ct2L);
                                        p11132.remove(tf2);
                                        p11132.remove(r2);
                                        
                                        String toUse = "Update CT2 mark ("+num+") to :";
                                        Ct2L = new JLabel(toUse);
                                        Ct2L.setFont(new Font("Arial", Font.PLAIN, 20));
                                        
                                        toUse = "Updated ("+num+")";
                                        r2 = new JLabel(toUse);
                                        r2.setFont(new Font("Arial", Font.PLAIN, 20));
                                        
                                        tf2 = new JTextField();
                                        tf2.setFont(new Font("Arial", Font.PLAIN, 20));
                                        
                                        p11132.add(Ct2L);
                                        p11132.add(r2);
                                        p11132.add(tf2);
                                        
                                        Ct2L.setBounds(100,150,350,40);
                                        tf2.setBounds(450,150,200,40);
                                        r2.setBounds(820,150,150,40);
                                    }
                                    catch(Exception eu1){
                                        p11132.remove(r2);
                                        r2 = new JLabel("Invalid input");
                                        r2.setFont(new Font("Arial", Font.PLAIN, 20));
                                        
                                        p11132.add(r2);
                                        r2.setBounds(820,150,150,40);
                                        
                                        System.out.println("Error eu1");
                                     //   eu1.printStackTrace();
                                    }
                                }
                            }
                            
                            class update3 implements ActionListener{                    //   update ct3 mark
                                
                                
                                public void actionPerformed(ActionEvent e){
                                    String temp = tf3.getText();
                                    
                                    try{
                                        
                                        int mark = Integer.parseInt(temp);
                                    
                                        String toPass = "UPDATE "+bat_nam+" SET CT3 = "+mark+" WHERE id in ("+id+")";
                                        
                                        st.executeUpdate(toPass);
                                        
                                        toPass = "SELECT CT3 FROM "+bat_nam+" WHERE id = "+id;
                                        
                                        rs1 = st.executeQuery(toPass);
                                        rs1.next();
                                        int num = rs1.getInt("CT3");
                                        System.out.println("CT3 is "+ num);
                                        
                                        p11132.remove(Ct3L);
                                        p11132.remove(tf3);
                                        p11132.remove(r3);
                                        
                                        String toUse = "Update CT3 mark ("+num+") to :";
                                        Ct3L = new JLabel(toUse);
                                        Ct3L.setFont(new Font("Arial", Font.PLAIN, 20));
                                        
                                        toUse = "Updated ("+num+")";
                                        r3 = new JLabel(toUse);
                                        r3.setFont(new Font("Arial", Font.PLAIN, 20));
                                        
                                        tf3 = new JTextField();
                                        tf3.setFont(new Font("Arial", Font.PLAIN, 20));
                                        
                                        p11132.add(Ct3L);
                                        p11132.add(r3);
                                        p11132.add(tf3);
                                        
                                        Ct3L.setBounds(100,200,350,40);
                                        tf3.setBounds(450,200,200,40);
                                        r3.setBounds(820,200,150,40);
                                    }
                                    catch(Exception eu1){
                                        p11132.remove(r3);
                                        r3 = new JLabel("Invalid input");
                                        r3.setFont(new Font("Arial", Font.PLAIN, 20));
                                        
                                        p11132.add(r3);
                                        r3.setBounds(820,200,150,40);
                                        
                                        System.out.println("Error eu1");
                                     //   eu1.printStackTrace();
                                    }
                                }
                            }
                            
                            class update4 implements ActionListener{                    //    update ct4 mark
                                
                                
                                public void actionPerformed(ActionEvent e){
                                    String temp = tf4.getText();
                                    
                                    try{
                                        
                                        int mark = Integer.parseInt(temp);
                                    
                                        String toPass = "UPDATE "+bat_nam+" SET CT4 = "+mark+" WHERE id in ("+id+")";
                                        
                                        st.executeUpdate(toPass);
                                        
                                        toPass = "SELECT CT4 FROM "+bat_nam+" WHERE id = "+id;
                                        
                                        rs1 = st.executeQuery(toPass);
                                        rs1.next();
                                        int num = rs1.getInt("CT4");
                                        System.out.println("CT4 is "+ num);
                                        
                                        p11132.remove(Ct4L);
                                        p11132.remove(tf4);
                                        p11132.remove(r4);
                                        
                                        String toUse = "Update CT4 mark ("+num+") to :";
                                        Ct4L = new JLabel(toUse);
                                        Ct4L.setFont(new Font("Arial", Font.PLAIN, 20));
                                        
                                        toUse = "Updated ("+num+")";
                                        r4 = new JLabel(toUse);
                                        r4.setFont(new Font("Arial", Font.PLAIN, 20));
                                        
                                        tf4 = new JTextField();
                                        tf4.setFont(new Font("Arial", Font.PLAIN, 20));
                                        
                                        p11132.add(Ct4L);
                                        p11132.add(r4);
                                        p11132.add(tf4);
                                        
                                        Ct4L.setBounds(100,250,350,40);
                                        tf4.setBounds(450,250,200,40);
                                        r4.setBounds(820,250,150,40);
                                    }
                                    catch(Exception eu1){
                                        p11132.remove(r1);
                                        r4 = new JLabel("Invalid input");
                                        r4.setFont(new Font("Arial", Font.PLAIN, 20));
                                        
                                        p11132.add(r4);
                                        r4.setBounds(820,250,150,40);
                                        
                                        System.out.println("Error eu1");
                                     //   eu1.printStackTrace();
                                    }
                                }
                            }
                            
                            class update5 implements ActionListener{                       //       update assignment mark
                                
                                
                                public void actionPerformed(ActionEvent e){
                                    String temp = tf5.getText();
                                    
                                    try{
                                        
                                        int mark = Integer.parseInt(temp);
                                    
                                        String toPass = "UPDATE "+bat_nam+" SET asnmnt = "+mark+" WHERE id in ("+id+")";
                                        
                                        st.executeUpdate(toPass);
                                        
                                        toPass = "SELECT asnmnt FROM "+bat_nam+" WHERE id = "+id;
                                        
                                        rs1 = st.executeQuery(toPass);
                                        rs1.next();
                                        int num = rs1.getInt("asnmnt");
                                        System.out.println("Assignment is "+ num);
                                        
                                        p11132.remove(AsnmntL);
                                        p11132.remove(tf5);
                                        p11132.remove(r5);
                                        
                                        String toUse = "Update Assignment mark ("+num+") to :";
                                        AsnmntL = new JLabel(toUse);
                                        AsnmntL.setFont(new Font("Arial", Font.PLAIN, 20));
                                        
                                        toUse = "Updated ("+num+")";
                                        r5 = new JLabel(toUse);
                                        r5.setFont(new Font("Arial", Font.PLAIN, 20));
                                        
                                        tf5 = new JTextField();
                                        tf5.setFont(new Font("Arial", Font.PLAIN, 20));
                                        
                                        p11132.add(AsnmntL);
                                        p11132.add(r5);
                                        p11132.add(tf5);
                                        
                                        AsnmntL.setBounds(100,300,350,40);
                                        tf5.setBounds(450,300,200,40);
                                        r5.setBounds(820,300,150,40);
                                    }
                                    catch(Exception eu1){
                                        p11132.remove(r5);
                                        r5 = new JLabel("Invalid input");
                                        r5.setFont(new Font("Arial", Font.PLAIN, 20));
                                        
                                        p11132.add(r5);
                                        r5.setBounds(820,300,150,40);
                                        
                                        System.out.println("Error eu1");
                                     //   eu1.printStackTrace();
                                    }
                                }
                            }
                            
                            class update6 implements ActionListener{                //  update final exam mark
                                
                                
                                public void actionPerformed(ActionEvent e){
                                    String temp = tf6.getText();
                                    
                                    try{
                                        
                                        int mark = Integer.parseInt(temp);
                                    
                                        String toPass = "UPDATE "+bat_nam+" SET examMark = "+mark+" WHERE id in ("+id+")";
                                        
                                        st.executeUpdate(toPass);
                                        
                                        toPass = "SELECT examMark FROM "+bat_nam+" WHERE id = "+id;
                                        
                                        rs1 = st.executeQuery(toPass);
                                        rs1.next();
                                        int num = rs1.getInt("examMark");
                                        System.out.println("examMark is "+ num);
                                        
                                        p11132.remove(ExmL);
                                        p11132.remove(tf6);
                                        p11132.remove(r6);
                                        
                                        String toUse = "Update examMark mark ("+num+") to :";
                                        ExmL = new JLabel(toUse);
                                        ExmL.setFont(new Font("Arial", Font.PLAIN, 20));
                                        
                                        toUse = "Updated ("+num+")";
                                        r6 = new JLabel(toUse);
                                        r6.setFont(new Font("Arial", Font.PLAIN, 20));
                                        
                                        tf6 = new JTextField();
                                        tf6.setFont(new Font("Arial", Font.PLAIN, 20));
                                        
                                        p11132.add(ExmL);
                                        p11132.add(r6);
                                        p11132.add(tf6);
                                        
                                        ExmL.setBounds(100,350,350,40);
                                        tf6.setBounds(450,350,200,40);
                                        r6.setBounds(820,350,150,40);
                                    }
                                    catch(Exception eu1){
                                        p11132.remove(r6);
                                        r6 = new JLabel("Invalid input");
                                        r6.setFont(new Font("Arial", Font.PLAIN, 20));
                                        
                                        p11132.add(r6);
                                        r6.setBounds(820,350,150,40);
                                        
                                        System.out.println("Error eu1");
                                     //   eu1.printStackTrace();
                                    }
                                }
                            }
                            
                            
                        }
                        
                        
                        
                        class handleAbsList implements ActionListener{                  //       clicked in a particular absent date of a student to delete that from absent dates.
                            
                            public void actionPerformed(ActionEvent e){
                                String getInfo = ((JButton) e.getSource()).getText();
                                int size,position;
                                String loc,posStr;
                                if(getInfo.charAt(17)=='<'){
                                    size = 11;
                                    loc = getInfo.substring(6,17);
                                    posStr = getInfo.substring(16,17);
                                    position = Integer.parseInt(posStr);
                                }
                                else{
                                    size = 12;
                                    loc = getInfo.substring(6,18);
                                    posStr = getInfo.substring(17,18);
                                    position = Integer.parseInt(posStr);
                                }
                                
                                String toPass = "SELECT absents FROM "+bat_nam+" WHERE ID = '"+id+"'";
                                try{
                                    rs1 = st.executeQuery(toPass);
                                    int num;
                                    rs1.next();
                                    num = rs1.getInt("absents");
                                    num--;
//                                    System.out.println(num);
                                    toPass = "UPDATE "+bat_nam+" SET absents = "+num+" WHERE id IN ('"+id+"')";
                                    st.executeUpdate(toPass);
                                    
                              
                                    int i;
                                    String NullDateChecker,NullDateCheckerD;
                                    
                                    String collectAll = "SELECT * FROM "+bat_nam+" WHERE ID = '"+id+"'";
                                    rs1 = st.executeQuery(collectAll);
                                    rs1.next();
                                    
                                    int Last = 1;
                                    
                                    
                                    for(i=30;i>0;i--){
                                        NullDateChecker = "absentDate"+i;
                                        NullDateCheckerD = rs1.getString(NullDateChecker);
//                                        System.out.println(NullDateChecker);
                                        if((NullDateCheckerD!=null)){
                                            Last = i;
                                            break;
                                        }
                                    }
                                    
                                    if(i==0){
                                        Last = 1;
                                    }
                                    
//                                    System.out.println("position = "+position);
//                                    System.out.println("last = "+Last);
                                    
//                                    System.out.println("checking dates: ");
                                    for(i=1;i<31;i++){
                                        String a = "absentDate"+i;
                                        String b = rs1.getString(a);
//                                        System.out.println(b);
                                    }
                                    System.out.println("checked");
                                    
                                    for(i=position+1; i<Last; i++){
                                        toPass = "SELECT * FROM "+bat_nam+" WHERE id = '"+id+"'";
                                        rs1 = st.executeQuery(toPass);
                                        rs1.next();
                                        toPass = "absentDate"+i;
//                                        System.out.println(toPass);
                                        String storage = rs1.getString(toPass);
//                                        System.out.println("Storage is: "+storage);
                                        int j = i-1;
                                        toPass = "UPDATE "+bat_nam+" SET absentDate"+j+" = '"+storage+"' WHERE id in ('"+id+"')";
                                        st.executeUpdate(toPass);
                                    }  
                             
                                   toPass = "UPDATE "+bat_nam+" SET absentDate"+Last+" = NULL WHERE id in ('"+id+"')";
                                   st.executeUpdate(toPass);
                             
                                    
                                    toPass = "UPDATE "+bat_nam+" SET absentDate30 = NULL WHERE id in ('"+id+"')";
                                    st.executeUpdate(toPass);
                                    
                       
                                    toPass = "SELECT * FROM "+bat_nam+" WHERE id = '"+id+"'";
                                    rs1 = st.executeQuery(toPass);
                                    rs1.next();
                                    
                                    System.out.println("Checking dates during removing absences : ");
                                    for(i=1;i<31;i++){
                                       
                                        
                                        toPass = "absentDate"+i;
                                        String a = rs1.getString(toPass);
                                        System.out.println(i+" "+a);
                                    }
                                    System.out.println("Done");    

                                    position--;
                                    absPanel.remove(absButtons[position]);
                                 
                                    jspAbs.setBounds(0,0,0,0);
                                    jspAbs.setBounds(700,130,200,200);
                                    
                                    int presents = rs1.getInt("Presents");
                                    presents++;
                                    
                                    toPass = "UPDATE "+bat_nam+" SET Presents = "+presents+" WHERE id in ('"+id+"')";
                                    st.executeUpdate(toPass);
                                    
                                    toPass = "SELECT * FROM "+bat_nam+" WHERE id = '"+id+"'";
                                    rs1 = st.executeQuery(toPass);
                                    rs1.next();
                                    
                                    int abs = rs1.getInt("absents");
                                    int prs = rs1.getInt("Presents");
                                    System.out.println("Absents : "+abs+" Presents : "+prs);  
                       
                                }
                                catch(Exception eAbsList){
                                    System.out.println("ERROR abslist");
                                    eAbsList.printStackTrace();
                                }
                            }
                        }     
 
                    }
                    
                    class handle11134 implements ActionListener{                    //   to add student to a batch
                    
                        JLabel l111341,l111342,l111343;
                        JTextField tf111341, tf111342;
                        JButton b111341;
                        
                        public void actionPerformed(ActionEvent e){
                        
                            if(p111341!=null){
                                h1p2.remove(p111341);
                            }
                            p111341 = new JPanel();
                            
                            
                            
                            h1p2.add(p111341);
                            p111341.setBounds(261,1,1454,960);
                            p111341.setBorder(blackline);
                            
                            
                          //  l11134L = new JLabel()
                            
                            
                            l111341 = new JLabel("Name : ");
                            l111341.setFont(new Font("Monako",Font.BOLD,25));
                            tf111341 = new JTextField();
                            tf111341.setFont(new Font("Monako",4,20));
                            l111342 = new JLabel("ID : ");
                            l111342.setFont(new Font("Monako",Font.BOLD,25));
                            tf111342 = new JTextField();
                            tf111342.setFont(new Font("Monako",4,20));
                            b111341 = new JButton("Enter");
                            b111341.setFont(new Font("Monako",Font.BOLD,25));
                            b111341.addActionListener(new handle1111341());
                            JLabel l111344 = new JLabel("[ ID must be of nine digits. ]");
                            l111344.setFont(new Font("Monako",Font.ITALIC,25));
                            
                            l111343 = new JLabel();
                            l111343.setFont(new Font("Monako",4,25));
                            
                            
                            p111341.add(l111341);
                            p111341.add(tf111341);
                            p111341.add(l111342);
                            p111341.add(tf111342);
                            p111341.add(b111341);
                            p111341.add(l111343);
                            p111341.add(l111344);
                            
                            p111341.setLayout(null);
                            
                            l111341.setBounds(100,100,100,30);
                            tf111341.setBounds(250,100,300,30);
                            l111342.setBounds(100,160,100,30);
                            tf111342.setBounds(250,160,300,30);
                            l111344.setBounds(600,160,350,30);
                            b111341.setBounds(250,230,100,30);
                            l111343.setBounds(200,280,350,30);
                            
                            
                        }
                        
                        public class handle1111341 implements ActionListener{               //    name and id of new student entered.
                            
                            public void actionPerformed(ActionEvent e){
                            
                                String name = tf111341.getText();
                                String id = tf111342.getText();
                                
                                System.out.println(name);
                                System.out.println(id);
                                
                                if(id.length()==9){
                                
          
                                
                                    String temp = "SELECT Nmbr FROM number";
                                    System.out.println(temp);
                                    try{
                                    
                                        rs1 = st.executeQuery(temp);
                                        rs1.next();
                                        int bat_number = rs1.getInt("Nmbr");
                                    
                                        String[] batch_array = new String[bat_number];
                                    
                                        Boolean flag = true;
                                    
                                        int i;
                                    
                                        temp = "SELECT BatchName from InfoTable";
                                        rs1 = st.executeQuery(temp);
                                        for(i=0;i<bat_number;i++){
                                            rs1.next();
                                            batch_array[i] = rs1.getString("BatchName");
                                        }
                                    
                                        for(i=0;i<bat_number;i++){
                                            temp = "SELECT * FROM "+batch_array[i]+" WHERE id = '"+id+"'";
                                            rs1 = st.executeQuery(temp);
                                            if(rs1.next()){
                                                flag = false;
                                                break;
                                            }
                                        }
                                    
                                    
                                        if(flag){
                                            temp = "SELECT * FROM "+bat_nam+" WHERE id = '"+id+"'";
                                        
                                            rs1 = st.executeQuery(temp);
                                            System.out.println("Here");
                                            if(rs1.next()){
                                                System.out.println("Available");
                                            }
                                            else{
                                                System.out.println("Not available");
                                            }
                                            temp = "INSERT INTO "+bat_nam+" VALUES ('"+id+"','"+name+"','"+level+"','"+term+"','"+dept+"',0,0,0,0,0,0,0,100,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL"+")";

                                            System.out.println(temp);
                                            st.executeUpdate(temp);
                                            System.out.println("Done");
                                            String S ="SELECT * FROM InfoTable WHERE BatchName = '"+bat_nam+"'";
                                            rs1 = st.executeQuery(S);
                                            rs1.next();
                                            int stnm = rs1.getInt("StudentNumber");
                                            rs1.close();
                                            stnm++;
                                            System.out.println("stnm is: "+stnm);
                                            S = "UPDATE InfoTable SET StudentNumber = "+stnm+" WHERE BatchName in ('"+bat_nam+"')";
                                            System.out.println(S);
                                            st.executeUpdate(S);
                                            System.out.println("Updated Infotable");
                                            S = "Id "+id+" is added";
                                            l111343.setText(S);
                                    
                                        }
                                    
                                        else{
                                            String tempS = "Id "+id+" already exists.";
                                            l111343.setText(tempS);
                                            System.out.println("else");
                                        }
                                    }
                                    catch(Exception e1111341){
                                        String tempS = "Id "+id+" already exists.";
                                    
                                        l111343.setText(tempS);
                                    
                                        System.out.println("error e1111341");
                                    
                                        e1111341.printStackTrace();
                                    }
                                
                                }
                                else{
                                    l111343.setText("Id Must be of nine digits");
                                }
                                
                            }
                            
                        }
                    
                    }
                    
                    
                }
                
                class handle1114 implements ActionListener{                           //        to calculate total mark
                    
                    public void actionPerformed(ActionEvent e){
                        System.out.println("handle1114");
                        
                        String toPass = "SELECT * FROM InfoTable WHERE batchname = '"+bat_nam+"'";
                        
                        try{
                            rs1 = st.executeQuery(toPass);
                            rs1.next();
                            int studentNumber = rs1.getInt("StudentNumber");
                            String[] IDArray = new String[studentNumber]; 
                            int i;
                            
                            toPass = "SELECT id FROM "+bat_nam;
                            rs1 = st.executeQuery(toPass);
                            
                            for(i=0;i<studentNumber;i++){
                                rs1.next();
                                IDArray[i] = rs1.getString("id");
                            }
                            
                            
                            for(i=0;i<studentNumber;i++){
                                
                                toPass = "SELECT * FROM "+bat_nam+" WHERE id = '"+IDArray[i]+"'";
                                rs1 = st.executeQuery(toPass);
                                rs1.next();
                                
                                int CT1, CT2, CT3, CT4, ASNMNT;
                                double PRCNT, EXM, TOTL, PRCNTH;
                                System.out.println("line 2549");
                                CT1 = rs1.getInt("CT1");
                                System.out.println("line 2549");
                                CT2 = rs1.getInt("CT2");
                                CT3 = rs1.getInt("CT3");
                                CT4 = rs1.getInt("CT4");
                                ASNMNT = rs1.getInt("asnmnt");
                                
                                int pres = rs1.getInt("Presents");
                                int absn = rs1.getInt("absents");
                                int totlDay = pres+absn;
                                
                                
                                PRCNTH = ((double)pres*100/((double)totlDay));
                                
                                EXM = rs1.getDouble("examMark");
                                
                                
                                PRCNT = (PRCNTH*5/100);
                                
                                TOTL = CT1+CT2+CT3+CT4+ASNMNT+PRCNT+EXM;
                                System.out.println(TOTL + "is total mark");
                                
                                toPass = "UPDATE "+bat_nam+" SET TotalMark = "+TOTL+" WHERE id in ('"+IDArray[i]+"')";
                                st.executeUpdate(toPass);
                                
                                toPass = "UPDATE "+bat_nam+" SET percentage = "+PRCNTH+" WHERE id in ('"+IDArray[i]+"')";
                                st.executeUpdate(toPass);
                                
                            }
                            
                            b1114.setText("UPDATED TOTAL MARK");
                            b1114.setBackground(Color.GREEN);
                            
                        }
                        catch(Exception handle11141){
                            System.out.println("error handle11141");
                            handle11141.printStackTrace();
                        }
                        
                    }
                }
                
            }
            
        }
        
        
        
        class handle12 implements ActionListener{                                      // batches -> add batch
            
            JTextField t121,t122,t123,t124,t125;
            public void actionPerformed(ActionEvent e){
                
                if(h1p2!=null)
                    p4.remove(h1p2);
                h1p2 = new JPanel();
                
                JLabel l121 = new JLabel("Department Name : ");
                              l121.setFont(new Font("Monako",Font.BOLD,20));
                JLabel l122 = new JLabel("Level (Digit only) : ");
                              l122.setFont(new Font("Monako",Font.BOLD,20));
                JLabel l123 = new JLabel("Term : ");
                              l123.setFont(new Font("Monako",Font.BOLD,20));
                JLabel l124 = new JLabel("First six digits of ID : ");
                              l124.setFont(new Font("Monako",Font.BOLD,20));
                JLabel l125 = new JLabel("Section : ");
                              l125.setFont(new Font("Monako",Font.BOLD,20));
                              
                t121 = new JTextField();
                                  t121.setFont(new Font("Monako",4,20));
                t122 = new JTextField();
                                  t122.setFont(new Font("Monako",4,20));
                t123 = new JTextField();
                                  t123.setFont(new Font("Monako",4,20));
                t124 = new JTextField();
                                  t124.setFont(new Font("Monako",4,20));
                t125 = new JTextField();
                                  t125.setFont(new Font("Monako",4,20));
                                  
                JButton b121 = new JButton("Enter");
                                  b121.setFont(new Font("Monako",Font.BOLD,20));
                                  b121.addActionListener(new handle121());
                                  
                                  
                JLabel l126 = new JLabel("ADD BATCH");
                              l126.setFont(new Font("Monako",Font.BOLD,20));
                              
                JLabel l127 = new JLabel("");
                              l127.setFont(new Font("Monako",Font.BOLD,20));
                
                
                h1p2.add(l127);
                h1p2.add(l126);
                h1p2.add(l121);
                h1p2.add(l122);
                h1p2.add(l123);
                h1p2.add(l124);
                h1p2.add(l125);
                h1p2.add(t121);
                h1p2.add(t122);
                h1p2.add(t123);
                h1p2.add(t124);
                h1p2.add(t125);
                h1p2.add(b121);
                
                
                h1p2.setLayout(null);
                l126.setBounds(300,20,200,40);
                l121.setBounds(50,80,300,40);
                t121.setBounds(400,80,300,40);
                l122.setBounds(50,130,300,40);
                t122.setBounds(400,130,300,40);
                l123.setBounds(50,180,300,40);
                t123.setBounds(400,180,300,40);
                l125.setBounds(50,230,300,40);
                t125.setBounds(400,230,300,40);
                l124.setBounds(50,280,300,40);
                t124.setBounds(400,280,300,40);
                b121.setBounds(600,330,90,35);
                
                
                
                p4.add(h1p2);
                h1p2.setBounds(1,121,1715,960);
                
            }
            
            class handle121 implements ActionListener{                              //   batches -> add batches -> enter
                
                String bat_name;
                public void actionPerformed(ActionEvent e){
                    if(l127!=null)
                        h1p2.remove(l127);
                    l127 = new JLabel();
                           l127.setFont(new Font("Monako",4,20));
                           
                    try{
                        String dept_name = t121.getText();
                        String Lvl = t122.getText();
                        String trm = t123.getText();
                        String dgt = t124.getText();
                        String sec = t125.getText();
                        bat_name = dept_name+"_"+Lvl+"_"+trm+"_"+sec;
                        
                        String ToInsrt = "CREATE TABLE "+bat_name+" (id VARCHAR(14) not NULL, name VARCHAR(30) not NULL,  level CHAR,  term VARCHAR(2), dept VARCHAR(6), CT1 INTEGER, CT2 INTEGER, CT3 INTEGER, CT4 INTEGER, asnmnt INTEGER, absents INTEGER, presents INTEGER, percentage DOUBLE, examMark DOUBLE, TotalMark DOUBLE, absentDate1 VARCHAR(18), absentDate2 VARCHAR(18), absentDate3 VARCHAR(18), absentDate4 VARCHAR(18), absentDate5 VARCHAR(18), absentDate6 VARCHAR(18), absentDate7 VARCHAR(18), absentDate8 VARCHAR(18), absentDate9 VARCHAR(18), absentDate10 VARCHAR(18), absentDate11 VARCHAR(18), absentDate12 VARCHAR(18), absentDate13 VARCHAR(18), absentDate14 VARCHAR(18), absentDate15 VARCHAR(18), absentDate16 VARCHAR(18), absentDate17 VARCHAR(18), absentDate18 VARCHAR(18), absentDate19 VARCHAR(18), absentDate20 VARCHAR(18), absentDate21 VARCHAR(18), absentDate22 VARCHAR(18), absentDate23 VARCHAR(18), absentDate24 VARCHAR(18), absentDate25 VARCHAR(18), absentDate26 VARCHAR(18), absentDate27 VARCHAR(18), absentDate28 VARCHAR(18), absentDate29 VARCHAR(18), absentDate30 VARCHAR(18), PRIMARY KEY(id)) ";
                        st.executeUpdate(ToInsrt);
                        
                        String ToInsrtInfo = "INSERT INTO InfoTable VALUES ('"+bat_name+"', '"+dgt+"', 0)";
                        st.executeUpdate(ToInsrtInfo);
                        System.out.println("No Problem");
                        
                        if(rs1!=null)
                            rs1.close();
                        rs1 = st.executeQuery("SELECT Nmbr FROM number");
                        rs1.next();
                        System.out.println("No Problem");
                        int n = rs1.getInt("Nmbr");
                     //   System.out.println("No Problem");
                        System.out.println("Number of batches is: "+n);
                        ToInsrtInfo = "DELETE FROM number WHERE Nmbr = "+n;
                        System.out.println("No Problem");
                        st.executeUpdate(ToInsrtInfo);
                        System.out.println("No Problem");
                        n++;
                        System.out.println("Number of batches is: "+n);
                        ToInsrtInfo = "INSERT INTO number VALUES ("+n+")";
                        st.executeUpdate(ToInsrtInfo);
                        if(rs1!=null)
                            rs1.close();
                        rs1 = st.executeQuery("SELECT Nmbr FROM number");
                        
                        rs1.next();
                        
                        n = rs1.getInt("Nmbr");
                        
                        
                        System.out.println("Number of batches is: "+n);
                        
                        String temp = "Batch "+bat_name+" added succesfully";
                        
                        
                        l127.setText(temp);
                        
                        
                        h1p2.add(l127);
                        l127.setBounds(400,380,350,40);
                    }
                    catch(Exception ex){
                        System.out.println("Error in updating.");
                        String temp = "Batch "+bat_name+" already exists";
                        if(l127!=null)
                        h1p2.remove(l127);
                        l127 = new JLabel();
                        l127.setFont(new Font("Monako",4,20));
                        
                        h1p2.add(l127);
                        l127.setBounds(400,380,350,40);
                        
                        l127.setText(temp);
                        ex.printStackTrace();
                    }
              
                }
                
            }
        }
        
        class handle13 implements ActionListener{                   //    to remove a batch
            
            JButton bt[];
            JScrollPane jsp;
            
            public void actionPerformed(ActionEvent e){
            
               
                try{
                    if(h1p2!=null){
                        p4.remove(h1p2);
                        System.out.println("h1p2 removed");
                    }
                    h1p2 = new JPanel();
                    
                    
                    System.out.println("select number");
                    rs1 = st.executeQuery("SELECT Nmbr FROM number");
                    rs1.next();
                    int n = rs1.getInt("Nmbr");
                    String arr[] = new String[n];
                    int i;
                    System.out.println("select batchname");
                    rs1 = st.executeQuery("SELECT BatchName FROM InfoTable");
                    System.out.println("batchname selected");
                    
                    bt = new JButton[n];
                    
                    JPanel p11 = new JPanel();
                    
                    p11.setBorder(blackline);
                    
                    p11.setLayout(new GridLayout(n,1));
                //    p11.setLayout(null);
                  //  p11.setSize(240,n*50);
                    System.out.println("No problem");
                    
                    for(i=0;i<n;i++){
                        rs1.next();
                    //   System.out.println("  No problem "+i);
                        arr[i]=rs1.getString("BatchName");
                        
                    }
                    
                    for(i=0;i<n;i++){
                        System.out.println(arr[i]);
                    }
                    
                    int temp = 20;
                    
                    
                    for(i=0;i<n;i++){
                   //     System.out.println("    No problem "+i);
                        bt[i]=new JButton();
                       
                        bt[i].setText(arr[i]);
                    //    System.out.println("        No problem "+i);
                        bt[i].setFont(new Font("Monako",Font.BOLD,20));
                        bt[i].addActionListener(new handle131());
                        bt[i].setSize(210,40);
                        
                        p11.add(bt[i]);
                        
                   //     bt[i].setBounds(20,temp,210,40);
                        
                    //    temp = temp+50;
                        
                        System.out.println("done");
                    }
                    
                    jsp = new JScrollPane(p11);
                 //   jsp.getVerticalScrollBar();
                    
                    jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
                 
                   
                    h1p2.add(jsp);
                    h1p2.setLayout(null);
                    jsp.setBounds(50,50,210,400);
                    
                    p4.add(h1p2);
                    p4.setLayout(null);
                    h1p2.setBounds(1,121,1715,960);
                    h1p2.setBorder(blackline);
                    
                }
                catch(Exception e11){
                    System.out.println("Exception in e11");
                    e11.printStackTrace();
                }
            
            }
            
            class handle131 implements ActionListener{                   //      batch selected to remove from database
            JPasswordField PassRmv;
            JLabel L2Pass;
            JButton PassButton;
            String bat_nam;
            public void actionPerformed(ActionEvent e){
                
                bat_nam = ((JButton) e.getSource()).getText();
                
                
                if(haspass){
                    JLabel LPass = new JLabel("Enter Password: ");
                    LPass.setFont(new Font("Monako",Font.BOLD,25));
                    PassRmv = new JPasswordField();
                    PassRmv.setFont(new Font("Monako",Font.BOLD,25));
                    PassButton = new JButton("Enter");
                    PassButton.setFont(new Font("Monako",Font.BOLD,25));
                    PassButton.addActionListener(new handle1311());
                    L2Pass = new JLabel("");
                    L2Pass.setFont(new Font("Monako", Font.PLAIN, 25));
                    
                    
                    
                    h1p2.add(LPass);
                    h1p2.add(PassRmv);
                    h1p2.add(PassButton);
                    h1p2.add(L2Pass);
                    LPass.setBounds(500,100,250,40);
                    PassRmv.setBounds(750,100,250,40);
                    PassButton.setBounds(800,150,100,40);
                    L2Pass.setBounds(650,210,300,40);
                    
                    app.setVisible(true);
                    
                    
                }
                else{
                        removeBatch();
                        
                }
                
                
            }
            
            class handle1311 implements ActionListener{                //     entered password to remove batch from database
                
                public void actionPerformed(ActionEvent e){
                    
                    if(L2Pass!=null){
                        h1p2.remove(L2Pass);
                    }
                    L2Pass = new JLabel();
                    char[] bpas = PassRmv.getPassword();
                    String bpass = String.valueOf(bpas);
                    if(bpass.equals(password)){
                        System.out.println("Password is correct");
                        L2Pass.setText("Batch Removed");
                        
                        PassButton.removeActionListener(this);
                        
                        removeBatch();
                        
                    }
                    else{
                        L2Pass.setText("Incorrect Password!");
                        
                        System.out.println("Incorrect password. Correct password is: "+password);
                    }
                    L2Pass.setFont(new Font("Monako", Font.PLAIN, 25));
                    h1p2.add(L2Pass);
                    L2Pass.setBounds(650,210,300,40);
                    
                  //  app.setVisible(true);
                    
                }
            }
            
            void removeBatch(){            //             method  to remove batch
                
                
                String topass = "DELETE FROM InfoTable WHERE BatchName =  '"+bat_nam+"'";
                try{
                    st.executeUpdate(topass);
                    topass = "SELECT Nmbr FROM number";
                    rs1 = st.executeQuery(topass);
                    if(rs1.next()){
                        int n = rs1.getInt("Nmbr");
                        int np = n-1;
                        topass = "UPDATE number SET nmbr = "+np+" WHERE nmbr in ("+n+")";
                        st.executeUpdate(topass);
                        System.out.println("Updated");
                        topass = "SELECT Nmbr FROM number";
                        rs1 = st.executeQuery(topass);
                        rs1.next();
                        n = rs1.getInt("Nmbr");
                        System.out.println("Number is : "+n);
                        
                        topass = "DROP TABLE "+bat_nam;
                        st.executeUpdate(topass);
                        
                        h1p2.remove(jsp);
                        h1p2.repaint();
                    }
                }
                catch(Exception e1311){
                    System.out.println("Error e1311");
                    e1311.printStackTrace();
                }
            }
        }
        }
    }
    class handle2 implements ActionListener{                                             //   search id
    
        JTextField h2ltf1;
        
        public void actionPerformed(ActionEvent e){
            
            jsp11131Bool = false;
       
            if(p4!=null)
                remove(p4);
            p4 = new JPanel();
            add(p4);
            p4.setBounds(192,1,(11*1920)/10,1080);
            p4.setBorder(blackline);
            
            
            h2p1 = new JPanel();
            h2p2 = new JPanel();
            
            
            JLabel h1l = new JLabel("Enter ID: ");
            h2ltf1 = new JTextField(20);
            JButton b21 = new JButton("Search");
            b21.addActionListener(new handle21());
            h1l.setFont(new Font("Monako",Font.BOLD,25));
            b21.setFont(new Font("Monako", Font.BOLD,20));
            h2ltf1.setFont(new Font("Monako",4,20));
            
         
            
            h2p1.add(h1l);
            h2p1.add(b21);
            h2p1.add(h2ltf1);
            
            h2p1.setLayout(null);
            
            h1l.setBounds(300,50,400,40);
            h2ltf1.setBounds(420,50,200,40);
            b21.setBounds(650,52,100,35);
            
            p4.add(h2p1);
            p4.add(h2p2);
            
            
            p4.setLayout(null);
            
            h2p1.setBounds(1,1,1715,120);
            h2p2.setBounds(1,121,1715,960);
            
            h2p1.setBorder(blackline);
            h2p2.setBorder(blackline);
            
        }
        
        class handle21 implements ActionListener{                                   //    id entered to search
           
            String id,name,trm;
                        
            JLabel CT1,CT2,CT3,CT4,Assignment,Exam_mark,PRESENTS, ATT_PERCENT, TOTAL_MARK, TOTAL_MARK_HUNDRED;
            JButton Editor,absButtons[];
            int ct1,ct2,ct3,ct4,asnmnt,absents,presents;
            double exm_mrk,att_percent,total_mark,total_mark_hundred;
            JScrollPane jspAbs;
            JPanel absPanel;
                        
            public void actionPerformed(ActionEvent e){
                
               
                
                if(h2p2!=null){
                    p4.remove(h2p2);
                }
                h2p2 = new JPanel();
                
            //    h1p2.setLayout(null);
                
                id = h2ltf1.getText();
                
                String toPass = "SELECT Nmbr FROM number";
                try{
                    rs1 = st.executeQuery(toPass);
                    rs1.next();
                    int batchNumber = rs1.getInt("Nmbr");
                    int i;
                    
                    String[] bat_arr = new String[batchNumber];
                    
                    toPass = "SELECT BatchName from InfoTable";
                    rs1 = st.executeQuery(toPass);
                    
                    for(i=0;i<batchNumber;i++){
                        rs1.next();
                        bat_arr[i] = rs1.getString("BatchName");
                    }
                    
                    for(i=0;i<batchNumber;i++){
                        
                        toPass = "SELECT * FROM "+bat_arr[i]+" where id = '"+id+"'";
                        
                        rs1 = st.executeQuery(toPass);
                        
                        if(rs1.next()){
                            
                            if(h2p2!=null){
                                p4.remove(h2p2);
                            }
                            
                            h2p2 = new JPanel();
                            
                            rs1 = st.executeQuery(toPass);
                                rs1.next();
                                name = rs1.getString("name");
                                String batch = rs1.getString("Level");
                                String term = rs1.getString("Term");
                                String dept = rs1.getString("dept");
                                ct1 = rs1.getInt("CT1");
                                ct2 = rs1.getInt("CT2");
                                ct3 = rs1.getInt("CT3");
                                ct4 = rs1.getInt("CT4");
                                asnmnt = rs1.getInt("asnmnt");
                                absents = rs1.getInt("absents");
                                presents = rs1.getInt("Presents");
                                exm_mrk = rs1.getDouble("examMark");
                                total_mark = rs1.getDouble("TotalMark");
                                total_mark_hundred = (total_mark/3.0);
                                
                                att_percent = (((double)(presents*100)/(presents+absents)));
                                
                                
/*                                System.out.println(batch);
                                System.out.println(term);
                                System.out.println(dept);
                                System.out.println(ct1);
                                System.out.println(ct2);
                                System.out.println(ct3);
                                System.out.println(ct4);
                                System.out.println(asnmnt);
                                System.out.println(absents); */
                                
                                
                             //   String param = "Name            : "+name;
                                
                                JLabel lName = new JLabel("Name : ");
                                lName.setFont(new Font("Arial",Font.BOLD,25));
                                JLabel lName2 = new JLabel(name);
                                lName2.setFont(new Font("Arial",Font.PLAIN,25));
                                
                            //    param = "Id             : "+id;
                                JLabel lId = new JLabel("ID : ");
                                lId.setFont(new Font("Arial",Font.BOLD,25));
                                JLabel lId2 = new JLabel(id);
                                lId2.setFont(new Font("Arial",Font.PLAIN,25));
                                
                            //    param = "Level          : "+level;
                                JLabel lLevel = new JLabel("Level : ");
                                lLevel.setFont(new Font("Arial",Font.BOLD,25));
                                JLabel lLevel2 = new JLabel(batch);
                                lLevel2.setFont(new Font("Arial",Font.PLAIN,25));
                                
                        //        param ="Term            : "+term;
                                JLabel lTerm = new JLabel("Term : ");
                                lTerm.setFont(new Font("Arial",Font.BOLD,25));
                                JLabel lTerm2 = new JLabel(term);
                                lTerm2.setFont(new Font("Arial",Font.PLAIN,25));
                                
                        //        param ="Department      : "+dept;
                                JLabel lDept = new JLabel("Department : ");
                                lDept.setFont(new Font("Arial",Font.BOLD,25));
                                JLabel lDept2 = new JLabel(dept);
                                lDept2.setFont(new Font("Arial",Font.PLAIN,25));
                                
                                JLabel lAbsents = new JLabel("Absents: ");
                                lAbsents.setFont(new Font("Arial",Font.BOLD,25));
                                
                           //     JLabel CT1,CT2,CT3,CT4,Assignment,Exam_mark;
                           //     JButton CTb1,CTb2,CTb3,CTb4,Assignmentb,Exam_markb;
                           
                           
                           
                                String exm_mrkS, att_percentS, total_markS, total_mark_hundredS;
                                
                                NumberFormat formatter = new DecimalFormat("###.00");
                                
                                
                                exm_mrkS = (formatter.format(exm_mrk));
                                        
                                att_percentS = (formatter.format(att_percent));
                                        
                                total_markS = (formatter.format(total_mark));
                                        
                                total_mark_hundredS = (formatter.format(total_mark_hundred));
                           
                           
                           
                                
                                toPass = "CT 1 : "+ct1;
                                CT1 = new JLabel(toPass);
                                CT1.setFont(new Font("Arial", Font.BOLD,20));
                                
                                toPass = "CT 2 : "+ct2;
                                CT2 = new JLabel(toPass);
                                CT2.setFont(new Font("Arial", Font.BOLD,20));
                                
                                toPass = "CT 3 : "+ct3;
                                CT3 = new JLabel(toPass);
                                CT3.setFont(new Font("Arial", Font.BOLD,20));
                                
                                toPass = "CT 4 : "+ct4;
                                CT4 = new JLabel(toPass);
                                CT4.setFont(new Font("Arial", Font.BOLD,20));
                                
                                toPass = "Assignment : "+asnmnt;
                                Assignment = new JLabel(toPass);
                                Assignment.setFont(new Font("Arial", Font.BOLD,20));
                                
                                toPass = "Exam Mark : "+exm_mrkS;
                                Exam_mark = new JLabel(toPass);
                                Exam_mark.setFont(new Font("Arial", Font.BOLD,20));
                                
                                toPass = "Presents : "+presents;
                                PRESENTS = new JLabel(toPass);
                                PRESENTS.setFont(new Font("Arial", Font.BOLD,20));
                                
                                toPass = "Attendance Percentage : "+att_percentS;
                                ATT_PERCENT = new JLabel(toPass);
                                ATT_PERCENT.setFont(new Font("Arial", Font.BOLD,20));
                                
                                toPass = "Total Mark : "+total_markS;
                                TOTAL_MARK = new JLabel(toPass);
                                TOTAL_MARK.setFont(new Font("Arial", Font.BOLD,20));
                                
                                toPass = "Total Mark in 100 : "+total_mark_hundredS;
                                TOTAL_MARK_HUNDRED = new JLabel(toPass);
                                TOTAL_MARK_HUNDRED.setFont(new Font("Arial", Font.BOLD,20));
                                TOTAL_MARK_HUNDRED.setForeground(Color.BLUE);
                                
                                
                         //       Editor.addActionListener(new handle111311());
                                
                                
                                
                                
                                absPanel = new JPanel();
                                
                                absPanel.setLayout(new GridLayout(absents,1));
                                
                                absButtons = new JButton[absents];
                                int k,j=0;
                                for(k=1;k<absents+1;k++){
                                    String colm = "absentDate"+k;
                                    
                                    System.out.println(colm);
                                    
                                    String dateOfAbs = rs1.getString(colm);
                                    System.out.println(dateOfAbs);
                                    if(dateOfAbs == null){
                                        break;
                                    }
                                    else{
                                        String param = "<html>"+colm+"<br>"+dateOfAbs+"<html>";
                                        System.out.println(param);
                                        absButtons[k-1]=new JButton(param);
                                        absButtons[k-1].setFont(new Font("Arial",Font.PLAIN,25));
                                    //    absButtons[k-1].addActionListener(new handleAbsList());
                                        System.out.println("Before adding");
                                        absPanel.add(absButtons[k-1]);
                                        System.out.println("Added");
                                    }
                                    
                                    
                                }
                                
                                
                                
                                
                                jspAbs = new JScrollPane(absPanel);
                    
                                jspAbs.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
                                
                               
                              //  removeId.addActionListener(new IdRemover());
                                
                                
                                
                                
                                
                                h2p2.add(lName);
                                h2p2.add(lId);
                                h2p2.add(lLevel);
                                h2p2.add(lTerm);
                                h2p2.add(lDept);
                                h2p2.add(lAbsents);
                                h2p2.add(jspAbs);
                                
                                h2p2.add(lName2);
                                h2p2.add(lId2);
                                h2p2.add(lLevel2);
                                h2p2.add(lTerm2);
                                h2p2.add(lDept2);
                                
                                h2p2.add(CT1);
                                h2p2.add(CT2);
                                h2p2.add(CT3);
                                h2p2.add(CT4);
                                h2p2.add(Assignment);
                                h2p2.add(Exam_mark);
                                
                                h2p2.add(PRESENTS);
                                h2p2.add(ATT_PERCENT);
                                h2p2.add(TOTAL_MARK);
                                h2p2.add(TOTAL_MARK_HUNDRED);
                                
                          //      h2p2.add(Editor);
                                
                                
                                h2p2.setLayout(null);
                                
                                lName.setBounds(100,50,200,40);
                                lId.setBounds(100,100,200,40);
                                lLevel.setBounds(100,150,200,40);
                                lTerm.setBounds(100,200,200,40);
                                lDept.setBounds(100,250,200,40);
                                
                                lName2.setBounds(300,50,300,40);
                                lId2.setBounds(300,100,300,40);
                                lLevel2.setBounds(300,150,300,40);
                                lTerm2.setBounds(300,200,300,40);
                                lDept2.setBounds(300,250,300,40);
                                lAbsents.setBounds(700,70,200,40);
                                jspAbs.setBounds(700,130,200,200);
                                
                                CT1.setBounds(100,410,200,40);
                                CT2.setBounds(100,460,200,40);
                                CT3.setBounds(100,510,200,40);
                                CT4.setBounds(100,560,200,40);
                                Assignment.setBounds(100,610,200,40);
                                Exam_mark.setBounds(100,660,200,40);
                                
                                PRESENTS.setBounds(301,410,200,40);
                                ATT_PERCENT.setBounds(301,460,350,40);
                                
                                TOTAL_MARK.setBounds(301,510,350,40);
                                TOTAL_MARK_HUNDRED.setBounds(301,560,350,40);
                                
                                p4.add(h2p2);
                                h2p2.setBounds(1,121,1715,960);
                                h2p2.setBorder(blackline);
                               
                            
                            
                            break;
                        }
                        else{
                            if(h2p2!=null){
                                p4.remove(h2p2);
                            }
                            
                            h2p2 = new JPanel();
                            JLabel lIdNotFound = new JLabel("Id not found");
                            lIdNotFound.setFont(new Font("Arial",Font.PLAIN,30));
                            
                            h2p2.add(lIdNotFound);
                            h2p2.setLayout(null);
                            lIdNotFound.setBounds(100,100,300,40);
                            
                            p4.add(h2p2);
                            h2p2.setBounds(1,121,1715,960);
                            h2p2.repaint();
                        }
                        
                    }
                    
                }
                catch(Exception e21){
                    System.out.println("Error e21");
                    e21.printStackTrace();
                }
                
            }
        }
        
    }
    class handle3 implements ActionListener{                                                    //        setting
    
        public void actionPerformed(ActionEvent e){
         
            if(p4!=null)
                remove(p4);
            
            p4 = new JPanel();
            add(p4);
            p4.setBounds(192,1,(11*1920)/10,1080);
            
            p4.setBorder(blackline);
            
            
            
            JButton h3b1,h3b2;
            h3b1 = new JButton("Password Setting");
            h3b1.setFont(new Font("Monako", Font.BOLD, 20));
            h3b1.addActionListener(new handle31());
            
            h3b2 = new JButton("Reset all");
            h3b2.setFont(new Font("Monako", Font.BOLD, 20));
            h3b2.addActionListener(new handle32());
            
            h2p1 = new JPanel();
            h2p2 = new JPanel();            
            h2p1.add(h3b1);
            h2p1.add(h3b2);
            
            h2p1.setLayout(null);
            h3b1.setBounds(300,50,200,40);
            h3b2.setBounds(650,50,200,40);
            
            p4.add(h2p1);
            p4.add(h2p2);
            h2p1.setBorder(blackline);
            h2p2.setBorder(blackline);
            
            p4.setLayout(null);
            h2p1.setBounds(1,1,1715,120);
            h2p2.setBounds(1,121,1715,960);
            
            
            
        }
        class handle31 implements ActionListener{                                          //                                          setting -> password setting
            
            JButton b313, b3111;
            JLabel l3111, l3112, l3113;
            JPasswordField pf3111, pf3112, pf3113;
         //   JPanel p5;
            public void actionPerformed(ActionEvent e){
               // Boolean b = false;
                if(h2p2!=null)
                    p4.remove(h2p2);
                h2p2 = new JPanel();
                p4.add(h2p2);
                p4.setLayout(null);
                h2p2.setBounds(1,121,1715,960);
                JButton b311;
                b311 = new JButton("Set Password");
                b311.setFont(new Font("Monako", Font.BOLD, 20));
                b311.addActionListener(new handle311());
             //   ResultSet rs;                                                                 //  commented while editing
                
         
                
                h2p2.add(b311);
                h2p2.setLayout(null);
                b311.setBounds(100,100,250,40);
                b313 = new JButton("Remove Password");
                b313.setFont(new Font("Monako", Font.BOLD, 20));
                b313.addActionListener(new handle313());
                if(haspass){
                    h2p2.add(b313);
                    b313.setBounds(100,160,250,40);                                                                                              //  here
                    
                    
                }
  //              b312.setBounds(100,200,250,40);
            }
            
            
            class handle311 implements ActionListener{                                                                  //  setting -> password setting -> set password
                
                
                Boolean b,chk=true;
                private char[] pas, pas1, pas2;
                String ps1, ps2, ps;
                ResultSet rs; 
                String strd_pass;
                
                
                public void actionPerformed(ActionEvent e){
                    if(l3114!=null)
                        if(p5!=null)
                            p5.remove(l3114);
                    
                    if(p5!=null)
                        h2p2.remove(p5);
                    
                    p5 = new JPanel();
                    h2p2.add(p5);
                    h2p2.setLayout(null);
                    p5.setBounds(450,300,600,400);
                    p5.setBorder(blackline);
                    
                    
                    try{
                        rs = st.executeQuery("SELECT Pass FROM pswrd");
                    }
                    catch(Exception ex31){
                    
                    }
                    
                    
                    l3111 = new JLabel("Enter Current Password: ");
                                    l3111.setFont(new Font("Monako", 1, 20));
                    
                    l3112 = new JLabel("Enter new Password: ");
                                    l3112.setFont(new Font("Monako", 1, 20));
                    l3113 = new JLabel("Confirm Password: ");
                                    l3113.setFont(new Font("Monako", 1, 20));
                                    
                    
                    
                                    
                    pf3111 = new JPasswordField();
                                            pf3111.setFont(new Font("Monako", 4, 20));
                    pf3112 = new JPasswordField();
                                            pf3112.setFont(new Font("Monako", 4, 20));
                    pf3113 = new JPasswordField();
                                            pf3113.setFont(new Font("Monako", 4, 20));
                                    
                    b3111 = new JButton("Enter");
                                    b3111.setFont(new Font("Monako", 1, 20));
                                    b3111.addActionListener(new handle31111());
                    
                    
                    l3114 = new JLabel("");
                            l3114.setFont(new Font("Monako", 4, 20));
               
                    
                    
                    p5.add(l3111);
                    p5.add(l3112);
                    p5.add(l3113);
                    p5.add(pf3111);
                    p5.add(pf3112);
                    p5.add(pf3113);
                    p5.add(b3111);
                    p5.add(l3114);
                    
                    
                    p5.setLayout(null);
                    try{
                        System.out.println("Entered try block");
                        
                        
                        if(haspass){
                            l3111.setBounds(30,50,250,40);
                            pf3111.setBounds(280,50,280,40);
                            System.out.println("p5 added");
                        }
                        
                    }
                    catch(Exception ex31){
                        ex31.printStackTrace();
                    }
                    
                    
                    l3112.setBounds(30,120,250,40);
                    pf3112.setBounds(280,120,280,40);
                    l3113.setBounds(30,190,250,40);
                    pf3113.setBounds(280,190,280,40);
                    b3111.setBounds(460,260,100,40);
                    l3114.setBounds(200,330,200,40);
                    
                    
                }
                
                class handle31111 implements ActionListener{                                    //  setting -> password setting -> set password -> Enter
                    public void actionPerformed(ActionEvent e){
                        try{
                             p5.remove(l3114);
                          //  h2p2.remove(p5);
                        }
                        catch(Exception e31111){
                            System.out.println("error 31111");
                            e31111.printStackTrace();
                        }
                       // p5 = new JPanel();
                        p5.add(l3114);
                        l3114.setBounds(200,330,250,40);
                        System.out.println("handle 31111");
                        if(haspass){
                            pas = pf3111.getPassword();
                            ps = String.valueOf(pas);
                            if(!password.equals(ps)){
                                chk=false;
                                l3114.setText("Enter Correct Password");
                                System.out.println("Correct password is: "+password);
                            }
                            else
                                chk = true;
                      
                        }
                        pas1 = pf3112.getPassword();
                        ps1 = String.valueOf(pas1);
                        
                        pas2 = pf3113.getPassword();
                        ps2 = String.valueOf(pas2);
                        
                        boolean b1 = false;
                        
                        if(ps1.equals(ps2)){
                            b1 = true;
                        }
                            
                        
                        if(chk){
                        if(b1){
                            
                            System.out.println("pas1 and pas2 are equal");
                            
                             //   for(int i =0; i<pas1.length; i++){
                                    String cmnd;
                                    System.out.println("cmnd set");
                                    if(haspass){
                                        cmnd = "DELETE FROM pswrd WHERE pass = '"+ps+"'";
                                        try{
                                            System.out.println("catch");
                                            st.executeUpdate(cmnd);                                                //  this can cause error.
                                            System.out.println("caught");
                                        }
                                        catch(Exception e31111){
                                            System.out.println("Can not delete stored pasword");
                                            e31111.printStackTrace();
                                        }
                                    }
                                  
                                    
                                    cmnd = "INSERT INTO pswrd VALUES ('"+ps1+"')";
                                    System.out.println(cmnd);
                                    System.out.println("Command ready");
                                    try{
                                        st.executeUpdate(cmnd);
                                        System.out.println("Cmnd passes to insert.");
                                    }
                                    catch(Exception e311111){
                                        e311111.printStackTrace();
                                    }
                              //  }
                          //      System.out.println("The password is : ");
                                try{
                                    rs1.close();
                                    System.out.println("2 rs closed");
                                }
                                catch(Exception e11111){
                                    System.out.println("2 rs can not close");
                                }
                                try{                                                                                      // to print stored password
                                    System.out.println("Creating resultset");
                                    rs1 = st.executeQuery("SELECT pass FROM pswrd");
                                    if(rs1.next()){
                                        System.out.println("Created Resultset");
                                        password = rs1.getString("pass");
                                        haspass = true;
                                    };
                                    
                                    
                                    
                                    System.out.println(password);
                                }
                                catch(Exception e311111){
                                    
                                    System.out.println("Error e311111");
                                    e311111.printStackTrace();
                                }
                                System.out.println("");                               
                            
                               
                               
                               
                               
                                                                                                                //  operation change passward
                                
                                                                                                                
                                try{
                                    if(haspass){
                                            System.out.println("haspass");
                                            l3114.setText("Password set.");
                                                          l3114.setFont(new Font("Monako", 1, 30));
                                            l3114.setBounds(200,170,250,40);
                                            h2p2.add(b313);
                                            b313.setBounds(100,160,250,40);
                                            System.out.println("haspass done");
                                    }
                                    
                                    l3111.setBounds(0,0,0,0);
                                    System.out.println("l3111 setbounds");
                                    l3112.setBounds(0,0,0,0);
                                    System.out.println("l3112 setbounds");
                                    l3113.setBounds(0,0,0,0);
                                    System.out.println("l3113 setbounds");
                                    pf3111.setBounds(0,0,0,0);
                                    System.out.println("pf3111 setbounds");
                                    pf3112.setBounds(0,0,0,0);
                                    System.out.println("pf3112 setbounds");
                                    pf3113.setBounds(0,0,0,0);
                                    System.out.println("pf3113 setbounds");
                                    b3111.setBounds(0,0,0,0);
                                    System.out.println("b3111 setbounds");
                                    
                                    p5.remove(l3111);
                                    System.out.println("l3111 removed");
                                    p5.remove(l3112);
                                    System.out.println("l3112 removed");
                                    p5.remove(l3113);
                                    System.out.println("l3113 removed");
                                    p5.remove(pf3111);
                                    System.out.println("pf3111 removed");
                                    p5.remove(pf3112);
                                    System.out.println("pf3112 removed");
                                    p5.remove(pf3113);
                                    System.out.println("pf3113 removed");
                                    p5.remove(b3111);
                                    System.out.println("b3111 removed");
                                    
                                }
                                catch(Exception ex3111){
                                    System.out.println("Can not open resultset in handle3111");
                                    ex3111.printStackTrace();
                                }
                                
                                
                            
                        }
                        else{
                            l3114.setText("Password did not matched");
                        }
                    }
                    }
                }
            } 
            
            class handle313 implements ActionListener{                            //  setting -> password setting -> remove password
                
                JLabel l313,l314;
                JPasswordField pf313;
                JButton b3131;
                
                
                public void actionPerformed(ActionEvent e){
                    
                    if(p5!=null){
                        h2p2.remove(p5);
                        System.out.println("p5 is not null");
                    }
                //    h2p2 = new JPanel();
                    
                    
                    p5 = new JPanel();
                    
                    l313 = new JLabel("Enter Password: ");
                                l313.setFont(new Font("Monako", Font.BOLD, 20));
                    pf313 = new JPasswordField();
                                pf313.setFont(new Font("Monako", Font.BOLD, 20));
                    b3131 = new JButton("Enter");
                                b3131.setFont(new Font("Monako", Font.BOLD, 20));
                                b3131.addActionListener(new handle3131());
                    l314 = new JLabel("");
                               l314.setFont(new Font("Monako", 4, 20));
                                
                    
                                
                    p5.add(l313);
                    p5.add(pf313);
                    p5.add(b3131);
                    p5.add(l314);
                    
                  //  h2p2.setLayout(null);
                    p5.setLayout(null);
                    l313.setBounds(50,100,200,40);
                    pf313.setBounds(240,100,310,40);
                    b3131.setBounds(250,200,100,40);
                    l314.setBounds(210,275,200,40);
                    
                   
                  
                  
                    p5.setBorder(blackline);
                    h2p2.add(p5);
                    h2p2.setLayout(null);
                    p5.setBounds(450,300,600,400);
                    
                    
                    
                }
                class handle3131 implements ActionListener{                                                             //  setting -> password setting -> remove password -> Enter
                    
                    JLabel l31311;
                    JButton b31311;
                    
                    public void actionPerformed(ActionEvent e){
                        
                        char[] pswrd = pf313.getPassword();
                        String paswrd = String.valueOf(pswrd);
                        if(paswrd.equals(password)){
                            if(p5!=null){
                                h2p2.remove(p5);
                                System.out.println("p5 is removed");
                            }
                            p5 = new JPanel();
                            l31311 = new JLabel("<html>Are you sure to remove password?<br/>Your saved data will not be secured.<html>");
                                            l31311.setFont(new Font("Monako", Font.BOLD, 20));
                            
                            b31311 = new JButton("Remove");
                                            b31311.setFont(new Font("Monako", Font.BOLD, 20));
                                            b31311.addActionListener(new handle313111());
                                            
                            p5.add(l31311);
                            p5.add(b31311);
                            p5.setLayout(null);
                            l31311.setBounds(100,100,350,80);
                            b31311.setBounds(200,250,200,40);
                            
                            p5.setBorder(blackline);
                            
                            h2p2.add(p5);
                            h2p2.setLayout(null);
                            p5.setBounds(450,300,600,400);
                            
                        }
                        else{
                            l314.setText("Incorrect password.");
                        }
                        
                        
                    }
                    
                    class handle313111 implements ActionListener{                   //          remove button of process removing password
                        
                        public void actionPerformed(ActionEvent e){
                            
                            
                            
                            
                            try{
                                rs1 = st.executeQuery("SELECT pass FROM pswrd");
                                rs1.next();
                                String temp ="DELETE FROM pswrd WHERE pass = '"+password+"'";
                                try{
                                    st.executeUpdate(temp);
                                    System.out.println("transfered");
                                    p5.remove(l314);
                                    System.out.println("l313 removed");
                                    p5.remove(l31311);
                                    System.out.println("l31311 removed");
                                    p5.remove(b31311);
                                    l314.setText("Password deleted");
                                    p5.add(l314);
                                    l314.setBounds(200,250,350,40);
                                    b313.setBounds(0,0,0,0);
                                    h2p2.remove(b313);
                                    password = null;
                                    haspass = false;
                                }
                                catch(Exception e33131111){
                                    System.out.println("Can not delete");
                                    e33131111.printStackTrace();
                                }
                            }
                            catch(Exception e313111){
                                System.out.println("Error e313111");
                                e313111.printStackTrace();
                            }
                            
                        }
                        
                    }
                }
            
            }
            
        } 
        class handle32 implements ActionListener{                               // setting -> reset all
            
            JLabel l321, l322;
            JPasswordField pf321;
            JButton b321;
            Boolean permission = true;
            
            public void actionPerformed(ActionEvent e){
              //  if(p5!=null)
                //    h2p2.remove(p5);
                if(h2p2!=null)
                    p4.remove(h2p2);
                h2p2 = new JPanel();
                p4.add(h2p2);
                h2p2.setBorder(blackline);
                p4.setLayout(null);
                h2p2.setBounds(1,121,1715,960);
                JButton b321;
             
                h2p2.setLayout(null);
             
                if(haspass){
                    l321 = new JLabel("Enter Password: ");
                                l321.setFont(new Font("Monako", Font.BOLD, 20));
                    pf321 = new JPasswordField();
                                pf321.setFont(new Font("Monako", Font.BOLD, 20));
                    b321 = new JButton("Enter");
                                b321.setFont(new Font("Monako", Font.BOLD, 20));
                                b321.addActionListener(new handle321());
                    l322 = new JLabel();
                                l322.setFont(new Font("Monako", 4, 20));
                                
                    h2p2.add(l321);
                    h2p2.add(pf321);
                    h2p2.add(b321);
                    h2p2.add(l322);
                    
                    l321.setBounds(470,300,200,40);
                    pf321.setBounds(640,300,300,40);
                    b321.setBounds(840,400,100,40);
                    l322.setBounds(610,490,200,40);
                    
                    
                                
                }
                
                else{
                    reset();
                }
             
            }
            
            class handle321 implements ActionListener{                      //        entered password to reset all
                
                public void actionPerformed(ActionEvent e321){
                    
                    char[] pas = pf321.getPassword();
                    String pass = String.valueOf(pas);
                    if(password.equals(pass)){
                        reset();
                    }
                    else{
                        l322.setText("Incorrect password");
                    }
                    
                }
                
            }
            
            void reset(){                               //                         method to reset all
                
                if(h2p2!=null){
                    p4.remove(h2p2);
                }
                h2p2 = new JPanel();
                p4.add(h2p2);
                h2p2.setBorder(blackline);
                p4.setLayout(null);
                h2p2.setBounds(1,121,1715,960);
                
                l321 = new JLabel("Reset All? All data will be removed! ");
                l321.setFont(new Font("Monako", 4,25));
                b321 = new JButton("Reset");
                b321.setFont(new Font("Monako", Font.BOLD, 20));
                            b321.addActionListener(new resethandle());
                
                h2p2.add(l321);
                h2p2.add(b321);
                h2p2.setLayout(null);
                l321.setBounds(350,200,450,40);
                b321.setBounds(500,300,100,40);
                
                
            }
            
            class resethandle implements ActionListener{                                //    reset button
                
                public void actionPerformed(ActionEvent e){
                    
                    try{
                        st.executeUpdate("DROP DATABASE polok");
                        password = null;
                        haspass = false;
                    }
                    catch(Exception exResetHandle){
                        System.out.println("No database is available");
                    }
                    
                    try{
                        
                        con = DriverManager.getConnection("jdbc:mysql://localhost/","root","");
                        st = con.createStatement();
                        st.executeUpdate("CREATE DATABASE polok");
                        st.close();
                        con.close();
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/polok","root","");
                        st = con.createStatement();
                        System.out.println("Database Created");
                        st.executeUpdate("CREATE TABLE pswrd (pass VARCHAR(30))");
                        st.executeUpdate("CREATE TABLE number (Nmbr INTEGER)");
                        System.out.println("Created table number");
                        st.executeUpdate("INSERT INTO number VALUES (0)");
                        System.out.println("Inserted value into number");
                        st.executeUpdate("CREATE TABLE InfoTable (BatchName VARCHAR(50) not NULL, BasicId VARCHAR(6) not NULL, StudentNumber INTEGER, PRIMARY KEY(BatchName))");
                        System.out.println("Created InfoTable");
                        
                    }
                    catch(Exception exResetHandle2){
                        System.out.println("Error exResetHandle2");
                        exResetHandle2.printStackTrace();
                    }
                    
                }
                
            }
        } 
    }
    
    
}
