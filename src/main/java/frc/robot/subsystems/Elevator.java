// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Elevator extends SubsystemBase {
  private final CANSparkMax m_elevator1 = new CANSparkMax(Constants.ConsElevator.EMoteur1, MotorType.kBrushless);
  private final CANSparkMax m_elevator2 = new CANSparkMax(Constants.ConsElevator.EMoteur2, MotorType.kBrushless);
  private final CANSparkMax m_elevator3 = new CANSparkMax(Constants.ConsElevator.EMoteur3, MotorType.kBrushless);
  private final CANSparkMax m_elevator4 = new CANSparkMax(Constants.ConsElevator.EMoteur4, MotorType.kBrushless);
  /** Creates a new Elevator. */
  public Elevator() {}
  private AHRS m_ahrs = new AHRS(SPI.Port.kMXP);

  private static final int kGyroPort = 0;
  public void run(Double speed) {
    m_elevator1.set(speed);
    m_elevator2.set(speed);
    m_elevator3.set(speed);
    m_elevator4.set(speed);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("gyro angle", m_ahrs.getPitch());
    // This method will be called once per scheduler run
  }
}
