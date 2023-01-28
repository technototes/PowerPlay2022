package org.firstinspires.ftc.swerveteen750.subsystem.drive;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.drive.DriveSignal;
import com.acmerobotics.roadrunner.drive.SwerveDrive;
import com.acmerobotics.roadrunner.followers.HolonomicPIDVAFollower;
import com.acmerobotics.roadrunner.followers.TrajectoryFollower;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.acmerobotics.roadrunner.trajectory.constraints.AngularVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MinVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.ProfileAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.outoftheboxrobotics.photoncore.PhotonCore;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.swerveteen750.swerve_util.AnotherBetterSwerveLocalizer;
import org.firstinspires.ftc.swerveteen750.swerve_util.swerve_module.AnotherSwerveModule;
import org.firstinspires.ftc.swerveteen750.swerve_util.LynxModuleUtil;
import org.firstinspires.ftc.swerveteen750.swerve_util.TrajectorySequence;
import org.firstinspires.ftc.swerveteen750.swerve_util.TrajectorySequenceBuilder;
import org.firstinspires.ftc.swerveteen750.swerve_util.TrajectorySequenceRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Config
public class ConfigurableSwerveDriveSubsystem extends SwerveDrive {
    public static PIDCoefficients TRANSLATIONAL_PID = new PIDCoefficients(4, 0, 0);
    public static PIDCoefficients HEADING_PID = new PIDCoefficients(4, 0, 0);

    public static double MAX_SPEED = 1;

    public static double FL_STATIC = 0.2;
    public static double FR_STATIC = 0.2;
    public static double RL_STATIC = 0.2;
    public static double RR_STATIC = 0.2;

    public static double VX_WEIGHT = 1;
    public static double VY_WEIGHT = 1;
    public static double OMEGA_WEIGHT = 1;



    public static double STICK_X_SCALAR = 0.8;
    public static double STICK_Y_SCALAR = 0.8;

    public static double STICK_SCALAR_MAX = 1;
    public static double STICK_SCALAR_HIGH = 0.8;
    public static double STICK_SCALAR_MID = 0.6;
    public static double STICK_SCALAR_LOW = 0.4;
    public static double STICK_SCALAR_MIN = 0.25;

    public static int MAX_PARALLEL_COMMANDS = 8;

    private final TrajectorySequenceRunner trajectorySequenceRunner;

    private final TrajectoryVelocityConstraint velocityConstraint;

    private final TrajectoryAccelerationConstraint accelConstraint;

    private final TrajectoryFollower follower;

    // Made the public so easier for telemetry
    public AnotherSwerveModule leftFrontModule;
    public AnotherSwerveModule leftRearModule;
    public AnotherSwerveModule rightFrontModule;
    public AnotherSwerveModule rightRearModule;
    public List<AnotherSwerveModule> modules;

    private final VoltageSensor batteryVoltageSensor;

    public Thread imuThread;

    private final Object IMULock = new Object();
    // TODO: change them to private
    public double imuAngle = 0;
    public double imuAngleVelocity = 0;
    @GuardedBy("IMULock")
    private BNO055IMU imu;

    // For debugging
    public boolean enableMotor = true;
    public Orientation imuAngularOrientation;
    public AngularVelocity imuAngularVelocity;

    // Extra logging
    public double leftFrontMotorPower, rightFrontMotorPower, leftRearMotorPower, rightRearMotorPower = 0;
    private boolean debugTelemetryEnabled = false;
    private Telemetry telemetry;
    private boolean telemetryCallUpdate = false;
    public Function<ConfigurableSwerveDriveSubsystem, Integer> updateCallback = null;

