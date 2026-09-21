package br.com.dsc.library.repository;

import br.com.dsc.library.model.Autor;
import br.com.dsc.library.model.Categoria;
import br.com.dsc.library.model.Livro;
import br.com.dsc.library.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class LivroRepositoryTest {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void deveConsultarLivrosConformeOsFiltrosDaAtividade() {
        Categoria tecnologia = categoriaRepository.save(new Categoria("Tecnologia", "Computacao"));
        Categoria romance = categoriaRepository.save(new Categoria("Romance", "Ficcao"));
        Autor autor = autorRepository.save(new Autor("Ana Souza", LocalDate.of(1980, 1, 1), "Brasileira", "Autora"));
        usuarioRepository.save(new Usuario("Joao Silva", "123", "joao@email.com", "9999", LocalDate.now(), "Rua A", true));

        Livro disponivel = new Livro("111", "Java Moderno", "Editora A", 2020, 300, 2, 1, tecnologia);
        disponivel.getAutores().add(autor);
        Livro indisponivel = new Livro("222", "Banco de Dados", "Editora B", 2019, 250, 1, 0, tecnologia);
        Livro romanceLivro = new Livro("333", "Uma Historia", "Editora C", 2018, 180, 1, 1, romance);
        livroRepository.saveAll(Arrays.asList(disponivel, indisponivel, romanceLivro));

        assertThat(livroRepository.findDisponiveis()).extracting(Livro::getTitulo)
                .containsExactly("Java Moderno", "Uma Historia");
        assertThat(livroRepository.findByCategoriaNome("tecnologia")).extracting(Livro::getTitulo)
                .containsExactlyInAnyOrder("Java Moderno", "Banco de Dados");
        assertThat(livroRepository.findByAutorNome("ana souza")).extracting(Livro::getTitulo)
                .containsExactly("Java Moderno");
        assertThat(usuarioRepository.findByNomeContaining("silva")).hasSize(1);
        assertThat(categoriaRepository.contarLivrosPorCategoria()).extracting("quantidadeLivros")
                .containsExactly(2L, 1L);
    }
}
