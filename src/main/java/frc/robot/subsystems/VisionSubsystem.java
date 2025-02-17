package frc.robot.subsystems;


import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.LimelightHelpers;

public class VisionSubsystem extends SubsystemBase {
    public VisionSubsystem() {
        // Initialize the Limelight here
    }

    public double getTargetAngle() {
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0.0);
    }

    public double getTargetTY() {
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0.0);
    }

    public double getTargetTX() {
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0.0);
    }
// milliseconds
    public double getLatencyMS() {
        double Latencytl = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tl").getDouble(0.0);
        double Latencycl = NetworkTableInstance.getDefault().getTable("limelight").getEntry("cl").getDouble(0.0);
        return Latencytl + Latencycl;
    }

    public Pose2d get2DPose() {
     double[] poseArray = NetworkTableInstance.getDefault().getTable("limelight").getEntry("botpose_orb_wpiblue").getDoubleArray(new double[6]);
    return new Pose2d(poseArray[0], poseArray[1], new Rotation2d(poseArray[5]));
    }

    public int getTagCount() {
        double[] poseArray = NetworkTableInstance.getDefault().getTable("limelight").getEntry("botpose_orb_wpiblue").getDoubleArray(new double[6]);
       return(int) poseArray[7]; 
       }
}


