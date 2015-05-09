/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eacad.negocio;

import eacad.entidades.Evento;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.EventoExistenteException;
import eacad.exceptions.EventoInexistenteException;
import eacad.persistencia.RepositorioEvento;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CadastroEvento implements Serializable{
    @EJB
   private RepositorioEvento repEvento;

    public CadastroEvento() {
        
    }

    public CadastroEvento(RepositorioEvento repEvento) {
        this.repEvento = repEvento;
    }
    
     public void adicionar(Evento e) throws ErroInternoException, EventoExistenteException{
          try {
            Evento ev = this.repEvento.buscarCodigo(e.getCodigo());
            if ( ev!=null   ) {
                throw new EventoExistenteException();
            } 
        } catch (EventoInexistenteException ui) {
            this.repEvento.adicionar(e);
        }
     }
     
    public List<Evento> listarTudoEvento() throws ErroInternoException, EventoExistenteException{
    
        List<Evento> e = this.repEvento.listarTudoEvento();
        return e;
        
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
    public void remover(long codigo) throws ErroInternoException, EventoInexistenteException{
     this.repEvento.remover(codigo);
    }
    
}
