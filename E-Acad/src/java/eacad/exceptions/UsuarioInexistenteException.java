/**
 * Classe de exceção da entidade Usuario, exceção de usuario inexistente.
 *
 * @author Murillo Tande
 * @author Matheus Barbosa
 * @author Hugo Calado
 * @author Felipe Xavier
 */
package eacad.exceptions;

public class UsuarioInexistenteException extends Exception {

    public UsuarioInexistenteException() {
        super("Usuário não encontrado!");
    }

    /**
     * Exceção de usuario não encontrado.
     *
     * @param e;
     */
    public UsuarioInexistenteException(Exception e) {
        super("Usuário não encontrado!");
    }

}
