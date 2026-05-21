import com.example.Feline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)

public class FelineFoodParametrizedNegativeTest {

    private final String animalKind;

    public FelineFoodParametrizedNegativeTest(String animalKind) {
        this.animalKind = animalKind;
    }

    @Parameterized.Parameters(name = "{index}: Тип животного: {0} -> ожидаем исключение")
    public static Collection<Object[]> negativeData() {
        return Arrays.asList(new Object[][]{
                {"Всеядное"},
                {"ТРАВОЯДНОЕ"},
                {""},
                {null},
        });
    }

    @Test
    public void testGetFoodShouldThrowExceptionWhenInvalidAnimalKind(){
        Feline feline = new Feline();
        try {
            feline.getFood(animalKind);
            fail("Expected exception but none was thrown for: " + animalKind);
        } catch (Exception e) {
            assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник", e.getMessage());
        }
    }
}

