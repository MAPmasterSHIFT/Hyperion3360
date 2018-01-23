/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3360.robot.subsystems;

import org.usfirst.frc.team3360.robot.Robot;
import org.usfirst.frc.team3360.robot.RobotMap;
import org.usfirst.frc.team3360.robot.commands.TankDriveJoystickDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class TankDrive extends Subsystem {
	TalonSRX tankDriveL1Motor = RobotMap.tankDriveL1Motor;
	TalonSRX tankDriveL2Motor = RobotMap.tankDriveL2Motor;
	TalonSRX tankDriveL3Motor = RobotMap.tankDriveL3Motor;
	
	TalonSRX tankDriveR1Motor = RobotMap.tankDriveR1Motor;
	TalonSRX tankDriveR2Motor = RobotMap.tankDriveR2Motor;
	TalonSRX tankDriveR3Motor = RobotMap.tankDriveR3Motor;
	
	public TankDrive() {
    
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new TankDriveJoystickDrive());
	}

	public void driveWithJoysticks() {
		double JoystickLeftVal = Robot.oi.getLJoystick().getRawAxis(1);
		double JoystickRightVal = Robot.oi.getRJoystick().getRawAxis(1);
   	 
		// Configure Joystick deathzone : set Joystick to 0 if axis value is between -0.1 and 0.1
		if(JoystickLeftVal > -0.1 && JoystickLeftVal < 0.1) {
			JoystickLeftVal = 0;
		}
		if(JoystickRightVal > -0.1 && JoystickRightVal < 0.1) {
			JoystickRightVal = 0;
		}
		
		tankDriveL1Motor.set(ControlMode.PercentOutput, JoystickLeftVal);
		tankDriveL2Motor.set(ControlMode.PercentOutput, JoystickLeftVal);
		tankDriveL3Motor.set(ControlMode.PercentOutput, JoystickLeftVal);
		
		tankDriveR1Motor.set(ControlMode.PercentOutput, -JoystickRightVal);
		tankDriveR2Motor.set(ControlMode.PercentOutput, -JoystickRightVal);
		tankDriveR3Motor.set(ControlMode.PercentOutput, -JoystickRightVal);
	}
	
	public void setDriveValue(double RightVal, double LeftVal) {
		tankDriveL1Motor.set(ControlMode.Position, LeftVal);
		tankDriveL2Motor.set(ControlMode.Position, LeftVal);
		tankDriveL3Motor.set(ControlMode.Position, LeftVal);
		
		tankDriveR1Motor.set(ControlMode.Position, RightVal);
		tankDriveR2Motor.set(ControlMode.Position, RightVal);
		tankDriveR3Motor.set(ControlMode.Position, RightVal);
	}
	
	// TODO : driveWithEncoders
}
