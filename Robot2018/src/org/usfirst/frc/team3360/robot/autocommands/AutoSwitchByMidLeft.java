package org.usfirst.frc.team3360.robot.autocommands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoSwitchByMidLeft extends CommandGroup {
 //40 pouce
 //120 pouce

    public AutoSwitchByMidLeft(){
    	//Drive to the powercube of the switch
    	addSequential(new AutoDriveWithEncoders(39 , 39 ));
    	//turn 90 degrees
    	addSequential(new AutoTurnWithEncoders(90));
    	//drive to alligne the robot to the switch
    	addSequential(new AutoDriveWithEncoders(16, 16));
    	//turn 90 degrees
    	addSequential(new AutoTurnWithEncoders(90));
    	//drive to alligne the robot to the switch
    	addSequential(new AutoDriveWithEncoders(37, 37));
    	
    	//TODO: Raise and drop the cube
    	
    }
    
}
