import com.example.Predator;
import com.example.Lion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {

    @Mock
    private Predator mockPredator;

    private Lion lion;

    @Test
    public void lionMaleShouldHaveMane() throws Exception {
        lion = new Lion("Самец", mockPredator);
        assertTrue(lion.doesHaveMane());
    }

    @Test
    public void lionFemaleShouldNotHaveMane() throws Exception {
        lion = new Lion("Самка", mockPredator);
        assertFalse(lion.doesHaveMane());
    }

    @Test
    public void lionWithInvalidSexShouldThrowException() {
        try {
            new Lion("Неизвестный", mockPredator);
            fail("Должно было выброситься исключение!");
        } catch (Exception e) {
            assertEquals("Используйте допустимые значения пола животного - самец или самка", e.getMessage());
        }
    }

    @Test
    public void getKittensShouldCallPredatorGetKittens() throws Exception {
        when(mockPredator.getKittens()).thenReturn(3);

        lion = new Lion("Самец", mockPredator);
        int kittensCount = lion.getKittens();

        assertEquals(3, kittensCount);
        verify(mockPredator, times(1)).getKittens();
    }

    @Test
    public void getFoodShouldCallPredatorEatMeat() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(mockPredator.eatMeat()).thenReturn(expectedFood);

        lion = new Lion("Самец", mockPredator);
        List<String> actualFood = lion.getFood();

        assertEquals(expectedFood, actualFood);
        verify(mockPredator, times(1)).eatMeat();
    }

}
