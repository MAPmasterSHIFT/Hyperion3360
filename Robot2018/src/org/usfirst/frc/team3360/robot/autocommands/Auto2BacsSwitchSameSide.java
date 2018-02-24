package org.usfirst.frc.team3360.robot.autocommands;

import org.usfirst.frc.team3360.robot.commands.ClawGrabCube;
import org.usfirst.frc.team3360.robot.commands.ClawIdle;
import org.usfirst.frc.team3360.robot.commands.ClawReleaseCube;
import org.usfirst.frc.team3360.robot.commands.IntakeGrabCube;

import edu.wpi.first.wpilibj.can.CANMessageNotAllowedException;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auto2BacsSwitchSameSide extends CommandGroup {
 //40 pouce
 //120 pouce

    public Auto2BacsSwitchSameSide(boolean isLeft){
    	//Move to put 1st cube in scale
    	addParallel(new ElevatorRaiseAutoDelay(500, -20000));
    	addSequential(new AutoDriveWithEncoders(100, 100));
    	addParallel(new ClawGrabCube());
    	if(isLeft) {
        	addSequential(new AutoTurnWithEncoders(90));
        	}else {
        	addSequential(new AutoTurnWithEncoders(-90));
        }
    	addParallel(new ElevatorRaiseAutoDelay(100, -45000));
    	addSequential(new SystemWait(1000));
    	
    	addParallel(new ClawReleaseCube());
    	addParallel(new ElevatorRaiseAutoDelay(500, 0));
    	addSequential(new SystemWait(2000));
    	addParallel(new ClawIdle());
    	//5 sec
    	//Move to grab 2nd cube on side
    	if(isLeft) {
        	addSequential(new AutoTurnWithEncoders(-90));
        	}else {
        	addSequential(new AutoTurnWithEncoders(90));
        }
    	addSequential(new AutoDriveWithEncoders(90, 90));
    	if(isLeft) {
        	addSequential(new AutoTurnWithEncoders(125));
        	}else {
        	addSequential(new AutoTurnWithEncoders(-125));
        }
    	addParallel(new IntakeGrabCube());
    	addParallel(new ClawGrabCube());
    	addSequential(new AutoDriveWithEncoders(40, 40));
    	addParallel(new IntakeGrabCube());
    	addParallel(new ClawGrabCube());
    	addParallel(new ElevatorRaiseAutoDelay(500, -25000));
    	addSequential(new SystemWait(1700));
    	addParallel(new ClawReleaseCube());
    	addSequential(new SystemWait(1000));
    	//8 sec
    	
    	//put 2nd cube on switch
    	
    	
    }
    
}