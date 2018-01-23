/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3360.robot.subsystems;

import org.usfirst.frc.team3360.robot.Robot;
import org.usfirst.frc.team3360.robot.RobotMap;
import org.usfirst.frc.team3360.robot.commands.RunElevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Elevator extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	TalonSRX elevatormotor = RobotMap.elevatorMotor;
	//Command FreeMode;
	public boolean freemode;
	
	
	
	
	public void initDefaultCommand() {
		setDefaultCommand(new RunElevator());
	}
	
	public void RaiseWithCoJoystick(){
		double CopilotLevierVal = Robot.oi.getCopilotJoystick().getRawAxis(0);
		freemode = Robot.oi.getCopilotJoystick().getRawButton(12);
		//System.out.println(freemode + "STATE BOUTON FREEMODE");
		//FreeMode = false;
		//JoystickButton SideButton = Robot.oi.getButtonSideJoystick().whileHeld(FreeMode());
		
		
		//elevatormotor.set(ControlMode.PercentOutput , CopilotLevierVal);
		
		if(freemode) {
			elevatormotor.set(ControlMode.PercentOutput , CopilotLevierVal);
		}
		else {
		if(CopilotLevierVal <= -0.95) {
			elevatormotor.set(ControlMode.PercentOutput, 0);
			System.out.println("Elevator: 0 feet ");
		}else if (CopilotLevierVal >= -0.95 && CopilotLevierVal <= -0.56) {
			//2 feet
			elevatormotor.set(ControlMode.PercentOutput, 0.2);
			System.out.println("Elevator: 2 feet ");
		}else if (CopilotLevierVal >= -0.50 && CopilotLevierVal <= -0.056) {
			//4 feet
			elevatormotor.set(ControlMode.PercentOutput, 0.4 );
			System.out.println("Elevator: 4 feet" );
		}else if (CopilotLevierVal >= 0 && CopilotLevierVal <= 0.48) {
			//5 feet
			elevatormotor.set(ControlMode.PercentOutput, 0.6);
			System.out.println("Elevator: 5 feet" );
		}else if (CopilotLevierVal >= 0.54 && CopilotLevierVal <= 0.98) {
			//6 feet
			elevatormotor.set(ControlMode.PercentOutput, 1);
			System.out.println("Elevator: 6 feet" );
		}
		}
		
		


		
			//System.out.println("CopilotLevierVal: " + CopilotLevierVal);
	
	}
	
	public double encoderTofeet(double val) {
		
	return val/72;
	}
	
	
	
	
}

