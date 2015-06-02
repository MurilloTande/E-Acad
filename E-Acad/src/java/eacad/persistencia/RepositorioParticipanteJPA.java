/**
 * Clase repositório Participante, onde serão contidas os metodos da camada de
 * persistência em conexão com a base de dados.<p/>
 *
 * @author Murillo Tande
 * @author Matheus Barbosa
 * @author Hugo Calado
 * @author Felipe Xavier
 */
package eacad.persistencia;

import eacad.entidades.Evento;
import eacad.entidades.Participante;
import eacad.entidades.SubEvento;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.ParticipanteInexistenteException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class RepositorioParticipanteJPA implements RepositorioParticipante {

    @PersistenceContext(unitName = "EacadPU")
    private EntityManager em;

    /**
     * Método para adicionar um Participante a base de dados
     *
     * @param e;
     * @throws eacad.exceptions.ErroInternoException;
     */
    @Override
    public void adicionar(Participante e) throws ErroInternoException {
        try {
            this.em.persist(e);
        } catch (Exception r) {
            throw new ErroInternoException(r);
        }
    }

    /**
     * Método para Listar Participantes de um Evento.
     *
     * @param e;
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.ParticipanteInexistenteException;
     */
    @Override
    public List<Participante> listarTudoEventoParticipante(Evento e) throws ErroInternoException, ParticipanteInexistenteException {
        try {
            TypedQuery<Participante> consulta = this.em.createQuery("select u from Participante u join u.evento ue on ue.codigo = :codigo ", Participante.class);
            consulta.setParameter("codigo", e.getCodigo());
            return consulta.getResultList();
        } catch (NoResultException es) {
            throw new ParticipanteInexistenteException(es);

        } catch (Exception ex) {
            throw new ErroInternoException(ex);
        }
    }

    /**
     * Método para listar Participantes de um SubEvento.
     *
     * @param e;
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.ParticipanteInexistenteException;
     */
    @Override
    public List<Participante> listarTudoSubEventoParticipante(SubEvento e) throws ErroInternoException, ParticipanteInexistenteException {
        try {
            TypedQuery<Participante> consulta = this.em.createQuery("select u from Participante u join u.subEvento ue on ue.codigo = :codigo ", Participante.class);
            consulta.setParameter("codigo", e.getCodigo());
            return consulta.getResultList();
        } catch (NoResultException es) {
            throw new ParticipanteInexistenteException(es);

        } catch (Exception ex) {
            throw new ErroInternoException(ex);
        }
    }

    /**
     * Método para buscar um Participante.
     *
     * @param cpf;
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.ParticipanteInexistenteException;
     */
    @Override
    public Participante buscar(String cpf) throws ErroInternoException, ParticipanteInexistenteException {
        try {
            TypedQuery<Participante> consulta = this.em.createQuery("select u from Participante u where u.cpf like :cpf", Participante.class);
            consulta.setParameter("cpf", "%" + cpf + "%");
            return consulta.getSingleResult();
        } catch (NoResultException es) {
            throw new ParticipanteInexistenteException(es);

        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    /**
     * Método para buscar um participante através do código.
     *
     * @param codigo;
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.ParticipanteInexistenteException;
     */
    @Override
    public Participante buscarCodigo(long codigo) throws ErroInternoException, ParticipanteInexistenteException {

        Participante p = null;

        try {
            p = this.em.find(Participante.class, codigo);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }

        if (p == null) {
            throw new ParticipanteInexistenteException();
        }

        return p;

    }

    /**
     * Método para remover um Participante.
     *
     * @param codigo;
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.ParticipanteInexistenteException;
     */
    @Override
    public void remover(long codigo) throws ErroInternoException, ParticipanteInexistenteException {
        try {
            Participante e = buscarCodigo(codigo);
            this.em.remove(e);
        } catch (IllegalArgumentException e) {
            throw new ErroInternoException(e);
        }
    }

    /**
     * Método para atualizar Participante.
     *
     * @param participante;
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.ParticipanteInexistenteException;
     */
    @Override
    public void atualizar(Participante participante) throws ErroInternoException, ParticipanteInexistenteException {

        Participante u = buscar(participante.getCpf());
        u.setPrimeiroNome(participante.getPrimeiroNome());
        u.setSobreNome(participante.getSobreNome());
        u.setEmail(participante.getEmail());
        u.setSubEvento(participante.getSubEvento());

        try {
            this.em.merge(u);
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }

    }
}
