package frc.robot.Utils;

public class Utils {
    

    public static double applyDeadband(double value, double deadband){
        if (Math.abs(value) < deadband){
            return 0.0;
        } else {
            return value;
        }
    }
}
