
package eacad.fachada;

import eacad.entidades.Usuario;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.UsuarioExistenteException;
import eacad.exceptions.UsuarioInexistenteException;
import eacad.negocio.CadastroUsuario;
import java.util.List;
import javax.ejb.EJB;

/**
 * @author Tande
 */

public class FachadaSistema {
    
    @EJB
    private CadastroUsuario usuarios;
    
    
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

    public Usuario buscar(String email) throws ErroInternoException, UsuarioInexistenteException {
        return this.usuarios.buscar(email);
    }
    
    public void remover(String email) throws ErroInternoException, UsuarioInexistenteException{
        this.usuarios.remover(email);
    }
    
}
