// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  private final CANSparkMax m_shooter1 = new CANSparkMax(Constants.ConsShooter.Moteur1, MotorType.kBrushless);
  private final CANSparkMax m_shooter2 = new CANSparkMax(Constants.ConsShooter.Moteur2, MotorType.kBrushless);
  private final CANSparkMax m_pivot = new CANSparkMax(Constants.ConsShooter.Moteur3, MotorType.kBrushless);
  RelativeEncoder ShooterArmEncoder;
  SparkMaxPIDController ShooterArmController;
  private final DigitalInput limit = new DigitalInput(1);

  /** Creates a new Shooter. */

  public Shooter() {
    m_pivot.restoreFactoryDefaults();

    ShooterArmController = m_pivot.getPIDController();
    ShooterArmEncoder = m_pivot.getEncoder();

    //PID Parameters
    ShooterArmController.setP(0.001);
    ShooterArmController.setI(0.001);
    ShooterArmController.setD(0);
  }

  public boolean getSwitch(){
    return limit.get();
  }

  public void ShooterArmPID(double angle) {
    double rotation = angle*5*5*5*2*360;
    ShooterArmController.setReference(rotation, CANSparkMax.ControlType.kPosition);
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("switch", getSwitch());
    // This method will be called once per scheduler run
  }
  
  public void ShooterRollerDrive(double speed) {
    m_shooter1.set(speed);
    m_shooter2.set(-speed);
  }

  public void ShooterArmDrive(double speed) { 
    m_pivot.set(speed);
  }
}