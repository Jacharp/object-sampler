package org.ykone.object.sampler.samplers.examples;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClassToSampleWithClassAsField {
	
	private ClassToSample classField;
	private byte byteField;
	private double doubleField;
	private float floatField;
	private byte longField;
	private short shortField;
	private BigDecimal bigDecimalField;
	private BigInteger bigIntegerField;
	private java.sql.Date sqlDateField;
	private Calendar calendarField;
	private List<Integer> listField;
	private String[] stringArray;
	private Set<Long> setField;
	private Set<LocalDate> localDateSetField;
	private Map<String, BigDecimal> mapField;
	private Map<Date, Boolean> dateMapField;
	
	
	public ClassToSample getClassField() {
		return classField;
	}
	public byte getByteField() {
		return byteField;
	}
	public double getDoubleField() {
		return doubleField;
	}
	public float getFloatField() {
		return floatField;
	}
	public byte getLongField() {
		return longField;
	}
	public short getShortField() {
		return shortField;
	}
	public BigDecimal getBigDecimalField() {
		return bigDecimalField;
	}
	public BigInteger getBigIntegerField() {
		return bigIntegerField;
	}
	public java.sql.Date getSqlDateField() {
		return sqlDateField;
	}
	public Calendar getCalendarField() {
		return calendarField;
	}
	public List<Integer> getListField() {
		return listField;
	}
	public String[] getStringArray() {
		return stringArray;
	}
	public Set<Long> getSetField() {
		return setField;
	}
	public Set<LocalDate> getLocalDateSetField() {
		return localDateSetField;
	}
	public Map<String, BigDecimal> getMapField() {
		return mapField;
	}
	public Map<Date, Boolean> getDateMapField() {
		return dateMapField;
	}
}
