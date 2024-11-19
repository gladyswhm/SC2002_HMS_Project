package entity;

/**
 * Represents a medicine with its stock details and a low stock alert threshold.
 */
public class medicine {

    private String name;          // Name of the medicine
    private int stockLevel;       // Current stock level of the medicine
    private int lowStockAlert;    // Threshold level to trigger low stock alert

    /**
     * Constructs a new medicine object with specified name, stock level, and low stock alert threshold.
     *
     * @param name          the name of the medicine
     * @param stockLevel    the current stock level of the medicine
     * @param lowStockAlert the stock level threshold for low stock alerts
     */
    public medicine(String name, int stockLevel, int lowStockAlert) {
        this.name = name; 
        this.stockLevel = stockLevel;
        this.lowStockAlert = lowStockAlert; 
    } 

    /**
     * Gets the name of the medicine.
     *
     * @return the name of the medicine
     */
    public String getName() { 
        return name; 
    }

    /**
     * Gets the current stock level of the medicine.
     *
     * @return the current stock level
     */
    public int getStockLevel() { 
        return stockLevel; 
    }

    /**
     * Gets the low stock alert threshold.
     *
     * @return the low stock alert threshold
     */
    public int getLowStockAlert() { 
        return lowStockAlert; 
    }

    /**
     * Updates the stock level of the medicine.
     *
     * @param stockLevel the new stock level
     */
    public void setStockLevel(int stockLevel) { 
        this.stockLevel = stockLevel; 
    }

    /**
     * Updates the low stock alert threshold.
     *
     * @param lowStockAlert the new low stock alert threshold
     */
    public void setLowStockAlert(int lowStockAlert) { 
        this.lowStockAlert = lowStockAlert; 
    }
    
    /**
     * Returns a string representation of the medicine object, including its name,
     * stock level, and low stock alert threshold.
     *
     * @return a string representation of the medicine object
     */
    @Override
    public String toString() {
        return "[Medicine name=" + name + ", stockLevel=" + stockLevel + ", lowStockAlert=" + lowStockAlert + "]";
    }
}
