package View;

/**
 * Interface para implementação de menus no terminal.
 * <p>
 * Esta interface define o contrato para a exibição de menus, garantindo que
 * todas as classes que a implementem disponham de um método para apresentar
 * suas opções de forma padronizada.
 * </p>
 */
public interface Menu {

    /**
     * Exibe o menu no terminal.
     * <p>
     * Este método deve conter a lógica necessária para apresentar os itens do menu,
     * possibilitando a interação com o usuário conforme os requisitos da aplicação.
     * </p>
     */
    public void exibir();
}
