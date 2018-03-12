package com.thsoft.metamodel.ui.script;

public class VueAjaxScriptBlock extends ScriptBlock {

	private String restApi;
	private String vueData;
	
	public VueAjaxScriptBlock(String vueData, String restApi) {
		this.vueData = vueData;
		this.restApi = restApi;
	}
	
	public String getRestApi() {
		return restApi;
	}
	public void setRestApi(String restApi) {
		this.restApi = restApi;
	}
	public String getVueData() {
		return vueData;
	}
	public void setVueData(String vueData) {
		this.vueData = vueData;
	}
}
