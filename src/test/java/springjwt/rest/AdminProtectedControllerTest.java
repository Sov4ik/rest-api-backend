package springjwt.rest;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.MediaType;
import springjwt.util.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static springjwt.util.LoginUtils.getTokenForLogin;


public class AdminProtectedControllerTest extends AbstractControllerTest {

    @Test
    @Ignore
    public void getAdminProtectedEndpointForUser() throws Exception {
        final String token = getTokenForLogin("username", "pa55w0[d", getMockMvc());

        getMockMvc().perform(get("/api/test/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @Ignore
    public void checkAccessForUser() throws Exception {
        final String token = getTokenForLogin("username", "pa55w0[d", getMockMvc());

        getMockMvc().perform(get("/api/test/user")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    @Ignore
    public void getAdminProtectedEndpointForAdmin() throws Exception {
        final String token = getTokenForLogin("admin", "admin", getMockMvc());

        getMockMvc().perform(get("/api/test/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

    @Test
    @Ignore
    public void getAdminProtectedEndpointForAnonymous() throws Exception {
        getMockMvc().perform(get("/api/test/admin")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }
}
