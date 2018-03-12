package com.thsoft.metamodel.ui.controls;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.reflections.ReflectionUtils;
import com.thsoft.metamodel.MetaModel;
import com.thsoft.metamodel.ui.DataSource;
import com.thsoft.metamodel.ui.Page;
import com.thsoft.metamodel.ui.annotation.WebControlAttribute;
import com.thsoft.metamodel.ui.databinding.DataBinding;
import com.thsoft.metamodel.ui.enums.PageGenerateMode;
import com.thsoft.metamodel.ui.validator.Validatable;

public abstract class Control extends MetaModel {
	
	@WebControlAttribute(name="id")
	private String Id;
	
	@WebControlAttribute(name="name")
	private String name;
	
	@WebControlAttribute(name="v-model")
	private DataBinding dataBinding;
	
    @WebControlAttribute(name="v-show")
	private DataBinding visibleBinding;

    private Page page;
	private String dataContext;
	private Map<String, String> attributes;

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

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
    
	public String getDataContext() {
		return dataContext;
	}

	public void setDataContext(String dataContext) {
		this.dataContext = dataContext;
	}
	
	public DataBinding getDataBinding() {
		return dataBinding;
	}

	public void setDataBinding(DataBinding dataBinding) {
		this.dataBinding = dataBinding;
	}
	
	   
    public DataBinding getVisibleBinding() {
        return visibleBinding;
    }

    public void setVisibleBinding(DataBinding visibleBinding) {
        this.visibleBinding = visibleBinding;
    }

    

    public void createChildControls() {
        
        if (this.getDataContext() == null) {
            this.setDataContext(this.getPage().getDataContext());
        }

        if (this.getDataBinding() != null) {
            this.getDataBinding().setSource(this.getDataContext());
        }
        
        if (this.getVisibleBinding() != null) {
            String source = this.getVisibleBinding().getSource();
            this.getPage().getDataSources().add(new DataSource(source, source, false));
        }
    }

    public void preRender() {
        
        //生成类型为Update和Detail自动创建 
        if (this.getPage().getCurrentGenerateMode().equals(PageGenerateMode.Update) 
                || this.getPage().getCurrentGenerateMode().equals(PageGenerateMode.Detail)) {
            if (StringUtils.isNoneBlank(this.getName()) && StringUtils.isNoneBlank(this.getDataContext())) {
                dataBinding = new DataBinding();
                dataBinding.setSource(this.getDataContext());
                dataBinding.setPath(this.getName());
            }
        }
        else if (this.getPage().getCurrentGenerateMode().equals(PageGenerateMode.Insert)) {
            dataBinding = null;
        }
    }
	
	public String render() {
		return page.getEngine().render(this.getRenderTemplate(), this);		
	}
	
	public String renderAttributes() {
		this.buildRenderAttributes();
		
		StringBuilder sb = new StringBuilder();
		for (String key : attributes.keySet()) {
			sb.append(" " + key + "=\"" + attributes.get(key) + "\"");
		}
		return sb.toString();
	}
	
	protected void buildRenderAttributes() {
		try {
			//if (attributes == null) {
				attributes = new HashMap<String, String>();
				
				Set<Field> fields = ReflectionUtils.getAllFields(this.getClass(), ReflectionUtils.withAnnotation(WebControlAttribute.class));
				for (Field field : fields) {
				    
					field.setAccessible(true);
					Object fieldValue = field.get(this);

					if (fieldValue != null) {
					    String fieldName = field.getName();
		                   
		                //使用注解的设置作为属性名
	                    WebControlAttribute webControlAttribute = field.getAnnotation(WebControlAttribute.class);
	                    if (webControlAttribute != null && StringUtils.isNotBlank(webControlAttribute.name())) {
	                        fieldName = webControlAttribute.name();
	                    }
	                    
						attributes.put(fieldName, fieldValue.toString());
					}
				}
			//}
			
			//含有验证属性
			if (this instanceof Validatable) {
    			Validatable validatable = (Validatable)this;
    			if (validatable != null && validatable.getValidators() != null && validatable.getValidators().isRequired()) {
    			    attributes.put("data-options", "required:true");
    			}
			}
			
			
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}


	@Override
	public String getRenderTemplate() {
		return "ui/" + this.getClass().getSimpleName().toLowerCase() + ".html";
	}


}
