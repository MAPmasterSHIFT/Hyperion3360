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

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {	
		public boolean climbButton;
		
		TalonSRX climbMotorRight = RobotMap.climbMotorRight;
		TalonSRX climbMotorLeft = RobotMap.climbMotorLeft;
	
	public Climber() {
		
	    
	}
		
	public void initDefaultCommand() {
		setDefaultCommand(new Climb());
	}
	
	public void robotClimb(){
		climbButton = Robot.oi.getCopilotJoystick().getRawButton(7);
		if(climbButton) {
			//TODO : Make it work with encoder
			climbMotorRight.set(ControlMode.PercentOutput, -1);
			climbMotorLeft.set(ControlMode.PercentOutput, -1);
		}else {
			climbMotorRight.set(ControlMode.PercentOutput, 0);
			climbMotorLeft.set(ControlMode.PercentOutput, 0);
		}
			
	}
	

	
}

