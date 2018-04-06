package org.usfirst.frc.team6351.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Winch extends Subsystem {
  
  	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Spark climber_Winch_Motor = new Spark(0);
	Spark arm_Winch_Motor = new Spark(5);

@Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    // Movement Commands
    public void armUp() {
    	arm_Winch_Motor.set(-0.7);
    }
    
    public void armDown() {
    	arm_Winch_Motor.set(0.5);
    }
	
    public void armStop() {
    	arm_Winch_Motor.set(0.0);
    }
	
    public void winchClimb() {
    	climber_Winch_Motor.set(-1);
    }
    
    public void winchReverse() {
    	climber_Winch_Motor.set(0.5);
    }

    public void winchStop() { 
    	climber_Winch_Motor.set(0.0);
    }
}
