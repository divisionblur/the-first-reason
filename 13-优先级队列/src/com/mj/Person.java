package com.mj;

public class Person implements Comparable<Person> {

	private String name;

	private int boneBreak;

	public Person(String name, int boneBreak) {
		this.name = name;
		this.boneBreak = boneBreak;
	}
	
	@Override
	public int compareTo(Person person) {
		return this.boneBreak - person.boneBreak;   //比较优先级！谁骨头断得多谁就优先治病！
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", boneBreak=" + boneBreak + "]";
	}
}
