package springjwt.security;


import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.MediaType;
import springjwt.util.AbstractControllerTest;


import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthenticationControllerTest extends AbstractControllerTest {

    @Test
    public void successfulAuthenticationWithUser() throws Exception {
        getMockMvc().perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"password\": \"pa55w0[d\", \"username\": \"username\"}"))
                .andExpect(status().isOk());
    }

    @Test
    @Ignore
    public void successfulAuthenticationWithAdmin() throws Exception {
        getMockMvc().perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"password\": \"admin\", \"username\": \"admin\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("id_token")));
    }

    @Test
    public void unsuccessfulAuthenticationWithWrongPassword() throws Exception {
        getMockMvc().perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"password\": \"aaaa\", \"username\": \"baaaaaaa\"}"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(not(containsString("id_token"))));
    }

    @Test
    public void unsuccessfulAuthenticationWithNotExistingUser() throws Exception {
        getMockMvc().perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"password\": \"anypassword\", \"username\": \"notexisting\"}"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(not(containsString("id_token"))));
    }
}
