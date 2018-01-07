package org.usfirst.frc.team6351.robot.commands;

import org.usfirst.frc.team6351.robot.Robot;
import org.usfirst.frc.team6351.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GTADrive extends Command {

    public GTADrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double rightTrigger = Robot.m_oi.controllerDriverAxisValue(RobotMap.Controller1_Right_Trigger);
    	double leftTrigger = Robot.m_oi.controllerDriverAxisValue(RobotMap.Controller1_Left_Trigger);
    	double leftJoystickXAxis = Robot.m_oi.controllerDriverAxisValue(RobotMap.Controller1_Left_X_Axis);
    	   
    	//Creating motor variables
    	double leftMotors = (rightTrigger - leftTrigger - leftJoystickXAxis)*RobotMap.Drive_Scaling_Teleop*(-1);
    	double rightMotors = (rightTrigger - leftTrigger + leftJoystickXAxis)*RobotMap.Drive_Scaling_Teleop;
    	
    	if (leftMotors > RobotMap.MAX_ROBOT_SPEED) {
    		   
    		leftMotors = RobotMap.MAX_ROBOT_SPEED;
    		   
    	}
    	if (rightMotors > RobotMap.MAX_ROBOT_SPEED) {
    		   
    		rightMotors = RobotMap.MAX_ROBOT_SPEED;
    		   
    	}
    	   if (leftMotors < RobotMap.MIN_ROBOT_SPEED) {
    		   
    		leftMotors = RobotMap.MIN_ROBOT_SPEED;
    		   
    	}
    	if (rightMotors < RobotMap.MIN_ROBOT_SPEED) {
    		   
    		rightMotors = RobotMap.MIN_ROBOT_SPEED;
    		   
    	}
    	Robot.driveTrain.setLeft(leftMotors);
    	Robot.driveTrain.setRight(rightMotors);
    	
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
