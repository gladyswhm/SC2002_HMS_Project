package enum_class;

/**
 * Represents the possible statuses of an appointment in the scheduling system.
 * This enumeration ensures type safety and improves readability.
 */
public enum AppointmentStatus {
    
    /**
     * Indicates that the appointment has been approved.
     */
    Accepted,
    
    /**
     * Indicates that the appointment has been rejected.
     */
    Declined,
    
    /**
     * Indicates that the appointment is awaiting review or action.
     */
    Pending
}
