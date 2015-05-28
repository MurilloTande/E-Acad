
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
import eacad.negocio.CadastroEvento;
import eacad.negocio.CadastroParticipante;
import eacad.negocio.CadastroSubEvento;
import eacad.negocio.CadastroUsuario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author Tande
 */

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
    public void adicionarUsuario(Usuario usuario) throws ErroInternoException, UsuarioExistenteException, UsuarioInexistenteException {
    this.usuarios.adicionarUsuario(usuario);
    }

    public List<Usuario> listar() throws ErroInternoException {
    return this.usuarios.listar();
    }

    public void atualizar(Usuario usuario) throws ErroInternoException, UsuarioInexistenteException {
    this.usuarios.Atualizar(usuario);
    }

    public List<Usuario> buscarNome(String nome) throws ErroInternoException, UsuarioInexistenteException {
    return this.usuarios.buscarNome(nome);
    }

    public Usuario buscar(String cpf) throws ErroInternoException, UsuarioInexistenteException {
    return this.usuarios.buscar(cpf);
    }
    
    public Usuario buscarEmail(String email) throws ErroInternoException, UsuarioInexistenteException {
    return this.usuarios.buscarEmail(email);
    }
    
    public void remover(String cpf) throws ErroInternoException, UsuarioInexistenteException{
    this.usuarios.remover(cpf);
    }
    
    //---------------------------Evento---------------------------
    public void adicionarEvento(Evento e) throws ErroInternoException, EventoExistenteException, DatasIncorretas{
    this.evento.adicionar(e);
    }
     
    public List<Evento> listarTudoEvento() throws ErroInternoException, EventoInexistenteException{
    return this.evento.listarTudoEvento();
    }
    
    public List<Evento> EventosUsuario(String cpf) throws ErroInternoException, EventoInexistenteException{
    return this.evento.EventosUsuario(cpf);
    }
    
    public void atualizarEvento(Evento e) throws ErroInternoException, EventoInexistenteException{
    this.evento.atualizar(e);
    }
    
    public List<Evento> buscarNomeListEvento(String nome) throws ErroInternoException, EventoInexistenteException{
    return this.buscarNomeListEvento(nome);
    }
    
    public Evento buscarCodigoEvento(long codigo) throws ErroInternoException, EventoInexistenteException{
    return this.evento.buscarCodigo(codigo);
    }
    
    public Evento buscarNomeEvento(String nome) throws ErroInternoException, EventoInexistenteException{
    return this.buscarNomeEvento(nome);
    }
    
    public void removerEvento(long codigo) throws ErroInternoException, EventoInexistenteException{
    this.evento.remover(codigo);
    }
    
    //---------------------------SubEvento---------------------------
    public void adicionarSubEvento(SubEvento e) throws ErroInternoException, SubEventoExistenteException, DatasIncorretas{
    this.subEvento.adicionar(e);
    }
     
    public List<SubEvento> listarTudoSubEvento() throws ErroInternoException, SubEventoInexistenteException{
    return this.subEvento.listarTudoSubEvento();
    }
    
    public void atualizarSubEvento(SubEvento e) throws ErroInternoException, SubEventoInexistenteException{
    this.subEvento.atualizar(e);
    }
    
    public List<SubEvento> buscarListSubEvento(Evento evento) throws ErroInternoException, SubEventoInexistenteException{
    return this.subEvento.buscarListSubEvento(evento);
    }
    
    public SubEvento buscarCodigoSubEvento(long codigo) throws ErroInternoException, SubEventoInexistenteException{
    return this.subEvento.buscarCodigo(codigo);
    }
    
    public SubEvento buscarNomeSubEvento(String nome) throws ErroInternoException, SubEventoInexistenteException{
    return this.subEvento.buscarNomeSubEvento(nome);
    }
    
    public void removerSubEvento(long codigo) throws ErroInternoException, SubEventoInexistenteException{
    this.subEvento.remover(codigo);
    }
    
    //---------------------------Participante---------------------------
    public void adicionarParticipante(Participante e) throws ErroInternoException, ParticipanteExistenteException, ParticipanteInexistenteException{
    this.participante.adicionar(e);
    }
    
    public Participante buscarParticipante(String cpf) throws ErroInternoException, ParticipanteInexistenteException{
    return this.participante.buscar(cpf);
    } 
    
    public Participante buscarValidarPartipante(Evento e) throws ErroInternoException, ParticipanteExistenteException {
    return this.participante.buscarValidarPartipante(e);
    }
     
   
    
}

