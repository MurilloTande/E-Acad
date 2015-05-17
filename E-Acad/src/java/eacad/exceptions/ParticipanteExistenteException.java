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
public class ParticipanteExistenteException extends Exception{
    
     public ParticipanteExistenteException(){
        super("O Participante já está cadastrado no evento!");
    }
    public ParticipanteExistenteException(Exception e) {
        super(e);
    }
    
}
