package br.com.iapotech.controledetarefas.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tarefas")
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome da Tarefa é obrigatório")
    @Column(nullable = false, length = 200)
    private String descricao;

    @NotNull(message = "Data de Entrega é obrigatória")
    @Column(nullable = false)
    private LocalDate dataEntrega;

    @NotBlank(message = "Responsável é obrigatório")
    @Column(nullable = false,length = 200)
    private String responsavel;
}
