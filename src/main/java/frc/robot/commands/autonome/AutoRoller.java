// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonome;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class AutoRoller extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Shooter m_shooter;

  public boolean fin;

  private boolean m_finished = false;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutoRoller(Shooter shooter, Boolean a) {
    m_shooter = shooter;
    fin = a;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(fin == false){
      m_shooter.ShooterRollerDrive(-0.5f);
    }
    else{
      m_shooter.ShooterRollerDrive(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return this.m_finished;
  }
}
//6929 Kuyvr é aure 