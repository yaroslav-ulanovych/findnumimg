package com.chyvonalu.findnumimg.core;

public enum Digit {
	_0(0), _1(1), _2(2), _3(3), _4(4), _5(5), _6(6), _7(7), _8(8), _9(9);

	public final int value;

	Digit(int value) {
		this.value = value;
	}

	public static Digit fromInt(int value) {
		if (value >= 0 && value <= 9) {
			return values()[value];
		} else {
			throw new IllegalArgumentException("bad digit: " + value);
		}
	}
}
