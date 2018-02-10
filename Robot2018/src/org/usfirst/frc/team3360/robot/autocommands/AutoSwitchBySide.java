package org.usfirst.frc.team3360.robot.autocommands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoSwitchBySide extends CommandGroup {
 //40 pouce
 //120 pouce

    public AutoSwitchBySide(double driveDistance, double turnDegrees){
    	//Drive to the middle of the switch
    	addSequential(new AutoDriveWithEncoders(driveDistance, driveDistance));
    	//turn 90 degrees
    	addSequential(new AutoTurnWithEncoders(turnDegrees));
    	
    	//TODO: Raise and drop the cube
    	
    }
    
}
