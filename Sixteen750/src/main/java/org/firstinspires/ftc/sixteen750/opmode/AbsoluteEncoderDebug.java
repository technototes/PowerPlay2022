package org.firstinspires.ftc.sixteen750.opmode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.technototes.library.structure.CommandOpMode;

import org.firstinspires.ftc.sixteen750.swerve_util.AbsoluteAnalogEncoder;


@TeleOp(group = "Test-Hardware")
@SuppressWarnings("unused")
public class AbsoluteEncoderDebug extends CommandOpMode {
    AbsoluteAnalogEncoder leftFrontEncoder, leftRearEncoder, rightRearEncoder, rightFrontEncoder;

    @Override
    public void uponInit() {
        /// According to the alternative constructor in SwerveModule
        leftFrontEncoder = new AbsoluteAnalogEncoder(hardwareMap.get(AnalogInput.class, "leftFrontEncoder"));
        leftRearEncoder = new AbsoluteAnalogEncoder(hardwareMap.get(AnalogInput.class, "leftRearEncoder"));
        rightRearEncoder = new AbsoluteAnalogEncoder(hardwareMap.get(AnalogInput.class, "rightRearEncoder"));
        rightFrontEncoder = new AbsoluteAnalogEncoder(hardwareMap.get(AnalogInput.class, "rightFrontEncoder"));
    }

    @Override
    public void runLoop() {
        telemetry.addData("LeftFront - Position", leftFrontEncoder.getCurrentPosition());
        telemetry.addData("LeftRear - Position", leftRearEncoder.getCurrentPosition());
        telemetry.addData("RightRear - Position", rightRearEncoder.getCurrentPosition());
        telemetry.addData("RightFront - Position", rightFrontEncoder.getCurrentPosition());

        telemetry.update();
    }
}
