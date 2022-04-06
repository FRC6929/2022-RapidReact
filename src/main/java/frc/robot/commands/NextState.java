package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotState;
import frc.robot.subsystems.Elevator;

public class NextState extends CommandBase{
    private Elevator m_elevator;
    private long last_switch = 0;

    public NextState(Elevator e){
        m_elevator = e;
        addRequirements(e);
    }
    
    @Override
    public void initialize(){
        last_switch = 0;
    }

    @Override
    public void execute(){
        if(!RobotState.mode){
            if(System.currentTimeMillis() > last_switch +  + 1000){
                System.out.println("Next elevator state");
                m_elevator.state_id++;
                last_switch = System.currentTimeMillis();
            }
            else{
                System.out.println("Switch trop rapide");
            }
        }
    }

    @Override
    public boolean isFinished()
    {
        return true;
    }
}