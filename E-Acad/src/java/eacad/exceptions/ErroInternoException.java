/**
 * Classe de exceção para erros genéricos.
 *
 * @author Murillo Tande
 * @author Matheus Barbosa
 * @author Hugo Calado
 * @author Felipe Xavier
 */
package eacad.exceptions;

public class ErroInternoException extends Exception {

    private Exception causa;

    /**
     * Exceções de erros genericos.
     *
     * @param causa;
     */
    public ErroInternoException(Exception causa) {
        super(causa.getMessage());
        this.causa = causa;
    }
}
