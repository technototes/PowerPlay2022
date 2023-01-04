package org.firstinspires.ftc.forteaching.BasicOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;


import org.firstinspires.ftc.forteaching.TankDriveDemo;
import org.firstinspires.ftc.forteaching.TankTeachingCode;

public class SquareDrive extends LinearOpMode {

    private DcMotorEx motorL;
    private DcMotorEx motorR;

    private TankTeachingCode tankTeachingCode;

    @Override
    public void runOpMode() throws InterruptedException {
        tankTeachingCode = new TankTeachingCode(motorL, motorR);
        tankTeachingCode.moveForward(1, 2);
        tankTeachingCode.rotateRight(1, 1);

        tankTeachingCode.moveForward(1, 2);
        tankTeachingCode.rotateRight(1, 1);

        tankTeachingCode.moveForward(1, 2);
        tankTeachingCode.rotateRight(1, 1);

        tankTeachingCode.moveForward(1, 2);
        tankTeachingCode.rotateRight(1, 1);

    }


}
