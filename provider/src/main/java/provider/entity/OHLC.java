package provider.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity class for ohlc table
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "ohlc")
public class OHLC {
    private float open;
    private float high;
    private float low;
    private float close;
}
