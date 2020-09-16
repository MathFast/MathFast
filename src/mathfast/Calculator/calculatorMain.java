/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathfast.Calculator;

/**
 *
 * @author joona
 */
public class calculatorMain {
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
            default:
                throw new Error("Invalid operator \"" + operator + "\"");
        }
        return result;
    }
    
}
