package event;

public class TradeEvent {
    public static final int OPEN_ORDER = 1;

    private final int type;

    public TradeEvent(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
