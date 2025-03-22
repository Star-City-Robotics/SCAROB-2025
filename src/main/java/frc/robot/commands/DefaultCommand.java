package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

public class DefaultCommand extends Command {

  private boolean commandFinished = true;

  public DefaultCommand() {}

  @Override
  public void initialize() {}

  @Override
  public void execute() {}

  @Override
  public boolean isFinished() {
    return commandFinished;
  }

  @Override
  public void end(boolean interupted) {}
}
