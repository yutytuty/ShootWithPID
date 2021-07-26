package frc.robot;

import com.spikes2212.command.genericsubsystem.commands.MoveGenericSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.ShootSubsystem;

public class OI /*Gevald*/ {
    private Joystick left = new Joystick(0);

    public OI() {
        Button shoot = new JoystickButton(left, 5);

        shoot.whileHeld(new MoveGenericSubsystem(Robot.shootSubsystem, ShootSubsystem.pid::getVelocityPID));
    }
}
