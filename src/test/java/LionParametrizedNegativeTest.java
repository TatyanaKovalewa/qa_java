import com.example.Predator;
import com.example.Lion;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class LionParametrizedNegativeTest {

    private Predator mockPredator;

    private final String sex;

    public LionParametrizedNegativeTest(String sex) {
        this.sex = sex;
    }

    @Parameterized.Parameters(name = "Пол: {0} -> ожидаем исключение")
    public static Collection<Object[]> negativeData() {
        return Arrays.asList(new Object[][]{
                {"Неизвестный"},
                {"самец"},
                {"САМЕЦ"},
                {""},
                {null},
        });

    }

    @Before
    public void init() {
        mockPredator = mock(Predator.class);
    }

    @Test
    public void testLionCreationShouldThrowExceptionWhenInvalidSex(){
        try {
            new Lion(sex, mockPredator);
            fail("Expected exception was not thrown for sex: " + sex);
        } catch (Exception e) {
            assertEquals("Используйте допустимые значения пола животного - самец или самка", e.getMessage());
        }
    }

}
