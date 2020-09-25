/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathfast.Calculator.types;

import java.util.LinkedList;
import mathfast.Global.Flags;

/**
 *
 * @author elias
 */
public class SimpleEquation implements Comparable<SimpleEquation>{
    mathPiece x;
    public mathPiece operator;
    mathPiece y;
    public boolean wasFirst = false;

    public SimpleEquation(mathPiece x, mathPiece operator, mathPiece y, boolean wasFirst) {
        this.x = x;
        this.operator = operator;
        this.y = y;
        this.wasFirst = wasFirst;
    }

    public mathPiece getX() {
        return x;
    }

    public mathPiece getY() {
        return y;
    }

    public void setX(mathPiece x) {
        this.x = x;
    }

    public void setY(mathPiece y) {
        this.y = y;
    }

    @Override
    public int compareTo(SimpleEquation t) {
        double myScore = Flags.operatorScoring.get(operator.value);
        double otherScore = Flags.operatorScoring.get(t.operator.value);
        return Double.compare(otherScore, myScore);
    }

    @Override
    public String toString() {
        return "EQ" + x.value_double + operator.value + y.value_double;
    }
    
    
    
}
