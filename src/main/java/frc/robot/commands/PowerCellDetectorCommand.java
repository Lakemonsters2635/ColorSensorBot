/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DetectorSubsystem;

public class PowerCellDetectorCommand extends CommandBase {
  private DetectorSubsystem m_detectorSubsystem;
  /**
   * Creates a new PowerCellDetectorCommand.
   */
  public PowerCellDetectorCommand(DetectorSubsystem detectorSubsystem) {
   m_detectorSubsystem = detectorSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("detector command initialized!!!!!!!!!!!!!!!!!!!");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    boolean powerCellFound = m_detectorSubsystem.powerCellFound();
    System.out.println("power cell found: " + powerCellFound);
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
