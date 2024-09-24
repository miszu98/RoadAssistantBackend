package io.malek.roadassistantauthorization.user.register;

import io.malek.roadassistantauthorization.IntegrationTestConfig;
import io.malek.roadassistantauthorization.user.dtos.UserCreationRequest;
import io.malek.roadassistantauthorization.user.enums.ProcessStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static io.malek.roadassistantauthorization.user.data_generators.UserCreationRequestDataGeneratorUtil.*;
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
        userRepository.deleteAll();
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

    @Test
    void shouldReturnInformationAboutCreationUserProcessFailureWhenNotProvideEmail() throws Exception {
        final UserCreationRequest userCreationRequest = getUserWithNullEmail();

        final String userCreationRequestJson = objectMapper.writeValueAsString(userCreationRequest);

        mockMvc.perform(post(USER_CREATION_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userCreationRequestJson))
                .andDo(print())
                .andExpectAll(
                        status().isOk(), jsonPath("$.processStatus", equalTo(ProcessStatus.FAILURE.name())));

        assertEquals(0, userRepository.findAll().size());
    }

    @Test
    void shouldReturnInformationAboutCreationUserProcessFailureWhenNotProvideFirstname() throws Exception {
        final UserCreationRequest userCreationRequest = getUserWithNullFirstName();

        final String userCreationRequestJson = objectMapper.writeValueAsString(userCreationRequest);

        mockMvc.perform(post(USER_CREATION_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userCreationRequestJson))
                .andDo(print())
                .andExpectAll(
                        status().isOk(), jsonPath("$.processStatus", equalTo(ProcessStatus.FAILURE.name())));

        assertEquals(0, userRepository.findAll().size());
    }

    @Test
    void shouldReturnInformationAboutCreationUserProcessFailureWhenNotProvideLastname() throws Exception {
        final UserCreationRequest userCreationRequest = getUserWithNullLastName();

        final String userCreationRequestJson = objectMapper.writeValueAsString(userCreationRequest);

        mockMvc.perform(post(USER_CREATION_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userCreationRequestJson))
                .andDo(print())
                .andExpectAll(
                        status().isOk(), jsonPath("$.processStatus", equalTo(ProcessStatus.FAILURE.name())));

        assertEquals(0, userRepository.findAll().size());
    }

    @Test
    void shouldReturnInformationAboutCreationUserProcessFailureWhenNotProvidePassword() throws Exception {
        final UserCreationRequest userCreationRequest = getUserWithNullPassword();

        final String userCreationRequestJson = objectMapper.writeValueAsString(userCreationRequest);

        mockMvc.perform(post(USER_CREATION_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userCreationRequestJson))
                .andDo(print())
                .andExpectAll(
                        status().isOk(), jsonPath("$.processStatus", equalTo(ProcessStatus.FAILURE.name())));

        assertEquals(0, userRepository.findAll().size());
    }

    @Test
    void shouldReturnInformationAboutCreationUserProcessFailureWhenNotProvideRepeatPassword() throws Exception {
        final UserCreationRequest userCreationRequest = getUserWithNullRepeatPassword();

        final String userCreationRequestJson = objectMapper.writeValueAsString(userCreationRequest);

        mockMvc.perform(post(USER_CREATION_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userCreationRequestJson))
                .andDo(print())
                .andExpectAll(
                        status().isOk(), jsonPath("$.processStatus", equalTo(ProcessStatus.FAILURE.name())));

        assertEquals(0, userRepository.findAll().size());
    }

    @Test
    void shouldReturnInformationAboutCreationUserProcessFailureWhenPasswordAreNotEqual() throws Exception {
        final UserCreationRequest userCreationRequest = getUserWithDifferentPasswords();

        final String userCreationRequestJson = objectMapper.writeValueAsString(userCreationRequest);

        mockMvc.perform(post(USER_CREATION_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userCreationRequestJson))
                .andDo(print())
                .andExpectAll(
                        status().isOk(), jsonPath("$.processStatus", equalTo(ProcessStatus.FAILURE.name())));

        assertEquals(0, userRepository.findAll().size());
    }

}
