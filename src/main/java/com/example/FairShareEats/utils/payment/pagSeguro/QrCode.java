package com.example.FairShareEats.utils.payment.pagSeguro;

import java.util.List;

public class QrCode {
    private String id;
    private String expiration_date;
    private Amount amount;
    private String text;
    private List<String> arrangements;
    private List<Link> links;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getExpiration_date() {
		return expiration_date;
	}
	public void setExpiration_date(String expiration_date) {
		this.expiration_date = expiration_date;
	}
	public Amount getAmount() {
		return amount;
	}
	public void setAmount(Amount amount) {
		this.amount = amount;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<String> getArrangements() {
		return arrangements;
	}
	public void setArrangements(List<String> arrangements) {
		this.arrangements = arrangements;
	}
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	public Link getLinkPaymentQrCode() {
	    if (this.links != null) {
	        return this.links.stream()
	            .filter(link -> "image/png".equals(link.getMedia()))
	            .findFirst()
	            .orElse(null);
	    }
	    return null;
	}
}
