package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotState;
import frc.robot.subsystems.Shooter;

// Push deez balls - Maxence(encore)
public class PushBall extends CommandBase{
    private Shooter m_shooter;
    private boolean m_finished = false;

    public PushBall(Shooter s){
        m_shooter = s;
    }
    
    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        if(RobotState.mode == true){
            m_shooter.Toggle_Pusher();
            this.m_finished = true;
        }
    }

    @Override
    public boolean isFinished()
    {
        return m_finished;
    }
}


//where ball?