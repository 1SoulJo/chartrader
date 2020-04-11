package event;

import lombok.Getter;
import lombok.Setter;

public class ChartViewEvent {
    public static final int SHOW_INSTRUMENT = 1;

    @Getter
    private int type;

    @Setter
    @Getter
    private String instrument;

    public ChartViewEvent(int type) {
        this.type = type;
    }
}
