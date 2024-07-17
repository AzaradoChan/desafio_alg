package classes;

public class Aluno {
  private String id;
  private double mediaDesempenho;
  private int disciplina1;
  private int disciplina2;
  private int disciplina3;
  private int disciplina4;
  private int qtdMateriasAprovadas;

  private String ano;

  public Aluno(int[] data) {
      this.id = String.valueOf(data[0]);
      this.disciplina1 = data[1];
      this.disciplina2 = data[2];
      this.disciplina3 = data[3];
      this.disciplina4 = data[4];
      this.ano = String.valueOf(data[5]);
      
      this.qtdMateriasAprovadas = 
        (this.disciplina1 >= 70 ? 1 : 0) +
        (this.disciplina2 >= 70 ? 1 : 0) +
        (this.disciplina3 >= 70 ? 1 : 0) +
        (this.disciplina4 >= 70 ? 1 : 0);
      
      this.mediaDesempenho = (this.disciplina1 + this.disciplina2 + this.disciplina3 + this.disciplina4) / 4.0;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getDisciplina1() {
    return disciplina1;
  }

  public void setDisciplina1(int disciplina1) {
    this.disciplina1 = disciplina1;
  }

  public int getDisciplina2() {
    return disciplina2;
  }

  public void setDisciplina2(int disciplina2) {
    this.disciplina2 = disciplina2;
  }

  public int getDisciplina3() {
    return disciplina3;
  }

  public void setDisciplina3(int disciplina3) {
    this.disciplina3 = disciplina3;
  }

  public int getDisciplina4() {
    return disciplina4;
  }

  public void setDisciplina4(int disciplina4) {
    this.disciplina4 = disciplina4;
  }

  public int getQtdMateriasAprovadas() {
    return qtdMateriasAprovadas;
  }

  public void setQtdMateriasAprovadas(int qtdMateriasAprovadas) {
    this.qtdMateriasAprovadas = qtdMateriasAprovadas;
  }

  public String getAno() {
    return ano;
  }

  public void setAno(String ano) {
    this.ano = ano;
  }

  public double getMediaDesempenho() {
    return mediaDesempenho;
  }

  
  
}