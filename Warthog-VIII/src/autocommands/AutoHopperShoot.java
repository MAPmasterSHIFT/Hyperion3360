package autocommands;

import org.usfirst.frc.team3360.robot.commands.FeedBalls;
import org.usfirst.frc.team3360.robot.commands.ShooterShoot;
import org.usfirst.frc.team3360.robot.commands.SystemWait;

import edu.wpi.first.wpilibj.command.CommandGroup;


/***
 ***/
public class AutoHopperShoot extends CommandGroup 
{
    //TODO
    public  AutoHopperShoot(boolean side) 
    {
    	
    	//Move to hopper(2.5s)
    	addSequential(new AutonomousDriveWithEncoders(90, 90, true));
    	addSequential(new AutonomousTurnWithEncoders(-93));
    	addSequential(new AutonomousDriveWithEncoders(36, 36, true));
    	//Wait 2 sec for balls to fall
    	addSequential(new SystemWait(1500));
    	addSequential(new AutonomousDriveWithEncoders(-60, -60, true));
    	addSequential(new AutonomousTurnWithEncoders(-60));
    	addParallel(new ShooterShoot(true));
    	addSequential(new AutonomousDriveWithEncoders(95, 95, true));
    	addParallel(new ShooterShoot(true));
    	addParallel(new FeedBalls(true));
    	addSequential(new SystemWait(5000));
    	
    	
    	//Move back to shoot while starting the shooter(2.5s)
    	
    	//Shoot Until you DIEEEEE (8 sec)
    	
    	
    }
}

