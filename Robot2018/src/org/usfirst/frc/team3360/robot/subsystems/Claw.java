/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3360.robot.subsystems;

import org.usfirst.frc.team3360.robot.RobotMap;
import org.usfirst.frc.team3360.robot.commands.ClawHandleCube;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem {	
	private final TalonSRX clawMotor = RobotMap.clawMotor;
	
	private final double grabSpeed = -1;
    private final double releaseSpeed = 1;
	
	public Claw() {
		
	}
		
	public void initDefaultCommand() {
		setDefaultCommand(new ClawHandleCube(750));
	}
	
	public void grabCube(){
		setClawSpeed(grabSpeed);
    }
	
	public void releaseCube() {
		setClawSpeed(releaseSpeed);
    }
	
	public void idle() {
		setClawSpeed(0);
    }
	
	private void setClawSpeed(double speed) {
		clawMotor.set(ControlMode.PercentOutput, speed);
    }
}

