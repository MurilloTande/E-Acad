/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eacad.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Evento implements Serializable{
    
    private long codigo;
    private String nome;
    private String descricao;
    private Date data_inicio;
    private Date data_final;
    private String localidade;
    private String endereco;
    private String cidade;
    private String estado;
    private int total_vagas;
    private Usuario criador;
    private List<Participante> participantes;
    private List<SubEvento> subEventos;
    


    public Evento() {
    }

    public Evento(long codigo, String nome, String descricao, Date data_inicio, Date data_final, String localidade, String endereco, String cidade, String estado, int total_vagas) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.data_inicio = data_inicio;
        this.data_final = data_final;
        this.localidade = localidade;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.total_vagas = total_vagas;  
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }
    
    
    @Column (unique = true, nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Temporal(TemporalType.DATE)
    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    @Temporal(TemporalType.DATE)
    public Date getData_final() {
        return data_final;
    }

    public void setData_final(Date data_final) {
        this.data_final = data_final;
    }
    
    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getTotal_vagas() {
        return total_vagas;
    }

    public void setTotal_vagas(int total_vagas) {
        this.total_vagas = total_vagas;
    }
    
    @ManyToOne
    public Usuario getCriador(){
        return this.criador;
    }
    
    public void setCriador(Usuario criador){
    this.criador = criador;
    }
    
    @OneToMany(mappedBy = "eventoPai")
    public List<SubEvento> getSubEventos() {
        return subEventos;
    }

    public void setSubEventos(List<SubEvento> subEventos) {
        this.subEventos = subEventos;
    }

   
    @OneToMany(mappedBy = "evento")
    @ManyToMany
    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (int) (this.codigo ^ (this.codigo >>> 32));
        hash = 79 * hash + Objects.hashCode(this.nome);
        hash = 79 * hash + Objects.hashCode(this.descricao);
        hash = 79 * hash + Objects.hashCode(this.data_inicio);
        hash = 79 * hash + Objects.hashCode(this.data_final);
        hash = 79 * hash + Objects.hashCode(this.localidade);
        hash = 79 * hash + Objects.hashCode(this.endereco);
        hash = 79 * hash + Objects.hashCode(this.cidade);
        hash = 79 * hash + Objects.hashCode(this.estado);
        hash = 79 * hash + this.total_vagas;
       
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evento other = (Evento) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.data_inicio, other.data_inicio)) {
            return false;
        }
        if (!Objects.equals(this.data_final, other.data_final)) {
            return false;
        }
        if (!Objects.equals(this.localidade, other.localidade)) {
            return false;
        }
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        if (!Objects.equals(this.cidade, other.cidade)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (this.total_vagas != other.total_vagas) {
            return false;
        }
        
        return true;
    }

 
    
    
    
}
