/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3360.robot.subsystems;

import org.usfirst.frc.team3360.robot.Robot;
import org.usfirst.frc.team3360.robot.RobotMap;
import org.usfirst.frc.team3360.robot.commands.RunTankDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class TankDrive extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	TalonSRX TankDriveR1 = RobotMap.TankDriveR1;
	TalonSRX TankDriveR2 = RobotMap.TankDriveR2;
	TalonSRX TankDriveR3 = RobotMap.TankDriveR3;
	
	TalonSRX TankDriveL1 = RobotMap.TankDriveL1;
	TalonSRX TankDriveL2 = RobotMap.TankDriveL2;
	TalonSRX TankDriveL3 = RobotMap.TankDriveL3;
	
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new RunTankDrive());
	}

	public void DriveWithJoystick() {
		
		double JoystickLeftVal = Robot.oi.getLJoystick().getRawAxis(1);
		double JoystickRightVal = Robot.oi.getRJoystick().getRawAxis(1);
		
		
		
		// set Joystick to 0 if they are between -0.1 and 0.1
		if(JoystickLeftVal > -0.1 && JoystickLeftVal < 0.1) {
			JoystickLeftVal = 0;
		}
		if(JoystickRightVal > -0.1 && JoystickRightVal < 0.1) {
			JoystickRightVal = 0;
		}
		
		
		TankDriveR1.set(ControlMode.PercentOutput, -JoystickRightVal);
		TankDriveR2.set(ControlMode.PercentOutput, -JoystickRightVal);
		TankDriveR3.set(ControlMode.PercentOutput, -JoystickRightVal);
		
		TankDriveL1.set(ControlMode.PercentOutput, JoystickLeftVal);
		TankDriveL2.set(ControlMode.PercentOutput, JoystickLeftVal);
		TankDriveL3.set(ControlMode.PercentOutput, JoystickLeftVal);
		
		
		
	}
	
	public void setDriveValue(double RightVal, double LeftVal) {
		TankDriveR1.set(ControlMode.Position, -RightVal);
		TankDriveR2.set(ControlMode.Position, -RightVal);
		TankDriveR3.set(ControlMode.Position, -RightVal);
		
		TankDriveL1.set(ControlMode.Position, -LeftVal);
		TankDriveL2.set(ControlMode.Position, -LeftVal);
		TankDriveL3.set(ControlMode.Position, -LeftVal);
	}


}
