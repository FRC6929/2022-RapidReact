package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotState;
import frc.robot.subsystems.Pneumatics;

// Honnetement je sait pas c'est censer etre quoi
// faq jai inventer ce incroyable nom
public class PushArm extends CommandBase{
    private Pneumatics m_pneumatics;
    private boolean m_finished = false;

    public PushArm(Pneumatics p){
        m_pneumatics = p;
    }
    
    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        if(RobotState.mode == false){
            m_pneumatics.Toggle_Climber();
            this.m_finished = true;
        }
    }

    @Override
    public boolean isFinished()
    {
        return m_finished;
    }
}