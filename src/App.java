import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class App {

    public static class Curso {
        private HashMap<String, Turma> turmas;
        private Long qtdAlunos = 0l;
        private Double[] somaTotalDisciplinas = {0d, 0d, 0d, 0d};

        public Curso() {
            this.turmas = new HashMap<>();
        }
    

        public void addTurma(Turma turma) {
            this.turmas.put(turma.ano, turma);
        }

        public void addAluno(Aluno aluno) {
            Turma turma = this.turmas.get(aluno.ano);
            if (turma == null) {
                turma = new Turma(aluno.ano);
                this.addTurma(turma);
            }

            turma.addAluno(aluno);
            this.qtdAlunos++;
        }

        public void getMediaTurma(String ano) {
            Turma turma = this.turmas.get(ano);
            if (turma == null) {
                System.out.println("Turma não encontrada");
                return;
            }

            System.out.println("Média da turma " + ano);
            System.out.println("Disciplina 1: " + turma.disciplinas.get("disc1").getMedia());
            System.out.println("Disciplina 2: " + turma.disciplinas.get("disc2").getMedia());
            System.out.println("Disciplina 3: " + turma.disciplinas.get("disc3").getMedia());
            System.out.println("Disciplina 4: " + turma.disciplinas.get("disc4").getMedia());
            System.out.println("-------------------------------------------------");
        }

        public void getMediaPorAnos() {
            this.turmas.forEach((ano, turma) -> {
                this.getMediaTurma(ano);
            });
        }

        public void getMediaTodosAnos() {
            Double[] somaGeral = {0d, 0d, 0d, 0d};
            this.turmas.forEach((ano, turma) -> {
                somaGeral[0] += turma.disciplinas.get("disc1").getSomaTotal();
                somaGeral[1] += turma.disciplinas.get("disc2").getSomaTotal();
                somaGeral[2] += turma.disciplinas.get("disc3").getSomaTotal();
                somaGeral[3] += turma.disciplinas.get("disc4").getSomaTotal();
            });

            Integer lengthTurmas = this.turmas.size();
            Double[] medias = {
                somaGeral[0] / this.qtdAlunos,
                somaGeral[1] / this.qtdAlunos,
                somaGeral[2] / this.qtdAlunos,
                somaGeral[3] / this.qtdAlunos
            };

            for (int m = 0; m < medias.length; m++) {
                System.out.println("Média da disciplina " + (m + 1) + ": " + medias[m]);
            }
        }


        public Long getQtdAlunos() {
            return qtdAlunos;
        }
    }

    public static class Turma {
        private String ano;
        private HashMap<String, Disciplina> disciplinas;
        private ArrayList<Aluno> alunos;
        private long qtdAlunos = 0;

        public Turma() {
            this.disciplinas = new HashMap<>();
            this.disciplinas.put("disc1", new Disciplina());
            this.disciplinas.put("disc2", new Disciplina());
            this.disciplinas.put("disc3", new Disciplina());
            this.disciplinas.put("disc4", new Disciplina());
            
            this.alunos = new ArrayList<>();
        }

        public Turma(String ano) {
            this();
            this.ano = ano;
        }

        public void addAluno(Aluno aluno) {
            this.alunos.add(aluno);

            this.disciplinas.get("disc1").addNota(aluno.ano, aluno.disciplina1, aluno.id);
            this.disciplinas.get("disc2").addNota(aluno.ano, aluno.disciplina2, aluno.id);
            this.disciplinas.get("disc3").addNota(aluno.ano, aluno.disciplina3, aluno.id);
            this.disciplinas.get("disc4").addNota(aluno.ano, aluno.disciplina4, aluno.id);
            this.qtdAlunos++;
        }

        public long getQtdAlunos() {
            return this.qtdAlunos;
        }
    }
    
    public static class Disciplina {
        private ArrayList<Integer> notas;
        private double somaTotal = 0;
        private long qtdAluno = 0;
        
        public Disciplina() {
            this.notas = new ArrayList<>();
        }

        public Double getSomaTotal() {
            return this.somaTotal;
        }

        public void addNota(String ano, int nota, String idAluno) {
            this.notas.add(nota);
            this.somaTotal += nota;
            this.qtdAluno++;
        }

        public double getMedia() {
            return this.somaTotal / this.qtdAluno;
        }
    }

    public static class Aluno {
        private String id;
        private int disciplina1;
        private int disciplina2;
        private int disciplina3;
        private int disciplina4;

        private String ano;

        public Aluno(int[] data) {
            this.id = String.valueOf(data[0]);
            this.disciplina1 = data[1];
            this.disciplina2 = data[2];
            this.disciplina3 = data[3];
            this.disciplina4 = data[4];
            this.ano = String.valueOf(data[5]);
        }
    }
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

            
            curso.getMediaPorAnos();
            curso.getMediaTodosAnos();
            
            long endTime = System.currentTimeMillis();
            System.out.println("Tempo de execução: " + (endTime - startTime) + "ms");
            System.out.println("Quantidade de alunos: " + curso.getQtdAlunos());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
