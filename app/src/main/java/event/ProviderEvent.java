package event;

public class ProviderEvent {
    public static final int SHOW_ADD_NEW = 1;

    private final int type;

    public ProviderEvent(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
