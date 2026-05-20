import com.example.Feline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class FelineFoodParametrizedTest {

    private final String animalKind;
    private final List<String> expectedFood;
    private final boolean shouldThrowException;

    public FelineFoodParametrizedTest(String animalKind, List<String> expectedFood, boolean shouldThrowException) {
        this.animalKind = animalKind;
        this.expectedFood = expectedFood;
        this.shouldThrowException = shouldThrowException;
    }

    @Parameterized.Parameters(name = "{index}: Тип животного: {0} -> ожидаемая еда: {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Травоядное", List.of("Трава", "Различные растения"), false},
                {"Хищник", List.of("Животные", "Птицы", "Рыба"), false},
                {"Всеядное", null, true},
                {"ТРАВОЯДНОЕ", null, true},
                {"", null, true},
                {null, null, true},
        });
    }

    @Test
    public void testGetFood() throws Exception {
        Feline feline = new Feline();

        if (shouldThrowException) {
            try {
                feline.getFood(animalKind);
                fail("Expected exception was not thrown");
            } catch (Exception e) {
                assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник", e.getMessage());
            }
        } else {
            List<String> actualFood = feline.getFood(animalKind);
            assertEquals(expectedFood, actualFood);
        }
    }

}
