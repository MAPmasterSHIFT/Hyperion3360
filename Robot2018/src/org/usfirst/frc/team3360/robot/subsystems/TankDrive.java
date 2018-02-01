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
    private final double positionI = 0.0001;
    private final double positionD = 1;
    private final double positionF = 0;
    
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
	
	public TankDrive() {
    
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new TankDriveJoystickDrive());
	}

	public void driveWithJoysticks() {
		double joystickLeftVal = Robot.oi.getJoystickLeft().getRawAxis(1);
		double joystickRightVal = Robot.oi.getJoystickRight().getRawAxis(1);
   	 
		
		// Configure Joystick deathzone : set Joystick to 0 if axis value is between -0.1 and 0.1
		if(joystickLeftVal > -0.1 && joystickLeftVal < 0.1) {
			joystickLeftVal = 0;
		}
		if(joystickRightVal > -0.1 && joystickRightVal < 0.1) {
			joystickRightVal = 0;
		}
		
		tankDriveL1Motor.set(ControlMode.PercentOutput, joystickLeftVal);
		tankDriveL2Motor.set(ControlMode.PercentOutput, joystickLeftVal);
		tankDriveL3Motor.set(ControlMode.PercentOutput, joystickLeftVal);
		
		tankDriveR1Motor.set(ControlMode.PercentOutput, joystickRightVal);
		tankDriveR2Motor.set(ControlMode.PercentOutput, joystickRightVal);
		tankDriveR3Motor.set(ControlMode.PercentOutput, joystickRightVal);
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
			System.out.println("positionLeft :  " + tankDriveL1Motor.getSensorCollection().getPulseWidthPosition() + "   setPointLeft :  " + encodersToInches(-distanceLeft));
	    	System.out.println("positionRight :  " + tankDriveR1Motor.getSensorCollection().getPulseWidthPosition() + "   setPointRight :  " + encodersToInches(distanceRight));
		}
		
		tankDriveL1Motor.set(ControlMode.Position, encodersToInches(-distanceLeft));
		tankDriveR1Motor.set(ControlMode.Position, encodersToInches(distanceRight));
	}
	
    public void resetEncoderDistance() {
    	if(Robot.isDebugEnable()) {
    		System.out.println("Resetting encoders");
    	}
    	
    	tankDriveL1Motor.setSelectedSensorPosition(0, pidLoopIdx, timeoutMS);
    	tankDriveR1Motor.setSelectedSensorPosition(0, pidLoopIdx, timeoutMS);
    	
    	driveWithEncoders(0, 0);
    }
    
    //returns true when it is done driving
    public boolean isAtSetPoint() {
    	return tankDriveL1Motor.getSelectedSensorVelocity(pidLoopIdx) < 40 && tankDriveL1Motor.getSelectedSensorVelocity(pidLoopIdx) > -40 && 
    			tankDriveR1Motor.getSelectedSensorVelocity(pidLoopIdx) < 40 && tankDriveR1Motor.getSelectedSensorVelocity(pidLoopIdx) > -40;
    }
    
    //Converts requested drive values to Encoder values
    public double encodersToInches(double val) {
    	return val / 72;
    }
    
    //Converts a requested turn angle to drive values
    public double turnDegrees(double val) {
    	return val * 0.275;
    }
	public void setControlMode(final int mode) {
		if(mode == AUTO_ROTATE_MODE) {
			tankDriveL1Motor.selectProfileSlot(0, pidLoopIdx);
    		tankDriveR1Motor.selectProfileSlot(0, pidLoopIdx);
    		
    		tankDriveL1Motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, pidLoopIdx, timeoutMS);
    		tankDriveL1Motor.config_kF(pidLoopIdx, positionF, timeoutMS);
    		tankDriveL1Motor.config_kP(pidLoopIdx, positionP, timeoutMS);
    		tankDriveL1Motor.config_kI(pidLoopIdx, positionI, timeoutMS);
    		tankDriveL1Motor.config_kD(pidLoopIdx, positionD, timeoutMS);
    		tankDriveL1Motor.setInverted(false);
    		tankDriveL1Motor.configPeakOutputForward(0.6, timeoutMS);
    		tankDriveL1Motor.configPeakOutputReverse(-0.6, timeoutMS);
    		
    		tankDriveL2Motor.set(ControlMode.Follower, tankDriveL1Motor.getDeviceID());
    		tankDriveL3Motor.set(ControlMode.Follower, tankDriveL1Motor.getDeviceID());
    		
    		tankDriveR1Motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, pidLoopIdx, timeoutMS);
    		tankDriveR1Motor.config_kF(pidLoopIdx, positionF, timeoutMS);
    		tankDriveR1Motor.config_kP(pidLoopIdx, positionP, timeoutMS);
    		tankDriveR1Motor.config_kI(pidLoopIdx, positionI, timeoutMS);
    		tankDriveR1Motor.config_kD(pidLoopIdx, positionD, timeoutMS);
    		tankDriveR1Motor.setInverted(false);
    		tankDriveR1Motor.configPeakOutputForward(0.6, timeoutMS);
    		tankDriveR1Motor.configPeakOutputReverse(-0.6, timeoutMS);
    		
    		tankDriveR2Motor.set(ControlMode.Follower, tankDriveR1Motor.getDeviceID());
    		tankDriveR3Motor.set(ControlMode.Follower, tankDriveR1Motor.getDeviceID());
    		
    	} else if (mode == AUTO_MOVE_MODE) {
    		tankDriveL1Motor.selectProfileSlot(0, pidLoopIdx);
    		tankDriveR1Motor.selectProfileSlot(0, pidLoopIdx);
    		
    		tankDriveL1Motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, pidLoopIdx, timeoutMS);
    		tankDriveL1Motor.config_kF(pidLoopIdx, moveF, timeoutMS);
    		tankDriveL1Motor.config_kP(pidLoopIdx, moveP, timeoutMS);
    		tankDriveL1Motor.config_kI(pidLoopIdx, moveI, timeoutMS);
    		tankDriveL1Motor.config_kD(pidLoopIdx, moveD, timeoutMS);
    		tankDriveL1Motor.setInverted(false);
    		tankDriveL1Motor.configPeakOutputForward(0.8, timeoutMS);
    		tankDriveL1Motor.configPeakOutputReverse(-0.8, timeoutMS);
    		
    		tankDriveL2Motor.set(ControlMode.Follower, tankDriveL1Motor.getDeviceID());
    		tankDriveL3Motor.set(ControlMode.Follower, tankDriveL1Motor.getDeviceID());
    		
    		tankDriveR1Motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, pidLoopIdx, timeoutMS);
    		tankDriveR1Motor.config_kF(pidLoopIdx, moveF, timeoutMS);
    		tankDriveR1Motor.config_kP(pidLoopIdx, moveP, timeoutMS);
    		tankDriveR1Motor.config_kI(pidLoopIdx, moveI, timeoutMS);
    		tankDriveR1Motor.config_kD(pidLoopIdx, moveD, timeoutMS);
    		tankDriveR1Motor.setInverted(false);
    		tankDriveR1Motor.configPeakOutputForward(0.8, timeoutMS);
    		tankDriveR1Motor.configPeakOutputReverse(-0.8, timeoutMS);
    		
    		tankDriveR2Motor.set(ControlMode.Follower, tankDriveR1Motor.getDeviceID());
    		tankDriveR3Motor.set(ControlMode.Follower, tankDriveR1Motor.getDeviceID());
    		
    	} else if (mode == TELEOP_MODE) {
    		tankDriveL1Motor.selectProfileSlot(1, pidLoopIdx);
    		tankDriveR1Motor.selectProfileSlot(1, pidLoopIdx);

    		tankDriveL1Motor.config_kF(pidLoopIdx, vBusF, timeoutMS);
    		tankDriveL1Motor.config_kP(pidLoopIdx, vBusP, timeoutMS);
    		tankDriveL1Motor.config_kI(pidLoopIdx, vBusI, timeoutMS);
    		tankDriveL1Motor.config_kD(pidLoopIdx, vBusD, timeoutMS);
    		
    		tankDriveL2Motor.config_kF(pidLoopIdx, vBusF, timeoutMS);
    		tankDriveL2Motor.config_kP(pidLoopIdx, vBusP, timeoutMS);
    		tankDriveL2Motor.config_kI(pidLoopIdx, vBusI, timeoutMS);
    		tankDriveL2Motor.config_kD(pidLoopIdx, vBusD, timeoutMS);

    		tankDriveL3Motor.config_kF(pidLoopIdx, vBusF, timeoutMS);
    		tankDriveL3Motor.config_kP(pidLoopIdx, vBusP, timeoutMS);
    		tankDriveL3Motor.config_kI(pidLoopIdx, vBusI, timeoutMS);
    		tankDriveL3Motor.config_kD(pidLoopIdx, vBusD, timeoutMS);

    		tankDriveR1Motor.config_kF(pidLoopIdx, vBusF, timeoutMS);
    		tankDriveR1Motor.config_kP(pidLoopIdx, vBusP, timeoutMS);
    		tankDriveR1Motor.config_kI(pidLoopIdx, vBusI, timeoutMS);
    		tankDriveR1Motor.config_kD(pidLoopIdx, vBusD, timeoutMS);
    		
    		tankDriveR2Motor.config_kF(pidLoopIdx, vBusF, timeoutMS);
    		tankDriveR2Motor.config_kP(pidLoopIdx, vBusP, timeoutMS);
    		tankDriveR2Motor.config_kI(pidLoopIdx, vBusI, timeoutMS);
    		tankDriveR2Motor.config_kD(pidLoopIdx, vBusD, timeoutMS);

    		tankDriveR3Motor.config_kF(pidLoopIdx, vBusF, timeoutMS);
    		tankDriveR3Motor.config_kP(pidLoopIdx, vBusP, timeoutMS);
    		tankDriveR3Motor.config_kI(pidLoopIdx, vBusI, timeoutMS);
    		tankDriveR3Motor.config_kD(pidLoopIdx, vBusD, timeoutMS);
    	}
    }
}
