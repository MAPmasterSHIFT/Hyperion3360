package autocommands;

import org.usfirst.frc.team3360.robot.commands.FeedBalls;
import org.usfirst.frc.team3360.robot.commands.ShooterShoot;
import org.usfirst.frc.team3360.robot.commands.SystemWait;

import edu.wpi.first.wpilibj.command.CommandGroup;


/***
 ***/
public class AutoBoilerGear extends CommandGroup 
{
    //TODO
    public  AutoBoilerGear(boolean side) 
    {
    	addSequential(new AutonomousDriveWithEncoders(100, 100, true));

    	if(side){
    		addSequential(new AutonomousTurnWithEncoders(-60));
    	}
    	else{
    		addSequential(new AutonomousTurnWithEncoders(60));

    	}
    	addSequential(new AutonomousDriveWithEncoders(24, 24, true));
    	
    	
    	
    }
}

