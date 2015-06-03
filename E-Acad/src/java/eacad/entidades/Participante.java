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
     * @return long - Código do Participante.
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
     * @return String - CPF do Participante.
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
     * @return String - Primeiro nome do Participante.
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
     * @return String - Sobrenome do Participante..
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
     * @return String - E-mail do Participante.
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
     * @return List - Lista de Eventos em que esse participante está..
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
     * @return String - Lista de SubEventos em que esse participante está.
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
