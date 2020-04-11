package provider.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import provider.entity.Price;
import provider.util.HibernateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    public List<Price> getPriceData(String symbol, Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String query = String.format("FROM Price WHERE symbol='%s' AND date='%s'", symbol, df.format(date));

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(query, Price.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Price> getPriceData(String symbol, Date date, String minute) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String query =
                String.format("FROM Price WHERE symbol='%s' AND date='%s' AND minute='%s'", symbol, df.format(date), minute);

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

    public List<Date> getDates(String symbol) {
        String query = String.format("SELECT DISTINCT date FROM Price WHERE symbol='%s' ORDER BY date DESC", symbol);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(query).list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
