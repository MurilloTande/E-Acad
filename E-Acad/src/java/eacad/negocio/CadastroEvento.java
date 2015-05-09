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
    
    }
    public void atualizar(Evento e) throws ErroInternoException, EventoInexistenteException;
    public List<Evento> buscarNomeListEvento(String nome) throws ErroInternoException, EventoInexistenteException;
    public Evento buscarCodigo(long codigo) throws ErroInternoException, EventoInexistenteException;
    public Evento buscarNomeEvento(String nome) throws ErroInternoException, EventoInexistenteException;
    public void remover(long codigo) throws ErroInternoException, EventoInexistenteException;
    
}
