/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ColorCommand;
import frc.robot.commands.PowerCellDetectorCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.VisionLightCommand;
import frc.robot.subsystems.ColorSpinner;
import frc.robot.subsystems.DetectorSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.Vision;
import edu.wpi.first.wpilibj2.command.Command;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ColorSpinner m_colorSpinner = new ColorSpinner();
  private final ShooterSubsystem m_shooterSubsystem =  new ShooterSubsystem();
  private final DetectorSubsystem m_detectorSubsystem = new DetectorSubsystem();
  private final ColorCommand m_colorCommand = new ColorCommand(m_colorSpinner);
  public static final Vision vision = new Vision();
  private final ShooterCommand m_shooterCommand = new ShooterCommand(m_shooterSubsystem);
  private final VisionLightCommand m_visionLightCommand = new VisionLightCommand();
 private final PowerCellDetectorCommand m_powerCellCommand = new PowerCellDetectorCommand(m_detectorSubsystem);
  public static OI oi;



  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    SmartDashboard.putNumber("ShooterMotor1", 500);
    SmartDashboard.putNumber("ShooterMotor2", 500);

    oi = new OI();
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    oi.colorButton.toggleWhenPressed(m_colorCommand);
    oi.shooterButton.whenHeld(m_shooterCommand);
    //oi.visionButton.whenHeld(m_visionLightCommand);
    oi.visionButton.toggleWhenPressed(m_visionLightCommand);
    oi.powerCellButton.whenHeld(m_powerCellCommand);
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_colorCommand;
  }
}
