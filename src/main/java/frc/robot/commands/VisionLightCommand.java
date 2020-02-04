/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.model.FMSInfo;
import frc.robot.subsystems.ColorMatcher;
import frc.robot.subsystems.ColorSpinner;
import frc.robot.subsystems.Vision;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class VisionLightCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Vision m_Vision;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public VisionLightCommand(Vision subsystem) {
    m_Vision = subsystem;
 
    // Use addRequirements() here to declare subsystem dependencies.
    //addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {   
    m_Vision.ledOn(); 
    System.out.println("vision light command initalized");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //System.out.println("Vision light cmd execute");
   m_Vision.data();

    m_Vision.printArea();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Vision.ledOff();
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