    /*
     * Constants shared between multiple drive types.
     *
     * TODO: Tune or adjust the following constants to fit your robot. Note that the non-final
     * fields may also be edited through the dashboard (connect to the robot's WiFi network and
     * navigate to https://192.168.49.1:8080/dash). Make sure to save the values here after you
     * adjust them in the dashboard; **config variable changes don't persist between app restarts**.
     *
     * These are not the only parameters; some are located in the localizer classes, drive base classes,
     * and op modes themselves.
     */
    @Config
    public static class SwerveDriveConstant {
        /*
         * These are motor constants that should be listed online for your motors.
         */
        public static final double TICKS_PER_REV = 28;
        public static final double MAX_RPM = 6000;

        /*
         * Set RUN_USING_ENCODER to true to enable built-in hub velocity control using drive encoders.
         * Set this flag to false if drive encoders are not present and an alternative localization
         * method is in use (e.g., tracking wheels).
         *
         * If using the built-in motor velocity PID, update MOTOR_VELO_PID with the tuned coefficients
         * from DriveVelocityPIDTuner.
         */
        public static final boolean RUN_USING_ENCODER = true;
        public static PIDFCoefficients MOTOR_VELO_PID = new PIDFCoefficients(30, 0, 8,
                getMotorVelocityF(MAX_RPM / 60 * TICKS_PER_REV));

        /*
         * These are physical constants that can be determined from your robot (including the track
         * width; it will be tune empirically later although a rough estimate is important). Users are
         * free to chose whichever linear distance unit they would like so long as it is consistently
         * used. The default values were selected with inches in mind. Road runner uses radians for
         * angular distances although most angular parameters are wrapped in Math.toRadians() for
         * convenience. Make sure to exclude any gear ratio included in MOTOR_CONFIG from GEAR_RATIO.
         */
        public static double WHEEL_RADIUS = 1.4; // in
        public static double GEAR_RATIO = 1 / (3.5 * 1.5 * 2); // output (wheel) speed / input (motor) speed
        public static double TRACK_WIDTH = 9; // in

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
        public static double MAX_VEL = 60;
        public static double MAX_ACCEL = 60;
        public static double MAX_ANG_VEL = Math.toRadians(120);
        public static double MAX_ANG_ACCEL = Math.toRadians(120);


        public static double encoderTicksToInches(double ticks) {
            return WHEEL_RADIUS * 2 * Math.PI * GEAR_RATIO * ticks / TICKS_PER_REV;
        }

        public static double rpmToVelocity(double rpm) {
            return rpm * GEAR_RATIO * 2 * Math.PI * WHEEL_RADIUS / 60.0;
        }

        public static double getMotorVelocityF(double ticksPerSecond) {
            // see https://docs.google.com/document/d/1tyWrXDfMidwYyP_5H4mZyVgaEswhOC35gvdmP-V-5hA/edit#heading=h.61g9ixenznbx
            return 32767 / ticksPerSecond;
        }
    }

