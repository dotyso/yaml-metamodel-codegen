package com.thsoft.metamodel.ui.controls;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.ReflectionUtils;
import com.thsoft.metamodel.MetaModel;
import com.thsoft.metamodel.ui.Page;
import com.thsoft.metamodel.ui.annotation.ControlAttribute;
import com.thsoft.metamodel.ui.databinding.DataBinding;

public abstract class Control extends MetaModel {
	
	@ControlAttribute
	private String Id;
	
	@ControlAttribute
	private String name;
	
	@ControlAttribute
	private DataBinding dataBinding;
	
	private String bind;
	
	public String getBind() {
		return bind;
	}

	public void setBind(String bind) {
		
		if (dataBinding == null) {
			dataBinding = new DataBinding();
		}
		
		dataBinding.setSource(this.getDataContext());
		dataBinding.setPath(bind);
		
		this.bind = bind;
	}

	private Page page;
	
	private String dataContext;

	private Map<String, String> attributes;
	
	public String getDataContext() {
		return dataContext;
	}

	public void setDataContext(String dataContext) {
		this.dataContext = dataContext;
	}
	
	public DataBinding getDataBinding() {
		
		if (dataBinding != null) {
			dataBinding.setSource(this.getDataContext());
		}
		
		return dataBinding;
	}

	public void setDataBinding(DataBinding dataBinding) {
		this.dataBinding = dataBinding;
	}
	
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String render() {
	    
        Map args = new HashMap();
        args.put("control", this);
        
		return page.getEngine().render(this.getRenderTemplate(), args);		
	}
	
	public String renderAttributes() {
		this.buildAttributes();
		
		StringBuilder sb = new StringBuilder();
		for (String key : attributes.keySet()) {
			sb.append(" " + key + "=\"" + attributes.get(key) + "\"");
		}
		return sb.toString();
	}
	
	protected void buildAttributes() {
		try {
			if (attributes == null) {
				attributes = new HashMap<String, String>();
				
				Set<Field> fields = ReflectionUtils.getAllFields(this.getClass(), ReflectionUtils.withAnnotation(ControlAttribute.class));
				for (Field field : fields) {
					field.setAccessible(true);
					Object fieldValue = field.get(this);
					if (fieldValue != null) {
						attributes.put(field.getName(), fieldValue.toString());
					}
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	@Override
	public String getRenderTemplate() {
		return "ui/" + this.getClass().getSimpleName().toLowerCase() + ".html";
	}

	public void init() {
		
		if (this.getDataContext() != null) {
			this.setDataContext(this.getPage().getDataContext());
		}

		if (this.getDataBinding() != null) {
			this.getDataBinding().setSource(this.getPage().getDataContext());
		}
	}
}
