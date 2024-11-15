package entity;

import enum_class.ReplenishStatus;

public class replenish {
    private String medicineName;
    private int requestedAmount;
    private ReplenishStatus status; 

    public replenish(String medicineName, int requestedAmount, ReplenishStatus status) {
        this.medicineName = medicineName;
        this.requestedAmount = requestedAmount;
        this.status = status; 
    }

    public String getMedicineName() {
        return medicineName;
    }

    public int getRequestedAmount() {
        return requestedAmount;
    }

    public ReplenishStatus getStatus() {
        return status;
    }
    
    public void setRequestedAmount(int newAmount) {
        this.requestedAmount = newAmount;
    }

    public void setStatus(ReplenishStatus newStatus) {
        this.status = newStatus;
    }
    
    @Override
    public String toString() {
        return "Replenishment{" +
                "name='" + medicineName + '\'' +
                ", quantity=" + requestedAmount +
                ", status=" + status +
                '}';
    }
}
