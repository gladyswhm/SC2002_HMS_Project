package enum_class;

/**
 * Enum representing the availability status of a resource or entity.
 * <p>
 * This enum provides three possible states:
 * <ul>
 *     <li>{@code Available} - Indicates that the resource is currently free for use.</li>
 *     <li>{@code Confirmed} - Indicates that the resource has been booked or reserved.</li>
 *     <li>{@code Pending} - Indicates that the resource is awaiting confirmation.</li>
 * </ul>
 * </p>
 */
public enum AvailStatus {
    /**
     * The resource is currently free for use.
     */
    Available,
    
    /**
     * The resource has been booked or reserved.
     */
    Confirmed,
    
    /**
     * The resource is awaiting confirmation.
     */
    Pending
}
