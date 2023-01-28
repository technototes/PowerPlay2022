package org.firstinspires.ftc.swerveteen750.opmode.auto;

import org.firstinspires.ftc.swerveteen750.subsystem.drive.AnotherPathSegment;

public class AnotherVisionPark extends ProgrammableSimpleSwerveAuto {
    @Override
    public void beforeStart() {

    }

    @Override
    public void setPreStartSegments() {
        this.preStartSegments.add(new AnotherPathSegment(AnotherPathSegment.SegmentType.LOGIC, () -> System.out.println("Looking at the Signal Sleeve!")));
    }

    @Override
    public void setInGameSegments() {
        this.inGameSegments.add(new AnotherPathSegment(AnotherPathSegment.SegmentType.LOGIC, () -> System.out.println("Hello World!")));
        this.inGameSegments.add(new AnotherPathSegment(AnotherPathSegment.SegmentType.TURN, new double[]{0, 0, 0, 0}));
        this.inGameSegments.add(new AnotherPathSegment(AnotherPathSegment.SegmentType.WAIT, 1000));
        this.inGameSegments.add(new AnotherPathSegment(AnotherPathSegment.SegmentType.MOVE, new double[]{0.3, 0.3, 0.3, 0.3}, AnotherPathSegment.WhichModule.RIGHT_FRONT, 10));
    }
}
