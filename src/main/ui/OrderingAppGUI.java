package ui;

import model.Dish;
import model.Menu;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;


// This class represents the main frame of the Ordering App
public class OrderingAppGUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 640;
    private Menu myMenu;
    private JScrollPane scrollPane;
    private JPanel tablePanel;
    private JTable table;
    private JButton menuAddButton;
    private JButton menuRemoveButton;
    private JButton menuReloadButton;
    private JButton menuSaveButton;
    private JTextField dishNameField;
    private JTextField priceField;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/myMenu.json";

    // EFFECTS: construct the main frame, adding label, tablePanel, buttonPanel, and imagePanel
    @SuppressWarnings("methodlength")
    public OrderingAppGUI() {
        super("Order System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize fields
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        myMenu = new Menu();
        myMenu.addDish("Double Cheeseburger", 4.69);
        myMenu.addDish("Chicken Sandwich", 6.49);
        myMenu.addDish("Beef Sandwich", 8.25);
        myMenu.addDish("Salmon Salad", 9.85);

        // create tablePanel
        // // create table
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Dish Name");
        tableModel.addColumn("Price");

        for (Dish d : myMenu.getDishList()) {
            tableModel.addRow(new Object[]{d.getName(), d.getPrice()});
        }

        // // add to tablePanel
        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(600, 300));
        table.setBackground(Color.WHITE);
        table.setVisible(true);

        scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.white);

        tablePanel = new JPanel();
        tablePanel.setPreferredSize(new Dimension(600, 380));
        tablePanel.add(scrollPane);


        // create button Panel
        // // create buttons
        menuAddButton = new JButton("Add Dish to Menu");
        menuAddButton.addActionListener(new MenuAddListener());
        menuRemoveButton = new JButton("Remove Dish from Menu");
        menuRemoveButton.addActionListener(new MenuRemoveListener());
        menuSaveButton = new JButton("Save My Menu");
        menuSaveButton.addActionListener(new MenuSaveListener());
        menuReloadButton = new JButton("Reload My Menu");
        menuReloadButton.addActionListener(new MenuReloadListener());

        // // create menuAddButton's associated text fields
        JLabel dishNameLabel = new JLabel("New Dish Name: ");
        JLabel priceLabel = new JLabel("Price: $");
        dishNameField = new JTextField();
        dishNameField.setPreferredSize(new Dimension(150, 30));
        priceField = new JTextField();
        priceField.setPreferredSize(new Dimension(150, 30));

        JPanel menuAddButtonRowPanel = new JPanel();
        menuAddButtonRowPanel.add(dishNameLabel);
        menuAddButtonRowPanel.add(dishNameField);
        menuAddButtonRowPanel.add(priceLabel);
        menuAddButtonRowPanel.add(priceField);
        menuAddButtonRowPanel.add(menuAddButton);

        // add to buttonPanel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0,1));
        buttonPanel.add(menuAddButtonRowPanel);
        buttonPanel.add(menuRemoveButton);
        buttonPanel.add(menuSaveButton);
        buttonPanel.add(menuReloadButton);

        // create image Panel
        ImageIcon image = new ImageIcon(".idea/image/bg2.jpg");
        image.setImage(image.getImage().getScaledInstance(1000,640,Image.SCALE_DEFAULT));
        JLabel imageLabel = new JLabel(image);
        imageLabel.setSize(image.getIconWidth(),image.getIconHeight());

        JPanel imagePanel = (JPanel) this.getContentPane();
        imagePanel.setOpaque(false);

        // create label: my menu
        JLabel menuLabel = new JLabel("My Menu");
        menuLabel.setFont(new Font("Arial", Font.BOLD, 48));
        menuLabel.setForeground(Color.PINK);

        // this frame set layout, put things in
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(menuLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(tablePanel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(buttonPanel, gbc);

        this.getLayeredPane().add(imageLabel,new Integer(Integer.MIN_VALUE));

        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }


    // This class represents a MenuAddListener associated to MenuAddButton
    private class MenuAddListener extends AbstractAction {

        // MODIFIES: this
        // EFFECTS: adds a new dish to the menu. If haven't filled name or price, pop message; if price is not a
        //          number, pop message
        @Override
        public void actionPerformed(ActionEvent e) {
            if (dishNameField.getText().equals("") || priceField.getText().equals("")) {
                JOptionPane.showMessageDialog(OrderingAppGUI.this,
                        "Please enter the name AND the price");
                return;
            }
            String newDishName = dishNameField.getText();
            try {
                Double newDishPrice = Double.parseDouble(priceField.getText());
                myMenu.addDish(newDishName,newDishPrice);
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                tableModel.addRow(new Object[]{newDishName,newDishPrice});
                dishNameField.setText("");
                priceField.setText("");
            } catch (NumberFormatException exception) {
                priceField.setText("");
                JOptionPane.showMessageDialog(OrderingAppGUI.this,
                        "Price should be a number!");
            }
        }
    }

    // This class represents a MenuRemoveListener associated to MenuRemoveButton
    private class MenuRemoveListener extends AbstractAction {

        // MODIFIES: this
        // EFFECTS: remove the selected dish from my menu.
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                myMenu.removeDish(table.getValueAt(selectedRow,0).toString());
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                tableModel.removeRow(selectedRow);
            }
        }
    }


    // This class represents a MenuReloadListener associated to MenuReloadButton
    private class MenuReloadListener extends AbstractAction {

        // MODIFIES: this
        // EFFECTS: load the saved menu from JSON at JSON_STORE
        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                myMenu = jsonReader.read();
            } catch (IOException exception) {
                JOptionPane.showMessageDialog(OrderingAppGUI.this,
                        "Successfully loaded my Menu from " + JSON_STORE);
            }

            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

            int numRows = tableModel.getRowCount();
            for (int i = numRows - 1; i >= 0; i--) {
                tableModel.removeRow(i);
            }
            for (Dish d : myMenu.getDishList()) {
                tableModel.addRow(new Object[]{d.getName(),d.getPrice()});
            }
            JOptionPane.showMessageDialog(OrderingAppGUI.this,
                    "Successfully loaded my Menu from " + JSON_STORE);
        }
    }

    // This class represents a MenuSaveListener associated to MenuSaveButton
    private class MenuSaveListener extends AbstractAction {

        // MODIFIES: this
        // EFFECTS: save my menu to JSON at JSON_STORE
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(myMenu);
                jsonWriter.close();
                JOptionPane.showMessageDialog(OrderingAppGUI.this,
                        "Successfully saved my Menu to " + JSON_STORE);
            } catch (FileNotFoundException exception) {
                JOptionPane.showMessageDialog(OrderingAppGUI.this,
                        "Unable to write to file: " + JSON_STORE);
            }
        }
    }





}
