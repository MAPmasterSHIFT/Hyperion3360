package autocommands;

import org.usfirst.frc.team3360.robot.Robot;
import org.usfirst.frc.team3360.robot.subsystems.TankDrive;

import edu.wpi.first.wpilibj.command.Command;

public class autoDriveWithEncoder extends Command {
 //40 pouce 
 //120 pouce
	double distanceR;
	double distanceL;

    public autoDriveWithEncoder(double rVal, double lVal){
    	requires(Robot.tankDrive);
    	distanceR = rVal;
    	distanceL = lVal;
    }
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.tankDrive.setControlMode(2);
		Robot.tankDrive.resetEncoderDistance();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	  Robot.tankDrive.driveWithEncoders(distanceR, distanceL);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		exit();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		exit();
	}
	public void exit() {
	 Robot.tankDrive.driveWithEncoders(0, 0);
	}
 
 
}
