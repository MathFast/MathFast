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
        String test = "1+1";
        System.out.println("Calculator test on " + test + ": " + calculate(test));
        System.out.println("Calculator " + this.hashCode() + " registered.");
    }
    
    //Basic parsing, HAS to be replaced later
    public LinkedList<mathPiece> parse(String toParse){
        LinkedList<mathPiece> out = new LinkedList<>();
        int ind = 0;
        //array and to get the length
        char[] cArray = toParse.replace(" ", "").toCharArray();
        int cLen;
        cLen = cArray.length;
        for( char c : cArray){
            System.out.println(c);
            try{
                double val = (int) Float.parseFloat(c + "");
                out.add(new mathPiece(val+"", val));
            }
            
            catch(NumberFormatException e){
                out.add(new mathPiece(c+"", 0));
            }
            ind++;
           
        }
        return out;
    }
    public String calculate(String toCalculate) {
        LinkedList<mathPiece> parsed = parse(toCalculate);
        double x = parsed.get(0).value_double;
        String op = parsed.get(1).value;
        double y = parsed.get(2).value_double;
        return simpleCalculate(x, op, y) + "";
    }
    public double simpleCalculate(double x, String operator, double y) throws NumberFormatException{
        double result;
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
            
            case "**":
                result = Math.pow(x, y);
                break;
            
            case "^":
                result = Math.pow(x, y);
                break;
            default:
                throw new NumberFormatException("Invalid operator \"" + operator + "\"");
        }
        return result;
    }
    
}
