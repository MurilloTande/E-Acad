/**
 * @author Tande
 */
package eacad.exceptions;

public class UsuarioInexistenteException extends Exception{
    public UsuarioInexistenteException() {
        super("Usuário não encontrado!");
    }
    public UsuarioInexistenteException(Exception e) {
        super(e);
    }
    
    
}
