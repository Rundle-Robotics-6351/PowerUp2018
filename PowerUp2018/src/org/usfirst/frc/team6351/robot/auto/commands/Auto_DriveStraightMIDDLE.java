package org.usfirst.frc.team6351.robot.auto.commands;

import org.usfirst.frc.team6351.robot.Robot;
import org.usfirst.frc.team6351.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
/**
 *
 */
public class Auto_DriveStraightMIDDLE extends Command {
	
	double spd, tme, dst;
	boolean encoderDrive;
	boolean finished;
	double autoTime;
	
    public Auto_DriveStraightMIDDLE(double speed, double time, double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	requires(Robot.sensors);
    	spd = speed;
    	
    	if (time == 0.0) {
    		encoderDrive = true;
    		dst = distance;
    	} else {
        	tme = time;
    	}
    	
    }
    
    public Auto_DriveStraightMIDDLE(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this(0.45, 0.0, distance);
    	
    }
    
    public Auto_DriveStraightMIDDLE(double distance, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this(speed, 0.0, distance);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	finished = false;
    	if (encoderDrive == true) {
    		Robot.sensors.driveEncoderLeft.reset();
    		Robot.driveTrain.setLeft(spd*RobotMap.Curve_Reduction_Factor_Left);
    		Robot.driveTrain.setRight((spd)*(-1)*RobotMap.Curve_Reduction_Factor_Right);
    		
    		
    	} else {
    		Robot.driveTrain.setLeft(spd*RobotMap.Curve_Reduction_Factor_Left);
    		Robot.driveTrain.setRight((spd)*(-1)*RobotMap.Curve_Reduction_Factor_Right);
    		Timer.delay(tme);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	autoTime = DriverStation.getInstance().getMatchTime();
    	if (encoderDrive == true) {
    		double currentDistance = Robot.sensors.getDriveEncoderDistance();
    		if (currentDistance >= dst - 3) {
    			Robot.driveTrain.setLeft(-0.1);
        		Robot.driveTrain.setRight(0.1);
        		Timer.delay(0.4);
    			Robot.driveTrain.setLeft(0.0);
        		Robot.driveTrain.setRight(0.0);
        		finished = true;
    		}
    	} else {
    		Robot.driveTrain.setLeft(0.0);
    		Robot.driveTrain.setRight(0.0);
    		finished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//    	if (encoderDrive == true) {
//    		double currentDistance = Robot.sensors.getDriveEncoderDistance();
//    		if (currentDistance >= dst - 3) {
//    			return true;
//    		} else {
//    			return false;
//    		}
//    	} else {
//    		return true;
//    	}
    	
    	if (finished == true || autoTime <= 4.5) {
    		return true;
    	} else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.setLeft(0.0);
		Robot.driveTrain.setRight(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.setLeft(0.0);
		Robot.driveTrain.setRight(0.0);
    }
}