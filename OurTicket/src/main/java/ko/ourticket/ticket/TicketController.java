package ko.ourticket.ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ticket")
@RequiredArgsConstructor
public class TicketController {
    private TicketService ticketService;

    @PostMapping("/ticketReserve")
    public ResponseEntity<?> reserveTicket(@RequestParam String nickName, @RequestParam Long ticketId, @RequestParam Integer requestSeatCount){
        try {
            ticketService.purchaseTicket(nickName, ticketId, requestSeatCount);
            return ResponseEntity.ok("성공적으로 티켓 구매가 되었습니다.");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
