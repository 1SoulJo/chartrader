package event;

public class ViewToggleEvent {
    String name;

    public ViewToggleEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
