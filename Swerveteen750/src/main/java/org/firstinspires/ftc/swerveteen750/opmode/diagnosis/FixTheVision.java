package org.firstinspires.ftc.swerveteen750.opmode.diagnosis;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.technototes.library.hardware2.HardwareBuilder;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.swerveteen750.Hardware;
import org.firstinspires.ftc.swerveteen750.Robot;
import org.firstinspires.ftc.swerveteen750.command.autonomous.StartingPosition;
import org.firstinspires.ftc.swerveteen750.subsystem.VisionSubsystem;

@Disabled
@Autonomous()
public class FixTheVision extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        HardwareBuilder.initMap(hardwareMap);
        Hardware hardware = new Hardware(hardwareMap, Robot.SubsystemCombo.VISION_ONLY);
        VisionSubsystem vision = new VisionSubsystem(hardware.camera, Alliance.BLUE, StartingPosition.AWAY);
        vision.startVisionPipeline();

        waitForStart();

        while (!isStopRequested() && opModeIsActive()){
        }

        vision.stopVisionPipeline();
    }
}
