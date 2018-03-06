/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3360.robot.autocommands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3360.robot.Robot;

public class ElevatorRaiseAutoDelay extends Command {
	double startTimeMs;
	double delayMs;
	double dheight;
	boolean readyToEnd = false;
	public ElevatorRaiseAutoDelay(double delay, double height) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.lift);
		delayMs = delay;
		dheight = height;
		
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		startTimeMs= System.currentTimeMillis();
		
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if(System.currentTimeMillis() > startTimeMs+delayMs) {
		Robot.lift.setLiftHeight(dheight);
		readyToEnd=true;
		}
		
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return readyToEnd;
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
		System.out.println("Exit auto Lift command");
	}
}
