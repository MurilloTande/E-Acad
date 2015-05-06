/**
 * @author Tande
 */
package eacad.controller;

import eacad.entidades.Usuario;
import eacad.exceptions.ErroInternoException;
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
    private String login;
    private String senha;
    private String novaSenha;
    private static Usuario usuarioLogado;

    public BeanUsuario() {
        this.usuario = new Usuario();
    }

    public static Usuario getInstancia() {
        return BeanUsuario.usuarioLogado;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNovaSenha() {
        return this.novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        BeanUsuario.usuarioLogado = usuarioLogado;
    }
    
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

    public boolean isLogado() {
        return usuarioLogado != null;
    }

    public String dislog() {
        usuarioLogado = null;
        return "PaginaInicial.xhtml";
    }

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
        }
        return "";/*Aqui vai ficar a pagina inicial*/
    }
}
