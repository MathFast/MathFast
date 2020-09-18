/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathfast.UI.components;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author elias
 */
public class HintField extends JTextField implements DocumentListener, FocusListener{

    public HintField() {
        getDocument().addDocumentListener(this);
        addFocusListener(this);
    }
    
    private void change(){
        
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
    public void focusGained(FocusEvent fe) {
        if(! (getText().length() > 0)){
            setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent fe) {
        if(! (getText().length() > 0)){
            setText("Type the calculation here");
        }
    }
    
}
