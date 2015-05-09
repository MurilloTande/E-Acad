/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eacad.entidades;

import java.io.Serializable;
import java.util.Date;
import java.sql.Time;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Evento implements Serializable{
    
    private long codigo;
    private String nome;
    private String descricao;
    private Date data_inicio;
    private Date data_final;
    private Time hora_inicial;
    private Time hora_final;
    private String Local;
    private String endereco;
    private String cidade;
    private String estado;
    private int total_vagas;
    private List<Participantes> participante;
    private List<Evento> evento;

    public Evento() {
    }

    public Evento(long codigo, String nome, String descricao, Date data_inicio, Date data_final, Time hora_inicial, Time hora_final, String Local, String endereco, String cidade, String estado, int total_vagas, List<Participantes> participante, List<Evento> evento) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.data_inicio = data_inicio;
        this.data_final = data_final;
        this.hora_inicial = hora_inicial;
        this.hora_final = hora_final;
        this.Local = Local;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.total_vagas = total_vagas;
        this.participante = participante;
        this.evento = evento;
    }
    
    @Id
    @Column (unique = true, nullable = false)
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

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Date getData_final() {
        return data_final;
    }

    public void setData_final(Date data_final) {
        this.data_final = data_final;
    }

    public Time getHora_inicial() {
        return hora_inicial;
    }

    public void setHora_inicial(Time hora_inicial) {
        this.hora_inicial = hora_inicial;
    }

    public Time getHora_final() {
        return hora_final;
    }

    public void setHora_final(Time hora_final) {
        this.hora_final = hora_final;
    }

    public String getLocal() {
        return Local;
    }

    public void setLocal(String Local) {
        this.Local = Local;
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

    public List<Participantes> getParticipante() {
        return participante;
    }

    public void setParticipante(List<Participantes> participante) {
        this.participante = participante;
    }

    public List<Evento> getEvento() {
        return evento;
    }

    public void setEvento(List<Evento> evento) {
        this.evento = evento;
    }
    
    
    
}
