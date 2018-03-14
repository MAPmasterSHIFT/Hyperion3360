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

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {	
	private final TalonSRX intakeLeftMotor = RobotMap.intakeLeftMotor;
	private final TalonSRX intakeRightMotor = RobotMap.intakeRightMotor;
	private final DigitalInput intakeLLimit = new DigitalInput(6);
	private final DigitalInput intakeRLimit = new DigitalInput(7);
	
	private final double grabSpeed = -1;
    private final double releaseSpeed = 1;
	
	public Intake() {
		
	}
		
	public void initDefaultCommand() {
		
	}
	
	public void grabCube(){
		setIntakeSpeed(grabSpeed);
    }
	
	public void grabCube(double speedLeft, double speedRight){
		System.out.println("LeftVal: " + intakeLLimit.get());
		System.out.println("RightVal: " + intakeRLimit.get());
		if(!intakeLLimit.get()) {
			speedLeft = -speedLeft *0.5;
		}
		else if(!intakeRLimit.get()) {
			speedRight = -speedRight * 0.5;
		}
		
		setIntakeSpeed(speedLeft, speedRight);
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
	
	private void setIntakeSpeed(double speedLeft, double speedRight) {
		intakeLeftMotor.set(ControlMode.PercentOutput, speedLeft);
		intakeRightMotor.set(ControlMode.PercentOutput, -speedRight);
    }
}

