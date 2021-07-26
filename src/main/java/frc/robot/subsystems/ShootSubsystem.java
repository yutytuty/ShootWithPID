package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.spikes2212.command.genericsubsystem.GenericSubsystem;
import com.spikes2212.util.TalonEncoder;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.PID;
import frc.robot.Robot;

public class ShootSubsystem extends GenericSubsystem {

    public static final double distancePerPulse = 10 / 4096.0;
    private WPI_TalonSRX master;
    private WPI_VictorSPX slave;
    public TalonEncoder encoder;

    public static PID pid;

    final double TARGET_SPEED = 5;

    public ShootSubsystem(WPI_TalonSRX master, WPI_VictorSPX slave) {
        this.master = master;
        this.slave = slave;

        this.encoder = new TalonEncoder(this.master, distancePerPulse);

        this.slave.setInverted(true);
        this.slave.follow(this.master);

        pid = new PID(Robot.kP, Robot.kI, Robot.kD, Robot.kV, this.TARGET_SPEED, this.encoder);
    }

    @Override
    public void apply(double speed) {
        this.master.set(speed);
    }

    @Override
    public boolean canMove(double speed) {
        return true;
    }

    @Override
    public void stop() {
        master.stopMotor();
    }
}
