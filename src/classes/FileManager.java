package classes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class FileManager {
  public static void writeOutputFile(String path, String content) {
      try {
          deleteFile(path);
          createFile(path);
          BufferedWriter writer = new BufferedWriter(new FileWriter(path));
          writer.write(content);
          writer.close();
      } catch (IOException e) {
          e.printStackTrace();
      }
  }

  public static void createFile(String path) {
      try {
          File myObj = new File(path);
          if (myObj.createNewFile()) {
              System.out.println("Arquivo criado: " + myObj.getName());
          } else {
              System.out.println("Arquivo já existe");
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
  }

  public static void deleteFile(String path) {
      File myObj = new File(path);
      if (myObj.delete()) {
          System.out.println("Arquivo deletado: " + myObj.getName());
      } else {
          System.out.println("Falha ao deletar arquivo.");
      }
  }
}
