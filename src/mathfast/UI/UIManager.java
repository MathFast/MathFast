/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathfast.UI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import mathfast.Calculator.calculatorMain;
import mathfast.Global.Flags;

/**
 *
 * @author elias
 */
public class UIManager extends JFrame{

    private calculatorMain calculator;
    
    public UIManager(calculatorMain calc) {
        this.calculator = calc;
        System.out.println("UIManager " + this.hashCode() + " registered.");
    }
    public void initWindow(){
        setTitle(Flags.app_name + " v." + Flags.app_ver);
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setVisible(true);
    }
    public void initComponents(){
        add(new JLabel("Hello World!"));
    }
}
