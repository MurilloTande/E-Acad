/**
 * Classe de exceção de vagas, exceção de vagas incorretas.
 *
 * @author Murillo Tande
 * @author Matheus Barbosa
 * @author Hugo Calado
 * @author Felipe Xavier
 */
package eacad.exceptions;


public class VagasIncorretasException extends Exception {

    public VagasIncorretasException() {
        super("Quantidade de vagas disponíveis é menos que a quantidade de vagas ofertadas");
    }

    /**
     * Exceção de erro nas quantidades de vagas disponiveis/ofertada.
     *
     * @param e;
     */

    public VagasIncorretasException(Exception e) {
        super(e);
    }
}
