package util;

import java.util.function.Consumer;

public class Analyzer {
	public static void p(Object line) { System.out.println(line); }
	public static void benchmark(Consumer<String> consumer) {
		long before = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long startTime = System.currentTimeMillis();
		
		consumer.accept(null);
		
		long endTime = System.currentTimeMillis();
		long after = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        p("Execution time : " + (endTime - startTime) + "ms");
        p("Memory Consumed : " + (after - before));
	}
}
