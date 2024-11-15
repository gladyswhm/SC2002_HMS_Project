package enum_class;

public enum BloodType {
    O_NEG,
    A_NEG,
    B_NEG,
    AB_NEG,
    O_POS,
    A_POS,
    B_POS,
    AB_POS;

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
