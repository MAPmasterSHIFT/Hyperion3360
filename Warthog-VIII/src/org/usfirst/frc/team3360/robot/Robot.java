
package org.usfirst.frc.team3360.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team3360.robot.commands.ExampleCommand;
import org.usfirst.frc.team3360.robot.subsystems.*;

import autocommands.AutoBoilerGear;
import autocommands.AutoBoilerGearAndShoot;
import autocommands.AutoFeederGear;
import autocommands.AutoFrontGear;
import autocommands.AutoHopperShoot;
import autocommands.AutoHopperShootGear;
import autocommands.AutoShoot;



public class Robot extends IterativeRobot {

	    public static OI oi;
	    
	    public static TankDrive jetDrive;
	    public static Intake turbine;
	    public static Winch GForce;
	    public static Feeder beltFedMG;
	    public static Shooter A10Thunderbolt;
	    public static LEDs leds;
	    
	

	Command autonomousCommand;
	

	
	
	public static DigitalInput autoSwitchSide = new DigitalInput(0);    
    public static DigitalInput autoSwitch1 = new DigitalInput(2);
    public static DigitalInput autoSwitch2 = new DigitalInput(3);
    public static DigitalInput autoSwitch3 = new DigitalInput(4);
    public static DigitalInput autoSwitch4 = new DigitalInput(5);
    public int autoState = 0;
    public boolean isRed = true;
    public boolean isBlue = false;
    public boolean side = isRed;
    
	@Override
	public void robotInit() {
		
		
		RobotMap.init();
		
		jetDrive = new TankDrive();
		turbine = new Intake();
		GForce = new Winch();
		beltFedMG = new Feeder();
		A10Thunderbolt = new Shooter();
		leds = new LEDs();
		
		oi = new OI();
		
		if (!autoSwitchSide.get()){
			side = isRed;
		}
		else{
			side = isBlue;
		}
		if (!autoSwitch1.get()){
			autoState += 1;
		}
		if(!autoSwitch2.get()){
			autoState += 2;
		}
		if(!autoSwitch3.get()){
			autoState += 4;
		}
		if(!autoSwitch4.get()){
			autoState += 8;
		}
		System.out.println("autostate : " + autoState);
		System.out.println("side : " + side);
		
		switch(autoState){
		case 0:
			autonomousCommand = new AutoHopperShootGear(side);
			break;
		case 1:
			autonomousCommand = new AutoFeederGear(side);
			break;
		case 2:
			autonomousCommand = new AutoFrontGear(side);
			break;
		case 3:
			autonomousCommand = new AutoShoot(side);
			break;
		}
		
		
		}
		
		
	

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		Robot.jetDrive.resetEncoderDistance();
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
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
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
