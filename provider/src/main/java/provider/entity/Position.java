package provider.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Position {
    private String instrument;
    private double averagePrice;
    private int quantity;
    private double incompletePL;
    private double completePL;
}
