/**
 * Classe de controlador, Usuário, onde serão contidos os métodos da camada
 * de controller para conexão com a view.<p/>
 *
 * @author Murillo Tande
 * @author Matheus Barbosa
 * @author Hugo Calado
 * @author Felipe Xavier
 */
package eacad.controller;

import eacad.entidades.Usuario;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.EventoInexistenteException;
import eacad.exceptions.ParticipanteInexistenteException;
import eacad.exceptions.SubEventoInexistenteException;
import eacad.exceptions.UsuarioExistenteException;
import eacad.exceptions.UsuarioInexistenteException;
import eacad.fachada.FachadaSistema;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class BeanUsuario implements Serializable {

    @EJB
    private FachadaSistema fachada;
    
    private Usuario usuario;
    private Usuario esqueciSenhaUsuario;
    private String login;
    private String senha;
    private String novaSenha;
    private static Usuario usuarioLogado;
    
    
    public BeanUsuario() {
        this.usuario = new Usuario();
    }

    /**
     * @return Usuário.
     */
    public static Usuario getInstancia() {
        return BeanUsuario.usuarioLogado;
    }

    /**
     * @return Usuário.
     */
    public Usuario getUsuario() {
        return this.usuario;
    }

    /**
     * @param usuario;
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return Usuário.
     */
    public Usuario getEsqueciSenhaUsuario() {
        return esqueciSenhaUsuario;
    }

    /**
     * @param esqueciSenhaUsuario;
     */
    public void setEsqueciSenhaUsuario(Usuario esqueciSenhaUsuario) {
        this.esqueciSenhaUsuario = esqueciSenhaUsuario;
    }
   
    /**
     * @return String.
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * @param login;
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return String.
     */
    public String getSenha() {
        return this.senha;
    }

    /**
     * @param senha;
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return String.
     */
    public String getNovaSenha() {
        return this.novaSenha;
    }

    /**
     * @param novaSenha;
     */
    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    /**
     * @return Usuário.
     */
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    /**
     * @param usuarioLogado;
     */
    public void setUsuarioLogado(Usuario usuarioLogado) {
        BeanUsuario.usuarioLogado = usuarioLogado;
    }
    
    /**
     * Método para cadastrar Usuário no sistema.
     * @return Strign.
     * @throws ErroInternoException
     * @throws UsuarioExistenteException
     * @throws UsuarioInexistenteException
     */
    public String cadastrarUsuario() throws ErroInternoException, UsuarioExistenteException, UsuarioInexistenteException {
        try {
            this.fachada.adicionarUsuario(usuario);
            usuario = new Usuario();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cadastro efetuado com sucesso!"));
        } catch (ErroInternoException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Ocorreu um erro no sistema. Tente novamente." + e.getMessage()));
            return null;
        } catch (UsuarioExistenteException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Usuario Existente no sistema."));
            return null;
        }
        return "PaginaInicial.xhtml"/*Falta criar home page*/;

    }

    /**
     * Método para logar no sistema.
     * @return String.
     */
    public String logar() {
        
        
        try {
            Usuario u = this.fachada.buscarEmail(this.login);
            
            if (this.senha.equals(u.getSenha())) {
                usuarioLogado = u;
                return "PaginaInicial.xhtml"/*falta*/;
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Senha inválida!"));
                return null;
            }
        } catch (UsuarioInexistenteException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Usuario Inexistente"));
            return null;
        } catch (ErroInternoException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Ocorreu um erro no sistema. Tente novamente." + e.getMessage()));
            return null;

        }

    }
    
    /**
     * Método de "Esqueci minha senha"
     * @return String.
     */
    public String esqueciSenha1() {  
        try {
            Usuario u = this.fachada.buscarEmail(this.login);  
              esqueciSenhaUsuario = u;   
                
                return "esqueciSenha.xhtml";
                
        } catch (UsuarioInexistenteException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Usuario Inexistente"));
            return null;
        } catch (ErroInternoException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Ocorreu um erro no sistema. Tente novamente." + e.getMessage()));
            return null;
        }
    }
    
    /**
     * Método de "Esqueci minha senha".
     * @return String.
     */
     public String esqueciSenha2() {
            try {
                esqueciSenhaUsuario.setSenha(novaSenha);
                this.fachada.atualizar(esqueciSenhaUsuario);
                esqueciSenhaUsuario = new Usuario();

                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Senha alterada com sucesso!"));
            } catch (UsuarioInexistenteException | ErroInternoException u) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Ocorreu um erro no sistema. Tente novamente." + u.getMessage()));
            }
            
           return "PaginaInicial.xhtml";
    }

     /**
      * Método para permanecer logado no sistema.
     * @return boolean;
     */
    public boolean isLogado() {
        return usuarioLogado != null;
    }

    /**
     * Método para Deslogar.
     * @return String.
     */
    public String dislog() {
        usuarioLogado = null;
        return "PaginaInicial.xhtml";
    }

    /**
     * Método para atualizar Usuário no sistema.
     * @return String
     * @throws ErroInternoException
     * @throws UsuarioInexistenteException
     */
    public String atualizarUsuario() throws ErroInternoException, UsuarioInexistenteException {
        try {
            this.fachada.atualizar(usuarioLogado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cadastro Atualizado com sucesso!"));
        } catch (UsuarioInexistenteException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Usuario Inexistente no sistema."));
            return null;

        } catch (ErroInternoException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Ocorreu um erro no sistema. Tente novamente." + e.getMessage()));
            return null;
        }
        return null;

    }

    /**
     * Método para alterar senha de Usuário no sistema.
     */
    public void alterarSenha() {
        if (usuarioLogado.getSenha().equals(senha)) {
            usuarioLogado.setSenha(novaSenha);
            try {
                this.atualizarUsuario();
            } catch (UsuarioInexistenteException | ErroInternoException u) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Ocorreu um erro no sistema. Tente novamente." + u.getMessage()));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Senha inválida!"));

        }

    }

    /**
     * Método para remover Usuário do sistema.
     * @return String.
     */
    public String removerUsuario() {

        try {
            this.fachada.remover(usuarioLogado.getCpf());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Registro excluído!"));
        } catch (ErroInternoException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Ocorreu um erro no sistema. Tente novamente." + ex.getMessage()));
        } catch (UsuarioInexistenteException ex1) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Ocorreu um erro no sistema. Tente novamente." + ex1.getMessage()));
            usuarioLogado = null;
        }catch ( EventoInexistenteException ex2) {
           FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Evento inexistente!" + ex2.getMessage()));
        }catch ( SubEventoInexistenteException ex3) {
           FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("SubEvento inexistente!" + ex3.getMessage()));
        }catch ( ParticipanteInexistenteException ex4) {
           FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Participante inexistente!" + ex4.getMessage()));
        }
        
        
        return "PaginaInicial";
    }

}
