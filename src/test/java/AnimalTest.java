import com.example.Animal;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Тесты базового класса Animal.
 *
 * Наследники переопределяют getFamily(), поэтому через них реализация из Animal
 * не выполняется — её нужно проверять на объекте самого Animal.
 */
public class AnimalTest {

    private static final String EXPECTED_FAMILIES =
            "Существует несколько семейств: заячьи, беличьи, мышиные, кошачьи, псовые, медвежьи, куньи";

    private final Animal animal = new Animal();

    @Test
    public void getFamilyShouldReturnListOfFamilies() {
        assertEquals(EXPECTED_FAMILIES, animal.getFamily());
    }

    @Test
    public void getFoodForHerbivoreShouldReturnPlants() throws Exception {
        assertEquals(List.of("Трава", "Различные растения"), animal.getFood("Травоядное"));
    }

    @Test
    public void getFoodForPredatorShouldReturnMeat() throws Exception {
        assertEquals(List.of("Животные", "Птицы", "Рыба"), animal.getFood("Хищник"));
    }

    @Test
    public void getFoodForUnknownKindShouldThrowException() {
        Exception thrown = assertThrows(Exception.class, () -> animal.getFood("Насекомое"));
        assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник",
                thrown.getMessage());
    }
}
