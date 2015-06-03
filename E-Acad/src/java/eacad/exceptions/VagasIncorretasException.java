/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eacad.exceptions;

/**
 *
 * @author Hugo Calado
 */
public class VagasIncorretasException extends Exception{
     public VagasIncorretasException(){
        super("Quantidade de vagas disponíveis é menos que a quantidade de vagas ofertadas");
    }
     
     public VagasIncorretasException(Exception e){
        super(e);
    }
}




