@args ClientScript clientScript, VueAjaxScriptBlock scriptBlock
@import com.thsoft.metamodel.ui.*
@import com.thsoft.metamodel.ui.script.*
@raw() {
    $.ajax({
        url:'@scriptBlock.getRestApi()',
        type:'get',
        success:function(data){
            vueObject.@scriptBlock.getVueData() = data.rows;
        },
        error:function(err){
            console.log(err);
        }
    });
 }