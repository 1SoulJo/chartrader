package event;

import lombok.Getter;
import lombok.Setter;
import transaction.Transaction;

/**
 * Event class for trade view
 */
public class TradeViewEvent {
    public static final int ORDER_PLACED = 1;

    @Getter
    private int type;

    @Setter
    @Getter
    private Transaction transactionParam;

    public TradeViewEvent(int type) {
        this.type = type;
    }
}
