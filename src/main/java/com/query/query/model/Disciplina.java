package com.query.query.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NoArgsConstructor
public class Disciplina implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	private Integer idDisciplina;
	private String descricao;
	private String cargaHoraria;
	private String sigla;
	@ManyToOne
	private Professor professor;
}
