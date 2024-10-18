package src;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Generic Class with File Handling
public class Library<T> implements Serializable {
    private List<T> items;

    public Library() {
        this.items = new ArrayList<>();
    }

    public void addItem(T item) {
        items.add(item);
    }

    public void removeItem(T item) {
        items.remove(item);
    }

    public List<T> getAllItems() {
        return items;
    }

    // File handling methods

    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(items);
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            items = (List<T>) ois.readObject();
        }
    }

    @Override
    public String toString() {
        return items.toString();
    }
}
