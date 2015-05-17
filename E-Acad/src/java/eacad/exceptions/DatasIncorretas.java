
package eacad.exceptions;


public class DatasIncorretas extends Exception{
   
      public DatasIncorretas(){
        super("A data inicial é maior que a data final!");
    }
    public DatasIncorretas(Exception e) {
        super(e);
    }
    
}
