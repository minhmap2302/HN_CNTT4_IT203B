package Bai1.Test;

import Bai1.Main.ValidUserName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestValidUserName {

    ValidUserName validUserName = new ValidUserName();

    @Test
    void testFalse() {
        // Arrange
        String name = null;

        // Act
        boolean result = validUserName.Test(name);

        // Assert
        assertFalse(result);
    }
}