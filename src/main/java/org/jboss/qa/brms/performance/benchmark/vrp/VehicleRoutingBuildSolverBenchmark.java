package org.jboss.qa.brms.performance.benchmark.vrp;

import org.jboss.qa.brms.performance.benchmark.AbstractBuildSolverBenchmark;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;
import org.optaplanner.examples.vehiclerouting.domain.VehicleRoutingSolution;

public class VehicleRoutingBuildSolverBenchmark extends AbstractBuildSolverBenchmark<VehicleRoutingSolution> {

    private static final String DRL_SOLVER_CONFIG = "org/optaplanner/examples/vehiclerouting/solver/vehicleRoutingSolverConfig.xml";
    private static final String CS_SOLVER_CONFIG = "solver/vrp/vehicleRoutingSolverConfig.xml";

    @Benchmark
    public Object buildSolverFactoryCsMeasure(VehicleRoutingBuildSolverBenchmark buildSolverBenchmark, Blackhole blackhole) {
        iterateSolver(buildSolverBenchmark.solverFactoryCs, blackhole);
        return new Object();
    }

    @Benchmark
    public Object buildSolverFactoryDrlMeasure(VehicleRoutingBuildSolverBenchmark buildSolverBenchmark, Blackhole blackhole) {
        iterateSolver(buildSolverBenchmark.solverFactoryDrl, blackhole);
        return new Object();
    }

    @Override
    protected String getDrlSolverConfigResource() {
        return DRL_SOLVER_CONFIG;
    }

    @Override
    protected String getCsSolverConfigResource() {
        return CS_SOLVER_CONFIG;
    }
}
