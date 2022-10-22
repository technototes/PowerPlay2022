package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;


public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(750);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setDimensions(12, 12)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 9.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(0, 0, 0))
                                .addTrajectory(AutoConstantsBlue.Home.START_TO_E_JUNCTION.get())
                                .addTrajectory(AutoConstantsBlue.Home.E_JUNCTION_TO_BETWEEN.get())
                                .addTrajectory(AutoConstantsBlue.Home.BETWEEN_TO_STACK.get())
                                .addTrajectory(AutoConstantsBlue.Home.STACK_TO_BETWEEN.get())
                                .addTrajectory(AutoConstantsBlue.Home.BETWEEN_TO_E_JUNCTION.get())
                                .addTrajectory(AutoConstantsBlue.Home.E_JUNCTION_TO_BETWEEN.get())
                                .addTrajectory(AutoConstantsBlue.Home.BETWEEN_TO_STACK.get())
                                .addTrajectory(AutoConstantsBlue.Home.STACK_TO_BETWEEN.get())
                                .addTrajectory(AutoConstantsBlue.Home.BETWEEN_TO_E_JUNCTION.get())
                                .addTrajectory(AutoConstantsBlue.Home.E_JUNCTION_TO_BETWEEN.get())
                                .addTrajectory(AutoConstantsBlue.Home.BETWEEN_TO_STACK.get())
                                .addTrajectory(AutoConstantsBlue.Home.STACK_TO_BETWEEN.get())
                                .addTrajectory(AutoConstantsBlue.Home.BETWEEN_TO_E_JUNCTION.get())
                                .addTrajectory(AutoConstantsBlue.Home.E_JUNCTION_TO_BETWEEN.get())
                                .addTrajectory(AutoConstantsBlue.Home.BETWEEN_TO_STACK.get())
                                .addTrajectory(AutoConstantsBlue.Home.STACK_TO_BETWEEN.get())
                                .addTrajectory(AutoConstantsBlue.Home.BETWEEN_TO_E_JUNCTION.get())
                                .addTrajectory(AutoConstantsBlue.Home.E_JUNCTION_TO_BETWEEN_2.get())
                                .addTrajectory(AutoConstantsBlue.Home.BETWEEN_2_TO_BETWEEN_3.get())
                                .addTrajectory(AutoConstantsBlue.Home.BETWEEN_3_TO_LEFT.get())
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}