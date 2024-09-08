package io.malek.roadassistantauthorization.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static io.malek.roadassistantauthorization.user.data_generators.UserCreationRequestDataGeneratorUtil.getCorrectUser;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserCreationControllerTest extends IntegrationTestConfig {

    static final String USER_CREATION_API_URL = "/api/v1/user";

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void shouldCreateUserWithoutAnyValidationIssues() throws Exception {
        final UserCreationRequest userCreationRequest = getCorrectUser();

        final String userCreationRequestJson = objectMapper.writeValueAsString(userCreationRequest);

        mockMvc.perform(post(USER_CREATION_API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userCreationRequestJson))
                .andDo(print())
                .andExpectAll(
                        status().isOk(), jsonPath("$.processStatus", equalTo(ProcessStatus.COMPLETED.name())));

        assertEquals(1, userRepository.findAll().size());
    }

}
