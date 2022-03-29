package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Elevator extends SubsystemBase {
  private final CANSparkMax m_elevator_lf = new CANSparkMax(Constants.ConsElevator.EMoteur1, MotorType.kBrushless);
  private final CANSparkMax m_elevator_lm = new CANSparkMax(Constants.ConsElevator.EMoteur2, MotorType.kBrushless);
  private final CANSparkMax m_elevator_rf = new CANSparkMax(Constants.ConsElevator.EMoteur3, MotorType.kBrushless);
  private final CANSparkMax m_elevator_rm = new CANSparkMax(Constants.ConsElevator.EMoteur4, MotorType.kBrushless);

  private SparkMaxPIDController EArmController_lf;
  private SparkMaxPIDController EArmController_lm;
  private SparkMaxPIDController EArmController_rf;
  private SparkMaxPIDController EArmController_rm;

  // Valeur d'encodeur que le moteur veut atteindre
  private int lf_target = 0;
  private int rf_target = 0;
  private int lm_target = 0;
  private int rm_target = 0;

  // Change d'etat quand:

  // Atteint target
  private boolean trigger_tstate = false;
  // Change pneumatique
  private boolean trigger_pstate = false;

  private boolean goto_target = false;

  // États
  // 0 : État initiale
  // 1 : Monte bras fixe
  // 2 (auto): Hold
  // 3 : Decendre les bras fixe
  // 4 (auto): Hold
  // 5 : Pneutmatique
  // 6 : Étendre bras  mobile
  // 7 (auto): Hold
  // 8 : Pneumatique
  // 9 : Monter bras fixe et Descendre bras mobile
  // 10 (auto): Hold
  // 11 : Monter bras fixe
  
  public int state_id = 0;

  private AHRS m_ahrs = new AHRS(SPI.Port.kMXP);

  private static final int kGyroPort = 0;

  public Elevator() {
    reset_encoders();
  }

  public void reset_encoders()
  {
    System.out.println("Reset elevator encoders");
    m_elevator_lf.getEncoder().setPosition(0.0f);
    m_elevator_lm.getEncoder().setPosition(0.0f);
    m_elevator_rf.getEncoder().setPosition(0.0f);
    m_elevator_rm.getEncoder().setPosition(0.0f);
  }

  public void StableDrive(Double speed) {
    m_elevator_lf.set(speed);
    m_elevator_rf.set(-speed);
  }

  public void MobileDrive(Double speed) {
    m_elevator_lm.set(-speed);
    m_elevator_rm.set(speed);
  }

  @Override
  public void periodic() {
    // NOTE: Bessoin de manuellement trouver les longeurs de cordes en encodeurs

    SmartDashboard.putNumber("Etat", state_id);

    SmartDashboard.putNumber("lf", m_elevator_lf.getEncoder().getPosition());
    SmartDashboard.putNumber("lm", m_elevator_lm.getEncoder().getPosition());
    SmartDashboard.putNumber("rf", m_elevator_rf.getEncoder().getPosition());
    SmartDashboard.putNumber("rm", m_elevator_rm.getEncoder().getPosition());

    //"Machine à état"
    //(pas vrm une vrai machine à état mais vous savez mm pas c quoi faq c correcte)
    // (aussi pas bessoin d'etre appeler chaque tick)
    if(state_id == 1){
      lf_target = Constants.ConsElevator.lf_length;
      rf_target = Constants.ConsElevator.rf_length;

      // Pour sauver du temps
      lm_target = Constants.ConsElevator.lm_length/2;
      rm_target = Constants.ConsElevator.rm_length/2;

      goto_target = true;
    }
    else if(state_id == 2){
      goto_target = false;
    }
    else if(state_id == 3){
      lf_target = 0;
      rf_target = 0;
      goto_target = true;
    }
    else if(state_id == 4){
      goto_target = false;
    }
    else if(state_id == 5){

    }

    // Mouvements
    // Goto targets
    int verified = 0;
    if(goto_target){
      // lf 
      if(m_elevator_lf.getEncoder().getPosition() < lf_target - Constants.ConsElevator.fdeadzone){
        m_elevator_lf.set(Constants.ConsElevator.fixe_speed);
      }
      else if(m_elevator_lf.getEncoder().getPosition() > lf_target + Constants.ConsElevator.fdeadzone){
        m_elevator_lf.set(-Constants.ConsElevator.fixe_speed);
      }
      else{
        verified++;
      }

      // rf
      if(m_elevator_rf.getEncoder().getPosition() < rf_target - Constants.ConsElevator.fdeadzone){
        m_elevator_rf.set(Constants.ConsElevator.fixe_speed);
      }
      else if(m_elevator_rf.getEncoder().getPosition() > rf_target + Constants.ConsElevator.fdeadzone){
        m_elevator_rf.set(-Constants.ConsElevator.fixe_speed);
      }
      else{
        verified++;
      }

      // lm
      if(m_elevator_lm.getEncoder().getPosition() < lm_target - Constants.ConsElevator.mdeadzone){
        m_elevator_lm.set(Constants.ConsElevator.mobile_speed);
      }
      else if(m_elevator_lm.getEncoder().getPosition() > lm_target + Constants.ConsElevator.mdeadzone){
        m_elevator_lm.set(-Constants.ConsElevator.mobile_speed);
      }
      else{
        verified++;
      }

      // rm
      if(m_elevator_rm.getEncoder().getPosition() < rm_target - Constants.ConsElevator.mdeadzone){
        m_elevator_rm.set(Constants.ConsElevator.mobile_speed);
      }
      else if(m_elevator_rm.getEncoder().getPosition() > rm_target + Constants.ConsElevator.mdeadzone){
        m_elevator_rm.set(-Constants.ConsElevator.mobile_speed);
      }
      else{
        verified++;
      }

      if(verified == 4 && trigger_tstate){
        state_id++;
      }
      else
      {
        System.out.println("Not all verified");
      }
    }
  }
}
