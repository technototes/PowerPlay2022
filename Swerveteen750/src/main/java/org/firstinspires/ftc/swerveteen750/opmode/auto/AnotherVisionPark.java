package org.firstinspires.ftc.swerveteen750.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.technototes.library.util.Alliance;
import com.technototes.vision.hardware.Webcam;

import org.firstinspires.ftc.swerveteen750.Hardware;
import org.firstinspires.ftc.swerveteen750.Robot;
import org.firstinspires.ftc.swerveteen750.command.autonomous.StartingPosition;
import org.firstinspires.ftc.swerveteen750.subsystem.VisionSubsystem;
import org.firstinspires.ftc.swerveteen750.subsystem.drive.AnotherPathSegment;

@Autonomous(group = "Simple")
@SuppressWarnings("unused")
public class AnotherVisionPark extends ProgrammableSimpleSwerveAuto {
    boolean parkLeft = false;
    boolean parkMiddle = false;
    boolean parkRight = false;

    VisionSubsystem vision;

    @Override
    public void beforeStart() {
        vision = new VisionSubsystem(new Webcam(Hardware.HardwareConstant.CAMERA), Alliance.BLUE, StartingPosition.AWAY);
        vision.startVisionPipeline();
    }

    @Override
    public void visionLoop() {
        parkLeft = vision.visionPipeline.left();
        parkRight = vision.visionPipeline.right();
        parkMiddle = vision.visionPipeline.middle();

        telemetry.addData("Park Left", parkLeft);
        telemetry.addData("Park Middle", parkMiddle);
        telemetry.addData("Park Right", parkRight);
        telemetry.update();
    }

    @Override
    public void setInGameSegments() {
        this.inGameSegments.add(new AnotherPathSegment(AnotherPathSegment.SegmentType.LOGIC, () -> System.out.println("Hello World!")));
        this.inGameSegments.add(new AnotherPathSegment(AnotherPathSegment.SegmentType.TURN, new double[]{0, 0, 0, 0}));
        this.inGameSegments.add(new AnotherPathSegment(AnotherPathSegment.SegmentType.WAIT, 1000));
        this.inGameSegments.add(new AnotherPathSegment(AnotherPathSegment.SegmentType.MOVE, new double[]{0.3, 0.3, 0.3, 0.3}, AnotherPathSegment.WhichModule.RIGHT_FRONT, 26));
        if (parkRight) {
            this.inGameSegments.add(new AnotherPathSegment(AnotherPathSegment.SegmentType.TURN, new double[]{Math.PI * 2 / 4 * 3, Math.PI * 2 / 4 * 3, Math.PI * 2 / 4 * 3, Math.PI * 2 / 4 * 3}));
            this.inGameSegments.add(new AnotherPathSegment(AnotherPathSegment.SegmentType.MOVE, new double[]{0.3, 0.3, 0.3, 0.3}, AnotherPathSegment.WhichModule.RIGHT_FRONT, 24));
        } else if (parkLeft) {
            this.inGameSegments.add(new AnotherPathSegment(AnotherPathSegment.SegmentType.TURN, new double[]{Math.PI * 2 / 4 * 1, Math.PI * 2 / 4 * 1, Math.PI * 2 / 4 * 1, Math.PI * 2 / 4 * 1}));
            this.inGameSegments.add(new AnotherPathSegment(AnotherPathSegment.SegmentType.MOVE, new double[]{0.3, 0.3, 0.3, 0.3}, AnotherPathSegment.WhichModule.RIGHT_FRONT, 24));
        }
    }
}
