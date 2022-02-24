// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
public class Elevator extends SubsystemBase {
  private final CANSparkMax m_elevator = new CANSparkMax(Constants.elevator.Moteur1, MotorType.kBrushless);
  /** Creates a new Elevator. */
  public Elevator() {}

  public void run(Double speed) {
    m_elevator.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
