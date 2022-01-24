/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author HP
 */
public class Validator {
    
    public static boolean isNumeric(String str){
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
