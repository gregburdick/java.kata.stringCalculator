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

    def "add two comma delimited numbers expect sum of those numbers"(){
        // ToDo: continue test development here...
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
