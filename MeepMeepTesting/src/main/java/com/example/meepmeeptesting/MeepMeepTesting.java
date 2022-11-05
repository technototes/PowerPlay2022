package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;


public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(750);
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setDimensions(14, 14)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 9.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(AutoConstantsRed.Away.START)
//                        .addTrajectory(AutoConstantsRed.Away.START_TO_W_JUNCTION.get())
//                        .addTrajectory(AutoConstantsRed.Away.W_JUNCTION_TO_JUNCTION_TO_BETWEEN.get())
//                        .addTrajectory(AutoConstantsRed.Away.BETWEEN_TO_STACK.get())
//                        .addTrajectory(AutoConstantsRed.Away.STACK_TO_STACK_TO_BETWEEN.get())

                                //cone1
                                .addTrajectory(AutoConstantsRed.Home.START_TO_W_JUNCTION.get())


                                .addTrajectory(AutoConstantsRed.Home.W_JUNCTION_TO_BETWEEN.get())
                                .addTrajectory(AutoConstantsRed.Home.BETWEEN_TO_STACK.get())
                                .addTrajectory(AutoConstantsRed.Home.STACK_TO_BETWEEN.get())
                                .addTrajectory(AutoConstantsRed.Home.BETWEEN_TO_W_JUNCTION.get())
                                .addTrajectory(AutoConstantsRed.Home.W_JUNCTION_TO_PARK_MIDDLE.get())


//                                .addTrajectory(AutoConstantsBlue.Away.START_TO_W_JUNCTION.get())
//                                .addTrajectory(AutoConstantsBlue.Away.W_JUNCTION_TO_BETWEEN_TO_STACK.get())
//                                .addTrajectory(AutoConstantsBlue.Away.BETWEEN_T0_STACK_TO_STACK.get())
//                                .addTrajectory(AutoConstantsBlue.Away.STACK_TO_BETWEEN_TO_JUNCTION.get())
//                                .addTrajectory(AutoConstantsBlue.Away.BETWEEN_TO_JUNCTION_TO_JUNCTION.get())
//                                .addTrajectory(AutoConstantsBlue.Away.W_JUNCTION_TO_BETWEEN_TO_STACK.get())
//                                .addTrajectory(AutoConstantsBlue.Away.BETWEEN_T0_STACK_TO_STACK.get())
//                                .addTrajectory(AutoConstantsBlue.Away.STACK_TO_BETWEEN_TO_JUNCTION.get())
//                                .addTrajectory(AutoConstantsBlue.Away.BETWEEN_TO_JUNCTION_TO_JUNCTION.get())
//                                .addTrajectory(AutoConstantsBlue.Away.W_JUNCTION_TO_BETWEEN_TO_STACK.get())
//                                .addTrajectory(AutoConstantsBlue.Away.BETWEEN_T0_STACK_TO_STACK.get())
//                                .addTrajectory(AutoConstantsBlue.Away.STACK_TO_BETWEEN_TO_JUNCTION.get())
//                                .addTrajectory(AutoConstantsBlue.Away.BETWEEN_TO_JUNCTION_TO_JUNCTION.get())
//                                .addTrajectory(AutoConstantsBlue.Away.W_JUNCTION_TO_BETWEEN_TO_STACK.get())
//                                .addTrajectory(AutoConstantsBlue.Away.BETWEEN_T0_STACK_TO_STACK.get())
//                                .addTrajectory(AutoConstantsBlue.Away.STACK_TO_BETWEEN_TO_JUNCTION.get())
//                                .addTrajectory(AutoConstantsBlue.Away.BETWEEN_TO_JUNCTION_TO_JUNCTION.get())
//                                .addTrajectory(AutoConstantsBlue.Away.W_JUNCTION_TO_MIDDLE.get())
// // >>>>>>> 5de6fbddabe59891c8bd45f0edb5ebd37bbfae50
//                                .addTrajectory(AutoConstantsRed.Away.START_TO_W_JUNCTION.get())
//                                .addTrajectory(AutoConstantsRed.Away.W_JUNCTION_TO_JUNCTION_TO_BETWEEN.get())
//                                .addTrajectory(AutoConstantsRed.Away.BETWEEN_TO_STACK.get())
//                                .addTrajectory(AutoConstantsRed.Away.STACK_TO_STACK_TO_BETWEEN.get())
//



                                .build()

                );


        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();

    }
}