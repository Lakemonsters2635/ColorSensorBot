/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.model.FMSInfo;
import frc.robot.subsystems.ColorMatcher;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class ColorCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ColorMatcher m_colorMatcher;
  private FMSInfo m_fmsInfo;
  private Color m_targetColor;
  private boolean m_CommandInitializationFailed = false;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ColorCommand(ColorMatcher subsystem) {
    m_colorMatcher = subsystem;
 
    // Use addRequirements() here to declare subsystem dependencies.
    //addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

     m_fmsInfo = getFMSInfo();
    if (m_fmsInfo.isInitalized) {
      switch(m_fmsInfo.controlPanelTargetColor) {
        case 'R':
          m_targetColor = ColorMatcher.kRedTarget;
          break;
        case 'G':
          m_targetColor = ColorMatcher.kGreenTarget;
          break;
        case 'B':
          m_targetColor = ColorMatcher.kBlueTarget;
          break;   
        case 'Y':
        m_targetColor = ColorMatcher.kYellowTarget;
          break;                     
        default:
          m_CommandInitializationFailed = true;
      }

      System.out.println("color command initalized");
    } else {
      System.out.println("Could not get color info from FMS");
      m_CommandInitializationFailed = true;
    }

   
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //FHE: Do we need this?
    m_colorMatcher.get_color();
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
    return m_colorMatcher.isFinished(m_targetColor);
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
