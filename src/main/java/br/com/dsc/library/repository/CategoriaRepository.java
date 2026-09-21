package br.com.dsc.library.repository;

import br.com.dsc.library.model.Categoria;
import br.com.dsc.library.repository.projection.QuantidadeLivrosPorCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("select c.nome as nomeCategoria, count(l) as quantidadeLivros " +
           "from Categoria c left join c.livros l " +
           "group by c.id, c.nome order by count(l) desc")
    List<QuantidadeLivrosPorCategoria> contarLivrosPorCategoria();
}
