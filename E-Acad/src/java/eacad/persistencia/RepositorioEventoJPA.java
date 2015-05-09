/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eacad.persistencia;

import eacad.entidades.Evento;
import eacad.entidades.Usuario;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.EventoExistenteException;
import eacad.exceptions.EventoInexistenteException;
import eacad.exceptions.UsuarioInexistenteException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class RepositorioEventoJPA implements RepositorioEvento{
    
    @PersistenceContext(unitName = "EacadPU")
    private EntityManager em;
    
     public void adicionar(Evento e) throws ErroInternoException{
      try {
            this.em.persist(e);
        } catch (Exception r) {
            throw new ErroInternoException(r);
        }
     }
     
     
     
    public List<Evento> listarTudoEvento() throws ErroInternoException, EventoExistenteException{
        try {
        TypedQuery<Evento> consulta = this.em.createQuery("select e from Evento e", Evento.class);
        return  consulta.getResultList();
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
}
    public void atualizar(Evento e) throws ErroInternoException, EventoInexistenteException{
    
        Evento ev = buscarCodigo(e.getCodigo());
        e.setNome(ev.getNome());
        e.setCidade(ev.getCidade());
        e.setData_final(ev.getData_final());
        e.setData_inicio(ev.getData_inicio());
        e.setDescricao(ev.getDescricao());
        e.setEndereco(ev.getEndereco());
        e.setHora_final(ev.getHora_final());
        e.setTotal_vagas(ev.getTotal_vagas());
        e.setParticipante(ev.getParticipante());
        e.setLocal(ev.getLocal());
        e.setEstado(ev.getEstado());
        e.setHora_inicial(ev.getHora_inicial());
        e.setEvento(ev.getEvento());
        try {
            this.em.merge(e);
        } catch (Exception es) {
            throw new ErroInternoException(es);
        }
        
    }
    public List<Evento> buscarNomeListEvento(String nome) throws ErroInternoException, EventoInexistenteException{
          try {
           TypedQuery<Evento> consulta = this.em.createQuery("select e from Evento e where e.nome like :nome", Evento.class);
           consulta.setParameter("nome", "%" + nome + "%");
            return consulta.getResultList();
           
        }catch(NoResultException es){
                 throw new EventoInexistenteException();  
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
        
}
    public Evento buscarCodigo(long codigo) throws ErroInternoException, EventoInexistenteException{
        try {
           TypedQuery<Evento> consulta = this.em.createQuery("select e from Evento e where e.codigo like :codigo", Evento.class);
           consulta.setParameter("codigo", "%" + codigo + "%");
            return consulta.getSingleResult();
           
        }catch(NoResultException es){
                 throw new EventoInexistenteException();  
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    
}
    public Evento buscarNomeEvento(String nome) throws ErroInternoException, EventoInexistenteException{
        
         try {
           TypedQuery<Evento> consulta = this.em.createQuery("select e from Evento e where e.nome like :nome", Evento.class);
           consulta.setParameter("nome", "%" + nome + "%");
            return consulta.getSingleResult();
           
        }catch(NoResultException es){
                 throw new EventoInexistenteException();  
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
}
    public void remover(long codigo) throws ErroInternoException, EventoInexistenteException{
    try{
         Evento e = buscarCodigo(codigo);
        this.em.remove(e);
        }catch(IllegalArgumentException e ){
            throw new ErroInternoException(e);
        }
    }
    
}
