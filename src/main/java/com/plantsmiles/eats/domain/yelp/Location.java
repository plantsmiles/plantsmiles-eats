package com.plantsmiles.eats.domain.yelp;

import java.util.List;

public class Location {
	private Coordinate coordinate;
	private List<String> address;
	private List<String> display_address;
	private String city;
	private String state_code;
	private String postal_code;
	private String country_code;
	private String cross_streets;
	private List<String> neighborhoods;
	private double geo_accuracy;
	public Coordinate getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	public List<String> getAddress() {
		return address;
	}
	public void setAddress(List<String> address) {
		this.address = address;
	}
	public List<String> getDisplay_address() {
		return display_address;
	}
	public void setDisplay_address(List<String> display_address) {
		this.display_address = display_address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState_code() {
		return state_code;
	}
	public void setState_code(String state_code) {
		this.state_code = state_code;
	}
	public String getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public String getCross_streets() {
		return cross_streets;
	}
	public void setCross_streets(String cross_streets) {
		this.cross_streets = cross_streets;
	}
	public List<String> getNeighborhoods() {
		return neighborhoods;
	}
	public void setNeighborhoods(List<String> neighborhoods) {
		this.neighborhoods = neighborhoods;
	}
	public double getGeo_accuracy() {
		return geo_accuracy;
	}
	public void setGeo_accuracy(double geo_accuracy) {
		this.geo_accuracy = geo_accuracy;
	}
}
