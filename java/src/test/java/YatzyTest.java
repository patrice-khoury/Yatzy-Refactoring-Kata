import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class YatzyTest {
	private Yatzy yatzy;
	
	@BeforeEach
	public void setup() {
		yatzy = new Yatzy();
	}
	
	private void testYatzy(final int expectedResult, int result) {
		assertThat(result).isEqualTo(expectedResult);
	}
	
    @Test
    public void chance_scores_sum_of_all_dice() {
    	testYatzy(15, yatzy.chance(2,3,4,5,1));
    	testYatzy(16, yatzy.chance(3,3,4,5,1));
    }

    @Test 
    public void yatzy_scores_50() {
        testYatzy(50, yatzy.yatzy(4,4,4,4,4));
        testYatzy(50, yatzy.yatzy(6,6,6,6,6));
        testYatzy(0, yatzy.yatzy(6,6,6,6,3));
    }

    @Test 
    public void test_1s() {
    	testYatzy(1, yatzy.ones(1,2,3,4,5));
        testYatzy(2, yatzy.ones(1,2,1,4,5));
        testYatzy(0, yatzy.ones(6,2,2,4,5));
        testYatzy(4, yatzy.ones(1,2,1,1,1));
    }

    @Test
    public void test_2s() {
        testYatzy(4, yatzy.twos(1,2,3,2,6));
        testYatzy(10, yatzy.twos(2,2,2,2,2));
    }

    @Test
    public void test_threes() {
        testYatzy(6, yatzy.threes(1,2,3,2,3));
        testYatzy(12, yatzy.threes(2,3,3,3,3));
    }

    @Test
    public void fours_test() {
        testYatzy(12, yatzy.fours(4,4,4,5,5));
        testYatzy(8, yatzy.fours(4,4,5,5,5));
        testYatzy(4, yatzy.fours(4,5,5,5,5));
    }

    @Test
    public void fives() {
        testYatzy(10, yatzy.fives(4,4,4,5,5));
        testYatzy(15, yatzy.fives(4,4,5,5,5));
        testYatzy(20, yatzy.fives(4,5,5,5,5));
    }

    @Test
    public void sixes_test() {
        testYatzy(0, yatzy.sixes(4,4,4,5,5));
        testYatzy(6, yatzy.sixes(4,4,6,5,5));
        testYatzy(18, yatzy.sixes(6,5,6,6,5));
    }

    @Test
    public void one_pair() {
        testYatzy(6, yatzy.score_pair(3,4,3,5,6));
        testYatzy(10, yatzy.score_pair(5,3,3,3,5));
        testYatzy(12, yatzy.score_pair(5,3,6,6,5));
        testYatzy(6, yatzy.score_pair(3,3,3,3,1));
    }

    @Test
    public void two_Pair() {
        testYatzy(16, yatzy.two_pair(3,3,5,4,5));
        testYatzy(16, yatzy.two_pair(3,3,5,5,5));
    }

    @Test
    public void three_of_a_kind() {
        testYatzy(9, yatzy.three_of_a_kind(3,3,3,4,5));
        testYatzy(15, yatzy.three_of_a_kind(5,3,5,4,5));
        testYatzy(9, yatzy.three_of_a_kind(3,3,3,3,5));
    }

    @Test
    public void four_of_a_knd() {
        testYatzy(12, yatzy.four_of_a_kind(3,3,3,3,5));
        testYatzy(20, yatzy.four_of_a_kind(5,5,5,4,5));
        testYatzy(9, yatzy.three_of_a_kind(3,3,3,3,3));
    }

    @Test
    public void smallStraight() {
        testYatzy(15, yatzy.smallStraight(1,2,3,4,5));
        testYatzy(15, yatzy.smallStraight(2,3,4,5,1));
        testYatzy(0, yatzy.smallStraight(1,2,2,4,5));
    }

    @Test
    public void largeStraight() {
        testYatzy(20, yatzy.largeStraight(6,2,3,4,5));
        testYatzy(20, yatzy.largeStraight(2,3,4,5,6));
        testYatzy(0, yatzy.largeStraight(1,2,2,4,5));
    }

    @Test
    public void fullHouse() {
        testYatzy(18, yatzy.fullHouse(6,2,2,2,6));
        testYatzy(0, yatzy.fullHouse(2,3,4,5,6));
    }
}
