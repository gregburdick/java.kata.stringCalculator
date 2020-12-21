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

        if (numbers == "") {
            return result;
        }

        String delimiter = getDelimiter(numbers);

        ArrayList<String> numberList = getNumberList(numbers, delimiter);

        for (int i = 0; i < numberList.size(); i++)
            result += Integer.valueOf(numberList.get(i));

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

    protected String getDelimiter(String numbers) {
        String delimiter = "";
        if (numbers.startsWith("//")) {
            delimiter = numbers.substring(2, 3);
        } else {
            if (!numbers.contains(",") && numbers.contains("\n")) {
                delimiter = "\n";
            } else {
                delimiter = ",";
            }
        }
        return delimiter;
    }

    protected ArrayList<String> getNumberList(String numbers, String delimiter) {
        ArrayList<String> numberList = new ArrayList<>();

        if (numbers.startsWith("//")) {
            numbers = numbers.substring(4);
        }

        try {
            numberList = Stream.of(numbers
                    .split(delimiter))
                    .map(elem -> new String(elem))
                    .collect(Collectors.toCollection(ArrayList::new));

        } catch (Exception e) {
            // ToDo: add logging and messaging
//            return numberList.add(e.toString());
        }
        return numberList;
    }
}
