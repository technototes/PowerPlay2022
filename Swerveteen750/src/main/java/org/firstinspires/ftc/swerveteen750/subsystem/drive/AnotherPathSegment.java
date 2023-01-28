package org.firstinspires.ftc.swerveteen750.subsystem.drive;

public class AnotherPathSegment {
    public enum SegmentType {
        WAIT,
        TURN,
        MOVE,
        LOGIC
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
    public double[] targetOrientationsRadians;

    public AnotherPathSegment(SegmentType type, double[] targetOrientationsRadians) {
        this.type = type;
        this.targetOrientationsRadians = targetOrientationsRadians;
    }

    // MOVE
    public double[] motorVelocity;
    public WhichModule measureFrom;
    public double targetDistanceFakeInch;


    public AnotherPathSegment(SegmentType type, double[] motorVelocity, WhichModule measureFrom, double targetDistanceFakeInch) {
        this.type = type;
        this.motorVelocity = motorVelocity;
        this.measureFrom = measureFrom;
        this.targetDistanceFakeInch = targetDistanceFakeInch;
    }

    // LOGIC
    public Runnable logic;
    public AnotherPathSegment(SegmentType type, Runnable logic) {
        this.type = type;
        this.logic = logic;
    }
}
