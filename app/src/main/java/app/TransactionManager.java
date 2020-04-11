package app;

import event.EventBusUtil;
import event.PositionViewEvent;
import event.TradeViewEvent;
import lombok.Getter;
import lombok.Setter;
import provider.entity.Position;
import provider.entity.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TransactionManager {
    private static TransactionManager INSTANCE;

    @Setter
    @Getter
    private String activeInstrument;

    @Setter
    @Getter
    private double price;

    @Setter
    @Getter
    private int volume;

    @Getter
    List<Transaction> transactions = new ArrayList<>();

    @Getter
    HashMap<String, Position> positions = new HashMap<>();

    public static TransactionManager get() {
        if (INSTANCE == null) {
            INSTANCE = new TransactionManager();
        }

        return INSTANCE;
    }

    private TransactionManager() {
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public boolean cancelTransaction(int id) {
        Transaction t = transactions.get(id);
        if (t != null && !t.isCompleted()) {
            transactions.remove(t);
            return true;
        }

        return false;
    }

    public void update() {
        for (Transaction t : transactions) {
            if (!t.getInstrumentId().equals(activeInstrument)) continue;

            if (!t.isCompleted()) {
                if (t.getType() == Transaction.TYPE_BUY) {
                    // BUY
                    if (price <= t.getPrice()) {
                        t.setCompleted(true);

                        if (!positions.containsKey(t.getInstrumentId())) {
                            Position p = new Position(t.getInstrumentId(), t.getPrice(), t.getQuantity(), 0, 0);
                            positions.put(t.getInstrumentId(), p);
                        } else {
                            Position p = positions.get(t.getInstrumentId());
                            int totalQuantity = p.getQuantity() + t.getQuantity();
                            p.setAveragePrice(
                                    ((p.getAveragePrice() * p.getQuantity()) + (t.getQuantity() * t.getPrice()))
                                            / totalQuantity);
                            p.setQuantity(totalQuantity);
                        }

                        TradeViewEvent ev = new TradeViewEvent(TradeViewEvent.UPDATE_ROW);
                        ev.setUpdateIndex(transactions.indexOf(t));
                        EventBusUtil.get().post(ev);
                    }
                } else {
                    // SELL
                    if (price >= t.getPrice()) {
                        t.setCompleted(true);

                        Position p = positions.get(activeInstrument);
                        double profit = (t.getPrice() - p.getAveragePrice()) * t.getQuantity();
                        p.setQuantity(p.getQuantity() - t.getQuantity());
                        p.setCompletePL(p.getCompletePL() + profit);

                        TradeViewEvent ev = new TradeViewEvent(TradeViewEvent.UPDATE_ROW);
                        ev.setUpdateIndex(transactions.indexOf(t));
                        EventBusUtil.get().post(ev);
                    }
                }
            }
        }

        for (Position p : positions.values()) {
            if (!p.getInstrument().equals(activeInstrument)) continue;

            double profit = (price - p.getAveragePrice()) * p.getQuantity();
            p.setIncompletePL(profit);
        }
        EventBusUtil.get().post(new PositionViewEvent(PositionViewEvent.UPDATE_TABLE));
    }
}
