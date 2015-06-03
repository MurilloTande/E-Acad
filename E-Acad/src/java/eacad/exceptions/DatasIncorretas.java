/**
 * Classe de exceção de datas incorretas.
 *
 * @author Murillo Tande
 * @author Matheus Barbosa
 * @author Hugo Calado
 * @author Felipe Xavier
 */
package eacad.exceptions;

public class DatasIncorretas extends Exception {

    public DatasIncorretas() {
        super("A data inicial é maior que a data final!");
    }
    /**
     * Exceção para datas erradas.
     * @param e; 
     */

    public DatasIncorretas(Exception e) {
        super(e);
    }

}
