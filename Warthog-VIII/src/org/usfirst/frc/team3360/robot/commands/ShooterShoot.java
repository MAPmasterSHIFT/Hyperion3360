package org.usfirst.frc.team3360.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3360.robot.Robot;

/**
 *
 */
public class ShooterShoot extends Command {
	boolean b;
	public ShooterShoot(boolean state) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.A10Thunderbolt);
		b = state;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.A10Thunderbolt.initPID();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if(b){
		Robot.A10Thunderbolt.setShooterSpeed(39000, true);
		}
		else{
			Robot.A10Thunderbolt.setShooterSpeed(0, false);
		}
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
		Robot.A10Thunderbolt.setShooterSpeed(0, false);
	}
}
