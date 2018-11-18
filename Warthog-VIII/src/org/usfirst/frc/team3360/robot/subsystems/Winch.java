package org.usfirst.frc.team3360.robot.subsystems;

import org.usfirst.frc.team3360.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Winch extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public final CANTalon winchLeftMotor = RobotMap.winchLeftMotor;
	public final CANTalon winchRightMotor = RobotMap.winchRightMotor;
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	public void setWinchSpeed(double speed){
		winchLeftMotor.set(-speed);
		winchRightMotor.set(-speed);
	}
}
