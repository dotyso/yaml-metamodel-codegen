@args ClientScript clientScript, ScriptBlock scriptBlock, DetailForm form
@import com.thsoft.metamodel.domain.*
@import com.thsoft.metamodel.ui.*
@import com.thsoft.metamodel.ui.controls.*
@import com.thsoft.metamodel.ui.script.*
@raw() {
	var formUpdate = new DetailForm({
	    id: "#formUpdate",
        type: enumPageState.Update
	});
	
    var formInsert = new DetailForm({
        id: "#formInsert",
        type: enumPageState.Insert,
        url: '@@url("@form.getAction()")',
        success: function (result) {
            console.log(result);
            //paintTree.AppendTreeNode(result.data.schedulingPriorityID, result.data.priorityCode, result.data.priorityName);//resource set
            document.getElementById("formInsert").reset();
            //switcher.Reload(enumSwitcherBindType);
        }
    });
}