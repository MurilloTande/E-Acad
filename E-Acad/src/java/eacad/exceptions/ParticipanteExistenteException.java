/**
 * Classe de exceção da entidade Participante, exceção de participante
 * existente.
 *
 * @author Murillo Tande
 * @author Matheus Barbosa
 * @author Hugo Calado
 * @author Felipe Xavier
 */
package eacad.exceptions;


public class ParticipanteExistenteException extends Exception {

    public ParticipanteExistenteException() {
        super("O Participante já está cadastrado no evento!");
    }

    /**
     * Exceção para jogar duplicidade de participantes.
     *
     * @param e;
     */
    public ParticipanteExistenteException(Exception e) {
        super("O Participante já está cadastrado no evento!");
    }

}
