package br.com.petrescue.api.controller.dto;

import br.com.petrescue.api.domain.Animal;
import br.com.petrescue.api.domain.enums.Sexo;
import br.com.petrescue.api.domain.enums.SituacaoAdocao;
import br.com.petrescue.api.domain.enums.TipoAnimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDTO {

    private Integer id;
    private String nome;
    private SituacaoAdocao situacaoAdocao;
    private String foto;
    private TipoAnimal tipoAnimal;
    private Boolean castrado;
    private String raca;
    private Sexo sexo;
    private Integer dataNascimento;
    private String descricao;
    private String vacinas;
    private Integer idUsuario;
    private String nomeUsuario;

    public AnimalDTO(Animal animal) {
        this.id = animal.getId();
        this.nome = animal.getNome();
        this.situacaoAdocao = animal.getSituacaoAdocao();
        this.foto = animal.getFoto();
        this.tipoAnimal = animal.getTipoAnimal();
        this.castrado = animal.getCastrado();
        this.raca = animal.getRaca();
        this.sexo = animal.getSexo();
        this.dataNascimento = animal.getDataNascimento();
        this.descricao = animal.getDescricao();
        this.vacinas = animal.getVacinas();
        this.idUsuario = animal.getUsuario().getId();
        this.nomeUsuario = animal.getUsuario().getNome();
    }
}
