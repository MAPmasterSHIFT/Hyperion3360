package autocommands;

import org.usfirst.frc.team3360.robot.commands.FeedBalls;
import org.usfirst.frc.team3360.robot.commands.ShooterShoot;
import org.usfirst.frc.team3360.robot.commands.SystemWait;
import org.usfirst.frc.team3360.robot.commands.WinchClimb;
import org.usfirst.frc.team3360.robot.commands.WinchClimbAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;


/***
 ***/
public class AutoHopperShootGear extends CommandGroup 
{
    //TODO
    public  AutoHopperShootGear(boolean side) 
    {
    	/*
    	//Move to hopper(2.5s)
    	addSequential(new AutonomousDriveWithEncoders(79, 79, true));
    	addSequential(new AutonomousTurnWithEncoders(-93));
    	addSequential(new AutonomousDriveWithEncoders(34, 34, true));
    	//Wait 2 sec for balls to fall
    	addSequential(new SystemWait(1500));
    	//drive back to the peg
    	addSequential(new AutonomousDriveWithEncoders(-35, -35, true));
    	addSequential(new AutonomousTurnWithEncoders(-36));
    	addSequential(new AutonomousDriveWithEncoders(-52, -52, true));
    	//ddddrop the gear
    	addParallel(new WinchClimbAuto(0.25));
    	addSequential(new SystemWait(500));
    	addParallel(new WinchClimbAuto(0));
    	//go shoot
    	addSequential(new AutonomousDriveWithEncoders(12,12,true));
    	addSequential(new AutonomousTurnWithEncoders(-24));
    	addParallel(new ShooterShoot());
    	addSequential(new AutonomousDriveWithEncoders(122, 122, true));
    	//shoot till you DIEEEEEE
    	addParallel(new ShooterShoot());
    	addParallel(new FeedBalls());
    	addSequential(new SystemWait(5000));
    	
    	*/
    	addParallel(new ShooterShoot(true));
    	addParallel(new FeedBalls(true));
    	addSequential(new SystemWait(5000));
    	addParallel(new ShooterShoot(false));
    	addParallel(new FeedBalls(false));
    	if(side){
    	addSequential(new AutonomousTurnWithEncoders(-42));
    	addSequential(new AutonomousDriveWithEncoders(-105, -134, true));
    	//ddddrop the gear
    	addParallel(new WinchClimbAuto(0.25));
    	addSequential(new SystemWait(500));
    	addParallel(new WinchClimbAuto(0));
    	//get to the Hoppaaaa
    	addSequential(new AutonomousDriveWithEncoders(50, 70, true));
    	addSequential(new AutonomousTurnWithEncoders(70));
    	addSequential(new AutonomousDriveWithEncoders(35, 35, true));
    	}
    	else{
    		addSequential(new AutonomousTurnWithEncoders(42));
        	addSequential(new AutonomousDriveWithEncoders(-134, -105, true));
        	//ddddrop the gear
        	addParallel(new WinchClimbAuto(0.25));
        	addSequential(new SystemWait(500));
        	addParallel(new WinchClimbAuto(0));
        	//get to the Hoppaaaa
        	addSequential(new AutonomousDriveWithEncoders(70, 50, true));
        	addSequential(new AutonomousTurnWithEncoders(-70));
        	addSequential(new AutonomousDriveWithEncoders(35, 35, true));
    	}
    	
    	
    }
}

