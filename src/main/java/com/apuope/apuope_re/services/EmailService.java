package com.apuope.apuope_re.services;

import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.jooq.tables.records.TokenRecord;
import com.apuope.apuope_re.jooq.tables.records.UsersRecord;
import com.apuope.apuope_re.repositories.UserRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Optional;
import java.util.UUID;

import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailService {
    @Value("${api.base.url}")
    private String appUrl;
    @Value("${spring.mail.username}")
    private String apuopeMail;
    @Autowired
    private JavaMailSender mailSender;

    private final DSLContext dslContext;
    private final UserRepository userRepository;

    public EmailService(DSLContext dslContext, UserRepository userRepository) {
        this.dslContext = dslContext;
        this.userRepository = userRepository;
    }

    public ResponseData<String> sendVerification(String to) throws MessagingException {
        Optional<UsersRecord> userByEmail = userRepository.findByEmail(to, this.dslContext);

        if (userByEmail.isPresent()) {
            UUID uuid = userByEmail.get().getUuid();

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessage = new MimeMessageHelper(message, true);

            mimeMessage.setTo(to);
            mimeMessage.setSubject("Verify your APUOPE-RE account");
            String htmlContent = "Thank you for creating an account with APUOPE-RE learning " +
                    "assistant!<br>" + "Please verify your account to complete your registration" + ".<br>" + "Simply click the link below to " +
                    "verify your account:<br><br>" + "<a" + " href=\"" + appUrl + "login?token=" + uuid + "\">Verify Your " + "Account</a><br><br>" +
                    "If following URL into your browser: " + "http://130.230.52.194:443/login?token=" + uuid + "<br><br>" +
                    "If you did not sign up for an account with us, please ignore " + "this email" + ".<br><br>" + "Thank you,<br>" +
                    "APUOPE-RE Team";

            mimeMessage.setText(htmlContent, true);
            mimeMessage.setFrom(apuopeMail); // Optional, depending on the setup

            mailSender.send(message);

            return new ResponseData<>(true, "Mail sent successfully!");
        } else {
            return new ResponseData<>(false,"No user with given email found from database.");
        }
    }

    public ResponseData<String> sendResetPasswordLink(TokenRecord token,
    String email) throws MessagingException {
        try{
            UUID uuid = token.getUuid();

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessage = new MimeMessageHelper(message, true);

            mimeMessage.setTo(email);
            mimeMessage.setSubject("Reset your APUOPE-RE password");
            String htmlContent = "We received a request to reset the password for your account.<br><br>" +
                    "If you made this request, please click the link below to reset your password:<br><br>" +
                    "<a" + " href=\"" + appUrl + "reset-password?token=" + uuid + "\">Reset password</a><br><br>" +
                    "If you did not request a password reset, please ignore this email.<br><br>" +
                    "For your security, this link will expire in 30 minutes.<br><br>" +
                    "Thank you,<br>APUOPE-RE Team";
            mimeMessage.setText(htmlContent, true);
            mimeMessage.setFrom(apuopeMail); // Optional, depending on the setup

            mailSender.send(message);

            return new ResponseData<>(true, "Mail sent successfully!");
        } catch (Exception e) {
            return new ResponseData<>(false, "Error when sending password reset mail.");
        }

    }
}
