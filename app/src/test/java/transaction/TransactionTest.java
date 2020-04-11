package transaction;

import org.junit.Test;
import provider.dao.TransactionDao;
import provider.entity.Transaction;

public class TransactionTest {
    @Test
    public void transactionTest() {
        for (Transaction t : TransactionDao.getInstance().getTransaction()) {
            System.out.println(t.toString());
        }
    }
}
