import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Yatzy {
	private int sums(final int valueToSum, final int... dice) {
		return Arrays.stream(dice)
			.filter(value-> value == valueToSum)
			.sum();
	}
	
	private Map<Integer, Integer> getStatistics(final int... dice) {
		final Map<Integer, Integer> statistics = new HashMap<>(); 
		
		Arrays.stream(dice).forEach(value-> {
			statistics.merge(value, 1, Integer::sum);
		});
		
		return statistics;
	}
	
	public int chance(final int... dice) {
		return Arrays.stream(dice).sum();
	}

	public int yatzy(final int... dice) {
		return Arrays.stream(dice).distinct().count() > 1 ? 0: 50;
	}

	public int ones(final int... dice) {
		return sums(1, dice);
	}

	public int twos(final int... dice) {
		return sums(2, dice);
	}

	public int threes(final int... dice) {
		return sums(3, dice);
	}

	public int fours(final int... dice) {
		return sums(4, dice);
	}

	public int fives(final int... dice) {
		return sums(5, dice);
	}

	public int sixes(final int... dice) {
		return sums(6, dice);
	}
	
	public int score_pair(final int... dice) {
		final Map<Integer, Integer> statistics = getStatistics(dice);
		
		return statistics.entrySet().stream()
			.filter((statistic)-> statistic.getValue() == 2)
			.map(Entry::getKey)
			.max(Integer::compare)
			.map(value -> value * 2)
			.orElse(0);
	}

	public int two_pair(final int... dice) {
		final Map<Integer, Integer> statistics = getStatistics(dice);
		
		return statistics.entrySet().stream()
				.filter((statistic)-> statistic.getValue() > 1)
				.map(Entry::getKey)
				.map(value -> value * 2)
				.mapToInt(Integer::valueOf)
				.sum();
	}

	public int four_of_a_kind(final int _1, final int _2, final int d3, final int d4, final int d5) {
		int[] tallies;
		tallies = new int[6];
		tallies[_1-1]++;
		tallies[_2-1]++;
		tallies[d3-1]++;
		tallies[d4-1]++;
		tallies[d5-1]++;
		for (int i = 0; i < 6; i++) {
			if (tallies[i] >= 4) {
				return (i+1) * 4;
			}
		}
		return 0;
	}

	public int three_of_a_kind(final int d1, final int d2, final int d3, final int d4, final int d5) {
		int[] t;
		t = new int[6];
		t[d1-1]++;
		t[d2-1]++;
		t[d3-1]++;
		t[d4-1]++;
		t[d5-1]++;
		for (int i = 0; i < 6; i++) {
			if (t[i] >= 3) {
				return (i+1) * 3;
			}
		}
		return 0;
	}

	public int smallStraight(final int d1, final int d2, final int d3, final int d4, final int d5) {
		int[] tallies;
		tallies = new int[6];
		tallies[d1-1] += 1;
		tallies[d2-1] += 1;
		tallies[d3-1] += 1;
		tallies[d4-1] += 1;
		tallies[d5-1] += 1;
		if (tallies[0] == 1 &&
				tallies[1] == 1 &&
				tallies[2] == 1 &&
				tallies[3] == 1 &&
				tallies[4] == 1) {
			return 15;
		}
		return 0;
	}

	public int largeStraight(final int d1, final int d2, final int d3, final int d4, final int d5) {
		int[] tallies;
		tallies = new int[6];
		tallies[d1-1] += 1;
		tallies[d2-1] += 1;
		tallies[d3-1] += 1;
		tallies[d4-1] += 1;
		tallies[d5-1] += 1;
		if (tallies[1] == 1 &&
				tallies[2] == 1 &&
				tallies[3] == 1 &&
				tallies[4] == 1
				&& tallies[5] == 1) {
			return 20;
		}
		return 0;
	}

	public int fullHouse(final int d1, final int d2, final int d3, final int d4, final int d5) {
		int[] tallies;
		boolean _2 = false;
		int i;
		int _2_at = 0;
		boolean _3 = false;
		int _3_at = 0;

		tallies = new int[6];
		tallies[d1-1] += 1;
		tallies[d2-1] += 1;
		tallies[d3-1] += 1;
		tallies[d4-1] += 1;
		tallies[d5-1] += 1;

		for (i = 0; i != 6; i += 1) {
			if (tallies[i] == 2) {
				_2 = true;
				_2_at = i+1;
			}
		}

		for (i = 0; i != 6; i += 1) {
			if (tallies[i] == 3) {
				_3 = true;
				_3_at = i+1;
			}
		}

		if (_2 && _3) {
			return _2_at * 2 + _3_at * 3;
		}
		return 0;
	}
}