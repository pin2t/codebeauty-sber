import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextServerTest {
    @Test
    public void testValid() {
        assertTrue(TextServer.isCorrect("(text)"));
        assertTrue(TextServer.isCorrect("(((text)))"));
        assertTrue(TextServer.isCorrect("(text( (text) ) text )"));
        assertTrue(TextServer.isCorrect("\n\n\n(\ntext\n)"));
    }

    @Test
    public void testInvalid() {
        assertFalse(TextServer.isCorrect("(text)))"));
        assertFalse(TextServer.isCorrect("()"));
        assertFalse(TextServer.isCorrect("("));
        assertFalse(TextServer.isCorrect("   "));
        assertFalse(TextServer.isCorrect("\t"));
        assertFalse(TextServer.isCorrect("\n\n\n(\n\n)"));
        assertFalse(TextServer.isCorrect("(()text)"));
    }
}