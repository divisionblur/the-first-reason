package com.mj;

public class Asserts {
	public static void test(boolean value) {
		try {
			if (!value) throw new Exception("���Գ���");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
