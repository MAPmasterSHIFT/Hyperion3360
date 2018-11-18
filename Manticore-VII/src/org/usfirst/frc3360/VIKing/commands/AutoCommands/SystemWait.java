package org.usfirst.frc3360.VIKing.commands.AutoCommands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3360.VIKing.*;

/***
 Fait une pause
 ***/
public class SystemWait extends Command 
{
	double bid;			//Temps a attendre
	double initTime;	//Moment auquel le wait commence

    public SystemWait(double timeMS) 
    {
    	bid = timeMS;
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
    	initTime = System.currentTimeMillis();
    	System.out.println("Waiting");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        return System.currentTimeMillis() > (bid + initTime);
    }

    // Called once after isFinished returns true
    protected void end()
    {}

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {}
}
