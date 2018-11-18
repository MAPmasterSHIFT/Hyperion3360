package autocommands;

import edu.wpi.first.wpilibj.command.CommandGroup;


/***
 ***/
public class AutoFrontGear extends CommandGroup 
{
    //TODO
    public  AutoFrontGear(boolean side) 
    {
		addSequential(new AutonomousDriveWithEncoders(76, 76, true));

    	
    	
    }
}

