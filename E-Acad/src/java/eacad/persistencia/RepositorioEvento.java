/**
 * Interface Evento, onde serão contidas as assinaturas dos metodos da camada de
 * persistência.<p/>
 *
 * @author Murillo Tande
 * @author Matheus Barbosa
 * @author Hugo Calado
 * @author Felipe Xavier
 */
package eacad.persistencia;

import eacad.entidades.Evento;
import eacad.entidades.Participante;
import eacad.exceptions.DatasIncorretas;
import java.io.Serializable;
import javax.ejb.Local;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.EventoInexistenteException;
import eacad.exceptions.ParticipanteExistenteException;
import java.util.List;

/**
 * Interface onde serão contidas as assinaturas de todos os metodos da
 * persistencia.
 */
@Local
public interface RepositorioEvento extends Serializable {

    /**
     * Assinatura do método para validação de participante;
     *
     * @param e;
     * @param p;
     * @return Evento
     * @throws ErroInternoException
     * @throws ParticipanteExistenteException
     */
    public Evento buscarValidarPartipante(Evento e, Participante p) throws ErroInternoException, ParticipanteExistenteException;

    /**
     * Assinatura do método para atualizar as vagas de um evento.
     *
     * @param vagas;
     * @param ev;
     * @throws ErroInternoException
     * @throws EventoInexistenteException
     */
    public void atualizarVagasEvento(int vagas, Evento ev) throws ErroInternoException, EventoInexistenteException;

    /**
     * Assinatura do método adicionar evento.
     *
     * @param e;
     * @throws ErroInternoException
     * @throws DatasIncorretas
     */
    public void adicionar(Evento e) throws ErroInternoException, DatasIncorretas;

    /**
     * Assinatura do método listarTudoEvento.
     *
     * @return List - Retorna uma lista de eventos.
     * @throws ErroInternoException
     * @throws EventoInexistenteException
     */
    public List<Evento> listarTudoEvento() throws ErroInternoException, EventoInexistenteException;

    /**
     * Assinatura do método atualizar evento.
     *
     * @param e;
     * @throws ErroInternoException
     * @throws EventoInexistenteException
     */
    public void atualizar(Evento e) throws ErroInternoException, EventoInexistenteException;

    /**
     * Assinatura do método de listar evento através do nome.
     *
     * @param nome;
     * @return List- uma lista de eventos.
     * @throws ErroInternoException
     * @throws EventoInexistenteException
     */
    public List<Evento> buscarNomeListEvento(String nome) throws ErroInternoException, EventoInexistenteException;

    /**
     * Assinatura do metodo buscarCodigo.
     *
     * @param codigo - Busca um evento através do código
     * @return Evento - Retorna um evento.
     * @throws ErroInternoException
     * @throws EventoInexistenteException
     */
    public Evento buscarCodigo(long codigo) throws ErroInternoException, EventoInexistenteException;

    /**
     * Assinatura do método buscarNomeEvento.
     *
     * @param nome - Busca um evento pelo nome
     * @return Evento - Retorna um evento.
     * @throws ErroInternoException
     * @throws EventoInexistenteException
     */
    public Evento buscarNomeEvento(String nome) throws ErroInternoException, EventoInexistenteException;

    /**
     * Assinatura do método remover evento.
     *
     * @param codigo;
     * @throws ErroInternoException
     * @throws EventoInexistenteException
     */
    public void remover(long codigo) throws ErroInternoException, EventoInexistenteException;

    /**
     * Assunatura do médodo EventosUsuario.
     *
     * @param cpf;
     * @return List - retorna uma lista de eventos.
     * @throws ErroInternoException
     * @throws EventoInexistenteException
     */
    public List<Evento> EventosUsuario(String cpf) throws ErroInternoException, EventoInexistenteException;
}
