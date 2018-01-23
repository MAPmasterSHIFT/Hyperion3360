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
	
	public static TalonSRX TankDriveR1;
	public static TalonSRX TankDriveR2;
	public static TalonSRX TankDriveR3;
	
	public static TalonSRX TankDriveL1;
	public static TalonSRX TankDriveL2;
	public static TalonSRX TankDriveL3;
	
	public static TalonSRX elevatorMotor;
	

public static void init(){
	
	TankDriveR1 = new TalonSRX (4);
	TankDriveR2 = new TalonSRX (5);
	TankDriveR3 = new TalonSRX (6);
	
	TankDriveL1 = new TalonSRX (1);
	TankDriveL2 = new TalonSRX (2);
	TankDriveL3 = new TalonSRX (3);
	
	elevatorMotor = new TalonSRX (12); //A changer
	
	}
}
