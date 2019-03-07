package com.online.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

@Retention(RUNTIME)
public @interface EntityDescription {

	/**
	 * 实例名称
	 */
	String caption() default "";
}
