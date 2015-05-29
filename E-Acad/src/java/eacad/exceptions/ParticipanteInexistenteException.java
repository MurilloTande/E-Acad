/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eacad.exceptions;


public class ParticipanteInexistenteException extends Exception{
    
    public ParticipanteInexistenteException(){
        super("Participante não encontrado!");
    }
    public ParticipanteInexistenteException(Exception e) {
        super("Participante não encontrado!");
    }
}
