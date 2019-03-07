package com.smoke;

import java.util.HashSet;
import java.util.Set;

public class CmpareTest {

	public static  class student {
		String name;
		
		int id;
		
		String sex;

		public student(String name, int id, String sex) {
			super();
			this.name = name;
			this.id = id;
			this.sex = sex;
		}
		
		@Override
		public boolean equals(Object obj) {
			student student1 = (student)obj;
			return this.name.equals(student1.name)&&this.sex.equals(student1.sex);
		}
		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return this.name.hashCode() + this.sex.hashCode();
		}
	}
	
	public static void main(String[] args) {
		student student = new student("女1", 1, "女");
		student student1 = new student("女2", 2, "女");
		student student2 = new student("女3", 3, "女");
		student student3 = new student("女4", 4, "女");
		student student4 = new student("女1", 5, "女");
		Set<student> set = new HashSet<>();
		set.add(student);
		set.add(student1);
		set.add(student2);
		set.add(student3);
		set.add(student4);
		System.out.println("121313::::"+set.contains(new student("女4", 4, "女")));
		System.out.println(student.equals(student4));
		System.out.println("size====="+set.size());
		
	}
	
}
