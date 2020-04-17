package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerE2ETest {

    @LocalServerPort
    private int port;

    TestRestTemplate testRestTemplate;
    User fakeUser = new User();

    UserRepository userRepository;

    @Autowired
    public ControllerE2ETest(UserRepository userRepository, TestRestTemplate testRestTemplate) {
        this.userRepository = userRepository;
        this.testRestTemplate = testRestTemplate;
    }

    @BeforeEach
    void init() {
        Optional<User> user = userRepository.findUserByEmail("witaszek99@wp.pl");
        user.ifPresent(value -> userRepository.delete(value));
        fakeUser.setPassword("retsad123");
    }

    @AfterEach
    void tearDown() {
        Optional<User> user = userRepository.findUserByEmail("witaszek99@wp.pl");
        user.ifPresent(value -> userRepository.delete(value));
    }

    @Test
    public void shouldThrowThisUserExists() {
        UserModel userModel = new UserModel();
        userModel.setEmail("witaszek98@wp.pl");
        userModel.setPassword("retsad123");
        String odp = testRestTemplate.postForObject("http://localhost:" + port + "/register", userModel, String.class);
        assertTrue(odp.contains("exists"));
    }

    @Test
    public void shouldAddUser() {
        UserModel userModel = new UserModel();
        userModel.setEmail("witaszek99@wp.pl");
        userModel.setPassword("retsad123");
        testRestTemplate.postForObject("http://localhost:" + port + "/register", userModel, String.class);
        Optional<User> optionalUser = userRepository.findUserByEmail("witaszek99@wp.pl");
        assertTrue(optionalUser.isPresent());
        User user = optionalUser.orElse(fakeUser);
        assertNotEquals("retsad123", user.getPassword());
    }

    @Test
    public void shouldLogin() {
        String ans = testRestTemplate
                .withBasicAuth("witaszek98@wp.pl", "retsad123")
                .getForObject("http://localhost:" + port + "/secured", String.class);
        assertEquals("DUPA", ans);
    }

    @Test
    public void shouldNotAllow(){
        String ans = testRestTemplate
                .getForObject("http://localhost:" + port + "/secured", String.class);
        assertTrue(ans.contains("Unauthorized"));
    }

    @Test
    public void shouldReturn401OnBadCredentials(){
        String ans = testRestTemplate
                .withBasicAuth("wiasdasdas","asasdasda")
                .getForObject("http://localhost:" + port + "/secured", String.class);
       assertNull(ans);
    }
}
