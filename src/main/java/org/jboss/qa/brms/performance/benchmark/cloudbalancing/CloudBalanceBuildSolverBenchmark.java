package org.jboss.qa.brms.performance.benchmark.cloudbalancing;

import org.jboss.qa.brms.performance.benchmark.AbstractBuildSolverBenchmark;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;
import org.optaplanner.examples.cloudbalancing.domain.CloudBalance;

public class CloudBalanceBuildSolverBenchmark extends AbstractBuildSolverBenchmark<CloudBalance> {

    private static final String DRL_SOLVER_CONFIG = "org/optaplanner/examples/cloudbalancing/solver/cloudBalancingSolverConfig.xml";
    private static final String CS_SOLVER_CONFIG = "solver/cloudBalance/cloudBalancingSolverCSConfig.xml";

    @Benchmark
    public Object buildSolverFactoryCsMeasure(CloudBalanceBuildSolverBenchmark buildSolverBenchmark, Blackhole blackhole) {
        iterateSolver(buildSolverBenchmark.solverFactoryCs, blackhole);
        return new Object();
    }

    @Benchmark
    public Object buildSolverFactoryDrlMeasure(CloudBalanceBuildSolverBenchmark buildSolverBenchmark, Blackhole blackhole) {
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
