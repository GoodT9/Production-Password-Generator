import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class GeneratorTest {
    private Generator generator;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


/**
 * Tests the generation of a password with the correct length.
 *
 * This test method verifies that the Generator class correctly produces
 * a password of the specified length when all character types (uppercase,
 * lowercase, numbers, and symbols) are enabled.
 *
 * @throws AssertionError if the generated password length does not match the expected length
 * @since 1.0.0
 * @see Generator
 * @see Password
 */
@Test
public void testGeneratePasswordWithCorrectLength() {
    Generator generator = new Generator(true, true, true, true);
    int expectedLength = 10;
    Password generatedPassword = generator.GeneratePassword(expectedLength);
    assertEquals(expectedLength, generatedPassword.Length);
}

/**
 * Tests if the main loop displays useful information when option 3 is selected.
 * 
 * This test method simulates user input to select option 3 (display password security tips)
 * followed by option 4 (exit the program) in the main loop. It then verifies that
 * the expected output is displayed, including password security tips and a closing message.
 * 
 * The test uses System.setIn() and System.setOut() to redirect input and output streams,
 * allowing for the capture and verification of the program's output.
 * 
 * @throws AssertionError if the expected output is not present in the program's output
 */
@Test
public void testMainLoopDisplaysUsefulInfoWhenOption3Selected() {
    ByteArrayInputStream in = new ByteArrayInputStream("3\n4\n".getBytes());
    System.setIn(in);

    Generator generator = new Generator(new Scanner(System.in));

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    generator.mainLoop();

    String output = outContent.toString();
    assertTrue(output.contains("=== Password Security Tips ==="));
    assertTrue(output.contains("Closing the program bye bye!"));

    System.setIn(System.in);
    System.setOut(System.out);
}

/**
 * Tests the generation of a password containing only lowercase letters.
 * 
 * This test method verifies that the Generator class correctly produces
 * a password of the specified length using only lowercase letters when
 * configured to do so.
 * 
 * @throws AssertionError if the generated password does not meet the expected criteria
 */
@Test
public void testGeneratePasswordWithOnlyLowercase() {
    Generator generator = new Generator(false, true, false, false);
    int expectedLength = 10;
    Password generatedPassword = generator.GeneratePassword(expectedLength);
    assertEquals(expectedLength, generatedPassword.Length);
    assertTrue(generatedPassword.toString().matches("^[a-z]+$"));
}
}
