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

public class Lift extends Subsystem {	
	private final TalonSRX liftLeftMotor = RobotMap.liftLeftMotor;
	private final TalonSRX liftRightMotor = RobotMap.liftRightMotor;

	//TODO tweak pid
	//maybe set 2 profiles up and down
    private final double moveP = 0.05;
    private final double moveI = 0;
    private final double moveD = 0;
    private final double moveF = 0;
	
    private final int pidLoopIdx = 0;
    private final int timeoutMS = 100;
    
	private boolean freeModeFlag;
	private boolean isRaise;
	
	//TODO define lift stages encoder setpoint we want 
	// lift encoder range 0-50000
	private double posAt0 = 0;
	private double posAt1 = 2000;
	private double posAt2 = 10000;
	private double posAt3 = 40000;
	
	public Lift() {
	    initLift();
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
			//TODO free mode lift control
		} else {
			//TODO rework stages
			if(copilotLevierVal <= -0.95) {
				liftLeftMotor.set(ControlMode.Position , posAt0);
				isRaise = false;
				
			} else if (copilotLevierVal >= -0.95 && copilotLevierVal <= -0.56) {
				liftLeftMotor.set(ControlMode.Position , posAt1);
				isRaise = true;
				
			} else if (copilotLevierVal >= -0.50 && copilotLevierVal <= -0.056) {
				liftLeftMotor.set(ControlMode.Position , posAt2);
				isRaise = true;
				
			} else if (copilotLevierVal >= 0 && copilotLevierVal <= 0.48) {		
				liftLeftMotor.set(ControlMode.Position , posAt3);
				isRaise = true;
				
			} else if (copilotLevierVal >= 0.54 && copilotLevierVal <= 0.98) {		
				liftLeftMotor.set(ControlMode.Position , posAt3);
				isRaise = true;
			}
			
			liftRightMotor.set(ControlMode.Follower, liftLeftMotor.getDeviceID());
		}	
	}
	
	public boolean isRaise() {
		return isRaise;
	}
	
	private void initLift() {
		liftLeftMotor.getSensorCollection().setQuadraturePosition(0, timeoutMS);
		
		liftLeftMotor.selectProfileSlot(0, pidLoopIdx);
		
		liftLeftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, pidLoopIdx, timeoutMS);
		liftLeftMotor.config_kF(pidLoopIdx, moveF, timeoutMS);
		liftLeftMotor.config_kP(pidLoopIdx, moveP, timeoutMS);
		liftLeftMotor.config_kI(pidLoopIdx, moveI, timeoutMS);
		liftLeftMotor.config_kD(pidLoopIdx, moveD, timeoutMS);
		liftLeftMotor.setInverted(false);
		liftLeftMotor.configPeakOutputForward(1, timeoutMS);
		liftLeftMotor.configPeakOutputReverse(-1, timeoutMS);
		
		liftRightMotor.set(ControlMode.Follower, liftLeftMotor.getDeviceID());
	}
}