    public ConfigurableSwerveDriveSubsystem(
            HardwareMap hardwareMap,
            BNO055IMU imu,
            AnotherSwerveModule leftFrontSwerveModule,
            AnotherSwerveModule leftRearSwerveModule,
            AnotherSwerveModule rightFrontSwerveModule,
            AnotherSwerveModule rightRearSwerveModule
    ) {
        // The HardwareMap still needed but now can have arbitrary naming to these hardware devices
        super(SwerveDriveConstant.kV, SwerveDriveConstant.kA, SwerveDriveConstant.kStatic, SwerveDriveConstant.TRACK_WIDTH);

        velocityConstraint = getVelocityConstraint(SwerveDriveConstant.MAX_VEL, SwerveDriveConstant.MAX_ANG_VEL, SwerveDriveConstant.TRACK_WIDTH);
        accelConstraint = getAccelerationConstraint(SwerveDriveConstant.MAX_ACCEL);

        follower = new HolonomicPIDVAFollower(TRANSLATIONAL_PID, TRANSLATIONAL_PID, HEADING_PID,
                new Pose2d(0.5, 0.5, Math.toRadians(5)), 0);

        LynxModuleUtil.ensureMinimumFirmwareVersion(hardwareMap); // The reason why HardwareMap is passed in

        batteryVoltageSensor = hardwareMap.voltageSensor.iterator().next();


        // TODO: adjust the names of the following hardware devices to match your configuration
        synchronized (IMULock) {
            this.imu = imu;
            BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
            parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
            this.imu.initialize(parameters);
        }

        // TODO: If the hub containing the IMU you are using is mounted so that the "REV" logo does not face up,
        //  remap the IMU axes so that the z-axis points upward (normal to the floor.)
        //
        // Before SDK 8.1:
        //
        //             | +Z axis
        //             |
        //             |
        //             |
        //      _______|_____________     +Y axis
        //     /       |_____________/|__________
        //    /   REV / EXPANSION   //
        //   /       / HUB         //
        //  /_______/_____________//
        // |_______/_____________|/
        //        /
        //       / +X axis
        //
        // This diagram is derived from the axes in section 3.4 https://www.bosch-sensortec.com/media/boschsensortec/downloads/datasheets/bst-bno055-ds000.pdf
        // and the placement of the dot/orientation from https://docs.revrobotics.com/duo-control/control-system-overview/dimensions#imu-location
        //
        // For example, if +Y in this diagram faces downwards, you would use AxisDirection.NEG_Y.
        // BNO055IMUUtil.remapZAxis(imu, AxisDirection.NEG_Y);


        this.leftFrontModule = leftFrontSwerveModule;
        this.leftRearModule = leftRearSwerveModule;
        this.rightRearModule = rightRearSwerveModule;
        this.rightFrontModule = rightFrontSwerveModule;

        modules = Arrays.asList(leftFrontModule, leftRearModule, rightRearModule, rightFrontModule);


        if (SwerveDriveConstant.RUN_USING_ENCODER) {
            setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }


        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //TODO instantiate hardware


        if (SwerveDriveConstant.RUN_USING_ENCODER && SwerveDriveConstant.MOTOR_VELO_PID != null) {
            setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, SwerveDriveConstant.MOTOR_VELO_PID);
        }

        // TODO: reverse any motors using DcMotor.setDirection()

        // TODO: if desired, use setLocalizer() to change the localization method
        // for instance, setLocalizer(new ThreeTrackingWheelLocalizer(...));
        setLocalizer(new AnotherBetterSwerveLocalizer(this::getExternalHeading, leftFrontModule, leftRearModule, rightRearModule, rightFrontModule));

        trajectorySequenceRunner = new TrajectorySequenceRunner(follower, HEADING_PID);

