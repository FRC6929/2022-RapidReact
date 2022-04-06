// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotState;
import frc.robot.subsystems.Elevator;

public class BackState extends CommandBase {
  private Elevator m_elevator;
  private long last_switch = 0;
  /** Creates a new BackState. */
  public BackState(Elevator e) {
    m_elevator = e;
    addRequirements(e);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    last_switch = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(!RobotState.mode){
      if(System.currentTimeMillis() > last_switch +  + 1000){
          System.out.println("Next elevator state");
          m_elevator.state_id--;
          last_switch = System.currentTimeMillis();
      }
      else{
          System.out.println("Switch trop rapide");
      }
  }
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
