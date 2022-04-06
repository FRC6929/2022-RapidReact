package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotState;
import frc.robot.subsystems.Elevator;

public class NextState extends CommandBase{
    private Elevator m_elevator;

    public NextState(Elevator e){
        m_elevator = e;
        addRequirements(e);
    }
    
    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        if(!RobotState.mode){
            System.out.println("Next elevator state");
            m_elevator.state_id++;
        }
    }

    @Override
    public boolean isFinished()
    {
        return true;
    }
}