// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  private final CANSparkMax m_shooter1 = new CANSparkMax(Constants.ConsShooter.Moteur1, MotorType.kBrushless);
  private final CANSparkMax m_shooter2 = new CANSparkMax(Constants.ConsShooter.Moteur2, MotorType.kBrushless);
  private CANSparkMax m_pivot = new CANSparkMax(Constants.ConsShooter.Moteur3, MotorType.kBrushless);
  private RelativeEncoder ShooterArmEncoder;
  private SparkMaxPIDController ShooterArmController;
  private final DigitalInput limit = new DigitalInput(1);

  private DoubleSolenoid m_pusher = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.pneumatic.arm2.port1, Constants.pneumatic.arm2.port2);
  private boolean m_pusher_state = false;

  /** Creates a new Shooter. */

  public Shooter() {
    m_pivot.restoreFactoryDefaults();
    m_pivot.getEncoder().setPosition(0.0f);

    ShooterArmController = m_pivot.getPIDController();
    ShooterArmEncoder = m_pivot.getEncoder();

    m_pusher.set(Value.kReverse);

    //PID Parameters
    // P est necessaire
    // I est probablement utile
    // D est overkill pour un robot frc selon la documentation
    ShooterArmController.setP(.015);
    ShooterArmController.setI(0.0000005f);
    ShooterArmController.setD(0);
  }

  public boolean getSwitch(){
    return limit.get();
  }

  public void ShooterArmPID(double angle) {
    //double rotation = (angle*5*5*5*2)/360;
    SmartDashboard.putNumber("PID Rotation", angle);
    ShooterArmController.setReference(angle, CANSparkMax.ControlType.kPosition);
  }

  public void Toggle_Pusher(){
    if(m_pusher_state){
      SmartDashboard.putString("pstate", "forward");
      m_pusher.set(Value.kReverse);
      m_pusher_state = false;
    }
    else{
      SmartDashboard.putString("pstate", "reverse");
      m_pusher.set(Value.kForward);
      m_pusher_state = true;
    }
  }

  public void Pusher_Reset(){
    SmartDashboard.putString("pstate", "forward");
    m_pusher.set(Value.kReverse);
  }

  public void Shooter_Reset(){
    m_pivot.restoreFactoryDefaults();
    m_pivot.getEncoder().setPosition(0.0f);
    ShooterArmController.setReference(0, CANSparkMax.ControlType.kPosition);

    ShooterArmController = m_pivot.getPIDController();
    ShooterArmEncoder = m_pivot.getEncoder();
  }

  @Override
  public void periodic() {
    //SmartDashboard.putBoolean("switch", getSwitch());

    SmartDashboard.putNumber("Pivot Positon", m_pivot.getEncoder().getPosition());
    //SmartDashboard.putNumber("",);
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