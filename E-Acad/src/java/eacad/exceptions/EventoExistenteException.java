/**
 * Classe de exceção da entidade Evento, exceção de evento existente.
 *
 * @author Murillo Tande
 * @author Matheus Barbosa
 * @author Hugo Calado
 * @author Felipe Xavier
 */
package eacad.exceptions;

public class EventoExistenteException extends Exception {

    public EventoExistenteException() {
        super("O Evento já está cadastrado no sistema!");
    }

    /**
     * Exceção de duplicidade de eventos.
     *
     * @param e;
     */
    public EventoExistenteException(Exception e) {
        super("O Evento já está cadastrado no sistema!");
    }

}
