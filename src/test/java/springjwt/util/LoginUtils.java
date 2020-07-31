package springjwt.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public final class LoginUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private LoginUtils() {
    }

    public static String getTokenForLogin(String username, String password, MockMvc mockMvc) throws Exception {

        String content = mockMvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"password\": \"" + password + "\", \"username\": \"" + username + "\"}"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        AuthenticationResponse authResponse = new AuthenticationResponse(OBJECT_MAPPER
                .readTree(content)
                .get("accessToken")
                .asText());
        return authResponse.getAccessToken();
    }

    private static class AuthenticationResponse {

        private String accessToken;

        public AuthenticationResponse(String accessToken){
            this.accessToken = accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getAccessToken() {
            return accessToken;
        }
    }
}
