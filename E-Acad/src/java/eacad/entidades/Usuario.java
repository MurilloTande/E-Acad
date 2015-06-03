/**
 * Classe para objetos do tipo Usuario, onde serão contidos, atributos e métodos
 * para o mesmo.<p/>
 *
 * @author Murillo Tande
 * @author Matheus Barbosa
 * @author Hugo Calado
 * @author Felipe Xavier
 */
package eacad.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario implements Serializable {

    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private List<Participante> participantes;
    private List<Evento> eventosDoUsuario;

    /**
     * @param cpf;
     * @param nome;
     * @param senha;
     * @param email;
     */
    public Usuario(String cpf, String nome, String email, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    /**
     * Construtor vazio.
     */
    public Usuario() {
    }

    /**
     * @return long.
     */
    @Id
    @Column(unique = true, nullable = false)
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf;
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return String.
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome;
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return String.
     */
    @Column(unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    /**
     * @param email;
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha;
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return List.
     */
    @OneToMany(mappedBy = "criador")
    public List<Evento> getEventosDoUsuario() {
        return this.eventosDoUsuario;
    }

    /**
     * @param eventoDoUsuario;
     */
    public void setEventosDoUsuario(List<Evento> eventoDoUsuario) {
        this.eventosDoUsuario = eventoDoUsuario;
    }

}
