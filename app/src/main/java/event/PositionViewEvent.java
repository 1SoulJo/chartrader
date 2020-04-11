package event;

import lombok.Getter;

public class PositionViewEvent {
    public static final int UPDATE_TABLE = 1;

    @Getter
    private int type;

    public PositionViewEvent(int type) {
        this.type = type;
    }
}
