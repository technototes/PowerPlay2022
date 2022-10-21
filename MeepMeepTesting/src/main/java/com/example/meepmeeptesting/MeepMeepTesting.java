package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;


public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(750);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 9.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(0, 0, 0))
                                .addTrajectory(AutoConstantsBlue.Away.START_TO_W_JUNCTION.get())
                                .addTrajectory(AutoConstantsBlue.Away.W_JUNCTION_TO_BETWEEN_TO_STACK.get())
                                .addTrajectory(AutoConstantsBlue.Away.BETWEEN_T0_STACK_TO_STACK.get())
                                .addTrajectory(AutoConstantsBlue.Away.STACK_TO_BETWEEN_TO_JUNCTION.get())
                                .addTrajectory(AutoConstantsBlue.Away.BETWEEN_TO_JUNCTION_TO_JUNCTION.get())
                                .addTrajectory(AutoConstantsBlue.Away.W_JUNCTION_TO_BETWEEN_TO_STACK.get())
                                .addTrajectory(AutoConstantsBlue.Away.BETWEEN_T0_STACK_TO_STACK.get())
                                .addTrajectory(AutoConstantsBlue.Away.STACK_TO_BETWEEN_TO_JUNCTION.get())
                                .addTrajectory(AutoConstantsBlue.Away.BETWEEN_TO_JUNCTION_TO_JUNCTION.get())
                                .addTrajectory(AutoConstantsBlue.Away.W_JUNCTION_TO_BETWEEN_TO_STACK.get())
                                .addTrajectory(AutoConstantsBlue.Away.BETWEEN_T0_STACK_TO_STACK.get())
                                .addTrajectory(AutoConstantsBlue.Away.STACK_TO_BETWEEN_TO_JUNCTION.get())
                                .addTrajectory(AutoConstantsBlue.Away.BETWEEN_TO_JUNCTION_TO_JUNCTION.get())
                                .addTrajectory(AutoConstantsBlue.Away.W_JUNCTION_TO_BETWEEN_TO_STACK.get())
                                .addTrajectory(AutoConstantsBlue.Away.BETWEEN_T0_STACK_TO_STACK.get())
                                .addTrajectory(AutoConstantsBlue.Away.STACK_TO_BETWEEN_TO_JUNCTION.get())
                                .addTrajectory(AutoConstantsBlue.Away.BETWEEN_TO_JUNCTION_TO_JUNCTION.get())
                                .addTrajectory(AutoConstantsBlue.Away.W_JUNCTION_TO_BETWEEN_TO_STACK.get())
                                .addTrajectory(AutoConstantsBlue.Away.BETWEEN_T0_STACK_TO_STACK.get())
                                .addTrajectory(AutoConstantsBlue.Away.STACK_TO_BETWEEN_TO_JUNCTION.get())
                                .addTrajectory(AutoConstantsBlue.Away.BETWEEN_TO_JUNCTION_TO_JUNCTION.get())
                                .addTrajectory(AutoConstantsBlue.Away.W_JUNCTION_TO_LEFT.get())
                                .addTrajectory(AutoConstantRed.Away.START_TO_W_JUNCTION.get())
                                .addTrajectory(AutoConstantRed.Away.W_JUNCTION_TO_STACK.get())
                                .addTrajectory(AutoConstantRed.Away.STACK_TO_W_JUNCTION.get())
                                .addTrajectory(AutoConstantRed.Away.W_JUNCTION_TO_STACK.get())
                                .addTrajectory(AutoConstantRed.Away.STACK_TO_W_JUNCTION.get())
                                .addTrajectory(AutoConstantRed.Away.W_JUNCTION_TO_STACK.get())
                                .addTrajectory(AutoConstantRed.Away.STACK_TO_W_JUNCTION.get())
                                .addTrajectory(AutoConstantRed.Away.W_JUNCTION_TO_STACK.get())
                                .addTrajectory(AutoConstantRed.Away.STACK_TO_W_JUNCTION.get())
                                .addTrajectory(AutoConstantRed.Away.W_JUNCTION_TO_PARK_LEFT.get())
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}