import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public static double deviance(double dist, double vr) {
        double angle = 1.22173; // #in radians
        double hoop = 2.4384; // #height of hood from ground
        double step = 5e-4; // #Euler's method delta x.  smaller values increase accuracy but raise calculation time
        double c = 0.47; // drag coefficent
        double p = 1.04; // air density?
        double a = Math.PI * (Math.pow(0.3, 2)); // cross-sectional area
        double mu = (c*p*a)/2; // drag force dont change
        double g = 9.807; // gravity constant dont change

        double x = -dist;
        double y = 0;
        double vx = Math.cos(angle)*vr;
        double vy = Math.sin(angle)*vr;
        double toggle = 0;
        double error = -1;

        while (y >= 0) {
            x = x + step*vx;
            y = y + step*vy;
            vx = vx + step*(-mu*vx*Math.sqrt((Math.pow(vx, 2))+(Math.pow(vy, 2))));
            vy = vy + step*((-g)-mu*vy*Math.sqrt((Math.pow(vx, 2))+(Math.pow(vy, 2))));

            if (y <= hoop && toggle == 1) {
                toggle = toggle + 1;
                error = x;
            }
            if (y >= hoop && toggle == 0) {
                toggle = toggle + 1;
            }
        }

        return error;
    }

    public static double final_vr(double dist, double accuracy, double left_bound, double right_bound) {
        double test_vr = (right_bound+left_bound)/2;
        for (int i = 0; i < accuracy; ++i) {
            if (deviance(dist, test_vr) > 0) {
                right_bound = (right_bound + left_bound)/2;
            } else {
                left_bound = (right_bound + left_bound)/2;
            }
            test_vr = (right_bound + left_bound)/2;
        }
        return test_vr;
    }
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
        double dist = Double.parseDouble(distString);

        System.out.println(final_vr(dist, 25, 0, 150));
    }
}
