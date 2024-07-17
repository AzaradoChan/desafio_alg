package classes;

import java.util.HashMap;

public class Curso {
  private HashMap<String, Turma> turmas;
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
        } else {
          sbAp.append(aluno.getId() + ", ");
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

  public Long getQtdAlunos() {
      return qtdAlunos;
  }
}
