package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotState;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Shooter;

// Honnetement je sait pas c'est censer etre quoi
// faq jai inventer ce incroyable nom
public class PushArm extends CommandBase{
    private Elevator m_elevator;

    private boolean m_target = false;

    public PushArm(Elevator e, boolean t){
        m_target = t;
        m_elevator = e;
    }
    
    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        if(RobotState.mode == false){
            System.out.println(m_target);
            m_elevator.Set_Arm(m_target);
        }
    }

    @Override
    public boolean isFinished()
    {
        return true;
    }
}