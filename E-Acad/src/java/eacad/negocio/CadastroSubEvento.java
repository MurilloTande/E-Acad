/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eacad.negocio;

import eacad.entidades.SubEvento;
import eacad.exceptions.DatasIncorretas;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.SubEventoExistenteException;
import eacad.exceptions.SubEventoInexistenteException;
import eacad.persistencia.RepositorioSubEvento;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CadastroSubEvento implements Serializable{
     @EJB
    private RepositorioSubEvento repSubEvento;
     
    public CadastroSubEvento(RepositorioSubEvento repSubEvento) {
        this.repSubEvento = repSubEvento;
    }

    public CadastroSubEvento() {
    }
    
    public void adicionar(SubEvento e) throws ErroInternoException,DatasIncorretas{
        if((e.getData_final().after(e.getData_inicio()) || e.getData_final().equals(e.getData_inicio())) & 
               ((e.getData_inicio().after(e.getEventoPai().getData_inicio()) || e.getData_inicio().equals(e.getEventoPai().getData_inicio())  )
                & (e.getData_inicio().before(e.getEventoPai().getData_final())) || e.getData_inicio().equals(e.getEventoPai().getData_final()) )
                
                
                & ((e.getData_final().before(e.getEventoPai().getData_final()))|| e.getData_final().equals(e.getEventoPai().getData_final()))){  
        try {  
            this.repSubEvento.adicionar(e);        
        } catch (ErroInternoException ui) {
            throw new ErroInternoException(ui);
        }}else{
                 throw new DatasIncorretas(); 
     }
     }
     
    public List<SubEvento> listarTudoSubEvento() throws ErroInternoException, SubEventoInexistenteException{
    
        List<SubEvento> e = this.repSubEvento.listarTudoSubEvento();
        return e;
    }
    
    public void atualizar(SubEvento e) throws ErroInternoException, SubEventoInexistenteException{
    
    this.repSubEvento.atualizar(e);    
    }
    
    public List<SubEvento> buscarNomeListSubEvento(String nome) throws ErroInternoException, SubEventoInexistenteException{
    List<SubEvento> e = this.repSubEvento.buscarNomeListSubEvento(nome);
        if ((e.isEmpty())) {
            throw new SubEventoInexistenteException();
        }
        return e;
    }
    
    public SubEvento buscarCodigo(long codigo) throws ErroInternoException, SubEventoInexistenteException{
    SubEvento e = this.repSubEvento.buscarCodigo(codigo);

        if (e == null) {
            throw new SubEventoInexistenteException();
        }
        return e;
    }
    
    public SubEvento buscarNomeSubEvento(String nome) throws ErroInternoException, SubEventoInexistenteException{
     SubEvento e = this.repSubEvento.buscarNomeSubEvento(nome);

        if (e == null) {
            throw new SubEventoInexistenteException();
        }
        return e;
    }
    
    public void remover(long codigo) throws ErroInternoException, SubEventoInexistenteException{
     this.repSubEvento.remover(codigo);
    }
    
}
