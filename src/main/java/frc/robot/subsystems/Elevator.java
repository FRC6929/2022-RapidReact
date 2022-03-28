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
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Elevator extends SubsystemBase {
  private final CANSparkMax m_elevator_lf = new CANSparkMax(Constants.ConsElevator.EMoteur1, MotorType.kBrushless);
  private final CANSparkMax m_elevator_lm = new CANSparkMax(Constants.ConsElevator.EMoteur2, MotorType.kBrushless);
  private final CANSparkMax m_elevator_rf = new CANSparkMax(Constants.ConsElevator.EMoteur3, MotorType.kBrushless);
  private final CANSparkMax m_elevator_rm = new CANSparkMax(Constants.ConsElevator.EMoteur4, MotorType.kBrushless);

  private SparkMaxPIDController EArmController_lf;
  private SparkMaxPIDController EArmController_lm;
  private SparkMaxPIDController EArmController_rf;
  private SparkMaxPIDController EArmController_rm;

  private boolean hold_fixed = false;
  private boolean hold_mobile = false;

  private int lf_hold = 0;
  private int lm_hold = 0;
  private int rf_hold = 0;
  private int rm_hold = 0;

  // États
  // 0 : État initiale
  // 1 : Monte bras fixe
  // 2 (auto): Hold
  // 3 : Decendre les bras fixe
  // 4 (auto): Hold
  // 5 : Pneutmatique
  // 6 : Étendre bras  mobile
  // 7 (auto): Hold
  // 8 : Pneumatique
  // 9 : Monter bras fixe et Descendre bras mobile
  // 10 (auto): Hold
  // 11 : Monter bras fixe
  
  private int state_id = 0;

  /** Creates a new Elevator. */
  public Elevator() {
    //EArmController_lf = m_elevator_lf.getPIDController();
    //EArmController_lm = m_elevator_lm.getPIDController();
    //EArmController_rf = m_elevator_rf.getPIDController();
    //EArmController_rm = m_elevator_rm.getPIDController();
  }
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
    //double rotation = (dist*5*5*5*2)/360;
    //SmartDashboard.putNumber("PID Rotation", rotation);
    //EArmController_lf.setReference(rotation, CANSparkMax.ControlType.kPosition);
    //EArmController_lm.setReference(rotation, CANSparkMax.ControlType.kPosition);
    //EArmController_rf.setReference(rotation, CANSparkMax.ControlType.kPosition);
    //EArmController_rm.setReference(rotation, CANSparkMax.ControlType.kPosition);
  }

  public void StablePID(double dist) {

  }

  public void set_hold_fixe(boolean should_hold)
  {
    // Mettre valeur encodeur a hold
  }

  public void set_hold_stable(boolean should_hold){

  } 

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Etat", state_id);

    if(state_id == 1){

    }
    else if(state_id == 2){

    }
  }
}
