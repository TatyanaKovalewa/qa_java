import com.example.Feline;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FelineTest {

    @Spy
    private Feline feline = new Feline();

    @Test
    public void testEatMeatShouldReturnMeatList() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        List<String> actualFood = feline.eatMeat();
        assertEquals(expectedFood, actualFood);
    }

    @Test
    public void testEatMeatShouldCallGetFoodWithPredator() throws Exception {
        feline.eatMeat();
        verify(feline, times(1)).getFood("Хищник");
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
