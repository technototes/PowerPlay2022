package org.firstinspires.ftc.twenty403.subsystem;

import java.util.function.Supplier;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.path.subsystem.MecanumConstants;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

public class DrivebaseSubsystem extends MecanumDrivebaseSubsystem implements Supplier<Pose2d>, Loggable {

    @Config
    public static class DriveConstants implements MecanumConstants {
        /*
         * These are motor constants that should be listed online for your motors.
         */
        public static final double TICKS_PER_REV = 537.6;
        public static final double MAX_RPM = 312;

        /*
         * Set RUN_USING_ENCODER to true to enable built-in hub velocity control using drive encoders.
         * Set this flag to false if drive encoders are not present and an alternative localization
         * method is in use (e.g., tracking wheels).
         *
         * If using the built-in motor velocity PID, update MOTOR_VELO_PID with the tuned coefficients
         * from DriveVelocityPIDTuner.
         */
        public static final boolean RUN_USING_ENCODER = true;
        // public static PIDFCoefficients MOTOR_VELO_PID = new PIDFCoefficients(0, 0, 0,
        // getMotorVelocityF(MAX_RPM / 60 * TICKS_PER_REV));

        /*
         * These are physical constants that can be determined from your robot (including the track
         * width; it will be tune empirically later although a rough estimate is important). Users are
         * free to chose whichever linear distance unit they would like so long as it is consistently
         * used. The default values were selected with inches in mind. Road runner uses radians for
         * angular distances although most angular parameters are wrapped in Math.toRadians() for
         * convenience. Make sure to exclude any gear ratio included in MOTOR_CONFIG from GEAR_RATIO.
         */
        public static double WHEEL_RADIUS = 1.8898; // in
        public static double GEAR_RATIO = 1; // output (wheel) speed / input (motor) speed
        public static double TRACK_WIDTH = 9.1875; // in

        /*
         * These are the feedforward parameters used to model the drive motor behavior. If you are using
         * the built-in velocity PID, *these values are fine as is*. However, if you do not have drive
         * motor encoders or have elected not to use them for velocity control, these values should be
         * empirically tuned.
         */
        public static double kV = 1.0 / rpmToVelocity(MAX_RPM);
        public static double kA = 0;
        public static double kStatic = 0;

        /*
         * These values are used to generate the trajectories for you robot. To ensure proper operation,
         * the constraints should never exceed ~80% of the robot's actual capabilities. While Road
         * Runner is designed to enable faster autonomous motion, it is a good idea for testing to start
         * small and gradually increase them later after everything is working. All distance units are
         * inches.
         */
        /*
        * Note from LearnRoadRunner.com:
        * The velocity and acceleration constraints were calculated based on the following equation:
        * ((MAX_RPM / 60) * GEAR_RATIO * WHEEL_RADIUS * 2 * Math.PI) * 0.85
        * Resulting in 52.48291908330528 in/s.
        * This is only 85% of the theoretical maximum velocity of the bot, following the recommendation above.
        * This is capped at 85% because there are a number of variables that will prevent your bot from actually
        * reaching this maximum velocity: voltage dropping over the game, bot weight, general mechanical inefficiencies, etc.
        * However, you can push this higher yourself if you'd like. Perhaps raise it to 90-95% of the theoretically
        * max velocity. The theoretically maximum velocity is 61.74461068624151 in/s.
        * Just make sure that your bot can actually reach this maximum velocity. Path following will be detrimentally
        * affected if it is aiming for a velocity not actually possible.
        *
        * The maximum acceleration is somewhat arbitrary and it is recommended that you tweak this yourself based on
        * actual testing. Just set it at a reasonable value and keep increasing until your path following starts
        * to degrade. As of now, it simply mirrors the velocity, resulting in 52.48291908330528 in/s/s
        *
        * Maximum Angular Velocity is calculated as: maximum velocity / trackWidth * (180 / Math.PI) but capped at 360°/s.
        * You are free to raise this on your own if you would like. It is best determined through experimentation.

        */
        public static double MAX_VEL = 52.48291908330528;
        public static double MAX_ACCEL = 52.48291908330528;
        public static double MAX_ANG_VEL = Math.toRadians(327.2979330612245);
        public static double MAX_ANG_ACCEL = Math.toRadians(327.2979330612245);

