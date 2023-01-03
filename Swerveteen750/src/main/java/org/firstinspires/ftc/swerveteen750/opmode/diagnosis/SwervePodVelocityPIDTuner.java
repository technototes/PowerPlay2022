package org.firstinspires.ftc.swerveteen750.opmode.diagnosis;

import static org.firstinspires.ftc.swerveteen750.subsystem.drive.DriveConstantsForPidTuner.MAX_ACCEL;
import static org.firstinspires.ftc.swerveteen750.subsystem.drive.DriveConstantsForPidTuner.MAX_VEL;
import static org.firstinspires.ftc.swerveteen750.subsystem.drive.DriveConstantsForPidTuner.MOTOR_VELO_PID_UNIVERSAL;
import static org.firstinspires.ftc.swerveteen750.subsystem.drive.DriveConstantsForPidTuner.RUN_USING_ENCODER;
import static org.firstinspires.ftc.swerveteen750.subsystem.drive.DriveConstantsForPidTuner.kV;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.profile.MotionProfile;
import com.acmerobotics.roadrunner.profile.MotionProfileGenerator;
import com.acmerobotics.roadrunner.profile.MotionState;
import com.acmerobotics.roadrunner.util.NanoClock;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.swerveteen750.subsystem.drive.SwerveDriveForVelocityTuning;

import java.util.List;

/*
 * This routine is designed to tune the PID coefficients used by the REV Expansion Hubs for closed-
 * loop velocity control. Although it may seem unnecessary, tuning these coefficients is just as
 * important as the positional parameters. Like the other manual tuning routines, this op mode
 * relies heavily upon the dashboard. To access the dashboard, connect your computer to the RC's
 * WiFi network. In your browser, navigate to https://192.168.49.1:8080/dash if you're using the RC
 * phone or https://192.168.43.1:8080/dash if you are using the Control Hub. Once you've successfully
 * connected, start the program, and your robot will begin moving forward and backward according to
 * a motion profile. Your job is to graph the velocity errors over time and adjust the PID
 * coefficients (note: the tuning variable will not appear until the op mode finishes initializing).
 * Once you've found a satisfactory set of gains, add them to the DriveConstants.java file under the
 * MOTOR_VELO_PID field.
 *
 * Recommended tuning process:
 *
 * 1. Increase kP until any phase lag is eliminated. Concurrently increase kD as necessary to
 *    mitigate oscillations.
 * 2. Add kI (or adjust kF) until the steady state/constant velocity plateaus are reached.
 * 3. Back off kP and kD a little until the response is less oscillatory (but without lag).
 *
 * Pressing Y/Δ (Xbox/PS4) will pause the tuning process and enter driver override, allowing the
 * user to reset the position of the bot in the event that it drifts off the path.
 * Pressing B/O (Xbox/PS4) will cede control back to the tuning process.
 */
@Config
@Autonomous(group = "drive")
public class SwervePodVelocityPIDTuner extends LinearOpMode {
    public static double DISTANCE = 72; // in

    private static MotionProfile generateProfile(boolean movingForward) {
        MotionState start = new MotionState(movingForward ? 0 : DISTANCE, 0, 0, 0);
        MotionState goal = new MotionState(movingForward ? DISTANCE : 0, 0, 0, 0);
        return MotionProfileGenerator.generateSimpleMotionProfile(start, goal, MAX_VEL, MAX_ACCEL);
    }

    @Override
    public void runOpMode() {
        if (!RUN_USING_ENCODER) {
            RobotLog.setGlobalErrorMsg("%s does not need to be run if the built-in motor velocity" +
                    "PID is not in use", getClass().getSimpleName());
        }

        Telemetry telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());

        SwerveDriveForVelocityTuning drive = new SwerveDriveForVelocityTuning(hardwareMap);

        double lastKp = MOTOR_VELO_PID_UNIVERSAL.p;
        double lastKi = MOTOR_VELO_PID_UNIVERSAL.i;
        double lastKd = MOTOR_VELO_PID_UNIVERSAL.d;
        double lastKf = MOTOR_VELO_PID_UNIVERSAL.f;

        drive.setAllMotorPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, MOTOR_VELO_PID_UNIVERSAL);

        NanoClock clock = NanoClock.system();

        telemetry.addLine("Ready!");
        telemetry.update();
        telemetry.clearAll();

        waitForStart();

        if (isStopRequested()) return;

        boolean movingForwards = true;
        MotionProfile activeProfile = generateProfile(true);
        double profileStart = clock.seconds();


        while (!isStopRequested()) {
            // calculate and set the motor power
            double profileTime = clock.seconds() - profileStart;

            if (profileTime > activeProfile.duration()) {
                // generate a new profile
                movingForwards = !movingForwards;
                activeProfile = generateProfile(movingForwards);
                profileStart = clock.seconds();
            }

            MotionState motionState = activeProfile.get(profileTime);
            double targetPower = kV * motionState.getV();
            drive.setMotorPowers(targetPower, targetPower, targetPower, targetPower);

            List<Double> velocities = drive.getWheelVelocities();

            // update telemetry
            telemetry.addData("targetVelocity", motionState.getV());
            for (int i = 0; i < velocities.size(); i++) {
                telemetry.addData("measuredVelocity" + i, velocities.get(i));
                telemetry.addData(
                        "error" + i,
                        motionState.getV() - velocities.get(i)
                );
            }

            if (lastKp != MOTOR_VELO_PID_UNIVERSAL.p || lastKd != MOTOR_VELO_PID_UNIVERSAL.d
                    || lastKi != MOTOR_VELO_PID_UNIVERSAL.i || lastKf != MOTOR_VELO_PID_UNIVERSAL.f) {
                drive.setAllMotorPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, MOTOR_VELO_PID_UNIVERSAL);

                lastKp = MOTOR_VELO_PID_UNIVERSAL.p;
                lastKi = MOTOR_VELO_PID_UNIVERSAL.i;
                lastKd = MOTOR_VELO_PID_UNIVERSAL.d;
                lastKf = MOTOR_VELO_PID_UNIVERSAL.f;
            }

            telemetry.update();
        }
    }
}
