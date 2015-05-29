/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eacad.persistencia;

import eacad.entidades.Evento;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.DatasIncorretas;
import eacad.exceptions.EventoInexistenteException;
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
    
    @Override
     public void adicionar(Evento e) throws ErroInternoException,DatasIncorretas{
      try {
            this.em.persist(e);
        } catch (Exception r) {
            throw new ErroInternoException(r);
        }
     }
     
    @Override
    public List<Evento> listarTudoEvento() throws ErroInternoException, EventoInexistenteException{
        try {
        TypedQuery<Evento> consulta = this.em.createQuery("select e from Evento e", Evento.class);
        return  consulta.getResultList();
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
}
 
    @Override
      public List<Evento> EventosUsuario(String cpf) throws ErroInternoException, EventoInexistenteException{
        try {
        TypedQuery<Evento> consulta = this.em.createQuery("select e from Evento e where e.criador.cpf like :cpf", Evento.class);
       consulta.setParameter("cpf", "%" + cpf + "%");
        return  consulta.getResultList();
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
}
   
    
    @Override
    public void atualizar(Evento ev) throws ErroInternoException, EventoInexistenteException{
    
        Evento e = buscarCodigo(ev.getCodigo());
        e.setNome(ev.getNome());
        e.setCidade(ev.getCidade());
        e.setData_final(ev.getData_final());
        e.setData_inicio(ev.getData_inicio());
        e.setDescricao(ev.getDescricao());
        e.setEndereco(ev.getEndereco()); 
        e.setTotal_vagas(ev.getTotal_vagas());
        e.setLocalidade(ev.getLocalidade());
        e.setEstado(ev.getEstado());
        try {
            this.em.merge(e);
        } catch (Exception es) {
            throw new ErroInternoException(es);
        }
        
    }
    
    @Override
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
    
    @Override
    public Evento buscarCodigo(long codigo) throws ErroInternoException, EventoInexistenteException{
        Evento p = null;

        try {
            p = this.em.find(Evento.class, codigo);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }

        if (p == null) {
            throw new EventoInexistenteException();
        }

        return p;
    
}
    
    @Override
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
    
    @Override
    public void remover(long codigo) throws ErroInternoException, EventoInexistenteException{
    try{
         Evento e = buscarCodigo(codigo);
        this.em.remove(e);
        }catch(IllegalArgumentException e ){
            throw new ErroInternoException(e);
        }
    }
    
}
