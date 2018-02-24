package org.usfirst.frc.team3360.robot.autocommands;

import org.usfirst.frc.team3360.robot.commands.ClawGrabCube;
import org.usfirst.frc.team3360.robot.commands.ClawReleaseCube;
import org.usfirst.frc.team3360.robot.commands.IntakeGrabCube;

import edu.wpi.first.wpilibj.can.CANMessageNotAllowedException;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auto3BacsSameSide extends CommandGroup {
 //40 pouce
 //120 pouce

    public Auto3BacsSameSide(boolean isLeft){
    	//Move to put 1st cube in scale
    	addParallel(new ElevatorRaiseAutoDelay(1100, -42000));
    	addSequential(new AutoDriveWithEncoders(250, 250));
    	if(isLeft) {
        	addSequential(new AutoTurnWithEncoders(45));
        	}else {
        	addSequential(new AutoTurnWithEncoders(-45));
        }
    	addParallel(new ClawReleaseCube());
    	addParallel(new ElevatorRaiseAutoDelay(500, 0));
    	addSequential(new SystemWait(1000));
    	
    	//5 sec
    	//Move to grab 2nd cube on side
    	if(isLeft) {
    	addSequential(new AutoTurnWithEncoders(130));
    	}else {
    	addSequential(new AutoTurnWithEncoders(-130));
    }
    	addParallel(new IntakeGrabCube());
    	addParallel(new ClawGrabCube());
    	addSequential(new AutoDriveWithEncoders(82, 82));
    	addParallel(new IntakeGrabCube());
    	addParallel(new ClawGrabCube());
    	//8 sec
    	//put 2nd cube in switch
    	addParallel(new ElevatorRaiseAutoDelay(1000, -20000));
    	addSequential(new SystemWait(1500));
    	addParallel(new ClawReleaseCube());
    	addSequential(new SystemWait(1000));
    	addParallel(new ElevatorRaiseAutoDelay(0, 0));
    	//9,5 sec
    	addSequential(new AutoDriveWithEncoders(-24, -24));
    	//Move to grab 3rd cube
    	if(isLeft) {
        	addSequential(new AutoTurnWithEncoders(45));
        	}else {
        	addSequential(new AutoTurnWithEncoders(45));
        }
    	addParallel(new IntakeGrabCube());
    	addParallel(new ClawGrabCube());
    	addSequential(new AutoDriveWithEncoders(48, 48));
    	addParallel(new IntakeGrabCube());
    	addParallel(new ClawGrabCube());
    	
    	
    	
    }
    
}