// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  private final CANSparkMax m_shooterCanSparkMax = new CANSparkMax(Constants.ShooterCST.Moteurshooter, MotorType.kBrushless);
  private final CANSparkMax m_shooterCanSparkMax2 = new CANSparkMax(Constants.ShooterCST.Moteurshooter2, MotorType.kBrushless);

  /** Creates a new Shooter. */
  public Shooter() {}
  public void run(Double Speed,Double Speed2) {
  m_shooterCanSparkMax.set(Speed);
  m_shooterCanSparkMax2.set(Speed2);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
