
package eacad.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Participante implements Serializable{
    
    private String cpf;
    private String primeiroNome;
    private String sobreNome;
    private String email;
    private Evento evento;

    public Participante(String cpf, String primeiroNome, String sobreNome, String email) {
        this.cpf = cpf;
        this.primeiroNome = primeiroNome;
        this.sobreNome = sobreNome;
        this.email = email;
    }

    public Participante() {
    }

    @Id
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManyToOne
    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    
   
}
