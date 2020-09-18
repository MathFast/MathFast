/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathfast.UI;

import JFUtils.Input;
import JFUtils.InputActivated;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;
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
public class UIManager extends JFrame implements DocumentListener, JFUtils.InputListener, ActionListener{

    private final calculatorMain calculator;
    JFUtils.Input utilsInput;
    JTextField inp = new JTextField("");
    JTextField result = new JTextField("Result goes here!");
    Font resutBaseFont = result.getFont();
    
    JMenuItem i1, i2, i3, i4, i5;  
        
    String lastCorrect = "";
    
    public UIManager(calculatorMain calc) {
        this.calculator = calc;
        System.out.println("UIManager " + this.hashCode() + " registered.");
    }
    public void initWindow(){
        setTitle(Flags.app_name + " " + Flags.app_ver + " - " + Flags.app_ver_codename);
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        setVisible(true);
    }
    public void initMenu(){
        JMenuBar menus = new JMenuBar();
        JMenu menu=new JMenu("Menu");  
        JMenu submenu;
        submenu=new JMenu("Sub Menu");  
          i1=new JMenuItem("Item 1");  
          i2=new JMenuItem("Item 2");  
          i3=new JMenuItem("Info");  
          i4=new JMenuItem("Item 4");  
          i5=new JMenuItem("Item 5");  
          menu.add(i1); menu.add(i2); menu.add(i3);  
          submenu.add(i4); submenu.add(i5);  
          menu.add(submenu);  
        menus.add(menu);  
        setJMenuBar(menus);
        
        //action
        i3.addActionListener(this);
    }
    public void initComponents(){
        //Layout
        setLayout(new BorderLayout());
        
        //Input field
        JPanel top = new JPanel(new BorderLayout());
        inp.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        top.add(inp, BorderLayout.CENTER);
        
        //Result field
        result.setEditable(false); //
        result.setBackground(null); //this is the same as a JLabel
        result.setBorder(null); //remove the border
        
        JPanel center = new JPanel(new BorderLayout());
        center.add(result, BorderLayout.PAGE_START);
        add(center, BorderLayout.CENTER);
        
        //Enter-key detection
        utilsInput = new Input(new InputActivated());
        addKeyListener(utilsInput);
        inp.addKeyListener(utilsInput);
        result.addKeyListener(utilsInput);
        utilsInput.verbodose = false;
        utilsInput.addListener(this);
        
        //changelistener
        inp.getDocument().addDocumentListener(this);
        
        //add inputfield
        add(top, BorderLayout.PAGE_START);
        
        //Set the focus to the inputfield, so the user can start typing at once
        inp.requestFocus();
        inp.requestFocusInWindow();
    }
    public void initAll(){
        initWindow();
        initComponents();
        initMenu();
        //Ensure that everything is visible
        repaint();
        revalidate();
    }
    private void change(){
        System.out.println("Change: " + inp.getText());
        try {
            String res = calculator.calculate(inp.getText());
            System.out.println("\t" + res);
            result.setText(res);
            lastCorrect = res;
            result.setFont(new Font(resutBaseFont.getName(), resutBaseFont.getStyle(), 40));
        } catch (Exception e) {
            System.out.println("\t" + "invalid text! error: " + e.toString());
            result.setText(lastCorrect + " (error : " + e.toString() + ")");
        }
        repaint();
        revalidate();
        //pack();
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

    
    @Override
    public void handleInput(char c, int i, boolean keyDown) {
        if(keyDown){
            switch(c){
                case '\n':
                    System.out.println("Enter pressed!");
                    result.setFont(new Font(resutBaseFont.getName(), resutBaseFont.getStyle(), 40));
                    result.requestFocusInWindow();
                    result.requestFocus();
                    result.selectAll();
                    break;
                default:
                    //17 is the code for ctrl, so this enables ctrl+c and v
                    if(i != 17){
                        //Do nada, reset font
                        result.setFont(new Font(resutBaseFont.getName(), resutBaseFont.getStyle(), resutBaseFont.getSize()));
                        inp.requestFocus();
                        inp.requestFocusInWindow();
                    }
            }
        }
    }

    @Override
    public void handleMouse(int i, int i1) {
        //Not going to use this utils function
    }

    @Override
    public void handleMouseExtra(boolean bln, boolean bln1, boolean bln2, int i) {
        //Not going to use this utils function
    }

    @Override
    public Input returnSource() {
        //Crappy
        return utilsInput;
    }

    //Menu buttons
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == i3){
            JFUtils.quickTools.alert("Info", "Program version: " + Flags.app_ver + " \"" + Flags.app_ver_codename + "\"" + ", JFUtils version: " + JFUtils.versionCheck.version);
        }
    }

}
