package enum_class;

/**
 * Enum representing different blood types, including their positive and negative variants.
 */
public enum BloodType {
    /**
     * O negative blood type.
     */
    O_NEG,
    
    /**
     * A negative blood type.
     */
    A_NEG,
    
    /**
     * B negative blood type.
     */
    B_NEG,
    
    /**
     * AB negative blood type.
     */
    AB_NEG,
    
    /**
     * O positive blood type.
     */
    O_POS,
    
    /**
     * A positive blood type.
     */
    A_POS,
    
    /**
     * B positive blood type.
     */
    B_POS,
    
    /**
     * AB positive blood type.
     */
    AB_POS;

    /**
     * Converts a string representation of a blood type into its corresponding {@code BloodType} enum.
     *
     * @param input the string representation of the blood type (e.g., "A+", "B_NEG").
     * @return the corresponding {@code BloodType}.
     * @throws IllegalArgumentException if the input does not match any known blood type.
     */
    public static BloodType fromString(String input) {
        switch (input.toUpperCase()) {
            case "A+":
            case "A_POS":
                return A_POS;
            case "B+":
            case "B_POS":
                return B_POS;
            case "AB+":
            case "AB_POS":
                return AB_POS;
            case "O+":
            case "O_POS":
                return O_POS;
            case "A-":
            case "A_NEG":
                return A_NEG;
            case "B-":
            case "B_NEG":
                return B_NEG;
            case "AB-":
            case "AB_NEG":
                return AB_NEG;
            case "O-":
            case "O_NEG":
                return O_NEG;
            default:
                throw new IllegalArgumentException("Unknown blood type: " + input);
        }
    }
}
