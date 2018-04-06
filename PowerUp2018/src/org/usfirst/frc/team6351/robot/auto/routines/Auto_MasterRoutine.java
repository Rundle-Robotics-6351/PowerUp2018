package org.usfirst.frc.team6351.robot.auto.routines;

import org.usfirst.frc.team6351.robot.Robot;
import org.usfirst.frc.team6351.robot.auto.commands.Auto_ArmToPosition;
import org.usfirst.frc.team6351.robot.auto.commands.Auto_ArmToTime;
import org.usfirst.frc.team6351.robot.auto.commands.Auto_ArmUpToScale;
import org.usfirst.frc.team6351.robot.auto.commands.Auto_DriveBackwards;
import org.usfirst.frc.team6351.robot.auto.commands.Auto_DriveStraight;
import org.usfirst.frc.team6351.robot.auto.commands.GyroTurnToAngle;
import org.usfirst.frc.team6351.robot.commands.GrabberSolenoids;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auto_MasterRoutine extends CommandGroup {

	public Auto_MasterRoutine(String switchPos, String scalePos, String which, String startingPos) {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		//      addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		//      addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		System.out.println("Which Routine "+which);
		System.out.println("Switch POS '"+switchPos+"'");
		System.out.println("Scale POS '"+scalePos+"'");
		System.out.println("Starting POS '"+startingPos+"'");
		if (which == "cross") {
			addSequential(new Auto_DriveStraight(130));
			DriverStation.reportError("Cross Line Auto",false);
		} else if (which == "switch") {
			if (startingPos == "M") {
				DriverStation.reportError("Middle Auto",false);
				if (switchPos.contains("L") == true) {
					DriverStation.reportError("Middle Switch Left",false);
					addSequential(new Auto_DriveStraight(40));
					addSequential(new GyroTurnToAngle(-90));
					addSequential(new Auto_DriveStraight(70));
					addSequential(new GyroTurnToAngle(90));
					addSequential(new Auto_ArmToTime(0.8));
					addSequential(new Auto_DriveStraight(54));
					addSequential(new GrabberSolenoids());
				} else if (switchPos.contains("R") == true) {
					DriverStation.reportError("Middle Switch Right",false);
					addSequential(new Auto_DriveStraight(40));
					addSequential(new GyroTurnToAngle(90));
					addSequential(new Auto_DriveStraight(38));
					addSequential(new GyroTurnToAngle(-90));
					addSequential(new Auto_ArmToTime(0.8));
					addSequential(new Auto_DriveStraight(58));
					addSequential(new GrabberSolenoids());
				}
			} else if (startingPos == "R") {
				DriverStation.reportError("Right Auto",false);
				if (switchPos.contains("R") == true) {
					DriverStation.reportError("Right Switch",false);
					addSequential(new Auto_DriveStraight(149));
					addSequential(new Auto_ArmToTime(0.8));
					addSequential(new GyroTurnToAngle(-90));
					addSequential(new Auto_DriveStraight(27));
					addSequential(new GrabberSolenoids());
				} else {
					DriverStation.reportError("Right Auto No Options: Crossing Base Line",false);
					addSequential(new Auto_DriveStraight(130));
				}
			} else if (startingPos == "L") {
				DriverStation.reportError("Left Auto",false);
				if (switchPos.contains("L") == true) {
					DriverStation.reportError("Left Switch",false);
					addSequential(new Auto_DriveStraight(149));
					addSequential(new Auto_ArmToTime(0.8));					
					addSequential(new GyroTurnToAngle(90));
					addSequential(new Auto_DriveStraight(27));
					addSequential(new GrabberSolenoids());
				} else {
					DriverStation.reportError("Left Auto No Options: Crossing Base Line",false);
					addSequential(new Auto_DriveStraight(130));
				}
			}
		} else if (which == "scale") {
			if (startingPos == "R") {
				DriverStation.reportError("Right Auto",false);
				if (scalePos.contains("R") == true) {
					DriverStation.reportError("Right Scale",false);
					//SCALE CODE iF NO SWITCH ON RIGHT
					addSequential(new Auto_DriveStraight(304,0.6));
					addSequential(new GyroTurnToAngle(90));
					addSequential(new Auto_DriveBackwards(5));
					addSequential(new Auto_ArmUpToScale());
					addSequential(new GrabberSolenoids());
					addSequential(new Auto_DriveStraight(10));
				}
			} else if (startingPos == "L") {
				DriverStation.reportError("Left Auto",false);
				if (scalePos.contains("L") == true) {
					DriverStation.reportError("Left Scale",false);
//					SCALE CODE iF NO SWITCH ON LEFT
					addSequential(new Auto_DriveStraight(304,0.6));
					addSequential(new GyroTurnToAngle(-90));
					addSequential(new Auto_DriveBackwards(5));
					addSequential(new Auto_ArmUpToScale());
					addSequential(new GrabberSolenoids());
					addSequential(new Auto_DriveStraight(10));
				}
			}
		}
	}
}