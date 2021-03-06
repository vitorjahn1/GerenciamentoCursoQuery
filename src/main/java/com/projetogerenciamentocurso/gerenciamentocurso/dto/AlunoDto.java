package com.projetogerenciamentocurso.gerenciamentocurso.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@EqualsAndHashCode(callSuper=false)
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NoArgsConstructor
public class AlunoDto extends PessoaDto implements Serializable{

	private static final long serialVersionUID = -2740689589450511134L;

	private Integer matricula;

	private String formaIngresso;

}
