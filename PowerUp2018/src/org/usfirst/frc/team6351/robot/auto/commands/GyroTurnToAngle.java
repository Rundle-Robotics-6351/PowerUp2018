package org.usfirst.frc.team6351.robot.auto.commands;

import org.usfirst.frc.team6351.robot.Robot;
import org.usfirst.frc.team6351.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GyroTurnToAngle extends Command {

	double currentAngle;
	double targetAngle;
	private double m_angle;
	
    public GyroTurnToAngle(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    		requires(Robot.driveTrain);
    		requires(Robot.sensors);
    		
    		m_angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    		currentAngle = Robot.sensors.getGyroAngle();
    		targetAngle = (currentAngle + m_angle);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double error = Math.abs(targetAngle-Robot.sensors.getGyroAngle());
    	double kP;
    	if (error < 10.0) {
    		kP = Math.pow((0.09*error), 2)+0.1;
    	} else {
    		kP = 1;
    	}
    	if (targetAngle < currentAngle){
    		Robot.driveTrain.setLeft(0.60*-1*kP);
    		Robot.driveTrain.setRight(0.60*-1*kP);
    	} else if (targetAngle > currentAngle) {
    		Robot.driveTrain.setLeft(0.60*kP);
    		Robot.driveTrain.setRight(0.60*kP);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (targetAngle >= Robot.sensors.getGyroAngle() - 11.5 && targetAngle <= Robot.sensors.getGyroAngle() + 11.5) {
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