package fonte;

import java.util.Arrays;
import java.util.Random;

/** 
 * MIT License
 *
 * Copyright(c) 2024 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/**
 * fonte.App-base para demonstração de valores aleatórios com semente fixa para testes e marcação de tempo em nanossegundos.
 */
public class App {
    public static void main(String[] args) throws Exception {
        Random rand = new Random(42);       //semente fixa: gera sempre os mesmos números
        final int TAMANHO = 100_000_000;         //tamanho do conjunto de teste
        final double NANO_TO_MS = 1_000_000d;    //para converter de nano a milissegundos
        final double MS_TO_SEC = 1_000d;         //para converter de mili a segundos
        int[] valores = new int[TAMANHO];        //vetor com dados de teste

        long ini = System.nanoTime();           //início da marcação de tempo
       
        for (int i = 0; i < TAMANHO; i++) {
            valores[i] = rand.nextInt(TAMANHO)+1;       //preenchendo o vetor com valores aleatórios (fixos) entre 1 e TAMANHO 
        }

        Arrays.sort(valores);                   //ordena o array utilizando um algoritmo "Dual-Pivot Quicksort" (YAROSLAVSKIY, 2009)
                                                // ver referências completas no fim do código

        long fim = System.nanoTime();           //fim da marcação de tempo

        double tempoMs = (fim-ini)/NANO_TO_MS;      //conversões e, em seguida, impressão do resultado
        double tempoSeg = tempoMs/MS_TO_SEC;
        
        System.out.println("Finalizado em "+ String.format("%.2f",tempoMs)+" ms ("
                                          +  String.format("%.4f",tempoSeg)+" segundos)."); 
    }
}

/// YAROSLAVSKIY, V. Dual-pivot quicksort. Research Disclosure, 2009.
// Disponível em 
// https://pt.wikipedia.org/wiki/Quicksort#Quicksort_utilizando_dois_ou_mais_pivôs
// https://en.wikipedia.org/wiki/Quicksort#Variants
// http://www.kriche.com.ar/root/programming/spaceTimeComplexity/DualPivotQuicksort.pdf
