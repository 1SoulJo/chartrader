package event;

import com.google.common.eventbus.Subscribe;
import ui.frame.Main;

public class ProviderEventSubscriber {
    private final Main main;

    @Subscribe
    public void showAddNew(ProviderEvent e) {
        switch (e.getType()) {
            case ProviderEvent.SHOW_ADD_NEW:
                main.showAddNew();
                break;
            default:
                break;
        }
    }

    public ProviderEventSubscriber(Main main) {
        this.main = main;
    }
}
