// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.EArmDrive;
import frc.robot.commands.PushArm;
import frc.robot.commands.PushBall;
import frc.robot.commands.SetBras;
import frc.robot.commands.SetMode;
import frc.robot.commands.ShooterArmDrive;
import frc.robot.commands.ShooterPID;
import frc.robot.commands.ShooterRollerDrive;
import frc.robot.commands.Shooting;
import frc.robot.commands.autonome.AutoBouger;
import frc.robot.commands.autonome.AutoTourner;
import frc.robot.commands.autonome.Delay;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
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
  public final Camera m_camera = new Camera();
  // The robot's subsystems and commands are defined here...
  //private final AutoBouger m_autoBouger = new AutoBouger(m_drivetrain);
  private final AutoTourner m_autoTourner = new AutoTourner(m_drivetrain);

  private final Shooter m_shooter = new Shooter();

  private final Joystick m_Joystick = new Joystick(0);
  private final Joystick m_Copilote = new Joystick(1); // Buttons copilote
  private final Joystick m_Copilote2 = new Joystick(2); // Joystick copilote

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
    JoystickButton push_arm_btn = new JoystickButton(m_Joystick, 5);
    JoystickButton push_ball_btn = new JoystickButton(m_Joystick, 1);

    JoystickButton co2_JsUp = new JoystickButton(m_Copilote2, 3);
    JoystickButton co2_JsDown = new JoystickButton(m_Copilote2, 4);
    JoystickButton co2_JsLeft = new JoystickButton(m_Copilote2, 1);
    JoystickButton co2_JsRight = new JoystickButton(m_Copilote2, 2);

    JoystickButton co_ShooterMode = new JoystickButton(m_Copilote, 8);
    JoystickButton co_ElevatorMode = new JoystickButton(m_Copilote, 9);
    JoystickButton co_FixeMode_Lvl1 = new JoystickButton(m_Copilote, 4);
    JoystickButton co_MobileMode_Intake = new JoystickButton(m_Copilote, 5);

    m_drivetrain.init_drive();
    m_drivetrain.setDefaultCommand(new DriveCommand(m_Joystick, m_drivetrain));


    push_ball_btn.whenPressed( (new PushBall(m_pneumatics)).andThen(new Delay(1000)).andThen(new PushBall(m_pneumatics)));
    //push_arm_btn.whenPressed(new PushArm(m_pneumatics));

    //Bras mobile et fixe
    co2_JsUp.whenHeld(new EArmDrive(m_elevator, 0.5));
    co2_JsDown.whenHeld(new EArmDrive(m_elevator, -0.3));
    co2_JsLeft.whenHeld(new PushArm(m_pneumatics, false));
    co2_JsLeft.whenHeld(new PushArm(m_pneumatics, true));

    //Rouleaux et bras du shooter
    co2_JsLeft.whenHeld(new ShooterArmDrive(m_shooter, -0.5));
    co2_JsRight.whenHeld(new ShooterArmDrive(m_shooter, 0.5));
    co2_JsUp.whenHeld(new ShooterRollerDrive(m_shooter, -0.3));
    co2_JsDown.whenHeld(new ShooterRollerDrive(m_shooter, 0.2));

    //Paramétrage des modes
    co_ShooterMode.whenPressed(new SetMode(m_camera, true));
    co_ElevatorMode.whenPressed(new SetMode(m_camera, false));
    co_FixeMode_Lvl1.whenPressed(new SetBras(true));
    co_MobileMode_Intake.whenPressed(new SetBras(false));

    // Shooter Positions
    co_FixeMode_Lvl1.whenPressed(new ShooterPID(m_shooter, -10)); // 1 AV

    //co_Shoot.whenPressed(new Shooting(m_shooter, m_pneumatics, true));
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