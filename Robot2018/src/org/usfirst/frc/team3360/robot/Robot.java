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
import org.usfirst.frc.team3360.robot.autocommands.Auto2BacsScaleSameSide;
import org.usfirst.frc.team3360.robot.autocommands.Auto2BacsSwitchSameSide;
import org.usfirst.frc.team3360.robot.autocommands.Auto3BacsSameSide;
import org.usfirst.frc.team3360.robot.autocommands.AutoDriveWithEncoders;
import org.usfirst.frc.team3360.robot.autocommands.AutoScaleOpposite;
import org.usfirst.frc.team3360.robot.autocommands.AutoSwitchByMidLeft;
import org.usfirst.frc.team3360.robot.autocommands.AutoSwitchBySide;
import org.usfirst.frc.team3360.robot.autocommands.CenterAutoSingle;
import org.usfirst.frc.team3360.robot.subsystems.Lift;
import org.usfirst.frc.team3360.robot.subsystems.Claw;
import org.usfirst.frc.team3360.robot.subsystems.Intake;
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
	public static Lift lift;
	public static Winch winch;
	public static Claw claw;
	public static Intake intake;

	public static DigitalInput autoSwitchCentral = new DigitalInput(0);
	public static DigitalInput autoSwitchSide = new DigitalInput(1);
	public static DigitalInput autoSwitchFocus = new DigitalInput(2);
	public static DigitalInput autoSwitch4 = new DigitalInput(3);
	public static DigitalInput autoSwitch5 = new DigitalInput(4);
	public static boolean isAuto = false;

	Command autonomousCommand;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotMap.init();
		
		tankDrive = new TankDrive();
		lift = new Lift();
		winch = new Winch();
		claw = new Claw();
		intake = new Intake();

		oi = new OI();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
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
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		isAuto = true;
		String gamedata = "";
		int tries = 100;
		while(tries > 0 && gamedata.length() < 2) {
			System.out.println("GETTING GAME INFO");
		gamedata = DriverStation.getInstance().getGameSpecificMessage();
		tries--;
		}
		System.out.println("GAME MESSAGE IS : " + gamedata);

		if (!autoSwitchCentral.get()) {
			if (gamedata.charAt(0) == 'L') {
				System.out.println("Your switch side is Left");
				// Auto switch central a gauche
				autonomousCommand = new CenterAutoSingle(true);
			} else {
				System.out.println("Your switch side is Right");
				// Auto switch central a droite
				autonomousCommand = new CenterAutoSingle(false);
			}
		} else {
			if (autoSwitchSide.get()) {
				// cote gauche depart
				if (gamedata.charAt(1) == 'L' && gamedata.charAt(0) == 'L' && !autoSwitchFocus.get()) {
					System.out.println("AUTO LL");
					// auto grosse (2 bacs).
					autonomousCommand=new Auto3BacsSameSide(true);
				} else if (gamedata.charAt(1) == 'L' && gamedata.charAt(0) == 'L') {
					System.out.println("AUTO LL");
					// auto petite par cote (2e bac centre?)
					autonomousCommand=new Auto3BacsSameSide(true);
				} else if (gamedata.charAt(1) == 'L') {
					// auto grosse meme cote (2e bac?)
					autonomousCommand=new Auto2BacsScaleSameSide(true);
				} else if (gamedata.charAt(0) == 'L') {
					// auto petite par cote (2e bac switch?)
					autonomousCommand=new Auto2BacsSwitchSameSide(true);
					
				} else if (!autoSwitchFocus.get()) {
					// grosse par le centre
					autonomousCommand=new AutoScaleOpposite(true);
				} else {
					// petite par le centre
					autonomousCommand=new AutoScaleOpposite(true);
				}
			}

			else {
				// cote droit depart
				if (gamedata.charAt(1) == 'R' && gamedata.charAt(0) == 'R' && !autoSwitchFocus.get()) {
					// auto grosse 2 bacs.
					autonomousCommand=new Auto3BacsSameSide(false);
				} else if (gamedata.charAt(1) == 'R' && gamedata.charAt(0) == 'R') {
					// auto petite par cote (2e bac centre?)
					autonomousCommand=new Auto3BacsSameSide(false);
				} else if (gamedata.charAt(1) == 'R') {
					// auto grosse meme cote (2e bac?)
					autonomousCommand=new Auto2BacsScaleSameSide(false);
				} else if (gamedata.charAt(0) == 'R') {
					// auto petite par cote (2e bac switch?)
					autonomousCommand=new Auto2BacsSwitchSameSide(false);
				} else if (!autoSwitchFocus.get()) {
					// scale par le centre
					autonomousCommand=new AutoScaleOpposite(false);
				} else {
					// switch par le centre
					autonomousCommand=new AutoScaleOpposite(false);
				}
			}
		}

		// schedule the autonomous command (example)
		if (autonomousCommand != null) {
			System.out.println("Autonomous Mode : " + autonomousCommand.getName());
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
		isAuto = false;
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
	public static boolean isAutoOn() {
		return isAuto;
	}
}
