package provider.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity class for order table
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "order")
public class Order {
}
