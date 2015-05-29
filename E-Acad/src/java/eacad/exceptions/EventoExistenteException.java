
package eacad.exceptions;


public class EventoExistenteException extends Exception{
   
      public EventoExistenteException(){
        super("O Evento já está cadastrado no sistema!");
    }
    public EventoExistenteException(Exception e) {
        super("O Evento já está cadastrado no sistema!");
    }
    
}
