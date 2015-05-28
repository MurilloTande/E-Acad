/** Classe para objetos do tipo Usuario, onde serão contidos, atributos e métodos para o mesmo.<p/>
 * 
 *  @author Murillo Tande
 *  @author Matheus Barbosa
 *  @author Hugo Calado
 *  @author Felipe Xavier
 */
package eacad.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * "@Entity" é usada para informar que a classe Usuario é uma entidade no JPA.
 */
@Entity
public class Usuario implements Serializable{
    
    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private List<Participante> participantes;
    private List<Evento> eventosDoUsuario;
 
    /**
     * @param cpf
     * @param nome
     * @param senha
     * @param email
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
     * @return long - Retorna codigo do Usuario.
     * "@Id" e "@GeneratedValue" indicam respectivamente que o atributo é a chave principal do usuario e nele o valor é gerado automaticamente. 
     */
    @Id
    @Column (unique = true, nullable = false)
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return String - Retorna o nome do usuário.
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     * @return String - Retorna o E-mail do usuário.
     */
    @Column (unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String - Retorna a senha do usuário.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    /**
     * @return List - Retorna uma lista de eventos do usuário.<p/>
     * "@OneToMany" indica o mapeamento de um para muitos no JPA
     */
    @OneToMany(mappedBy = "criador")
    public List<Evento> getEventosDoUsuario() {
        return this.eventosDoUsuario;
    }

    /**
     * @param eventoDoUsuario
     */
    public void setEventosDoUsuario(List<Evento> eventoDoUsuario) {  
        this.eventosDoUsuario = eventoDoUsuario;
    }
    
}
