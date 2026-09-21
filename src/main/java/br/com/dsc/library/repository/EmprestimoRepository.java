package br.com.dsc.library.repository;

import br.com.dsc.library.model.Emprestimo;
import br.com.dsc.library.model.StatusEmprestimo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    @EntityGraph(attributePaths = {"itens", "itens.livro"})
    List<Emprestimo> findByUsuarioIdAndStatus(Long usuarioId, StatusEmprestimo status);

    @EntityGraph(attributePaths = {"usuario", "itens", "itens.livro"})
    @Query("select distinct e from Emprestimo e " +
           "where e.dataDevolucaoPrevista < CURRENT_DATE and e.status = :status")
    List<Emprestimo> findAtrasados(@Param("status") StatusEmprestimo status);
}
