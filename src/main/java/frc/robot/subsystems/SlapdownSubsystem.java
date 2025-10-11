package frc.robot.subsystems;

import au.grapplerobotics.LaserCan;
import au.grapplerobotics.interfaces.LaserCanInterface.Measurement;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkFlexConfig;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Slapdown;

public class SlapdownSubsystem extends SubsystemBase {
  private final SparkFlex slapdownAngleMotor =
      new SparkFlex(Slapdown.SLAPDOWN_ANGLE_ID, MotorType.kBrushless);
  private final SparkClosedLoopController slapdownController =
      slapdownAngleMotor.getClosedLoopController();

  private final SparkFlex slapdownRoller1 =
      new SparkFlex(Slapdown.SLAPDOWN_ROLLER_1_ID, MotorType.kBrushless);
  private final SparkFlex slapdownRoller2 =
      new SparkFlex(Slapdown.SLAPDOWN_ROLLER_2_ID, MotorType.kBrushless);

  private final LaserCan slapdownSensor = new LaserCan(Slapdown.SLAPDOWN_SENSOR_ID);

  public SlapdownSubsystem() {

    SparkFlexConfig sparkFlexConfigAngle = new SparkFlexConfig();

    sparkFlexConfigAngle.closedLoop.p(0.05);
    sparkFlexConfigAngle.closedLoop.i(0);
    sparkFlexConfigAngle.closedLoop.d(0);
    sparkFlexConfigAngle.closedLoop.outputRange(-0.1, .1);

    // Newer version of setInverted
    // sparkFlexConfigAngle.inverted(true);

    slapdownAngleMotor.configure(
        sparkFlexConfigAngle, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);

    slapdownAngleMotor.setInverted(false);
  }

  public void angleIntake(double rotation) {
    slapdownController.setReference(rotation, ControlType.kPosition);
  }

  public boolean reachedAngle(int rotations) {
    if (slapdownAngleMotor.getEncoder().getPosition() == rotations) {
      return true;
    }
    return false;
  }

  public void intakeRollers() {
    slapdownRoller1.set(-0.5);
    slapdownRoller2.set(0.5);
  }

  public void outakeRollers() {
    slapdownRoller1.set(0.5);
    slapdownRoller2.set(-0.5);
  }

  public void stopRollers() {
    slapdownRoller1.stopMotor();
    slapdownRoller2.stopMotor();
  }

  public boolean algaeDetected() {
    if (slapdownRoller1.get() == 0) return true;
    Measurement measurement = slapdownSensor.getMeasurement();
    return measurement.distance_mm <= 10;
  }
}
