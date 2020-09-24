/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathfast.Global;

import java.util.HashMap;

/**
 *
 * @author elias
 */
public class Flags {

    public final static String app_name = "MathFast";
    public final static float app_ver = 0.12F;
    public final static String app_ver_codename = "Indev";
    
    
    //Scoreboard for operators
    public static HashMap<String, Integer> operatorScoring = new HashMap<>();
    static {
        operatorScoring.put("+", 1);
        operatorScoring.put("-", 1);
        
        operatorScoring.put("*", 2);
        operatorScoring.put("/", 2);
        
        operatorScoring.put("^", 3);
        operatorScoring.put("**", 3);
    }
    
}
