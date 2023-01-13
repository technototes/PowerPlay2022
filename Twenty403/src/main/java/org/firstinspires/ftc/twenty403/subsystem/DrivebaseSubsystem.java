package org.firstinspires.ftc.twenty403.subsystem;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.util.Angle;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.path.subsystem.MecanumConstants;
import com.technototes.path.subsystem.MecanumDrivebaseSubsystem;
import java.util.function.Supplier;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class DrivebaseSubsystem
    extends MecanumDrivebaseSubsystem
    implements Supplier<Pose2d>, Loggable {

    // Notes from Kevin:
    // The 5203 motors when direct driven
    // move about 63 inches forward and is measured as roughly 3000 ticks on the encoders

    @Config
    public abstract static class DriveConstants implements MecanumConstants {

        // We could use a PID controller for the heading, but that seems messy...

        // This controls how far the controller pushes the robot's heading.
        public static double HEADING_ADJUST_PER_SECOND = 30;
        // Adjust this toward zero if the bot over-turns, then turns back too often
        // Adjust this away from zero if the bot doesn't turn fast enough for your liking
        // Nothing higher than 1.0 (or lower than -1.0) is sensible
        public static double HEADING_ADJUST_COEFF = 1.0;

        public static double VEL_SCALE = 5.0;

        public static double SLOW_MOTOR_SPEED = 0.6;
        public static double FAST_MOTOR_SPEED = 1.0;
        public static double AUTO_MOTOR_SPEED = 0.9;

        @TicksPerRev
        public static final double TICKS_PER_REV = 537.6; // 2021: 28;

        @MaxRPM
        public static final double MAX_RPM = 312; // 2021: 6000;

        @UseDriveEncoder
        public static final boolean RUN_USING_ENCODER = true;

        @MotorVeloPID
        public static PIDFCoefficients MOTOR_VELO_PID = new PIDFCoefficients(
            18,
            0,
            1,
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
        public static double MAX_VEL = 50;

        // This was 35, which also felt a bit too fast. The bot controls more smoothly now
        @MaxAccel
        public static double MAX_ACCEL = 30; //30

        // This was 180 degrees
        @MaxAngleVelo
        public static double MAX_ANG_VEL = Math.toRadians(180);

        // This was 90 degrees
        @MaxAngleAccel
        public static double MAX_ANG_ACCEL = Math.toRadians(90);

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

        // FL - 0.82
        // FR - 0.8
        // RL - 0.1
        // RR - 0.74
        public static double AFR_SCALE = 0.8;
        public static double AFL_SCALE = 0.82;
        public static double ARR_SCALE = 0.74;
        public static double ARL_SCALE = 1;
    }

    private static final boolean ENABLE_POSE_DIAGNOSTICS = true;

    // @Log(name = "Pose2d: ")
    public String poseDisplay = ENABLE_POSE_DIAGNOSTICS ? "" : null;

    // @Log.Number(name = "FL")
    public EncodedMotor<DcMotorEx> fl2;
    // @Log.Number(name = "FR")
    public EncodedMotor<DcMotorEx> fr2;
    // @Log.Number(name = "RL")
    public EncodedMotor<DcMotorEx> rl2;
    // @Log.Number(name = "RR")
    public EncodedMotor<DcMotorEx> rr2;

    public double targetHeading;
    public ElapsedTime lastAdjust;

    @Log
    public String driveInfo = "none";

    public OdoSubsystem odometry;

    public DrivebaseSubsystem(
        EncodedMotor<DcMotorEx> fl,
        EncodedMotor<DcMotorEx> fr,
        EncodedMotor<DcMotorEx> rl,
        EncodedMotor<DcMotorEx> rr,
        IMU i,
        OdoSubsystem odo
    ) {
        super(fl, fr, rl, rr, i, () -> DriveConstants.class);
        fl2 = fl;
        fr2 = fr;
        rl2 = rl;
        rr2 = rr;
        speed = DriveConstants.SLOW_MOTOR_SPEED;
        odometry = odo;

        if (this.getLocalizer() != null && odo != null) {
            this.setLocalizer(new OverrideLocalizer(this.getLocalizer(), odo, this));
        }
        lastAdjust = new ElapsedTime();
        targetHeading = getExternalHeading();
        invalidateLastHeading();
    }

    public void fast() {
        speed = DriveConstants.FAST_MOTOR_SPEED;
    }

    public void slow() {
        speed = DriveConstants.SLOW_MOTOR_SPEED;
    }

    public void auto() {
        speed = DriveConstants.AUTO_MOTOR_SPEED;
    }

    private double getRotationPower(double curHeading) {
        // Check to see which direction we want to turn:
        double dir = AngleUnit.normalizeRadians(targetHeading - curHeading);
        driveInfo = String.format("Dir: %f (t: %f, c: %f)", dir, targetHeading, curHeading);
        return Range.clip(dir, -1, 1) * DriveConstants.HEADING_ADJUST_COEFF;
    }

    private final double INVALID_HEADING = 1024.0;
    private final double INITIAL_HEADING = -1024.0;

    private double lastHeading = INITIAL_HEADING;

    private void invalidateLastHeading() {
        lastHeading = INVALID_HEADING;
    }

    private boolean isLastHeadingValid() {
        return lastHeading != INVALID_HEADING && !isInitialHeading();
    }

    private boolean isInitialHeading() {
        return lastHeading == INITIAL_HEADING;
    }

    // This should be the 'joystick' values, which transloates to
    // a movement vector (x,y) and a heading target adjustment
    public void updateJoystickPosition(double x, double y, double r) {
        // This is used to control how much we will rotate the target heading based
        // on the amount of time the stick is held. Clip it to no more than .1 seconds,
        // just to prevent crazy stuff from happening...
        double timeSinceLastUpdate = Range.clip(lastAdjust.seconds(), 0.001, 0.1);
        lastAdjust.reset();

        double curHeading = getExternalHeading();
        // Deal with "we just started" so we don't want the bot to move until the driver says so
        if (isInitialHeading()) {
            targetHeading = curHeading;
            lastHeading = curHeading;
        }

        if (Math.abs(r) > 1e-10) {
            double headingChange =
                -r * timeSinceLastUpdate * DriveConstants.HEADING_ADJUST_PER_SECOND;
            if (isLastHeadingValid()) {
                targetHeading = lastHeading + headingChange;
            } else {
                targetHeading = curHeading + headingChange;
            }

            // Now, instead of using *r* to decide which direction to turn, we should use
            // the delta from target heading
            // We save the current heading so that when the driver stops pushing the stick,
            // the robot stops rotating whereever it's at
            lastHeading = curHeading;
        } else if (isLastHeadingValid()) {
            // If we're stopping from a user-requested rotation
            // just set the target to the current
            targetHeading = curHeading;
            invalidateLastHeading();
        }

        // The math & signs looks wonky, because this makes things field-relative
        // (Recall that "3 O'Clock" is zero degrees)
        Vector2d input = new Vector2d(-y, -x).rotated(curHeading);
        double rotationPower = getRotationPower(curHeading);
        setWeightedDrivePower(new Pose2d(input.getX(), input.getY(), rotationPower));
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
            // System.out.println("Pose: " + poseDisplay);
        }
    }

    @Override
    public void setMotorPowers(double v, double v1, double v2, double v3) {
        leftFront.setVelocity(v * DriveConstants.VEL_SCALE, AngleUnit.RADIANS);
        leftRear.setVelocity(v1 * DriveConstants.VEL_SCALE, AngleUnit.RADIANS);
        rightRear.setVelocity(v2 * DriveConstants.VEL_SCALE, AngleUnit.RADIANS);
        rightFront.setVelocity(v3 * DriveConstants.VEL_SCALE, AngleUnit.RADIANS);
        /*
        leftFront.setPower(v * DriveConstants.AFL_SCALE);
        leftRear.setPower(v1 * DriveConstants.ARL_SCALE);
        rightRear.setPower(v2 * DriveConstants.ARR_SCALE);
        rightFront.setPower(v3 * DriveConstants.AFR_SCALE);
        */
    }

    // Stuff below is used for tele-op trajectory motion

    public double trajectoryX, trajectoryY, trajectoryAngleRadians;
    public Pose2d targetPose, newPose;
    public double xdifference, ydifference;

    public void requestTrajectoryMove(double deltaX, double deltaY, double deltaAngleRadians) {
        if (!isBusy()) {
            trajectoryX = deltaX;
            trajectoryY = deltaY;
            trajectoryAngleRadians = deltaAngleRadians;
            targetPose = new Pose2d(trajectoryX, trajectoryY, trajectoryAngleRadians);
            startNewTrajectory();
        } else {
            // queueTrajectory(deltaX, deltaY, deltaAngleRadians);

        }
    }

    public void queueTrajectory(double deltaX, double deltaY, double deltaAngleRadians) {
        Pose2d curPose = getPoseEstimate();
        xdifference = targetPose.getX() - curPose.getX();
        ydifference = targetPose.getY() - curPose.getY();
        newPose = new Pose2d(xdifference);
        if (deltaX == 0.0 && targetPose.getX() == 0.0) {
            trajectoryX = 0;
            trajectoryY = ydifference + deltaY;
            trajectoryAngleRadians = deltaAngleRadians;
            startNewTrajectory();
        }
        if (deltaY == 0.0 && targetPose.getY() == 0.0) {
            trajectoryY = 0;
            trajectoryX = xdifference + deltaX;
            trajectoryAngleRadians = deltaAngleRadians;
            startNewTrajectory();
        }
    }

    public void clearRequestedTrajectory() {
        trajectoryX = 0;
        trajectoryY = 0;
        trajectoryAngleRadians = 0;
    }

    public void requestCancelled() {
        this.stop();
    }

    public void startNewTrajectory() {
        Pose2d start = this.getPoseEstimate();
        start = new Pose2d(start.getX() + .01, start.getY(), start.getHeading());
        double endX = start.getX() + this.trajectoryX;
        double endY = start.getY() + this.trajectoryY;
        double endHeading = AngleUnit.normalizeRadians(
            start.getHeading() + this.trajectoryAngleRadians
        );

        this.poseDisplay =
            String.format(
                "%f, %f [%f] => %f, %f [%f]",
                start.getX(),
                start.getY(),
                start.getHeading(),
                endX,
                endY,
                endHeading
            );
        System.out.println(this.poseDisplay);
        // lineToLinearHeading seems to mess things up, maybe? :/
        Trajectory t;
        if (Math.abs(this.trajectoryAngleRadians) > .01) {
            Pose2d end = new Pose2d(endX, endY, endHeading);
            t = this.trajectoryBuilder(start).lineToLinearHeading(end).build();
        } else {
            Vector2d end = new Vector2d(endX, endY);
            t = this.trajectoryBuilder(start).lineTo(end).build();
        }
        this.followTrajectoryAsync(t);
        this.clearRequestedTrajectory();
    }
}
