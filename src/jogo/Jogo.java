package jogo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Jogador;

/**
 *
 * @author Emmerich
 */
public class Jogo {

    private Jogador jogador1 = new Jogador();
    private Jogador jogador2 = new Jogador();
    private int numeroAleatorio;

//    private List<Jogador> jogadores = new ArrayList<>(); //Criar jogo com varios jogadores (Mais do que 2)
    public Jogo() {
        int qtdeJogador = Integer.parseInt(JOptionPane.showInputDialog("Quantos jogadores?"));
        configurarJogador(qtdeJogador);

    }

    private void configurarJogador(int qtdeJogador) {
//        for (int i = 0; i < qtdeJogador; i++) {
//            jogadores.add(new Jogador());
//            jogadores.get(i).setNome(JOptionPane.showInputDialog("Qual o nome do jogador?"));
//        }
//        for (Jogador jogadore : jogadores) {
//            System.out.println(jogadore.getNome());
//        }
        if (qtdeJogador == 2) {
            jogador1.setNome(JOptionPane.showInputDialog("Qual o nome do primeiro jogador?"));
            jogador2.setNome(JOptionPane.showInputDialog("Qual o nome do segundo jogador?"));
            jogoMultiplayer();

        } else {
            jogador1.setNome(JOptionPane.showInputDialog("Qual o nome do primeiro jogador?"));
            jogador2.setNome("Computador");
            jogoSingleplayer();
        }
        System.out.println(jogador1.getNome());
        System.out.println(jogador2.getNome());
    }

    public void jogoMultiplayer() {
        JOptionPane.showMessageDialog(null, "Jogo de adivinhação Multi Player");
        String menu;
        boolean acerto;

        do {
            numeroAleatorio = (int) (Math.random() * 100 + 1);

            acerto = false;
            do {
                jogador1.setPalpite(Integer.parseInt(JOptionPane.showInputDialog(jogador1.getNome() + " "
                        + "digite o seu palpite:")));
                acerto = verificarAcerto(jogador1);
                if (!acerto) {
                    jogador2.setPalpite(Integer.parseInt(JOptionPane.showInputDialog(jogador2.getNome() + " "
                            + "digite o seu palpite:")));
                    acerto = verificarAcerto(jogador2);
                }

            } while (!acerto);

            mostrarPlacar();
            menu = JOptionPane.showInputDialog("Deseja continuar? (s/n)");
        } while (menu.equalsIgnoreCase("s"));
    }
    
    public void jogoSingleplayer() {
        JOptionPane.showMessageDialog(null, "Jogo de adivinhação Single Player");
        String menu;
        boolean acerto;

        do {
            numeroAleatorio = (int) (Math.random() * 100 + 1);

            acerto = false;
            do {
                jogador1.setPalpite(Integer.parseInt(JOptionPane.showInputDialog(jogador1.getNome() + " "
                        + "digite o seu palpite:")));
                acerto = verificarAcerto(jogador1);
                if (!acerto) {
                    palpiteComputador(jogador2);
                    JOptionPane.showMessageDialog(null, "Palpite da maquina foi: " + jogador2.getPalpite());
                    acerto = verificarAcerto(jogador2);
                }

            } while (!acerto);

            mostrarPlacar();
            menu = JOptionPane.showInputDialog("Deseja continuar? (s/n)");
        } while (menu.equalsIgnoreCase("s"));
    }

    private boolean verificarAcerto(Jogador jogador) {
        if (numeroAleatorio == jogador.getPalpite()) {
            JOptionPane.showMessageDialog(null, jogador.getNome() + " você acertou!");
            jogador.setPonto(jogador.getPonto() + 1);

            return true;
        } else {
            if (numeroAleatorio > jogador.getPalpite()) {
                JOptionPane.showMessageDialog(null, "O numero é maior que o palpite!");
            } else {
                JOptionPane.showMessageDialog(null, "O numero é menor que o palpite!");
            }
        }
        return false;
    }

    private void mostrarPlacar() {
        JOptionPane.showMessageDialog(null, "O placar está: \n\n"
                + jogador1.getNome() + ": " + jogador1.getPonto() + "\n"
                + jogador2.getNome() + ": " + jogador2.getPonto());

    }

    private void palpiteComputador(Jogador jogador) {
        int palpiteMaquina = (int) (Math.random() * 100 + 1);
        jogador.setPalpite(palpiteMaquina);

    }
}
