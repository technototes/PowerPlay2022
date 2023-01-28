package org.firstinspires.ftc.swerveteen750.opmode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.technototes.library.hardware2.HardwareBuilder;

import org.firstinspires.ftc.swerveteen750.subsystem.drive.AnotherPathSegment;
import org.firstinspires.ftc.swerveteen750.subsystem.drive.ConfigurableSwerveDriveSubsystem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

@Autonomous(group = "Simple")
public class ProgrammableSimpleSwerveAuto extends LinearOpMode {
    public ArrayList<AnotherPathSegment> preStartSegments = new ArrayList<>(); // for running vision, but will not move
    public ArrayList<AnotherPathSegment> inGameSegments = new ArrayList<>(); // full support for types of segments
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

    public void setInGameSegments() {

    }

    public void setPreStartSegments() {

    }

    public void beforeStart() {

    }

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        HardwareBuilder.initMap(hardwareMap);

        ConfigurableSwerveDriveSubsystem drive = new ConfigurableSwerveDriveSubsystem(hardwareMap);
        drive.setSwerveMotorEncoderZero();

        setInGameSegments();
        setPreStartSegments();

        beforeStart();

        while (!isStarted()) {
            for (AnotherPathSegment segment : preStartSegments) {
                switch (segment.type) {
                    case LOGIC:
                        telemetry.addData("Current State", "LOGIC");
                        telemetry.update();
                        segment.logic.run();
                        break;
                    case WAIT:
                        // probably not a good idea here since we want to listen to isStarted()
                        timer.reset();
                        telemetry.addData("Current State", "WAIT");
                        telemetry.addData("Waiting", segment.waitDuration);
                        telemetry.update();
                        while (shouldContinue() && timer.milliseconds() < segment.waitDuration) {
                            drive.update();
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        waitForStart();

        for (AnotherPathSegment segment : inGameSegments) {
            switch (segment.type){
                case WAIT:
                    timer.reset();
                    telemetry.addData("Current State", "WAIT");
                    telemetry.addData("Waiting", segment.waitDuration);
                    telemetry.update();
                    while (shouldContinue() && timer.milliseconds() < segment.waitDuration) {
                        drive.update();
                    }
                    break;
                case TURN:
                    telemetry.addData("Current State", "TURN");
                    telemetry.addData("Target Orientations", segment.targetOrientationsRadians);
                    telemetry.update();
                    drive.setModuleOrientations(segment.targetOrientationsRadians[0], segment.targetOrientationsRadians[1], segment.targetOrientationsRadians[2], segment.targetOrientationsRadians[3]);
                    timer.reset();
                    while (shouldContinue() && timer.milliseconds() < 500) {
                        drive.update();
                    }
                    break;
                case MOVE:
                    telemetry.addData("Current State", "MOVE");
                    telemetry.addData("Target Distances", segment.targetDistanceFakeInch);
                    telemetry.addData("Target Velocities", segment.motorVelocity);
                    telemetry.update();
                    double startingPosition = 0;
                    switch (segment.measureDistanceFrom){
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
                    drive.setSwerveMotorVelocities(segment.motorVelocity);
                    System.out.println("Velocity Set: " + Arrays.toString(segment.motorVelocity));

                    do {
                        drive.update();
                        switch (segment.measureDistanceFrom){
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
                        telemetry.addData("Current State", "MOVE");
                        telemetry.addData("Target Distances", segment.targetDistanceFakeInch);
                        telemetry.addData("Current Distances", Math.abs(currentPosition - startingPosition));
                        telemetry.addData("Current Velocities", "%f %f %f %f", drive.leftFrontModule.getWheelVelocity(), drive.leftRearModule.getWheelVelocity(), drive.rightFrontModule.getWheelVelocity(), drive.rightRearModule.getWheelVelocity());
                        telemetry.update();
                    } while (shouldContinue() && Math.abs(currentPosition - startingPosition) < segment.targetDistanceFakeInch);
                    drive.setSwerveMotorVelocities(new double[]{0, 0, 0, 0});
                case LOGIC:
                    telemetry.addData("Current State", "LOGIC");
                    telemetry.update();
                    segment.logic.run();
                    break;
                default:
                    break;
            }
        }
    }
}