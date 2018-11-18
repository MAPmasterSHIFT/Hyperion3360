package org.usfirst.frc.team3360.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team3360.robot.commands.AlignWithUltraSonic;
import org.usfirst.frc.team3360.robot.commands.FeedBalls;
import org.usfirst.frc.team3360.robot.commands.IntakeBalls;
import org.usfirst.frc.team3360.robot.commands.ShooterShoot;
import org.usfirst.frc.team3360.robot.commands.WinchClimb;

import autocommands.AutonomousDriveWithEncoders;
import autocommands.AutonomousTurnWithEncoders;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	    public Joystick joystickLeft;
	    public Joystick joystickRight;
	    public Joystick joystickCoPilot;
	    
	    
	    public JoystickButton btnRunShooter;
	    public JoystickButton btnRunFeeder;
	    public JoystickButton btnRunIntake;
	    public JoystickButton btnBackUpGear;
	    public JoystickButton btnRunWinch;
	    public JoystickButton btnTest2;
	    public JoystickButton btnTest1;
	    
	    public JoystickButton btnSensAlign;
	    
	    
	    public OI(){
	    	
	    	joystickLeft = new Joystick(0);
	    	
	        joystickRight = new Joystick(1);
	        
	        
	        joystickCoPilot = new Joystick(2);
	        
	       // btnTest1 = new JoystickButton(joystickRight, 10);
	       // btnTest1.whenPressed(new AutonomousTurnWithEncoders(360));
	        
	       // btnTest2 = new JoystickButton(joystickRight, 11); 
	       // btnTest2.whenPressed(new AutonomousDriveWithEncoders(90, 90, true));
	        
	       // btnSensAlign = new JoystickButton(joystickCoPilot, 3);
	      //  btnSensAlign.whenPressed(new AlignWithUltraSonic());
	        
	        btnRunShooter = new JoystickButton(joystickRight, 1);
	        btnRunShooter.whileHeld(new ShooterShoot(true));
	        
	        btnRunFeeder = new JoystickButton(joystickRight, 2);
	        btnRunFeeder.whileHeld(new FeedBalls(true));
	        
	        btnRunIntake = new JoystickButton(joystickLeft, 1);
	        btnRunIntake.whileHeld(new IntakeBalls(true));
	        
	        btnRunWinch = new JoystickButton(joystickCoPilot, 1);
	        btnRunWinch.whileHeld(new WinchClimb());
	        
	        
	        btnBackUpGear = new JoystickButton(joystickCoPilot, 5);
	        btnBackUpGear.whenPressed(new AutonomousDriveWithEncoders(-10, -10, true));
	    }
	    
	    public Joystick getJoystickLeft() {
	        return joystickLeft;
	    }

	    public Joystick getJoystickRight() {
	        return joystickRight;
	    }
	    
	    public Joystick getCoJoystick() {
	    	return joystickCoPilot;
	    }
}
