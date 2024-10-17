package com.apuope.apuope_re.services;

import com.apuope.apuope_re.jooq.tables.records.UsersRecord;
import com.apuope.apuope_re.repositories.UserRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Optional;
import java.util.UUID;

import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    private final DSLContext dslContext;
    private final UserRepository userRepository;

    public EmailService(DSLContext dslContext, UserRepository userRepository) {
        this.dslContext = dslContext;
        this.userRepository = userRepository;
    }

    public void sendVerification(String to) throws MessagingException {
        Optional<UsersRecord> userByEmail = userRepository.findByEmail(to, this.dslContext);

        if (userByEmail.isPresent()) {
            UUID uuid = userByEmail.get().getUuid();
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessage = new MimeMessageHelper(message, true);

            mimeMessage.setTo(to);
            mimeMessage.setSubject("Verify your APUOPE-RE account");
            String htmlContent = "Thank you for creating an account with APUOPE-RE learning " +
                    "assistant!<br>" + "Please verify your account to complete your registration" + ".<br>" + "Simply click the link below to verify your account:<br><br>" + "<a" + " href=\"http://localhost:3000/login?token=" + uuid + "\">Verify Your " + "Account</a><br><br>" + "If the link above does not work, copy and paste the " + "following URL into your browser: " + "http://localhost:3000/login?token=" + uuid + "<br><br>" + "If you did not sign up for an account with us, please ignore " + "this email" + ".<br><br>" + "Thank you,<br>" + "The APUOPE-RE Team<br>";

            mimeMessage.setText(htmlContent, true);
            mimeMessage.setFrom("mail.apuopere@gmail.com"); // Optional, depending on the setup

            mailSender.send(message);

            System.out.println("Mail sent successfully!");
        } else {
            System.out.println("No user with given email found from database.");
        }

    }
}
