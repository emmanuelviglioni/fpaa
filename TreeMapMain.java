import fonte.Pessoa;

import javax.management.InvalidAttributeValueException;
import java.util.Random;
import java.util.TreeMap;
import java.util.Map;

public class TreeMapMain {
    public static void main(String[] args) {

        final int TAMANHO_P = 2500000;
        final int TAMANHO_N = 40000;
        final double NANO_TO_MS = 1_000_000d;
        final double MS_TO_SEC = 1_000d;
        Random r = new Random(42);
        int[] valores = new int[TAMANHO_N];
        char c;
        long somaIds = 0;

        long iniCriacao = System.nanoTime();

        Map<Integer, Pessoa> treeMapPessoas = new TreeMap<>();
        for (int i = 0; i < TAMANHO_P; i++) {
            c = (char) (r.nextInt(26) + 'a');
            try {
                treeMapPessoas.put(i + 1, new Pessoa(i + 1, generateRandomName(c)));
            } catch (InvalidAttributeValueException e) {
                e.printStackTrace();
            }
        }

        long fimCriacao = System.nanoTime();

        long iniBusca = System.nanoTime();

        for (int i = 0; i < TAMANHO_N; i++) {
            valores[i] = r.nextInt(TAMANHO_P) + 1;
            if (treeMapPessoas.containsKey(valores[i])) {
                somaIds++;
            }
        }

        long fimBusca = System.nanoTime();

        System.out.println("Soma dos IDs: " + somaIds);

        double tempoCriacaoMs = (fimCriacao - iniCriacao) / NANO_TO_MS;
        double tempoCriacaoSeg = tempoCriacaoMs / MS_TO_SEC;

        double tempoBuscaMs = (fimBusca - iniBusca) / NANO_TO_MS;
        double tempoBuscaSeg = tempoBuscaMs / MS_TO_SEC;

        double tempoTotalMs = tempoCriacaoMs + tempoBuscaMs;
        double tempoTotalSeg = tempoCriacaoSeg + tempoBuscaSeg;

        System.out.println("Tempo de criação: " + String.format("%.2f", tempoCriacaoMs) + " ms (" + String.format("%.4f", tempoCriacaoSeg) + " segundos).");
        System.out.println("Tempo de busca: " + String.format("%.2f", tempoBuscaMs) + " ms (" + String.format("%.4f", tempoBuscaSeg) + " segundos).");
        System.out.println("Tempo total: " + String.format("%.2f", tempoTotalMs) + " ms (" + String.format("%.4f", tempoTotalSeg) + " segundos).");

    }

    private static String generateRandomName(char c) {
        return "P" + c;
    }
}