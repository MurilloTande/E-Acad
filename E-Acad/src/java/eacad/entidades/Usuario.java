/**
 * @author Tande
 */
package eacad.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario implements Serializable{
    
    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private List<Evento> eventosDoUsuario;
 
    public Usuario(String cpf, String nome, String email, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario() {
    }

    @Id
    @Column (unique = true, nullable = false)
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Column (unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    @OneToMany
    public List<Evento> getEventosDoUsuario() {
        return this.eventosDoUsuario;
    }

    public void setEventosDoUsuario(List<Evento> eventoDoUsuario) {
        this.eventosDoUsuario = eventoDoUsuario;
    }
    
}
