package net.johnathan.user.api;

public class AuthResponse {
	private String email;
	private String token;

	public AuthResponse() { }
	
	public AuthResponse(String email, String accessToken) {
		this.email = email;
		this.token = accessToken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void seToken(String accessToken) {
		this.token = accessToken;
	}

}
