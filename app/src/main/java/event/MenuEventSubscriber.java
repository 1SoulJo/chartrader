package event;

import com.google.common.eventbus.Subscribe;
import ui.frame.Main;

public class MenuEventSubscriber {
    private Main main;

    @Subscribe
    public void toggleViewEvent(ViewToggleEvent e) {
        main.toggleView(e.getName());
    }

    public MenuEventSubscriber(Main main) {
        this.main = main;
    }
}
