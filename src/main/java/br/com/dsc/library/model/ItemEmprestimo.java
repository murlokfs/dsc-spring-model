package br.com.dsc.library.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemEmprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Emprestimo emprestimo;

    @ManyToOne
    private Livro livro;

    public ItemEmprestimo() {
    }

    public ItemEmprestimo(Emprestimo emprestimo, Livro livro) {
        this.emprestimo = emprestimo;
        this.livro = livro;
    }

    public Long getId() {
        return id;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}
