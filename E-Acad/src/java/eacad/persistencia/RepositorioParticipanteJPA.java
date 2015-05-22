/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eacad.persistencia;

import eacad.entidades.Participante;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.ParticipanteInexistenteException;
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
                 throw new ParticipanteInexistenteException();  
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    
    }
    
}