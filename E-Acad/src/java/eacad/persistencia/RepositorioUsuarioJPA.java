/**
 * @author Tande
 */
package eacad.persistencia;

import eacad.entidades.Usuario;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.UsuarioInexistenteException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class RepositorioUsuarioJPA implements RepositorioUsuario{

    @PersistenceContext(unitName = "EacadPU")// O NOME TÁ LÁ EM PERSISTENCE
    private EntityManager em;
    
    @Override
    public void adicionar(Usuario usuario) throws ErroInternoException{
        try {
            this.em.persist(usuario);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }


    @Override
    public List<Usuario> listar() throws ErroInternoException {
        try {
        TypedQuery<Usuario> consulta = this.em.createQuery("select u from Usuario u", Usuario.class);
        return  consulta.getResultList();
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
       
    }

    
    @Override
    public void atualizar(Usuario usuario) throws ErroInternoException, UsuarioInexistenteException {
        Usuario u = buscar(usuario.getCpf());
        u.setNome(usuario.getNome());
        u.setSenha(usuario.getSenha());
        u.setEmail(usuario.getEmail());
        
        try {
            this.em.merge(u);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }
    
    @Override
    public List<Usuario> buscarNome(String nome) throws ErroInternoException, UsuarioInexistenteException {
       
        try {
           TypedQuery<Usuario> consulta = this.em.createQuery("select u from Usuario u where u.nome like :nome", Usuario.class);
           consulta.setParameter("nome", "%" + nome + "%");
           return consulta.getResultList();
        } 
        catch (NoResultException t) {
            throw new UsuarioInexistenteException(t);
        }
        catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    @Override
    public Usuario buscar(String cpf) throws ErroInternoException, UsuarioInexistenteException {
       try {
           TypedQuery<Usuario> consulta = this.em.createQuery("select u from Usuario u where u.cpf like :cpf", Usuario.class);
           consulta.setParameter("cpf", "%" + cpf + "%");
            return consulta.getSingleResult();
           
        }catch(NoResultException es){
                 throw new UsuarioInexistenteException(es);  
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    
    }
    
    @Override
      public Usuario buscarEmail(String email) throws ErroInternoException, UsuarioInexistenteException {
       try {
           TypedQuery<Usuario> consulta = this.em.createQuery("select u from Usuario u where u.email like :email", Usuario.class);
           consulta.setParameter("email", "%" + email + "%");
            return consulta.getSingleResult();
           
        }catch(NoResultException es){
                 throw new UsuarioInexistenteException();  
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    
    }
    
     @Override
    public void remover(String cpf) throws  ErroInternoException,UsuarioInexistenteException{
        try{
         Usuario u = buscar(cpf);
        this.em.remove(u);
        }catch(IllegalArgumentException e ){
            throw new ErroInternoException(e);
        }
    }
}
