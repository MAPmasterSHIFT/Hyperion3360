/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3360.robot;



import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {	
	public Joystick RJoystick;
	public Joystick LJoystick;
	public Joystick CoPilotJoystick;
	
	public JoystickButton ButtonSideJoystick;
	
	public OI(){
		RJoystick = new Joystick(0);
		LJoystick = new Joystick(1);
		CoPilotJoystick = new Joystick(2);
		
		//ButtonSideJoystick = new JoystickButton(CoPilotJoystick, 12) ;
		//ButtonSideJoystick.whenReleased(Robot.elevator.stopFreeMode());
	}
	
	public Joystick getRJoystick() {
		return RJoystick;
	}
	
	public Joystick getLJoystick() {
		return LJoystick;
	}
	
	public Joystick getCopilotJoystick() {
		return CoPilotJoystick;
	}
}
