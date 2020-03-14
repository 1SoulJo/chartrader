package provider;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import provider.entity.Account;
import provider.util.HibernateUtil;

import java.util.List;

public class ProviderTest {
    @Test
    public void dbConnectionTest() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            Account a = new Account();
            a.setUserId("hansol0486");
            a.setPassword("Temp1234!");
            a.setBalance(100000);
            session.save(a);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Account> accounts = session.createQuery("from Account", Account.class).list();
            accounts.forEach(a -> System.out.println(a.getUserId() + ", " + a.getBalance()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
