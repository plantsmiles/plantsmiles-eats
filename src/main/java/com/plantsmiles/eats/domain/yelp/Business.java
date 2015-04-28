package com.plantsmiles.eats.domain.yelp;

import java.util.List;

public class Business {
	private String id;
	private String name;
	private String image_url;
	private String url;
	private String mobile_url;
	private String phone;
	private String display_phone;
	private int review_count;
	private List<List<String>> categories;
	private double distance;
    private double rating;
    private String rating_img_url;
    private String rating_img_url_small;
    private String rating_img_url_large;
    private String snippet_text;
    private String snippet_img_url;
    private Location location;

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getRating_img_url_large() {
        return rating_img_url_large;
    }

    public void setRating_img_url_large(String rating_img_url_large) {
        this.rating_img_url_large = rating_img_url_large;
    }

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMobile_url() {
		return mobile_url;
	}
	public void setMobile_url(String mobile_url) {
		this.mobile_url = mobile_url;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDisplay_phone() {
		return display_phone;
	}
	public void setDisplay_phone(String display_phone) {
		this.display_phone = display_phone;
	}
	public int getReview_count() {
		return review_count;
	}
	public void setReview_count(int review_count) {
		this.review_count = review_count;
	}
	public List<List<String>> getCategories() {
		return categories;
	}
	public void setCategories(List<List<String>> categories) {
		this.categories = categories;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getRating_img_url() {
		return rating_img_url;
	}
	public void setRating_img_url(String rating_img_url) {
		this.rating_img_url = rating_img_url;
	}
	public String getRating_img_url_small() {
		return rating_img_url_small;
	}
	public void setRating_img_url_small(String rating_img_url_small) {
		this.rating_img_url_small = rating_img_url_small;
	}
	public String getSnippet_text() {
		return snippet_text;
	}
	public void setSnippet_text(String snippet_text) {
		this.snippet_text = snippet_text;
	}
	public String getSnippet_img_url() {
		return snippet_img_url;
	}
	public void setSnippet_img_url(String snippet_img_url) {
		this.snippet_img_url = snippet_img_url;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	
}
