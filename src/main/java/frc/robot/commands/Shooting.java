// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.RobotState;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class Shooting extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private Shooter m_shooter;
  private Pneumatics m_pneumatics;

  private boolean m_finished = false;
  
  public Shooting(Shooter shooter,Pneumatics pneumatics) {
    m_shooter = shooter;
    m_pneumatics = pneumatics;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter,pneumatics);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(!RobotState.shooter_lvl){
      m_shooter.ShooterRollerDrive(-0.5f);
    }
    else{
      m_shooter.ShooterRollerDrive(-0.8f);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_pneumatics.Toggle_Ball_Pusher();
    m_shooter.ShooterRollerDrive(0);
    m_finished = true;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}



//6929 Kuyvr é aure 