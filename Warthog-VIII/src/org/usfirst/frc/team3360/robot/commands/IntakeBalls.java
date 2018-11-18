package org.usfirst.frc.team3360.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3360.robot.Robot;

/**
 *
 */
public class IntakeBalls extends Command {
	boolean b;
	public IntakeBalls(boolean reverse) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.turbine);
		b = reverse;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.turbine.intake(1);
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
	
	private void exit(){
		Robot.turbine.intake(0);
	}
}
