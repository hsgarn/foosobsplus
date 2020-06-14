package com.midsouthfoosball.foosobsplus.commands;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class Memento {
	byte[] serializedObject;

	public Memento(Object object) {
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream so = new ObjectOutputStream(bo);
			so.writeObject(object);
			so.flush();
			serializedObject = bo.toByteArray();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public byte[] getState() {
		return serializedObject;
	}
}
