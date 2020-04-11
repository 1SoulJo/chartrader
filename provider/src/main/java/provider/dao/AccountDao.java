package provider.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import provider.entity.Account;
import provider.util.HibernateUtil;

import java.util.List;

public class AccountDao {
    private static AccountDao INSTANCE;

    private AccountDao() {}

    public static AccountDao get() {
        if (INSTANCE == null) {
            INSTANCE = new AccountDao();
        }

        return INSTANCE;
    }

    public List<Account> getUser(String user, String password) {
        String query =
                String.format("FROM Account WHERE user_id='%s' AND password='%s'", user, password);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(query, Account.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean save(String user, String password) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            Account a = new Account();
            a.setUserId(user);
            a.setPassword(password);
            a.setBalance(100000);
            session.save(a);

            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
