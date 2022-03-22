// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.RobotState;
import frc.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
/** An example command that uses an example subsystem. */
public class EArmDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Elevator m_stable_arm;
  double speed;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public EArmDrive(Elevator stable_arm, double speed) {
    this.speed = speed;
    m_stable_arm = stable_arm;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_stable_arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler StableDrives while the command is scheduled.
  @Override
  public void execute() {
    if(RobotState.mode == false && RobotState.bras == true){
      m_stable_arm.StableDrive(speed);
    }
    else if(RobotState.mode == false && RobotState.bras == false)
    {
      m_stable_arm.MobileDrive(speed);
    }
  } 

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_stable_arm.StableDrive(0.0);
    m_stable_arm.MobileDrive(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
//6929 Kuyvr é aure 
