package com.thsoft.metamodel.ui.databinding;

import java.text.MessageFormat;

public class DataBinding {
	private String sourceType;
	private String source;
	private String path;
	
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return MessageFormat.format("v-model=\"{0}.{1}\"", source, path);
	}
}
