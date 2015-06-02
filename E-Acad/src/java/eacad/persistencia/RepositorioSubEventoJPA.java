/**
 * Clase repositório SubEvento, onde serão contidas os metodos da camada de
 * persistência em conexão com a base de dados.<p/>
 *
 * @author Murillo Tande
 * @author Matheus Barbosa
 * @author Hugo Calado
 * @author Felipe Xavier
 */
package eacad.persistencia;

import eacad.entidades.Evento;
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
public class RepositorioSubEventoJPA implements RepositorioSubEvento {

    @PersistenceContext(unitName = "EacadPU")
    private EntityManager em;

    /**
     * Método para adicionar um SubEvento a base de dados.
     *
     * @throws eacad.exceptions.ErroInternoException;
     */
    @Override
    public void adicionar(SubEvento e) throws ErroInternoException {
        try {
            this.em.persist(e);
        } catch (Exception r) {
            throw new ErroInternoException(r);
        }
    }

    /**
     * Método para listar um SubEvento.
     *
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.SubEventoInexistenteException;
     */
    @Override
    public List<SubEvento> listarTudoSubEvento() throws ErroInternoException, SubEventoInexistenteException {
        try {
            TypedQuery<SubEvento> consulta = this.em.createQuery("select e from SubEvento e", SubEvento.class);
            return consulta.getResultList();
        } catch (NoResultException t) {
            throw new SubEventoInexistenteException(t);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    /**
     * Método para atualizar SubEvento da base de dados.
     *
     * @param ev;
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.SubEventoInexistenteException;
     */
    @Override
    public void atualizar(SubEvento ev) throws ErroInternoException, SubEventoInexistenteException {

        SubEvento e = buscarCodigo(ev.getCodigo());
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

    /**
     * Método atualizarVagasSubEvento.
     *
     * @param vagas;
     * @param ev;
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.SubEventoInexistenteException;
     */
    @Override
    public void atualizarVagasSubEvento(int vagas,SubEvento ev) throws ErroInternoException, SubEventoInexistenteException {

        SubEvento e = buscarCodigo(ev.getCodigo());
        e.setContVagasSubEvento(vagas);
       

        try {
            this.em.merge(e);
        } catch (Exception es) {
            throw new ErroInternoException(es);
        }

    }
    
    /**
     * Método para listar os SubEventos de um Evento.
     *
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.SubEventoInexistenteException;
     */
    @Override
    public List<SubEvento> buscarListSubEvento(Evento evento) throws ErroInternoException, SubEventoInexistenteException {
        try {
            TypedQuery<SubEvento> consulta = this.em.createQuery("select s from SubEvento s where s.eventoPai.codigo = :codigo", SubEvento.class);
            consulta.setParameter("codigo", evento.getCodigo());
            return consulta.getResultList();

        } catch (NoResultException es) {
            throw new SubEventoInexistenteException(es);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }

    }

    /**
     * Método para buscar um SubEvento a partir do código.
     *
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.SubEventoInexistenteException;
     */
    @Override
    public SubEvento buscarCodigo(long codigo) throws ErroInternoException, SubEventoInexistenteException {
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

    /**
     * Método para buscar um SubEvento a partir do Nome.
     *
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.SubEventoInexistenteException;
     */
    @Override
    public SubEvento buscarNomeSubEvento(String nome) throws ErroInternoException, SubEventoInexistenteException {

        try {
            TypedQuery<SubEvento> consulta = this.em.createQuery("select e from SubEvento e where e.nome like :nome", SubEvento.class);
            consulta.setParameter("nome", "%" + nome + "%");
            return consulta.getSingleResult();

        } catch (NoResultException es) {
            throw new SubEventoInexistenteException();
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    /**
     * Método para remover um SubEvento da base de dados.
     *
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.SubEventoInexistenteException;
     */
    @Override
    public void remover(long codigo) throws ErroInternoException, SubEventoInexistenteException {
        try {
            SubEvento e = buscarCodigo(codigo);
            this.em.remove(e);
        } catch (IllegalArgumentException e) {
            throw new ErroInternoException(e);
        }
    }

}
