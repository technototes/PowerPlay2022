package org.firstinspires.ftc.sixteen750.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.sixteen750.subsystem.TankDrivebaseSubsystem;

@Autonomous(name = "Move Forward")
public class DangerousTankAuto extends LinearOpMode {
    public static int DURATION_MS = 300;
    @Override
    public void runOpMode() throws InterruptedException {
        TankDrivebaseSubsystem drive = new TankDrivebaseSubsystem(hardwareMap);
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
