package org.jibble.pircbot;

public class Parse {
	////////////////////////////////////////////////
	public String status;

	public String getStatus() {
		return this.status;
	}

	public void setstatus(String status) {
		this.status = status;
	}
	/////////////////////////////////////////
	public String game_name;

	public String getGame() {
		return this.game_name;
	}
	public void setGame(String game_name) {
		this.game_name= game_name;
	}
	///////////////////////////////
	String stream;
	public void setStream(String stream) {
		this.stream = stream;
	}
	public String getLive(String stream) {
		return this.stream;
	}
	
	///////////////////////////////
	public String moderators;

	public String getMods() {
		return this.moderators;
	}

	public void setMods(String moderators) {
		this.moderators= moderators;
	}
	
	///////////////////////////
	
	public String created_at;

	public String getCreated() {
		return this.created_at;
	}

	public void setCreated(String created_at) {
		this.created_at = created_at;
	}
	
	//////////////////////
	
	public String followers;
	public String getFollowers() {
		return this.followers;
	}

	public void setFollowers(String followers) {
		this.followers = followers;
	}
	///////////////////////////////
	public String display_name;
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public String getDisplay_name() {
		return this.display_name;
	}
    /////////////////////////
	public String teams;
	public void setTeams(String teams) {
		this.teams = teams;
	}
	public String getTeams() {
		return this.teams;
	}
	//////////////////
	public String chatters;

	public String getChatters() {
		return this.chatters;
	}

	public void setChatterCount(String chatters) {
		this.chatters= chatters;
	}
	
	/////////////////////////
	public String chatter_count;

	public String getChatterCount() {
		return this.chatter_count;
	}

	public void setChatters(String chatter_count) {
		this.chatter_count= chatter_count;
	}
	/////////////////////////
	public String data;
	

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data= data;
	}
	public String is_live;
	public String title;
	public String started_at;
}


