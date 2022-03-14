import java.util.function.BiPredicate;

public enum StraightType {
	SMALL((maxValue, length)-> maxValue == length),
	LARGE((maxValue, length)-> maxValue > length);
	
	private final BiPredicate<Integer, Integer> valueTest;

	private StraightType(final BiPredicate<Integer, Integer> valueTest) {
		this.valueTest = valueTest;
	}

	public BiPredicate<Integer, Integer> getValueTest() {
		return valueTest;
	}
}
