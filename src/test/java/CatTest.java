import com.example.Feline;
import com.example.Cat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CatTest {

    @Mock
    private Feline mockFeline;

    private Cat cat;

    @Before
    public void setUp() {
        cat = new Cat(mockFeline);  // Внедряем мок в кота
    }

    @Test
    public void testGetSound_ShouldReturnMeow() {
        String sound = cat.getSound();
        assertEquals("Мяу", sound);
    }

    @Test
    public void testGetFood_ShouldReturnPredatorFood() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(mockFeline.eatMeat()).thenReturn(expectedFood);
        List<String> actualFood = cat.getFood();
        assertEquals(expectedFood, actualFood);
        verify(mockFeline, times(1)).eatMeat();
    }

    @Test
    public void testGetFood_WhenFelineThrowsException_ShouldPropagateException()  throws Exception {
        when(mockFeline.eatMeat()).thenThrow(new RuntimeException("Ошибка БД"));

        try {
            cat.getFood();
            fail("Должно было выброситься исключение!");
        } catch (Exception e) {
            assertEquals("Ошибка БД", e.getMessage());
        }
    }

}
