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

            curso.populaListaValoresDisciplina();

            HashMap<String, String> dps = curso.getAlunosStatus();

            FileManager.writeOutputFile(
                defaultOutputFilePath + "/dps.txt", dps.get("reprovados"));
            FileManager.writeOutputFile(
                defaultOutputFilePath + "/aps.txt", dps.get("aprovados"));

            FileManager.writeOutputFile(
                defaultOutputFilePath + "/aps_rps_por_disciplina.txt", curso.getAprovadosPorDisciplina());
            
            FileManager.writeOutputFile(
                defaultOutputFilePath + "/media_disciplinas.txt", curso.getMediaTotalDisciplinas());
            
            FileManager.writeOutputFile(
                defaultOutputFilePath + "/mediana_disciplinas.txt", curso.getMedianasTotalDisciplinas());
            
            FileManager.writeOutputFile(
                defaultOutputFilePath + "/desvio_padrao.txt", curso.getDesvioPadraoTotalDisciplinas());

            StringBuilder sb = new StringBuilder();
            sb.append(curso.getTotalAlunos());
            sb.append(curso.getQtdAlunosStatusTotal());
            sb.append(curso.getMelhorPiorAluno());
            sb.append(curso.getMMDPorDisciplina());
            FileManager.writeOutputFile(
                defaultOutputFilePath + "/main.txt", sb.toString());

            long endTime = System.currentTimeMillis();
            System.out.println("Tempo de execução: " + (endTime - startTime) + "ms");
            System.out.println("Quantidade de alunos: " + curso.getQtdAlunos());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
