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
}
