package com.apuope.apuope_re.services;

import com.apuope.apuope_re.dto.RegistrationData;
import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.dto.UserCredentials;
import com.apuope.apuope_re.repositories.UserRepository;
import jakarta.mail.MessagingException;
import org.jooq.DSLContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class RegistrationServiceTest {
    public static String TEST_USERNAME = "testuser";
    public static String TEST_USERNAME2 = "testuser2";
    public static String TEST_EMAIL = "test@success.com";
    public static String TEST_EMAIL2 = "test2@success.com";
    public static String TEST_EMAIL_NON_EXISTING = "test@non-existing.com";
    public static String TEST_PASSWORD = "password123";
    public static boolean VERIFIED1 = true;

    @Autowired
    DSLContext dslContext;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        TestDataGenerator.insertTestUser(dslContext, TEST_EMAIL, TEST_USERNAME, TEST_PASSWORD,
                VERIFIED1);
    }

    @AfterEach
    void tearDown() {
        TestDataGenerator.deleteTestUsers(dslContext, TEST_EMAIL);
        TestDataGenerator.deleteTestUsers(dslContext, TEST_EMAIL2);
    }

    @Test
    void testEmailAlreadyExists() {
        RegistrationData registrationData = new RegistrationData(TEST_EMAIL, TEST_USERNAME,
                TEST_PASSWORD);
        ResponseData<String> result = registrationService.registerUser(registrationData);
        assertFalse(result.getSuccess(), "User already exist for given email.");
    }

    @Test
    void testUsernameAlreadyExists() {
        RegistrationData registrationData = new RegistrationData(TEST_EMAIL2, TEST_USERNAME,
                TEST_PASSWORD);
        ResponseData<String> result = registrationService.registerUser(registrationData);
        assertFalse(result.getSuccess(), "User already exist for given username.");
    }

    @Test
    void testRegistrationSuccessful() {
        RegistrationData registrationData = new RegistrationData(TEST_EMAIL2, TEST_USERNAME2,
                TEST_PASSWORD);
        ResponseData<String> result = registrationService.registerUser(registrationData);
        assertTrue(result.getSuccess(), "User added successfully.");
    }

    @Test
    void testEmailFailure() throws MessagingException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStream));
        emailService.sendVerification(TEST_EMAIL_NON_EXISTING);

        assertEquals("No user with given email found from database.\n", outputStream.toString());
    }

    @Test
    void testEmailSuccessful() throws MessagingException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStream));
        emailService.sendVerification(TEST_EMAIL);

        assertEquals("Mail sent successfully!\n", outputStream.toString());
    }
}
