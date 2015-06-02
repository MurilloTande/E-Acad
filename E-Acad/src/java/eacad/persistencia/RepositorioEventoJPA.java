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
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.DatasIncorretas;
import eacad.exceptions.EventoInexistenteException;
import eacad.exceptions.ParticipanteExistenteException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class RepositorioEventoJPA implements RepositorioEvento {

    @PersistenceContext(unitName = "EacadPU")
    private EntityManager em;

    /**
     * Método para adicionar um Evento a base de dados.
     *
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.DatasIncorretas;
     */
    @Override
    public void adicionar(Evento e) throws ErroInternoException, DatasIncorretas {
        try {
            this.em.persist(e);
        } catch (Exception r) {
            throw new ErroInternoException(r);
        }
    }

    /**
     * Método para buscar um Participante de um evento na base de dados.
     *
     * @param e;
     * @param p;
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.ParticipanteExistenteException;
     */
    @Override
    public Evento buscarValidarPartipante(Evento e, Participante p) throws ErroInternoException, ParticipanteExistenteException {

        return null;

    }

    /**
     * Método para listar o(s) Eventos da base de dados.
     *
     * @throws eacad.exceptions.ErroInternoException
     * @throws eacad.exceptions.EventoInexistenteException
     */
    @Override
    public List<Evento> listarTudoEvento() throws ErroInternoException, EventoInexistenteException {
        try {
            TypedQuery<Evento> consulta = this.em.createQuery("select e from Evento e", Evento.class);
            return consulta.getResultList();
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    /**
     * Método para listar os Eventos de um Usuário a base de dados.
     *
     * @throws eacad.exceptions.ErroInternoException
     * @throws eacad.exceptions.EventoInexistenteException
     */
    @Override
    public List<Evento> EventosUsuario(String cpf) throws ErroInternoException, EventoInexistenteException {
        try {
            TypedQuery<Evento> consulta = this.em.createQuery("select e from Evento e where e.criador.cpf like :cpf", Evento.class);
            consulta.setParameter("cpf", "%" + cpf + "%");
            return consulta.getResultList();
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    /**
     * Método para atulizar um Evento a base de dados.
     *
     * @param ev;
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.EventoInexistenteException;
     */
    @Override
    public void atualizar(Evento ev) throws ErroInternoException, EventoInexistenteException {

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

    /**
     * Método para atualizar as vagas de um Evento.
     *
     * @param vagas;
     * @param ev;
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.EventoInexistenteException;
     */
    @Override
    public void atualizarVagasEvento(int vagas, Evento ev) throws ErroInternoException, EventoInexistenteException {

        Evento e = buscarCodigo(ev.getCodigo());

        e.setContVagasEvento(vagas);
        try {
            this.em.merge(e);
        } catch (Exception es) {
            throw new ErroInternoException(es);
        }

    }

    /**
     * Método para buscar Eventos apartir de um nome.
     *
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.EventoInexistenteException;
     */
    @Override
    public List<Evento> buscarNomeListEvento(String nome) throws ErroInternoException, EventoInexistenteException {
        try {
            TypedQuery<Evento> consulta = this.em.createQuery("select e from Evento e where e.nome like :nome", Evento.class);
            consulta.setParameter("nome", "%" + nome + "%");
            return consulta.getResultList();

        } catch (NoResultException es) {
            throw new EventoInexistenteException();
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }

    }

    /**
     * Método para buscar um Eventos a partir de um código
     *
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.EventoInexistenteException;
     */
    @Override
    public Evento buscarCodigo(long codigo) throws ErroInternoException, EventoInexistenteException {
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

    /**
     *
     * Método para buscar um Evento a partir do nome.
     *
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.EventoInexistenteException;
     */
    @Override
    public Evento buscarNomeEvento(String nome) throws ErroInternoException, EventoInexistenteException {

        try {
            TypedQuery<Evento> consulta = this.em.createQuery("select e from Evento e where e.nome like :nome", Evento.class);
            consulta.setParameter("nome", "%" + nome + "%");
            return consulta.getSingleResult();

        } catch (NoResultException es) {
            throw new EventoInexistenteException();
        } catch (Exception e) {
            throw new ErroInternoException(e);
        }
    }

    /**
     * Método para remover um Evento a base de dados.
     *
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.EventoInexistenteException;
     */
    @Override
    public void remover(long codigo) throws ErroInternoException, EventoInexistenteException {
        try {
            Evento e = buscarCodigo(codigo);
            this.em.remove(e);
        } catch (IllegalArgumentException e) {
            throw new ErroInternoException(e);
        }
    }

}
