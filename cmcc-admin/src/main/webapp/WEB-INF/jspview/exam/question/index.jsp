<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
</head>
<body >
    <div class="wrapper wrapper-content animated fadeInRight">
       <div class="row">
            <div class="col-sm-12">
                <div class="ibox ">
                    <div class="ibox-content">
                        <form role="form" class="form-inline">
                         <div class="form-group">
                                <label for="" class="sr-only">类别</label>
                                <select name='categoryid'  class="form-control" >
                                <option value=""></option>
                                <c:forEach items="${categorys}"  var="bean">
                            	    <option value="${bean.id }">${bean.name }</option>
                                </c:forEach>
                                </select>
                                
                            </div>
                            
                            <div class="form-group">
                                <label for="exampleInputEmail2" class="sr-only">题目</label>
                                <input type="text" placeholder="题目 "  id="_title" class="form-control">
                            </div>
                            <button class="btn btn-primary" type="button" id='_search'>查询</button>
                              <button class="btn btn-primary" type="button" id='_new'>新增</button>
                        </form>
                    </div>
                    
                    <div class="ibox-content ">
                         <table ID='dt_table_view' class="table table-striped table-bordered table-hover ">
                            <thead>
                                <tr>
									<th>id</th>
									<th>类别</th>
									<th>题目</th>
									<th>题型</th>
									<th>难度</th>
									<th>创建时间</th>
									<th>操作</th>
								</tr>
                            </thead>
                       		 <tbody>
                            </tbody>
                          </table>
                    </div>
                    
                </div>
            </div>
        </div>
   </div>
   
   <div id='_form' style="display: none;">
       <div class="ibox-content">
 		 <div class="row">
                            <div class="col-sm-12 b-r">
		                           <form class="form-horizontal" action="" method="get">
		                           <input name='id' type="hidden"/>
		                           	<table class='table table-bordered'>
		                           		<thead>
		                           		<tr style="text-align: center;" ><td colspan="6" ><h3>题库管理</h3></td></tr>
		                           		</thead>
		                           		<tbody>
		                           			
		                           			<tr>
		                           				<td>类别</td>
		                           				<td> 
		                           					<select name='category.id'  class="form-control" >
						                                <option value=""></option>
						                                <c:forEach items="${categorys}"  var="bean">
						                            	    <option value="${bean.id }">${bean.name }</option>
						                                </c:forEach>
						                                </select>
		                           				</td>
		                           			</tr>
		                           			<tr>
		                           				<td>题干</td>
		                           				<td> 
		                           					<textarea name='title' rows="3" class="form-control"></textarea>
		                           				</td>
		                           			</tr>
		                           			
		                           			<tr>
			                           			<td>题目类型</td>
			                           			<td> 
			                           				<select name='type'  class="form-control" >
			                           					<c:forEach items="${types}" var="type" >
			                           						<option value="${type.name()}">${type.lable }</option>
			                           					</c:forEach>
			                           				</select>
			                           			</td>
			                           		</tr>
		                           			<tr>
			                           			<td>难度</td>
			                           			<td> 
			                           				<select  name='level'  class="form-control">
			                           					<c:forEach items="${levels}" var="level">
			                           						<option  value="${level.name()}">${level.lable }</option>
			                           					</c:forEach>
			                           				</select>
			                           			</td>
			                           		</tr>
			                           		
			                           		<tr>
			                           			<td>选项</td>
			                           			<td>
				                           			<table class='table' id='exam_items_table'>
				                           			<thead>
				                           			
				                           			<tr>
				                           				<th>答案</th>
				                           				<th>正确选项</th>
				                           			</tr>
				                           			</thead>
				                           			<!-- 单选题 -->
				                           			<tbody id='SINGLECHOICE' >
				                           					<tr>
				                           						<td><input type="text"  name='answers[0].title' class="form-control"/></td>
				                           						<td>
				                           							<label class='checkbox-inline'> <input  type="radio"  name="answers[0].isRight"  value="true" ></label>
				                           						</td>
				                           					</tr>
				                           					
				                           				   <tr>
				                           						<td><input  type="text" name='answers[1].title' class="form-control"/></td>
				                           						<td>
				                           							<label class='checkbox-inline'> <input type="radio" name="answers[1].isRight"  value="true" ></label>
				                           						</td>
				                           					</tr>
				                           					
				                           					  <tr>
				                           						<td><input  type="text" name='answers[2].title' class="form-control"/></td>
				                           						<td>
				                           							<label class='checkbox-inline'> <input type="radio" name="answers[2].isRight"  value="true" ></label>
				                           						</td>
				                           					</tr>
				                           					
				                           					 <tr>
				                           						<td><input  type="text" name='answers[3].title' class="form-control"/></td>
				                           						<td>
				                           							<label class='checkbox-inline'> <input type="radio" name="answers[3].isRight"  value="true" ></label>
				                           						</td>
				                           					</tr>
				                           				</tbody>
				                           				
				                           				
				                           				<!-- 多选题 -->
				                           			 <tbody id='MULTISELECT' style="display: none;">
				                           					<tr>
				                           						<td><input  type="text"  name='answers[0].title' class="form-control"/></td>
				                           						<td>
				                           							<label class="checkbox-inline">
																	  <input type="checkbox" name="answers[0].isRight"  value="true"> 
																	</label>
				                           						</td>
				                           					</tr>
				                           					
				                           				   <tr>
				                           						<td><input  type="text" name='answers[1].title' class="form-control"/></td>
				                           						<td>
				                           							<label class="checkbox-inline">
																	  <input type="checkbox" name="answers[1].isRight"  value="true"> 
																	</label>
				                           						</td>
				                           					</tr>
				                           					
				                           					  <tr>
				                           						<td><input   type="text" name='answers[2].title' class="form-control"/></td>
				                           						<td>
				                           							<label class="checkbox-inline">
																	  <input type="checkbox" name="answers[2].isRight"  value="true"> 
																	</label>
				                           						</td>
				                           					</tr>
				                           					
				                           					 <tr>
				                           						<td><input  type="text" name='answers[3].title' class="form-control"/></td>
				                           						<td>
				                           							<label class="checkbox-inline">
																	  <input type="checkbox" name="answers[3].isRight"  value="true"> 
																	</label>
				                           						</td>
				                           					</tr>
				                           				</tbody>
				                           				
				                           				
				                           				 <tbody id='TRUEORFALSE' style="display: none;">
				                           			
				                           					<tr>
				                           						<td><input   type="text" name='answers[0].title' class="form-control" value="正确"/></td>
				                           						<td>
																	<label class='checkbox-inline'> <input type="radio"  name="answers[0].isRight"  value="true" ></label>
				                           						</td>
				                           					</tr>
				                           					
				                           				   <tr>
				                           						<td><input  type="text"  name='answers[1].title' class="form-control" value="错误"/></td>
				                           						<td>
				                           							<label class='checkbox-inline'> <input type="radio"  name="answers[0].isRight"  value="true" ></label>
				                           						</td>
				                           					</tr>
				                           					
				                           				</tbody>
				                           				
				                           				
			                           				</table>
			                           			</td>
			                           		</tr>
		                           			<tr>
		                           				<td colspan="6"> 
		                           					 <div class="col-sm-4 col-sm-offset-2">
		                                  			  		<button class="btn btn-primary" type="button" onclick="submit_form()">提交</button>
		                                   				    <button class="btn btn-white" type="submit">取消</button>
		                               				 </div>
		                           				</td>
		                           			</tr>
		                           		</tbody>
		                           	</table>
		                           	</form>
                            </div>
                        </div>
                        </div>
   </div>
   <script>
    var table=null;
    function fun_cleanform(){
    	$("input[name='id']").val("");
    	$("select[name='level']").val("LEVEL1");
    	$("select[name='type']").val("SINGLECHOICE");
    	$("select[name='category.id']").val("");
    	$("textarea[name='title']").val("");
    	
    	$("#SINGLECHOICE input[type='text']").val("");
    	$("#MULTISELECT input[type='text']").val("");
    	
    	$("#exam_items_table input[type='checkbox']").prop("checked",false);
    	$("#exam_items_table input[type='radio']").prop("checked",false);
    	
    }
    function get_data(){
    	 var data="category.id="+$("select[name='category.id']").val()+
    			"&title="+$("textarea[name='title']").val()+
    			"&type="+$("select[name='type']").val()+
    			"&id="+$("input[name='id']").val()+
    			"&level="+$("select[name='level']").val();
    	
    	if(!$("#SINGLECHOICE").is(":hidden")){
    		for(i=0;i<$("#SINGLECHOICE input[type='text']").length;i++){
        		data+="&answers["+i+"].title="+$("#SINGLECHOICE input[type='text']").eq(i).val();
        	}
        	for(i=0;i<$("#SINGLECHOICE input[type='radio']").length;i++){
        		data+="&answers["+i+"].isRight="+$("#SINGLECHOICE input[type='radio']").eq(i).is(":checked");	 
        	}
    	}
    	if(!$("#MULTISELECT").is(":hidden")){
    		for(i=0;i<$("#MULTISELECT input[type='text']").length;i++){
        		data+="&answers["+i+"].title="+$("#MULTISELECT input[type='text']").eq(i).val();
        	}
        	for(i=0;i<$("#MULTISELECT input[type='checkbox']").length;i++){
        		data+="&answers["+i+"].isRight="+$("#MULTISELECT input[type='checkbox']").eq(i).is(":checked");	 
        	}
    	}
    	
    	if(!$("#TRUEORFALSE").is(":hidden")){
    		for(i=0;i<$("#TRUEORFALSE input[type='text']").length;i++){
        		data+="&answers["+i+"].title="+$("#TRUEORFALSE input[type='text']").eq(i).val();
        	}
        	for(i=0;i<$("#TRUEORFALSE input[type='checkbox']").length;i++){
        		data+="&answers["+i+"].isRight="+$("#TRUEORFALSE input[type='checkbox']").eq(i).is(":checked");	 
        	}
    	}
		return data;    	
    }
    
    
    function submit_form(){
    	$.ajax({
    		   type: "POST",
    		   url:  $.common.getContextPath() + "/exam/question/save",
    		   data: get_data(),
    		   success: function(msg){
    		     if(msg.code==1){
    		    	 toastr.success('操作成功');
    		    	 table.draw();
    		     }
    		     layer.closeAll() ;
    		   }
    		});
     }
    
    function fun_delete(id){
    	layer.confirm('确定删除当前题目？', {
    		  btn: ['确定','取消'] //按钮
    		}, function(){
    			$.ajax({
    		 		   url:  $.common.getContextPath() + "/exam/question/delete?id="+id,
    		 		   success: function(msg){
    		 		     if(msg.code==1){
    		 		    	 toastr.success('操作成功');
    		 		    	 table.draw();
    		 		     }
    		 		     layer.closeAll() ;
    		 		   }
    		 	});
    		}, function(){
    			 layer.closeAll() ;
    		});
    	
    	
     }
    /**点击修改*/
    function fun_update(id){
    	
    	$.ajax({
 		   url:  $.common.getContextPath() + "/exam/question/get?id="+id,
 		   success: function(msg){
 			   
 		     if(msg.code==1){
 		    	fun_cleanform();
 		    	$("input[name='id']").val(msg.datas.id);
 		    	$("select[name='category.id']").val(msg.datas.category.id);
 		    	$("textarea[name='title']").val(msg.datas.title);
 		    	$("select[name='type'] option").each(function(){
 		    	   if($(this).html() == msg.datas.type){
 		    	  	 $("select[name='type']").val($(this).val());
 		    	   }
 		    	});
 		    	$("select[name='type']").change();
 		    	$("select[name='level'] option").each(function(){
  		    	   if($(this).html() == msg.datas.level){
  		    	  	 $("select[name='level']").val($(this).val());
  		    	   }
  		    	});
  		    	
 		    	if(msg.datas.type=='单选题'){
 		    		for(i=0;i<msg.datas.answers.length;i++){
 		    			$("#SINGLECHOICE input[name='answers["+i+"].title']").val(msg.datas.answers[i].title);
 		    			$("#SINGLECHOICE input[name='answers["+i+"].isRight']").prop("checked",msg.datas.answers[i].isRight);
 		    		}
 		    	}
 		    	
 		    	if(msg.datas.type=='多选题'){
 		    		for(i=0;i<msg.datas.answers.length;i++){
 		    			$("#MULTISELECT input[name='answers["+i+"].title']").val(msg.datas.answers[i].title);
 		    			$("#MULTISELECT input[name='answers["+i+"].isRight']").prop("checked",msg.datas.answers[i].isRight);
 		    		}
 		    	}
 		    	if(msg.datas.type=='判断题'){
 		    		for(i=0;i<msg.datas.answers.length;i++){
 		    			$("#TRUEORFALSE input[name='answers["+i+"].title']").val(msg.datas.answers[i].title);
 		    			$("#TRUEORFALSE input[name='answers["+i+"].isRight']").prop("checked",msg.datas.answers[i].isRight);
 		    		}
 		    	}
 		    	layer.open({
      			  type: 1,
      			  skin: 'layui-layer-rim', 
      			  content: $("#_form"),
      			  area: "800px"
      			});
 		     }
 		   }
 		});
     }
    
    $(document).ready(function(){
          $("select[name='type']").change(function(){
       		  $("#exam_items_table tbody").hide();
       		  $("#exam_items_table #"+$(this).val()).show();
          	}
          );
          
          $("#SINGLECHOICE input[type='radio']").click(function(){
        	  $("#SINGLECHOICE input[type='radio']").prop("checked",false);
        	  $(this).prop("checked",true);
          });
          
          $("#TRUEORFALSE input[type='radio']").click(function(){
        	  $("#SINGLECHOICE input[type='radio']").prop("checked",false);
        	  $(this).prop("checked",true);
          });
          
          
          
    	  $("#_new").click(function(){
    		  fun_cleanform();
        		layer.open({
        			  type: 1,
        			  skin: 'layui-layer-rim', //加上边框
        			  content: $("#_form"),
        			  area: "800px"
        			});
        	});
        	
        	table=$('#dt_table_view').DataTable( {
        		"dom": "rt<'row'<'col-sm-5'i><'col-sm-7'p>>",
	            "ajax": {
	                "url":  $.common.getContextPath() + "/exam/question/listall",
	                "type": "POST",
	                "dataSrc": "datas"
	              },
				"columns" : [{
					"data" : "id"
				}, {
					"data" : "category.name"
				},{
					"data" : "title",
				},{
					"data" : "type",
				},{
					"data" : "level",
				},{
					"data" : "createDate",
				},{
					"data" : "id",
				}] ,
				 "columnDefs": [
				                {
				                    "render": function ( data, type, row ) {
				                    	return "<a tager='_blank' href='javascript:void(0)' onclick='fun_update("+data+")'>修改 </a>"+
				                         "<a tager='_blank' href='javascript:void(0)' onclick='fun_delete("+data+")'>删除 </a>";
				                    },
				                    "targets":6
				                }
				               
				            ],
        		"initComplete": function () {
        			var api = this.api();
        			$("#_search").on("click", function(){
            		 	api.draw();
        			} );
        		} 
        	 } ).on('preXhr.dt', function ( e, settings, data ) {
		        	data.title = $("#_title").val();
		        	data.categoryid = $("select[name='categoryid']").val();
		        	
		        	return true;
		     } ).on('xhr.dt', function ( e, settings, json, xhr ) {
		    		 $(".dataTables_processing").hide();	
		     } )
        });
    </script>
</body>

</html>
