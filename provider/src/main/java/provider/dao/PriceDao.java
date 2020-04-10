package provider.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import provider.entity.Price;
import provider.util.HibernateUtil;

import java.util.List;

/**
 * DAO for price data
 */
public class PriceDao {
    private static PriceDao INSTANCE;

    public static PriceDao get() {
        if (INSTANCE == null) {
            INSTANCE = new PriceDao();
        }
        return INSTANCE;
    }

    private PriceDao() {
    }

    public List<Price> getPriceData(String symbol) {
        String query = String.format("from Price where symbol='%s'", symbol);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(query, Price.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getSymbols() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT DISTINCT symbol FROM Price").list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
