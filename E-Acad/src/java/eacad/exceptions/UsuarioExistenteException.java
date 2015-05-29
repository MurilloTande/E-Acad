/**
 * @author Tande
 */
package eacad.exceptions;

public class UsuarioExistenteException extends Exception{
    public UsuarioExistenteException(){
        super("O usuario já está cadastrado no sistema!");
    }
    public UsuarioExistenteException(Exception e) {
        super("O usuario já está cadastrado no sistema!");
    }
    
}
