package br.com.petrescue.api.controller.dto;

import br.com.petrescue.api.domain.Usuario;
import br.com.petrescue.api.domain.enums.TipoUsuario;
import br.com.petrescue.api.domain.subClasses.Localizacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Integer id;
    private Double saldo;
    private String email;
    private String nome;
    private String foto;
    private Localizacao localizacao;

    private TipoUsuario tipoUsuario;

    private String nomeOng;
    private String cpfCnpj;
    private String descricao;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.saldo = usuario.getSaldo();
        this.email = usuario.getEmail();
        this.nome = usuario.getNome();
        this.foto = usuario.getFoto();
        this.localizacao = usuario.getLocalizacao();
        this.tipoUsuario = usuario.getTipoUsuario();
        this.nomeOng = usuario.getNomeOng();
        this.cpfCnpj = usuario.getCpfCnpj();
        this.descricao = usuario.getDescricao();
    }
}