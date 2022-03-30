package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
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

  private DoubleSolenoid m_arm = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.pneumatic.arm1.port1, Constants.pneumatic.arm1.port2);
  private boolean m_arm_state = false;

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

  private boolean p_target = false;

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

    m_arm.set(Value.kForward);
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

  public void Set_Arm(){

  }

  public void Toggle_Arm(){
    if(this.m_arm_state){
      Set_Arm(false);
    }
    else{
      Set_Arm(true);
    }
  }

  public void Set_Arm(boolean v){
    this.m_arm_state = v;

    if(v){
      System.out.println("v");
      m_arm.set(Value.kReverse);
    }
    else{
      System.out.println("i");
      m_arm.set(Value.kForward);
    }
  }

  @Override
  public void periodic() {
    // NOTE: Bessoin de manuellement trouver les longeurs de cordes en encodeurs

    SmartDashboard.putNumber("Etat", state_id);

    SmartDashboard.putNumber("lf_target", lf_target);
    SmartDashboard.putNumber("lm_target", lm_target);
    SmartDashboard.putNumber("rf_target", rf_target);
    SmartDashboard.putNumber("rm_target", rm_target);

    SmartDashboard.putNumber("lf", m_elevator_lf.getEncoder().getPosition());
    SmartDashboard.putNumber("lm", m_elevator_lm.getEncoder().getPosition());
    SmartDashboard.putNumber("rf", m_elevator_rf.getEncoder().getPosition());
    SmartDashboard.putNumber("rm", m_elevator_rm.getEncoder().getPosition());

    //"Machine à état"
    //(pas vrm une vrai machine à état mais vous savez mm pas c quoi faq c correcte)
    // (aussi pas bessoin d'etre appeler chaque tick)
    if(state_id == -1){
      reset_encoders();
      m_elevator_lf.set(0);
      m_elevator_rf.set(0);
      m_elevator_lm.set(0);
      m_elevator_rm.set(0);

      rf_target = 0;
      lf_target = 0;
      lm_target = 0;
      rm_target = 0;

      goto_target = false;
      trigger_tstate = false;
      trigger_pstate = false;

      p_target = false;

      state_id = 0;
    }
    
    if(state_id == 1){
      lf_target = Constants.ConsElevator.lf_length;
      rf_target = Constants.ConsElevator.rf_length;

      // Pour sauver du temps
      lm_target = Constants.ConsElevator.lm_length/2;
      rm_target = Constants.ConsElevator.rm_length/2;

      trigger_tstate = true;
      goto_target = true;
    }
    else if(state_id == 2){
      trigger_tstate = false;
      goto_target = true;
    }
    else if(state_id == 3){
      lf_target = 0;
      rf_target = 0;
      lm_target = Constants.ConsElevator.lm_length;
      rm_target = Constants.ConsElevator.rm_length;
      trigger_tstate = true;
      goto_target = true;
    }
    else if(state_id == 4){
      trigger_tstate = false;
      goto_target = true;
    }
    else if(state_id == 5){
      p_target = true;
    }
    else if(state_id == 6){
      trigger_tstate = false;
      lf_target = Constants.ConsElevator.lf_length/3;
      rf_target = Constants.ConsElevator.rf_length/3;
    }
    else if(state_id == 7){
      trigger_tstate = false;
      lm_target = 0;
      rm_target = 0;
    }

    // Mouvements
    // Goto targets
    int verified = 0;
    if(goto_target){
      // lf 
      if(m_elevator_lf.getEncoder().getPosition() < lf_target - Constants.ConsElevator.fdeadzone*2){
        if(state_id == 6){
          m_elevator_lf.set(Constants.ConsElevator.fixe_speed*2);
        }else{
          m_elevator_lf.set(Constants.ConsElevator.fixe_speed);
        }
      }
      else if(m_elevator_lf.getEncoder().getPosition() > lf_target + Constants.ConsElevator.fdeadzone*2){
        m_elevator_lf.set(-Constants.ConsElevator.fixe_speed);
      }
      else if(m_elevator_lf.getEncoder().getPosition() < lf_target - (Constants.ConsElevator.fdeadzone)){
        m_elevator_lf.set(Constants.ConsElevator.fixe_speed/3);
      }
      else if(m_elevator_lf.getEncoder().getPosition() > lf_target + (Constants.ConsElevator.fdeadzone)){
        m_elevator_lf.set(-Constants.ConsElevator.fixe_speed/3);
      }
      else{
        m_elevator_lf.set(0);
        verified++;
      }

      // rf
      if(m_elevator_rf.getEncoder().getPosition() < rf_target - Constants.ConsElevator.fdeadzone*2){
        if(state_id == 6){
          m_elevator_rf.set(Constants.ConsElevator.fixe_speed*2);
        }else{
          m_elevator_rf.set(Constants.ConsElevator.fixe_speed);
        }
      }
      else if(m_elevator_rf.getEncoder().getPosition() > rf_target + Constants.ConsElevator.fdeadzone*2){
        m_elevator_rf.set(-Constants.ConsElevator.fixe_speed);
      }
      else if(m_elevator_rf.getEncoder().getPosition() < rf_target - (Constants.ConsElevator.fdeadzone)){
        m_elevator_rf.set(Constants.ConsElevator.fixe_speed/3);
      }
      else if(m_elevator_rf.getEncoder().getPosition() > rf_target + (Constants.ConsElevator.fdeadzone)){
        m_elevator_rf.set(-Constants.ConsElevator.fixe_speed/3);
      }
      else{
        m_elevator_rf.set(0);
        verified++;
      }

      // lm
      if(m_elevator_lm.getEncoder().getPosition() < lm_target - Constants.ConsElevator.mdeadzone*2){
        m_elevator_lm.set(Constants.ConsElevator.mobile_speed);
      }
      else if(m_elevator_lm.getEncoder().getPosition() > lm_target + Constants.ConsElevator.mdeadzone*2){
        m_elevator_lm.set(-Constants.ConsElevator.mobile_speed);
      }
      else if(m_elevator_lm.getEncoder().getPosition() < lm_target - (Constants.ConsElevator.mdeadzone)){
        m_elevator_lm.set(Constants.ConsElevator.mobile_speed/3);
      }
      else if(m_elevator_lm.getEncoder().getPosition() > lm_target + (Constants.ConsElevator.mdeadzone)){
        m_elevator_lm.set(-Constants.ConsElevator.mobile_speed/3);
      }
      else{
        m_elevator_lm.set(0);
        verified++;
      }

      // rm
      if(m_elevator_rm.getEncoder().getPosition() < rm_target - Constants.ConsElevator.mdeadzone*2){
        m_elevator_rm.set(Constants.ConsElevator.mobile_speed);
      }
      else if(m_elevator_rm.getEncoder().getPosition() > rm_target + Constants.ConsElevator.mdeadzone*2){
        m_elevator_rm.set(-Constants.ConsElevator.mobile_speed);
      }
      else if(m_elevator_rm.getEncoder().getPosition() < rm_target - (Constants.ConsElevator.mdeadzone)){
        m_elevator_rm.set(Constants.ConsElevator.mobile_speed/3);
      }
      else if(m_elevator_rm.getEncoder().getPosition() > rm_target + (Constants.ConsElevator.mdeadzone)){
        m_elevator_rm.set(-Constants.ConsElevator.mobile_speed/3);
      }
      else{
        m_elevator_rm.set(0);
        verified++;
      }

      if(verified == 4 && trigger_tstate){
        state_id++;
      }
      else
      {
        SmartDashboard.putNumber("Verified", verified);
      }
    }

    if(p_target != m_arm_state){
      Set_Arm(p_target);
    }
  }
}
