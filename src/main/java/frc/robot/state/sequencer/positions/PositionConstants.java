package frc.robot.state.sequencer.positions;

// (Evens) This class is were you list the positions you want for each sequence
public final class PositionConstants {

  /*
   * !!!!!!!!!!!!!!!!!!!!!!!!
   * CORAL INTAKE POSITION CONSTANTS
   * !!!!!!!!!!!!!!!!!!!!!!!!
   */
  
  public static final class CORAL_INTAKE {
    public static final class CORAL_FEEDER {
      public static final double moveElevatorPosition = 0; // TODO: define value
    }

    //public static final class CORAL_FLOOR_INTAKE {
      //public static final class CORAL_FEEDER {
        //public static final double moveElevatorPosition = 0; // TODO: define value
      //}
    //}

    //public static final class CORAL_FLOOR_UPRIGHT {
      //public static final double moveElevatorPosition = 0; // TODO: define value
    //}
  }

  /*
   * !!!!!!!!!!!!!!!!!!!!!!!!
   * CORAL SCORE POSITION CONSTANTS
   * !!!!!!!!!!!!!!!!!!!!!!!!
   */

  public final class CORAL_SCORE {
    // TODO needs real positions, we haven't worked on this yet
    public static final class CORAL_L1 {
      public static final double moveElevatorPosition = 4;
    }

    public static final class CORAL_L2 {
      public static final double moveElevatorPosition = 9;
    }

    public static final class CORAL_L3 {
      public static final double moveElevatorPosition = 17;
    }

    public static final class CORAL_L4 {
      public static final double moveElevatorPosition = 30;
    }
  }

  /*
   * !!!!!!!!!!!!!!!!!!!!!!!!
   * ALGAE INTAKE POSITION CONSTANTS
   * !!!!!!!!!!!!!!!!!!!!!!!!
   */

  public static final class ALGAE_INTAKE {
    public static final class ALGAE_REEF_L2 {
      public static final double moveElevatorPosition = 0; // TODO: define value
    }

    public static final class ALGAE_REEF_L3 {
      public static final double moveElevatorPosition = 0; // TODO: define value
    }

    public static final class ALGAE_FLOOR {
      public static final double moveElevatorPosition = 0; // TODO: define value
    }
  }

  /*
   * !!!!!!!!!!!!!!!!!!!!!!!!
   * ALGAE SCORE POSITION CONSTANTS
   * !!!!!!!!!!!!!!!!!!!!!!!!
   */

  public static final class ALGAE_SCORE {
    public static final class ALGAE_SCORE_BARGE {
      public static final double moveElevatorPosition = 0; // TODO: define value
    }

    public static final class ALGAE_SCORE_PROCESSOR {
      public static final double moveElevatorPosition = 0; // TODO: define value
    }
  }
}
