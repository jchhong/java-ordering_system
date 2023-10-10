package model;


// represent an order placed by a server including
// a table number, a dish list, a note, and a boolean
// showing if is completed.
public class Order extends DishCollection {
    private static final double DISCOUNT_VALUE = 100.0;
    private static final double DISCOUNT_AMOUNT = 10.0;
    private static final double GST = 0.05;
    private static final double PST = 0.07;


    private int tableNumber;
    private String note;
    private boolean isComplete;


    // EFFECTS: Place an order of the given table number,
    //          with an empty dish list, empty notes, and status being
    //          not complete.
    public Order(int tableNumber) {
        super();
        this.tableNumber = tableNumber;
        note = "";
        isComplete = false;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public String getNote() {
        return note;
    }

    public boolean getIsComplete() {
        return isComplete;
    }

    // MODIFIES: this
    // EFFECTS: replace the existing note with the new note.
    public void changeNoteTo(String note) {
        this.note = note;
    }


    // MODIFIES: this
    // EFFECTS: change the status to complete.
    public void orderCompleted() {
        isComplete = true;
    }

    // EFFECTS: If the totalPrice is greater than or equal to the DISCOUNT_VALUE,
    //          a discount of DISCOUNT_AMOUNT will be subtracted. And the discount will
    //          apply ONLY ONCE.
    public boolean eligibleForDiscount() {
        return getSubTotal() >= DISCOUNT_VALUE;
    }


    // EFFECTS: view the order placed. This will print the table number,
    //          all the dishes that has been ordered include the name and the price,
    //          subtotal of this order, subtotal after the discount if eligible, GST,
    //          PST, and the total value that need to be paid.
    public String view() {
        String outline;
        outline = "***** Order for table " + tableNumber + "*****" + "\n"
                + "------------" + "\n"
                + "Summary of dishes:\n";
        for (Dish d :  dishList) {
            outline += "\t" + d.getName() + "\t" + "$" + d.getPrice() + "\n";
        }
        outline += "------------" + "\n"
                + "Note:" + "\n"
                + "\t" + note + "\n"
                + "------------" + "\n"
                + "SUB TOTAL: $" + String.format("%.2f", getSubTotal()) + "\n";
        if (this.eligibleForDiscount()) {
            outline += "DISCOUNT: $-" + DISCOUNT_AMOUNT + "\n";
        }
        outline += "------------" + "\n"
                + "GST: $" + String.format("%.2f", GST * getSubTotalAfterDiscount()) + "\n"
                + "PST: $" + String.format("%.2f", PST * getSubTotalAfterDiscount()) + "\n"
                + "------------" + "\n"
                + "TOTAL: $" + String.format("%.2f", getTotalPrice());
        return outline;
    }


    // EFFECTS: calculate the subtotal of this order.
    public double getSubTotal() {
        double subTotal = 0;
        for (Dish d : dishList) {
            subTotal += d.getPrice();
        }
        return subTotal;
    }

    // EFFECTS: calculate the subtotal after discount if eligible,
    //          else just return the subtotal.
    public double getSubTotalAfterDiscount() {
        if (this.eligibleForDiscount()) {
            return getSubTotal() - DISCOUNT_AMOUNT;
        } else {
            return getSubTotal();
        }
    }

    // EFFECTS: calculate the total price of this order. Total price
    //          is calculated by subtotal - (discount) - GST and PST.
    public double getTotalPrice() {
        return getSubTotalAfterDiscount() * (1 + GST + PST);
    }

}
