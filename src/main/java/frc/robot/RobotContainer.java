// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ElevatorCommand;
import frc.robot.commands.MobileArmsDown;
import frc.robot.commands.MobileArmsUp;
import frc.robot.commands.PushArm;
import frc.robot.commands.PushBall;
import frc.robot.commands.ShooterIn;
import frc.robot.commands.ShooterOut;
import frc.robot.commands.StableArmDown;
import frc.robot.commands.StableArmUp;
import frc.robot.commands.autonome.AutoBouger;
import frc.robot.commands.autonome.AutoTourner;
import frc.robot.commands.autonome.Delay;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  public final Pneumatics m_pneumatics = new Pneumatics();
  public final Drivetrain m_drivetrain = new Drivetrain();
  public final Elevator m_elevator = new Elevator();
  private final ElevatorCommand m_commandelevator = new ElevatorCommand(m_elevator, m_drivetrain);
  // The robot's subsystems and commands are defined here...
  //private final AutoBouger m_autoBouger = new AutoBouger(m_drivetrain);
  private final AutoTourner m_autoTourner = new AutoTourner(m_drivetrain);

  private final Shooter m_shooter = new Shooter();

  private final Joystick m_Joystick = new Joystick(0);
  private final Joystick m_Copilote = new Joystick(1);
  private final Joystick m_Copilote2 = new Joystick(2);

  private RobotState m_robotstate;
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    JoystickButton buttonIn = new JoystickButton(m_Joystick, 5);
    JoystickButton triggerOut = new JoystickButton(m_Joystick, 1);
    JoystickButton button6 = new JoystickButton(m_Joystick, 6);
    JoystickButton push_arm_btn = new JoystickButton(m_Joystick, 7);
    JoystickButton push_ball_btn = new JoystickButton(m_Joystick, 7);

    JoystickButton co2_button3 = new JoystickButton(m_Copilote2, 3);
    JoystickButton co2_button4 = new JoystickButton(m_Copilote2, 4);
    JoystickButton co2_button1 = new JoystickButton(m_Copilote2, 1);
    JoystickButton co2_button2 = new JoystickButton(m_Copilote2, 2);


    m_drivetrain.init_drive();
    m_drivetrain.setDefaultCommand(
      new DriveCommand(m_Joystick, m_drivetrain)
    );
    buttonIn.whenHeld(new ShooterIn(m_shooter));
    triggerOut.whenHeld(new ShooterOut(m_shooter));
    button6.whenHeld(m_commandelevator);
    push_ball_btn.whenPressed(new PushBall(m_pneumatics));
    push_arm_btn.whenPressed(new PushArm(m_pneumatics));

    co2_button3.whenHeld(new StableArmUp(m_elevator));
    co2_button4.whenHeld(new StableArmDown(m_elevator));
    co2_button1.whenHeld(new MobileArmsUp(m_elevator));
    co2_button2.whenHeld(new MobileArmsDown(m_elevator));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return (new AutoBouger(m_drivetrain,100)).andThen(new Delay(1000)).andThen((new AutoBouger(m_drivetrain,-100)));
  }
}
//noadecoco
