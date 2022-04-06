// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.RobotState;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ShooterPID extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Shooter m_shooter;
  private boolean m_jsp;
  private String m_hack;
  double angle;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShooterPID(Shooter shooter, double angle, boolean jsp, String hack) {
    m_shooter = shooter;
    m_jsp = jsp;
    m_hack = hack;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
    this.angle = angle;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotState.shooter_lvl = m_jsp;
    SmartDashboard.putBoolean("shooter_lvl", RobotState.shooter_lvl);
    SmartDashboard.putString("shooter_set", m_hack);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(RobotState.mode == true){
      m_shooter.ShooterArmPID(angle);
      }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}