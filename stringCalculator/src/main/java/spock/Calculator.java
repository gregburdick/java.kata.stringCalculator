package spock;

import java.util.ArrayList;
import java.util.List;

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
//        if (numbers.length() == 0) {
//            return result;
//        }
//        if (numbers.length() == 1){
//            result = Integer.parseInt(numbers);
//        }
//        return result;

        switch(numbers.length()) {
            case 0:
                return result;
            case 1:
                result = Integer.parseInt(numbers);
                break;
            case 3:
                result = Integer.parseInt(numbers.split(",")[1]) + Integer.parseInt(numbers.split(",")[2]);
                break;
            default:
                break;
        }
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
