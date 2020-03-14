package provider.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity class for instrument table
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "instrument")
public class Instrument {
    private String id;
    private String name;
}