        //photon funnies
        PhotonCore.enable();
        PhotonCore.CONTROL_HUB.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        PhotonCore.experimental.setMaximumParallelCommands(MAX_PARALLEL_COMMANDS);
    }
    public static double kStatic = 0.04;
    public static PIDCoefficients LF_SERVO_ROTATION_PID_COEF = new PIDCoefficients(0.8, 0, 0);
    public static PIDCoefficients LR_SERVO_ROTATION_PID_COEF = new PIDCoefficients(0.8, 0, 0);
    public static PIDCoefficients RF_SERVO_ROTATION_PID_COEF = new PIDCoefficients(0.8, 0, 0);
    public static PIDCoefficients RR_SERVO_ROTATION_PID_COEF = new PIDCoefficients(0.8, 0, 0);

    // the default value if PIDFCoefficients(p=10.000000 i=3.000000 d=0.000000 f=0.000000 alg=LegacyPID)
    public static PIDFCoefficients LF_MOTOR_VELO_PIDF_COEF = new PIDFCoefficients(2, 0, 0, 15);
    public static PIDFCoefficients LR_MOTOR_VELO_PIDF_COEF = new PIDFCoefficients(2, 0, 0, 12.5);
    public static PIDFCoefficients RF_MOTOR_VELO_PIDF_COEF = new PIDFCoefficients(0.2, 0, 0, 14);
    public static PIDFCoefficients RR_MOTOR_VELO_PIDF_COEF = new PIDFCoefficients(2, 0, 0, 13.4);


    public ConfigurableSwerveDriveSubsystem(HardwareMap hardwareMap) {
//        this(
//                hardwareMap,
//                hardwareMap.get(BNO055IMU.class, "imu"),
//                new AnotherSwerveModule(hardwareMap, "leftFrontMotor", "leftFrontServo", "leftFrontEncoder", LF_SERVO_ROTATION_PID_COEF, LF_MOTOR_VELO_PIDF_COEF),
//                new AnotherSwerveModule(hardwareMap, "leftRearMotor", "leftRearServo", "leftRearEncoder", LR_SERVO_ROTATION_PID_COEF, LR_MOTOR_VELO_PIDF_COEF),
//                new AnotherSwerveModule(hardwareMap, "rightFrontMotor", "rightFrontServo", "rightFrontEncoder", RF_SERVO_ROTATION_PID_COEF, RF_MOTOR_VELO_PIDF_COEF),
//                new AnotherSwerveModule(hardwareMap, "rightRearMotor", "rightRearServo", "rightRearEncoder", RR_SERVO_ROTATION_PID_COEF, RR_MOTOR_VELO_PIDF_COEF)
//        );
        // TODO:revert this
        this(
                hardwareMap,
                hardwareMap.get(BNO055IMU.class, "imu"),
                new AnotherSwerveModule(hardwareMap, "leftFrontMotor", "leftFrontServo", "leftFrontEncoder", LF_SERVO_ROTATION_PID_COEF, LF_MOTOR_VELO_PIDF_COEF, kStatic),
                new AnotherSwerveModule(hardwareMap, "leftRearMotor", "leftRearServo", "leftRearEncoder", LR_SERVO_ROTATION_PID_COEF, LR_MOTOR_VELO_PIDF_COEF, kStatic),
                new AnotherSwerveModule(hardwareMap, "rightRearMotor", "rightRearServo", "rightRearEncoder", RR_SERVO_ROTATION_PID_COEF, RR_MOTOR_VELO_PIDF_COEF, kStatic),
                new AnotherSwerveModule(hardwareMap, "rightFrontMotor", "rightFrontServo", "rightFrontEncoder", RF_SERVO_ROTATION_PID_COEF, RF_MOTOR_VELO_PIDF_COEF, kStatic)
        );
    }


    public void startIMUThread(LinearOpMode opMode) {
        imuThread = new Thread(() -> {
            while (!opMode.isStopRequested() && opMode.opModeIsActive()) {
                synchronized (IMULock) {
                    imuAngularOrientation = imu.getAngularOrientation();
                    imuAngularVelocity = imu.getAngularVelocity();
                    imuAngle = imuAngularOrientation.firstAngle;
                    imuAngleVelocity = -imuAngularVelocity.xRotationRate;
                }
            }
        });
        imuThread.start();
    }

    public TrajectoryBuilder trajectoryBuilder(Pose2d startPose) {
        return new TrajectoryBuilder(startPose, velocityConstraint, accelConstraint);
    }

    public TrajectoryBuilder trajectoryBuilder(Pose2d startPose, boolean reversed) {
        return new TrajectoryBuilder(startPose, reversed, velocityConstraint, accelConstraint);
    }

    public TrajectoryBuilder trajectoryBuilder(Pose2d startPose, double startHeading) {
        return new TrajectoryBuilder(startPose, startHeading, velocityConstraint, accelConstraint);
    }

    public static TrajectorySequenceBuilder trajectorySequenceBuilder(Pose2d startPose) {
        return new TrajectorySequenceBuilder(
                startPose,
                getVelocityConstraint(SwerveDriveConstant.MAX_VEL, SwerveDriveConstant.MAX_ANG_VEL, SwerveDriveConstant.TRACK_WIDTH), getAccelerationConstraint(SwerveDriveConstant.MAX_ACCEL),
                SwerveDriveConstant.MAX_ANG_VEL, SwerveDriveConstant.MAX_ANG_ACCEL
        );
    }

    public static TrajectorySequenceBuilder trajectorySequenceBuilder(Pose2d startPose, double startHeading) {
        return new TrajectorySequenceBuilder(
                startPose,
                startHeading,
                getVelocityConstraint(SwerveDriveConstant.MAX_VEL, SwerveDriveConstant.MAX_ANG_VEL, SwerveDriveConstant.TRACK_WIDTH), getAccelerationConstraint(SwerveDriveConstant.MAX_ACCEL),
                SwerveDriveConstant.MAX_ANG_VEL, SwerveDriveConstant.MAX_ANG_ACCEL
        );
    }

    public void turnAsync(double angle) {
        trajectorySequenceRunner.followTrajectorySequenceAsync(
                trajectorySequenceBuilder(getPoseEstimate())
                        .turn(angle)
                        .build()
        );
    }

    public void turn(double angle) {
        turnAsync(angle);
        waitForIdle();
    }

    public void followTrajectoryAsync(Trajectory trajectory) {
        trajectorySequenceRunner.followTrajectorySequenceAsync(
                trajectorySequenceBuilder(trajectory.start())
                        .addTrajectory(trajectory)
                        .build()
        );
    }

    public void followTrajectory(Trajectory trajectory) {
        followTrajectoryAsync(trajectory);
        waitForIdle();
    }

    public void followTrajectorySequenceAsync(TrajectorySequence trajectorySequence) {
        trajectorySequenceRunner.followTrajectorySequenceAsync(trajectorySequence);
    }

    public void followTrajectorySequence(TrajectorySequence trajectorySequence) {
        followTrajectorySequenceAsync(trajectorySequence);
        waitForIdle();
    }

    public Pose2d getLastError() {
        return trajectorySequenceRunner.getLastPoseError();
    }

    public void updateModules() {
        StringBuilder logLine = new StringBuilder();
        logLine.append("Modules: ");
        for (AnotherSwerveModule m : modules) {
            m.update();
            logLine.append(m.getServoPower() + ", ");
        }
        System.out.println(logLine.toString());
        PhotonCore.CONTROL_HUB.clearBulkCache();
    }

    public void update() {
        updateModules();
        updatePoseEstimate();
        DriveSignal signal = trajectorySequenceRunner.update(getPoseEstimate(), getPoseVelocity());
        if (signal != null) setDriveSignal(signal);
        if (debugTelemetryEnabled) {
            modulesOrientationTelemetry(this.telemetry, this.telemetryCallUpdate);
        }
        if (this.updateCallback != null) {
            this.updateCallback.apply(this);
        }
    }

    public void waitForIdle() {
        while (!Thread.currentThread().isInterrupted() && isBusy())
            update();
    }

    public boolean isBusy() {
        return trajectorySequenceRunner.isBusy();
    }

    public void setMode(DcMotor.RunMode runMode) {
        for (AnotherSwerveModule m : modules) m.setMode(runMode);
    }

    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior zeroPowerBehavior) {
        for (AnotherSwerveModule m : modules) m.setZeroPowerBehavior(zeroPowerBehavior);
    }

    public void setPIDFCoefficients(DcMotor.RunMode runMode, PIDFCoefficients coefficients) {
        PIDFCoefficients compensatedCoefficients = new PIDFCoefficients(
                coefficients.p, coefficients.i, coefficients.d,
                coefficients.f * 12 / batteryVoltageSensor.getVoltage()
        );

        for (AnotherSwerveModule m : modules)
            m.setPIDFCoefficients(runMode, compensatedCoefficients);
    }

    public void setWeightedDrivePower(@NonNull Pose2d drivePower) {
        drivePower = new Pose2d(
                drivePower.getX(),
                drivePower.getY(),
                drivePower.getHeading()
        );
        Pose2d vel = drivePower;

        if (Math.abs(drivePower.getX()) + Math.abs(drivePower.getY()) + Math.abs(drivePower.getHeading()) > 1) {
            // re-normalize the powers according to the weights
            double denom = VX_WEIGHT * Math.abs(drivePower.getX())
                    + VY_WEIGHT * Math.abs(drivePower.getY())
                    + OMEGA_WEIGHT * Math.abs(drivePower.getHeading());

            vel = new Pose2d(
                    VX_WEIGHT * drivePower.getX(),
                    VY_WEIGHT * drivePower.getY(),
                    OMEGA_WEIGHT * drivePower.getHeading()
            ).div(denom);
        }

        setDrivePower(vel);
    }

    @NonNull
    @Override
    public List<Double> getWheelPositions() {
        List<Double> wheelPositions = new ArrayList<>();
        for (AnotherSwerveModule m : modules)
            wheelPositions.add(m.getUnadjustedWheelInchPosition());
        return wheelPositions;
    }

    @Override
    public List<Double> getWheelVelocities() {
        List<Double> wheelVelocities = new ArrayList<>();
        for (AnotherSwerveModule m : modules) wheelVelocities.add(m.getWheelVelocity());
        return wheelVelocities;
    }

    @Override
    public void setMotorPowers(double v0, double v1, double v2, double v3) {
        leftFrontModule.enableMotor = this.enableMotor;
        rightFrontModule.enableMotor = this.enableMotor;
        leftRearModule.enableMotor = this.enableMotor;
        rightRearModule.enableMotor = this.enableMotor;
        // TODO: figure out the order of the motors
//        if (enableMotor){
//            leftFrontModule.setMotorPower(v0 * LF_MOTOR_SCALAR);
//            leftRearModule.setMotorPower(v1 * LR_MOTOR_SCALAR);
//            rightFrontModule.setMotorPower(v2 * RF_MOTOR_SCALAR);
//            rightRearModule.setMotorPower(v3 * RR_MOTOR_SCALAR);
//        }
//        leftFrontMotorPower = v0 * LF_MOTOR_SCALAR;
//        leftRearMotorPower = v1 * LR_MOTOR_SCALAR;
//        rightFrontMotorPower = v2 * RF_MOTOR_SCALAR;
//        rightRearMotorPower = v3 * RR_MOTOR_SCALAR;


//        if (enableMotor){
//            leftFrontModule.setMotorPower(v0 * 1);
//            leftRearModule.setMotorPower(v1 * 1);
//            rightFrontModule.setMotorPower(v2 * 1);
//            rightRearModule.setMotorPower(v3 * 1);
//        }
//        leftFrontMotorPower = v0;
//        leftRearMotorPower = v1;
//        rightFrontMotorPower = v2;
//        rightRearMotorPower = v3;

        if (enableMotor) {
            leftFrontModule.setMotorVelocity(v0);
            leftRearModule.setMotorVelocity(v1);
            rightFrontModule.setMotorVelocity(v2);
            rightRearModule.setMotorVelocity(v3);
        }
        leftFrontMotorPower = v0;
        leftRearMotorPower = v1;
        rightFrontMotorPower = v2;
        rightRearMotorPower = v3;
    }

    @Override
    public double getRawExternalHeading() {
        return imuAngle;
    }

    @Override
    public Double getExternalHeadingVelocity() {
        // To work around an SDK bug, use -zRotationRate in place of xRotationRate
        // and -xRotationRate in place of zRotationRate (yRotationRate behaves as
        // expected). This bug does NOT affect orientation.
        //
        // See https://github.com/FIRST-Tech-Challenge/FtcRobotController/issues/251 for details.
        return imuAngleVelocity;
    }

    public static TrajectoryVelocityConstraint getVelocityConstraint(double maxVel, double maxAngularVel, double trackWidth) {
        return new MinVelocityConstraint(Arrays.asList(
                new AngularVelocityConstraint(maxAngularVel),
                new MecanumVelocityConstraint(maxVel, trackWidth)
        ));
    }

    public static TrajectoryAccelerationConstraint getAccelerationConstraint(double maxAccel) {
        return new ProfileAccelerationConstraint(maxAccel);
    }

    @NonNull
    @Override
    public List<Double> getModuleOrientations() {
        List<Double> moduleOrientations = new ArrayList<>();
        for (AnotherSwerveModule m : modules) moduleOrientations.add(m.getModuleRotation());

        return moduleOrientations;
    }


    @Override
    public void setModuleOrientations(double v0, double v1, double v2, double v3) {
        leftFrontModule.setTargetRotation(v0);
        leftRearModule.setTargetRotation(v1);
        rightFrontModule.setTargetRotation(v2);
        rightRearModule.setTargetRotation(v3);
    }

    public void setLFModuleOrientations(double v0) {
        leftFrontModule.setTargetRotation((v0));
    }

    public void enableDiagnoseTelemetry(Telemetry telemetry, boolean callUpdate) {
        this.telemetry = telemetry;
        this.debugTelemetryEnabled = true;
        this.telemetryCallUpdate = callUpdate;
    }

    public Integer modulesOrientationTelemetry(Telemetry telemetry, boolean callUpdate) {
        if (telemetry != null) {
            telemetry.addData("LeftFrontTargetOrientation", leftFrontModule.getModuleRotation());
//            telemetry.addData("LeftFrontCurrentOrientation", this.leftFrontModuleCurrentOrientation);
//            telemetry.addData("LeftRearTargetOrientation", this.leftRearModuleTargetOrientation);
//            telemetry.addData("LeftRearCurrentOrientation", this.leftRearModuleCurrentOrientatio
//            ..........n);
//            telemetry.addData("RightFrontTargetOrientation", this.rightFrontModuleTargetOrientation);
//            telemetry.addData("RightFrontCurrentOrientation", this.rightFrontModuleCurrentOrientation);
//            telemetry.addData("RightRearTargetOrientation", this.rightRearModuleTargetOrientation);
//            telemetry.addData("RightRearCurrentOrientation", this.rightRearModuleCurrentOrientation);
            if (callUpdate) {
                telemetry.update();
            }
        }
        return 0;
    }

    public static double getTicksFromInches(double distance) {
        return Math.PI * SwerveDriveConstant.WHEEL_RADIUS * distance;
    }

    public double getLFModuleRotationOrientation() {
        return leftFrontModule.getModuleRotation();
    }

    public double getLRRotationOrientation() {
        return leftRearModule.getModuleRotation();
    }

    public double getRFRotationOrientation() {
        return rightFrontModule.getModuleRotation();
    }

    public double getRRRotationOrientation() {
        return rightRearModule.getModuleRotation();
    }

    public double[] getAdjustedMotorEncoderValue() {
        return new double[]{
                leftFrontModule.getAdjustedWheelInchPosition(),
                leftRearModule.getAdjustedWheelInchPosition(),
                rightFrontModule.getAdjustedWheelInchPosition(),
                rightRearModule.getAdjustedWheelInchPosition()
        };
    }

    public void setPowerForAllMotor(double power) {
        leftFrontModule.setMotorVelocity(power);
        leftRearModule.setMotorVelocity(power);
        rightFrontModule.setMotorVelocity(power);
        rightRearModule.setMotorVelocity(power);
    }

    public void setSwerveMotorEncoderZero() {
        leftFrontModule.setMotorEncoderZero();
        leftRearModule.setMotorEncoderZero();
        rightFrontModule.setMotorEncoderZero();
        rightRearModule.setMotorEncoderZero();
    }

    public void setSwerveMotorVelocities(double[] velocities){
        leftFrontModule.setMotorVelocity(velocities[0]);
        leftRearModule.setMotorVelocity(velocities[1]);
        rightFrontModule.setMotorVelocity(velocities[2]);
        rightRearModule.setMotorVelocity(velocities[3]);
    }
}
