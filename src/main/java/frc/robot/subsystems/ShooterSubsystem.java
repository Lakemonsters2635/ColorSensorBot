package frc.robot.subsystems;

// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkMax.IdleMode;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;
//import  com.
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.PortMap;
//import frc.robot.steps.FindColorWheelSlot;
//import frc.robot.steps.SpinColorWheel;
//import frc.robot.subsystems.Intake.State;

public class ShooterSubsystem extends SubsystemBase{

    //  CANSparkMax drive = new CANSparkMax(PortMap.COLOR_CAN, MotorType.kBrushless);
   // Solenoid drop = new Solenoid(PortMap.COLORWHEEL_SOLENOID);
  //  AutonomousSequence seq = new AutonomousSequence();

    public ShooterSubsystem(){
        motor1 = new TalonFX(5);
        motor2 = new TalonFX(6);
        configureMotors();  
      }

    TalonFX motor1;
    TalonFX motor2;
  

    Constants constants = Constants.getConstants();
    
    public void configureMotors(){
        motor1.setSensorPhase(true);
    motor2.setSensorPhase(true);

    motor1.configNominalOutputForward(0, Constants.kTimeoutMs);
		motor1.configNominalOutputReverse(0, Constants.kTimeoutMs);
		motor1.configPeakOutputForward(1, Constants.kTimeoutMs);
    motor1.configPeakOutputReverse(-1, Constants.kTimeoutMs);
    
    motor2.configNominalOutputForward(0, Constants.kTimeoutMs);
		motor2.configNominalOutputReverse(0, Constants.kTimeoutMs);
		motor2.configPeakOutputForward(1, Constants.kTimeoutMs);
		motor2.configPeakOutputReverse(-1, Constants.kTimeoutMs);

    motor1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor,
                                            Constants.kPIDLoopIdx, 
                                            Constants.kTimeoutMs);

    motor2.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor,
                                            Constants.kPIDLoopIdx, 
                                            Constants.kTimeoutMs);
  
    motor1.config_kF(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kF, Constants.kTimeoutMs);
		motor1.config_kP(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kP, Constants.kTimeoutMs);
		motor1.config_kI(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kI, Constants.kTimeoutMs);
    motor1.config_kD(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kD, Constants.kTimeoutMs);
    
    motor2.config_kF(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kF, Constants.kTimeoutMs);
		motor2.config_kP(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kP, Constants.kTimeoutMs);
		motor2.config_kI(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kI, Constants.kTimeoutMs);
		motor2.config_kD(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kD, Constants.kTimeoutMs);
  
    }
    
    public void update() {
       // FireLog.log("colorwheelpos", drive.getEncoder().getPosition());
    }

    public void stop() {
     //  state = State.DISABLED;
     motor1.set(ControlMode.Velocity, 0);
     motor2.set(ControlMode.Velocity, 0);
     
    }

    public void Init() {
    }

    public void Periodic() {
       // seq.update();
        SmartDashboard.putNumber("ColorSpinner Distance", GetMotorDistance());
    }

    
    

    public void SpinShooter(double motor1Speed, double motor2Speed) {
        //DON'T GO OVER 3,000;
        motor1Speed = Math.min(3000, motor1Speed);
        motor2Speed = Math.min(3000, motor2Speed);

        motor1.set(ControlMode.Velocity, -motor1Speed*2048/600);
        motor2.set(ControlMode.Velocity, motor2Speed*2048/600);
    }

    public double GetMotorDistance() {
        //return drive.getEncoder().getPosition();
        return 0;
    }
    // public Color getColor(){
    //    // return matcher.get_color();
        
    // }
    public void StartRotation() {
        //if a color wheel operation is going, don't change it
      //  if(seq.isRunning() == true) return;
    //    seq.addStep(new SpinColorWheel());
    }

    
}