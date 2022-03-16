// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj2.command.CommandBase;
/** An example command that uses an example subsystem. */
public class MobileArmsUp extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Elevator m_mobile_arm;
  private boolean ended = false;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public MobileArmsUp(Elevator mobile_arm) {
    
    m_mobile_arm = mobile_arm;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_mobile_arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_mobile_arm.run(0.2);
      }
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_mobile_arm.run(0.0);
    ended = true;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
//6929 Kuyvr é aure 
