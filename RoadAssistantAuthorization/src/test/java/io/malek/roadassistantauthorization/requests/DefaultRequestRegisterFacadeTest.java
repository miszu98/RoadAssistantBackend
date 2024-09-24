package io.malek.roadassistantauthorization.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.malek.roadassistantauthorization.requests.dtos.RequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class DefaultRequestRegisterFacadeTest {

    @Mock
    private RequestRepository requestRepository;

    @Mock
    private RequestMapper requestMapper;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private Clock clock;

    @InjectMocks
    private DefaultRequestRegisterFacade defaultRequestRegisterFacade;

    @Test
    void shouldSetTimestampBeforeSaveRequest() throws IOException {
        final RequestDTO requestDTO = RequestGenerator.createUserRegisterRequestDto();
        final Request request = RequestGenerator.createUserRegisterRequest(requestDTO);

        when(requestMapper.mapToRequest(requestDTO)).thenReturn(request);
        when(clock.getZone()).thenReturn(ZoneId.systemDefault());
        when(clock.instant()).thenReturn(Instant.now(Clock.fixed(Instant.parse("2024-08-22T10:00:00Z"),
                ZoneOffset.UTC)));

        defaultRequestRegisterFacade.registerRequest(requestDTO);

        assertNotNull(request.getCreatedAt());
    }

    @Test
    void shouldSaveRequest() throws IOException {
        final RequestDTO requestDTO = RequestGenerator.createUserRegisterRequestDto();
        final Request request = RequestGenerator.createUserRegisterRequest(requestDTO);

        when(requestMapper.mapToRequest(requestDTO)).thenReturn(request);
        when(clock.getZone()).thenReturn(ZoneId.systemDefault());
        when(clock.instant()).thenReturn(Instant.now(Clock.fixed(Instant.parse("2024-08-22T10:00:00Z"),
                ZoneOffset.UTC)));

        defaultRequestRegisterFacade.registerRequest(requestDTO);

        verify(requestRepository, times(1)).save(request);
    }

}