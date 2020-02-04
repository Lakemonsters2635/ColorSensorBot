package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;


/**
 *
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

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        //Robot.vision.driveMode();
        if (RobotContainer.vision.isLightOn()) {
            RobotContainer.vision.ledOff();
            System.out.println("END light is on. Turn off");
        }
        else {
            RobotContainer.vision.ledOn();
            System.out.println("END light is off. Turn on");
        }
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}