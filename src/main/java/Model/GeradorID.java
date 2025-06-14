package Model;

import java.util.HashSet;
import java.util.Random;

/**
 * Classe responsável por gerar IDs únicos para elementos.
 * Garante que o ID gerado não se repita com os já existentes na lista fornecida.
 */
public class GeradorID {
    private static final Random random = new Random();

    /**
     * Gera um ID único baseado nos IDs já existentes na lista fornecida.
     * O ID gerado é um número aleatório entre 0 e 9999, e será único dentro da lista de IDs.
     *
     * @param listaId Lista de IDs já existentes para garantir a unicidade do novo ID.
     * @return Um novo ID único.
     */
    public static int gerarID(HashSet<Integer> listaId) {
        int novoId;
        do {
            novoId = random.nextInt(10000); // Gera de 0 até 9999
        } while (listaId.contains(novoId));

        listaId.add(novoId);
        return novoId;
    }
}
