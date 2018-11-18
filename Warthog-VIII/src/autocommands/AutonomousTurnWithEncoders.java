// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package autocommands;

import org.usfirst.frc.team3360.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousTurnWithEncoders extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
	double deg;
	long startTimeMs;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public AutonomousTurnWithEncoders(double degrees) {
deg = degrees;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    	requires(Robot.jetDrive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTimeMs = System.currentTimeMillis();
    	Robot.jetDrive.setControlMode(1);
    	Robot.jetDrive.resetEncoderDistance();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("setPoint: " + deg);
    	double value = Robot.jetDrive.turnDegrees(deg);
    	 Robot.jetDrive.driveWithEncoders(value, -value);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.jetDrive.isAtSetPoint() && 
        		System.currentTimeMillis() - startTimeMs > 500); //||
        		//System.currentTimeMillis() - startTimeMs > 4500;
    }

    // Called once after isFinished returns true
    protected void end() {
    	exit();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	exit();
    }
    private void exit(){
    	System.out.println("Exit auto rotate");
    	Robot.jetDrive.resetEncoderDistance();
    }
}
