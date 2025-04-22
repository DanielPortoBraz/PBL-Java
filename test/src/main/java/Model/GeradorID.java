package Model;

import java.util.HashSet;
import java.util.Random;

// Gera IDs com base na lista de IDs já inseridos. Garante que sempre haja um ID único
public class GeradorID {
    private static final Random random = new Random();

    public static int gerarID(HashSet<Integer> listaId) {
        int novoId;
        do {
            novoId = random.nextInt(10000); // Gera de 0 até 9999
        } while (listaId.contains(novoId));

        listaId.add(novoId);
        return novoId;
    }
}
