package org.usfirst.frc.team3360.robot.subsystems;

import org.usfirst.frc.team3360.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public final CANTalon intakeMotor = RobotMap.intakeMotor;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	public void intake(double speed){
		intakeMotor.set(speed);
	}
}
