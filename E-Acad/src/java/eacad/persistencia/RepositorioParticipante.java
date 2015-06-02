/**
 * Interface Participante, onde serão contidas as assinaturas dos metodos da
 * camada de persistência.<p/>
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
import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface onde serão contidas as assinaturas de todos os metodos da
 * persistencia de Participante.
 */
@Local
public interface RepositorioParticipante extends Serializable {

    /**
     * Assinatura do método para listar os participantes de um evento.
     *
     * @param e;
     * @return List.
     * @throws ErroInternoException;
     * @throws ParticipanteInexistenteException;
     */
    public List<Participante> listarTudoEventoParticipante(Evento e) throws ErroInternoException, ParticipanteInexistenteException;

    /**
     * Assinatura do método para listar os participantes de um SubEvento.
     *
     * @param e;
     * @return List.
     * @throws ErroInternoException;
     * @throws ParticipanteInexistenteException;
     */
    public List<Participante> listarTudoSubEventoParticipante(SubEvento e) throws ErroInternoException, ParticipanteInexistenteException;

    /**
     * Assinatura do método adicionar um Participante.
     *
     * @param e;
     * @throws ErroInternoException;
     */
    public void adicionar(Participante e) throws ErroInternoException;

    /**
     * Assinatura do método buscar Participante.
     *
     * @param cpf;
     * @return Participante;
     * @throws ErroInternoException;
     * @throws ParticipanteInexistenteException;
     */
    public Participante buscar(String cpf) throws ErroInternoException, ParticipanteInexistenteException;

    /**
     * Assinatura do método buscar Código.
     *
     * @param codigo;
     * @return Participante.
     * @throws ErroInternoException;
     * @throws ParticipanteInexistenteException;
     */
    public Participante buscarCodigo(long codigo) throws ErroInternoException, ParticipanteInexistenteException;

    /**
     * Assinatura do método remover Participante.
     *
     * @param codigo;
     * @throws ErroInternoException;
     * @throws ParticipanteInexistenteException;
     */
    public void remover(long codigo) throws ErroInternoException, ParticipanteInexistenteException;

    /**
     * Assinatura do método atualizar Participante.
     *
     * @param participante;
     * @throws ErroInternoException;
     * @throws ParticipanteInexistenteException;
     */
    public void atualizar(Participante participante) throws ErroInternoException, ParticipanteInexistenteException;

}
