package ko.ourticket.ticket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import ko.ourticket.performance.Performance;
import ko.ourticket.performance.PerformanceDateTime;
import ko.ourticket.performance.PerformanceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
class TicketServiceTest {

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
        Long performanceId = 1L;
        final String name = "레베카";
        final String description = "...";
        PerformanceDateTime performanceDateTime = PerformanceDateTime.of(LocalDateTime.parse("2023-01-01T00:00:00"), LocalDateTime.parse("2023-01-31T01:00:00"));
        Performance performance = Performance.of(name, description, performanceDateTime);

        List<Ticket> tickets = Arrays.asList(
                Ticket.of(10, 100000, Grade.VIP),
                Ticket.of(100, 80000, Grade.R),
                Ticket.of(1000, 50000, Grade.S)
        );

        tickets.forEach(ticket -> ticket.setPerformance(performance.getId()));
        when(ticketRepository.findByPerformanceId(performanceId)).thenReturn(tickets);
        Map<Grade, Integer> map = new HashMap<>();
        map.put(Grade.VIP, 10);
        map.put(Grade.R, 100);
        map.put(Grade.S, 1000);
        GradeCount expected = GradeCount.of(map);
        GradeCount actual = ticketService.countTicketByGradeForPerformance(performanceId);

        assertEquals(expected, actual);
    }
}
