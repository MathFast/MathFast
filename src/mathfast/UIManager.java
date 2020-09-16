/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathfast;

import javax.swing.JFrame;
import javax.swing.JLabel;
import mathfast.Global.Flags;

/**
 *
 * @author elias
 */
public class UIManager extends JFrame{

    public UIManager() {
        System.out.println("UIManager " + this.hashCode() + " registered.");
    }
    protected void initWindow(){
        setTitle(Flags.app_name + " v." + Flags.app_ver);
        setSize(888, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setVisible(true);
    }
    protected void initComponents(){
        add(new JLabel("Hello World!"));
    }
}
