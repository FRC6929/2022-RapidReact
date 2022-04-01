// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonome;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class AutoBouger extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Drivetrain m_drivetrain;

  private int temps;

  private double encodeur_start = 0;

  public double dist;
  public double speed;

  private boolean m_finished = false;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutoBouger(Drivetrain drivetrain, double a) {
    m_drivetrain = drivetrain;
    dist = a;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    temps = 0;
    encodeur_start = m_drivetrain.get_encoder();

    if(this.dist > 0){
      speed = -0.5f;
    }
    else if(this.dist < 0){
      speed = 0.5f;
    }
    else{
      end(true);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Devrait arreter immediatement
    m_drivetrain.drive(speed, 0);
    
    if(Math.abs(m_drivetrain.get_encoder() - encodeur_start) >= Math.abs(dist*0.16666666666666666666666666666667f)){
      end(true);
    }

    temps++;

    // Devrait continuer
    // this.schedule();
    //end(true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.drive(0, 0);
    this.m_finished = true;
    this.cancel();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return this.m_finished;
  }
}
//6929 Kuyvr é aure 