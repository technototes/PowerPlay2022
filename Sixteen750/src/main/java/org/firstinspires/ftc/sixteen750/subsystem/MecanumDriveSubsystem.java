package org.firstinspires.ftc.sixteen750.subsystem;

import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.path.subsystem.MecanumConstants;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;

import java.util.function.Supplier;

public class MecanumDriveSubsystem extends MecanumDrivebaseSubsystem implements Supplier<Pose2d> {
    // TODO: create DriveConstants

    public abstract static class MecanumDriveConstants implements MecanumConstants {
        @TicksPerRev
        public static final double TICKS_PER_REV = 0; // TODO: set ticks per rev

        @MaxRPM
        public static final double MAX_RPM = 0; //TODO: set max rpm

        @UseDriveEncoder
        public static final boolean RUN_USING_ENCODER = true;

        @MotorVeloPID
        public static PIDFCoefficients MOTOR_VELO_PID = new PIDFCoefficients(20, 0, 3, MecanumConstants.getMotorVelocityF(MAX_RPM / 60 * TICKS_PER_REV));

        @WheelRadius
        public static double WHEEL_RADIUS = 1.88976; // in, TODO: verify this

        @GearRatio
        public static double GEAR_RATIO = 0; // TODO: set gear ratio

        @TrackWidth
        public static double TRACK_WIDTH = 9.1875; // in, TODO: set track width

        @WheelBase
        public static double WHEEL_BASE = 8.5; // in, TODO: set wheel

        @KV
        public static double kV = 1.0 / MecanumConstants.rpmToVelocity(MAX_RPM, WHEEL_RADIUS, GEAR_RATIO);

        @KA
        public static double kA = 0;

        @KStatic
        public static double kStatic = 0;

        @MaxVelo
        public static double MAX_VEL = 30;

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

    public MecanumDriveSubsystem(EncodedMotor<DcMotorEx> fl,
                                 EncodedMotor<DcMotorEx> fr,
                                 EncodedMotor<DcMotorEx> rl,
                                 EncodedMotor<DcMotorEx> rr,
                                 IMU i
    ) {
        super(fl, fr, rl, rr, i, () -> MecanumDriveConstants.class);
    }

    @Override
    public Pose2d get() {
        return getPoseEstimate();
    }
}
