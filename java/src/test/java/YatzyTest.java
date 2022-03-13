import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class YatzyTest {
	private Yatzy yatzy;
	
	@Before
	public void setup() {
		yatzy = new Yatzy();
	}
	
    @Test
    public void chance_scores_sum_of_all_dice() {
        int expected = 15;
        int actual = yatzy.chance(2,3,4,5,1);
        assertEquals(expected, actual);
        assertEquals(16, yatzy.chance(3,3,4,5,1));
    }

    @Test 
    public void yatzy_scores_50() {
        int expected = 50;
        int actual = yatzy.yatzy(4,4,4,4,4);
        assertEquals(expected, actual);
        assertEquals(50, yatzy.yatzy(6,6,6,6,6));
        assertEquals(0, yatzy.yatzy(6,6,6,6,3));
    }

    @Test 
    public void test_1s() {
        assertTrue(yatzy.ones(1,2,3,4,5) == 1);
        assertEquals(2, yatzy.ones(1,2,1,4,5));
        assertEquals(0, yatzy.ones(6,2,2,4,5));
        assertEquals(4, yatzy.ones(1,2,1,1,1));
    }

    @Test
    public void test_2s() {
        assertEquals(4, yatzy.twos(1,2,3,2,6));
        assertEquals(10, yatzy.twos(2,2,2,2,2));
    }

    @Test
    public void test_threes() {
        assertEquals(6, yatzy.threes(1,2,3,2,3));
        assertEquals(12, yatzy.threes(2,3,3,3,3));
    }

    @Test
    public void fours_test() {
        assertEquals(12, yatzy.fours(4,4,4,5,5));
        assertEquals(8, yatzy.fours(4,4,5,5,5));
        assertEquals(4, yatzy.fours(4,5,5,5,5));
    }

    @Test
    public void fives() {
        assertEquals(10, yatzy.fives(4,4,4,5,5));
        assertEquals(15, yatzy.fives(4,4,5,5,5));
        assertEquals(20, yatzy.fives(4,5,5,5,5));
    }

    @Test
    public void sixes_test() {
        assertEquals(0, yatzy.sixes(4,4,4,5,5));
        assertEquals(6, yatzy.sixes(4,4,6,5,5));
        assertEquals(18, yatzy.sixes(6,5,6,6,5));
    }

    @Test
    public void one_pair() {
        assertEquals(6, yatzy.score_pair(3,4,3,5,6));
        assertEquals(10, yatzy.score_pair(5,3,3,3,5));
        assertEquals(12, yatzy.score_pair(5,3,6,6,5));
    }

    @Test
    public void two_Pair() {
        assertEquals(16, yatzy.two_pair(3,3,5,4,5));
        assertEquals(16, yatzy.two_pair(3,3,5,5,5));
    }

    @Test
    public void three_of_a_kind() {
        assertEquals(9, yatzy.three_of_a_kind(3,3,3,4,5));
        assertEquals(15, yatzy.three_of_a_kind(5,3,5,4,5));
        assertEquals(9, yatzy.three_of_a_kind(3,3,3,3,5));
    }

    @Test
    public void four_of_a_knd() {
        assertEquals(12, yatzy.four_of_a_kind(3,3,3,3,5));
        assertEquals(20, yatzy.four_of_a_kind(5,5,5,4,5));
        assertEquals(9, yatzy.three_of_a_kind(3,3,3,3,3));
    }

    @Test
    public void smallStraight() {
        assertEquals(15, yatzy.smallStraight(1,2,3,4,5));
        assertEquals(15, yatzy.smallStraight(2,3,4,5,1));
        assertEquals(0, yatzy.smallStraight(1,2,2,4,5));
    }

    @Test
    public void largeStraight() {
        assertEquals(20, yatzy.largeStraight(6,2,3,4,5));
        assertEquals(20, yatzy.largeStraight(2,3,4,5,6));
        assertEquals(0, yatzy.largeStraight(1,2,2,4,5));
    }

    @Test
    public void fullHouse() {
        assertEquals(18, yatzy.fullHouse(6,2,2,2,6));
        assertEquals(0, yatzy.fullHouse(2,3,4,5,6));
    }
}
