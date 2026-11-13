package com.qa.opencart.listeners;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Re-runs a failed test up to N times. N comes from -Dretry.count (default 2),
 * so the CI pipeline can change it without code changes, e.g. mvn test -Dretry.count=1
 */
public class RetryAnalyzer implements IRetryAnalyzer {

	private static final int MAX_RETRY = Integer.getInteger("retry.count", 2);

	// keyed by method + data-provider params, so each searchTest row gets its own retry budget
	private final Map<String, Integer> attempts = new ConcurrentHashMap<>();

	@Override
	public boolean retry(ITestResult result) {
		String key = result.getMethod().getQualifiedName() + Arrays.toString(result.getParameters());
		int used = attempts.merge(key, 1, Integer::sum);

		if (used <= MAX_RETRY) {
			System.out.println("Retrying " + result.getName() + " | attempt " + used + " of " + MAX_RETRY);
			return true; // TestNG re-runs the test
		}
		return false; // retries exhausted -> final result stays FAILED
	}
}
