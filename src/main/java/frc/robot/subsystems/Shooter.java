// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  private final CANSparkMax m_shooter1 = new CANSparkMax(Constants.ConsShooter.Moteur1, MotorType.kBrushless);
  private final CANSparkMax m_shooter2 = new CANSparkMax(Constants.ConsShooter.Moteur2, MotorType.kBrushless);
  private final DigitalInput limit = new DigitalInput(1);

  /** Creates a new Shooter. */

  public Shooter() {}

  public boolean getSwitch(){
    return limit.get();
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("switch", getSwitch());

    // This method will be called once per scheduler run
  }
  public void shooterdrive(double speed) {
    
    m_shooter1.set(speed);
    m_shooter2.set(-speed);

  }
}
