package org.usfirst.frc.team6351.robot.commands;

import org.usfirst.frc.team6351.robot.Robot;
import org.usfirst.frc.team6351.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

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
    	   
    	//Deadzone For Joystick
    	if (Math.abs(leftJoystickXAxis) < RobotMap.JoystickDeadzone) {
    		leftJoystickXAxis = 0;
    	} else {
    		if (leftJoystickXAxis < 0) {
//    			SmartDashboard.putNumber("NEG JS BEFORE", leftJoystickXAxis);
    			leftJoystickXAxis = (((leftJoystickXAxis - (-0.99)) * (0 - (-0.99))) / ((-1)*(RobotMap.JoystickDeadzone) - (-0.99))) + (-0.99);
//    			SmartDashboard.putNumber("NEG JS AFTER", leftJoystickXAxis);
    		} else if (leftJoystickXAxis > 0) {
//    			SmartDashboard.putNumber("POS JS BEFORE", leftJoystickXAxis);
    			leftJoystickXAxis = (((leftJoystickXAxis - (RobotMap.JoystickDeadzone)) * (0.99 - 0)) / (0.99 - RobotMap.JoystickDeadzone)) + 0;
//    			SmartDashboard.putNumber("POS JS AFTER", leftJoystickXAxis);
    		}
    	}
    	//Deadzone For Right Trigger
    	if (Math.abs(rightTrigger) < RobotMap.TriggerDeadzone) {
    		rightTrigger = 0;
    	} else {
//    			SmartDashboard.putNumber("RT BEFORE", rightTrigger);
    		rightTrigger = (((rightTrigger - (RobotMap.TriggerDeadzone)) * (1.00 - 0)) / (1.00 - RobotMap.TriggerDeadzone)) + 0;
//    			SmartDashboard.putNumber("RT AFTER", rightTrigger);
    		
    	}
    	//Deadzone For Left Trigger
    	if (Math.abs(leftTrigger) < RobotMap.TriggerDeadzone) {
    		leftTrigger = 0;
    	} else {
//    			SmartDashboard.putNumber("LT BEFORE", leftTrigger);
    			leftTrigger = (((leftTrigger - (RobotMap.TriggerDeadzone)) * (1.00 - 0)) / (1.00 - RobotMap.TriggerDeadzone)) + 0;
//    			SmartDashboard.putNumber("LT AFTER", leftTrigger);
    		
    	}
    	
    	//Creating motor variables
    	double leftMotors = ((rightTrigger) - leftTrigger + leftJoystickXAxis)*RobotMap.Drive_Scaling_Teleop*RobotMap.Curve_Reduction_Factor_Left;
    	double rightMotors = (rightTrigger - leftTrigger - leftJoystickXAxis)*RobotMap.Drive_Scaling_Teleop*RobotMap.Curve_Reduction_Factor_Right*(-1);
    	
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
