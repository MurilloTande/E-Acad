/**
 * Classe de exceção da entidade Participante, exceção de participante
 * inexistente.
 *
 * @author Murillo Tande
 * @author Matheus Barbosa
 * @author Hugo Calado
 * @author Felipe Xavier
 */
package eacad.exceptions;

public class ParticipanteInexistenteException extends Exception {

    public ParticipanteInexistenteException() {
        super("Participante não encontrado!");
    }

    /**
     * Exceção de participante não encontrado.
     *
     * @param e;
     */
    public ParticipanteInexistenteException(Exception e) {
        super("Participante não encontrado!");
    }
}
