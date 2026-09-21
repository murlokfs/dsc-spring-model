package br.com.dsc.library.repository;

import br.com.dsc.library.model.Categoria;
import br.com.dsc.library.model.Emprestimo;
import br.com.dsc.library.model.ItemEmprestimo;
import br.com.dsc.library.model.Livro;
import br.com.dsc.library.model.StatusEmprestimo;
import br.com.dsc.library.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmprestimoRepositoryTest {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private ItemEmprestimoRepository itemEmprestimoRepository;

    @Test
    void deveListarEmprestimosAtivosDoUsuarioComSeusLivros() {
        Usuario usuario = usuarioRepository.save(new Usuario("Maria Silva", "456", "maria@email.com", "8888", LocalDate.now(), "Rua B", true));
        Categoria categoria = categoriaRepository.save(new Categoria("Historia", "Historia geral"));
        Livro livro = livroRepository.save(new Livro("444", "Historia do Brasil", "Editora D", 2021, 220, 3, 2, categoria));
        Emprestimo emprestimo = emprestimoRepository.save(new Emprestimo(usuario, LocalDate.now(), LocalDate.now().plusDays(7), null, StatusEmprestimo.ATIVO, null));
        itemEmprestimoRepository.save(new ItemEmprestimo(emprestimo, livro));

        assertThat(emprestimoRepository.findByUsuarioIdAndStatus(usuario.getId(), StatusEmprestimo.ATIVO))
                .singleElement().satisfies(resultado -> assertThat(resultado.getItens()).hasSize(1));
    }

    @Test
    void deveListarSomenteEmprestimosAtivosComVencimentoPassado() {
        Usuario usuario = usuarioRepository.save(new Usuario("Carlos Lima", "789", "carlos@email.com", "7777", LocalDate.now(), "Rua C", true));
        Emprestimo atrasado = new Emprestimo(usuario, LocalDate.now().minusDays(10), LocalDate.now().minusDays(2), null, StatusEmprestimo.ATIVO, null);
        Emprestimo devolvido = new Emprestimo(usuario, LocalDate.now().minusDays(10), LocalDate.now().minusDays(2), LocalDate.now(), StatusEmprestimo.DEVOLVIDO, null);
        emprestimoRepository.saveAll(Arrays.asList(atrasado, devolvido));

        assertThat(emprestimoRepository.findAtrasados(StatusEmprestimo.ATIVO)).containsExactly(atrasado);
    }
}
