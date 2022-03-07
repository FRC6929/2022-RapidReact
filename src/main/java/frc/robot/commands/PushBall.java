package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Pneumatics;

// Push deez balls - Maxence(encore)
public class PushBall extends CommandBase{
    private Pneumatics m_pneumatics;
    private boolean m_finished = false;

    public PushBall(Pneumatics p){
        m_pneumatics = p;
    }
    
    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        SmartDashboard.putString("test", "12");
        m_pneumatics.Toggle_Ball_Pusher();
        this.m_finished = true;
    }

    @Override
    public boolean isFinished()
    {
        return m_finished;
    }
}
