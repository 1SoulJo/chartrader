package transaction;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Value object for transaction
 */
@Data
@NoArgsConstructor
class Transaction {
    // Transaction types
    private static final int TYPE_BUY = 0;
    private static final int TYPE_SELL = 1;

    private int accountId;
    private String instrumentId;
    private Date date;
    private long price;
    private int type;
    private int quantity;
}
