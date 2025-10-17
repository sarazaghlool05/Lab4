import java.io.*;
import java.util.ArrayList;
public abstract class Database<T> {
    protected ArrayList<T> records;
    protected String filename;
    public Database(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
    }
    protected abstract T createRecordFrom(String line);
    protected abstract String lineRepresentation(T record);
    public abstract T getRecord(String key);
    public void readFromFile() {
        records.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                T record = createRecordFrom(line);
                if (record != null)
                    records.add(record);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + filename);
        }
    }
    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (T record : records) {
                writer.println(lineRepresentation(record));
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + filename);
        }
    }
    public void insertRecord(T record) {
        records.add(record);
    }
    public void deleteRecord(String key) {
        T record = getRecord(key);
        if (record != null)
            records.remove(record);
        else
            System.out.println("Record not found: " + key);
    }
    public boolean contains(String key) {
        return getRecord(key) != null;
    }
    public ArrayList<T> returnAllRecords() {
        return records;
    }

    
}
