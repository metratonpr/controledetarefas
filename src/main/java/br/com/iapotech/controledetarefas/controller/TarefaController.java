package br.com.iapotech.controledetarefas.controller;

import br.com.iapotech.controledetarefas.model.Tarefa;
import br.com.iapotech.controledetarefas.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
@CrossOrigin
@RequiredArgsConstructor
@Validated
public class TarefaController {

    private final TarefaRepository tarefaRepository;

    @PostMapping
    public ResponseEntity<Tarefa> criar(@Valid @RequestBody Tarefa novaTarefa){
        // Lógica para salvar a nova tarefa no banco de dados
        Tarefa tarefaSalva = tarefaRepository.save(novaTarefa);
        return ResponseEntity.created(URI.create("/api/tarefas/" + tarefaSalva.getId())).body(tarefaSalva);
    }

    @GetMapping
    public List<Tarefa> listar(){
        return tarefaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Tarefa buscarPorId(@PathVariable Long id){
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));
    }

    @PutMapping("/{id}")
    public Tarefa atualizar(@PathVariable Long id, @Valid @RequestBody Tarefa atualizada) {
        Tarefa existente = tarefaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa " + id + " não encontrada"));

        existente.setDescricao(atualizada.getDescricao());
        existente.setDataEntrega(atualizada.getDataEntrega());
        existente.setResponsavel(atualizada.getResponsavel());

        return tarefaRepository.save(existente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable Long id) {
        Tarefa existente = tarefaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa " + id + " não encontrada"));

        tarefaRepository.delete(existente);
        return ResponseEntity.ok("Registro excluído com sucesso");
    }

}