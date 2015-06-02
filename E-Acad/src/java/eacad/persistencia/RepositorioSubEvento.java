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
     * @param e - Adiciona um SubEvento a base de dados;
     * @throws eacad.exceptions.ErroInternoException
     * @throws eacad.exceptions.DatasIncorretas
     */
    public void adicionar(SubEvento e) throws ErroInternoException, DatasIncorretas;

    /**
     * Assinatura do método atualizarVagasSubEvento.
     *
     * @param vagas;
     * @param ev;
     * @throws ErroInternoException;
     * @throws SubEventoInexistenteException;
     */
    public void atualizarVagasSubEvento(int vagas,SubEvento ev) throws ErroInternoException, SubEventoInexistenteException;
    
    /**
     * Assinatura do método para listar SubEvento.
     *
     * @return List - Retorna uma lista de eventos.
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.SubEventoInexistenteException;
     */
    public List<SubEvento> listarTudoSubEvento() throws ErroInternoException, SubEventoInexistenteException;

    /**
     * Assinatura do método atualizar subEvento.
     *
     * @param e - Atualiza um evento na base de dados;
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.SubEventoInexistenteException;
     */
    public void atualizar(SubEvento e) throws ErroInternoException, SubEventoInexistenteException;

    /**
     * Assinatura do método de listar subEvento através do nome.
     *
     * @param evento - busca Eventos atraves do nome;
     * @return List- uma lista de eventos.
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.SubEventoInexistenteException;
     */
    public List<SubEvento> buscarListSubEvento(Evento evento) throws ErroInternoException, SubEventoInexistenteException;

    /**
     * Assinatura do metodo buscarCodigo.
     *
     * @param codigo - busca um subEvento através do código;
     * @return Evento - Retorna um subEvento.
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.SubEventoInexistenteException;
     */
    public SubEvento buscarCodigo(long codigo) throws ErroInternoException, SubEventoInexistenteException;

    /**
     * Assinatura do método para buscar um SubEvento pelo nome.
     *
     * @param nome - busca um subEvento através no nome;
     * @return SubEvento - Retorna um subEvento.
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.SubEventoInexistenteException;
     */
    public SubEvento buscarNomeSubEvento(String nome) throws ErroInternoException, SubEventoInexistenteException;

    /**
     * Assinatura do método remover subEvento.
     *
     * @param codigo - remove um subEvento da base de dados.
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.SubEventoInexistenteException;
     */
    public void remover(long codigo) throws ErroInternoException, SubEventoInexistenteException;

}
