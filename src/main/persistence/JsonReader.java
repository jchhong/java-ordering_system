package persistence;

import model.Menu;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Cited from: ubc CPSC210 2023W1 JsonSerializationDemo

// Represents a reader that reads menu from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads menu from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Menu read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMenu(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses menu from JSON object and returns it
    private Menu parseMenu(JSONObject jsonObject) {
        Menu myMenu = new Menu();
        addDishesToMenu(myMenu, jsonObject);
        return myMenu;
    }

    // MODIFIES: menu
    // EFFECTS: parses dishes from JSON object and adds them to the given menu
    private void addDishesToMenu(Menu menu, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("dishes");
        for (Object json : jsonArray) {
            JSONObject nextDish = (JSONObject) json;
            addOneDishToMenu(menu, nextDish);
        }
    }

    // MODIFIES: menu
    // EFFECTS: parses one dish from JSON object and adds it to the given menu
    private void addOneDishToMenu(Menu menu, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double price = jsonObject.getDouble("price");
        menu.addDish(name, price);
    }
}
