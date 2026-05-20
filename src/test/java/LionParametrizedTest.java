import com.example.Predator;
import com.example.Lion;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class LionParametrizedTest {

    private Predator mockPredator;

    private final String sex;
    private final boolean expectedHasMane;
    private final boolean shouldThrowException;

    public LionParametrizedTest(String sex, boolean expectedHasMane, boolean shouldThrowException) {
        this.sex = sex;
        this.expectedHasMane = expectedHasMane;
        this.shouldThrowException = shouldThrowException;
    }

    @Parameterized.Parameters(name = "Пол: {0} -> имеет гриву: {1}, исключение: {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Самец", true, false},
                {"Самка", false, false},
                {"Неизвестный", false, true},
                {"самец", false, true},
                {"САМЕЦ", false, true},
                {"", false, true},
                {null, false, true},
        });
    }

    @Before
    public void init() {
        mockPredator = mock(Predator.class);
    }

    @Test
    public void testLionCreation() throws Exception {
        if (shouldThrowException) {
            try {
                new Lion(sex, mockPredator);
                fail("Expected exception was not thrown for sex: " + sex);
            } catch (Exception e) {
                assertEquals("Используйте допустимые значения пола животного - самец или самка", e.getMessage());
            }
        } else {
            Lion lion = new Lion(sex, mockPredator);
            assertEquals(expectedHasMane, lion.doesHaveMane());
        }
    }

}
