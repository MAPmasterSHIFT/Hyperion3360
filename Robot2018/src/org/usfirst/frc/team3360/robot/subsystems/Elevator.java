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
	private final TalonSRX elevatorLeftMotor = RobotMap.elevatorLeftMotor;
	private final TalonSRX elevatorRightMotor = RobotMap.elevatorRightMotor;
	
	private boolean freeModeFlag;
	private boolean isRaise;
	
	public Elevator() {
	    
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new ElevatorRaise());
	}
	
	public void raiseWithCoJoystick(){
		double copilotLevierVal = Robot.oi.getJoystickCoPilot().getRawAxis(0);
		
		freeModeFlag = Robot.oi.getJoystickCoPilot().getRawButton(12);
		
		if(Robot.isDebugEnable()) {
			System.out.println("freeModeFlag STATE = " + freeModeFlag);
			System.out.println("copilotLevierVal: " + copilotLevierVal);
		}
		
		if(freeModeFlag) {
			elevatorLeftMotor.set(ControlMode.PercentOutput , copilotLevierVal);
			elevatorRightMotor.set(ControlMode.PercentOutput , copilotLevierVal);
		} else {
			if(copilotLevierVal <= -0.95) {
				if(Robot.isDebugEnable()) {
					System.out.println("Elevator: 0 feet ");
				}
				
				isRaise = false;
				
			} else if (copilotLevierVal >= -0.95 && copilotLevierVal <= -0.56) {
				if(Robot.isDebugEnable()) {
					System.out.println("Elevator: 2 feet ");
				}
				
				isRaise = true;
				
			} else if (copilotLevierVal >= -0.50 && copilotLevierVal <= -0.056) {
				if(Robot.isDebugEnable()) {
					System.out.println("Elevator: 4 feet" );
				}
				
				isRaise = true;
				
			} else if (copilotLevierVal >= 0 && copilotLevierVal <= 0.48) {
				if(Robot.isDebugEnable()) {
					System.out.println("Elevator: 5 feet" );
				}
				
				isRaise = true;
				
			} else if (copilotLevierVal >= 0.54 && copilotLevierVal <= 0.98) {
				if(Robot.isDebugEnable()) {
					System.out.println("Elevator: 6 feet" );
				}
				
				isRaise = true;
			}
		}	
	}
	
	
	public boolean isRaise() {
		return isRaise;
	}
	
	// TODO : elevatorWithEncoder
}

