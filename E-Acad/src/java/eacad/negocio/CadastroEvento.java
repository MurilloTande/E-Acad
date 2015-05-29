/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eacad.negocio;

import eacad.entidades.Evento;
import eacad.entidades.SubEvento;
import eacad.exceptions.DatasIncorretas;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.EventoInexistenteException;
import eacad.exceptions.SubEventoInexistenteException;
import eacad.persistencia.RepositorioEvento;
import eacad.persistencia.RepositorioSubEvento;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CadastroEvento implements Serializable{
    
    @EJB
   private RepositorioEvento repEvento;
    @EJB
    private RepositorioSubEvento repSubEvento;

    public CadastroEvento() {
    }

    public CadastroEvento(RepositorioEvento repEvento) {
        this.repEvento = repEvento;
    }
    
     public void adicionar(Evento e) throws ErroInternoException, DatasIncorretas{
         
              if(e.getData_final().after(e.getData_inicio()) || e.getData_inicio().equals(e.getData_final())){
               try {
            this.repEvento.adicionar(e);        
        } catch (ErroInternoException ui) {
            throw new ErroInternoException(ui);
        }
               }else{
                 throw new DatasIncorretas(); 
     }}
     
    public List<Evento> listarTudoEvento() throws ErroInternoException, EventoInexistenteException{
    
        
        try {
            List<Evento> e = this.repEvento.listarTudoEvento();
            if(e!=null){
            return e;
            }
        } catch (EventoInexistenteException a) {
            throw new EventoInexistenteException();
        } 
        catch (ErroInternoException r) {
            throw new ErroInternoException(r);
        } 
       return listarTudoEvento();
    }
    
    public List<Evento> EventosUsuario(String cpf) throws ErroInternoException, EventoInexistenteException{
        try{
        List<Evento> e = this.repEvento.EventosUsuario(cpf);
        if(e!=null){
        return e;
        }
        } catch (EventoInexistenteException a) {
            throw new EventoInexistenteException();
        } 
        catch (ErroInternoException r) {
            throw new ErroInternoException(r);
        } 
       return EventosUsuario(cpf);
        
    }
    
    public void atualizar(Evento e) throws ErroInternoException, EventoInexistenteException{
    
    this.repEvento.atualizar(e);
        
    }
    public List<Evento> buscarNomeListEvento(String nome) throws ErroInternoException, EventoInexistenteException{
    List<Evento> e = this.repEvento.buscarNomeListEvento(nome);
        if ((e.isEmpty())) {
            throw new EventoInexistenteException();
        }
        return e;
    }
    
    public Evento buscarCodigo(long codigo) throws ErroInternoException, EventoInexistenteException{
    Evento e = this.repEvento.buscarCodigo(codigo);

        if (e == null) {
            throw new EventoInexistenteException();
        }
        return e;
    }
    
    public Evento buscarNomeEvento(String nome) throws ErroInternoException, EventoInexistenteException{
     Evento e = this.repEvento.buscarNomeEvento(nome);
        if (e == null) {
            throw new EventoInexistenteException();
        }
        return e;
    }
    
    public void remover(long codigo) throws ErroInternoException, EventoInexistenteException, SubEventoInexistenteException{
        Evento temp = this.repEvento.buscarCodigo(codigo);
        
        try {
            
            for(SubEvento e : this.repSubEvento.buscarListSubEvento(temp)){
            if(e != null){    
                this.repSubEvento.remover(e.getCodigo());    
            }}
            
             this.repEvento.remover(codigo);
            } catch (ErroInternoException e) {
               throw new ErroInternoException(e);
        }
        
    }
    
}
