
package eacad.fachada;

import eacad.entidades.Evento;
import eacad.entidades.Usuario;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.EventoExistenteException;
import eacad.exceptions.EventoInexistenteException;
import eacad.exceptions.UsuarioExistenteException;
import eacad.exceptions.UsuarioInexistenteException;
import eacad.negocio.CadastroEvento;
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
    
    //EVENTO
     public void adicionar(Evento e) throws ErroInternoException, EventoExistenteException{
     this.evento.adicionar(e);
     }
     
    public List<Evento> listarTudoEvento() throws ErroInternoException, EventoExistenteException{
    return this.evento.listarTudoEvento();
    }
    
    public void atualizar(Evento e) throws ErroInternoException, EventoInexistenteException{
    this.evento.atualizar(e);
    }
    
    public List<Evento> buscarNomeListEvento(String nome) throws ErroInternoException, EventoInexistenteException{
    return this.buscarNomeListEvento(nome);
    }
    
    public Evento buscarCodigo(long codigo) throws ErroInternoException, EventoInexistenteException{
    return this.evento.buscarCodigo(codigo);
    }
    
    public Evento buscarNomeEvento(String nome) throws ErroInternoException, EventoInexistenteException{
    return this.buscarNomeEvento(nome);
    }
    
    public void remover(long codigo) throws ErroInternoException, EventoInexistenteException{
    this.evento.remover(codigo);
    }
    
}

