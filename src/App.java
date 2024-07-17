import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import classes.Aluno;
import classes.Curso;
import classes.FileManager;

public class App {

    public static String inputFilePath =  "./input.txt";
    public static String defaultOutputFilePath =  "./output";

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        String arquivo = "./input.txt";
        Curso curso = new Curso();

        try {
            BufferedReader bf = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(arquivo), StandardCharsets.UTF_8
                ), 10 * 8192
            );

            boolean firstIteraction = true;
            String line = bf.readLine();
            while (line != null) {
                if (firstIteraction) {
                    line = bf.readLine();
                    firstIteraction = false;
                    continue;
                }

                int inicio = 0;
                int fim = line.indexOf(",");
                int[] alunoData = new int[6];
                int i = 0;
                while (i <= 5) {
                    String elemento = line.substring(inicio, fim);
                    
                    alunoData[i] = Integer.parseInt(elemento);
                    inicio = fim + 1;
                    i++;
                    if (i == 5) {
                        fim = line.length();
                    } else {
                        fim = line.indexOf(",", inicio);
                    }
                }

                Aluno aluno = new Aluno(alunoData);

                curso.addAluno(aluno);

                line = bf.readLine();
            }
            bf.close();

            HashMap<String, String> dps = curso.getAlunosStatus();

            FileManager.writeOutputFile(
                defaultOutputFilePath + "/dps.txt", dps.get("reprovados"));
            FileManager.writeOutputFile(
                defaultOutputFilePath + "/aps.txt", dps.get("aprovados"));

            long endTime = System.currentTimeMillis();
            System.out.println("Tempo de execução: " + (endTime - startTime) + "ms");
            System.out.println("Quantidade de alunos: " + curso.getQtdAlunos());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
