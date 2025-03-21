// Copyright 2021-2025 FRC 6328
// http://github.com/Mechanical-Advantage
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// version 3 as published by the Free Software Foundation or
// available in the root directory of this project.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.

package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;

/**
 * This class defines the runtime mode used by AdvantageKit. The mode is always "real" when running
 * on a roboRIO. Change the value of "simMode" to switch between "sim" (physics sim) and "replay"
 * (log replay from a file).
 */
public final class Constants {
  public static final Mode simMode = Mode.SIM;
  public static final Mode currentMode = RobotBase.isReal() ? Mode.REAL : simMode;

  public static enum Mode {
    /** Running on a real robot. */
    REAL,

    /** Running a physics simulator. */
    SIM,

    /** Replaying from a log file. */
    REPLAY
  }

  public class CoralManipulator {
    public static final int CORAL_MANIPULATOR_1_ID = 41;
    public static final int CORAL_MANIPULATOR_2_ID = 42;

    public static final int CORAL_SENSOR_ID = 43;
  }

  public class Slapdown {
    public static final int SLAPDOWN_ANGLE_ID = 51;
    public static final int SLAPDOWN_ROLLER_1_ID = 52;
    public static final int SLAPDOWN_ROLLER_2_ID = 53;

    public static final int SLAPDOWN_SENSOR_ID = 54;
  }

  public class Intake {
    public static final int INTAKE_ID = 61;
    public static final int INTAKE_SENSOR_ID = 62;
  }

  public class ScorePositions {
    public static final int ElevatorL4 = 31;
    public static final int ElevatorL3 = 18;
    public static final int ElevatorL2 = 9;
    public static final int ElevatorL1 = 4;
    public static final double ElevatorHome = 0;
    public static final double ElevatorBarge = 36;
    public static final double ElevatorProcessor = 0;
    public static final int ElevatorL3Intake = 17;
    public static final int ElevatorL2Intake = 9;
    public static final double SlapdownOut = -0.6;
    public static final double SlapdownIntake = -2;
    public static final double SlapdownGroundIntake = -3.6;
    public static final double SlapdownOuttakeBarge = -2.5;
    public static final double SlapdownOuttakeProcessor = -1.6;
  }
}
