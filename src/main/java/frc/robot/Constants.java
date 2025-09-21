// Copyright (c) 2021-2025 Littleton Robotics
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by a BSD
// license that can be found in the LICENSE file
// at the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.signals.InvertedValue;
import edu.wpi.first.wpilibj.RobotBase;

/**
 * This class defines the runtime mode used by AdvantageKit. The mode is always "real" when running
 * on a roboRIO. Change the value of "simMode" to switch between "sim" (physics sim) and "replay"
 * (log replay from a file).
 */
public final class Constants {
  public static final Mode simMode = Mode.SIM;
  public static final Mode currentMode = RobotBase.isReal() ? Mode.REAL : simMode;

  public class Slapdown {
    public static final int SLAPDOWN_ANGLE_ID = 51;
    public static final int SLAPDOWN_ROLLER_1_ID = 52;
    public static final int SLAPDOWN_ROLLER_2_ID = 53;

    public static final int SLAPDOWN_SENSOR_ID = 54;

    public static final double SlapdownOut = -0.6;
    public static final double SlapdownIntake = -2;
    public static final double SlapdownGroundIntake = -3.6;
    public static final double SlapdownOuttakeBarge = -2.5;
    public static final double SlapdownOuttakeProcessor = -1.6;
  }

  public final class Elevator {
    public static final int leaderMotorid = 32;
    public static final int followerMotorid = 31;
    public static final double gearRatioModifier = (1);

    public static final double idleOutput = 0;

    public static final InvertedValue elevatorMotor1Direction =
        InvertedValue.CounterClockwise_Positive;
    public static final InvertedValue elevatorMotor2Direction =
        InvertedValue.CounterClockwise_Positive;

    public static final double elevatorHomePosition = 0;
    public static final double minElevatorPosition = 0;
    public static final double maxElevatorPosition = 37 * gearRatioModifier;

    public static final int ElevatorL4 = 31;
    public static final int ElevatorL3 = 18;
    public static final int ElevatorL2 = 11;
    public static final int ElevatorL1 = 5;
    public static final double ElevatorHome = 0;
    public static final double ElevatorBarge = 36;
    public static final double ElevatorProcessor = 0;
    public static final int ElevatorL3Intake = 17;
    public static final int ElevatorL2Intake = 9;
  }

  public class CoralManipulator {
    public static final int CORAL_MANIPULATOR_1_ID = 41;
    public static final int CORAL_MANIPULATOR_2_ID = 42;

    public static final int CORAL_SENSOR_ID = 43;
  }

  public static enum Mode {
    /** Running on a real robot. */
    REAL,

    /** Running a physics simulator. */
    SIM,

    /** Replaying from a log file. */
    REPLAY
  }
}
