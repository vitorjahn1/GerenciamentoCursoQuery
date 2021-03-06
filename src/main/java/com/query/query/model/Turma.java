package com.query.query.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Turma implements Serializable {

	private static final long serialVersionUID = 1163091750281931L;
	@Id
	private Integer idTurma;
	private String anoLetivo;
	private String descricao;
	private Integer numeroVagas;
	private Integer periodoLetivo;
	@OneToMany
	private Set<Disciplina> disciplinas = new HashSet<>();
	@OneToMany
	@JsonIgnore
	private Set<Aluno> alunos = new HashSet<>();
}
