package org.spotify.model;

public class CurrencyReponse {
    public String from;
    public String to;	
    public double rate;
    public String date;	
    public String source;
    public double value;
    public double convertedValue;

    public String getFrom() {
	return from;
    }
    public String getTo() {
	return to;
    }
    public double getRate() {
	return rate;
    }
    public String getDate() {
	return date;
    }
    public String getSource() {
	return source;
    }
    public double getValue() {
	return value;
    }
    public double getConvertedValue() {
	return convertedValue;
    }
    public void setConvertedValue(double value) {
	this.convertedValue = value;
    }
    public void setValue(double value) {
	this.value = value;
    }
}
