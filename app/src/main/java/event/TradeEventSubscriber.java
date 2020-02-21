package event;

import com.google.common.eventbus.Subscribe;
import ui.frame.Main;

public class TradeEventSubscriber {
    private final Main main;

    @Subscribe
    public void eventTriggered(TradeEvent e) {
        switch (e.getType()) {
            case TradeEvent.OPEN_ORDER:
                main.showOrder();
                break;
            default:
                break;
        }
    }

    public TradeEventSubscriber(Main main) {
        this.main = main;
    }
}
