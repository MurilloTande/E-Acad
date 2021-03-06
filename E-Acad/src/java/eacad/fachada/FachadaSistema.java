/**
 * Classe da Fachada do sistema, onde estão contidos todos os métodos in-line do
 * sistema.
 *
 * @author Murillo Tande
 * @author Matheus Barbosa
 * @author Hugo Calado
 * @author Felipe Xavier
 */
package eacad.fachada;

import eacad.entidades.Evento;
import eacad.entidades.Participante;
import eacad.entidades.SubEvento;
import eacad.entidades.Usuario;
import eacad.exceptions.DatasIncorretas;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.EventoExistenteException;
import eacad.exceptions.EventoInexistenteException;
import eacad.exceptions.ParticipanteExistenteException;
import eacad.exceptions.ParticipanteInexistenteException;
import eacad.exceptions.SubEventoExistenteException;
import eacad.exceptions.SubEventoInexistenteException;
import eacad.exceptions.UsuarioExistenteException;
import eacad.exceptions.UsuarioInexistenteException;
import eacad.exceptions.VagasIncorretasException;
import eacad.negocio.CadastroEvento;
import eacad.negocio.CadastroParticipante;
import eacad.negocio.CadastroSubEvento;
import eacad.negocio.CadastroUsuario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class FachadaSistema {

    @EJB
    private CadastroUsuario usuarios;
    @EJB
    private CadastroEvento evento;
    @EJB
    private CadastroSubEvento subEvento;
    @EJB
    private CadastroParticipante participante;

    //---------------------------Usuario---------------------------
    /**
     * Assinatura in line do método adicionar.
     *
     * @param usuario;
     * @throws eacad.exceptions.ErroInternoException
     * @throws eacad.exceptions.UsuarioExistenteException
     * @throws eacad.exceptions.UsuarioInexistenteException
     */
    public void adicionarUsuario(Usuario usuario) throws ErroInternoException, UsuarioExistenteException, UsuarioInexistenteException {
        this.usuarios.adicionarUsuario(usuario);
    }

    /**
     * Assinatura in line do método listar Usuário.
     *
     * @return List;
     * @throws eacad.exceptions.ErroInternoException
     * @throws eacad.exceptions.UsuarioInexistenteException
     */
    public List<Usuario> listar() throws ErroInternoException, UsuarioInexistenteException {
        return this.usuarios.listar();
    }

    /**
     * Assinatura in line do método atualizar Usuário.
     *
     * @param usuario;
     * @throws ErroInternoException
     * @throws UsuarioInexistenteException
     */
    public void atualizar(Usuario usuario) throws ErroInternoException, UsuarioInexistenteException {
        this.usuarios.Atualizar(usuario);
    }

    /**
     * Assinatura in line do método buscar Usuário.
     *
     * @param cpf;
     * @return Usuario.
     * @throws eacad.exceptions.ErroInternoException
     * @throws eacad.exceptions.UsuarioInexistenteException
     */
    public Usuario buscar(String cpf) throws ErroInternoException, UsuarioInexistenteException {
        return this.usuarios.buscar(cpf);
    }

    /**
     * Assinatura in line do método buscar Usuário através do e-mail.
     *
     * @param email;
     * @return Usuário.
     * @throws eacad.exceptions.ErroInternoException
     * @throws eacad.exceptions.UsuarioInexistenteException
     */
    public Usuario buscarEmail(String email) throws ErroInternoException, UsuarioInexistenteException {
        return this.usuarios.buscarEmail(email);
    }

    /**
     * Assinatura in line do método remover Usuário.
     *
     * @param cpf;
     * @throws ErroInternoException
     * @throws UsuarioInexistenteException
     * @throws EventoInexistenteException 
     * @throws SubEventoInexistenteException
     * @throws ParticipanteInexistenteException
     */
    public void remover(String cpf) throws ErroInternoException, UsuarioInexistenteException, EventoInexistenteException, SubEventoInexistenteException, ParticipanteInexistenteException {
        this.usuarios.remover(cpf);
    }

    //---------------------------Evento---------------------------
    /**
     * Assinatura in line do método adicionar Evento.
     *
     * @param e;
     * @throws eacad.exceptions.ErroInternoException
     * @throws eacad.exceptions.EventoExistenteException
     * @throws eacad.exceptions.DatasIncorretas
     */
    public void adicionarEvento(Evento e) throws ErroInternoException, EventoExistenteException, DatasIncorretas {
        this.evento.adicionar(e);
    }

    /**
     * Assinatura in line do método listar Evento.
     *
     * @return List.
     * @throws eacad.exceptions.ErroInternoException
     * @throws eacad.exceptions.EventoInexistenteException
     */
    public List<Evento> listarTudoEvento() throws ErroInternoException, EventoInexistenteException {
        return this.evento.listarTudoEvento();
    }

    /**
     * Assinatura in line do método listar Eventos de um Usuário.
     *
     * @param cpf;
     * @return List.
     * @throws eacad.exceptions.ErroInternoException
     * @throws eacad.exceptions.EventoInexistenteException
     */
    public List<Evento> EventosUsuario(String cpf) throws ErroInternoException, EventoInexistenteException {
        return this.evento.EventosUsuario(cpf);
    }

    /**
     * Assinatura in line do método atualizar Evento.
     *
     * @param e;
     * @throws eacad.exceptions.ErroInternoException
     * @throws eacad.exceptions.EventoInexistenteException
     */
    public void atualizarEvento(Evento e) throws ErroInternoException, EventoInexistenteException, VagasIncorretasException {
        this.evento.atualizar(e);
    }

    /**
     * Assinatura in line do método atualizar vagas do Evento.
     *
     * @param vagas;
     * @param ev;
     * @throws ErroInternoException
     * @throws EventoInexistenteException
     */
    public void atualizarVagasEvento(int vagas, Evento ev) throws ErroInternoException, EventoInexistenteException {
        this.evento.atualizarVagasEvento(vagas, ev);
    }

    /**
     * Assinatura in line do método buscar Evento através do nome.
     *
     * @param nome;
     * @return List.
     * @throws eacad.exceptions.ErroInternoException
     * @throws eacad.exceptions.EventoInexistenteException
     */
    public List<Evento> buscarNomeListEvento(String nome) throws ErroInternoException, EventoInexistenteException {
        return this.evento.buscarNomeListEvento(nome);
    }

    /**
     * Assinatura in line do método validação de Participante em um Evento.
     *
     * @param f;
     * @param p;
     * @return Evento.
     * @throws eacad.exceptions.ErroInternoException
     * @throws eacad.exceptions.ParticipanteExistenteException
     */
    public Evento buscarValidarPartipante(Evento f, Participante p) throws ErroInternoException, ParticipanteExistenteException {
        return this.evento.buscarValidarPartipante(f, p);
    }

    /**
     * Assinatura in line do método buscar Evento, através do código.
     *
     * @param codigo
     * @return Evento.
     * @throws eacad.exceptions.ErroInternoException
     * @throws eacad.exceptions.EventoInexistenteException
     */
    public Evento buscarCodigoEvento(long codigo) throws ErroInternoException, EventoInexistenteException {
        return this.evento.buscarCodigo(codigo);
    }

    /**
     * Assinatura in line do método buscar Evento, através do nome.
     *
     * @param nome;
     * @return Evento.
     * @throws eacad.exceptions.ErroInternoException
     * @throws eacad.exceptions.EventoInexistenteException
     */
    public Evento buscarNomeEvento(String nome) throws ErroInternoException, EventoInexistenteException {
        return this.evento.buscarNomeEvento(nome);
    }

    /**
     * Assinatura in line do método remover Evento.
     *
     * @param codigo;
     * @throws ErroInternoException
     * @throws EventoInexistenteException
     * @throws SubEventoInexistenteException
     * @throws ParticipanteInexistenteException
     */
    public void removerEvento(long codigo) throws ErroInternoException, EventoInexistenteException, SubEventoInexistenteException, ParticipanteInexistenteException {
        this.evento.remover(codigo);
    }

    //---------------------------SubEvento---------------------------
    /**
     * Assinatura in line do método adicionar SubEvento.
     *
     * @param e;
     * @throws ErroInternoException
     * @throws SubEventoExistenteException
     * @throws DatasIncorretas
     */
    public void adicionarSubEvento(SubEvento e) throws ErroInternoException, SubEventoExistenteException, DatasIncorretas {
        this.subEvento.adicionar(e);
    }

    /**
     * Assinatura in line do método atualizar vagas de SubEvento.
     *
     * @param vagas;
     * @param ev;
     * @throws ErroInternoException
     * @throws SubEventoInexistenteException
     */
    public void atualizarVagasSubEvento(int vagas, SubEvento ev) throws ErroInternoException, SubEventoInexistenteException {

        this.subEvento.atualizarVagasSubEvento(vagas, ev);

    }

    /**
     * Assinatura in line do método listar SubEvento.
     *
     * @return List;
     * @throws ErroInternoException
     * @throws SubEventoInexistenteException
     */
    public List<SubEvento> listarTudoSubEvento() throws ErroInternoException, SubEventoInexistenteException {
        return this.subEvento.listarTudoSubEvento();
    }

    /**
     * Assinatura in line do método atualizar SubEvento.
     *
     * @param e;
     * @throws ErroInternoException
     * @throws SubEventoInexistenteException
     */
    public void atualizarSubEvento(SubEvento e) throws ErroInternoException, SubEventoInexistenteException, EventoInexistenteException, ParticipanteInexistenteException, VagasIncorretasException {
        this.subEvento.atualizar(e);
    }

    /**
     * Assinatura in line do método buscar SubEvento.
     *
     * @param evento;
     * @return List.
     * @throws ErroInternoException
     * @throws SubEventoInexistenteException
     */
    public List<SubEvento> buscarListSubEvento(Evento evento) throws ErroInternoException, SubEventoInexistenteException {
        return this.subEvento.buscarListSubEvento(evento);
    }

    /**
     * Assinatura in line do método buscar SubEvento, através do código.
     *
     * @param codigo;
     * @return SubEvento.
     * @throws eacad.exceptions.ErroInternoException
     * @throws eacad.exceptions.SubEventoInexistenteException
     */
    public SubEvento buscarCodigoSubEvento(long codigo) throws ErroInternoException, SubEventoInexistenteException {
        return this.subEvento.buscarCodigo(codigo);
    }

    /**
     * Assinatura in line do método buscar SubEvento, através do nome.
     *
     * @param nome;
     * @return SubEvento.
     * @throws ErroInternoException
     * @throws SubEventoInexistenteException
     */
    public SubEvento buscarNomeSubEvento(String nome) throws ErroInternoException, SubEventoInexistenteException {
        return this.subEvento.buscarNomeSubEvento(nome);
    }

    /**
     * Assinatura in line do método remover SubEvento.
     *
     * @param codigo;
     * @throws ErroInternoException
     * @throws SubEventoInexistenteException
     * @throws ParticipanteInexistenteException
     * @throws EventoInexistenteException
     */
    public void removerSubEvento(long codigo) throws ErroInternoException, SubEventoInexistenteException, ParticipanteInexistenteException, EventoInexistenteException {
        this.subEvento.remover(codigo);
    }

    //---------------------------Participante---------------------------
    /**
     * Assinatura in line do método buscar adicionar Participante.
     *
     * @param e;
     * @throws ErroInternoException
     * @throws ParticipanteExistenteException
     * @throws ParticipanteInexistenteException
     */
    public void adicionarParticipante(Participante e) throws ErroInternoException, ParticipanteExistenteException, ParticipanteInexistenteException {
        this.participante.adicionar(e);
    }

    /**
     * Assinatura in line do método buscar Participante.
     *
     * @param cpf;
     * @return Participante.
     * @throws ErroInternoException
     * @throws ParticipanteInexistenteException
     */
    public Participante buscarParticipante(String cpf) throws ErroInternoException, ParticipanteInexistenteException {
        return this.participante.buscar(cpf);
    }

    /**
     * Assinatura in line do método listar Participantes de um Evento.
     *
     * @param e;
     * @return List;
     * @throws ErroInternoException
     * @throws ParticipanteInexistenteException
     */
    public List<Participante> listarTudoEventoParticipante(Evento e) throws ErroInternoException, ParticipanteInexistenteException {
        return this.participante.listarTudoEventoParticipante(e);
    }

    /**
     * Assinatura in line do método listar Participantes de um SubEvento.
     *
     * @param e;
     * @return List.
     * @throws ErroInternoException
     * @throws ParticipanteInexistenteException
     */
    public List<Participante> listarTudoSubEventoParticipante(SubEvento e) throws ErroInternoException, ParticipanteInexistenteException {
        return this.participante.listarTudoSubEventoParticipante(e);
    }

    /**
     * Assinatura in line do método atualizar Participante.
     *
     * @param participante;
     * @throws eacad.exceptions.ErroInternoException
     * @throws eacad.exceptions.ParticipanteInexistenteException
     */
    public void atualizarParticipante(Participante participante) throws ErroInternoException, ParticipanteInexistenteException {
        this.participante.atualizar(participante);
    }

    /**
     * Assinatura in line para remover Participante.
     *
     * @param codigo;
     * @throws ErroInternoException
     * @throws ParticipanteInexistenteException
     * @throws SubEventoInexistenteException
     * @throws EventoInexistenteException
     */
    public void removerParticipante(long codigo) throws ErroInternoException, ParticipanteInexistenteException, SubEventoInexistenteException, EventoInexistenteException {
        this.participante.remover(codigo);
    }

    /**
     * Assiantura in line para buscar Participante pelo código.
     *
     * @param codigo;
     * @return Participante.
     * @throws ErroInternoException
     * @throws ParticipanteInexistenteException
     */
    public Participante buscarCodigo(long codigo) throws ErroInternoException, ParticipanteInexistenteException {

        return this.participante.buscarCodigo(codigo);

    }

}
