package org.usfirst.frc.team3360.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3360.robot.Robot;

/**
 *
 */
public class FeedBalls extends Command {
	boolean b;
	double initTime;	//Moment auquel le wait commence
	public FeedBalls(boolean state) {
		
		// Use requires() here to declare subsystem dependencies
		requires(Robot.beltFedMG);
		b = state;
		}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		initTime = System.currentTimeMillis();
		
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		//Robot.beltFedMG.feed(0.75, 0.78);
		if(b){
		Robot.beltFedMG.feed(0.8, 1);
		if(System.currentTimeMillis()-initTime >1000){
			Robot.beltFedMG.vibrate((-Robot.oi.getCoJoystick().getRawAxis(6) + 1)/2);
		}
		
		else{
			//Robot.beltFedMG.vibrate((-Robot.oi.getCoJoystick().getRawAxis(6) + 1)/2);
			Robot.beltFedMG.vibrate(1);
		}
		if (System.currentTimeMillis()-initTime > 2000){
			initTime = System.currentTimeMillis();
		}
		}
		else{
			Robot.beltFedMG.feed(0, 0);
			Robot.beltFedMG.vibrate(0);
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
		Robot.beltFedMG.feed(0, 0);
		Robot.beltFedMG.vibrate(0);
	}
}
