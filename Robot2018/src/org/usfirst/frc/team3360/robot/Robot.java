/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3360.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

import org.usfirst.frc.team3360.robot.subsystems.Winch;
import org.usfirst.frc.team3360.robot.autocommands.AutoDriveWithEncoders;
import org.usfirst.frc.team3360.robot.subsystems.Elevator;
import org.usfirst.frc.team3360.robot.subsystems.Grabber;
import org.usfirst.frc.team3360.robot.subsystems.TankDrive;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	public static final boolean DEBUG = false;

	public static OI oi;
	
	public static TankDrive tankDrive;
	public static Elevator elevator;
	public static Winch winch;
	public static Grabber grabber;
	
	public static DigitalInput autoSwitchCentral = new DigitalInput(0);    
    public static DigitalInput autoSwitchSide = new DigitalInput(1);
    public static DigitalInput autoSwitchFocus = new DigitalInput(2);
    public static DigitalInput autoSwitch4 = new DigitalInput(3);
    public static DigitalInput autoSwitch5 = new DigitalInput(4);

	
	Command autonomousCommand;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotMap.init();
		
		oi = new OI();
		
		tankDrive = new TankDrive();
		elevator = new Elevator();
		winch = new Winch();
		grabber = new Grabber();
		
		// TODO : get autonomous mode physical switch state
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		String gamedata;
		gamedata = DriverStation.getInstance().getGameSpecificMessage();
		
		if(autoSwitchCentral.get()) {
			if(gamedata.charAt(0) == 'L'){
				System.out.println("Your switch side is Left");
				//Auto switch central a gauche
				autonomousCommand = new AutoDriveWithEncoders(0, 0);
			}
			else{
				System.out.println("Your switch side is Right");
				//Auto switch central a droite
				autonomousCommand = new AutoDriveWithEncoders(0, 0);
			}
		}
		else {
			if (autoSwitchSide.get()) {
				//cot� gauche d�part
				if(gamedata.charAt(1) == 'L' && gamedata.charAt(0) == 'L' && !autoSwitchFocus.get()){
					//auto centre 2 bacs.
				}
				else if (gamedata.charAt(1) == 'L' && gamedata.charAt(0) == 'L'){
					//auto petite par cot� (2e bac centre?)
				}
				else if (gamedata.charAt(1) == 'L') {
					//auto scale meme cot� (2e bac?)
				}else if (gamedata.charAt(0) == 'L') {
					//auto sitch par cot� (2e bac switch?)
				}else if (gamedata.charAt(0) == 'L') {
					//auto sitch par cot� (2e bac switch?)
				}else if (!autoSwitchFocus.get()) {
					//scale par le centre
				}else{
					//switch par le centre
				}
			}
			
			else {
				//cot� droit d�part
				if(gamedata.charAt(1) == 'R' && gamedata.charAt(0) == 'R' && !autoSwitchFocus.get()){
					//auto centre 2 bacs.
				}
				else if (gamedata.charAt(1) == 'R' && gamedata.charAt(0) == 'R'){
					//auto petite par cot� (2e bac centre?)
				}
				else if (gamedata.charAt(1) == 'R') {
					//auto scale meme cot� (2e bac?)
				}else if (gamedata.charAt(0) == 'R') {
					//auto sitch par cot� (2e bac switch?)
				}else if (gamedata.charAt(0) == 'R') {
					//auto sitch par cot� (2e bac switch?)
				}else if (!autoSwitchFocus.get()) {
					//scale par le centre
				}else{
					//switch par le centre
				}
			}
		}
			
		
		// TODO : finish implement autonomous mode selection
		if(gamedata.charAt(0) == 'L') {
			System.out.println("Your switch side is Left");
		}
		else {
			System.out.println("Your switch side is Right");
		}
		
		if(gamedata.charAt(1) == 'L' ) {
			System.out.println("Your scale side is Left");	
		}
		else {
			System.out.println("Your scale side is Right");
		}
		
		if(gamedata.charAt(2) == 'L' ) {
			System.out.println("Your enemy switch side is Left");	
		}
		else {
			System.out.println("Your enemy switch side is Right");
		}
		
		
		// TODO : reset tankdrive encoders 

		// schedule the autonomous command (example)
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		
	}
	
	public static boolean isDebugEnable() {
		return DEBUG;
	}
}
