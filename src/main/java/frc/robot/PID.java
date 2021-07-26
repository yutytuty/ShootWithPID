package frc.robot;

import com.spikes2212.util.TalonEncoder;
import edu.wpi.first.wpilibj.Timer;

import java.util.function.Supplier;

public class PID {
    Supplier<Double> kP, kI, kD, kV;
    double lastCallTime, previousError = 0;
    double target;

    TalonEncoder encoder;

    double error, errorSum, errorRate;

    public PID(Supplier<Double> kP, Supplier<Double> kI, Supplier<Double> kD, Supplier<Double> kV,
               double target, TalonEncoder encoder) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kV = kV;
        this.target = target;
        this.encoder = encoder;
        this.lastCallTime = -1;
    }

    public double getVelocityPID() {
        double dt = lastCallTime == -1 ? 0 : Timer.getFPGATimestamp() - lastCallTime;
        error = target - encoder.getVelocity();
        errorSum += error * dt;
        errorRate = (error - previousError) / dt;
        previousError = error;

        lastCallTime = Timer.getFPGATimestamp();

        if (kP == null || kP.get() == null) {
            throw new NullPointerException("kP");
        }
        if (kI == null || kI.get() == null) {
            throw new NullPointerException("kI");
        }
        if (kD == null || kD.get() == null) {
            throw new NullPointerException("kD");
        }
        if (kV == null || kV.get() == null) {
            throw new NullPointerException("kV");
        }
        return kP.get() * error + kI.get() * errorSum + kD.get() * errorRate + kV.get() * encoder.getVelocity();
    }
}
