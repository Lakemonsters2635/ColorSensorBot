/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class ShooterCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ShooterSubsystem m_Shooter;
  private final  Constants m_constants;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShooterCommand(ShooterSubsystem subsystem) {
    m_Shooter = subsystem;
    m_constants =  Constants.getConstants();
 
    // Use addRequirements() here to declare subsystem dependencies.
    //addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {    
   // m_colorSpinner.determineTargetColor();
      System.out.println("shooter command initalized");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double motor1Adjust = RobotContainer.oi.leftStick.getRawAxis(3);
    double motor2Adjust = RobotContainer.oi.rightStick.getRawAxis(3);

    double motor1Speed = m_constants.SHOOTER_MOTOR_1_DEFAULT_SPEED + (1000 * motor1Adjust);
    double motor2Speed = m_constants.SHOOTER_MOTOR_2_DEFAULT_SPEED + (1000 * motor2Adjust);

    //double motor1Speed =  SmartDashboard.getNumber("ShooterMotor1", 0);
    //double motor2Speed =  SmartDashboard.getNumber("ShooterMotor2", 0);

    //System.out.println("Motor1 Speed: " + motor1Speed);
    //System.out.println("Motor2 Speed: " + motor2Speed);
    m_Shooter.SpinShooter(motor1Speed, motor2Speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  m_Shooter.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // if (m_CommandInitializationFailed) {
    //   System.out.println("Command initalization failed. Finished immediately.");
    //   return true;
    // }

    //return m_colorSpinner.spinFinished();
    // boolean colorFound =  m_colorSpinner.isFinished(m_targetColor);
    // if (colorFound) {
    //   System.out.println("Color '" +  m_fmsInfo.controlPanelTargetColor + "' found.");
    // }
    // return colorFound;
    return false;
  }
}
