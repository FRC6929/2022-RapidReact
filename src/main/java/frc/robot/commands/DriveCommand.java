// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Joystick;

public class DriveCommand extends CommandBase {
  private final Drivetrain m_drivetrain;

  private final Joystick m_joystick;

  public DriveCommand(Joystick j, Drivetrain drivetrain) {
    m_drivetrain = drivetrain;
    m_joystick = j;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
//    SmartDashboard.putNumber("xSpeed", m_joystick.getY());
//    SmartDashboard.putNumber("rot", m_joystick.getX());
    m_drivetrain.drive(m_joystick.getX()*0.8, m_joystick.getY());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.drive(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
