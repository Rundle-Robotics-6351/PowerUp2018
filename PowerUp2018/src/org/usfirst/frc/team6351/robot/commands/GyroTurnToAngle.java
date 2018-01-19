package org.usfirst.frc.team6351.robot.commands;

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
    		if (targetAngle < currentAngle){
    			Robot.driveTrain.setLeft(RobotMap.Drive_Scaling_Auto*(-1));
    			Robot.driveTrain.setRight(RobotMap.Drive_Scaling_Auto*(-1));
    		} else if (targetAngle > currentAngle) {
    			Robot.driveTrain.setLeft(RobotMap.Drive_Scaling_Auto);
    			Robot.driveTrain.setRight(RobotMap.Drive_Scaling_Auto);
    		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
