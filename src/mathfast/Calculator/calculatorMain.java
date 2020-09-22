/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathfast.Calculator;

import JFUtils.Range;
import java.util.LinkedList;
import mathfast.Calculator.types.mathPiece;

/**
 *
 * @author joona
 */
public class calculatorMain {

    public calculatorMain() {
        String test = "10*1";
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
        String numBuffer = "";
        String operators = "+-/*:⋅^ ";
        System.out.println("Operator test (is + an operator?): " + operators.contains("+"));
        boolean workingOnNumber=false;
        toParse = toParse + " ";
        boolean skipNext = false;
        for( char c : cArray){
            char nextChar = ' ';
            if("".equals(numBuffer)){
                workingOnNumber = !operators.contains(c+"");
            }
            if(!skipNext){
                numBuffer = numBuffer + c;
            }
            if (ind != cLen - 1) {
                nextChar = cArray[ind + 1];
            }
            if(skipNext){
                //skip
                skipNext = false;
            }
            else if (operators.contains(nextChar + "") || (!workingOnNumber && (!operators.contains(nextChar+"")))) {
                try{
                    double val = (int) Float.parseFloat(numBuffer + "");
                    out.add(new mathPiece(val+"", val));
                }

                catch(NumberFormatException e){
                    if("*".equals(numBuffer) && nextChar =='*'){
                        skipNext = true;
                        numBuffer = "**";
                    }
                    out.add(new mathPiece(numBuffer+"", 0));
                }
                numBuffer = "";
            }
//            System.out.println(c + " | " + workingOnNumber + " | " + numBuffer);
            ind++;
           
        }
        for(mathPiece mp : out){
            System.out.println(mp);
        }
        return out;
    }
    public final String calculate(String toCalculate) {
        LinkedList<mathPiece> parsed = parse(toCalculate);
        double sum = 0;
/*        double x = parsed.get(0).value_double;
        String op = parsed.get(1).value;
        double y = parsed.get(2).value_double;
        return simpleCalculate(x, op, y) + "";*/
        for(int i : new Range((int) Math.floor(parsed.size()/2.0)) ){
            int index = i * 2;
            double x = sum;
            if(index == 0){
                x = parsed.get(index).value_double;
            }
            String op = parsed.get(index+1).value;
            double y = parsed.get(index+2).value_double;
            sum = simpleCalculate(x, op, y);
        }
        return sum + "";
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
