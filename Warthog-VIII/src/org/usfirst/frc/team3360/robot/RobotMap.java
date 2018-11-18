package org.usfirst.frc.team3360.robot;

import com.ctre.*;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	    public static CANTalon tankDriveR1Motor;
	    public static CANTalon tankDriveR2Motor;
	    public static CANTalon tankDriveR3Motor;
	    public static CANTalon tankDriveL1Motor;
	    public static CANTalon tankDriveL2Motor;
	    public static CANTalon tankDriveL3Motor;
	    
	    public static CANTalon feederMotor;
	    public static CANTalon feederGingerWheel;
	    public static Talon feederVibrator;
	    
	    public static CANTalon intakeMotor;
	    
	    public static CANTalon winchLeftMotor;
	    public static CANTalon winchRightMotor;
	    
	    public static CANTalon shooterLeftMotor;
	    public static CANTalon shooterRightMotor;
	    
	    
	    
	    public static void init()
	    {
	    	tankDriveR1Motor = new CANTalon(4);
	        LiveWindow.addActuator("TankDrive", "R1Motor", tankDriveR1Motor);
	        tankDriveR1Motor.setInverted(false);
	        
	        tankDriveR2Motor = new CANTalon(5);
	        LiveWindow.addActuator("TankDrive", "R2Motor", tankDriveR2Motor);
	        tankDriveR2Motor.setInverted(false);
	        
	        tankDriveR3Motor = new CANTalon(6);
	        LiveWindow.addActuator("TankDrive", "R3Motor", tankDriveR3Motor);
	        tankDriveR3Motor.setInverted(false);
	   
	        tankDriveL1Motor = new CANTalon(1);
	        LiveWindow.addActuator("TankDrive", "L1Motor", tankDriveL1Motor);
	        tankDriveL1Motor.setInverted(false);
	        
	        tankDriveL2Motor = new CANTalon(2);
	        LiveWindow.addActuator("TankDrive", "L2Motor", tankDriveL2Motor);
	        tankDriveL2Motor.setInverted(false);
	        
	        tankDriveL3Motor = new CANTalon(3);
	        LiveWindow.addActuator("TankDrive", "L3Motor", tankDriveL3Motor);
	        tankDriveL3Motor.setInverted(false);
	        
	        feederMotor = new CANTalon(10);
	        LiveWindow.addActuator("feeder", "feederMotor", feederMotor);
	        feederMotor.setInverted(false);
	
	        intakeMotor = new CANTalon(31);
	        LiveWindow.addActuator("intake", "intakeMotor", intakeMotor);
	        intakeMotor.setInverted(false);
	        
	        winchLeftMotor = new CANTalon(21);
	        LiveWindow.addActuator("winch", "winchLeftMotor", winchLeftMotor);
	        winchLeftMotor.setInverted(false);
	        
	        winchRightMotor = new CANTalon(22);
	        LiveWindow.addActuator("winch", "winchRightMotor", winchRightMotor);
	        winchRightMotor.setInverted(false);
	        
	        shooterLeftMotor = new CANTalon(12);
	        LiveWindow.addActuator("shooter", "shooterLeftMotor", shooterLeftMotor);
	        shooterLeftMotor.setInverted(false);
	        
	        shooterRightMotor = new CANTalon(11);
	        LiveWindow.addActuator("shooter", "shooterRightMotor", shooterRightMotor);
	        shooterRightMotor.setInverted(false);
	        
	        feederGingerWheel = new CANTalon(13);
	        LiveWindow.addActuator("feeder", "feederGingerwheel", feederGingerWheel);
	        feederGingerWheel.setInverted(true);
	        
	        feederVibrator = new Talon(0);
	        
	        
	        
	    }
}
