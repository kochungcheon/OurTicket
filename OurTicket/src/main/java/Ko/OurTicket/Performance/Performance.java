package Ko.OurTicket.Performance;

import Ko.OurTicket.Ticket.Ticket;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Entity @Getter
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;
    private String name;
    private String description;
    @Embedded
    private PerformanceDate performanceDate;
    @OneToMany(mappedBy = "performance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> ticketList = new ArrayList<>();
    protected Performance(){}
    private Performance(final String name, final String description, final PerformanceDate performanceDate) {
        this.name = name;
        this.description = description;
        this.performanceDate = performanceDate;
    }
    public static Performance of(final String name, final String description, final PerformanceDate performanceDate){
        return new Performance(name, description, performanceDate);
    }
}
