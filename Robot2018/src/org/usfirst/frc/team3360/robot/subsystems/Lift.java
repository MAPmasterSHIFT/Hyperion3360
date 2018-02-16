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
    private final double moveP = 0.1;
    private final double moveI = 0;
    private final double moveD = 0;
    private final double moveF = 0;
	
    private final int pidLoopIdx = 0;
    private final int timeoutMS = 100;
    
	private boolean freeModeFlag;
	private boolean isRaise;
	
	//TODO define lift stages encoder setpoint we want 
	// lift encoder range 0-50000
	public double posAt0 = 0;
	public double posAt1 = -10000;
	public double posAt2 = -20000;
	private double posAt3 = -45000;
	
	
	public Lift() {
	    initLift();
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new ElevatorRaise());
	}
	
	public void raiseWithCoJoystick(){
		double copilotLevierVal = Robot.oi.getJoystickCoPilot().getRawAxis(0);
		
		
		boolean redButton = Robot.oi.getJoystickCoPilot().getRawButton(2);
		boolean yellowButton = Robot.oi.getJoystickCoPilot().getRawButton(3);
		boolean greenButton = Robot.oi.getJoystickCoPilot().getRawButton(1);
		boolean blueButton = Robot.oi.getJoystickCoPilot().getRawButton(4);
		boolean whiteButton = Robot.oi.getJoystickCoPilot().getRawButton(5);
		
		freeModeFlag = Robot.oi.getJoystickCoPilot().getRawButton(12);
		
		if(Robot.isDebugEnable()) {
			System.out.println("freeModeFlag STATE = " + freeModeFlag);
			System.out.println("copilotLevierVal: " + copilotLevierVal);
		}
		
		if(!redButton && !greenButton && !yellowButton && !blueButton) {
			//TODO free mode lift control
			double val = raiseValue();
			
			if(copilotLevierVal <= -0.95) {
				val = posAt0;
				isRaise = false;
			}
			
			liftLeftMotor.set(ControlMode.Position, val);
			
			if(val == posAt2 && val == posAt3) {
				isRaise = true;
			}else {
				isRaise = false;
			}
			
			System.out.println("VOLTAGE OUT : " + liftLeftMotor.getMotorOutputVoltage());
			System.out.println("POSITION : " + liftLeftMotor.getSensorCollection().getQuadraturePosition());
		} else {
			//TODO rework stages
			if(whiteButton) {
				liftLeftMotor.set(ControlMode.Position , posAt0);
				isRaise = false;
			}else if (blueButton) {
				//copilotLevierVal >= -0.95 && copilotLevierVal <= -0.56
				liftLeftMotor.set(ControlMode.Position , posAt1);
				isRaise = true;
				
			} else if (greenButton) {
				//copilotLevierVal >= -0.50 && copilotLevierVal <= -0.56
				liftLeftMotor.set(ControlMode.Position , posAt2);
				isRaise = true;
				
			} else if (yellowButton) {	
				//copilotLevierVal >= 0 && copilotLevierVal <= 0.48
				liftLeftMotor.set(ControlMode.Position , posAt3);
				isRaise = true;
				
			} else if (redButton) {	
				//copilotLevierVal >= 0.54 && copilotLevierVal <= 0.98
				liftLeftMotor.set(ControlMode.Position , posAt3);
				isRaise = true;
			}
			
			liftRightMotor.set(ControlMode.Follower, liftLeftMotor.getDeviceID());
		}	
	}
	
	public boolean isRaise() {
		return isRaise;
	}
	
	private double raiseValue() {
		double val = (Robot.oi.getJoystickCoPilot().getRawAxis(0) + 1)/2;
		val = val*-47000;
		System.out.println("SETPOINT : " + val);
		return val;
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

