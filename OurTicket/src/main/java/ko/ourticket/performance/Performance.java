package ko.ourticket.performance;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    protected Performance(){}
    private Performance(final String name, final String description,
                        final PerformanceDateTime performanceDateTime) {
        this.name = name;
        this.description = description;
        this.performanceDateTime = performanceDateTime;
    }
    public static Performance of(final String name, final String description,
                                 final PerformanceDateTime performanceDateTime){
        return new Performance(name, description, performanceDateTime);
    }
}
