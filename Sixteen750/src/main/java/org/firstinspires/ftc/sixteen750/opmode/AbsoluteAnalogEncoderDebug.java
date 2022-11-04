package org.firstinspires.ftc.sixteen750.opmode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.technototes.library.structure.CommandOpMode;

import org.firstinspires.ftc.sixteen750.swerve_util.AbsoluteAnalogEncoder;


@TeleOp(group = "Test-Hardware")
@SuppressWarnings("unused")
public class AbsoluteAnalogEncoderDebug extends CommandOpMode {
    AbsoluteAnalogEncoder leftFrontEncoder, leftRearEncoder, rightRearEncoder, rightFrontEncoder;

    boolean isLeftSideConnected, isRightSideConnected = true;

    @Override
    public void uponInit() {
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
        if (isLeftSideConnected) {
            telemetry.addData("LeftFront - Position", leftFrontEncoder.getCurrentPosition());
            telemetry.addData("LeftFront - Voltage", leftFrontEncoder.getVoltage());

            telemetry.addData("LeftRear - Position", leftRearEncoder.getCurrentPosition());
            telemetry.addData("LeftRear - Voltage", leftRearEncoder.getVoltage());
        }

        if (isRightSideConnected) {
            telemetry.addData("RightRear - Position", rightRearEncoder.getCurrentPosition());
            telemetry.addData("RightRear - Voltage", rightRearEncoder.getVoltage());

            telemetry.addData("RightFront - Position", rightFrontEncoder.getCurrentPosition());
            telemetry.addData("RightFront - Voltage", rightFrontEncoder.getVoltage());
        }

        telemetry.update();
    }
}
