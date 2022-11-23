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
  private int[] distances = new int[6];
  private double[] distanceValues = new double[6];
  private int[] checkpoints = new int[23];


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
    if(counter >= checkpoints[0] && counter < checkpoints[1]){
      driveBase.arcadeDrive(0.0, 0.0);
    }
    if(counter >= checkpoints[1] && counter < checkpoints[2]){
      driveBase.arcadeDrive(0.0, distanceValues[0]);
    }
    /*4*/
    if(counter >= checkpoints[2] && counter < checkpoints[3]){
      driveBase.arcadeDrive(0.0, 0.0);
    }
    if(counter >= checkpoints[3] && counter < checkpoints[4]){
      driveBase.arcadeDrive(0.0, rotationValue1);
    }
    if(counter >= checkpoints[4] && counter < checkpoints[5]){
      driveBase.arcadeDrive(0.0, 0.0);
    }
    if(counter >= checkpoints[5] && counter < checkpoints[6]){
      driveBase.arcadeDrive(0.0, distanceValues[1]);
    }
    /*8*/
    if(counter >= checkpoints[6] && counter < checkpoints[7]){
      driveBase.arcadeDrive(0.0, 0.0);
    }
    if(counter >= checkpoints[7] && counter < checkpoints[8]){
      driveBase.arcadeDrive(0.0, rotationValue1);
    }
    if(counter >= checkpoints[8] && counter < checkpoints[9]){
      driveBase.arcadeDrive(0.0, 0.0);
    }
    if(counter >= checkpoints[9] && counter < checkpoints[10]){
      driveBase.arcadeDrive(0.0, distanceValues[2]);
    }
    /*12*/
    if(counter >= checkpoints[10] && counter < checkpoints[11]){
      driveBase.arcadeDrive(0.0, 0.0);
    }
    if(counter >= checkpoints[11] && counter < checkpoints[12]){
      driveBase.arcadeDrive(0.0, rotationValue1);
    }
    if(counter >= checkpoints[12] && counter < checkpoints[13]){
      driveBase.arcadeDrive(0.0, 0.0);
    }
    if(counter >= checkpoints[13] && counter < checkpoints[14]){
      driveBase.arcadeDrive(0.0, distanceValues[3]);
    }
    /*16*/
    if(counter >= checkpoints[14] && counter < checkpoints[15]){
      driveBase.arcadeDrive(0.0, 0.0);
    }
    if(counter >= checkpoints[15] && counter < checkpoints[16]){
      driveBase.arcadeDrive(0.0, -rotationValue1);
    }
    if(counter >= checkpoints[16] && counter < checkpoints[17]){
      driveBase.arcadeDrive(0.0, 0.0);
    }
    if(counter >= checkpoints[17] && counter < checkpoints[18]){
      driveBase.arcadeDrive(0.0, distanceValues[4]);
    }
    /*20*/
    if(counter >= checkpoints[18] && counter < checkpoints[19]){
      driveBase.arcadeDrive(0.0, 0.0);
    }
    if(counter >= checkpoints[19] && counter < checkpoints[20]){
      driveBase.arcadeDrive(0.0, rotationValue1);
    }
    if(counter >= checkpoints[20] && counter < checkpoints[21]){
      driveBase.arcadeDrive(0.0, 0.0);
    }
    if(counter >= checkpoints[21] && counter < checkpoints[22]){
      driveBase.arcadeDrive(0.0, distanceValues[5]);
    }
    /*20*/

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
