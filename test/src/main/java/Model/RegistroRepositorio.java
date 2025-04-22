package Model;

import java.util.TreeSet;

public class RegistroRepositorio {

    private TreeSet<Registro> registros;

    public RegistroRepositorio() {
        this.registros = new TreeSet<Registro>();
    }

    public boolean addRegistro(Registro registro){
        return this.registros.add(registro);
    }

    public boolean removeRegistro(Registro registro){
        return this.registros.remove(registro);
    }

    public TreeSet<Registro> getRegistros() {
        return registros;
    }
}
