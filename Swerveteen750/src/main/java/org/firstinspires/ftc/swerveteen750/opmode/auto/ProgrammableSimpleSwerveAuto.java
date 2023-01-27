package org.firstinspires.ftc.swerveteen750.opmode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.technototes.library.hardware2.HardwareBuilder;

import org.firstinspires.ftc.swerveteen750.subsystem.drive.AnotherPathSegment;
import org.firstinspires.ftc.swerveteen750.subsystem.drive.ConfigurableSwerveDriveSubsystem;

@Autonomous(group = "Simple")
public class ProgrammableSimpleSwerveAuto extends LinearOpMode {
    public AnotherPathSegment[] segments;
    public ElapsedTime timer = new ElapsedTime();

    public void safeSleep(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }
    }

    public boolean shouldContinue() {
        return !isStopRequested() && opModeIsActive();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        HardwareBuilder.initMap(hardwareMap);

        ConfigurableSwerveDriveSubsystem drive = new ConfigurableSwerveDriveSubsystem(hardwareMap);
        drive.setSwerveMotorEncoderZero();

        waitForStart();

        for (AnotherPathSegment segment : segments) {
            switch (segment.type){
                case WAIT:
                    timer.reset();
                    while (shouldContinue() && timer.milliseconds() < segment.waitDuration) {
                        drive.update();
                    }
                    break;
                case TURN:
                    drive.setModuleOrientations(segment.targetOrientations[0], segment.targetOrientations[1], segment.targetOrientations[2], segment.targetOrientations[3]);
                    timer.reset();
                    while (shouldContinue() && timer.milliseconds() < 500) {
                        drive.update();
                    }
                    break;
                case MOVE:
                    double startingPosition = 0;
                    switch (segment.measureFrom){
                        case LEFT_FRONT:
                            startingPosition = drive.leftFrontModule.getUnadjustedWheelInchPosition();
                            break;
                        case LEFT_REAR:
                            startingPosition = drive.leftRearModule.getUnadjustedWheelInchPosition();
                            break;
                        case RIGHT_FRONT:
                            startingPosition = drive.rightFrontModule.getUnadjustedWheelInchPosition();
                            break;
                        case RIGHT_REAR:
                            startingPosition = drive.rightRearModule.getUnadjustedWheelInchPosition();
                            break;
                    }

                    double currentPosition = 0;
                    drive.setModuleVelocities(segment.motorVelocity[0], segment.motorVelocity[1], segment.motorVelocity[2], segment.motorVelocity[3]);

                    do {
                        drive.update();
                        switch (segment.measureFrom){
                            case LEFT_FRONT:
                                currentPosition = drive.leftFrontModule.getUnadjustedWheelInchPosition();
                                break;
                            case LEFT_REAR:
                                currentPosition = drive.leftRearModule.getUnadjustedWheelInchPosition();
                                break;
                            case RIGHT_FRONT:
                                currentPosition = drive.rightFrontModule.getUnadjustedWheelInchPosition();
                                break;
                            case RIGHT_REAR:
                                currentPosition = drive.rightRearModule.getUnadjustedWheelInchPosition();
                                break;
                        }
                    } while (shouldContinue() && Math.abs(currentPosition - startingPosition) < segment.distanceDifference);
                    drive.setModuleVelocities(0, 0, 0, 0);
                default:
                    break;
            }
        }
    }
}