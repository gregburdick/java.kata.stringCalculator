package spock

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class CalculatorTest extends Specification {

    def "should calculate: #x + #y = #sum"() {

        expect:
        spock.Calculator.take(x).add(y).calculate() == sum

        where:
        x | y  | sum
        1 | 2  | 3
        2 | 5  | 7
        3 | -1 | 2

    }

    def "add empty string returns 0"() {
        given: "instantiate the class"
        Calculator calculator = new Calculator()

        expect: "invoke the string method with empty string"
        calculator.add("") == 0
    }

    def "add single number returns that number"() {
        given: "add method in Calculator class"
        Calculator calculator = new Calculator()

        expect: "invoke add method with single number"
        String x = "5"
        calculator.add(x) == Integer.parseInt(x)
    }

    def "add two comma delimited numbers expect sum of those numbers"() {
        given: "two comma delimited numbers"
        Calculator calculator = new Calculator()

//        when: "invoking add (String) method"

        expect: "sum of both supplied numbers"
        String x = "5,4"
        calculator.add(x) == 9
    }

    def "sum of #numbers (comma delimited) = #sum"() {
        given: "string of three or more comma delimited numbers"

        when: "the class is instantiated"
        Calculator calculator = new Calculator()

        then: "should sum #numbers = sum"
        calculator.add(numbers) == sum

        //ToDo: add two digit numbers;
        where:
        numbers      | sum
        "1,2,3"      | 6
        "2,4,6"      | 12
        "1,3,5,7"    | 16
        "-1,0,0,0,1" | 0
    }

    def "sum of #numbers (new line delimited) = #sum"() {
        given: "string of new line delimited numbers"

        when: "the class is instantiated"
        Calculator calculator = new Calculator()

        then: "should sum #numbers = sum"
        calculator.add(numbers) == sum

        //ToDo: add two digit numbers;
        where:
        numbers               | sum
        "1\n2\n3"             | 6
        "2\n3"                | 5
        "2\n-1\n3\n-2\n4\n-3" | 3
    }

    def "support different delimiters"() {
        given: "delimiter specified in string"

        when: "invoking the add (String) method"
        Calculator calculator = new Calculator()

        then: "the sum of all numbers is returned"
        calculator.add(numbers) == sum

        where:
    numbers             | sum
        "//;\n1;2"      |   3
        "//,\n1,2"      |   3
        "//!\n2!2"      |   4
        "//#\n3#2"      |   5
        "//@\n7@2"      |   9
    }

    def "5. Calling Add with a negative number throws exception"(){
        given: "negative number in string"

        when: "invoking the add (String) method"

        then: "\"Negatives Not Allowed\" exception thrown"
        calculator.add(numbers)
        def e = thrown(CustomException)
        e.message == "Some Message"
    }
/*
    def "should subtract numbers"() {

        given:
            Calculator calculator = new Calculator()
        when:
            calculator.add(4).subtract(4)
        then:
            calculator.calculate() == 0

    }

    def "should multiply"() {

        expect:
            spock.Calculator.take(2).multiply(2).calculate() == 4

    }
 */

}
