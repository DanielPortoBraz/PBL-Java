package Controller;

import Model.Genero;
import Model.RegistroRepositorio;
import Model.Registro;

import java.util.HashSet;

public class RegistroController {
    private RegistroRepositorio registroR;

    public RegistroController(){
        this.registroR = new RegistroRepositorio();
    }

    public void cadastrarRegistro(String titulo, HashSet<Genero> generos, int anoLancamento,
                             boolean visto){
        registroR.addRegistro(new Registro(titulo, generos, anoLancamento, visto));
    }

    public void avaliarRegistro(){

    }

    public void listarRegistros() {

    }
}