package com.medico.app.web.models.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Entity
@Table(name="MEDICAMENTO")
public class Medicamento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "IDMEDICAMENTO")
	private Integer idmedicamento;
	
	@Column(name = "COMPONENTEACTIVO")
	@Size(max = 255)
	private String componenteActivo;
	
	@Column(name = "CONCENTRACION")
	@Size(max = 35)
	@NotEmpty
	private String concentracion;
	
	@Column(name = "NOMBRECOMERCIAL")
	@Size(max = 65)
	@NotEmpty
	private String nombreComercial;
	
	@Column(name = "PRECIO")
	@Digits(integer=8, fraction=2)
	@NotNull
	private float precio;
	
	@Column(name = "FECHACADUCIDAD")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private LocalDate fechaCaducidad;
	
	@Column(name = "CREADOPOR")
	private String creadoPor;
	
	@Column(name = "CREADOEN")
	private LocalDateTime creadoEn;
	
	@Column(name = "ACTUALIZADOPOR")
	private String actualizadoPor;
	
	@Column(name = "ACTUALIZADOPEN")
	private LocalDateTime actualizadoEn;
	
	@JoinColumn(name="IDVIAADMINISTRACION", referencedColumnName = "IDVIAADMINISTRACION")
	@ManyToOne
	private ViaAdministracion viaAdministracion;
	
	public Medicamento() {
		super();
	}
	
	public Medicamento(Integer idmedicamento) {
		super();
		this.idmedicamento = idmedicamento;
	}
	
	public Integer getIdmedicamento() {
		return idmedicamento;
	}
	
	public String getComponenteActivo() {
		return componenteActivo;
	}
	
	public void setComponenteActivo(String componenteActivo) {
		this.componenteActivo = componenteActivo;
	}
	
	public void setIdmedicamento(Integer idmedicamento) {
		this.idmedicamento = idmedicamento;
	}
	
	public ViaAdministracion getViaAdministracion() {
		return viaAdministracion;
	}
	
	public void setViaAdministracion(ViaAdministracion viaAdministracion) {
		this.viaAdministracion = viaAdministracion;
	}
	
	public String getNombreComercial() {
		return nombreComercial;
	}
	
	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}
	
	public float getPrecio() {
		return precio;
	}
	
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public String getConcentracion() {
		return concentracion;
	}
	
	public void setConcentracion(String concentracion) {
		this.concentracion = concentracion;
	}
	
	public LocalDate getFechaCaducidad() {
		return fechaCaducidad;
	}
	
	public void setFechaCaducidad(LocalDate fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	
	public String getCreadoPor() {
		return creadoPor;
	}
	
	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}
	
	public LocalDateTime getCreadoEn() {
		return creadoEn;
	}
	
	public void setCreadoEn(LocalDateTime creadoEn) {
		this.creadoEn = creadoEn;
	}
	
	public String getActualizadoPor() {
		return actualizadoPor;
	}
	
	public void setActualizadoPor(String actualizadoPor) {
		this.actualizadoPor = actualizadoPor;
	}
	
	public LocalDateTime getActualizadoEn() {
		return actualizadoEn;
	}
	
	public void setActualizadoEn(LocalDateTime actualizadoEn) {
		this.actualizadoEn = actualizadoEn;
	}
	
	@PrePersist
	public void prePersist() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		creadoPor = auth.getName();
		creadoEn = LocalDateTime.now();
	}
	
	@PreUpdate
	public void preUpdate() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		actualizadoPor = auth.getName();
		actualizadoEn = LocalDateTime.now();
	}
	
	
}