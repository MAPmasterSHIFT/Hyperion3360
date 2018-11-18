package org.usfirst.frc.team3360.robot.subsystems;

import org.usfirst.frc.team3360.robot.Robot;
import org.usfirst.frc.team3360.robot.RobotMap;
import org.usfirst.frc.team3360.robot.commands.TankDriveJoystickDrive;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

/**
 *
 */
public class TankDrive extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	//Declare subsystem components
	public final CANTalon r1Motor = RobotMap.tankDriveR1Motor;
    public final CANTalon r2Motor = RobotMap.tankDriveR2Motor;
    public final CANTalon r3Motor = RobotMap.tankDriveR3Motor;
    public final CANTalon l1Motor = RobotMap.tankDriveL1Motor;
    public final CANTalon l2Motor = RobotMap.tankDriveL2Motor;
    public final CANTalon l3Motor = RobotMap.tankDriveL3Motor;

    
    public final ADXRS450_Gyro gyro = new ADXRS450_Gyro();
    //public final AnalogInput sensL = new AnalogInput(2);
    //public final AnalogInput sensR = new AnalogInput(3);
    
    //New Values
    private final double positionP = 1;
    private final double positionI = 0.0001;
    private final double positionD = 1;
    private final double positionF = 0;
    
    
    private final double moveP = 0.5;
    private final double moveI = 0;
    private final double moveD = 3;
    private final double moveF = 0;
    
    
    private final double vBusP = 0.01;
    private final double vBusI = 25;
    private final double vBusD = 0;
    private final double vBusF = 1;

    private double gyroInitVal;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public TankDrive(){
    	gyro.calibrate();
    	gyroInitVal =  gyro.getAngle();
    }
    
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		 setDefaultCommand(new TankDriveJoystickDrive());
	}
	 public void tankDriveWithJoysticks(){
    	 double joyLeftVal = -Robot.oi.getJoystickRight().getRawAxis(1);
    	 double joyRightVal = Robot.oi.getJoystickLeft().getRawAxis(1);
    	 double rightOutput = 0;
    	 double leftOutput = 0;
     	
    	 if(joyRightVal > -0.1 && joyRightVal < 0.1){
    		 joyRightVal = 0;
    	 }
    	 if(joyLeftVal > -0.1 && joyLeftVal < 0.1){
    		 joyLeftVal = 0;
    	 }
    	 
    	// if(Robot.oi.getJoystickRight().getRawButton(1)){
    	//	 rightOutput = joyRightVal - (Robot.oi.getJoystickRight().getRawAxis(0)/2);
    	//	 leftOutput = joyRightVal + (Robot.oi.getJoystickRight().getRawAxis(0)/2);
    	// }
    	// else{
    		 leftOutput = Math.pow(joyRightVal, 2);
    		 if(joyRightVal<0){leftOutput = -leftOutput;}
    		 rightOutput = Math.pow(joyLeftVal, 2);
    		 if(joyLeftVal<0){rightOutput = -rightOutput;}
    	// }
    //	 rightOutput = joyRightVal - (Robot.oi.getJoystickRight().getRawAxis(0)/2);
		// leftOutput = joyRightVal + (Robot.oi.getJoystickRight().getRawAxis(0)/2);
    	 
    	 r1Motor.set(rightOutput);
    	 r2Motor.set(rightOutput);
    	 r3Motor.set(rightOutput);
    	 l1Motor.set(leftOutput);
    	 l2Motor.set(leftOutput);
    	 l3Motor.set(leftOutput);
    	 
    }
    public void setDriveValue(double dRightVal, double dLeftVal){
    	r1Motor.set(dRightVal);
   	 	r2Motor.set(dRightVal);
   	 	r3Motor.set(dRightVal);
   	 	l1Motor.set(dLeftVal);
   	 	l2Motor.set(dLeftVal);
   	 	l3Motor.set(dLeftVal);
    }
    
    public void driveWithEncoders(double distanceR, double distanceL){
    	System.out.println("positionLeft :  " + l1Motor.getEncPosition() + "   setPointLeft :  " + l1Motor.getSetpoint());
    	System.out.println("positionRight :  " + r1Motor.getEncPosition() + "   setPointRight :  " + r1Motor.getSetpoint());
    	r1Motor.set(encodersToInches(distanceR));
    	l1Motor.set(encodersToInches(-distanceL));
    }
    
    public void setControlMode(int mode)
    {
    	if(mode == 1)
    	{
    		//AUTO rotate PROFILE
    		l1Motor.changeControlMode(TalonControlMode.Position);
    		l1Motor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
            l1Motor.setPID(positionP, positionI, positionD, positionF, 4096, 0.05, 0);

            l1Motor.enableControl();
            l1Motor.reverseSensor(false);
            l1Motor.configPeakOutputVoltage(8, -8);
            
            r1Motor.changeControlMode(TalonControlMode.Position);
            r1Motor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
            r1Motor.setPID(positionP, positionI, positionD, positionF, 4096, 0.05, 0);

            r1Motor.enableControl();
            r1Motor.reverseSensor(false);
            r1Motor.configPeakOutputVoltage(8, -8);
            
            l3Motor.changeControlMode(TalonControlMode.Follower);
            l2Motor.changeControlMode(TalonControlMode.Follower);
            
            r3Motor.changeControlMode(TalonControlMode.Follower);
            r2Motor.changeControlMode(TalonControlMode.Follower);
            
            l3Motor.set(l1Motor.getDeviceID());
            l2Motor.set(l1Motor.getDeviceID());
            
            r3Motor.set(r1Motor.getDeviceID());
            r2Motor.set(r1Motor.getDeviceID());
            
            r1Motor.setProfile(0);
            l1Motor.setProfile(0);
    	}
    	else if (mode == 2)
    	{
    		//TELEOP PROFILE
    		
    		l1Motor.changeControlMode(TalonControlMode.PercentVbus);
    		l1Motor.setPID(vBusP, vBusI, vBusD, vBusF, 0, 1, 1);
    		l2Motor.changeControlMode(TalonControlMode.PercentVbus);
    		l2Motor.setPID(vBusP, vBusI, vBusD, vBusF, 0, 1, 1);
    		l3Motor.changeControlMode(TalonControlMode.PercentVbus);
    		l3Motor.setPID(vBusP, vBusI, vBusD, vBusF, 0, 1, 1);
    		r1Motor.changeControlMode(TalonControlMode.PercentVbus);
    		r1Motor.setPID(vBusP, vBusI, vBusD, vBusF, 0, 1, 1);
    		r2Motor.changeControlMode(TalonControlMode.PercentVbus);
    		r2Motor.setPID(vBusP, vBusI, vBusD, vBusF, 0, 1, 1);
    		r3Motor.changeControlMode(TalonControlMode.PercentVbus);
    		r3Motor.setPID(vBusP, vBusI, vBusD, vBusF, 0, 1, 1);
    		
    		r1Motor.setProfile(1);
    		l1Motor.setProfile(1);
    	}
    	else if (mode == 3){
    		//AUTO move PROFILE
    		l1Motor.changeControlMode(TalonControlMode.Position);
    		l1Motor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
            l1Motor.setPID(moveP, moveI, moveD, moveF, 4096, 0.05, 0);

            l1Motor.enableControl();
            l1Motor.reverseSensor(false);
            l1Motor.configPeakOutputVoltage(10, -10);
            
            r1Motor.changeControlMode(TalonControlMode.Position);
            r1Motor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
            r1Motor.setPID(moveP, moveI, moveD, moveF, 4096, 0.05, 0);

            r1Motor.enableControl();
            r1Motor.reverseSensor(false);
            r1Motor.configPeakOutputVoltage(10, -10);
            
            l3Motor.changeControlMode(TalonControlMode.Follower);
            l2Motor.changeControlMode(TalonControlMode.Follower);
            
            r3Motor.changeControlMode(TalonControlMode.Follower);
            r2Motor.changeControlMode(TalonControlMode.Follower);

            l3Motor.set(l1Motor.getDeviceID());
            l2Motor.set(l1Motor.getDeviceID());
            
            r3Motor.set(r1Motor.getDeviceID());
            r2Motor.set(r1Motor.getDeviceID());
            
            r1Motor.setProfile(0);
            l1Motor.setProfile(0);
            
            
            
    	}
    }
    /*** RESET ENCODER ***/
    public void resetEncoderDistance()
    {
    	System.out.println("Resetting encoders");
    	r1Motor.setPosition(0);
    	l1Motor.setPosition(0);
    	
    	driveWithEncoders(0, 0);
    }
    
    /*** DISTANCE GETTER ***/
    //returns true when it<s done driving
    public boolean isAtSetPoint(){
    	return l1Motor.getEncVelocity() < 40 && l1Motor.getEncVelocity() > -40 && 
    			r1Motor.getEncVelocity() < 40 && r1Motor.getEncVelocity() > -40;
    			
    }
    
    //Converts requested drive values to Encoder values
    public double encodersToInches(double val){
    	return val/72;
    	
    	//return val /16.0865;
    }
    
    //Converts a requested turn angle to drive values
    public double turnDegrees(double val){
    	//0.275 = val toronto
    	
    	
    return val * 0.275;
    }
    
    public double getOffsetDeg(){
    	System.out.println("Gyro Offset : " + (gyro.getAngle() - gyroInitVal));
    	return gyro.getAngle() - gyroInitVal;
    }
    
    public double corrAngle(){
    	//System.out.println(sensL.getVoltage() + "   "  + sensR.getVoltage());
    	//return sensL.getVoltage() - sensR.getVoltage() * 10000;
    	return 0;
    }
    
}


