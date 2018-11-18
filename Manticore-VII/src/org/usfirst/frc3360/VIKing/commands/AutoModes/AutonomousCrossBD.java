package org.usfirst.frc3360.VIKing.commands.AutoModes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3360.VIKing.*;
import org.usfirst.frc3360.VIKing.commands.KillerWedgeAutoHandle;
import org.usfirst.frc3360.VIKing.commands.ShooterShoot;
import org.usfirst.frc3360.VIKing.commands.AutoCommands.AutonomousDriveWithEncoders;
import org.usfirst.frc3360.VIKing.commands.AutoCommands.AutonomousDriveWithVBus;
import org.usfirst.frc3360.VIKing.commands.AutoCommands.AutonomousKillerWedgeSet;
import org.usfirst.frc3360.VIKing.commands.AutoCommands.AutonomousTurnWithEncoders;
import org.usfirst.frc3360.VIKing.commands.AutoCommands.SystemWait;

/***
 ***/
public class AutonomousCrossBD extends CommandGroup 
{
    //TODO
    public  AutonomousCrossBD(int pos) 
    {
    	addSequential(new AutonomousKillerWedgeSet(615, "BID"));
        addSequential(new SystemWait(750));
    	addSequential(new AutonomousDriveWithEncoders(45, 45, true));
    	addSequential(new SystemWait(500));
    	addParallel(new AutonomousDriveWithVBus(0.45, 0.45));
    	addSequential(new SystemWait(3500));
    	addParallel(new AutonomousDriveWithVBus(0, 0));
    	addSequential(new SystemWait(1500));
    	
    	
    }
}

