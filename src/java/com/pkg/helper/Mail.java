package com.pkg.helper;


import java.util.Random;
import java.util.Properties;
import java.util.stream.IntStream;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
    //sending OTP
    private String from="ewastemanagement14@gmail.com";
    private String password="Neeraj@123";
    public String getOtp(){
          int temp=new Random().nextInt(10000);
          String otp="";
          otp=Integer.toString(temp);
          System.out.println(otp+" is this");
          return otp;
    }
    
    public void sendOTP(String to,String message){
        String host="smtp.gmail.com";
        //geting system properties
        Properties sysProperties=System.getProperties();
        //setting host
        sysProperties.put("mail.smtp.host", host);
        sysProperties.put("mail.smtp.port", "465");
        sysProperties.put("mail.smtp.ssl.enable", "true");
        sysProperties.put("mail.smtp.tls.enable", "true");
        sysProperties.put("mail.smtp.auth", "true");
        
        //getting session
       Session mailSession=Session.getInstance(sysProperties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from,password); //To change body of generated methods, choose Tools | Templates.
            }
                         
       });
       mailSession.setDebug(true);
       
       //composing the message
       MimeMessage msg= new MimeMessage(mailSession);
       try{
           //set from 
           msg.setFrom(from);
           //set reciepiend
           msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
           
           //adding subject to message
		msg.setSubject("OTP from ewaste");
            //set mesaage
            msg.setText(message);
            
            //send the otp to user email
            Transport.send(msg);
            System.out.println("Otp has been sent send succesfully ");
       }catch(Exception e){
           e.printStackTrace();
       }
        
    }
    //function to compare otp
    public boolean compOTP(String sysOTP,String userOTP){
        if(sysOTP.equals(userOTP)) return true;
        else return false;
    }
    //Driver code
    public static void main(String args[]){
        new Mail().sendOTP("mohit.patidar2400@gmail.com", new Mail().getOtp());
    }
}

/*
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class Mail {
    
     public String getOtp(){
          int temp=new Random().nextInt(10000);
          String otp="";
          otp=Integer.toString(temp);
          System.out.println(otp+" is this");
          return otp;
    }
    
    public boolean compOTP(String sysOTP,String userOTP){
        if(sysOTP.equals(userOTP)) return true;
        else return false;
    }
    
    public static void sendOTP(String recipientEmail, String otp) {
        final String senderEmail = "mohit.patidar2400@gmail.com.com"; // Replace with your sender email address
        final String senderPassword = "Mohit@2400"; // Replace with your sender email password or app password
        String host="smtp.mail.com";
        // Configure SMTP properties
        Properties props = new Properties();
        
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.startsls.enable", "true");
        props.put("mail.smtp.host", "smtp.mail.com"); // Replace with your SMTP server address
        props.put("mail.smtp.port", "465"); // Replace with your SMTP server port
        
        // Create a Session with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        
        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);
            
            // Set the sender, recipient, subject, and content
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Email Verification OTP from Ewaste Website");
            message.setText("Your OTP for email verification is: " + otp);
            
            // Send the message
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
*/
