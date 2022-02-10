import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    /* public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    } */
    public static void main(String[] args)
        throws IOException
    {
        System.out.println("Enter desired distance from center");
        
        // Enter data using BufferReader
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in)
        );

        // Reading data using readLine
        String name = reader.readLine();

        // Printing the read line
        System.out.println(name);
    }

    float angle = 1.22173f;

}
