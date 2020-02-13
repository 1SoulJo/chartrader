package event;

import com.google.common.eventbus.EventBus;

/**
 * Singleton for event bus
 */
public class EventBusUtil {
    private static EventBus INSTANCE;

    private EventBusUtil() {
    }

    public static EventBus get() {
        if (INSTANCE != null) {
            return INSTANCE;
        }

        INSTANCE = new EventBus();
        return INSTANCE;
    }
}
