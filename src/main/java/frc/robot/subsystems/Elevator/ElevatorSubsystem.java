package frc.robot.subsystems.Elevator;

import static edu.wpi.first.units.Units.Second;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.SignalLogger;
import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.NeutralOut;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import frc.robot.state.Score.ScoreInput;
import frc.robot.state.StateMachineCallback;
import frc.robot.subsystems.ToggleableSubsystem;
import org.littletonrobotics.junction.Logger;

public class ElevatorSubsystem extends SubsystemBase implements ToggleableSubsystem {

  // motors for the elevator
  private TalonFX leaderMotor;
  private TalonFX followerMotor;
  private double desiredPosition;

  // motor movement
  private double arbitraryFeedForward = 0;
  private MotionMagicVoltage mmReq1 = new MotionMagicVoltage(0);
  private final NeutralOut brake = new NeutralOut();

  // state machine callback handling
  private StateMachineCallback stateMachineCallback;
  private boolean callbackOnThreshold = false;
  private double positionThreshold = 0;
  private boolean raisingThreshold = false;

  private final VoltageOut m_voltReq = new VoltageOut(0.0);

  private boolean enabled;

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  public ElevatorSubsystem(boolean enabled) {
    this.enabled = enabled;
    if (!enabled) return;
    initializeElevatorMotors();
  }

  /*
   * Elevator MOTOR MOVEMENT
   */

  public void moveElevator(double position) {
    if (!enabled) return;

    // do not go outside boundary thresholds
    if (position * ElevatorConstants.gearRatioModifier > ElevatorConstants.maxElevatorPosition) {
      desiredPosition = ElevatorConstants.maxElevatorPosition;
    } else if (position * ElevatorConstants.gearRatioModifier
        < ElevatorConstants.minElevatorPosition) {
      desiredPosition = ElevatorConstants.minElevatorPosition;
    } else {
      desiredPosition = position * ElevatorConstants.gearRatioModifier;
    }

    leaderMotor.setControl(
        mmReq1.withPosition(desiredPosition).withFeedForward(arbitraryFeedForward));
  }

  public void moveElevator(double position, StateMachineCallback callback) {
    stateMachineCallback = callback;
    moveElevator(position);
  }

  public void moveElevator(double position, StateMachineCallback callback, double threshold) {
    stateMachineCallback = callback;
    callbackOnThreshold = true;
    positionThreshold = threshold * ElevatorConstants.gearRatioModifier;
    raisingThreshold = threshold < position;
    moveElevator(position);
  }

  public void stopElevator() {
    if (!enabled) return;
    leaderMotor.setControl(brake);
  }

  // Initialize Motors
  private void initializeElevatorMotors() {
    if (!enabled) return;

    System.out.println("elevatorSubsystem: Starting UP & Initializing elevator motors !!!!!!");
    leaderMotor = new TalonFX(ElevatorConstants.leaderMotorid, "rio");
    followerMotor = new TalonFX(ElevatorConstants.followerMotorid, "rio");
    // Follower followLeader = new Follower(ElevatorConstants.leaderMotorid, false);
    TalonFXConfiguration cfg = new TalonFXConfiguration();
    leaderMotor.getConfigurator().apply(cfg);
    followerMotor.getConfigurator().apply(cfg);
    followerMotor.setControl(new Follower(ElevatorConstants.leaderMotorid, false));

    /* Configure current limits */
    MotionMagicConfigs mm = cfg.MotionMagic;
    mm.MotionMagicCruiseVelocity = 100; // 5 rotations per second cruise
    mm.MotionMagicAcceleration = 10; // Take approximately 0.5 seconds to reach max vel
    // Take approximately 0.2 seconds to reach max accel
    mm.MotionMagicJerk = 0;

    Slot0Configs slot0 = cfg.Slot0;
    // Working PID
    slot0.kG = 0.22529; // 0.01
    slot0.kP = 15; // 1
    slot0.kI = 0; // 0
    slot0.kD = 0; // 0.1
    slot0.kV = 0; // 0
    slot0.kS = 0.15; // 0
    FeedbackConfigs fdb = cfg.Feedback;
    fdb.SensorToMechanismRatio = 1;

    cfg.CurrentLimits.StatorCurrentLimit = 80;
    cfg.CurrentLimits.SupplyCurrentLowerLimit = 60;
    cfg.CurrentLimits.StatorCurrentLimitEnable = true;

    // Apply the configs for Motor 1
    cfg.MotorOutput.Inverted = ElevatorConstants.elevatorMotor1Direction;
    StatusCode status = StatusCode.StatusCodeNotInitialized;
    for (int i = 0; i < 5; ++i) {
      status = leaderMotor.getConfigurator().apply(cfg);
      if (status.isOK()) break;
    }
    if (!status.isOK()) {
      System.out.println("Could not configure device. Error: " + status.toString());
    }

    // Apply the configs for Motor 2
    cfg.MotorOutput.Inverted = ElevatorConstants.elevatorMotor2Direction;
    status = StatusCode.StatusCodeNotInitialized;

    for (int i = 0; i < 5; ++i) {
      status = followerMotor.getConfigurator().apply(cfg);
      if (status.isOK()) break;
    }
    if (!status.isOK()) {
      System.out.println("Could not configure device. Error: " + status.toString());
    }

    leaderMotor.setPosition(0);
    followerMotor.setPosition(0); // TODO: check if this is needed
    leaderMotor.setNeutralMode(NeutralModeValue.Brake);
    followerMotor.setNeutralMode(NeutralModeValue.Brake); // TODO: check if this is needed
  }

