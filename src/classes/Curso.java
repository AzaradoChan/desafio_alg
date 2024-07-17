package classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Curso {
  private HashMap<String, Turma> turmas;
  private Aluno melhorAluno;
  private Aluno piorAluno;
  private HashMap<String, Double> totalNotas = new HashMap<>();
  private HashMap<String, ArrayList<Integer>> notas = new HashMap<>();
  private Long qtdAlunosAprovados = 0l;
  private Long qtdAlunosReprovados = 0l;

  private Long qtdAlunos = 0l;

  public Curso() {
      this.turmas = new HashMap<>();
  }


  public void addTurma(Turma turma) {
      this.turmas.put(turma.getAno(), turma);
  }

  public void addAluno(Aluno aluno) {
    String anoAluno = aluno.getAno();
      Turma turma = this.turmas.get(anoAluno);
      if (turma == null) {
          turma = new Turma(anoAluno);
          this.addTurma(turma);
      }

      if (this.melhorAluno == null || aluno.getMediaDesempenho() > this.melhorAluno.getMediaDesempenho()) {
          this.melhorAluno = aluno;
      }

      if (this.piorAluno == null || aluno.getMediaDesempenho() < this.piorAluno.getMediaDesempenho()) {
          this.piorAluno = aluno;
      }

      turma.addAluno(aluno);
      this.qtdAlunos++;
  }

  public HashMap<String, String> getAlunosStatus() {
    StringBuilder sbDp = new StringBuilder();
    StringBuilder sbAp = new StringBuilder();
    this.turmas.forEach((ano, turma) -> {
      sbDp.append("Ano: " + ano + "{  ");
      sbAp.append("Ano: " + ano + "{  ");
      turma.getAlunos().forEach(aluno -> {

        if (aluno.getQtdMateriasAprovadas() < 4) {
          sbDp.append(aluno.getId() + ", ");
          this.qtdAlunosReprovados++;
        } else {
          sbAp.append(aluno.getId() + ", ");
          this.qtdAlunosAprovados++;
        }
      });
      sbDp.append("}\n");
      sbAp.append("}\n");
    });

    return new HashMap<String, String>() {{
      put("aprovados", sbAp.toString());
      put("reprovados", sbDp.toString());
    }};
  }

  public void populaListaValoresDisciplina() {
    this.turmas.forEach((key, turma) -> {
      turma.getDisciplinas().forEach((ano, disciplina) -> {
        String nomeDisc = disciplina.nome;
        disciplina.getNotas().forEach((nota) -> {
          if (this.notas.get(nomeDisc) == null) {
            this.notas.put(nomeDisc, new ArrayList<>());
          }
          this.notas.get(nomeDisc).add(nota);

          if (this.totalNotas.get(nomeDisc) == null) {
            this.totalNotas.put(nomeDisc, 0.0);
          }
          Double valor = this.totalNotas.get(nomeDisc);
          this.totalNotas.put(nomeDisc, valor + nota);
        });
      });
    });

    this.notas.forEach((disciplina, notas) -> {
      Collections.sort(notas);
    });
  }

  public String getMediaTotalDisciplinas() {
    StringBuilder sb = new StringBuilder();
    this.totalNotas.forEach((disciplina, valor) -> {
      ArrayList<Integer> notas = this.notas.get(disciplina);
      sb.append("[\n\tDisciplina: " + disciplina + "\n");
      sb.append("\tMedia: " + valor / notas.size() + "\n]\n");
    });

    return sb.toString();
  }

  public String getMedianasTotalDisciplinas() {

    StringBuilder sb = new StringBuilder();

    this.notas.forEach((disc, notas) -> {
      int n = notas.size();
      if (n % 2 == 0) {
        sb.append("Disciplina: " + disc + " Mediana: " + (notas.get(n / 2) + notas.get(n / 2 - 1)) / 2.0 + "\n");
      } else {
        sb.append("Disciplina: " + disc + " Mediana: " + notas.get(n / 2) + "\n");
      }
    });

    return sb.toString();
  }

  public String getDesvioPadraoTotalDisciplinas() {
    StringBuilder sb = new StringBuilder();
    this.totalNotas.forEach((disciplina, valor) -> {
      ArrayList<Integer> notas = this.notas.get(disciplina);
      double media = valor / notas.size();
      double soma = 0;
      for (Integer nota : notas) {
        soma += Math.pow(nota - media, 2);
      }
      sb.append("[\n\tDisciplina: " + disciplina + "\n");
      sb.append("\tDesvio Padrao: " + Math.sqrt(soma / notas.size()) + "\n]\n");
    });

    return sb.toString();
  }

  public String getAprovadosPorDisciplina(){
    StringBuilder sb = new StringBuilder();
    this.turmas.forEach((ano, turma) -> {
      sb.append("Ano: " + ano + " {\n");
      turma.getDisciplinas().forEach((disciplina, disciplinaObj) -> {
        sb.append("\n");
        sb.append("\tDisciplina: " + disciplina + "\n");
        sb.append("\tAprovados: " + disciplinaObj.getLenAprovados() + "\n");
        sb.append("\tReprovados: " + disciplinaObj.getLenReprovados() + "\n");
        sb.append("\n");
      });
      sb.append("}\n");
    });
    return sb.toString();
  }

  public String getQtdAlunosStatusTotal() {
    return "Aprovados no Total: " + this.qtdAlunosAprovados + "\nReprovados no Total: " + this.qtdAlunosReprovados + "\n";
  }

  public String getMelhorPiorAluno() {
    return "Melhor Aluno: " + this.melhorAluno.getId() + " Media Desempenho: "+ this.melhorAluno.getMediaDesempenho() + "\nPior Aluno: " + this.piorAluno.getId() + " Media Desempenho: "+ this.piorAluno.getMediaDesempenho()+"\n";
  }

  public String getTotalAlunos() {
    return "Total de Alunos: " + this.qtdAlunos + "\n";
  }

  public String getMMDPorDisciplina() {
    StringBuilder sb = new StringBuilder();
    this.turmas.forEach((ano, turma) -> {
      sb.append("Ano: " + ano + " {\n");
      sb.append(turma.getMMDPorDisciplina());
      sb.append("}\n");
    });
    return sb.toString();
  }
  
  public Long getQtdAlunos() {
      return qtdAlunos;
  }
}
