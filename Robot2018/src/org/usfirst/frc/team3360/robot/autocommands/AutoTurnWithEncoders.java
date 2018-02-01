package org.usfirst.frc.team3360.robot.autocommands;

import org.usfirst.frc.team3360.robot.Robot;
import org.usfirst.frc.team3360.robot.subsystems.TankDrive;

import edu.wpi.first.wpilibj.command.Command;

public class AutoTurnWithEncoders extends Command {

	double deg;

	public AutoTurnWithEncoders(double degrees) {
		requires(Robot.tankDrive);
		deg = degrees;
	}
    
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.tankDrive.setControlMode(TankDrive.AUTO_ROTATE_MODE);
		Robot.tankDrive.resetEncoderDistance();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double value = Robot.tankDrive.turnDegrees(deg);
   	 	Robot.tankDrive.driveWithEncoders(value, -value);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return Robot.tankDrive.isAtSetPoint();
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
