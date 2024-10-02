package com.apuope.apuope_re.services;

import com.apuope.apuope_re.dto.EmailDetails;
import org.springframework.stereotype.Service;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

@Service
public class EmailService {

    public void sendVerification() {
        // Recipient's email
        String to = "address-here@tuni.fi"; // Replace with recipient's email

        // Sender's email (Gmail)
        String from = "address-here@gmail.com"; // Your Gmail address
        final String username = "address-here@gmail.com"; // Your Gmail username
        final String appPassword = "app-password-here"; // App password if using 2FA

        // Gmail SMTP server details
        String host = "smtp.gmail.com";

        // Set properties for the SMTP server
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587"); // Use port 587 for TLS
        properties.put("mail.smtp.ssl.trust", host); // Trust the Gmail SMTP server

        // Get a Session object and authenticate using the app password
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, appPassword); // App Password
            }
        });

        try {
            // Create the message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from)); // Sender's email
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); // Recipient's email
            message.setSubject("Test Email from Java Program"); // Email subject
            message.setText("This is a test email sent using Jakarta Mail API via Gmail SMTP server."); // Email body

            // Send the message
            Transport.send(message);
            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
