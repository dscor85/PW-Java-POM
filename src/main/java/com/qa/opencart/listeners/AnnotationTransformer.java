package com.qa.opencart.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

/**
 * Attaches RetryAnalyzer to every @Test at runtime, so no test needs
 * @Test(retryAnalyzer = RetryAnalyzer.class) written by hand.
 */
public class AnnotationTransformer implements IAnnotationTransformer {

	@Override
	@SuppressWarnings("rawtypes")
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		// don't override a retryAnalyzer someone set explicitly on a test
		if (annotation.getRetryAnalyzerClass() == null) {
			annotation.setRetryAnalyzer(RetryAnalyzer.class);
		}
	}
}
