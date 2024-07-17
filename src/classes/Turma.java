package classes;

import java.util.ArrayList;
import java.util.HashMap;

public class Turma {
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

      String anoAluno = aluno.getAno();
      String idAluno = aluno.getId();
      
      this.disciplinas.get("disc1").addNota(anoAluno, aluno.getDisciplina1(), idAluno);
      this.disciplinas.get("disc2").addNota(anoAluno, aluno.getDisciplina2(), idAluno);
      this.disciplinas.get("disc3").addNota(anoAluno, aluno.getDisciplina3(), idAluno);
      this.disciplinas.get("disc4").addNota(anoAluno, aluno.getDisciplina4(), idAluno);

      this.qtdAlunos++;
  }

  public void getMediaDisciplinaDisciplina() {
    StringBuilder sb = new StringBuilder();
    this.disciplinas.forEach((disciplina, disciplinaObj) -> {
      sb.append("Disciplina: " + disciplina + "\n");
      sb.append("Media: " + disciplinaObj.getMedia() + "\n");
      sb.append("Mediana: " + disciplinaObj.getMediana() + "\n");
    });
  }
  public long getQtdAlunos() {
      return this.qtdAlunos;
  }

  public String getAno() {
    return ano;
  }

  public void setAno(String ano) {
    this.ano = ano;
  }

  public HashMap<String, Disciplina> getDisciplinas() {
    return disciplinas;
  }

  public void setDisciplinas(HashMap<String, Disciplina> disciplinas) {
    this.disciplinas = disciplinas;
  }

  public ArrayList<Aluno> getAlunos() {
    return alunos;
  }

  public void setAlunos(ArrayList<Aluno> alunos) {
    this.alunos = alunos;
  }

  public void setQtdAlunos(long qtdAlunos) {
    this.qtdAlunos = qtdAlunos;
  }

  
}
