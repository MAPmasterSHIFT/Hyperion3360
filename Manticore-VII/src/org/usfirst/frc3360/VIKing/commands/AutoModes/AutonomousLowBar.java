package org.usfirst.frc3360.VIKing.commands.AutoModes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3360.VIKing.*;
import org.usfirst.frc3360.VIKing.commands.IntakeGrabBoulder;
import org.usfirst.frc3360.VIKing.commands.KillerWedgeAutoHandle;
import org.usfirst.frc3360.VIKing.commands.KillerWedgeAutoHandle;
import org.usfirst.frc3360.VIKing.commands.ShooterShoot;
import org.usfirst.frc3360.VIKing.commands.AutoCommands.AutonomousDriveWithEncoders;
import org.usfirst.frc3360.VIKing.commands.AutoCommands.AutonomousKillerWedgeSet;
import org.usfirst.frc3360.VIKing.commands.AutoCommands.AutonomousTurnWithEncoders;
import org.usfirst.frc3360.VIKing.commands.AutoCommands.SystemWait;

/***
 ***/
public class AutonomousLowBar extends CommandGroup 
{
    //TODO
    public  AutonomousLowBar() 
    {
    	
    	addSequential(new AutonomousKillerWedgeSet(Robot.killerWedge.kPosAtFront, "BID"));
    	addSequential(new SystemWait(1500));
    	addSequential(new AutonomousDriveWithEncoders(45, 45, true));
    	addSequential(new SystemWait(250));
    	addSequential(new AutonomousDriveWithEncoders(175, 175, true));
    	addSequential(new AutonomousKillerWedgeSet(Robot.killerWedge.kPosAtBatter, "Batter"));
    	addSequential(new SystemWait(100));
    	
    	
    }
}

