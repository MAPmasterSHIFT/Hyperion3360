/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3360.robot;

import org.usfirst.frc.team3360.robot.autocommands.AutoDriveWithEncoders;
import org.usfirst.frc.team3360.robot.autocommands.AutoTurnWithEncoders;
import org.usfirst.frc.team3360.robot.commands.ClawGrabCube;
import org.usfirst.frc.team3360.robot.commands.ClawReleaseCube;
import org.usfirst.frc.team3360.robot.commands.IntakeGrabCube;
import org.usfirst.frc.team3360.robot.commands.IntakeReleaseCube;
import org.usfirst.frc.team3360.robot.commands.WinchClimb;

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
	
    public JoystickButton btnCopilotIntakeGrab;
    public JoystickButton btnCopilotIntakeRelease;
	public JoystickButton btnCopilotWinch;
	public JoystickButton btnTurn180Degrees;
	public JoystickButton btnMoveWithEncoders;
	
	public OI(){
		joystickRight = new Joystick(0);
		joystickLeft = new Joystick(1);
		joystickCoPilot = new Joystick(2);
		
		btnMoveWithEncoders = new JoystickButton(joystickRight, 10);
		btnMoveWithEncoders.whenPressed(new AutoDriveWithEncoders(72, 72));
		
		btnTurn180Degrees = new JoystickButton(joystickRight, 11);
		btnTurn180Degrees.whenPressed(new AutoTurnWithEncoders(360));
		
		btnCopilotIntakeGrab = new JoystickButton(joystickLeft, 1);
        btnCopilotIntakeGrab.whileHeld(new IntakeGrabCube());
        btnCopilotIntakeGrab.whileHeld(new ClawGrabCube());
        
        btnCopilotIntakeRelease = new JoystickButton(joystickRight, 1);
        btnCopilotIntakeRelease.whileHeld(new IntakeReleaseCube());
        btnCopilotIntakeRelease.whileHeld(new ClawReleaseCube());
        
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

