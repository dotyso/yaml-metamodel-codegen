package com.thsoft.metamodel.ui.script;

import java.util.ArrayList;
import java.util.List;

public class VueDeclareScriptBlock extends ScriptBlock {

	private List<VueData> vueDatas = new ArrayList<VueData>();
	private List<VueMethodScriptBlock> vueMethods = new ArrayList<VueMethodScriptBlock>();
	
	public VueDeclareScriptBlock(List<VueData> vueDatas) {
		this.setDatas(vueDatas);
		//this.setMethods(vueMethods);
	}

	public List<VueData> getDatas() {
		return vueDatas;
	}

	public void setDatas(List<VueData> datas) {
		this.vueDatas = datas;
	}

	public List<VueMethodScriptBlock> getMethods() {
		return vueMethods;
	}

	public void setMethods(List<VueMethodScriptBlock> methods) {
		this.vueMethods = methods;
	}

}
