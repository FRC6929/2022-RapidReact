package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.pneumatic;

public class Pneumatics extends SubsystemBase {
    private Compressor m_compressor = new Compressor(PneumaticsModuleType.CTREPCM);
    private DoubleSolenoid m_arm = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, pneumatic.arm1.port1, pneumatic.arm1.port2);
    private DoubleSolenoid m_pusher = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, pneumatic.arm2.port1, pneumatic.arm2.port2);
    private boolean m_arm_state = false;
    private boolean m_pusher_state = false;

    public Pneumatics() {
        m_compressor.enableDigital();

        m_arm.set(Value.kForward);
        m_pusher.set(Value.kForward);

        SmartDashboard.putString("pstate", "forward");
    }

    // Fuck you c'est moi qui choisit de faire des noms beaucoup trop long
    // - Maxence
    public void Set_Climber(boolean x){
        if(x)
        {
            m_arm.set(Value.kForward);
            m_arm_state = x;
        }
        else
        {
            m_arm.set(Value.kReverse);
            m_arm_state = x;
        }
    }

    public void Toggle_Ball_Pusher()
    {
        System.out.println("Ball toggle");
        if(m_pusher_state)
        {
            SmartDashboard.putString("pstate", "forward");
            m_pusher.set(Value.kReverse);
            m_pusher_state = false;
        }
        else
        {
            SmartDashboard.putString("pstate", "reverse");
            m_pusher.set(Value.kForward);
            m_pusher_state = true;
        }
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Pression", m_compressor.getPressure());
    }
}


//god help me... i dont understand this 