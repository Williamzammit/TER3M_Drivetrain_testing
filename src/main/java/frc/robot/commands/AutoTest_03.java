// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;
  
public class AutoTest_03 extends CommandBase {
  private final DriveBase driveBase;
  private int counter = 0;
  private int target;
  private int angle;
  private double rotationValue1;
  private int[] distances = {0, 0, 0, 0, 0, 0};
  private double[] distanceValues = {0, 0, 0, 0, 0, 0};
  private int[] checkpoints = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};


  public AutoTest_03(DriveBase driveBase) {
    this.driveBase = driveBase;
    target = (int) (23*50);
/*
    Angle formula
    angle = 45;
    david = angle/10635;
    rotationValue01 = Math.pow(david, (1/6.05));

    Angle Code here
*/
    angle = 90;
    rotationValue1 = Math.pow((angle/10635), (1/6.05));

/*
    Distance Formula
    distance = 60;
    aiden = distance/1668;
    throttleValue_01 = Math.pow(aiden, (1/4.7));

    Distance Code here
*/
    distances[0] = 81;
    distances[1] = 27;
    distances[2] = 32;
    distances[3] = 22;
    distances[4] = 49;
    distances[5] = 17;

    for (int i = 0; i <= distanceValues.length; i++){
    distanceValues[i] = Math.pow(distances[i]/1668, (1/4.7));
    }

    addRequirements(driveBase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    checkpoints[0] = 50;
    for (int i = 1; i <= checkpoints.length; i++){
      checkpoints[i] = 50 + checkpoints[i-1];
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(counter == 0){
      driveBase.arcadeDrive(0.0, 0.0);
    }
    if(counter > 0 && counter < checkpoints[0]){
      driveBase.arcadeDrive(rotationValue1, 0.0);
    }

    counter++;
    SmartDashboard.putNumber("COUNTER", counter);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveBase.setCoastMode();
    counter = 0;
    driveBase.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return counter >= target;
  }
}
