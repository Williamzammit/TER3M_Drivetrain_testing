// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.*;
import frc.robot.subsystems.DriveBase;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveBase drivebase = new DriveBase();
  private final AutoTest_01 autotest_01 = new AutoTest_01(drivebase);
  private final AutoTest_02 autotest_02 = new AutoTest_02(drivebase);
  private final AutoTest_03 autotest_03 = new AutoTest_03(drivebase);

  private final XboxController driver = new XboxController(0);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */


  SendableChooser<Command> m_chooser = new SendableChooser<>();
  public RobotContainer() {

    //Default option here
    m_chooser.setDefaultOption("AutoTest_01", autotest_01);

    //Other options here
    m_chooser.addOption("AutoTest_03", autotest_03);
    m_chooser.addOption("AutoTest_02", autotest_02);

    drivebase.setDefaultCommand(new DriveWithJoystick(drivebase, () -> driver.getLeftY(), () -> driver.getLeftX()));

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    //If no commands is selected on the network table, it will return the default option
    return m_chooser.getSelected();
  }
}