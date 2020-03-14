package transaction;

import java.util.ArrayList;

/**
 * Transaction DAO interface
 */
public interface ITransactionDAO extends ITransactionConstants {
    ArrayList<Transaction> getTransaction(int id);

    void save(Transaction data);
}
