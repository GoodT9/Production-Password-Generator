
public class Password {
    String Value;
    int Length;

    public Password(String s) {
        Value = s;
        Length = s.length();
    }



    public double calculateEntropy() {
        double entropy = 0;
        int passwordLength = Value.length();
        int poolSize = 0;

        if (Value.matches(".*[A-Z].*")) poolSize += 26;
        if (Value.matches(".*[a-z].*")) poolSize += 26;
        if (Value.matches(".*\\d.*")) poolSize += 10;
        if (Value.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) poolSize += 32;

        entropy = passwordLength * (Math.log(poolSize) / Math.log(2));
        return entropy;
    }


public int CharType(char C) {
    int val;

    // Char is Uppercase Letter
    if ((int) C >= 65 && (int) C <= 90)
        val = 1;

    // Char is Lowercase Letter
    else if ((int) C >= 97 && (int) C <= 122) {
        val = 2;
    }

     // Char is Digit
    else if ((int) C >= 48 && (int) C <= 57) {
        val = 3;
}

    // Char is Symbol
    else {
        val = 4;
    }

    return val;
}


    public int PasswordStrength() {
        String s = this.Value;
        boolean UsedUpper = false;
        boolean UsedLower = false;
        boolean UsedNum = false;
        boolean UsedSym = false;
        int type;
        int Score = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            type = CharType(c);

            if (type == 1) UsedUpper = true;
            if (type == 2) UsedLower = true;
            if (type == 3) UsedNum = true;
            if (type == 4) UsedSym = true;
        }

        if (UsedUpper) Score += 1;
        if (UsedLower) Score += 1;
        if (UsedNum) Score += 1;
        if (UsedSym) Score += 1;

        if (s.length() >= 8) Score += 1;
        if (s.length() >= 16) Score += 1;

        return Score;
    }




    public String calculateScore() {
        int Score = this.PasswordStrength();
        double entropy = this.calculateEntropy();
        String visualization = this.visualizePasswordStrength();

        String entropyInfo = String.format("\nPassword Entropy: %.2f bits", entropy);

        if (entropy >= 80) {
            return visualization + entropyInfo + "\nThis is a very strong password with high entropy. Great job!";
        } else if (entropy >= 60) {
            return visualization + entropyInfo + "\nThis is a strong password with good entropy. You're on the right track!";
        } else if (entropy >= 40) {
            return visualization + entropyInfo + "\nThis password has moderate entropy. Consider making it stronger.";
        } else {
            return visualization + entropyInfo + "\nThis password has low entropy. It's recommended to choose a stronger password.";
        }
    }




/**
 * Creates a visual representation of the password strength based on entropy.
 * 
 * This method generates a string that visually represents the password strength
 * using a bar visualization and a descriptive label. The visualization consists of:
 * - A progress bar with filled (█) and empty (░) blocks scaled based on entropy
 * - A textual label categorizing the password as "Weak", "Medium", "Strong", or "Very Strong"
 * 
 * The strength categorization follows these entropy thresholds:
 * - Less than 40 bits: Weak
 * - 40-59 bits: Medium
 * - 60-79 bits: Strong
 * - 80+ bits: Very Strong
 * 
 * The visual bar scales the entropy value (dividing by 20) to determine how many
 * of the 6 possible blocks should be filled.
 *
 * @return A formatted string containing the visual representation of password strength
 *         in the format "[████░░] Strong" (example)
 * @see calculateEntropy()
 * @since 1.0.0
 */
public String visualizePasswordStrength() {
    double entropy = this.calculateEntropy();
    StringBuilder visualization = new StringBuilder("[");

    int filledBars = (int) Math.min(entropy / 20, 6); // Scale entropy to 0-6 range
    for (int i = 0; i < 6; i++) {
        if (i < filledBars) {
            visualization.append("█");
        } else {
            visualization.append("░");
        }
    }

    visualization.append("] ");

    if (entropy >= 80) {
        visualization.append("Very Strong");
    } else if (entropy >= 60) {
        visualization.append("Strong");
    } else if (entropy >= 40) {
        visualization.append("Medium");
    } else {
        visualization.append("Weak");
    }

    return visualization.toString();
}




    @Override
    public String toString() {
        return Value;
    }
}
