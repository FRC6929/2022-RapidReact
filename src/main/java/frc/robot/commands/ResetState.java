package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotState;
import frc.robot.subsystems.Elevator;

public class ResetState extends CommandBase{
    private Elevator m_elevator;

    public ResetState(Elevator e){
        m_elevator = e;
        addRequirements(e);
    }
    
    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        if(!RobotState.mode){
            System.out.println("Reset elevator state");
            m_elevator.state_id = -1;
        }
    }

    @Override
    public boolean isFinished()
    {
        return true;
    }
}