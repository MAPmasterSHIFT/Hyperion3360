/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3360.robot.subsystems;

import org.usfirst.frc.team3360.robot.Robot;
import org.usfirst.frc.team3360.robot.RobotMap;
import org.usfirst.frc.team3360.robot.commands.TankDriveJoystickDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class TankDrive extends Subsystem {
	public static final int AUTO_ROTATE_MODE = 1;
	public static final int AUTO_MOVE_MODE = 2;
	public static final int TELEOP_MODE = 3;
	
	private final TalonSRX tankDriveL1Motor = RobotMap.tankDriveL1Motor;
	private final TalonSRX tankDriveL2Motor = RobotMap.tankDriveL2Motor;
	private final TalonSRX tankDriveL3Motor = RobotMap.tankDriveL3Motor;
	
	private final TalonSRX tankDriveR1Motor = RobotMap.tankDriveR1Motor;
	private final TalonSRX tankDriveR2Motor = RobotMap.tankDriveR2Motor;
	private final TalonSRX tankDriveR3Motor = RobotMap.tankDriveR3Motor;
	
	private final double positionP = 1;
    private final double positionI = 0;
    private final double positionD = 0;
    private final double positionF = 0;
    
    private final double moveP = 0.55;
    private final double moveI = 0;
    private final double moveD = 5;
    private final double moveF = 0;
    
    private final double vBusP = 0.01;
    private final double vBusI = 25;
    private final double vBusD = 0;
    private final double vBusF = 1;
    
    private final int pidLoopIdx = 0;
    private final int timeoutMS = 100;
    
    public double joystickLeftVal = Robot.oi.getJoystickLeft().getRawAxis(1);
	public double joystickRightVal = -Robot.oi.getJoystickRight().getRawAxis(1);
	
	public TankDrive() {
		
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new TankDriveJoystickDrive());
	}

	public void driveWithJoysticks() {

		// Configure Joystick deathzone : set Joystick to 0 if axis value is between -0.1 and 0.1
		if(joystickLeftVal > -0.1 && joystickLeftVal < 0.1) {
			joystickLeftVal = 0;
		}
		if(joystickRightVal > -0.1 && joystickRightVal < 0.1) {
			joystickRightVal = 0;
		}
		
		if(Robot.lift.isRaise()) {
			joystickLeftVal = joystickLeftVal/2;
			joystickRightVal = joystickRightVal/2;
		}
		
		
		
		tankDriveL1Motor.set(ControlMode.PercentOutput, joystickLeftVal);
		tankDriveL2Motor.set(ControlMode.PercentOutput, joystickLeftVal);
		tankDriveL3Motor.set(ControlMode.PercentOutput, joystickLeftVal);
		
		tankDriveR1Motor.set(ControlMode.PercentOutput, joystickRightVal);
		tankDriveR2Motor.set(ControlMode.PercentOutput, joystickRightVal);
		tankDriveR3Motor.set(ControlMode.PercentOutput, joystickRightVal);
		
		if(Robot.isDebugEnable()) {
			System.out.println("positionLeft :  " + tankDriveL1Motor.getSensorCollection().getQuadraturePosition());
	    	System.out.println("positionRight :  " + tankDriveR1Motor.getSensorCollection().getQuadraturePosition());
		}
	}
	
	public void setDriveValue(double rightVal, double leftVal) {
		tankDriveL1Motor.set(ControlMode.PercentOutput, leftVal);
		tankDriveL2Motor.set(ControlMode.PercentOutput, leftVal);
		tankDriveL3Motor.set(ControlMode.PercentOutput, leftVal);
		
		tankDriveR1Motor.set(ControlMode.PercentOutput, rightVal);
		tankDriveR2Motor.set(ControlMode.PercentOutput, rightVal);
		tankDriveR3Motor.set(ControlMode.PercentOutput, rightVal);
	}
	
	public void driveWithEncoders(double distanceRight, double distanceLeft){
		if(Robot.isDebugEnable()) {
			System.out.println("positionLeft :  " + tankDriveL1Motor.getSensorCollection().getQuadraturePosition() + "   setPointLeft :  " + InchesToEncoders(-distanceLeft));
	    	System.out.println("positionRight :  " + tankDriveR1Motor.getSensorCollection().getQuadraturePosition() + "   setPointRight :  " + InchesToEncoders(distanceRight));
		}
		
		tankDriveL1Motor.set(ControlMode.Position, InchesToEncoders(-distanceLeft));
		tankDriveR1Motor.set(ControlMode.Position, InchesToEncoders(distanceRight));
		
		tankDriveR2Motor.set(ControlMode.Follower, tankDriveR1Motor.getDeviceID());
		tankDriveR3Motor.set(ControlMode.Follower, tankDriveR1Motor.getDeviceID());
		tankDriveL2Motor.set(ControlMode.Follower, tankDriveL1Motor.getDeviceID());
		tankDriveL3Motor.set(ControlMode.Follower, tankDriveL1Motor.getDeviceID());
	}
	
    public void resetEncoderDistance() {
    	if(Robot.isDebugEnable()) {
    		System.out.println("Resetting encoders");
    	}
    	
    	tankDriveL1Motor.getSensorCollection().setQuadraturePosition(0, timeoutMS);
    	tankDriveR1Motor.getSensorCollection().setQuadraturePosition(0, timeoutMS);
    }
    
    // returns true when it is done driving
    public boolean isAtSetPoint() {
    	if(Robot.isDebugEnable()) {
    		System.out.println("Velocity: " + tankDriveL1Motor.getSensorCollection().getQuadratureVelocity());
    	}
    	
    	return tankDriveL1Motor.getSensorCollection().getQuadratureVelocity() < 40 && tankDriveL1Motor.getSensorCollection().getQuadratureVelocity() > -40 && 
    			tankDriveR1Motor.getSensorCollection().getQuadratureVelocity() < 40 && tankDriveR1Motor.getSensorCollection().getQuadratureVelocity() > -40;		
    }
    
    // Converts requested drive values to Encoder values
    public double InchesToEncoders(double val) {
    	return val*40;
    }
    
    // Converts a requested turn angle to drive values
    public double turnDegrees(double val) {
    	return val * 0.28;
    }
    
	public void setControlMode(final int mode) {
		if(mode == AUTO_ROTATE_MODE) {
			tankDriveL1Motor.selectProfileSlot(0, pidLoopIdx);
    		tankDriveR1Motor.selectProfileSlot(0, pidLoopIdx);
    		
    		tankDriveL1Motor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, pidLoopIdx, timeoutMS);
    		tankDriveL1Motor.config_kF(pidLoopIdx, positionF, timeoutMS);
    		tankDriveL1Motor.config_kP(pidLoopIdx, positionP, timeoutMS);
    		tankDriveL1Motor.config_kI(pidLoopIdx, positionI, timeoutMS);
    		tankDriveL1Motor.config_kD(pidLoopIdx, positionD, timeoutMS);
    		tankDriveL1Motor.setInverted(false);
    		tankDriveL1Motor.configPeakOutputForward(1, timeoutMS);
    		tankDriveL1Motor.configPeakOutputReverse(-1, timeoutMS);
    		
    		tankDriveL2Motor.set(ControlMode.Follower, tankDriveL1Motor.getDeviceID());
    		tankDriveL3Motor.set(ControlMode.Follower, tankDriveL1Motor.getDeviceID());
    		
    		tankDriveR1Motor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, pidLoopIdx, timeoutMS);
    		tankDriveR1Motor.config_kF(pidLoopIdx, positionF, timeoutMS);
    		tankDriveR1Motor.config_kP(pidLoopIdx, positionP, timeoutMS);
    		tankDriveR1Motor.config_kI(pidLoopIdx, positionI, timeoutMS);
    		tankDriveR1Motor.config_kD(pidLoopIdx, positionD, timeoutMS);
    		tankDriveR1Motor.setInverted(false);
    		tankDriveR1Motor.configPeakOutputForward(1, timeoutMS);
    		tankDriveR1Motor.configPeakOutputReverse(-1, timeoutMS);
    		
    		tankDriveR2Motor.set(ControlMode.Follower, tankDriveR1Motor.getDeviceID());
    		tankDriveR3Motor.set(ControlMode.Follower, tankDriveR1Motor.getDeviceID());
    		
    	} else if (mode == AUTO_MOVE_MODE) {
    		tankDriveL1Motor.selectProfileSlot(0, pidLoopIdx);
    		tankDriveR1Motor.selectProfileSlot(0, pidLoopIdx);
    		
    		tankDriveL1Motor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, pidLoopIdx, timeoutMS);
    		tankDriveL1Motor.config_kF(pidLoopIdx, moveF, timeoutMS);
    		tankDriveL1Motor.config_kP(pidLoopIdx, moveP, timeoutMS);
    		tankDriveL1Motor.config_kI(pidLoopIdx, moveI, timeoutMS);
    		tankDriveL1Motor.config_kD(pidLoopIdx, moveD, timeoutMS);
    		tankDriveL1Motor.setInverted(false);
    		tankDriveL1Motor.configPeakOutputForward(1, timeoutMS);
    		tankDriveL1Motor.configPeakOutputReverse(-1, timeoutMS);
    		
    		tankDriveL2Motor.set(ControlMode.Follower, tankDriveL1Motor.getDeviceID());
    		tankDriveL3Motor.set(ControlMode.Follower, tankDriveL1Motor.getDeviceID());
    		
    		tankDriveR1Motor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, pidLoopIdx, timeoutMS);
    		tankDriveR1Motor.config_kF(pidLoopIdx, moveF, timeoutMS);
    		tankDriveR1Motor.config_kP(pidLoopIdx, moveP, timeoutMS);
    		tankDriveR1Motor.config_kI(pidLoopIdx, moveI, timeoutMS);
    		tankDriveR1Motor.config_kD(pidLoopIdx, moveD, timeoutMS);
    		tankDriveR1Motor.setInverted(false);
    		tankDriveR1Motor.configPeakOutputForward(1, timeoutMS);
    		tankDriveR1Motor.configPeakOutputReverse(-1, timeoutMS);
    		
    		tankDriveR2Motor.set(ControlMode.Follower, tankDriveR1Motor.getDeviceID());
    		tankDriveR3Motor.set(ControlMode.Follower, tankDriveR1Motor.getDeviceID());
    		
    	} else if (mode == TELEOP_MODE) {
    		tankDriveL1Motor.selectProfileSlot(1, pidLoopIdx);
    		tankDriveR1Motor.selectProfileSlot(1, pidLoopIdx);
    		
    		tankDriveL1Motor.config_kF(pidLoopIdx, vBusF, timeoutMS);
    		tankDriveL1Motor.config_kP(pidLoopIdx, vBusP, timeoutMS);
    		tankDriveL1Motor.config_kI(pidLoopIdx, vBusI, timeoutMS);
    		tankDriveL1Motor.config_kD(pidLoopIdx, vBusD, timeoutMS);
    		
    		tankDriveL2Motor.set(ControlMode.Follower, tankDriveL1Motor.getDeviceID());
    		tankDriveL3Motor.set(ControlMode.Follower, tankDriveL1Motor.getDeviceID());
    		
    		tankDriveR1Motor.config_kF(pidLoopIdx, vBusF, timeoutMS);
    		tankDriveR1Motor.config_kP(pidLoopIdx, vBusP, timeoutMS);
    		tankDriveR1Motor.config_kI(pidLoopIdx, vBusI, timeoutMS);
    		tankDriveR1Motor.config_kD(pidLoopIdx, vBusD, timeoutMS);
    		
    		tankDriveR2Motor.set(ControlMode.Follower, tankDriveR1Motor.getDeviceID());
    		tankDriveR3Motor.set(ControlMode.Follower, tankDriveR1Motor.getDeviceID());
    	}
    }
}

