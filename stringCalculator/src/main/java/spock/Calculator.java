package spock;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Calculator {

    private final List<Integer> accumulators;

    public Calculator() {
        this.accumulators = new ArrayList<>();
    }

    public static Calculator take(int accumulator) {
        return new Calculator(accumulator);
    }

    private Calculator(int accumulator) {
        this();
        add(accumulator);
    }

    public Calculator add(int x) {
        accumulators.add(x);
        return this;
    }

    public int add(String numbers) {
        int result = 0;
        String delimiter = ",";
        ArrayList<String> numberList = new ArrayList<>();

        if (numbers.length() == 0) {
            return result;
        } else {
            if (numbers.length() == 1) {
                result = Integer.parseInt(numbers);
                return result;
            }
        }
        // ToDo: extent to accommodate 2+ digit numbers

        if (numbers.contains(delimiter)) {
            try {
                numberList = Stream.of(numbers.split(delimiter))
                        .map(elem -> new String(elem))
                        .collect(Collectors.toCollection(ArrayList::new));
//                .collect(toCollection(ArrayList::new));

            } catch (Exception e) {
                // ToDo: add logging and messaging
                return 999;
            }
        }

        result = Integer.valueOf(numberList.get(0)) + Integer.valueOf(numberList.get(1));

        return result;

    }

    public Calculator subtract(int x) {
        accumulators.add(-x);
        return this;
    }

    public int calculate() {
        Integer result = 0;
        for (Integer accumulator : accumulators) {
            result += accumulator;
        }
        return result;
    }

    public Calculator multiply(int p) {
        int accumulator = calculate() * p;
        accumulators.clear();
        add(accumulator);
        return this;
    }
}
