package event;

import lombok.Getter;
import lombok.Setter;

/**
 * Event class for main view
 */
public class MainViewEvent {
    public static final int TOGGLE_VIEW_PROVIDER = 1;
    public static final int TOGGLE_VIEW_TRADE = 2;
    public static final int TOGGLE_VIEW_CHART = 3;
    public static final int PROVIDER_ADD_NEW = 4;
    public static final int TRADE_OPEN_ORDER = 5;
    public static final int TOOLS_TRADE_HISTORY = 6;

    @Getter
    private int type;

    @Setter
    @Getter
    private int intParam;

    public MainViewEvent(int type) {
        this.type = type;
    }
}
