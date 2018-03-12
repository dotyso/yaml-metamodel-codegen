package com.thsoft.metamodel.ui.controls;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import com.thsoft.metamodel.domain.Model;
import com.thsoft.metamodel.domain.Property;
import com.thsoft.metamodel.ui.action.Action;
import com.thsoft.metamodel.ui.action.ActionListener;
import com.thsoft.metamodel.ui.databinding.DataBinding;
import com.thsoft.metamodel.ui.enums.PageGenerateMode;
import com.thsoft.metamodel.ui.script.ScriptBlock;

public class DataForm extends Control {

	private List<DataFormItem> items;
	private String dataContext;
	private Boolean autoMode = false;
    private ActionListener onSubmit;
	
	public String getDataContext() {
		return dataContext;
	}

	public void setDataContext(String dataContext) {
		this.dataContext = dataContext;
	}
	
	public List<DataFormItem> getItems() {
		return items;
	}

	public void setItems(List<DataFormItem> items) {
		this.items = items;
	}
	
	public void preRender() {
	     
        if (this.getAutoMode() && this.getDataContext() != null) {
            Model model = this.getPage().getModule().getApplication().getModel(this.getDataContext());
            
            List<DataFormItem> items = new ArrayList<DataFormItem>();
            for (Property property : model.getProperties()) {
                DataFormItem item = new DataFormItem();
                item.setName(property.getName());
                item.setType("TextBox");
                item.setLabel(property.getLabel());
                //item.setDataBinding(new DataBinding());
                items.add(item);
            }
            
            this.setItems(items);
        }
        
        //生成类型为Update和Detail自动创建数据绑定
        if (this.getPage().getCurrentGenerateMode().equals(PageGenerateMode.Update) || this.getPage().getCurrentGenerateMode().equals(PageGenerateMode.Detail)) {
            for (DataFormItem item : this.getItems()) {
                if (StringUtils.isNoneBlank(item.getName()) && StringUtils.isNoneBlank(this.getDataContext())) {
                    DataBinding dataBinding = new DataBinding();
                    dataBinding.setSource(this.getDataContext());
                    dataBinding.setPath(item.getName());
                    
                    item.setDataBinding(dataBinding);
                }
            }
        }
        else if (this.getPage().getCurrentGenerateMode().equals(PageGenerateMode.Insert)) {
            for (DataFormItem item : this.getItems()) {
                item.setDataBinding(null);
            }
        }

        //注册Js代码块
        Map args = new LinkedHashMap();
        args.put("form", this);     
        
        this.getPage().getClientScript().registerDeclareScriptBlock("dataForm", new ScriptBlock("ui/js/dataForm.js", args));
	}

	public String renderChildControls() {
		
		StringBuilder sb = new StringBuilder();


		for (DataFormItem control : this.getItems()) {
			control.setPage(this.getPage());
			if (this.getDataContext() != null) {
				control.setDataContext(this.getDataContext());
			}
			if (control.getDataBinding() != null) {
				control.getDataBinding().setSource(this.getDataContext());
			}
			
			String result = control.render();
			//String result = this.getPage().getEngine().render(control.getRenderTemplate(), control.getControl());
			sb.append(result);
		}
		
		return sb.toString();

	}

    public Action getAction() {
        
        if (this.getPage().getCurrentGenerateMode().equals(PageGenerateMode.Custom))
            return this.getPage().getAction(onSubmit.getRefId());
        else {
            //自动生成Action
            return Action.createWithContext(this.getPage()); 
        }
    }

    public Boolean getAutoMode() {
        return autoMode;
    }

    public void setAutoMode(Boolean autoMode) {
        this.autoMode = autoMode;
    }
    
    public ActionListener getOnSubmit() {
        return onSubmit;
    }

    public void setOnSubmit(ActionListener onSubmit) {
        this.onSubmit = onSubmit;
    }
}
