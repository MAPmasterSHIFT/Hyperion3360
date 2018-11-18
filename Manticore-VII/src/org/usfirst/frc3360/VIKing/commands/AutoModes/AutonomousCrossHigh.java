package org.usfirst.frc3360.VIKing.commands.AutoModes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3360.VIKing.*;
import org.usfirst.frc3360.VIKing.commands.IntakeGrabBoulder;
import org.usfirst.frc3360.VIKing.commands.KillerWedgeAutoHandle;
import org.usfirst.frc3360.VIKing.commands.ShooterShoot;
import org.usfirst.frc3360.VIKing.commands.AutoCommands.AutonomousDriveWithEncoders;
import org.usfirst.frc3360.VIKing.commands.AutoCommands.AutonomousDriveWithVBus;
import org.usfirst.frc3360.VIKing.commands.AutoCommands.AutonomousKillerWedgeSet;
import org.usfirst.frc3360.VIKing.commands.AutoCommands.AutonomousTurnWithEncoders;
import org.usfirst.frc3360.VIKing.commands.AutoCommands.AutonomousTurnWithGyro;
import org.usfirst.frc3360.VIKing.commands.AutoCommands.SystemWait;
import org.usfirst.frc3360.VIKing.commands.AutoCommands.ToggleAimLight;

/***
 ***/
public class AutonomousCrossHigh extends CommandGroup 
{
    //TODO
    public  AutonomousCrossHigh(int pos) 
    {
    	
    	//Cross
    	addSequential(new AutonomousKillerWedgeSet(615, "BID"));
        addSequential(new SystemWait(750));
    	addSequential(new AutonomousDriveWithEncoders(45, 45, true));
    	addSequential(new SystemWait(200));
    	//VBus command before modifcation
    	//addParallel(new AutonomousDriveWithVBus(0.35, 0.35));
    	//VBus command after modifcation
    	addParallel(new AutonomousDriveWithVBus(0.45, 0.45));
    	addSequential(new SystemWait(2900));
    	addParallel(new AutonomousDriveWithVBus(0, 0));
    	addSequential(new AutonomousTurnWithGyro());
    	
    	//Move to goal
    	addSequential(new AutonomousKillerWedgeSet(Robot.killerWedge.kPosAtBatter, "Batter"));
    	addSequential(new ToggleAimLight(true));
    	addParallel(new IntakeGrabBoulder());
    	addSequential(new AutonomousDriveWithEncoders(50,50 , true));
    	//Align with goal
    	if(pos == 2){
    		addSequential(new AutonomousTurnWithEncoders(90));
    		addSequential(new AutonomousDriveWithEncoders(92, 92, true));
    		addSequential(new AutonomousTurnWithEncoders(-90));
    	}
    	else if(pos == 3){
    		addSequential(new AutonomousTurnWithEncoders(90));
    		addSequential(new AutonomousDriveWithEncoders(41, 41, true));
    		addSequential(new AutonomousTurnWithEncoders(-90));
    	}
    	else if(pos == 4){
    		addSequential(new AutonomousTurnWithEncoders(-90));
    		addSequential(new AutonomousDriveWithEncoders(20, 20, true));
    		addSequential(new AutonomousTurnWithEncoders(90));
    	}
    	else{
    		addSequential(new AutonomousTurnWithEncoders(-90));
    		addSequential(new AutonomousDriveWithEncoders(74, 74, true));
    		addSequential(new AutonomousTurnWithEncoders(90));
    	}

    	//Shoot
    	addSequential(new AutonomousTurnWithGyro());
    	addSequential(new AutonomousTurnWithGyro());
        addSequential(new ShooterShoot(true));
        addSequential(new ToggleAimLight(false));
    	
    	
    }
}

