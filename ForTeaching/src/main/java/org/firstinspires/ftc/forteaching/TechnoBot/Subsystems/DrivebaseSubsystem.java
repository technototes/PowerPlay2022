package org.firstinspires.ftc.forteaching.TechnoBot.Subsystems;

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
import java.util.function.Supplier;

public class DrivebaseSubsystem
    extends MecanumDrivebaseSubsystem
    implements Supplier<Pose2d>, Loggable {

    // Notes from Kevin:
    // The 5203 motors when direct driven
    // move about 63 inches forward and is measured as roughly 3000 ticks on the encoders

    @Config
    public abstract static class DriveConstants implements MecanumConstants {

        public static double SLOW_MOTOR_SPEED = 0.6;
        public static double FAST_MOTOR_SPEED = 1.0;

        @TicksPerRev
        public static final double TICKS_PER_REV = 537.6; // 2021: 28;

        @MaxRPM
        public static final double MAX_RPM = 312; // 2021: 6000;

        @UseDriveEncoder
        public static final boolean RUN_USING_ENCODER = false;

        @MotorVeloPID
        public static PIDFCoefficients MOTOR_VELO_PID = new PIDFCoefficients(
            20,
            0,
            3,
            MecanumConstants.getMotorVelocityF(MAX_RPM / 60 * TICKS_PER_REV)
        );

        @WheelRadius
        public static double WHEEL_RADIUS = 1.88976; // in

        @GearRatio
        public static double GEAR_RATIO = 1; // 2021: / 19.2; // output (wheel) speed / input (motor) speed

        @TrackWidth
        public static double TRACK_WIDTH = 9.1875; // 2021: 10; // in

        @WheelBase
        public static double WHEEL_BASE = 8.5; // in

        @KV
        public static double kV =
            1.0 / MecanumConstants.rpmToVelocity(MAX_RPM, WHEEL_RADIUS, GEAR_RATIO);

        @KA
        public static double kA = 0;

        @KStatic
        public static double kStatic = 0;

        // This was 60, which was too fast. Things slid around a lot.
        @MaxVelo
        public static double MAX_VEL = 30;

        // This was 35, which also felt a bit too fast. The bot controls more smoothly now
        @MaxAccel
        public static double MAX_ACCEL = 30;

        // This was 180 degrees
        @MaxAngleVelo
        public static double MAX_ANG_VEL = Math.toRadians(135);

        // This was 90 degrees
        @MaxAngleAccel
        public static double MAX_ANG_ACCEL = Math.toRadians(45);

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
    }

    private static final boolean ENABLE_POSE_DIAGNOSTICS = true;

    @Log(name = "Pose2d: ")
    public String poseDisplay = ENABLE_POSE_DIAGNOSTICS ? "" : null;

    // @Log.Number(name = "FL")
    public EncodedMotor<DcMotorEx> fl2;
    // @Log.Number(name = "FR")
    public EncodedMotor<DcMotorEx> fr2;
    // @Log.Number(name = "RL")
    public EncodedMotor<DcMotorEx> rl2;
    // @Log.Number(name = "RR")
    public EncodedMotor<DcMotorEx> rr2;

    public DrivebaseSubsystem(
        EncodedMotor<DcMotorEx> fl,
        EncodedMotor<DcMotorEx> fr,
        EncodedMotor<DcMotorEx> rl,
        EncodedMotor<DcMotorEx> rr,
        IMU i
    ) {
        super(fl, fr, rl, rr, i, () -> DriveConstants.class);
        fl2 = fl;
        fr2 = fr;
        rl2 = rl;
        rr2 = rr;
        speed = DriveConstants.SLOW_MOTOR_SPEED;
    }

    public void fast() {
        speed = DriveConstants.FAST_MOTOR_SPEED;
    }

    public void slow() {
        speed = DriveConstants.SLOW_MOTOR_SPEED;
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
            poseDisplay =
                pose.toString() +
                " : " +
                (poseVelocity != null ? poseVelocity.toString() : "<null>");
            System.out.println("Pose: " + poseDisplay);
        }
    }
}
