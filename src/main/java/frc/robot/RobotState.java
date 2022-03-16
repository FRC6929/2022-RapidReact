// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/** Add your docs here. */
public class RobotState {
    
    public static boolean mod = true; //true = elevateur, false = shooter
    public static int level = 0; // 0 = lvl 1arr, 1 = lvl 2arr, 2 = lvl 2av, 3 = lvl 1av
    public static boolean bras = false; //true = stable, false = mobile

    public void reset()
    {
        mod = true;
        level = 0;
        bras = false;
    }
}
