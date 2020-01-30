package frc.robot;

public class Constants {
    private Constants() {}

    private static Constants instance = new Constants();

    public static Constants getConstants() {
        return instance;
    }

 /**
	 * Which PID slot to pull gains from. Starting 2018, you can choose from
	 * 0,1,2 or 3. Only the first two (0,1) are visible in web-based
	 * configuration.
	 */
	public static final int kSlotIdx = 0;

	/**
	 * Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For
	 * now we just want the primary one.
	 */
	public static final int kPIDLoopIdx = 0;

	/**
	 * Set to zero to skip waiting for confirmation, set to nonzero to wait and
	 * report to DS if action fails.
	 */
    public static final int kTimeoutMs = 30;

	/**
	 * PID Gains may have to be adjusted based on the responsiveness of control loop.
     * kF: 1023 represents output value to Talon at 100%, 7200 represents Velocity units at 100% output
     * 
	 * 	                                    			  kP   kI   kD   kF          Iz    PeakOut */
 	//public final static Gains kGains_Velocit = new Gains( 0.25, 0.001, 20, 1023.0/7200.0,  300,  1.00);
	 static final double kP = 0.25; 		// 0.3735
	 static final double kI = 0.54*0.83/200; 	// 0.002241
	 static final double kD = 0.003; 	// 0.22636364
	 static final double kF = 0.047; 			//
	 //public final static Gains kGains_Velocit = new Gains( kP, kI, kD, kF,  0,  1.00);
	 public final static Gains kGains_Velocit = new Gains(kP, 0.0, 0.0, kF,  0,  1.00);

	 public final static double SHOOTER_MOTOR_1_DEFAULT_SPEED = 1500;
	 public final static double SHOOTER_MOTOR_2_DEFAULT_SPEED = 1500;
 
}