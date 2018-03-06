/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3360.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3360.robot.Robot;

public class ClawHandleCube extends Command {
	double LastTimeMs;
	double pulseTimeMs;
	public ClawHandleCube(double dpulseTimeMs) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.claw);
		pulseTimeMs = dpulseTimeMs;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.claw.idle();
		LastTimeMs = System.currentTimeMillis();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		System.out.println("EXECUTING HANDLE");
		if(System.currentTimeMillis() - LastTimeMs > pulseTimeMs  && Robot.lift.isRaise()) {
		Robot.claw.grabCube();
		if(System.currentTimeMillis() - LastTimeMs > 1.20*pulseTimeMs) {
		LastTimeMs = System.currentTimeMillis();
		}
		}else {
		Robot.claw.idle();
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
	
	public void exit() {
		Robot.claw.idle();
	}
}
