/**
 * @author Tande
 */
package eacad.exceptions;

public class ErroInternoException extends Exception{
    private Exception causa;
    public ErroInternoException(Exception causa){
        super(causa.getMessage());
        this.causa = causa;
    }
}
