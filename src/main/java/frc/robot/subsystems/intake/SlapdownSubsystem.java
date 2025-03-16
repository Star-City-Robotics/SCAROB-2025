package frc.robot.subsystems.intake;

import au.grapplerobotics.LaserCan;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkFlexConfig;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import org.littletonrobotics.junction.Logger;

public class SlapdownSubsystem extends SubsystemBase {

  private final SparkFlex slapdownAngleMotor =
      new SparkFlex(Constants.Slapdown.SLAPDOWN_ANGLE_ID, MotorType.kBrushless);
  private final SparkFlex slapdownRoller1Motor =
      new SparkFlex(Constants.Slapdown.SLAPDOWN_ROLLER_1_ID, MotorType.kBrushless);
  private final SparkFlex slapdownRoller2Motor =
      new SparkFlex(Constants.Slapdown.SLAPDOWN_ROLLER_2_ID, MotorType.kBrushless);
  private final LaserCan slapdownSensor = new LaserCan(Constants.Slapdown.SLAPDOWN_SENSOR_ID);
  // Initialize the closed loop controller
  private final SparkClosedLoopController slapdown_controller =
      slapdownAngleMotor.getClosedLoopController();
  private final RelativeEncoder slapdown_encoder;
  // private final double RotationOffset;

  public SlapdownSubsystem() {

    SparkFlexConfig sparkFlexConfigAngle = new SparkFlexConfig();

    sparkFlexConfigAngle.closedLoop.p(0.05);
    sparkFlexConfigAngle.closedLoop.i(0);
    sparkFlexConfigAngle.closedLoop.d(0);
    sparkFlexConfigAngle.closedLoop.outputRange(-0.1, .1);

    slapdownAngleMotor.configure(
        sparkFlexConfigAngle,
        com.revrobotics.spark.SparkBase.ResetMode.kNoResetSafeParameters,
        PersistMode.kPersistParameters);

    slapdown_encoder = slapdownAngleMotor.getEncoder();

    slapdownAngleMotor.setInverted(false);
  }

  public void periodic() {
    Logger.recordOutput("Slapdown/Position", slapdownAngleMotor.getEncoder().getPosition());
    SmartDashboard.putNumber("Slapdown_position", slapdownAngleMotor.getEncoder().getPosition());
  }

  public void outtakeRollers() {
    slapdownRoller1Motor.set(-0.5);
    slapdownRoller2Motor.set(0.5);
  }

  public void intakeRollers() {
    slapdownRoller1Motor.set(0.5);
    slapdownRoller2Motor.set(-0.5);
  }

  public void stopRollers() {
    slapdownRoller1Motor.stopMotor();
    slapdownRoller2Motor.stopMotor();
  }

  public void angleIntake(double rotation) {
    slapdown_controller.setReference(rotation, ControlType.kPosition);
  }

  public double getIntakePosition() {
    return slapdownAngleMotor.getEncoder().getPosition();
  }
  // public void angleDown() {
  //   slapdownAngleMotor.set(0.1);
  // }

  // public void angleUp() {
  //   slapdownAngleMotor.set(-0.1);
  // }

  // public void stopAngle() {
  //   slapdownAngleMotor.stopMotor();
  // }

  public boolean detectAlgae() {
    LaserCan.Measurement measurement = slapdownSensor.getMeasurement();
    if (measurement.distance_mm <= 20) {
      return true;
    }
    return false;
  }

  public boolean reachedAngle(int rotations) {
    if (slapdownAngleMotor.getEncoder().getPosition() == rotations) {
      return true;
    }
    return false;
  }
}
