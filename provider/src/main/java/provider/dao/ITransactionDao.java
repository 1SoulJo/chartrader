package provider.dao;

import provider.entity.Transaction;

import java.util.ArrayList;

/**
 * Transaction DAO interface
 */
public interface ITransactionDao extends ITransactionConstants {
    ArrayList<Transaction> getTransaction();
    ArrayList<Transaction> getTransaction(String filePath);
    void save(Transaction data);
    void save(String filePath, Transaction data);
}
