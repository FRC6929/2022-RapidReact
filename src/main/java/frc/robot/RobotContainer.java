// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.*;
import frc.robot.commands.autonome.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
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

    JoystickButton co_Hold_Lvl1_arr = new JoystickButton(m_Copilote, 1);
    // J'ai pas le tableau de controle faq je sait pas c quel button
    JoystickButton co_Reset = new JoystickButton(m_Copilote, 2);
    JoystickButton co_Next = new JoystickButton(m_Copilote, 6);
    JoystickButton co_Shoot = new JoystickButton(m_Copilote, 7);

    m_drivetrain.init_drive();
    m_drivetrain.setDefaultCommand(new DriveCommand(m_Joystick, m_drivetrain));


    push_ball_btn.whenPressed( (new PushBall(m_shooter)).andThen(new Delay(1000)).andThen(new PushBall(m_shooter)));
    //push_arm_btn.whenPressed(new PushArm(m_pneumatics));

    //Bras mobile et fixe
    co2_JsUp.whenHeld(new EArmDrive(m_elevator, 0.5));
    co2_JsDown.whenHeld(new EArmDrive(m_elevator, -0.3));
    co2_JsLeft.whenHeld(new PushArm(m_elevator, false));
    co2_JsRight.whenHeld(new PushArm(m_elevator, true));

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
    co_FixeMode_Lvl1.whenPressed(new ShooterPID(m_shooter, Constants.ConsShooter.p_lvl1_front,false)); // 1 AV
    co_Hold_Lvl1_arr.whenPressed(new ShooterPID(m_shooter, Constants.ConsShooter.p_lvl1_back,false));
    co_MobileMode_Intake.whenPressed(new ShooterPID(m_shooter,Constants.ConsShooter.p_lvl_intake,false));

    co_Shoot.whenHeld(new Shooting(m_shooter));
    co_Shoot.whenReleased(new Delay(1000).andThen(new ToggleBPusher(m_shooter)));

    // C un peu plus compliquer que ca devrait etre, donc je vais attendre de pouvoir tester
    // avant de faire en sorte que ca prenne les deux boutons
    co_Next.whenPressed(new NextState(m_elevator));
    co_Reset.whenPressed(new ResetState(m_elevator));
  }

  public Command getAutonomousCommand() {
    return (new AutoBouger(m_drivetrain,100)).andThen(new Delay(1000));//.andThen((new AutoBouger(m_drivetrain,-100)));
  }
}