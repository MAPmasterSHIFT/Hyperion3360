package org.usfirst.frc3360.VIKing.commands.AutoCommands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3360.VIKing.*;

/***
 Fait une pause
 ***/
public class ToggleAimLight extends Command 
{
	
	boolean bState;
    public ToggleAimLight(boolean state) 
    {
    	bState = state;
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
    	Robot.killerWedge.setLight(bState);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        return true;
    }

    // Called once after isFinished returns true
    protected void end()
    {}

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {}
}
