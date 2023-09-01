package ko.ourticket.performance;

import ko.ourticket.Ticket.Ticket;
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

@Entity
@Getter
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;
    private String name;
    private String description;
    @Embedded
    private PerformanceDateTime performanceDateTime;
    @OneToMany(mappedBy = "performance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();
    protected Performance(){}
    private Performance(final String name, final String description, final PerformanceDateTime performanceDateTime) {
        this.name = name;
        this.description = description;
        this.performanceDateTime = performanceDateTime;
    }
    public static Performance of(final String name, final String description, final PerformanceDateTime performanceDateTime){
        return new Performance(name, description, performanceDateTime);
    }
}
