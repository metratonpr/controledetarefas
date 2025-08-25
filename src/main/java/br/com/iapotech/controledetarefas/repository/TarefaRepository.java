package br.com.iapotech.controledetarefas.repository;

import br.com.iapotech.controledetarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> { }
