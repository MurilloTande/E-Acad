/**
 * Classe de exceção da entidade Usuario, exceção de usuario existente.
 *
 * @author Murillo Tande
 * @author Matheus Barbosa
 * @author Hugo Calado
 * @author Felipe Xavier
 */
package eacad.exceptions;

public class UsuarioExistenteException extends Exception {

    public UsuarioExistenteException() {
        super("O usuario já está cadastrado no sistema!");
    }

    /**
     * Exceção de usuarios duplicados.
     *
     * @param e;
     */
    public UsuarioExistenteException(Exception e) {
        super("O usuario já está cadastrado no sistema!");
    }

}
