package br.com.dsc.library.repository;

import br.com.dsc.library.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("select u from Usuario u where lower(u.nome) like lower(concat('%', :nome, '%'))")
    List<Usuario> findByNomeContaining(@Param("nome") String nome);
}
