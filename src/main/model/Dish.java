package model;


import org.json.JSONObject;

// represent a dish having a name, and a price
public class Dish {
    private String name;
    private double price;

    // EFFECTS: construct a dish with the given name and price
    public Dish(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // EFFECTS: return a JSON object that is converted from a dish
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("price", price);
        return json;
    }

}
