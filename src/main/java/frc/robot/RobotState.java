// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public class RobotState {
    public static boolean mode = true; //true = shooter, false = elevateur
    public static boolean bras = true; //true = stable, false = mobile

    public static boolean shooter_lvl = false; // false lvl 1 , true lvl 2

    public static boolean control_stick = false; // false manette, true joystick
    public static boolean fast = false;
}
