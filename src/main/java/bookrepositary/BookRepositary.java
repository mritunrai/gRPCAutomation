package bookrepositary;

import java.util.HashMap;
import java.util.Map;

public class BookRepositary {
    Map<Integer, Map<String, String>> bookRepositary = null;

    public BookRepositary() {
        bookRepositary = new HashMap<>();
    }

    public Map<Integer, Map<String, String>> getBookRepositary() {
        bookRepositary.put(1, new HashMap() {{
            put("Gradle", "Bob");
        }});
        bookRepositary.put(1, new HashMap() {{
            put("Gradle", "Bob");
        }});

        return bookRepositary;
    }
}