        public static double encoderTicksToInches(double ticks) {
            return WHEEL_RADIUS * 2 * Math.PI * GEAR_RATIO * ticks / TICKS_PER_REV;
        }

        public static double rpmToVelocity(double rpm) {
            return rpm * GEAR_RATIO * 2 * Math.PI * WHEEL_RADIUS / 60.0;
        }

        public static double getMotorVelocityF(double ticksPerSecond) {
            // see
            // https://docs.google.com/document/d/1tyWrXDfMidwYyP_5H4mZyVgaEswhOC35gvdmP-V-5hA/edit#heading=h.61g9ixenznbx
            return 32767 / ticksPerSecond;
        }

        @MotorVeloPID
        public static PIDFCoefficients MOTOR_VELO_PID =
                new PIDFCoefficients(20, 0, 3, MecanumConstants.getMotorVelocityF(MAX_RPM / 60 * TICKS_PER_REV));

        @WheelBase
        public static double WHEEL_BASE = 8.5; // in

        /*
                @KV
                public static double kV = 1.0 / MecanumConstants.rpmToVelocity(MAX_RPM, WHEEL_RADIUS, GEAR_RATIO);

                @KA
                public static double kA = 0;

                @KStatic
                public static double kStatic = 0;

                @MaxVelo
                public static double MAX_VEL = 60;

                @MaxAccel
                public static double MAX_ACCEL = 35;

                @MaxAngleVelo
                public static double MAX_ANG_VEL = Math.toRadians(180);

                @MaxAngleAccel
                public static double MAX_ANG_ACCEL = Math.toRadians(90);
        */
        @TransPID
        public static PIDCoefficients TRANSLATIONAL_PID = new PIDCoefficients(8, 0, 0);

        @HeadPID
        public static PIDCoefficients HEADING_PID = new PIDCoefficients(8, 0, 0);

        @LateralMult
        public static double LATERAL_MULTIPLIER = 1.14; // Lateral position is off by 14%

        @VXWeight
        public static double VX_WEIGHT = 1;

        @VYWeight
        public static double VY_WEIGHT = 1;

        @OmegaWeight
        public static double OMEGA_WEIGHT = 1;

        @PoseLimit
        public static int POSE_HISTORY_LIMIT = 100;

        @Override
        public Class getConstant() {
            return DriveConstants.class;
        }
    }

    private static final boolean ENABLE_POSE_DIAGNOSTICS = true;

    @Log(name = "Pose2d: ")
    public String poseDisplay = ENABLE_POSE_DIAGNOSTICS ? "" : null;

    //    @Log.Number (name = "FL")
    public EncodedMotor<DcMotorEx> fl2;
    //    @Log.Number (name = "FR")
    public EncodedMotor<DcMotorEx> fr2;
    //    @Log.Number (name = "RL")
    public EncodedMotor<DcMotorEx> rl2;
    //    @Log.Number (name = "RR")
    public EncodedMotor<DcMotorEx> rr2;

    public DrivebaseSubsystem(
            EncodedMotor<DcMotorEx> fl,
            EncodedMotor<DcMotorEx> fr,
            EncodedMotor<DcMotorEx> rl,
            EncodedMotor<DcMotorEx> rr,
            IMU i) {
        super(fl, fr, rl, rr, i, new DriveConstants());
        fl2 = fl;
        fr2 = fr;
        rl2 = rl;
        rr2 = rr;
    }

    @Override
    public Pose2d get() {
        return getPoseEstimate();
    }

    @Override
    public void periodic() {
        if (ENABLE_POSE_DIAGNOSTICS) {
            updatePoseEstimate();
            Pose2d pose = getPoseEstimate();
            Pose2d poseVelocity = getPoseVelocity();
            poseDisplay = pose.toString() + " : " + (poseVelocity != null ? poseVelocity.toString() : "<null>");
            System.out.println("Pose: " + poseDisplay);
        }
    }
}
