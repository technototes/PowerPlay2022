package org.firstinspires.ftc.sixteen750.opmode.diagnosis;

import org.firstinspires.ftc.sixteen750.swerve_util.AbsoluteAnalogEncoder;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;

import com.technototes.library.structure.CommandOpMode;

@TeleOp(group = "Test-Hardware")
@SuppressWarnings("unused")
public class AbsoluteAnalogEncoderDebug extends CommandOpMode {
    AbsoluteAnalogEncoder leftFrontEncoder, leftRearEncoder, rightRearEncoder, rightFrontEncoder;

    boolean isLeftSideConnected = true;
    boolean isRightSideConnected = true;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        /// According to the alternative constructor in SwerveModule
        try {
            leftFrontEncoder = new AbsoluteAnalogEncoder(hardwareMap.get(AnalogInput.class, "leftFrontEncoder"));
            leftRearEncoder = new AbsoluteAnalogEncoder(hardwareMap.get(AnalogInput.class, "leftRearEncoder"));
        } catch (Exception e) {
            isLeftSideConnected = false;
        }
        try {
            rightRearEncoder = new AbsoluteAnalogEncoder(hardwareMap.get(AnalogInput.class, "rightRearEncoder"));
            rightFrontEncoder = new AbsoluteAnalogEncoder(hardwareMap.get(AnalogInput.class, "rightFrontEncoder"));
        } catch (Exception e) {
            isRightSideConnected = false;
        }
    }

    @Override
    public void runLoop() {
        telemetry.addLine("Visit 192.168.43.1:8080/dash to see the FTC-Dashboard");
        if (isLeftSideConnected) {
            telemetry.addData("LeftFront - Position", leftFrontEncoder.getCurrentPosition());
            telemetry.addData("LeftRear - Position", leftRearEncoder.getCurrentPosition());

            telemetry.addData("LeftFront - Voltage", leftFrontEncoder.getVoltage());
            telemetry.addData("LeftRear - Voltage", leftRearEncoder.getVoltage());
        } else {
            telemetry.addLine("WARNING: Left Disconnected");
        }

        if (isRightSideConnected) {
            telemetry.addData("RightRear - Position", rightRearEncoder.getCurrentPosition());
            telemetry.addData("RightFront - Position", rightFrontEncoder.getCurrentPosition());

            telemetry.addData("RightRear - Voltage", rightRearEncoder.getVoltage());
            telemetry.addData("RightFront - Voltage", rightFrontEncoder.getVoltage());
        } else {
            telemetry.addLine("WARNING: Right Disconnected");
        }

        telemetry.update();
    }
}
