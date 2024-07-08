import fonte.Pessoa;

import javax.management.InvalidAttributeValueException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArrayListMain {
    public static void main(String[] args) {

        //tamanho de pessoas a serem preenchidas no vetor
        final int TAMANHO_P = 2500000;
        //quantidade de números que seram buscados
        final int TAMANHO_N = 20000;
        final double NANO_TO_MS = 1_000_000d;
        final double MS_TO_SEC = 1_000d;
        Random r = new Random(42);
        int[] valores = new int[TAMANHO_N];
        char c;

        List<Pessoa> pessoas = new ArrayList<>();
        List<Integer> idsPessoas = new ArrayList<>();
        int somaIds = 0;

        long iniCriacao = System.nanoTime();

        for (int i = 0; i < TAMANHO_P; i++) {
            c = (char) (r.nextInt(26) + 'a');

            try {
                pessoas.add(new Pessoa(i + 1, genereteRandomName(c)));
                idsPessoas.add(i + 1);
            } catch (InvalidAttributeValueException e) {
                e.printStackTrace();
            }
        }

        long fimCriacao = System.nanoTime();

        long iniBusca = System.nanoTime();

        for (int i = 0; i < TAMANHO_N; i++) {
            valores[i] = r.nextInt(TAMANHO_P) + 1;
            if (idsPessoas.contains(valores[i])) somaIds++;
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

    private static String genereteRandomName(char c) {
        String name = "P" + c;
        return name;
    }
}