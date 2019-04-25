package br.com.usjt.web.model;

public class Pedido {
	private int id;
	private int idComanda;
	private Item item;
	private String data;
	private double porcDesconto;
	private char status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getComanda() {
		return idComanda;
	}
	public void setComanda(int idComanda) {
		this.idComanda = idComanda;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public double getPorcDesconto() {
		return porcDesconto;
	}
	public void setPorcDesconto(double porcDesconto) {
		this.porcDesconto = porcDesconto;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	
	public Pedido() {
		super();
	}
	
	
}
