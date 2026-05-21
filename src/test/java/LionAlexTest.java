import com.example.Predator;
import com.example.LionAlex;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LionAlexTest {

    @Mock
    private Predator mockPredator;

    private LionAlex alexLion;

    @Before
    public void setUp() throws Exception {
        alexLion = new LionAlex(mockPredator);
    }

    @Test
    public void testConstructorShouldCreateMaleLion() {
        assertTrue(alexLion.doesHaveMane());
    }

    @Test
    public void testGetFriendsShouldReturnCorrectFriendsList() {
        List<String> friends = alexLion.getFriends();
        List<String> expectedFriends = List.of("Марти", "Глория", "Мелман");
        assertEquals(expectedFriends, friends);
    }

    @Test
    public void testGetPlaceOfLivingShouldReturnNYZoo() {
        String place = alexLion.getPlaceOfLiving();
        assertEquals("Нью-Йоркский зоопарк", place);
    }

    @Test
    public void testGetKittensShouldReturnZero() {
        int kittens = alexLion.getKittens();
        assertEquals(0, kittens);
    }

    @Test
    public void testGetFoodShouldDelegateToPredator() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(mockPredator.eatMeat()).thenReturn(expectedFood);
        List<String> actualFood = alexLion.getFood();
        assertEquals(expectedFood, actualFood);
    }

    @Test
    public void testGetFoodShouldCallEatMeatOnce() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(mockPredator.eatMeat()).thenReturn(expectedFood);
        alexLion.getFood();
        verify(mockPredator, times(1)).eatMeat();
    }

}
