package provider.dao;

import com.google.common.io.Files;
import provider.entity.Transaction;

import java.io.*;
import java.util.ArrayList;

/**
 * Data access object for transaction data file.
 */
public class TransactionDao implements ITransactionDao {
    private static TransactionDao Instance;

    private TransactionDao() {
    }

    public static TransactionDao getInstance() {
        if (Instance == null) {
            Instance = new TransactionDao();
        }
        return Instance;
    }

    @Override
    public ArrayList<Transaction> getTransaction() {
        return getTransaction(FILE_PATH);
    }

    @Override
    public ArrayList<Transaction> getTransaction(String filePath) {
        ArrayList<Transaction> list = new ArrayList<>();
        DataInputStream dis;
        try {
            dis = new DataInputStream(new FileInputStream(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        try {
            while(true) {
                String userId = dis.readUTF();
                int accountId = dis.readInt();
                String instrumentId = dis.readUTF();
                String date = dis.readUTF();
                float price = dis.readFloat();
                int type = dis.readInt();
                int quantity = dis.readInt();
                boolean completed = dis.readBoolean();
                Transaction t =
                        new Transaction(userId, accountId, instrumentId, date, price, type, quantity, completed);
                list.add(t);
            }
        } catch (EOFException e) {
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public void save(Transaction data) {
        save(FILE_PATH, data);
    }

    @Override
    public void save(String filePath, Transaction data) {
        DataOutputStream dos = null;
        try {
            checkFile();

            dos = new DataOutputStream(new FileOutputStream(filePath, true));
            dos.writeUTF(data.getUserId());
            dos.writeInt(data.getAccountId());
            dos.writeUTF(data.getInstrumentId());
            dos.writeUTF(data.getDate());
            dos.writeFloat(data.getPrice());
            dos.writeInt(data.getType());
            dos.writeInt(data.getQuantity());
            dos.writeBoolean(data.isCompleted());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void checkFile() throws IOException {
        File f = new File(FILE_PATH);
        if (!f.exists()) {
            Files.createParentDirs(f);
            Files.touch(f);
        }
    }
}
