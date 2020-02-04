/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;




/**
 * An example command that uses an example subsystem.
 */
public class VisionLightCommand extends CommandBase {

    public VisionLightCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {    
     // m_colorSpinner.determineTargetColor();
        System.out.println("light command initalized");
        if (RobotContainer.vision.isLightOn()) {
            System.out.println("light is on. Turn off");
            RobotContainer.vision.ledOff();
        }
        else {
            RobotContainer.vision.ledOn();
            System.out.println("light is off. Turn on");
        }
    
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        //System.out.println("Vision light cmd execute");
        // RobotContainer.vision.data();

        // RobotContainer.vision.printArea();
        
    }

    

    // Called once after isFinished returns true
    protected void end() {
        //Robot.vision.driveMode();
        // if (RobotContainer.vision.isLightOn()) {
        //     RobotContainer.vision.ledOff();
        //     System.out.println("END light is on. Turn off");
        // }
        // else {
        //     RobotContainer.vision.ledOn();
        //     System.out.println("END light is off. Turn on");
        // }
    }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;                     
  }
 // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end(true);
    }

  

}
