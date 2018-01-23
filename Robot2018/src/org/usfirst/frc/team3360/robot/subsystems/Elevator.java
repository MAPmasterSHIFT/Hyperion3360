/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3360.robot.subsystems;

import org.usfirst.frc.team3360.robot.Robot;
import org.usfirst.frc.team3360.robot.RobotMap;
import org.usfirst.frc.team3360.robot.commands.ElevatorRaise;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {	
	TalonSRX elevatormotor = RobotMap.elevatorMotor;
	
	public boolean freeModeFlag;
	
	public Elevator() {
	    
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new ElevatorRaise());
	}
	
	public void raiseWithCoJoystick(){
		double copilotLevierVal = Robot.oi.getCopilotJoystick().getRawAxis(0);
		
		freeModeFlag = Robot.oi.getCopilotJoystick().getRawButton(12);
		
		if(Robot.isDebugEnable()) {
			System.out.println("freeModeFlag STATE = " + freeModeFlag);
			System.out.println("copilotLevierVal: " + copilotLevierVal);
		}
		
		if(freeModeFlag) {
			elevatormotor.set(ControlMode.PercentOutput , copilotLevierVal);
		} else {
			if(copilotLevierVal <= -0.95) {
				elevatormotor.set(ControlMode.PercentOutput, 0);
				
				if(Robot.isDebugEnable()) {
					System.out.println("Elevator: 0 feet ");
				}
			} else if (copilotLevierVal >= -0.95 && copilotLevierVal <= -0.56) {
				//2 feet
				elevatormotor.set(ControlMode.PercentOutput, 0.2);
				
				if(Robot.isDebugEnable()) {
					System.out.println("Elevator: 2 feet ");
				}
			} else if (copilotLevierVal >= -0.50 && copilotLevierVal <= -0.056) {
				//4 feet
				elevatormotor.set(ControlMode.PercentOutput, 0.4 );
				
				if(Robot.isDebugEnable()) {
					System.out.println("Elevator: 4 feet" );
				}
			} else if (copilotLevierVal >= 0 && copilotLevierVal <= 0.48) {
				//5 feet
				elevatormotor.set(ControlMode.PercentOutput, 0.6);
				
				if(Robot.isDebugEnable()) {
					System.out.println("Elevator: 5 feet" );
				}
			} else if (copilotLevierVal >= 0.54 && copilotLevierVal <= 0.98) {
				//6 feet
				elevatormotor.set(ControlMode.PercentOutput, 1);
				
				if(Robot.isDebugEnable()) {
					System.out.println("Elevator: 6 feet" );
				}
			}
		}	
	}
	
	// TODO : elevatorWithEncoder
	public double encoderTofeet(double val) {	
		return val/72;
	}
}

