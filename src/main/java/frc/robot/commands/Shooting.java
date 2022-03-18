// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.RobotState;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class Shooting extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Shooter m_shooter;
  private Pneumatics m_pneumatics;
  boolean lvl;
  private double m_time;
  private long m_start;
  private boolean m_finished = false;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Shooting(Shooter shooter,Pneumatics pneumatics, boolean lvl) {
    m_shooter = shooter;
    this.lvl = lvl;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_start = System.currentTimeMillis();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(lvl){ // true = lvl1
      m_shooter.ShooterRollerDrive(.5);
      if(System.currentTimeMillis() - m_start >= 500){
        m_pneumatics.Toggle_Ball_Pusher();
        this.end(true);
      } 

    }
    else{
      m_shooter.ShooterRollerDrive(.8);
      if(System.currentTimeMillis() - m_start >= 500){
        m_pneumatics.Toggle_Ball_Pusher();
        this.end(true);
      } 
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_finished = true;
    m_shooter.ShooterRollerDrive(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_finished;
  }
}
//6929 Kuyvr é aure 