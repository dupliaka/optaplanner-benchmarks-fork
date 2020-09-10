# Optaplanner benchmarks fork 

Micro benchmarks of Optaplanner examples for single-time
performance measurements

The main idea is to compare average time 
spent on building solution that use DRL or Constraint Streams.

Before running the benchmark make sure you build it,
it is better to avoid side numbers of measuring work of cycles 
that creates the load itself. Build the project with:
 
`mvn clean install -DskipTests -Dversion.org.optaplanner=<OptaplannerVersion>`

`mvn clean install -DskipTests -Dversion.org.optaplanner=7.39.0.Final`

Then execute the benchmark

`java -jar ./target/optaplanner-benchmark-forks.jar  -jvmArgs "-Xms6144m -Xmx6144m" -v NORMAL -foe true -gc true -wi 4 -i 10 -f 10 -rf csv -rff results.csv`

- **buildSolverFactoryCs** benchmark of solution build on factory that used configuration for Constraint Stream provider class
- **buildSolverFactoryDrl** benchmark of solution build on factory that used configuration for Drl 