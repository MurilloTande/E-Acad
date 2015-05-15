/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eacad.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
public class SubEvento implements Serializable{
    
    private long codigo;
    private String nome;
    private String descricao;
    private String apresentador;
    private String tipo;
    private Date data_inicio;
    private Date data_final;
    private int total_vagas;
    private Evento eventoPai;
    
    public SubEvento() {
    }

    public SubEvento(long codigo, String nome, String descricao, String apresentador, String tipo, Date data_inicio, Date data_final, int total_vagas) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.apresentador = apresentador;
        this.tipo = tipo;
        this.data_inicio = data_inicio;
        this.data_final = data_final;
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

    public String getApresentador() {
        return apresentador;
    }

    public void setApresentador(String apresentador) {
        this.apresentador = apresentador;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public int getTotal_vagas() {
        return total_vagas;
    }

    public void setTotal_vagas(int total_vagas) {
        this.total_vagas = total_vagas;
    }
   
    @ManyToOne
    public Evento getEventoPai() {
        return eventoPai;
    }

    public void setEventoPai(Evento eventoPai) {
        this.eventoPai = eventoPai;
    } 
    
}
