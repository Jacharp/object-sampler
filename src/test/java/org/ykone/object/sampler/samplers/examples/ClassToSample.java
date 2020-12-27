package org.ykone.object.sampler.samplers.examples;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class ClassToSample {
	private int intField;
	private char charField;
	private String stringField;
	private Boolean booleanField;
	private LocalDate localDateField;
	private LocalDateTime localDateTimeField;
	private Date dateField;
	private Byte byteField;
	private Integer integerField;
	private EnumToSample enumField;
	private File fileField;
	private LinkedList<Calendar> listField;
	private HashSet<Character> setField;
	private HashMap<EnumToSample, Integer> mapField;
	private InterfaceToSample interfaceField;
	
	public int getIntField() {
		return intField;
	}
	public char getCharField() {
		return charField;
	}
	public String getStringField() {
		return stringField;
	}
	public Boolean getBooleanField() {
		return booleanField;
	}
	public LocalDate getLocalDateField() {
		return localDateField;
	}
	public LocalDateTime getLocalDateTimeField() {
		return localDateTimeField;
	}
	public Date getDateField() {
		return dateField;
	}
	public Integer getIntegerField() {
		return integerField;
	}
	public EnumToSample getEnumField() {
		return enumField;
	}
	public File getFileField() {
		return fileField;
	}
	public List<Calendar> getListField(){
		return listField;
	}
	public HashSet<Character> getSetField() {
		return setField;
	}
	public HashMap<EnumToSample, Integer> getMapField() {
		return mapField;
	}
	public InterfaceToSample getInterfaceField() {
		return interfaceField;
	}
	public Byte getByteField(){
		return byteField;
	}
}
