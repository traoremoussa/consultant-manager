package pwdecrypt;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordEncryptTest {

    @Test
    void test() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // assertEquals("$2a$10$1YuGJeaKn5PDxIP7lULQaujY0v4cGsrXuPuEpoozhMsdO1ZOfmKWm", encoder.encode("1234"));

        String rawPassword = "1234";
        String hashedPassword = encoder.encode(rawPassword);

        assertTrue(encoder.matches(rawPassword, hashedPassword));

    }
}
