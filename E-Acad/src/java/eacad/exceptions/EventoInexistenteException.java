/**
 * Classe de exceção da entidade Evento, exceção de evento inexistente.
 *
 * @author Murillo Tande
 * @author Matheus Barbosa
 * @author Hugo Calado
 * @author Felipe Xavier
 */
package eacad.exceptions;

public class EventoInexistenteException extends Exception {

    public EventoInexistenteException() {
        super("Evento não encontrado!");
    }

    /**
     * Exceção para eventos não encontrados.
     *
     * @param e;
     */
    public EventoInexistenteException(Exception e) {
        super("Evento não encontrado!");
    }

}