  public void periodic() {
    if (!enabled) return;

    /*
     * Score State Machine callback handling
     */
    if (isAtPosition(desiredPosition) && stateMachineCallback != null) {
      // final position reached, notify the state machine

      stateMachineCallback.setInput(ScoreInput.ELEVATOR_DONE);
      stateMachineCallback = null;
    } else if (callbackOnThreshold && stateMachineCallback != null) {
      // check to see if the threshold was met, if so notify the state machine
      boolean thresholdMet =
          raisingThreshold && getElevatorPosition() >= positionThreshold
              || !raisingThreshold && getElevatorPosition() <= positionThreshold;
      if (thresholdMet) {
        stateMachineCallback.setInput(ScoreInput.ELEVATOR_THRESHOLD_MET);
        callbackOnThreshold = false;
        positionThreshold = 0;
      }
    }

    log();

    Logger.recordOutput("Elevator/Position", getElevatorPosition());
  }

  public double getElevatorPosition() {
    if (!enabled) return 0;
    return leaderMotor.getPosition().getValueAsDouble();
  }

  public boolean isAtPosition(double position) {
    double tolerance = 2;
    return Math.abs(getElevatorPosition() - position) < tolerance;
  }

  private void log() {
    SmartDashboard.putNumber(
        "elevator motor 1 position", leaderMotor.getPosition().getValueAsDouble());
    SmartDashboard.putNumber(
        "elevator motor 2 position", followerMotor.getPosition().getValueAsDouble());
    SmartDashboard.putNumber("elevator desired position", desiredPosition);
    SmartDashboard.putNumber(
        "elevator motor 1 closedLoopError", leaderMotor.getClosedLoopError().getValueAsDouble());
    SmartDashboard.putNumber(
        "elevator motor 1 closedLoopError", leaderMotor.getClosedLoopError().getValueAsDouble());
    SmartDashboard.putNumber(
        "elevator motor 1 closedLoopReference",
        leaderMotor.getClosedLoopReference().getValueAsDouble());
    SmartDashboard.putNumber(
        "elevator motor 2 closedLoopReference",
        followerMotor.getClosedLoopReference().getValueAsDouble());
    SmartDashboard.putNumber(
        "elevator motor 1 closedLoopOutput", leaderMotor.getClosedLoopOutput().getValueAsDouble());
    SmartDashboard.putNumber(
        "elevator motor 2 closedLoopOutput",
        followerMotor.getClosedLoopOutput().getValueAsDouble());
    SmartDashboard.putNumber(
        "elevator motor 1 statorCurrent", leaderMotor.getStatorCurrent().getValueAsDouble());
    SmartDashboard.putNumber(
        "elevator motor 2 statorCurrent", followerMotor.getStatorCurrent().getValueAsDouble());
    SmartDashboard.putNumber("Arbitrary Feed Forward", arbitraryFeedForward);
  }

  private final SysIdRoutine m_sysIdRoutine =
      new SysIdRoutine(
          new SysIdRoutine.Config(
              Volts.of(0.2).per(Second), // Use default ramp rate (1 V/s)
              Volts.of(0.2), // Reduce dynamic step voltage to 4 to prevent brownout
              null, // Use default timeout (10 s)
              // Log state with Phoenix SignalLogger class
              (state) -> SignalLogger.writeString("state", state.toString())),
          new SysIdRoutine.Mechanism(
              (volts) -> leaderMotor.setControl(m_voltReq.withOutput(volts.in(Volts))),
              null,
              this));

  public Command sysIdQuasistatic(SysIdRoutine.Direction direction) {
    return m_sysIdRoutine.quasistatic(direction);
  }

  public Command sysIdDynamic(SysIdRoutine.Direction direction) {
    return m_sysIdRoutine.dynamic(direction);
  }
}
