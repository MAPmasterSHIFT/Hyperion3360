/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3360.robot.subsystems;
import org.usfirst.frc.team3360.robot.Robot;
import org.usfirst.frc.team3360.robot.RobotMap;
import org.usfirst.frc.team3360.robot.commands.Climb;
import org.usfirst.frc.team3360.robot.commands.RunGrabber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Grabber extends Subsystem {	

	public boolean buttonGrabIn;
	public boolean buttonGrabOut;
	public boolean elevatorIsRaise = Robot.elevator.isRaise;
	
	TalonSRX grabRightMotor = RobotMap.grabRightMotor;
	TalonSRX grabLeftMotor = RobotMap.grabLeftMotor;
	TalonSRX frontGrabLMotor = RobotMap.frontGrabLMotor;
	TalonSRX frontGrabRMotor = RobotMap.frontGrabRMotor;
	
	public Grabber() {
		
	    
	}
		
	public void initDefaultCommand() {
		setDefaultCommand(new RunGrabber());
	}
	
	public void Grab() {
		buttonGrabIn = Robot.oi.getLJoystick().getRawButton(1);
		buttonGrabOut = Robot.oi.getRJoystick().getRawButton(1);
		// If Left and Right Button are both pressed at the same time set motor to 0
		if (buttonGrabIn && buttonGrabOut == false) {
			buttonGrabOut = false;
			
			grabRightMotor.set(ControlMode.PercentOutput, 1);
			grabLeftMotor.set(ControlMode.PercentOutput, 1);
			
			frontGrabLMotor.set(ControlMode.PercentOutput, 1);
			frontGrabRMotor.set(ControlMode.PercentOutput, 1);
			
		}else if (buttonGrabIn == false && buttonGrabOut) {
			buttonGrabIn = false;
			
			grabRightMotor.set(ControlMode.PercentOutput, -1);
			grabLeftMotor.set(ControlMode.PercentOutput, -1);
			
		}else {
			
			grabRightMotor.set(ControlMode.PercentOutput, 0);
			grabLeftMotor.set(ControlMode.PercentOutput, 0);
			
			frontGrabLMotor.set(ControlMode.PercentOutput, 0);
			frontGrabRMotor.set(ControlMode.PercentOutput, 0);
		}
			
	}
	
	
	
}

