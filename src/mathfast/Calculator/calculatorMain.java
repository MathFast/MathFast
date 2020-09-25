/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathfast.Calculator;

import JFUtils.Range;
import java.util.LinkedList;
import java.util.PriorityQueue;
import mathfast.Calculator.types.SimpleEquation;
import mathfast.Calculator.types.mathPiece;

/**
 *
 * @author joona
 */
public class calculatorMain {

    public calculatorMain() {
        String test = "1+2*3";
        System.out.println("Calculator test on " + test + ": " + calculate(test));
        System.out.println("Calculator " + this.hashCode() + " registered.");
    }
    
    //basic operators
    String operators = "+-/*:⋅^ ";
    
    //A bit better parsing, has to still be developed
    public LinkedList<mathPiece> parse(String toParse){
        LinkedList<mathPiece> out = new LinkedList<>();
        int ind = 0;
        //array and to get the length
        char[] cArray = toParse.replace(" ", "").toCharArray();
        int cLen;
        cLen = cArray.length;
        String numBuffer = "";
        //System.out.println("Operator test (is + an operator?): " + operators.contains("+"));
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
        //    System.out.println(mp);
        }
        return out;
    }
    public PriorityQueue<SimpleEquation> sortParsed(LinkedList<mathPiece> toSort){
        PriorityQueue<SimpleEquation> out = new PriorityQueue<>();
        int ind = 0;
        boolean first = true;
        for(mathPiece mp : toSort){
            if(operators.contains(mp.value)){
                mathPiece after = new mathPiece("", 0);
                mathPiece before = new mathPiece("", 0);
                if(ind < toSort.size()-1){
                    after = toSort.get(ind+1);
                }
                if(ind > 0){
                    before = toSort.get(ind-1);
                }
                SimpleEquation eq = new SimpleEquation(before, mp, after, first);
                out.add(eq);
                first = false;
            }
            ind++;
        }
        return out;
    }
    public final String calculate(String toCalculate) {
        //toCalculate = toCalculate + "-";
        LinkedList<mathPiece> parsedsrc = parse(toCalculate);
        PriorityQueue<SimpleEquation> sorted = sortParsed(parsedsrc);
        LinkedList<mathPiece> parsed = new LinkedList<>();
        int ind = 0;
//        SimpleEquation first = sorted.poll();
//        parsed.add(first.getX());
//        parsed.add(first.operator);
//        parsed.add(first.getY());
        //parsed.add(sorted.poll().getX());
        boolean isFirst = true;
        boolean isLast = false;
        while(sorted.size() > 0){
            SimpleEquation eq = sorted.poll();
            isLast = sorted.size() < 1;
            System.out.println("\t" + eq);
            if(isFirst/*eq.wasFirst*/){
                parsed.add(eq.getX());
                parsed.add(eq.operator);
                parsed.add(eq.getY());
                System.out.println("(is first)");
            }
            else if(isLast){
                parsed.add(eq.operator);
                parsed.add(eq.getX());
                System.out.println("(is last)");
            }
            else{
                parsed.add(eq.operator);
                parsed.add(eq.getY());
            }
            isFirst = false;
            ind++;
            printParsed(parsed);
        }
        System.out.println(parsed);
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
            System.out.println("\t" + x + op + y);
            sum = simpleCalculate(x, op, y);
            System.out.println("\t=" + sum);
        }
        return sum + "";
    }
    private void printParsed(LinkedList<mathPiece> parsed){
        String out = "";
        for(mathPiece mp : parsed){
            out = out + mp.value;
        }
        System.out.println(out);
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
