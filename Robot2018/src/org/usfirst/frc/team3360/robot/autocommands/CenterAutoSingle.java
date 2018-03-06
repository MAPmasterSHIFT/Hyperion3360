package org.usfirst.frc.team3360.robot.autocommands;

import org.usfirst.frc.team3360.robot.commands.ClawGrabCube;
import org.usfirst.frc.team3360.robot.commands.ClawReleaseCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterAutoSingle extends CommandGroup {
 //40 pouce
 //120 pouce

    public CenterAutoSingle(boolean isLeft){
    	addParallel(new ElevatorRaiseAutoDelay(250, -10000));
    	addSequential(new AutoDriveWithEncoders(15, 15));
    	addParallel(new ClawGrabCube());
    	if(isLeft) {
        	addSequential(new AutoTurnWithEncoders(-90));
        	}else {
        	addSequential(new AutoTurnWithEncoders(90));
        }
    	addParallel(new ElevatorRaiseAutoDelay(750, -30000));
    	addSequential(new AutoDriveWithEncoders(50, 50));
    	addParallel(new ClawGrabCube());
    	if(isLeft) {
        	addSequential(new AutoTurnWithEncoders(90));
        	}else {
        	addSequential(new AutoTurnWithEncoders(-90));
        }
    	addSequential(new AutoDriveWithEncoders(70, 70));
    	addParallel(new ClawReleaseCube());
    	addSequential(new SystemWait(1000));
    	
    	
    }
    
}
