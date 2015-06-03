/**
 * Classe de exceção da entidade SubEventos, exceção de SubEvento existente.
 *
 * @author Murillo Tande
 * @author Matheus Barbosa
 * @author Hugo Calado
 * @author Felipe Xavier
 */
package eacad.exceptions;


public class SubEventoExistenteException extends Exception {

    public SubEventoExistenteException() {
        super("O SubEvento já está cadastrado no sistema!");
    }

    /**
     * Exceção de SubEventos.
     *
     * @param e;
     */
    public SubEventoExistenteException(Exception e) {
        super("O SubEvento já está cadastrado no sistema!");
    }

}
