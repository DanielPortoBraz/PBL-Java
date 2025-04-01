package Controller;

import Model.LivroRepositorio;
import Model.Genero;
import Model.Livro;

import java.util.HashSet;


public class LivroController extends RegistroController{
    private LivroRepositorio livrosR;

    public LivroController(){
        this.livrosR = new LivroRepositorio();
    }

    public void cadastrarLivro(String titulo, HashSet<Genero> generos, int anoLancamento,
                               boolean visto, String autor, String editora, String isbn, boolean exemplar){
        livrosR.addLivro(new Livro(titulo, generos, anoLancamento, visto, autor, editora, isbn, exemplar));
    }

    public void listarLivros(){
    }
}
