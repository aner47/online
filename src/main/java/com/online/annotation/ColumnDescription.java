package com.online.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

@Retention(RUNTIME)
public @interface ColumnDescription {
	public static String INPUT_TYPE_DATE = "date";
	public static String INPUT_TYPE_Text = "text";
	public static String INPUT_TYPE_TEXTAREA = "textarea";
	
	/**
	 * 字段标题
	 */
	String caption() default "";
	/**
	 * 输入类型
	 */
	String inputType() default "text";
	/**
	 * 是否隐藏
	 */
	boolean hidden() default false;
	/**
	 * 更新
	 * @return
	 */
	boolean update() default true;
	/**
	 * 新建
	 * @return
	 */
	boolean create() default true;
	
	/**
	 * 查询显示
	 * @return
	 */
	boolean query() default true;
	/**
	 *	过滤条件
	 * @return
	 */
	boolean  filter() default false;


}
