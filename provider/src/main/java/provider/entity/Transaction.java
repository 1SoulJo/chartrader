package provider.entity;

import lombok.Data;

/**
 * Value object for transaction
 */
@Data
public class Transaction {
    // Transaction types
    public static final int TYPE_BUY = 0;
    public static final int TYPE_SELL = 1;

    private static int nextId = 1;

    public Transaction() {
        this.id = nextId++;
    }

    public Transaction(String userId, int accountId, String instrumentId, String date, float price, int type,
                       int quantity, boolean completed) {
        this.id = nextId++;
        this.userId = userId;
        this.accountId = accountId;
        this.instrumentId = instrumentId;
        this.date = date;
        this.price = price;
        this.type = type;
        this.quantity = quantity;
        this.completed = completed;
    }

    private int id;
    private String userId;
    private int accountId;
    private String instrumentId;
    private String date;
    private float price;
    private int type;
    private int quantity;

    private boolean completed;
}
