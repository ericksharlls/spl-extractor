package br.ufrn.ct.cronos.entity;

import java.io.Serializable;
import java.util.Date;


public class EntidadeRelatorioTurmaDepartamento implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer dia;
	private String turno;
	private Date dataReserva;
	private Long idSala;
	private Long idPeriodo;
	private Long idHorarioSala;
	private Long idTurma;
	
	public EntidadeRelatorioTurmaDepartamento(){
		super();
	}
	
	public EntidadeRelatorioTurmaDepartamento(Integer dia, String turno, Date dataReserva, Long idSala, Long idPeriodo, Long idHorarioSala, Long idTurma) {
		this.dia = dia;
		this.turno = turno;
		this.dataReserva = dataReserva;
		this.idSala = idSala;
		this.idPeriodo = idPeriodo;
		this.idHorarioSala = idHorarioSala;
		this.idTurma = idTurma;
	}
	
	public Integer getDia() {
		return dia;
	}
	
	public void setDia(Integer dia) {
		this.dia = dia;
	}
	
	public String getTurno() {
		return turno;
	}
	
	public void setTurno(String turno) {
		this.turno = turno;
	}
	
	public Date getDataReserva() {
		return dataReserva;
	}
	
	public void setDataReserva(Date dataReserva) {
		this.dataReserva = dataReserva;
	}
	
	public Long getIdSala() {
		return idSala;
	}
	
	public void setIdSala(Long idSala) {
		this.idSala = idSala;
	}
	
	public Long getIdPeriodo() {
		return idPeriodo;
	}
	
	public void setIdPeriodo(Long idPeriodo) {
		this.idPeriodo = idPeriodo;
	}
	
	public Long getIdHorarioSala() {
		return idHorarioSala;
	}
	
	public void setIdHorarioSala(Long idHorarioSala) {
		this.idHorarioSala = idHorarioSala;
	}
	
	public Long getIdTurma() {
		return idTurma;
	}
	
	public void setIdTurma(Long idTurma) {
		this.idTurma = idTurma;
	}
	
}
