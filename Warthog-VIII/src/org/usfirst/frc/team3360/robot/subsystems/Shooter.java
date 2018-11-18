package org.usfirst.frc.team3360.robot.subsystems;

import org.usfirst.frc.team3360.robot.Robot;
import org.usfirst.frc.team3360.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public final CANTalon shooterLeftMotor = RobotMap.shooterLeftMotor;
	public final CANTalon shooterRightMotor = RobotMap.shooterRightMotor;
	
	private final double speedP = 0.05;
    private final double speedI = 0;
    private final double speedD = 0;
    private final double speedF = 0;

	public void initDefaultCommand(){
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	public void initPID(){
		shooterRightMotor.setPID(speedP, speedI, speedD, speedF, 4096, 0.05, 0);
		shooterRightMotor.enableControl();
		shooterRightMotor.reverseSensor(false);
		shooterLeftMotor.changeControlMode(TalonControlMode.Follower);
	}
	
	public void setShooterSpeed(double rpm, boolean mode){
		if(mode == true){
			shooterRightMotor.changeControlMode(TalonControlMode.Speed);
			rpm += (Robot.oi.getCoJoystick().getRawAxis(0)*2000);
		}
		else {
			shooterRightMotor.changeControlMode(TalonControlMode.PercentVbus);
		}
		
		shooterRightMotor.set(rpm);
		shooterLeftMotor.set(shooterRightMotor.getDeviceID());
		System.out.println("EncSpeed ---:  " + shooterRightMotor.getEncVelocity());
	}
}
