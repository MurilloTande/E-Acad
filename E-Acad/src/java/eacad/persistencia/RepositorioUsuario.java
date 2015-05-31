/**
 * Interface Usuario, onde serão contidas as assinaturas dos metodos da camada
 * de persistência.<p/>
 *
 * @author Murillo Tande
 * @author Matheus Barbosa
 * @author Hugo Calado
 * @author Felipe Xavier
 */
package eacad.persistencia;

import eacad.entidades.Usuario;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.UsuarioInexistenteException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface onde serão contidas as assinaturas de todos os metodos da
 * persistencia.
 */
@Local
public interface RepositorioUsuario extends Serializable {

    /**
     * Assinatura do método adicionar evento.
     *
     * @param usuario - Adciona um usuário a base de dados.
     * @throws eacad.exceptions.ErroInternoException;
     */
    public void adicionar(Usuario usuario) throws ErroInternoException;

    /**
     * Assinatura do método listarTudoEvento.
     *
     * @return List - Retorna uma lista de usuarios.
     * @throws eacad.exceptions.ErroInternoException;
     */
    public List<Usuario> listar() throws ErroInternoException;

    /**
     * Assinatura do método de listar evento através do nome.
     *
     * @param usuario - Atualiza um usuário da base de dados.
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.UsuarioInexistenteException;
     */
    public void atualizar(Usuario usuario) throws ErroInternoException, UsuarioInexistenteException;

    /**
     * <h1>Assinatura do metodo buscarCodigo.</h1>
     *
     * @param nome - Lista Usuários através de um nome;
     * @return List - Retorna uma lista de usuario através do nome.
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.UsuarioInexistenteException;
     */
    public List<Usuario> buscarNome(String nome) throws ErroInternoException, UsuarioInexistenteException;

    /**
     * Assinatura do método de busca através do cpf.
     *
     * @param cpf - Busca um Usuário através de um CPF.
     * @return Usuario - retorna Usuario.
     * @throws ErroInternoException;
     * @throws UsuarioInexistenteException;
     */
    public Usuario buscar(String cpf) throws ErroInternoException, UsuarioInexistenteException;

    /**
     * Assinatura do método de busca através do e-mail.
     *
     * @param email - Busca um Usuário através do e-mail.
     * @return Usuario - retorna Usuario;
     * @throws ErroInternoException;
     * @throws UsuarioInexistenteException;
     */
    public Usuario buscarEmail(String email) throws ErroInternoException, UsuarioInexistenteException;

    /**
     * Assinatura do método para remover usuário.
     *
     * @param cpf - Remove um Usuário da base de dados.
     * @throws ErroInternoException;
     * @throws UsuarioInexistenteException;
     */
    public void remover(String cpf) throws ErroInternoException, UsuarioInexistenteException;
}
