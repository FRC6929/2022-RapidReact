// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/** Add your docs here. */
public class RobotState {
    
    public static boolean mode = true; //true = shooter, false = elevateur
    public static boolean bras = true; //true = stable, false = mobile

    public void Reset()
    {
        mode = true;
        bras = false;
    }
}
