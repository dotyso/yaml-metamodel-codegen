package com.thsoft.metamodel.ui.databinding;

import java.text.MessageFormat;
import org.apache.commons.lang3.StringUtils;

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
	    if (StringUtils.isNoneBlank(path))
	        return MessageFormat.format("{0}.{1}", source, path);
	    else
            return MessageFormat.format("{0}", source);
	}
}
