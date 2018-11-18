package autocommands;

import org.usfirst.frc.team3360.robot.commands.FeedBalls;
import org.usfirst.frc.team3360.robot.commands.ShooterShoot;
import org.usfirst.frc.team3360.robot.commands.SystemWait;

import edu.wpi.first.wpilibj.command.CommandGroup;


/***
 ***/
public class AutoShoot extends CommandGroup 
{
    //TODO
    public  AutoShoot(boolean side) 
    {
    	
    	addParallel(new ShooterShoot(true));
    	addParallel(new FeedBalls(true));
    	addSequential(new SystemWait(7000));
    	addParallel(new ShooterShoot(false));
    	addParallel(new FeedBalls(false));
    	
        if(side){
        	
            	addSequential(new AutonomousTurnWithEncoders(-70));
            	addSequential(new AutonomousDriveWithEncoders(90, 90, true));	
    	
    }
        else{
        	addSequential(new AutonomousTurnWithEncoders(70));
        	addSequential(new AutonomousDriveWithEncoders(90, 90, true));	
        }
    }
}

