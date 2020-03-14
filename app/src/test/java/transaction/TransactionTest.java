package transaction;

import org.junit.Test;

public class TransactionTest {
    @Test
    public void transactionTest() {
        for (Transaction t : TransactionDAO.getInstance().getTransaction()) {
            System.out.println(t.toString());
        }
    }
}
