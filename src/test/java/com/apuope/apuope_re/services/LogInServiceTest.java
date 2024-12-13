package com.apuope.apuope_re.services;

import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.repositories.UserRepository;
import com.apuope.apuope_re.dto.UserCredentials;
import org.jooq.DSLContext;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class LogInServiceTest {
    public static String TEST_USERNAME = "testuser";
    public static String TEST_EMAIL1 = "test@success.com";
    public static String TEST_EMAIL2 = "test@failure.com";
    public static String TEST_PASSWORD = "password123";
    public static String TEST_PASSWORD_HASHED = PasswordHashService.hashPassword("password123");
    public static boolean VERIFIED = true;

    @Autowired
    DSLContext dslContext;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LogInService logInService;

    @BeforeEach
    void setUp() {
        TestDataGenerator.insertTestUser(dslContext, TEST_EMAIL1, TEST_USERNAME, TEST_PASSWORD_HASHED,
                VERIFIED);
    }

    @AfterEach
    void tearDown() {
        TestDataGenerator.deleteTestUsers(dslContext, TEST_EMAIL1);
    }

    @Test
    void testLoginSuccess() {
        UserCredentials userCredentials = new UserCredentials(TEST_EMAIL1, TEST_PASSWORD);
        ResponseData<Object> result = logInService.validateUser(userCredentials);
        assertTrue(result.getSuccess());
    }

    @Test
    void testLoginFailure() {
        UserCredentials userCredentials = new UserCredentials(TEST_EMAIL2, TEST_PASSWORD);
        ResponseData<Object>  result = logInService.validateUser(userCredentials);
        assertFalse(result.getSuccess(), "Logging in failed. Please, try again.");
    }
}