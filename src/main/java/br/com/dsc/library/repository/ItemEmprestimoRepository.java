package br.com.dsc.library.repository;

import br.com.dsc.library.model.ItemEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemEmprestimoRepository extends JpaRepository<ItemEmprestimo, Long> {
}
