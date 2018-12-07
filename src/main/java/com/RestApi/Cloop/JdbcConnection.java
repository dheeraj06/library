package com.RestApi.Cloop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class JdbcConnection 
{
	String type;
	int count;
	int finedue;
	String bdate;
	int bdur;
	String breturn;
	int eday;
	int fdues;
	 static Connection con;
     public JdbcConnection()
     {
    	 try
    	 {
    	 Class.forName("com.mysql.jdbc.Driver");  
    	 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","123456");
    	 }
    	 catch(Exception e)
    	 {
    		 System.out.println(e.toString());
    	 }
     }
     public String addBook(String bookTitle,String bookAuthor,String category,String dateAdded,String bookStatus)
     {
    	 try
    	 {
    	 PreparedStatement p=con.prepareStatement("INSERT INTO book(title,author,category,added,status) VALUES (?,?,?,?,?);");
    	 p.setString(1,bookTitle);
    	 p.setString(2,bookAuthor);
    	 p.setString(3,category);
    	 p.setString(4,dateAdded);
    	 p.setString(5,bookStatus);
    	 p.executeUpdate();
    	 con.close();
    	 }
    	 catch(Exception e)
    	 {
    		 System.out.println(e.toString());
    	 }
    	 return "ROW ADDED SUCCESFULLY";
     }
     public String getbook(int uid,int bid,String btitle,int bdur)
     {
    	 String s1=null;
    	 try
    	 {
    	 PreparedStatement ps=con.prepareStatement("select * from user where id=?");
    	 ps.setInt(1,uid);
    	 ResultSet rs=ps.executeQuery();
    	  LocalDateTime dur=LocalDateTime.now();
    	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    	  String bcurr=dur.toString();
    	 if(rs.next()==false)
    	 {
    		 return "Please enter valid user id";
    	 }
    	 while(rs.next()==true){
	    		type=rs.getString(4);
	    	 }
    	s1=bookIssue(uid,type); 
    	     addIssueBook(uid,bid,btitle,bcurr,bdur);
    	 }
    	 catch(Exception e)
    	 {
    		 System.out.println(e.toString());
    	 }
    	 return s1;
     }
     public String bookIssue(int u_id,String type)
     {
    	 String s;
    	 try
    	 {
    	 PreparedStatement p=con.prepareStatement("select count(book_title) from bookissue groupby user_id having user_id=?");
    	 
    	 
    	 ResultSet rs=p.executeQuery();
    	 if(rs.next()==false)
    	 {
    		 return "take your book";
    	 }
    	 while(rs.next()==true){
	    		 count=rs.getInt(1);
	    		 }
	    if(type=="student" && count>=5){
	        	return "You already taken 5 books";
	        	}
	    else if(type=="teacher" && count>=3){
	        	return "you already taken 3 books";
	        	}
	    else{
	          s=fine(u_id);
	        	}
	    }
    	 catch(Exception e)
    	 {
    		 System.out.println(e.toString());
    	 }
    	 finally {
    		 try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
    	 }
    	 return "Take your book please return on time";
     }
     public String fine(int u_id)
     {
    	 try
    	 {
    		 PreparedStatement p=con.prepareStatement("select sum(finedue) from fine_table where user_id=?");
    		 ResultSet rs=p.executeQuery();
    		 if(rs.next()==false)
    		 {
    			 return "Take your book";
    		 }
    		 while(rs.next()==true)
    		 {
    			 finedue=rs.getInt(1);
    		 }
    		 
    	 }
    	 catch(Exception e)
    	 {
    		 System.out.println(e.toString());
    	 }
    	 return "Please pay the Fine first";
     }
     public void addIssueBook(int uid,int bid,String btitle,String bdate,int bduration)
     {
    	 try {
			PreparedStatement pp=con.prepareStatement("insert into bankissue(user_id,book_id,book_title,dateissue,duration) values(?,?,?,?,?)");
			pp.setInt(1,uid);
			pp.setInt(2,bid);
			pp.setString(3,btitle);
			pp.setString(4,bdate);
			pp.setInt(5,bduration);
			pp.executeUpdate();
		} 
    	 catch (Exception e) {
			System.out.println(e.toString());
		}
     }
     public String returnBook(int uid,int bid)
     {
    	 String s4=null;
    	 s4=checkBookissue(uid,bid);
         return s4;
     }
     public String checkBookissue(int uid,int bid) 
     {
    	 String s3=null;
    	 try {
    	 PreparedStatement pp=con.prepareStatement("select * from bookisuue where book_id=?");
    	 pp.setInt(1,bid);
    	 ResultSet rs=pp.executeQuery();
    	 while(rs.next()==true)
    	 {
    		 bdate=rs.getString(5);
    		 bdur=rs.getInt(6);
    		 
    	 }
    	s3=checkDate(bdate,bdur,uid,bid);
    	 }
    	 catch(Exception e){
    		 System.out.println(e.toString());
    	 }
    	 return s3;
    	 }
     public String checkDate(String bdate,int bdur,int uid,int bid)
     {
    	 String s2=null;
            try {
			PreparedStatement pp=con.prepareStatement("SELECT CURDATE()");
			ResultSet rs=pp.executeQuery();
			while(rs.next()==true)
			{
				breturn=rs.getString(1);
			}
			String dateStart = bdate;
			String dateStop = breturn;
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date d1 = null;
			Date d2 = null;
           
				d1 = format.parse(dateStart);
				d2 = format.parse(dateStop);
				long diff = d2.getTime() - d1.getTime();
				int diffDays =(int) diff / (24 * 60 * 60 * 1000);
				
				if(diffDays>bdur)
				{
					eday=diffDays-bdur;
					fdues=eday*10;
					s2=addFine(uid,bid,eday,fdues);
				}
				else {
				 return "Thanks for returning on time";
				}
            }
    	 catch (Exception e) {
			System.out.println(e.toString());
		}
            return s2;
     }
    	 public String addFine(int uid,int bid,int edays,int fdue)
    	 {
    		 try {
    		 PreparedStatement pp=con.prepareStatement("insert into fine_table(user_id,book_id,extraday,finedue) values (?,?,?,?)");
    		pp.setInt(2,uid);
    		pp.setInt(3,bid);
    		pp.setInt(4,edays);
    		pp.setInt(5,fdue);
    		pp.executeUpdate();
    		 }
    		 catch(Exception e)
    		 {
    			 System.out.println(e.toString());    		 
    			 }
    		 return "Pay the fine";
    	 }
     
}
