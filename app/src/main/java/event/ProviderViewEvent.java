package event;

import lombok.Getter;
import lombok.Setter;

public class ProviderViewEvent {
    public static final int ADD_INSTRUMENT = 1;
    public static final int CANDLE_ADDED = 2;

    @Getter
    private int type;

    @Setter
    @Getter
    private String instrument;

    public ProviderViewEvent(int type) {
        this.type = type;
    }
}
