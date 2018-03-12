@args ClientScript clientScript, VueDeclareScriptBlock scriptBlock
@import com.thsoft.metamodel.ui.*
@import com.thsoft.metamodel.ui.script.*
@raw() {
	var vueObject = new Vue({
    	el:'#vueData',
        data:function(){
            return {
            	
			@for(String vueDate : scriptBlock.getDatas()){
				@vueDate: '',
            }
            }
        },
        methods:{
			@for(VueMethodScriptBlock vueMethod : scriptBlock.getMethods()){
				@vueMethod.render()
            }
    });
 }