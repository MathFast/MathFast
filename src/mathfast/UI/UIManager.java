/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathfast.UI;

import JFUtils.Input;
import JFUtils.InputActivated;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.System.Logger;
import java.util.LinkedList;
import javax.swing.ImageIcon;
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
    JPanel historyUI = new JPanel(new FlowLayout());
    
    JMenuItem i1, i2, i3, i4, i5, i6;  
        
    String lastCorrect = "";
    boolean isCorrect = true;
    
    LinkedList<String> history = new LinkedList<>();
    int placeInHistory = 0;
    boolean historyEditable = true;
    
    public UIManager(calculatorMain calc) {
        this.calculator = calc;
        System.out.println("UIManager " + this.hashCode() + " registered.");
    }
    public void initWindow(){
        setTitle(Flags.app_name + " " + Flags.app_ver + " - " + Flags.app_ver_codename);
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        ImageIcon icon = new ImageIcon("./MathFast.png");
        setIconImage(icon.getImage());
        
        setVisible(true);
    }
    public void initMenu(){
        JMenuBar menus = new JMenuBar();
        JMenu menu=new JMenu("Menu");  
        JMenu submenu;
        submenu=new JMenu("History");  
          menu.add(submenu);  
          i1=new JMenuItem("Load history");  
          i2=new JMenuItem("Clear history");  
          i6=new JMenuItem("Save history");
          i3=new JMenuItem("Info");  
          i4=new JMenuItem("Item 4");  
          i5=new JMenuItem("Item 5");  
          //menu.add(i1); menu.add(i2); 
//          menu.add(i1);
//          menu.add(i2);
          menu.add(i3);  
          submenu.add(i1); submenu.add(i2); submenu.add(i6);
        menus.add(menu);  
        setJMenuBar(menus);
        
        //action
        i3.addActionListener(this);
        i2.addActionListener(this);
        i1.addActionListener(this);
        i6.addActionListener(this);
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
        
        //History UI
        center.add(historyUI, BorderLayout.PAGE_END);
        historyUI.setBackground(Color.black);
        
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
//            if(historyEditable){
//                history.add(inp.getText());
//                placeInHistory++;
//            }
            isCorrect = true;
        } catch (Exception e) {
            System.out.println("\t" + "invalid text! error: " + e.toString());
            result.setText(lastCorrect + " (error : " + e.toString() + ")");
            isCorrect = false;
        }
        updateHistoryUI();
//        repaint();
//        revalidate();
        //pack();
    }
    void updateHistoryUI(){
        historyUI.removeAll();
        int ind = 0;
        for(String entry : history){
            int fg = 255;
            int bg = 255;
            JLabel comp = new JLabel(entry);
            comp.setForeground(new Color(fg, fg, fg));
            if(ind == placeInHistory){
                fg = 0;
                bg = 0;
                comp.setForeground(new Color(fg, 255, fg));
                comp.setBackground(new Color(bg, bg, bg));
                if(!isCorrect){
                    comp.setForeground(Color.red);
                }
            }
            historyUI.add(comp);
            ind++;
        }
        revalidate();
        repaint();
    }
    public void saveHistory(){
        try {
            String hist = "";
            for (String s : history){
                hist = hist + s + "\n";
            }
            JFUtils.IO.CompressedIO.writeString(hist, "mathfast_history.txt");
        } catch (FileNotFoundException ex) {
            JFUtils.quickTools.alert(ex.getMessage(), "Could not save config");
        }
    }
    public void loadHistory(){
        try {
            String hist = JFUtils.IO.CompressedIO.readAsString("mathfast_history.txt", false, false);
            for (String s : hist.split("\n")){
                history.add(s);
            }
        } catch (FileNotFoundException ex) {
            JFUtils.quickTools.alert(ex.getMessage(), "Could not load config");
        } catch (IOException ex) {
            JFUtils.quickTools.alert(ex.getMessage(), "Could not load config");
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
                    if(historyEditable){
                        if(history.size() < 1 || !inp.getText().equals(history.getLast())){
                            history.add(inp.getText());
                            placeInHistory++;
                            placeInHistory = Math.min(placeInHistory, history.size()-1);
                        }
                    }
                    updateHistoryUI();
                    break;
                default:
                    //delete
                    if (i == 127){
                        System.out.println("User requested entry deletion.");
                        try{
                            history.remove(placeInHistory);
                            placeInHistory--;
                            placeInHistory = Math.max(placeInHistory, 0);
                            updateHistoryUI();
                        }catch(Exception e){
                            System.out.println("Couldn't delete history at index " + placeInHistory + ", error: " + e);
                        }
                    }
                    //Up arrow or (not a good idea) left arrow
                    else if (i == 38 /*|| i == 37*/){
                        historyEditable = false;
                        System.out.println("Going back in history.");
                        placeInHistory--;
                        historyOverride();
                        updateHistoryUI();
                    }
                    //Up arrow or (not a good idea) right arrow
                    else if (i == 40 /*|| i == 39*/){
                        historyEditable = false;
                        System.out.println("Going forth in history.");
                        placeInHistory++;
                        historyOverride();
                        updateHistoryUI();
                    }
                    //left arrow
                    else if (i == 37){
                        //Do nothing
                    }
                    //right arrow
                    else if (i == 39){
                        //Do nothing
                    }
                    //17 is the code for ctrl, so this enables ctrl+c and v
                    else if(i != 17 && !(utilsInput.isControlDown && i == 67) && i!=10){
                        historyEditable = true;
                        //Do nada, reset font
                        result.setFont(new Font(resutBaseFont.getName(), resutBaseFont.getStyle(), resutBaseFont.getSize()));
                        inp.requestFocus();
                        inp.requestFocusInWindow();
                    }
            }
        }
    }
    private void historyOverride(){
        placeInHistory = Math.min(placeInHistory, history.size()-1);
        placeInHistory = Math.max(placeInHistory, 0);
        historyEditable = false;
        inp.setEditable(false);
        inp.setText(history.get(placeInHistory));
        inp.setEditable(true);
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
        if(ae.getSource() == i2){
            history = new LinkedList<>();
            placeInHistory = 0;
            updateHistoryUI();
        }
        if(ae.getSource() == i1){
            history = new LinkedList<>();
            loadHistory();
            placeInHistory = 999999999;
            placeInHistory = Math.min(placeInHistory, history.size()-1);
            placeInHistory = Math.max(placeInHistory, 0);
            updateHistoryUI();
        }
        if(ae.getSource() == i6){
            saveHistory();
            updateHistoryUI();
        }
    }

}
