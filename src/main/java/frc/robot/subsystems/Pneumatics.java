package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pneumatics extends SubsystemBase {
    private Compressor m_compressor = new Compressor(PneumaticsModuleType.CTREPCM);
    private DoubleSolenoid m_pusher = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
    private boolean m_pusher_state = false;

    public Pneumatics() {
        m_compressor.enableDigital();

        m_pusher.set(Value.kForward);
    }

    // Fuck you c'est moi qui choisit de faire des noms beaucoup trop long
    // - Maxence
    public void Toggle_Climber(){
        System.out.println(m_pusher_state);

        if(m_pusher_state)
        {
            m_pusher.set(Value.kForward);
            m_pusher_state = false;
        }
        else
        {
            m_pusher.set(Value.kReverse);
            m_pusher_state = true;
        }
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Pression", m_compressor.getPressure());
    }
}
