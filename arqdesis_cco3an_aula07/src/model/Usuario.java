package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Usuario implements Serializable {
	
	private String id, nome, senha, cpf, horaEnt, horaSai, cnpj;
	private boolean mudaTemp;
	private int perfil;
	
	public Usuario(String id, String nome, String senha, String cpf, String horaEnt, String horaSai, boolean mudaTemp,
			int perfil, String cnpj) {
		
		setId(id);
		setNome(nome);
		setSenha(senha);
		setCpf(cpf);
		setHoraEnt(horaEnt);
		setHoraSai(horaSai);
		setMudaTemp(mudaTemp); 
		setPerfil(perfil);
		setCnpj(cnpj);
	}
		
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getHoraEnt() {
		return horaEnt;
	}

	public void setHoraEnt(String horaEnt) {
		this.horaEnt = horaEnt;
	}

	public String getHoraSai() {
		return horaSai;
	}

	public void setHoraSai(String horaSai) {
		this.horaSai = horaSai;
	}

	public boolean isMudaTemp() {
		return mudaTemp;
	}

	public void setMudaTemp(boolean mudaTemp) {
		this.mudaTemp = mudaTemp;
	}

	public int getPerfil() {
		return perfil;
	}

	public void setPerfil(int perfil) {
		this.perfil = perfil;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	
}
