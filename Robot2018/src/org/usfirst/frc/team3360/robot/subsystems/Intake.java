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

public class Intake extends Subsystem {	
	private final TalonSRX intakeLeftMotor = RobotMap.intakeLeftMotor;
	private final TalonSRX intakeRightMotor = RobotMap.intakeRightMotor;
	
	private final double grabSpeed = -0.7;
    private final double releaseSpeed = 0.7;
	
	public Intake() {
		
	}
		
	public void initDefaultCommand() {
		
	}
	
	public void grabCube(){
		setIntakeSpeed(grabSpeed);
    }
	
	public void releaseCube() {
		setIntakeSpeed(releaseSpeed);
    }
	
	public void idle() {
		setIntakeSpeed(0);
    }
	
	private void setIntakeSpeed(double speed) {
		intakeLeftMotor.set(ControlMode.PercentOutput, speed);
		intakeRightMotor.set(ControlMode.PercentOutput, -speed);
    }
}

