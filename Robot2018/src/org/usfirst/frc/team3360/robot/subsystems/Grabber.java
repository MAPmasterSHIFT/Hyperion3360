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

public class Grabber extends Subsystem {	
	private final TalonSRX grabberLeftMotor = RobotMap.grabberLeftMotor;
	private final TalonSRX grabberRightMotor = RobotMap.grabberRightMotor;
	
	private final double grabSpeed = -1;
    private final double releaseSpeed = 1;
	
	public Grabber() {
		
	}
		
	public void initDefaultCommand() {
		
	}
	
	public void grabCube(){
		setGrabberSpeed(grabSpeed);
    }
	
	public void releaseCube() {
		setGrabberSpeed(releaseSpeed);
    }
	
	public void grabberIdle() {
		setGrabberSpeed(0);
    }
	
	private void setGrabberSpeed(double speed) {
		grabberLeftMotor.set(ControlMode.PercentOutput, speed);
		grabberRightMotor.set(ControlMode.PercentOutput, speed);
    }
}

