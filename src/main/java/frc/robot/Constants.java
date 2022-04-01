// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static class ConsDrivetrain {
        public static final int MoteurGaucheAvant = 1;
        public static final int MoteurDroitAvant = 2;
        public static final int MoteurGauche = 3;
        public static final int MoteurDroit = 4;
        
    } 
    public static class ConsShooter {
        public static final int Moteur1 = 5;
        public static final int Moteur2 = 6;
        public static final int Moteur3 = 7;

        public static final int RigthSwitch = 0;
        public static final int LeftSwitch = 1;

        public static final int p_lvl1_back = 0;
        public static final int p_lvl1_front = 30;
        public static final int p_lvl_intake = 80;
        public static final int p_lvl2_front = 30;
        public static final int p_lvl2_back = 5;

    } 
    public static class ConsElevator {
        public static final int EMoteur1 = 8;
        public static final int EMoteur2 = 10;
        public static final int EMoteur3 = 9;
        public static final int EMoteur4 = 11;

        // Difference de valeur d'encodeur entre la
        // position étendu et celle retracté
        public static final int lf_length = 132;
        public static final int lm_length = -95;
        public static final int rf_length = -132;
        public static final int rm_length = 95;

        public static final float fdeadzone = 1.5f;
        public static final float mdeadzone = 1.5f;

        public static final float fixe_speed = 0.4f;
        public static final float mobile_speed = 0.4f;
    }
    public static class pneumatic {
        public static class arm1  {
            public static final int port1 = 0;
            public static final int port2 = 1;
        }
        public static class arm2  {
            public static final int port1 = 6;
            public static final int port2 = 7;
        }
    }
}
