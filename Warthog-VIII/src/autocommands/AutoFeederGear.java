package autocommands;

import edu.wpi.first.wpilibj.command.CommandGroup;


/***
 ***/
public class AutoFeederGear extends CommandGroup 
{
    //TODO
    public  AutoFeederGear(boolean side) 
    {
    	
		addSequential(new AutonomousDriveWithEncoders(101, 101, true));

    	if(side){
    		addSequential(new AutonomousTurnWithEncoders(-60));
    	}
    	else{
    		addSequential(new AutonomousTurnWithEncoders(60));

    	}
    	addSequential(new AutonomousDriveWithEncoders(24, 24, true));
    	
    }
}

