package entity;

import enum_class.ReplenishStatus;

/**
 * Represents a replenishment request for a specific medicine.
 * This class stores details about the medicine name, requested amount, and the replenishment status.
 */
public class replenish {
    
    private String medicineName;   // Name of the medicine being requested for replenishment
    private int requestedAmount;   // The amount of medicine requested for replenishment
    private ReplenishStatus status; // The current status of the replenishment request (e.g., PENDING, APPROVED)

    /**
     * Constructs a replenishment request with the specified details.
     *
     * @param medicineName   the name of the medicine to be replenished
     * @param requestedAmount the amount of medicine being requested
     * @param status         the current status of the replenishment request
     */
    public replenish(String medicineName, int requestedAmount, ReplenishStatus status) {
        this.medicineName = medicineName;
        this.requestedAmount = requestedAmount;
        this.status = status;
    }

    /**
     * Gets the name of the medicine.
     *
     * @return the medicine name
     */
    public String getMedicineName() {
        return medicineName;
    }

    /**
     * Gets the requested amount of medicine.
     *
     * @return the requested amount
     */
    public int getRequestedAmount() {
        return requestedAmount;
    }

    /**
     * Gets the current status of the replenishment request.
     *
     * @return the replenishment status
     */
    public ReplenishStatus getStatus() {
        return status;
    }

    /**
     * Updates the requested amount of the medicine.
     *
     * @param newAmount the new requested amount
     */
    public void setRequestedAmount(int newAmount) {
        this.requestedAmount = newAmount;
    }

    /**
     * Updates the status of the replenishment request.
     *
     * @param newStatus the new status of the replenishment request
     */
    public void setStatus(ReplenishStatus newStatus) {
        this.status = newStatus;
    }

    /**
     * Returns a string representation of the replenishment request, including the medicine name,
     * requested amount, and replenishment status.
     *
     * @return a string representation of the replenishment
     */
    @Override
    public String toString() {
        return "Replenishment{" +
                "name='" + medicineName + '\'' +
                ", quantity=" + requestedAmount +
                ", status=" + status +
                '}';
    }
}
