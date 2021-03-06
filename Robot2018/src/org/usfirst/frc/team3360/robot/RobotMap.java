/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3360.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public class RobotMap {
	public static TalonSRX tankDriveL1Motor;
	public static TalonSRX tankDriveL2Motor;
	public static TalonSRX tankDriveL3Motor;
	
	public static TalonSRX tankDriveR1Motor;
	public static TalonSRX tankDriveR2Motor;
	public static TalonSRX tankDriveR3Motor;
	
	public static TalonSRX liftLeftMotor;
	public static TalonSRX liftRightMotor;
	
	public static TalonSRX winchLeftMotor;
	public static TalonSRX winchRightMotor;
	
	public static TalonSRX clawMotor;
	
	public static TalonSRX intakeLeftMotor;
	public static TalonSRX intakeRightMotor;

	public static void init(){
		tankDriveL1Motor = new TalonSRX (1);
		tankDriveL2Motor = new TalonSRX (2);
		tankDriveL3Motor = new TalonSRX (3);
		
		tankDriveR1Motor = new TalonSRX (4);
		tankDriveR2Motor = new TalonSRX (5);
		tankDriveR3Motor = new TalonSRX (6);
		
		liftLeftMotor = new TalonSRX (7);
		liftRightMotor = new TalonSRX (8);
		
		winchLeftMotor = new TalonSRX (9);
		winchRightMotor = new TalonSRX (10);
		
		clawMotor = new TalonSRX (13);
		
		intakeLeftMotor = new TalonSRX (11);
		intakeRightMotor = new TalonSRX (12);
	}
}

