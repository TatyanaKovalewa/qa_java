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
    public void testConstructor_ShouldCreateMaleLion() {
        assertTrue(alexLion.doesHaveMane());
    }

    @Test
    public void testGetFriends_ShouldReturnCorrectFriendsList() {
        List<String> friends = alexLion.getFriends();

        List<String> expectedFriends = List.of("Марти", "Глория", "Мелман");
        assertEquals(expectedFriends, friends);
        assertEquals(3, friends.size());
        assertTrue(friends.contains("Марти"));
        assertTrue(friends.contains("Глория"));
        assertTrue(friends.contains("Мелман"));
    }

    @Test
    public void testGetPlaceOfLiving_ShouldReturnNYZoo() {
        String place = alexLion.getPlaceOfLiving();
        assertEquals("Нью-Йоркский зоопарк", place);
    }

    @Test
    public void testGetKittens_ShouldReturnZero() {
        int kittens = alexLion.getKittens();
        assertEquals(0, kittens);
    }

    @Test
    public void testGetFood_ShouldDelegateToPredator() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(mockPredator.eatMeat()).thenReturn(expectedFood);
        List<String> actualFood = alexLion.getFood();
        assertEquals(expectedFood, actualFood);
        verify(mockPredator, times(1)).eatMeat();
    }

}
