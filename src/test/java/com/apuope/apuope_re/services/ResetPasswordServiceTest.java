package com.apuope.apuope_re.services;

import com.apuope.apuope_re.dto.ResetPasswordData;
import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.jooq.tables.records.TokenRecord;
import com.apuope.apuope_re.jooq.tables.records.UsersRecord;
import com.apuope.apuope_re.repositories.TokenRepository;
import com.apuope.apuope_re.repositories.UserRepository;
import jakarta.mail.MessagingException;
import org.jooq.DSLContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class ResetPasswordServiceTest {
    public static String TEST_USERNAME = "testuser";
    public static String TEST_EMAIL1 = "test@success.com";
    public static String TEST_EMAIL2 = "test2@success.com";
    public static String TEST_PASSWORD1 = "password123";
    public static String TEST_PASSWORD_HASHED1 = PasswordHashService.hashPassword(TEST_PASSWORD1);
    public static String TEST_PASSWORD2 = "321password";
    public static UUID TEST_UUID1 = UUID.randomUUID();
    public static UUID TEST_UUID2 = UUID.randomUUID();
    public static UUID TEST_UUID3 = UUID.randomUUID();
    public static LocalDateTime TEST_EXPIRATION_TIME_UNEXPIRED = LocalDateTime.now(ZoneId.of("Europe/Helsinki")).plusMinutes(30);
    public static LocalDateTime TEST_EXPIRATION_TIME_EXPIRED = LocalDateTime.now(ZoneId.of("Europe/Helsinki")).minusMinutes(30);

    @Autowired
    DSLContext dslContext;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResetPasswordService resetPasswordService;
    @Autowired
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        TestDataGenerator.insertTestUser(dslContext, TEST_EMAIL1, TEST_USERNAME, TEST_PASSWORD_HASHED1,
                true);
        UsersRecord user = TestDataGenerator.findByEmail(dslContext, TEST_EMAIL1);
        // Insert unexpired token
        TestDataGenerator.insertTestToken(dslContext, user.getId(), TEST_UUID1, TEST_EXPIRATION_TIME_UNEXPIRED,
                true);
        // Insert expired token
        TestDataGenerator.insertTestToken(dslContext, user.getId(), TEST_UUID2, TEST_EXPIRATION_TIME_EXPIRED,
                true);
        // Insert invalid token
        TestDataGenerator.insertTestToken(dslContext, user.getId(), TEST_UUID3, TEST_EXPIRATION_TIME_UNEXPIRED,
                false);
    }

    @AfterEach
    void tearDown() {
        TestDataGenerator.deleteTestUsers(dslContext, TEST_EMAIL1);
        TestDataGenerator.deleteTestUsers(dslContext, TEST_EMAIL2);
    }

    @Test
    void testGenerateEmailTokenSuccess() {
        Optional<UsersRecord> user = userRepository.findByEmail(TEST_EMAIL1, dslContext);
        if (user.isPresent()) {
            TokenRecord result = resetPasswordService.generateEmailToken(TEST_EMAIL1);
            assertEquals(user.get().getId(), result.getAccountId());
        } else{
            Assertions.fail("User should be found from db.");
        }
    }

    @Test
    void testGenerateEmailTokenFailure() {
        TokenRecord result = resetPasswordService.generateEmailToken(TEST_EMAIL2);
        assertNull(result);
    }

    @Test
    void testSendResetPasswordLinkSuccess() throws MessagingException {
        UsersRecord user = TestDataGenerator.findByEmail(dslContext, TEST_EMAIL1);
        TokenRecord token = tokenRepository.findByAccountId(user.getId(), dslContext);

        ResponseData<String> response = emailService.sendResetPasswordLink(token, TEST_EMAIL1);
        assertTrue(response.getSuccess(), "Mail sent successfully!");
    }

    @Test
    void testResetPasswordTokenValidAndExpired() {
        TokenRecord token = TestDataGenerator.fetchExpiredToken(dslContext);
        ResetPasswordData data = new ResetPasswordData(token.getUuid(), TEST_PASSWORD1);

        ResponseData<String> response = resetPasswordService.resetPassword(data);
        assertFalse(response.getSuccess(), "Reset password link is expired.");
    }

    @Test
    void testResetPasswordTokenInvalidAndUnexpired() {
        TokenRecord token = TestDataGenerator.fetchInvalidToken(dslContext);
        ResetPasswordData data = new ResetPasswordData(token.getUuid(), TEST_PASSWORD1);

        ResponseData<String> response = resetPasswordService.resetPassword(data);
        assertFalse(response.getSuccess(), "This reset password request has already been completed. Request a new link if needed.");
    }

    @Test
    void testResetPasswordTokenValidAndUnexpiredSuccess() {
        TokenRecord token = TestDataGenerator.fetchUnexpiredAndValidToken(dslContext);
        ResetPasswordData data = new ResetPasswordData(token.getUuid(), TEST_PASSWORD2);

        ResponseData<String> response = resetPasswordService.resetPassword(data);
        assertTrue(response.getSuccess(), "Password reset successfully.");
    }
}