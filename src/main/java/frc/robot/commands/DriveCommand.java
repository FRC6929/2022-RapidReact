// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.RobotState;
import frc.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Joystick;

public class DriveCommand extends CommandBase {
  private final Drivetrain m_drivetrain;

  private final Joystick m_joystick;
  private final Joystick m_gamepad;

  double ty;
  double rx;
  double mult;

  public DriveCommand(Joystick j, Joystick g, Drivetrain drivetrain) {
    m_drivetrain = drivetrain;
    m_joystick = j;
    m_gamepad = g;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}
//safs
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(RobotState.control_stick){
      mult = (-m_joystick.getRawAxis(3)*0.2)+0.8;
      ty = -m_joystick.getY();
      rx = -m_joystick.getX();
      m_drivetrain.drive(ty*mult, rx*mult);
    }
    else{
      if(RobotState.fast == true){
        mult = Constants.ConsDrivetrain.fastSpeed;
      }
      else{
        mult = Constants.ConsDrivetrain.slowSpeed;
      }
      rx = -m_gamepad.getRawAxis(0);
      ty = (m_gamepad.getRawAxis(2)-m_gamepad.getRawAxis(3))*0.9;

      m_drivetrain.drive(ty*mult, rx*mult);
    }
    
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