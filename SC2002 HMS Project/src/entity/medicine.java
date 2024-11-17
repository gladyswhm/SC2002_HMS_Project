package entity;

public class medicine {

    private String name;
    private int stockLevel;
    private int lowStockAlert;

    public medicine(String name, int stockLevel, int lowStockAlert) {
        this.name = name; 
        this.stockLevel = stockLevel;
        this.lowStockAlert = lowStockAlert; 
    } 

    public String getName() { return name; }
    public int getStockLevel() { return stockLevel; }
    public int getLowStockAlert() { return lowStockAlert; }

    public void setStockLevel(int stockLevel) { this.stockLevel = stockLevel; }
    public void setLowStockAlert(int lowStockAlert) { this.lowStockAlert = lowStockAlert; }
    

    @Override
    public String toString() {
        return "[Medicine name=" + name + ", stockLevel=" + stockLevel + ", lowStockAlert=" + lowStockAlert + "]";
    }
}
