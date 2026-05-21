import com.example.Feline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class FelineFoodParametrizedPositiveTest {

    private final String animalKind;
    private final List<String> expectedFood;

    public FelineFoodParametrizedPositiveTest(String animalKind, List<String> expectedFood) {
        this.animalKind = animalKind;
        this.expectedFood = expectedFood;
    }

    @Parameterized.Parameters(name = "{index}: Тип животного: {0} -> ожидаемая еда: {1}")
    public static Collection<Object[]> positiveData() {
        return Arrays.asList(new Object[][]{
                {"Травоядное", List.of("Трава", "Различные растения")},
                {"Хищник", List.of("Животные", "Птицы", "Рыба")},
        });
    }

    @Test
    public void testGetFoodShouldReturnCorrectFood() throws Exception {
        Feline feline = new Feline();
        List<String> actualFood = feline.getFood(animalKind);
        assertEquals(expectedFood, actualFood);
    }

}
