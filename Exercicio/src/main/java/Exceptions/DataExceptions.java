/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Rafael Rodrigues
 */
public class DataExceptions extends Exception{
     public DataExceptions (String message){
        super(message);
    }

    public DataExceptions(String erro_na_fonte_de_dados, Exception e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
