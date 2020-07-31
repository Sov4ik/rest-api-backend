package springjwt.security;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import springjwt.util.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ValidationTest extends AbstractControllerTest {

    @Test
    @Transactional
    @Rollback()
    public void registrationWithEmptyUsernameAndPassword() throws Exception {
        getMockMvc().perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"\", \"password\": \"\", \"email\": \"email@email.ru\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    @Rollback()
    public void registrationWithInvalidUsername() throws Exception {
        getMockMvc().perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"u53rnA'>3\", \"password\": \"password\", \"email\": \"email@email.ru\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Ignore
    public void registrationWithInvalidPassword() throws Exception {
        getMockMvc().perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"username\", \"password\": \"pa55w0[d\", \"email\": \"email@email.ru\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void loginWithEmptyUsernameAndPassword() throws Exception {
        getMockMvc().perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"\", \"password\": \"\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void loginWithInvalidUsername() throws Exception {
        getMockMvc().perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"kjhasd'}\", \"password\": \"\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void loginWithInvalidPassword() throws Exception {
        getMockMvc().perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"\", \"password\": \"{123}\"}"))
                .andExpect(status().isBadRequest());
    }
}
