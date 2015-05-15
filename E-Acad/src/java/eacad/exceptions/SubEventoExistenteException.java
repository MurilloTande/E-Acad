/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eacad.exceptions;

/**
 *
 * @author Matheus
 */
public class SubEventoExistenteException extends Exception{
    
    public SubEventoExistenteException(){
        super("O SubEvento já está cadastrado no sistema!");
    }
    public SubEventoExistenteException(Exception e) {
        super(e);
    }
    
}
