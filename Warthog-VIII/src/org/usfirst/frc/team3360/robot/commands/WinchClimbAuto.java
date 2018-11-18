package org.usfirst.frc.team3360.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3360.robot.Robot;

/**
 *
 */
public class WinchClimbAuto extends Command {
	double d;
	public WinchClimbAuto(double speed) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.GForce);
		d = speed;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.GForce.setWinchSpeed(d);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return true;
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
	
	private void exit(){
		//Robot.GForce.setWinchSpeed(0);
	}
}
