package org.usfirst.frc.team3360.robot.subsystems;

import org.usfirst.frc.team3360.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Feeder extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public final CANTalon feederMotor = RobotMap.feederMotor;
	public final CANTalon feederGingerWheel = RobotMap.feederGingerWheel;
	public final Talon feederVibrator = RobotMap.feederVibrator;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	public void feed(double feederSpeed, double gingerSpeed){
		feederMotor.set(feederSpeed);
		feederGingerWheel.set(gingerSpeed);
		
		
	}
	public void vibrate(double speed){
		feederVibrator.set(speed);
	}
	
	
	
}
