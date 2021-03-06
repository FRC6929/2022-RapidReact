// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ToggleBPusher extends CommandBase {
  /** Creates a new SetMode. */
  Shooter m_shooter;

  boolean m_finished = false;
  boolean switched = false;

  public ToggleBPusher(Shooter p) {
    m_shooter = p;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      switched = false;
      m_finished = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("aumoin ca lexecute une fois");
    if(!switched){
        System.out.println("Auto switch");
        m_shooter.Toggle_Pusher();
        switched = true;
    }
    m_finished = true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
