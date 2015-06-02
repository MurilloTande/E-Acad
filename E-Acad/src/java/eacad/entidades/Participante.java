/**
 * Classe para objetos do tipo Participante, onde serão contidos, atributos e
 * métodos para o mesmo.<p/>
 *
 * @author Murillo Tande
 * @author Matheus Barbosa
 * @author Hugo Calado
 * @author Felipe Xavier
 */
package eacad.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Participante implements Serializable {

    private long codigo;
    private String cpf;
    private String primeiroNome;
    private String sobreNome;
    private String email;
    private List<Evento> evento;
    private List<SubEvento> subEvento;

    /**
     * @param codigo;
     * @param cpf;
     * @param primeiroNome;
     * @param sobreNome;
     * @param email;
     */
    public Participante(long codigo, String cpf, String primeiroNome, String sobreNome, String email) {
        this.codigo = codigo;
        this.cpf = cpf;
        this.primeiroNome = primeiroNome;
        this.sobreNome = sobreNome;
        this.email = email;
    }

    /**
     * Construtor vazio.
     */
    public Participante() {
    }

    /**
     * @return long.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getCodigo() {
        return codigo;
    }

    /**
     * @param codigo;
     */
    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    /**
     * @return String - Retorna CPF do participante
     */
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
     * @return String - retorna o primeiro nome do participante.
     */
    public String getPrimeiroNome() {
        return primeiroNome;
    }

    /**
     * @param primeiroNome;
     */
    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    /**
     * @return String - Retorna o sobrenome do participante.
     */
    public String getSobreNome() {
        return sobreNome;
    }

    /**
     * @param sobreNome;
     */
    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    /**
     * @return String - Retorna o email do participante.
     */
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
     * @return List - Retorna o evento.
     */
    @ManyToMany
    public List<Evento> getEvento() {
        return evento;
    }

    /**
     * @param evento;
     */
    public void setEvento(List<Evento> evento) {
        this.evento = evento;
    }

    /**
     * @return String - Retorna os subEventos.
     */
    @ManyToMany
    public List<SubEvento> getSubEvento() {
        return subEvento;
    }

    /**
     * @param subEvento;
     */
    public void setSubEvento(List<SubEvento> subEvento) {
        this.subEvento = subEvento;
    }

}
