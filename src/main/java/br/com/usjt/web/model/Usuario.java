package br.com.usjt.web.model;

public class Usuario {

	private int codClient;
	private long cpf;
	private String name;
	private String dta_nascimento;
	private String email;
	private int ddd;
	private int telephone;
	private char gender;
	private String password;

	public int getCodClient() {
		return codClient;
	}

	public long getCpf() {
		return cpf;
	}

	public String getName() {
		return name;
	}

	public String getDta_nascimento() {
		return dta_nascimento;
	}

	public String getEmail() {
		return email;
	}

	public int getDdd() {
		return ddd;
	}

	public int getTelephone() {
		return telephone;
	}

	public char getGender() {
		return gender;
	}

	public String getPassword() {
		return password;
	}

	public void setCodClient(int codClient) {
		this.codClient = codClient;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDta_nascimento(String dta_nascimento) {
		this.dta_nascimento = dta_nascimento;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
