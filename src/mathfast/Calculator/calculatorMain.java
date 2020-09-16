/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathfast.Calculator;

import java.util.LinkedList;
import mathfast.Calculator.types.mathPiece;

/**
 *
 * @author joona
 */
public class calculatorMain {

    public calculatorMain() {
        System.out.println("Calculator test on 1 + 2: " + simpleCalculate(1, "+", 2));
        System.out.println("Calculator " + this.hashCode() + " registered.");
    }
    public LinkedList<mathPiece> parse(String toParse){
        return new LinkedList<>();
    }
    public String calculate(String toCalculate) {
        return "gg";
    }
    public int simpleCalculate(int x, String operator, int y){
        int result;
        switch(operator){
            case "+":
                result = x + y;
                break;
            case "-":
                result = x - y;
                break;
                
            case "/":
                result = x/y;
                break;
                
            case ":":
                result = x/y;
                break;
                
            case "*":
                result = x*y;
                break;
            
            case "⋅":
                result = x*y;
                break;
            
            default:
                throw new Error("Invalid operator \"" + operator + "\"");
        }
        return result;
    }
    
}
