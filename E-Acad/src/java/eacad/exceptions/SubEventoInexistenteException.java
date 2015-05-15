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
public class SubEventoInexistenteException extends Exception{
    
    public SubEventoInexistenteException(){
        super("SubEvento não encontrado!");
    }
    public SubEventoInexistenteException(Exception e) {
        super(e);
    }
    
}
