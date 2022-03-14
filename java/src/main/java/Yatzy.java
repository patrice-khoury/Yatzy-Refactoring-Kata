import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class Yatzy {
	private final BiPredicate<Entry<Integer, Integer>, Integer> filterNumberOfEntries = (statistic, nbEntries)-> statistic.getValue() >= nbEntries;
	private final BiFunction<Integer, Integer, Integer> sumEntries = (value, number)-> value * number;
	
	private int sums(final int... dice) {
		return Arrays.stream(dice)
			.sum();
	}
	
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
	
	private int computeOfAKind(final int numberOfAKind, final int... dice) {
		final Map<Integer, Integer> statistics = getStatistics(dice);
		
		return statistics.entrySet().stream()
			.filter(statistic-> filterNumberOfEntries.test(statistic, numberOfAKind))
			.map(Entry::getKey)
			.map(value -> sumEntries.apply(value, numberOfAKind))
			.mapToInt(Integer::valueOf)
			.sum();
	}
	
	private int straight(final StraightType type, final int... dice) {
		int result = 0;
		
		final Map<Integer, Integer> statistics = getStatistics(dice);
		if (statistics.entrySet().size() == dice.length) {
			final int maxValue = statistics.keySet().stream().max(Integer::compare).get();
			if (type.getValueTest().test(maxValue, dice.length)) {
				result = sums(dice);
			}
		}
		return result;
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
	
	public int scorePair(final int... dice) {
		final Map<Integer, Integer> statistics = getStatistics(dice);
		
		return statistics.entrySet().stream()
			.filter(statistic-> filterNumberOfEntries.test(statistic, 2))
			.map(Entry::getKey)
			.max(Integer::compare)
			.map(value ->sumEntries.apply(value, 2))
			.orElse(0);
	}

	public int twoPair(final int... dice) {
		return computeOfAKind(2, dice);
	}

	public int threeOfAKind(final int... dice) {
		return computeOfAKind(3, dice);
	}
	
	public int fourOfAKind(final int... dice) {
		return computeOfAKind(4, dice);
	}
	
	public int smallStraight(final int... dice) {
		return straight(StraightType.SMALL, dice);
	}

	public int largeStraight(final int... dice) {
		return straight(StraightType.LARGE, dice);
	}

	public int fullHouse(final int... dice) {
		int result = 0;
		
		final Map<Integer, Integer> statistics = getStatistics(dice);
		if (statistics.entrySet().size() == 2) {
			final int kinds = statistics.values().stream()
				.sorted(Comparator.reverseOrder())
				.reduce((nb1, nb2)-> nb1 - nb2)
				.get();
			if (kinds == 1) {
				result = sums(dice);
			}
		}
		return result;
	}
}