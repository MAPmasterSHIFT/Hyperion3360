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
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {	
	public static final int AUTO_MOVE_MODE = 1;
	public static final int TELEOP_MODE = 1;
	
	private final TalonSRX elevatorLeftMotor = RobotMap.elevatorLeftMotor;
	private final TalonSRX elevatorRightMotor = RobotMap.elevatorRightMotor;

	//Je sais pas si faut changer les numero ligne 27 a 38
    private final double moveP = 0.5;
    private final double moveI = 0;
    private final double moveD = 3;
    private final double moveF = 0;
	
    private final double vBusP = 0.01;
    private final double vBusI = 25;
    private final double vBusD = 0;
    private final double vBusF = 1;
	
    private final int pidLoopIdx = 0;
    private final int timeoutMS = 10;
    
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
	
	public void setControlMode(final int mode) {
		if(mode == AUTO_MOVE_MODE){
			elevatorLeftMotor.selectProfileSlot(0, pidLoopIdx);
			elevatorRightMotor.selectProfileSlot(0, pidLoopIdx);
			
			elevatorLeftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, pidLoopIdx, timeoutMS);
			elevatorLeftMotor.config_kF(pidLoopIdx, moveF, timeoutMS);
			elevatorLeftMotor.config_kP(pidLoopIdx, moveP, timeoutMS);
			elevatorLeftMotor.config_kI(pidLoopIdx, moveI, timeoutMS);
			elevatorLeftMotor.config_kD(pidLoopIdx, moveD, timeoutMS);
			elevatorLeftMotor.setInverted(false);
			elevatorLeftMotor.configPeakOutputForward(0.6, timeoutMS);
			elevatorLeftMotor.configPeakOutputReverse(-0.6, timeoutMS);
			
			elevatorLeftMotor.set(ControlMode.Follower, elevatorRightMotor.getDeviceID());
		}
		else if (mode == TELEOP_MODE) {
			elevatorLeftMotor.selectProfileSlot(1, pidLoopIdx);
			elevatorRightMotor.selectProfileSlot(1, pidLoopIdx);

			elevatorLeftMotor.config_kF(pidLoopIdx, vBusF, timeoutMS);
			elevatorLeftMotor.config_kP(pidLoopIdx, vBusP, timeoutMS);
			elevatorLeftMotor.config_kI(pidLoopIdx, vBusI, timeoutMS);
			elevatorLeftMotor.config_kD(pidLoopIdx, vBusD, timeoutMS);
    		
			elevatorRightMotor.config_kF(pidLoopIdx, vBusF, timeoutMS);
			elevatorRightMotor.config_kP(pidLoopIdx, vBusP, timeoutMS);
			elevatorRightMotor.config_kI(pidLoopIdx, vBusI, timeoutMS);
			elevatorRightMotor.config_kD(pidLoopIdx, vBusD, timeoutMS);
		}
	}
	
	public boolean isRaise() {
		return isRaise;
	}
	
	// TODO : elevatorWithEncoder
}

