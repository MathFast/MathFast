/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathfast.UI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import mathfast.Calculator.calculatorMain;
import mathfast.Global.Flags;

/**
 *
 * @author elias
 */
public class UIManager extends JFrame implements DocumentListener{

    private final calculatorMain calculator;
    JTextField inp = new JTextField("Type stuff here...");
    JLabel result = new JLabel("Result goes here!");
    
    String lastCorrect = "";
    
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
    public void initMenu(){
        JMenuBar menus = new JMenuBar();
        JMenu menu=new JMenu("Menu");  
        JMenu submenu;
        JMenuItem i1, i2, i3, i4, i5;  
        submenu=new JMenu("Sub Menu");  
          i1=new JMenuItem("Item 1");  
          i2=new JMenuItem("Item 2");  
          i3=new JMenuItem("Item 3");  
          i4=new JMenuItem("Item 4");  
          i5=new JMenuItem("Item 5");  
          menu.add(i1); menu.add(i2); menu.add(i3);  
          submenu.add(i4); submenu.add(i5);  
          menu.add(submenu);  
        menus.add(menu);  
        setJMenuBar(menus);
    }
    public void initComponents(){
        setLayout(new BorderLayout());
        JPanel center = new JPanel(new BorderLayout());
        center.add(result, BorderLayout.PAGE_START);
        add(center, BorderLayout.CENTER);
        inp.getDocument().addDocumentListener(this);
        add(inp, BorderLayout.PAGE_START);
    }
    public void initAll(){
        initWindow();
        initComponents();
        initMenu();
    }
    private void change(){
        System.out.println("Change: " + inp.getText());
        try {
            String res = calculator.calculate(inp.getText());
            System.out.println("\t" + res);
            result.setText(res);
            lastCorrect = res;
        } catch (Exception e) {
            System.out.println("\t" + "invalid text! error: " + e.toString());
            result.setText(lastCorrect + " (error : " + e.toString() + ")");
        }
    }
    @Override
    public void insertUpdate(DocumentEvent de) {
        change();
    }

    @Override
    public void removeUpdate(DocumentEvent de) {
        change();
    }

    @Override
    public void changedUpdate(DocumentEvent de) {
        change();
    }

}
