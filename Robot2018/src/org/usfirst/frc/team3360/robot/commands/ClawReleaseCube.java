/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3360.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3360.robot.Robot;

public class ClawReleaseCube extends Command {
	public ClawReleaseCube() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.claw);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.claw.idle();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.claw.releaseCube();
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
