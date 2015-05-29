
package eacad.exceptions;


public class EventoInexistenteException extends Exception{
    public EventoInexistenteException() {
        super("Evento não encontrado!");
    }
    public EventoInexistenteException(Exception e) {
        super("Evento não encontrado!");
    }
    
}
