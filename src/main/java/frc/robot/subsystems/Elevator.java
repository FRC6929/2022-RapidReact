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
  private final CANSparkMax m_elevator_lf = new CANSparkMax(Constants.ConsElevator.EMoteur1, MotorType.kBrushless);
  private final CANSparkMax m_elevator_lm = new CANSparkMax(Constants.ConsElevator.EMoteur2, MotorType.kBrushless);
  private final CANSparkMax m_elevator_rf = new CANSparkMax(Constants.ConsElevator.EMoteur3, MotorType.kBrushless);
  private final CANSparkMax m_elevator_rm = new CANSparkMax(Constants.ConsElevator.EMoteur4, MotorType.kBrushless);

  private boolean hold_fixed = false;
  private boolean hold_mobile = false;

  private int lf_hold = 0;
  private int lm_hold = 0;
  private int rf_hold = 0;
  private int rm_hold = 0;

  /** Creates a new Elevator. */
  public Elevator() {}
  private AHRS m_ahrs = new AHRS(SPI.Port.kMXP);

  private static final int kGyroPort = 0;
  public void StableDrive(Double speed) {
    m_elevator_lf.set(speed);
    m_elevator_rf.set(-speed);
  }

  public void MobileDrive(Double speed) {
    m_elevator_lm.set(-speed);
    m_elevator_rm.set(speed);
  }

  public void MobilePID(double dist) {

  }

  public void StablePID(double dist) {

  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("gyro angle", m_ahrs.getPitch());
    // This method will be called once per scheduler run
  }
}
