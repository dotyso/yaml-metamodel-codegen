@args ClientScript clientScript, VueDeclareScriptBlock scriptBlock
@import com.thsoft.metamodel.ui.*
@import com.thsoft.metamodel.ui.script.*
@raw() {
	var vueObject = new Vue({
    	el:'#vueData',
        data:function(){
            return {
            @for(VueData vueData : scriptBlock.getDatas()){
				@if (vueData.getDefaultValue() == null) {
@vueData.getName(): {},
				}
				else {
@vueData.getName(): @vueData.getDefaultValue(),
				}
            }
            }
        },
        methods:{
			@for(VueMethodScriptBlock vueMethod : scriptBlock.getMethods()){
				@vueMethod.render()
            }
        }
	});
}