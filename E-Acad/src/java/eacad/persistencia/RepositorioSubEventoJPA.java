/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eacad.persistencia;


import eacad.entidades.SubEvento;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.SubEventoInexistenteException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Stateless
public class RepositorioSubEventoJPA implements RepositorioSubEvento{
    
    @PersistenceContext(unitName = "EacadPU")
    private EntityManager em;
    
    
    @Override
     public void adicionar(SubEvento e) throws ErroInternoException{
      try {
            this.em.persist(e);
        } catch (Exception r) {
            throw new ErroInternoException(r);
        }
     }
     
    @Override
    public List<SubEvento> listarTudoSubEvento() throws ErroInternoException, SubEventoInexistenteException{
        try {
        TypedQuery<SubEvento> consulta = this.em.createQuery("select e from SubEvento e", SubEvento.class);
        return  consulta.getResultList();
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
}
 
   
    
    
    @Override
    public void atualizar(SubEvento e) throws ErroInternoException, SubEventoInexistenteException{
    
        SubEvento ev = buscarCodigo(e.getCodigo());
        e.setNome(ev.getNome());
        e.setData_final(ev.getData_final());
        e.setData_inicio(ev.getData_inicio());
        e.setDescricao(ev.getDescricao());
        e.setTotal_vagas(ev.getTotal_vagas());
        
        try {
            this.em.merge(e);
        } catch (Exception es) {
            throw new ErroInternoException(es);
        }
        
    }
    
  
    @Override
    public List<SubEvento> buscarNomeListSubEvento(String nome) throws ErroInternoException, SubEventoInexistenteException{
          try {
           TypedQuery<SubEvento> consulta = this.em.createQuery("select e from SubEvento e where e.nome like :nome", SubEvento.class);
           consulta.setParameter("nome", "%" + nome + "%");
            return consulta.getResultList();
           
        }catch(NoResultException es){
                 throw new SubEventoInexistenteException();  
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
        
}
    
   
    @Override
    public SubEvento buscarCodigo(long codigo) throws ErroInternoException, SubEventoInexistenteException{
        SubEvento p = null;

        try {
            p = this.em.find(SubEvento.class, codigo);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }

        if (p == null) {
            throw new SubEventoInexistenteException();
        }

        return p;
    
}
    
 
    @Override
    public SubEvento buscarNomeSubEvento(String nome) throws ErroInternoException, SubEventoInexistenteException{
        
         try {
           TypedQuery<SubEvento> consulta = this.em.createQuery("select e from SubEvento e where e.nome like :nome", SubEvento.class);
           consulta.setParameter("nome", "%" + nome + "%");
            return consulta.getSingleResult();
           
        }catch(NoResultException es){
                 throw new SubEventoInexistenteException();  
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
}
    
    @Override
    public void remover(long codigo) throws ErroInternoException, SubEventoInexistenteException{
    try{
         SubEvento e = buscarCodigo(codigo);
        this.em.remove(e);
        }catch(IllegalArgumentException e ){
            throw new ErroInternoException(e);
        }
    }
    
}
