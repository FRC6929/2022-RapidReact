// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.RobotState;
import frc.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj2.command.CommandBase;
/** An example command that uses an example subsystem. */
public class EMobileArmDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Elevator m_mobile_arm;
  double speed;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public EMobileArmDrive(Elevator stable_arm, double speed) {
    this.speed = speed;
    m_mobile_arm = stable_arm;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_mobile_arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("A");
    //if(RobotState.mode == false && RobotState.bras == false){
      m_mobile_arm.MobileDrive(speed);

      System.out.println("C");
    //  System.out.println("Ca le fait la");
    //}
    //else{
    //  System.out.println("What the hell");
    //}
  }
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_mobile_arm.MobileDrive(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
//6929 Kuyvr é aure 
