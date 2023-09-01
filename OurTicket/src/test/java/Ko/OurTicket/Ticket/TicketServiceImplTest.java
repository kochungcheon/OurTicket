package Ko.OurTicket.Ticket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import Ko.OurTicket.performance.Performance;
import Ko.OurTicket.performance.PerformanceDate;
import Ko.OurTicket.performance.PerformanceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest
@AutoConfigureMockMvc
class TicketServiceImplTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    TicketRepository ticketRepository;

    @Autowired
    TicketService ticketService;

    @MockBean
    PerformanceRepository performanceRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        performanceRepository.deleteAll();
    }

    @DisplayName("공연 티켓 잔여 개수 조회에 성공한다")
    @Test
    void countTicketByGradeForShow() throws Exception {
        Long showId = 1L;
        final String name = "레베카";
        final String description = "...";
        PerformanceDate performanceDate = PerformanceDate.of(LocalDateTime.parse("2023-01-01T00:00:00"), LocalDateTime.parse("2023-01-31T01:00:00"));
        Performance performance = Performance.of(name, description, performanceDate);

        List<Ticket> tickets = Arrays.asList(
                Ticket.of(10, 100000, Grade.VIP),
                Ticket.of(100, 80000, Grade.R),
                Ticket.of(1000, 50000, Grade.S)
        );

        tickets.forEach(ticket -> ticket.setPerformance(performance));
        performance.getTickets().addAll(tickets);

        when(performanceRepository.findById(showId)).thenReturn(Optional.of(performance));

        Map<Grade, Integer> expected = new HashMap<>();
        expected.put(Grade.VIP, 10);
        expected.put(Grade.R, 100);
        expected.put(Grade.S, 1000);

        Map<Grade, Integer> actual = ticketService.countTicketByGradeForShow(showId);

        assertEquals(expected, actual);
    }
}
