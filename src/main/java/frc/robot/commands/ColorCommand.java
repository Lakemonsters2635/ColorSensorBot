/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.model.FMSInfo;
import frc.robot.subsystems.ColorMatcher;
import frc.robot.subsystems.ColorSpinner;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class ColorCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ColorSpinner m_colorSpinner;
  private FMSInfo m_fmsInfo;
  private Color m_targetColor;
  private boolean m_CommandInitializationFailed = false;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ColorCommand(ColorSpinner subsystem) {
    m_colorSpinner = subsystem;
 
    // Use addRequirements() here to declare subsystem dependencies.
    //addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {    
    m_colorSpinner.determineTargetColor();
      System.out.println("color command initalized");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //FHE: Do we need this?
    m_colorSpinner.spinToTargetColor();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
 
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (m_CommandInitializationFailed) {
      System.out.println("Command initalization failed. Finished immediately.");
      return true;
    }

    return false;
    // boolean colorFound =  m_colorSpinner.isFinished(m_targetColor);
    // if (colorFound) {
    //   System.out.println("Color '" +  m_fmsInfo.controlPanelTargetColor + "' found.");
    // }
    // return colorFound;
  }


  public static FMSInfo getFMSInfo()
	{
    FMSInfo fmsInfo = new FMSInfo();
    try {
      DriverStation driveStation= DriverStation.getInstance();
      
      System.out.println("FMS Attached: " + driveStation.isFMSAttached());
        
      //FHE: How do we test "IsFMSAttached())
    
      String gameSpecificMessage = "";

        
      //driveStation.waitForData(); //FHE: DO WE NEED THIS?
      fmsInfo.alliance = driveStation.getAlliance();
      gameSpecificMessage = driveStation.getGameSpecificMessage();

      fmsInfo.controlPanelTargetColor = gameSpecificMessage.trim().toUpperCase().charAt(0);
      fmsInfo.driveStation = driveStation.getLocation();
      fmsInfo.isAutonomous = driveStation.isAutonomous();
      fmsInfo.isInitalized = true;
    } catch(Exception err) {
      System.out.println("ERROR Getting FMS Info: " + err.getMessage());
    }
		
		return fmsInfo;
	}

}
