package br.com.dsc.library.repository;

import br.com.dsc.library.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("select l from Livro l where l.quantidadeDisponivel > 0 order by l.titulo")
    List<Livro> findDisponiveis();

    @Query("select l from Livro l join l.categoria c where lower(c.nome) = lower(:nomeCategoria)")
    List<Livro> findByCategoriaNome(@Param("nomeCategoria") String nomeCategoria);

    @Query("select l from Livro l join l.autores a where lower(a.nome) = lower(:nomeAutor) order by l.anoPublicacao")
    List<Livro> findByAutorNome(@Param("nomeAutor") String nomeAutor);
}
