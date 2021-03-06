package com.query.query.mensageria;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projetogerenciamentocurso.gerenciamentocurso.dto.AlunoDto;
import com.projetogerenciamentocurso.gerenciamentocurso.dto.DisciplinaDto;
import com.projetogerenciamentocurso.gerenciamentocurso.dto.TurmaDto;
import com.query.query.exception.TurmaException;
import com.query.query.model.Aluno;
import com.query.query.model.Disciplina;
import com.query.query.model.Turma;
import com.query.query.repository.TurmaRepository;

@Component
public class TurmaReceiver {
	
	@Autowired
	public TurmaRepository turmaRepository;

	@RabbitListener(queues = "turmaCriar")
	public void inserirTurma(TurmaDto turmaDto) {

		turmaRepository.save(criarTurmaModel(turmaDto));

	}

	@RabbitListener(queues = "turmaAtualizar")
	public void atualizarTurma(TurmaDto turmaDto) {
		try {
			 turmaRepository.getOne(turmaDto.getIdTurma());
			
			turmaRepository.save(criarTurmaModel(turmaDto));
		}catch (EntityNotFoundException  e) {
			throw new TurmaException("Turma não encontrada");
		}
	}

	@RabbitListener(queues = "turmaDeletar")
	public void deletarTurma(TurmaDto turmaDto) {
		
		try {
			Turma turmaModel = turmaRepository.getOne(turmaDto.getIdTurma());
			
			turmaRepository.delete(turmaModel);
		}catch (EntityNotFoundException e) {
			throw new TurmaException("Turma não encontrada");
		}
	}
	
	public Turma criarTurmaModel(TurmaDto turmaDto) {
		
		Turma turmaModel = new Turma();
		
		turmaModel.setAlunos(criaListaAluno(turmaDto.getAlunos()));
		turmaModel.setAnoLetivo(turmaDto.getAnoLetivo());
		turmaModel.setDescricao(turmaDto.getDescricao());
		turmaModel.setNumeroVagas(turmaDto.getNumeroVagas());
		turmaModel.setPeriodoLetivo(turmaDto.getPeriodoLetivo());
		turmaModel.setDisciplinas(criaListaDisciplina(turmaDto.getDisciplinas()));
		turmaModel.setAlunos(criaListaAluno(turmaDto.getAlunos()));
		turmaModel.setIdTurma(turmaDto.getIdTurma());
		return turmaModel;
		
	}
	
	private Set<Aluno> criaListaAluno(Set<AlunoDto> alunosDto){
		
		Set<Aluno> alunos = new HashSet<>();
		
		if(alunosDto!= null && !alunosDto.isEmpty()) {
			
			
			for(AlunoDto alunoDto : alunosDto){
				
				Aluno aluno = new Aluno();
				aluno.setCpf(alunoDto.getCpf());
				aluno.setEmail(alunoDto.getEmail());
				aluno.setFormaIngresso(alunoDto.getFormaIngresso());
				aluno.setNome(alunoDto.getNome());
				aluno.setIdPessoa(alunoDto.getIdPessoa());
				aluno.setMatricula(alunoDto.getMatricula());
				
				alunos.add(aluno);
			}
			return alunos;
		}
		
		return alunos;
		
	}
	
	private Set<Disciplina> criaListaDisciplina(Set<DisciplinaDto> disciplinasDto){
		
		Set<Disciplina> disciplinas = new HashSet<>();
		
		if(disciplinasDto!= null && !disciplinasDto.isEmpty()) {
			
			for(DisciplinaDto disciplinaDto: disciplinasDto){
				
				Disciplina disciplina = new Disciplina();
				disciplina.setDescricao(disciplinaDto.getDescricao());
				disciplina.setCargaHoraria(disciplinaDto.getCargaHoraria());
				disciplina.setSigla(disciplinaDto.getSigla());
				disciplina.setIdDisciplina(disciplinaDto.getIdDisciplina());
				
				disciplinas.add(disciplina);
			}
			return disciplinas;
		}
		
		return disciplinas;
		
	}
}
