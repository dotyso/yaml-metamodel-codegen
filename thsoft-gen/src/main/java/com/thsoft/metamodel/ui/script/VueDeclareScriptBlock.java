package com.thsoft.metamodel.ui.script;

import java.util.ArrayList;
import java.util.List;

public class VueDeclareScriptBlock extends ScriptBlock {

	private List<String> datas = new ArrayList<String>();
	private List<VueMethodScriptBlock> methods = new ArrayList<VueMethodScriptBlock>();
	
	public VueDeclareScriptBlock(List<String> vueData) {
		this.setDatas(vueData);
	}

	public List<String> getDatas() {
		return datas;
	}

	public void setDatas(List<String> datas) {
		this.datas = datas;
	}

	public List<VueMethodScriptBlock> getMethods() {
		return methods;
	}

	public void setMethods(List<VueMethodScriptBlock> methods) {
		this.methods = methods;
	}

}
