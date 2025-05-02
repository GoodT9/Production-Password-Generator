import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class PasswordTest {

    /**
     * Tests the password strength calculation for a password containing only lowercase letters.
     * 
     * This test verifies that the PasswordStrength method of the Password class
     * correctly calculates the strength of a password that consists of only
     * lowercase letters. According to the password strength criteria, such a
     * password should have a strength of 1.
     *
     * @throws AssertionError if the calculated password strength does not match
     *         the expected value, indicating an error in the strength calculation logic.
     * @see Password#PasswordStrength()
     * @since 1.0
     */
    @Test
    void testPasswordStrengthWithOnlyLowercase() {
        Password password = new Password("abcdefgh");
        assertEquals(1, password.PasswordStrength(), "Password with only lowercase letters should have a strength of 1");

/**
 * Tests the calculateScore method for a password with low entropy.
 * 
 * This test verifies that the calculateScore method of the Password class
 * correctly generates a descriptive string that includes appropriate
 * visualization, entropy information, and guidance text for a password
 * with entropy less than 40 bits. The expected output should include
 * a weak visualization indicator and recommendation to choose a stronger
 * password.
 *
 * @throws AssertionError if the calculated score string does not contain
 *         the expected components for a low entropy password, indicating
 *         an error in the score calculation or formatting logic.
 * @see Password#calculateScore()
 * @since 1.0.0
 */
@Test
void testCalculateScoreForLowEntropyPassword() {
    Password password = new Password("123456");
    String scoreOutput = password.calculateScore();
    
    // Verify the output contains the expected components for a low entropy password
    assertTrue(scoreOutput.contains("[░░░░░░] Weak"),
            "Password score output should contain weak visualization for low entropy password");
    assertTrue(scoreOutput.contains("Password Entropy:"),
            "Password score output should contain entropy information");
    assertTrue(scoreOutput.contains("low entropy"),
            "Password score output should indicate low entropy");
    assertTrue(scoreOutput.contains("recommended to choose a stronger password"),
            "Password score output should recommend choosing a stronger password");
}
}

/**
 * Tests the CharType method of the Password class for lowercase letters.
 * This test verifies that the CharType method correctly identifies and
 * categorizes lowercase letters (a-z) by returning the expected value of 2.
 *
 * @throws AssertionError if any of the assertions fail, indicating that
 *         the CharType method is not correctly identifying lowercase letters.
 */
@Test
void testCharTypeForLowercaseLetters() {
    Password password = new Password("TestPassword123");
    // Test lowercase letters
    assertEquals(2, password.CharType('a'), "CharType should return 2 for lowercase letter 'a'");
    assertEquals(2, password.CharType('m'), "CharType should return 2 for lowercase letter 'm'");
    assertEquals(2, password.CharType('z'), "CharType should return 2 for lowercase letter 'z'");
}

/**
 * Tests the password strength calculation for a password meeting all strength criteria.
 * 
 * This test verifies that the PasswordStrength method of the Password class
 * correctly calculates the strength of a password that meets all the defined
 * criteria for a strong password. The password used in this test includes:
 * - Uppercase letters
 * - Lowercase letters
 * - Numbers
 * - Symbols
 * - Length >= 16 characters
 * 
 * According to the password strength criteria, such a password should have
 * the maximum strength of 6.
 *
 * @throws AssertionError if the calculated password strength does not match
 *         the expected value, indicating an error in the strength calculation logic.
 * @see Password#PasswordStrength()
 * @since 1.0
 */
@Test
void testPasswordStrengthWithAllCriteria() {
    Password password = new Password("Ab1!Cdef2#Ghij3$Klmn");
    assertEquals(6, password.PasswordStrength(), "Password with uppercase, lowercase, numbers, symbols, and length >= 16 should have a strength of 6");
}

/**
 * Tests the password strength calculation for a password containing only symbols.
 * 
 * This test verifies that the PasswordStrength method of the Password class
 * correctly calculates the strength of a password that consists of only
 * symbols. According to the password strength criteria, such a password
 * should have a strength of 2 (1 point for symbols, 1 point for length >= 8).
 *
 * @throws AssertionError if the calculated password strength does not match
 *         the expected value, indicating an error in the strength calculation logic.
 * @see Password#PasswordStrength()
 * @since 1.0
 */
@Test
void testPasswordStrengthWithOnlySymbols() {
    Password password = new Password("!@#$%^&*");
    assertEquals(2, password.PasswordStrength(), "Password with only symbols and length 8 should have a strength of 2");
}

/**
 * Tests the password strength calculation for a password with exactly 10 characters.
 * 
 * This test verifies that the PasswordStrength method of the Password class
 * correctly calculates the strength of a password that is exactly 10 characters long
 * and includes uppercase letters, lowercase letters, numbers, and symbols.
 * According to the password strength criteria, such a password should have
 * a strength of 5 (4 points for character types + 1 point for length >= 8).
 *
 * @throws AssertionError if the calculated password strength does not match
 *         the expected value, indicating an error in the strength calculation logic.
 * @see Password#PasswordStrength()
 * @since 1.0
 */
@Test
void testPasswordStrengthWithExactly10Characters() {
    Password password = new Password("P@ssw0rd1!");
    assertEquals(5, password.PasswordStrength(), "Password with 10 characters including uppercase, lowercase, numbers, and symbols should have a strength of 5");
}

/**
 * Tests the password strength calculation for a password with exactly 20 characters.
 * 
 * This test verifies that the PasswordStrength method of the Password class
 * correctly calculates the strength of a password that is exactly 20 characters long
 * and includes uppercase letters, lowercase letters, numbers, and symbols.
 * According to the password strength criteria, such a password should have
 * a strength of 6 (4 points for character types + 1 point for length >= 8 + 1 point for length >= 16).
 *
 * @throws AssertionError if the calculated password strength does not match
 *         the expected value, indicating an error in the strength calculation logic.
 * @see Password#PasswordStrength()
 * @since 1.0
 */
@Test
void testPasswordStrengthWithExactly20Characters() {
    Password password = new Password("P@ssw0rd1!Str0ng1234!");
    assertEquals(6, password.PasswordStrength(), "Password with 20 characters including uppercase, lowercase, numbers, and symbols should have a strength of 6");
}

/**
 * Tests the calculation of entropy for a password containing only lowercase letters.
 * This test verifies that the calculateEntropy method of the Password class
 * correctly computes the entropy for a simple lowercase password.
 *
 * The test creates a Password object with a known lowercase-only password,
 * calculates the expected entropy based on the password length and character set,
 * and compares it with the result of the calculateEntropy method.
 *
 * @throws AssertionError if the calculated entropy does not match the expected value
 *         within the specified delta, indicating an error in the entropy calculation.
 */
@Test
void testCalculateEntropyWithOnlyLowercase() {
    Password password = new Password("abcdefgh");
    double expectedEntropy = 8 * (Math.log(26) / Math.log(2)); // 8 characters, 26 possible lowercase letters
    assertEquals(expectedEntropy, password.calculateEntropy(), 0.001, "Password entropy calculation for lowercase-only password should match expected value");
}

/**
 * Tests the calculation of entropy for a password with mixed character types.
 * This test verifies that the calculateEntropy method of the Password class
 * correctly computes the entropy for a password containing uppercase letters,
 * lowercase letters, numbers, and symbols.
 *
 * The test creates a Password object with a known mixed-character password,
 * calculates the expected entropy based on the password length and character set,
 * and compares it with the result of the calculateEntropy method.
 *
 * @throws AssertionError if the calculated entropy does not match the expected value
 *         within the specified delta, indicating an error in the entropy calculation.
 */
@Test
void testCalculateEntropyWithMixedCharacters() {
    Password password = new Password("P@ssw0rd!");
    double expectedEntropy = 9 * (Math.log(94) / Math.log(2)); // 9 characters, 94 possible characters (26+26+10+32)
    assertEquals(expectedEntropy, password.calculateEntropy(), 0.001, "Password entropy calculation for mixed-character password should match expected value");
}

/**
 * Tests the CharType method of the Password class for uppercase letters and numbers.
 * This test verifies that the CharType method correctly identifies and
 * categorizes uppercase letters (A-Z) by returning the expected value of 1,
 * and numbers (0-9) by returning the expected value of 3.
 *
 * @throws AssertionError if any of the assertions fail, indicating that
 *         the CharType method is not correctly identifying uppercase letters or numbers.
 */
@Test
void testCharTypeForUppercaseLettersAndNumbers() {
    Password password = new Password("TestPassword123");
    // Test uppercase letters
    assertEquals(1, password.CharType('A'), "CharType should return 1 for uppercase letter 'A'");
    assertEquals(1, password.CharType('M'), "CharType should return 1 for uppercase letter 'M'");
    assertEquals(1, password.CharType('Z'), "CharType should return 1 for uppercase letter 'Z'");

    // Test numbers
    assertEquals(3, password.CharType('0'), "CharType should return 3 for number '0'");
    assertEquals(3, password.CharType('5'), "CharType should return 3 for number '5'");
    assertEquals(3, password.CharType('9'), "CharType should return 3 for number '9'");
}

/**
 * Tests the visual representation of password strength for a very strong password.
 * 
 * This test verifies that the visualizePasswordStrength method of the Password class
 * correctly generates a visual representation for a very strong password
 * with entropy greater than or equal to 100 bits. The expected visualization
 * should include a fully filled progress bar (six filled blocks) and the label "Very Strong".
 *
 * @throws AssertionError if the generated visualization does not match the expected format,
 *         indicating an error in the visualization logic.
 * @see Password#visualizePasswordStrength()
 * @since 1.0.0
 */
@Test
void testVisualizePasswordStrengthForVeryStrongPassword() {
    Password password = new Password("P@ssw0rd!Str0ng&C0mplex123!@#");
    String visualization = password.visualizePasswordStrength();
    assertEquals("[██████] Very Strong", visualization, 
            "Password with entropy >= 100 bits should be visualized with a full bar and 'Very Strong' label");
}

/**
 * Tests the visual representation of password strength for a strong password.
 * 
 * This test verifies that the visualizePasswordStrength method of the Password class
 * correctly generates a visual representation for a strong password
 * with entropy between 60 and 79 bits. The expected visualization
 * should include a partially filled progress bar (three to four filled blocks) and the label "Strong".
 *
 * @throws AssertionError if the generated visualization does not match the expected format,
 *         indicating an error in the visualization logic.
 * @see Password#visualizePasswordStrength()
 * @since 1.0.0
 */
@Test
void testVisualizePasswordStrengthForStrongPassword() {
    Password password = new Password("P@ssw0rd!2023");
    String visualization = password.visualizePasswordStrength();
    assertEquals("[████░░] Strong", visualization, 
            "Password with entropy between 60-79 bits should be visualized with four bars and 'Strong' label");
}
}