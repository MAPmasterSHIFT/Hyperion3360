package org.usfirst.frc.team3360.robot.subsystems;

import org.usfirst.frc.team3360.robot.Robot;
import org.usfirst.frc.team3360.robot.RobotMap;
import org.usfirst.frc.team3360.robot.commands.flashLEDS;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LEDs extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public Solenoid LRight = new Solenoid(0);
	public Solenoid LLeft = new Solenoid(1);

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		 setDefaultCommand(new flashLEDS());
	}
	
	public void Leds(){
	    LRight.set(true);
	    LLeft.set(true);
	}
}
