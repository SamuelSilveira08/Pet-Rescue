package br.com.petrescue.api.domain;

import br.com.petrescue.api.domain.subClasses.Localizacao;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double saldo;

    private String email;

    private String nome;

    private String foto;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "latitude", column = @Column(name = "latitude")),
            @AttributeOverride( name = "longitude", column = @Column(name = "longitude"))
    })
    private Localizacao localizacao;
}