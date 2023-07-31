package com.example.FairShareEats.utils.payment.pagSeguro;

import java.util.List;

public class OrderResponsePagSeguro {
	private String id;
	private List<QrCode> qr_codes;
    private String reference_id;
    private String created_at;
    private Customer customer;
    private List<Item> items;
    private List<Link> links;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<QrCode> getQr_codes() {
		return qr_codes;
	}
	public void setQr_codes(List<QrCode> qr_codes) {
		this.qr_codes = qr_codes;
	}
	public String getReference_id() {
		return reference_id;
	}
	public void setReference_id(String reference_id) {
		this.reference_id = reference_id;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
}

class Link {
	private String rel;
	private String href;
	private String media;
	private String type;

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
