// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SPI;

import com.kauailabs.navx.frc.AHRS;

public class Gyro extends SubsystemBase {
  private AHRS m_ahrs = new AHRS(SPI.Port.kMXP);

  private static final int kGyroPort = 0;

  /** Creates a new Gyro. */
  public Gyro() {}

  @Override
  public void periodic() {
    SmartDashboard.putNumber("gyro angle", m_ahrs.getAngle());
    // This method will be called once per scheduler run
  }
}
