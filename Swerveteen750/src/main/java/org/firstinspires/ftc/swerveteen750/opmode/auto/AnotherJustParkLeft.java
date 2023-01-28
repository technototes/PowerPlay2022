package org.firstinspires.ftc.swerveteen750.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.swerveteen750.subsystem.drive.AnotherPathSegment;

@Autonomous(group = "Simple")
public class AnotherJustParkLeft extends ProgrammableSimpleSwerveAuto {
    @Override
    public void setInGameSegments() {
        this.inGameSegments.add(new AnotherPathSegment(AnotherPathSegment.SegmentType.TURN, new double[]{0, 0, 0, 0}));
        this.inGameSegments.add(new AnotherPathSegment(AnotherPathSegment.SegmentType.WAIT, 1000));
        this.inGameSegments.add(new AnotherPathSegment(AnotherPathSegment.SegmentType.MOVE, new double[]{0.3, 0.3, 0.3, 0.3}, AnotherPathSegment.WhichModule.RIGHT_FRONT, 10));
        this.inGameSegments.add(new AnotherPathSegment(AnotherPathSegment.SegmentType.WAIT, 1000));
        this.inGameSegments.add(new AnotherPathSegment(AnotherPathSegment.SegmentType.TURN, new double[]{Math.PI * 2 / 4 * 3, Math.PI * 2 / 4 * 3, Math.PI * 2 / 4 * 3, Math.PI * 2 / 4 * 3}));
        this.inGameSegments.add(new AnotherPathSegment(AnotherPathSegment.SegmentType.MOVE, new double[]{0.3, 0.3, 0.3, 0.3}, AnotherPathSegment.WhichModule.RIGHT_FRONT, 3));
    }
}
