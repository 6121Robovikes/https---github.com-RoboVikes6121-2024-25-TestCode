package frc.robot.Constants;

import static edu.wpi.first.units.Units.*;


import java.util.function.Supplier;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import frc.robot.subsystems.CommandSwerveDrivetrain;

public class LinearController {

private final ProfiledPIDController xController; 
private final ProfiledPIDController yController; 
private final ProfiledPIDController thetaController; 

private final Supplier<Pose2d> goalPoseSupplier;

private double MaxSpeed = TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); // kSpeedAt12Volts desired top speed
private double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond); // 3/4 of a rotation per second max angular velocity


    public LinearController(Supplier<Pose2d> goalPoseSupplier)  
    {
        xController =
            new ProfiledPIDController(
             1,
             0,
             0,
            new TrapezoidProfile.Constraints(MaxAngularRate, MaxSpeed)); 
    
        yController =
            new ProfiledPIDController(
             1,
             0,
             0,
            new TrapezoidProfile.Constraints(MaxAngularRate, MaxSpeed)); 
    
        thetaController =
            new ProfiledPIDController(
             1,
             0,
             0,
            new TrapezoidProfile.Constraints(MaxAngularRate, MaxSpeed)); 

        this.goalPoseSupplier = goalPoseSupplier;
    }

    /**Calculates something idk*/
    public ChassisSpeeds Update(CommandSwerveDrivetrain drivetrain) 
    {
        double xOutput = xController.calculate(drivetrain.getPose().getX(), goalPoseSupplier.get().getX()); 
        double yOutput = yController.calculate(drivetrain.getPose().getY(), goalPoseSupplier.get().getY()); 
        double thetaOutput = thetaController.calculate(drivetrain.getPose().getRotation().getRadians(), goalPoseSupplier.get().getRotation().getRadians());
        
        ChassisSpeeds speeds = new ChassisSpeeds(xOutput, yOutput, thetaOutput);
        return speeds;
    }
}
