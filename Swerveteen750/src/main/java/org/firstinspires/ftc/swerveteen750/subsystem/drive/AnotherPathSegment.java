package org.firstinspires.ftc.swerveteen750.subsystem.drive;

public class AnotherPathSegment {
    public enum SegmentType {
        WAIT,
        TURN,
        MOVE
    }

    public enum WhichModule {
        LEFT_FRONT,
        RIGHT_FRONT,
        RIGHT_REAR,
        LEFT_REAR
    }

    public SegmentType type;

    // WAIT
    public long waitDuration;

    public AnotherPathSegment(SegmentType type, long waitDuration) {
        this.type = type;
        this.waitDuration = waitDuration;
    }

    // TURN
    public double[] targetOrientations;

    public AnotherPathSegment(SegmentType type, double[] targetOrientations) {
        this.type = type;
        this.targetOrientations = targetOrientations;
    }

    // MOVE
    public double[] motorVelocity;
    public WhichModule measureFrom;
    public double distanceDifference;

    public AnotherPathSegment(SegmentType type, double[] motorVelocity, WhichModule measureFrom, double distanceDifference) {
        this.type = type;
        this.motorVelocity = motorVelocity;
        this.measureFrom = measureFrom;
        this.distanceDifference = distanceDifference;
    }
}
