package org.firstinspires.ftc.swerveteen750.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.swerveteen750.subsystem.drive.AnotherPathSegment;

@Autonomous(group = "Simple")
public class AnotherJustParkMiddle extends ProgrammableSimpleSwerveAuto{
    @Override
    public void setInGameSegments() {
        this.inGameSegments.add(new AnotherPathSegment(AnotherPathSegment.SegmentType.TURN, new double[]{0, 0, 0, 0}));
        this.inGameSegments.add(new AnotherPathSegment(AnotherPathSegment.SegmentType.WAIT, 1000));
        this.inGameSegments.add(new AnotherPathSegment(AnotherPathSegment.SegmentType.MOVE, new double[]{0.3, 0.3, 0.3, 0.3}, AnotherPathSegment.WhichModule.RIGHT_FRONT, 30));
        this.inGameSegments.add(new AnotherPathSegment(AnotherPathSegment.SegmentType.WAIT, 3000));
        this.inGameSegments.add(new AnotherPathSegment(AnotherPathSegment.SegmentType.MOVE, new double[]{0.3, 0.3, 0.3, 0.3}, AnotherPathSegment.WhichModule.RIGHT_FRONT, 50));
    }
}
