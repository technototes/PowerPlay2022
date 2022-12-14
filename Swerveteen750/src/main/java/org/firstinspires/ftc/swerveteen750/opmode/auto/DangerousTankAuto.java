package org.firstinspires.ftc.swerveteen750.opmode.auto;

import org.firstinspires.ftc.swerveteen750.subsystem.drive.TankDriveSubsystem;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Disabled
@Autonomous(name = "Move Forward")
public class DangerousTankAuto extends LinearOpMode {
    public static int DURATION_MS = 3000;

    @Override
    public void runOpMode() throws InterruptedException {
        TankDriveSubsystem drive = new TankDriveSubsystem(hardwareMap);
        waitForStart();
        if (isStopRequested()) return;
        ElapsedTime time = new ElapsedTime();
        while (!isStopRequested() && opModeIsActive() && time.milliseconds() < DURATION_MS) {
            drive.setMotorPowers(0.5, -0.5);
            telemetry.addData("Loop Count", time.milliseconds());
            telemetry.update();
        }
        drive.setMotorPowers(0, 0);
        telemetry.addLine("Finished");
    }
}
