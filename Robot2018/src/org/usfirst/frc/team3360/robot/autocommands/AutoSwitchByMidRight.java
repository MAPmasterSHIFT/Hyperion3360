package org.usfirst.frc.team3360.robot.autocommands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoSwitchByMidRight extends CommandGroup {
 //40 pouce
 //120 pouce

    public AutoSwitchByMidRight(){
    	//Drive to the switch
    	addSequential(new AutoDriveWithEncoders(37 , 37 ));
    
    	//TODO: Raise and drop the cube
    	
    }
    
}
