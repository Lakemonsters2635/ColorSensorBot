package frc.robot.subsystems;

// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkMax.IdleMode;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;
//import  com.
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.PortMap;
import frc.robot.model.FMSInfo;
//import frc.robot.steps.FindColorWheelSlot;
//import frc.robot.steps.SpinColorWheel;
//import frc.robot.subsystems.Intake.State;
import edu.wpi.first.wpilibj.DriverStation;

public class ColorSpinner extends SubsystemBase{

    //  CANSparkMax drive = new CANSparkMax(PortMap.COLOR_CAN, MotorType.kBrushless);
   // Solenoid drop = new Solenoid(PortMap.COLORWHEEL_SOLENOID);
  //  AutonomousSequence seq = new AutonomousSequence();

    public ColorSpinner(){
        colorSpinnerMotor = new WPI_TalonSRX(PortMap.COLOR_SPINNER_MOTOR);
    }

    ColorMatcher matcher = new ColorMatcher();

    private Color m_targetColor; 

    Constants constants = Constants.getConstants();

    enum State {
        DISABLED,
        ENC_ROTATE,
        COLOR_ROTATE,
        COLOR_ROTATE_FINAL,
    };
    WPI_TalonSRX colorSpinnerMotor;
    State state = State.DISABLED;
    Color expectedColor = ColorMatcher.kRedTarget;
    double target_rotation = 0;
    
    public void update() {
       // FireLog.log("colorwheelpos", drive.getEncoder().getPosition());
    }

    public void stop() {
        state = State.DISABLED;
    }
   
    public void find_color() {
        if ( state != State.COLOR_ROTATE ) {
            state = State.COLOR_ROTATE;
        }
    }

    Color[] ColorWheel = new Color[] {
        ColorMatcher.kGreenTarget,
        ColorMatcher.kRedTarget,
        ColorMatcher.kYellowTarget,
        ColorMatcher.kBlueTarget
    };

    public void Init() {
    }

    public void Periodic() {
       // seq.update();
        SmartDashboard.putNumber("ColorSpinner Distance", GetMotorDistance());
    }

    public void spinToTargetColor(){
        colorSpinnerMotor.set(1);
    }

    public boolean spinFinished(){
        Color colorFound = matcher.get_color();
        //NOTE: Discuss the following with students
        //What is the difference between comparing objects with "==" or "colorFound.equals"
        boolean isFinished = (colorFound.equals(m_targetColor));

        if (isFinished){
            colorSpinnerMotor.set(0);
        }
        return isFinished;
    }
    public void determineTargetColor(){
        FMSInfo fmsInfo = getFMSInfo();
        if (fmsInfo.isInitalized) {
            switch(fmsInfo.controlPanelTargetColor) {
                case 'R':
                    m_targetColor = ColorMatcher.kRedTarget;
                    break;
                case 'G':
                    m_targetColor = ColorMatcher.kGreenTarget;
                    break;
                case 'B':
                    m_targetColor = ColorMatcher.kBlueTarget;
                    break;   
                case 'Y':
                    m_targetColor = ColorMatcher.kYellowTarget;
                    break;                     
                default:
                    break;// to do, exception handling
            }
        }    
    }

    public void SpinMotor(double speed) {
  //      drive.set(speed);
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

    public void setHeight(boolean state) {
      //  drop.set(state);
    }

    public void StartColorFind() {
        byte index;
        //if a color wheel operation is going, don't change it
        
        String gameData = DriverStation.getInstance().getGameSpecificMessage();

        //get the color target
        if (gameData.length() > 0) {
            switch (gameData.charAt(0)) {
            case 'G':
                index = 0;
                break;
            case 'R':
                index = 1;
                break;
            case 'Y':
                index = 2;
                break;
            case 'B':
                index = 3;
                break;
            default:
                index = 100;
                break;
            }
        } else {
            index = 100;
        }

        //start the color finding
        if(index >= 4) {
            System.out.println("No color was provided to spin to.");
        } else {
            //need to rotate around the table.  We look at it from the far left, and need to rotate it 3 slots left, or 1 right, over to be what needs to be under the sensor.
            index = (byte)((index + 1) % 4);
        
            //seq.addStep(new FindColorWheelSlot(ColorWheel[index]));
        }
    }

    public static FMSInfo getFMSInfo()
	{
        FMSInfo fmsInfo = new FMSInfo();
        try {
            DriverStation driveStation= DriverStation.getInstance();
        
            System.out.println("FMS Attached: " + driveStation.isFMSAttached());
                
            //FHE: How do we test "IsFMSAttached())
            
            String gameSpecificMessage = "";

                
            //driveStation.waitForData(); //FHE: DO WE NEED THIS?
            fmsInfo.alliance = driveStation.getAlliance();
            gameSpecificMessage = driveStation.getGameSpecificMessage();

            fmsInfo.controlPanelTargetColor = gameSpecificMessage.trim().toUpperCase().charAt(0);
            fmsInfo.driveStation = driveStation.getLocation();
            fmsInfo.isAutonomous = driveStation.isAutonomous();
            fmsInfo.isInitalized = true;
        } 
        
        catch(Exception err) {
            System.out.println("ERROR Getting FMS Info: " + err.getMessage());
        }
		
		return fmsInfo;
	}
}