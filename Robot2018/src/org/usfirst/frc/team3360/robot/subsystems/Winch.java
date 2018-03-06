/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3360.robot.subsystems;

import org.usfirst.frc.team3360.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Winch extends Subsystem {			
	private final TalonSRX winchLeftMotor = RobotMap.winchLeftMotor;
	private final TalonSRX winchRightMotor = RobotMap.winchRightMotor;
	
	public Winch() {
		
	}
		
	public void initDefaultCommand() {
		
	}
	
	public void setWinchSpeed(double speed) {
		winchLeftMotor.set(ControlMode.PercentOutput, speed);
		winchRightMotor.set(ControlMode.PercentOutput, speed);
	}
}

