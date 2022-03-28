package frc.robot.commands.autonome;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Delay extends CommandBase {
  private boolean m_finished = false;
  private double m_time;
  private long m_start;

  /** Creates a new Delay. */
  public Delay(double a) {
    m_time = a;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_finished = false;
    m_start = System.currentTimeMillis();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //SmartDashboard.putNumber("current time", System.currentTimeMillis());
    //SmartDashboard.putNumber("start time", m_start);
    //SmartDashboard.putNumber("time", m_time);
    //SmartDashboard.putNumber("elapsed", System.currentTimeMillis() - m_start);
    if(System.currentTimeMillis() - m_start >= m_time){
      this.end(true);
    } 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.m_finished = true;
    this.cancel();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return this.m_finished;
  }
}
