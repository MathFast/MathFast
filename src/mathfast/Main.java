/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathfast;

import mathfast.UI.UIManager;
import mathfast.Calculator.calculatorMain;

/**
 *
 * @author elias
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        calculatorMain calculator = new calculatorMain();
        UIManager windowStuff = new UIManager(calculator);
        windowStuff.initWindow();
        windowStuff.initComponents();
    }
    
}
