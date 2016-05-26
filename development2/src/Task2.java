import java.io.*;

public class Task2 {
    public static void main(String[] args) throws FileNotFoundException {

        ListSorted ls = new ListSorted();
        InputStream input = new FileInputStream("in.txt");
        OutputStream output = new FileOutputStream("out.txt");
        ls.process(input, output);
    }
}
