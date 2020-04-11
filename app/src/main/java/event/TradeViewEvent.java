package event;

import lombok.Getter;
import lombok.Setter;
import provider.entity.Transaction;

/**
 * Event class for trade view
 */
public class TradeViewEvent {
    public static final int UPDATE_TABLE = 1;
    public static final int UPDATE_ROW = 2;

    @Getter
    private int type;

    @Setter
    @Getter
    private Transaction transactionParam;

    @Setter
    @Getter
    private int updateIndex;

    public TradeViewEvent(int type) {
        this.type = type;
    }
}
