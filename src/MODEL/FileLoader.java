package MODEL;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileLoader {

    public FileLoader() {
    }

    public Scanner fileFinder(String fileName){
        Scanner myReader = null;
        try {
            File myObj = new File(fileName);
            myReader = new Scanner(myObj);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return myReader;
    }

//    public void loadComida(String ruta){
//        Scanner lector =
//        while (myReader.hasNextLine()) {
//            String data = myReader.nextLine();
//            System.out.println(data);
//        }
//        myReader.close();
//
//        return combos;
//    }
}
