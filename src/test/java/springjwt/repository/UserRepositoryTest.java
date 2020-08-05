package springjwt.repository;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import springjwt.models.User;
import springjwt.util.AbstractControllerTest;

import java.util.Optional;

public class UserRepositoryTest extends AbstractControllerTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder encoder;

    private static final String USERNAME = "qwerty";

    private static final String NOT_CORRECT_USERNAME = "qwerty2";

    @Test
    @Ignore
    public void successfulFindUserByUsername() {

        final User ENABLED_USER =
                new User("qwerty", "qwerty@qwerty.ru", encoder.encode("qwerty"));

        Mockito.when(userRepository.findByUsername(ENABLED_USER.getUsername()))
                .thenReturn(Optional.of(ENABLED_USER));

        Assert.assertTrue(userRepository.findByUsername(USERNAME).isPresent());
        Assert.assertEquals(userRepository.findByUsername(USERNAME).get().getUsername(), USERNAME);
    }

    @Test
    @Ignore
    public void unsuccessfulFindUserByUsername() {

        final User ENABLED_USER =
                new User("qwerty2", "qwerty@qwerty.ru", encoder.encode("qwerty"));

        Mockito.when(userRepository.findByUsername(ENABLED_USER.getUsername()))
                .thenReturn(Optional.of(ENABLED_USER));

        Assert.assertTrue(userRepository.findByUsername(NOT_CORRECT_USERNAME).isPresent());
        Assert.assertNotEquals(userRepository.findByUsername(NOT_CORRECT_USERNAME).get().getUsername(), USERNAME);
    }

}
