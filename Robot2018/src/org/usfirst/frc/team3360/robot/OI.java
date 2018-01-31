/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3360.robot;

import org.usfirst.frc.team3360.robot.commands.GrabberGrabCube;
import org.usfirst.frc.team3360.robot.commands.GrabberReleaseCube;
import org.usfirst.frc.team3360.robot.commands.WinchClimb;
import org.usfirst.frc.team3360.robot.commands.tankDriveTurn180Degrees;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {	
	public Joystick joystickLeft;
    public Joystick joystickRight;
    public Joystick joystickCoPilot;
	
    public JoystickButton btnCopilotGrab;
    public JoystickButton btnCopilotRelease;
	public JoystickButton btnCopilotWinch;
	public JoystickButton btnTurn180Degrees;
	
	public OI(){
		joystickRight = new Joystick(0);
		joystickLeft = new Joystick(1);
		joystickCoPilot = new Joystick(2);
		
		btnTurn180Degrees = new JoystickButton(joystickRight, 3);
		btnTurn180Degrees.whenPressed(new tankDriveTurn180Degrees());
		
		btnCopilotGrab = new JoystickButton(joystickCoPilot, 1);
        btnCopilotGrab.whileHeld(new GrabberGrabCube());
        
        btnCopilotRelease = new JoystickButton(joystickCoPilot, 2);
        btnCopilotRelease.whileHeld(new GrabberReleaseCube());
        
		btnCopilotWinch = new JoystickButton(joystickCoPilot, 11);
		btnCopilotWinch.whileHeld(new WinchClimb());
	}
	
	public Joystick getJoystickLeft() {
        return joystickLeft;
    }

    public Joystick getJoystickRight() {
        return joystickRight;
    }
    
    public Joystick getJoystickCoPilot() {
    	return joystickCoPilot;
    }
}
