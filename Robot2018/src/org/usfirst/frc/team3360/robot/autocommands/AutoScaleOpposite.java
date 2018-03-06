package org.usfirst.frc.team3360.robot.autocommands;

import org.usfirst.frc.team3360.robot.commands.ClawGrabCube;
import org.usfirst.frc.team3360.robot.commands.ClawReleaseCube;
import org.usfirst.frc.team3360.robot.commands.IntakeGrabCube;

import edu.wpi.first.wpilibj.can.CANMessageNotAllowedException;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoScaleOpposite extends CommandGroup {
 //40 pouce
 //120 pouce

    public AutoScaleOpposite(boolean isLeft){
    	//Move to put 1st cube in scale
    	addParallel(new ElevatorRaiseAutoDelay(1000, -10000));
    	addSequential(new AutoDriveWithEncoders(167, 167));
    	addParallel(new ClawGrabCube());
    	if(isLeft) {
        	addSequential(new AutoTurnWithEncoders(90));
        	}else {
        	addSequential(new AutoTurnWithEncoders(-90));
        }
    	addSequential(new AutoDriveWithEncoders(220, 220));
    	addParallel(new ClawGrabCube());
    	if(isLeft) {
        	addSequential(new AutoTurnWithEncoders(-100));
        	}else {
        	addSequential(new AutoTurnWithEncoders(100));
        }
    	addParallel(new ElevatorRaiseAutoDelay(500, -45000));
    	addParallel(new ClawGrabCube());
    	addSequential(new AutoDriveWithEncoders(64, 64));
    	addParallel(new ClawGrabCube());
    	addSequential(new SystemWait(1000));
    	addParallel(new ClawReleaseCube());
    	addSequential(new SystemWait(1000));
    	addParallel(new ElevatorRaiseAutoDelay(500, 0));
    	addSequential(new AutoDriveWithEncoders(-24, -24));
    	
    	
    	
    }
    
}