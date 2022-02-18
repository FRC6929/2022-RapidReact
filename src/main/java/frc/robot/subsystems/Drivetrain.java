// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.SPI;

import com.kauailabs.navx.frc.AHRS;

public class Drivetrain extends SubsystemBase {
  private final CANSparkMax m_drive_fl = new CANSparkMax(Constants.ConsDrivetrain.MoteurGaucheAvant, MotorType.kBrushless);
  private final CANSparkMax m_drive_fr = new CANSparkMax(Constants.ConsDrivetrain.MoteurDroitAvant, MotorType.kBrushless);
  private final CANSparkMax m_drive_bl = new CANSparkMax(Constants.ConsDrivetrain.MoteurGauche, MotorType.kBrushless);
  private final CANSparkMax m_drive_br = new CANSparkMax(Constants.ConsDrivetrain.MoteurDroit, MotorType.kBrushless);

  private final MotorControllerGroup m_leftFollower = new MotorControllerGroup(m_drive_fl, m_drive_bl);
  private final MotorControllerGroup m_rightFollower = new MotorControllerGroup(m_drive_fr, m_drive_br);

  private final DifferentialDrive m_DifferentialDrive = new DifferentialDrive(m_leftFollower, m_rightFollower);
  private final DifferentialDrive m_BackWheels = new DifferentialDrive(m_drive_bl, m_drive_br);

  private AHRS m_ahrs = new AHRS(SPI.Port.kMXP);

  private static final int kGyroPort = 0;

  /** Creates a new drivetrain. */
  public Drivetrain() {
    init_drive();
  }
  

  @Override
  public void periodic() {
    SmartDashboard.putNumber("motor speed", get_speed());
    SmartDashboard.putNumber("motor encoder", get_encoder());
    SmartDashboard.putNumber("gyro angle", m_ahrs.getAngle());
    // This method will be called once per scheduler run
  }

  // Gros hack de maxence
  public void init_drive()
  {
    m_drive_fr.restoreFactoryDefaults();
    m_drive_fl.restoreFactoryDefaults();
    m_drive_br.restoreFactoryDefaults();
    m_drive_bl.restoreFactoryDefaults();
  }

  public double get_encoder(){
    return m_drive_fl.getEncoder().getPosition();
  }

  public double get_speed(){
    return m_drive_fl.getEncoder().getVelocity();
  }

  public void drive(double speed, double rot) {
    m_DifferentialDrive.arcadeDrive(speed, -rot);
  }
  public void back(double speed, double rot) {
    m_BackWheels.arcadeDrive(-speed, -rot);
  }
}
//hack robot