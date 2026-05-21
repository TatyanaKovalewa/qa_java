import com.example.Feline;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FelineTest {

    private final Feline feline = new Feline();

    @Test
    public void testEatMeatShouldReturnMeatList() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        List<String> actualFood = feline.eatMeat();
        assertEquals(expectedFood, actualFood);
    }

    @Test
    public void testGetFamilyShouldReturnCatsFamily() {
        String family = feline.getFamily();
        assertEquals("Кошачьи", family);
    }

    @Test
    public void testGetKittensDefaultShouldReturnOne() {
        int kittens = feline.getKittens();
        assertEquals(1, kittens);  // По умолчанию возвращает 1
    }

    @Test
    public void testGetKittensWithCountShouldReturnSpecifiedCount() {
        int expectedCount = 5;
        int kittens = feline.getKittens(expectedCount);
        assertEquals(expectedCount, kittens);
    }

    @Test
    public void testGetFoodWithHerbivoreShouldReturnPlants() throws Exception {
        List<String> food = feline.getFood("Травоядное");
        assertEquals(List.of("Трава", "Различные растения"), food);
    }

    @Test
    public void testGetFoodWithUnknownKindShouldThrowException() {
        try {
            feline.getFood("Неизвестное");
            fail("Должно было выброситься исключение!");
        } catch (Exception e) {
            assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник",
                    e.getMessage());
        }
    }

}
