import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args)
        throws IOException
    {
        System.out.println("Enter desired distance from center");

        // Enter data using BufferReader
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in)
        );

        // Reading data using readLine
        String distString = reader.readLine();

        // Printing the read line
        //System.out.println(name);
        
        // math variables, most of these dont need to be changed
        double angle = 1.22173; // #in radians
        double hoop = 2.4384; // #height of hood from ground
        double step = 5e-4; // #Euler's method delta x.  smaller values increase accuracy but raise calculation time
        double c = 0.47; // drag coefficent
        double p = 1.04; // air density?
        double a = Math.PI * (Math.pow(0.3, 2)); // cross-sectional area
        double mu = (c*p*a)/2; // drag force dont change
        double g = 9.807; // gravity constant dont change

    }
}
