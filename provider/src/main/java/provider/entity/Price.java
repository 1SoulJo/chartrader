package provider.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity class for prices table
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "price")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private Date date;
    private String minute;
    private String label;
    private double high;
    private double low;
    private double open;
    private double close;
    private double average;
    private int volume;
    private double notional;
    private int numberOfTrades;
    private String symbol;
}
