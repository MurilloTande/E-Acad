/**
 * Interface SubEvento, onde serão contidas as assinaturas dos metodos da camada
 * de persistência.<p/>
 *
 * @author Murillo Tande
 * @author Matheus Barbosa
 * @author Hugo Calado
 * @author Felipe Xavier
 */
package eacad.persistencia;

import eacad.entidades.Evento;
import eacad.entidades.SubEvento;
import eacad.exceptions.DatasIncorretas;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.SubEventoInexistenteException;
import java.io.Serializable;
import java.util.List;

/**
 * Interface onde serão contidas as assinaturas de todos os metodos da
 * persistencia.
 */
public interface RepositorioSubEvento extends Serializable {

    /**
     * Assinatura do método adicionar subEvento.
     *
     * @param e;
     * @throws ErroInternoException
     * @throws DatasIncorretas
     */
    public void adicionar(SubEvento e) throws ErroInternoException, DatasIncorretas;

    /**
     * Assinatura do método atualizarVagasSubEvento.
     *
     * @param vagas;
     * @param ev;
     * @throws ErroInternoException
     * @throws SubEventoInexistenteException
     */
    public void atualizarVagasSubEvento(int vagas,SubEvento ev) throws ErroInternoException, SubEventoInexistenteException;
    
    /**
     * Assinatura do método para listar SubEvento.
     *
     * @return List - Retorna uma lista de eventos.
     * @throws ErroInternoException
     * @throws SubEventoInexistenteException
     */
    public List<SubEvento> listarTudoSubEvento() throws ErroInternoException, SubEventoInexistenteException;

    /**
     * Assinatura do método atualizar subEvento.
     *
     * @param e;
     * @throws ErroInternoException
     * @throws SubEventoInexistenteException
     */
    public void atualizar(SubEvento e) throws ErroInternoException, SubEventoInexistenteException;

    /**
     * Assinatura do método de listar subEvento através do nome.
     *
     * @param evento;
     * @return List.
     * @throws ErroInternoException
     * @throws SubEventoInexistenteException
     */
    public List<SubEvento> buscarListSubEvento(Evento evento) throws ErroInternoException, SubEventoInexistenteException;

    /**
     * Assinatura do metodo buscarCodigo.
     *
     * @param codigo;
     * @return Evento.
     * @throws ErroInternoException
     * @throws SubEventoInexistenteException
     */
    public SubEvento buscarCodigo(long codigo) throws ErroInternoException, SubEventoInexistenteException;

    /**
     * Assinatura do método para buscar um SubEvento pelo nome.
     *
     * @param nome;
     * @return SubEvento.
     * @throws ErroInternoException
     * @throws SubEventoInexistenteException
     */
    public SubEvento buscarNomeSubEvento(String nome) throws ErroInternoException, SubEventoInexistenteException;

    /**
     * Assinatura do método remover subEvento.
     *
     * @param codigo;
     * @throws ErroInternoException
     * @throws SubEventoInexistenteException
     */
    public void remover(long codigo) throws ErroInternoException, SubEventoInexistenteException;

}
