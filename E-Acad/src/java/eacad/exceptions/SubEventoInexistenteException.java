/**
 * Classe de exceção da entidade SubEvento, exceção de SubEvento inexistente.
 *
 * @author Murillo Tande
 * @author Matheus Barbosa
 * @author Hugo Calado
 * @author Felipe Xavier
 */
package eacad.exceptions;


public class SubEventoInexistenteException extends Exception {

    public SubEventoInexistenteException() {
        super("SubEvento não encontrado!");
    }

    /**
     * Exceção de SubEventos que não existem.
     *
     * @param e;
     */
    public SubEventoInexistenteException(Exception e) {
        super("SubEvento não encontrado!");
    }

}
