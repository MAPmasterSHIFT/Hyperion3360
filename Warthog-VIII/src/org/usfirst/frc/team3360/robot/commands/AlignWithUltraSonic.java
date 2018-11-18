package org.usfirst.frc.team3360.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3360.robot.Robot;

/**
 *
 */
public class AlignWithUltraSonic extends Command {
	public AlignWithUltraSonic() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.jetDrive);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.jetDrive.setControlMode(1);
		Robot.jetDrive.resetEncoderDistance();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		System.out.println("angle req :  " + Robot.jetDrive.corrAngle());
		double value = Robot.jetDrive.turnDegrees(Robot.jetDrive.corrAngle());
		Robot.jetDrive.driveWithEncoders(value, -value);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return Robot.jetDrive.isAtSetPoint();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.jetDrive.resetEncoderDistance();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		Robot.jetDrive.resetEncoderDistance();
	}
}
