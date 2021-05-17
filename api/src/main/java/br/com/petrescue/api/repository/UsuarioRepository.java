package br.com.petrescue.api.repository;


import br.com.petrescue.api.domain.Usuario;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Integer> {

//    boolean existsByUsernameAndCargoNot(String usuario, Cargo cargo);
//    boolean existsById(Integer id);
//
//    List<Usuario> findByDataIngressoLessThanEqualOrDataIngressoGreaterThanEqualAndCargoNot(LocalDate dataAntes, LocalDate data, Cargo cargo);
//
//    List<Usuario> findByDataIngressoBetweenAndCargoNot(LocalDate dataAntes, LocalDate data, Cargo cargo);

    Optional<Usuario> findByEmailAndSenha(String email, String senha);
}
