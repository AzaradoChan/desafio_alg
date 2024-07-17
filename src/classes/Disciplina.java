package classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

public class Disciplina {
  private ArrayList<Integer> notas;
  private  boolean sorted;
  private double somaTotal = 0;
  private long qtdAluno = 0;
  private double mediana;
  private HashMap<String, Integer> aprovados;
  private HashMap<String, Integer> reprovados;

  public Disciplina() {
      this.notas = new ArrayList<>();
      this.aprovados = new HashMap<>();
      this.reprovados = new HashMap<>();
  }

  public Double getSomaTotal() {
      return this.somaTotal;
  }

  public void addNota(String ano, int nota, String idAluno) {
    sorted = false;  
    this.notas.add(nota);
    this.somaTotal += nota;
    this.qtdAluno++;

    if (nota >= 70) {
        this.aprovados.put(idAluno, (Integer) nota);
    } else {
        this.reprovados.put(idAluno, (Integer) nota);
    }
  }

  public double getMedia() {
      return this.somaTotal / this.qtdAluno;
  }

  public double getMediana() {
    if (sorted && !Objects.equals(mediana, null)) {
      return mediana;
    } else if (!sorted) {
      Collections.sort(this.getNotas());
      sorted = true;
    }
    int n = this.getNotas().size();
    if (n % 2 == 0) {
      this.mediana = (this.getNotas().get(n / 2) + this.getNotas().get(n / 2 - 1)) / 2.0;
    } else {
      this.mediana = this.getNotas().get(n / 2);
    }
    return this.mediana;
  }
  
  public ArrayList<Integer> getNotas() {
    return notas;
  }

  public void setNotas(ArrayList<Integer> notas) {
    this.notas = notas;
  }

  public void setSomaTotal(double somaTotal) {
    this.somaTotal = somaTotal;
  }

  public long getQtdAluno() {
    return qtdAluno;
  }

  public void setQtdAluno(long qtdAluno) {
    this.qtdAluno = qtdAluno;
  }

  public HashMap<String, Integer> getAprovados() {
    return aprovados;
  }

  public void setAprovados(HashMap<String, Integer> aprovados) {
    this.aprovados = aprovados;
  }

  public HashMap<String, Integer> getReprovados() {
    return reprovados;
  }

  public void setReprovados(HashMap<String, Integer> reprovados) {
    this.reprovados = reprovados;
  }

  
}