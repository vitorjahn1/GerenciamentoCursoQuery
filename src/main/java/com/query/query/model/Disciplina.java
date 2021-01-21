package com.query.query.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	@JsonBackReference
	private Turma turmas;
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "disciplina")
	private Set<Professor> professores = new HashSet<Professor>();

}