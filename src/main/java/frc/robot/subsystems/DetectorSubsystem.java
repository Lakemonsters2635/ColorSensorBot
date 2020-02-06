/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class DetectorSubsystem extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  private DigitalInput powerCellSensor;
   public DetectorSubsystem() {
    powerCellSensor = new DigitalInput(0);
  
  }
  public boolean powerCellFound() {
    boolean cellFound = !powerCellSensor.get();
    SmartDashboard.putBoolean("Power cell found", cellFound);
    return cellFound;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
