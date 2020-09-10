package org.jboss.qa.brms.performance.benchmark;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.optaplanner.core.api.solver.SolverFactory;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3)
@Measurement(iterations = 10)
@Fork(value = 2, warmups = 7, jvmArgs = {"-Xms20g", "-Xmx20g"})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public abstract class AbstractBuildSolverBenchmark<Example> {

    @Param({"1", "2", "5", "10", "100", "1000"})
    protected int iterations;

    protected SolverFactory<Example> solverFactoryCs;
    protected SolverFactory<Example> solverFactoryDrl;

    @Setup
    public void setupFactory() {
        solverFactoryCs = getCsSolverConfigFromXml();
        solverFactoryDrl = getDrlSolverConfigFromXml();
    }

    public SolverFactory<Example> getDrlSolverConfigFromXml() {
        return SolverFactory.createFromXmlResource(getCsSolverConfigResource());
    }

    public SolverFactory<Example> getCsSolverConfigFromXml() {
        return SolverFactory.createFromXmlResource(getDrlSolverConfigResource());
    }

    public void iterateSolver(SolverFactory<Example> solverFactory, Blackhole blackhole) {
        Stream
                .iterate(0, i -> i + 1)
                .limit(iterations)
                .forEach(i -> blackhole.consume(solverFactory.buildSolver()));
    }

    protected abstract String getDrlSolverConfigResource();

    protected abstract String getCsSolverConfigResource();
}
