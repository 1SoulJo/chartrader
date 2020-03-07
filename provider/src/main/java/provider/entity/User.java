package provider.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity class for user table
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
}
