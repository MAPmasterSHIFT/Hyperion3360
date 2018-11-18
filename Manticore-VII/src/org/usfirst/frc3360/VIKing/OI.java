// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3360.VIKing;

import org.usfirst.frc3360.VIKing.commands.*;
import org.usfirst.frc3360.VIKing.commands.AutoCommands.*;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {



    public Joystick joystickLeft;
    public Joystick joystickRight;
    public Joystick coPilotJoystick;

    
    //Pilot Buttons
    public JoystickButton btnPilotBatterAngle;
    public JoystickButton btnPilotShoot;
    public JoystickButton btnPilotGrab;
    
    //CoPilot Buttons
    public JoystickButton btnCopilotBatterAngle;
    public JoystickButton btnCopilotFrontAngle;
    public JoystickButton btnCopilotShoot;
    public JoystickButton btnCopilotGrab;
    public JoystickButton btnCopilotRelease;
    public JoystickButton btnCopilotWinchClimb;
    public JoystickButton btnCopilotWinchRelease;
    public JoystickButton btnCopilotShooterUnload;
    public JoystickButton btnMove2;
    public JoystickButton btnturn;
    public JoystickButton btnAngleWall;
    
    double dBatter = Robot.killerWedge.kPosAtBatter;
    public double dFront = 650;
    
    public OI() {
    	
    	joystickLeft = new Joystick(0);
    	
        joystickRight = new Joystick(1);
        
        coPilotJoystick = new Joystick(2);

        // SmartDashboard Buttons
        
        //	Pilot Buttons
        
        btnPilotBatterAngle = new JoystickButton(joystickLeft, 1);
        btnPilotBatterAngle.whileHeld(new KillerWedgeAutoHandle(dBatter, "Batter"));
        btnPilotBatterAngle.whileHeld(new IntakeGrabBoulderOneSec());  
        btnPilotBatterAngle.whenPressed(new ToggleAimLight(true));
        btnPilotBatterAngle.whenReleased(new ToggleAimLight(false));
        
        
        btnPilotShoot = new JoystickButton(joystickRight, 1);
        btnPilotShoot.whenPressed(new ShooterShoot(false));
        btnPilotShoot.whileHeld(new IntakeIdle());
        
        
        btnPilotGrab = new JoystickButton(joystickRight, 3);
        btnPilotGrab.whileHeld(new KillerWedgeAutoHandle(Robot.killerWedge.kPosAtFront, "BID"));
        btnPilotGrab.whileHeld(new IntakeGrabBoulder());
        
        
        //	Copilot Buttons

        btnCopilotShoot = new JoystickButton(coPilotJoystick, 12);
        btnCopilotShoot.whenPressed(new ShooterShoot(false));
        btnCopilotShoot.whileHeld(new IntakeIdle());
        
        
        btnCopilotGrab = new JoystickButton(coPilotJoystick, 1);
        btnCopilotGrab.whileHeld(new IntakeGrabBoulder());
        
        
        btnCopilotRelease = new JoystickButton(coPilotJoystick, 2);
        btnCopilotRelease.whileHeld(new IntakeReleaseBoulder());

        
        btnCopilotWinchRelease = new JoystickButton(coPilotJoystick, 7);
        btnCopilotWinchRelease.whenPressed(new WinchRelease());
        
        
        btnCopilotWinchClimb = new JoystickButton(coPilotJoystick, 11);
        btnCopilotWinchClimb.whileHeld(new WinchClimb());
        
        btnCopilotBatterAngle = new JoystickButton(coPilotJoystick, 5);
        btnCopilotBatterAngle.whileHeld(new KillerWedgeAutoHandle(dBatter, "Batter"));
        btnCopilotBatterAngle.whileHeld(new IntakeGrabBoulder());
        btnCopilotBatterAngle.whenPressed(new ToggleAimLight(true));
        btnCopilotBatterAngle.whenReleased(new ToggleAimLight(false));
        
        btnCopilotFrontAngle = new JoystickButton(coPilotJoystick, 4);
        btnCopilotFrontAngle.whileHeld(new KillerWedgeAutoHandle(dFront, "Front"));
        btnCopilotFrontAngle.whileHeld(new IntakeGrabBoulder());
        btnCopilotFrontAngle.whenPressed(new ToggleAimLight(true));
        btnCopilotFrontAngle.whenReleased(new ToggleAimLight(false));

        
        btnAngleWall = new JoystickButton(coPilotJoystick, 3);
        btnAngleWall.whileHeld(new KillerWedgeAutoHandle(455, "Bid"));
        btnAngleWall.whileHeld(new IntakeGrabBoulder());
        btnAngleWall.whenPressed(new ToggleAimLight(true));
        btnAngleWall.whenReleased(new ToggleAimLight(false));
    }

    public Joystick getJoystickLeft() {
        return joystickLeft;
    }

    public Joystick getJoystickRight() {
        return joystickRight;
    }
    
    public Joystick getCoPilotJoystick(){
    	return coPilotJoystick;
    }


}
