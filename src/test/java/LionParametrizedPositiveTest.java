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
public class LionParametrizedPositiveTest {

    private Predator mockPredator;

    private final String sex;
    private final boolean expectedHasMane;

    public LionParametrizedPositiveTest(String sex, boolean expectedHasMane) {
        this.sex = sex;
        this.expectedHasMane = expectedHasMane;
    }

    @Parameterized.Parameters(name = "Пол: {0} -> имеет гриву: {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Самец", true},
                {"Самка", false},
        });
    }

    @Before
    public void init() {
        mockPredator = mock(Predator.class);
    }

    @Test
    public void testLionCreationShouldReturnCorrectMane() throws Exception {
        Lion lion = new Lion(sex, mockPredator);
        assertEquals(expectedHasMane, lion.doesHaveMane());
    }

}
