package org.firstinspires.ftc.sixteen750.opmode.diagnosis;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.structure.CommandOpMode;

import org.firstinspires.ftc.sixteen750.Hardware;
import org.firstinspires.ftc.sixteen750.Robot;

@TeleOp(group = "Test-Hardware")
@SuppressWarnings("unused")
public class LiftEncoderTest extends CommandOpMode {
    public Hardware hardware;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap, Robot.SubsystemCombo.LIFT_ONLY);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void runLoop() {
        telemetry.addData("Left Lift Motor Encoder", hardware.leftLiftMotor.getEncoder().getPosition()); // for just read encoder without the PID Controller running
        telemetry.addData("Left lift motor position", hardware.leftLiftMotor.getDevice().getCurrentPosition());
        telemetry.update();
    }
}
