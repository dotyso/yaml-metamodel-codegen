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

public class DetailForm extends ContainerControl {
    
    private ActionListener onSubmit;
    private Boolean autoMode = false;
    
    @Override
    public void createChildControls() {
        
        super.createChildControls();
        
        //AutoMode=true时，自动生成子控件
        if (this.getAutoMode() && this.getDataContext() != null) {
            Model model = this.getPage().getModule().getApplication().getModel(this.getDataContext());
            
            List<Control> childControls = new ArrayList<Control>();
            for (Property property : model.getProperties()) {
                TextBox item = new TextBox();
                item.setName(property.getName());
                item.setLabel(property.getLabel());
                item.setPage(this.getPage());
                //item.setDataBinding(new DataBinding());
                childControls.add(item);
            }
            
            this.setControls(childControls);
        }
    }
    
    @Override
    public void preRender() {
        
        //注册js代码块
        if (!this.getPage().getCurrentGenerateMode().equals(PageGenerateMode.Detail)) {
            Map args = new LinkedHashMap();
            args.put("form", this);     
            this.getPage().getClientScript().registerDeclareScriptBlock("detailForm", new ScriptBlock("ui/js/detailForm.js", args));
        }
    }

    public Action getAction() {
        
        if (this.getPage().getCurrentGenerateMode().equals(PageGenerateMode.Custom))
            return this.getPage().getAction(onSubmit.getRefId());
        else {
            //自动生成Action
            return Action.createWithContext(this.getPage()); 
        }
    }
    
    public ActionListener getOnSubmit() {
        return onSubmit;
    }

    public void setOnSubmit(ActionListener onSubmit) {
        this.onSubmit = onSubmit;
    }

    public Boolean getAutoMode() {
        return autoMode;
    }

    public void setAutoMode(Boolean autoMode) {
        this.autoMode = autoMode;
    }
}
