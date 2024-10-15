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
    public static String TEST_EMAIL = "test@success.com";
    public static String TEST_PASSWORD = "password123";
    @Autowired
    DSLContext dslContext;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LogInService logInService;

    @BeforeEach
    void setUp() {
        TestDataGenerator.insertTestUser(dslContext, TEST_EMAIL, TEST_PASSWORD);
    }

    @AfterEach
    void tearDown() {
        TestDataGenerator.deleteTestUsers(dslContext);
    }

    @Test
    void testLoginSuccess() {
        UserCredentials userCredentials = new UserCredentials(TEST_EMAIL, TEST_PASSWORD);
        ResponseData<String> result = logInService.validateUser(userCredentials);
        System.out.println("Login success: " + result.getSuccess());
        System.out.println("Login data: " + result.getData());
        assertTrue(result.getSuccess(), "Login successful");
    }

    @Test
    void testLoginFailure() {
        UserCredentials userCredentials = new UserCredentials("test@failure.com", "password123");
        ResponseData<String>  result = logInService.validateUser(userCredentials);
        assertFalse(result.getSuccess(), "Login unsuccessful");
    }
}