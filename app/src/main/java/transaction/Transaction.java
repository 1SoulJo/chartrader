package transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Value object for transaction
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    // Transaction types
    public static final int TYPE_BUY = 0;
    public static final int TYPE_SELL = 1;

    private String userId;
    private int accountId;
    private String instrumentId;
    private String date;
    private float price;
    private int type;
    private int quantity;
}
