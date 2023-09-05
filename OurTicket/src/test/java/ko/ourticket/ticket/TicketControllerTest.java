package ko.ourticket.ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TicketControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private TicketController ticketController;

    @Mock
    private TicketService ticketService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ticketController).build();
    }

    @Test
    void reserveTicket_success() throws Exception {
        // Given
        final String nickName = "testUser";
        final Long ticketId = 1L;
        final Integer requestSeatCount = 1;

        doNothing().when(ticketService).purchaseTicket(nickName, ticketId, requestSeatCount);

        // When & Then
        mockMvc.perform(post("/api/ticket/ticketReserve")
                        .param("nickName", nickName)
                        .param("ticketId", ticketId.toString())
                        .param("requestSeatCount", requestSeatCount.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
