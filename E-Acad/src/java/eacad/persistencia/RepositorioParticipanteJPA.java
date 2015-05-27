/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eacad.persistencia;

import eacad.entidades.Evento;
import eacad.entidades.Participante;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.ParticipanteExistenteException;
import eacad.exceptions.ParticipanteInexistenteException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class RepositorioParticipanteJPA implements RepositorioParticipante{
    
    @PersistenceContext(unitName = "EacadPU")
    private EntityManager em;
    
    @Override
    public void adicionar(Participante e) throws ErroInternoException{
      try {
            this.em.persist(e);
        } catch (Exception r) {
            throw new ErroInternoException(r);
        }
     }
    
    @Override
    public Participante buscar(String cpf) throws ErroInternoException, ParticipanteInexistenteException {
       try {
           TypedQuery<Participante> consulta = this.em.createQuery("select u from Participante u where u.cpf like :cpf", Participante.class);
           consulta.setParameter("cpf", "%" + cpf + "%");
            return consulta.getSingleResult();
           
        }catch(NoResultException es){
                 throw new ParticipanteInexistenteException(es);  
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    
    }
    
    public Participante buscarValidarPartipante(Evento e) throws ErroInternoException, ParticipanteExistenteException {
       try {
           TypedQuery<Participante> consulta = this.em.createQuery("select p from Participante p JOIN p.evento pe where pe.codigo = :codigo  ", Participante.class);
           consulta.setParameter("codigo",e.getCodigo());
            return consulta.getSingleResult();
           
       }catch (Exception ex) {
            throw new ErroInternoException(ex);
        }
    }
    
    @Override
    public void remover(String cpf) throws ErroInternoException, ParticipanteInexistenteException {
        try{
         Participante p = buscar(cpf);
        this.em.remove(p);
        }catch(IllegalArgumentException e ){
            throw new ErroInternoException(e);
        }
    }

    @Override
    public void atualizar(Participante part) throws ErroInternoException, ParticipanteInexistenteException {
        Participante p  = buscar(part.getCpf());
        p.setEmail(part.getEmail());
        p.setPrimeiroNome(part.getPrimeiroNome());
        p.setSobreNome(part.getSobreNome());
        
        try {
            this.em.merge(p);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public List<Participante> listar() throws ErroInternoException {
        try {
        TypedQuery<Participante> consulta = this.em.createQuery("select p from Participante p", Participante.class);
        return  consulta.getResultList();
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }
    
    
}
