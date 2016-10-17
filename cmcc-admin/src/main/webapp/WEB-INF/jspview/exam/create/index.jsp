<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
</head>
<body >
    <div class="wrapper wrapper-content animated fadeInRight">
          <div class="row">
           		 <div class="col-sm-4">
        			 
        			 <div class="ibox float-e-margins">
                    	<div class="ibox-title">
                        <h5>试卷信息</h5>
                    </div>
                    
                   	 <div class="ibox-content">
                        <form id='exam_form' role="form" class="form-horizontal">
                        <input type="hidden" name="eid" />
                            <div class="form-group">
                                
                                <label class="col-sm-4 control-label">试卷标题</label>

                                <div class="col-sm-8">
                                    <input name='title' type="text" class="form-control">
                                </div>
                            </div>
                           
                            <div class="hr-line-dashed"></div>
                            
                            <div class="form-group">
                                <label class="col-sm-4 control-label">启用</label>
                                <div class="col-sm-8">
                                   	<label class='checkbox-inline'> <input type="radio" name="isEnable"  value="true" >是</label>
                                   	<label class='checkbox-inline'> <input type="radio" name="isEnable" checked="checked" value="false" >否</label>
				                     <span class="help-block m-b-none">只有启动状态手机端才会显示</span>      					
                                </div>
                            </div>
                           <div class="hr-line-dashed"></div>
                           
                           
                           <div class="form-group">
                                <label class="col-sm-4 control-label">用时</label>
                                <div class="col-sm-8">
                                    <input name='minute' type="text" class="form-control">
                                     <span class="help-block m-b-none">单位：分钟</span> 
                                </div>
                            </div>
                            
                            <div class="hr-line-dashed"></div>
                            
                            <div class="form-group">
                            <div class="col-sm-4 ">
                                    <button onclick="fun_submit()" class="btn btn-primary" type="button">保存试卷</button>
                            </div>
                            </div>
                            
                        </form>
                    </div>
                </div>
           	</div>
           					 
          <div class="col-sm-8">
         		  <div class="ibox float-e-margins">
                    	<div class="ibox-title">
                        <h5>已选题目</h5>
                    </div>
         		 <div class="ibox-content ">
         		   <button class="btn btn-primary" type="button" id='_questions_btn'>从题库中选题 </button>
                         <table ID='dt_table_view_checked' class="table table-striped table-bordered table-hover ">
                            <thead>
                                <tr>
									<th>id</th>
									<th>类别</th>
									<th>题目</th>
									<th>题型</th>
									<th>难度</th>
									<th>操作</th>
								</tr>
                            </thead>
                       		 <tbody id='selected_questions'>
                       		 
                            </tbody>
                          </table>
                </div>			 
          </div>
     </div>
   </div>
</div>              
  
  

<div id='_questions_table'  style="display: none;">
       <div class="ibox-content">
   		 <div  class="row" >
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
									<th>选择</th>
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
   </div>
   <!-- end choose -->   
   
   <script>
    var table=null;
    
    function fun_reflesh_selectedquestions(){
    	$.ajax({
 		   type: "POST",
 		   url:  $.common.getContextPath() + "/exam/create/questions?eid="+ $("input[name='eid']").val(),
 		   success: function(msg){
 		     if(msg.code==1){
 		    	 $("#selected_questions").empty();
 		    	 for(i=0;i<msg.datas.length;i++){
 		    		 $("#selected_questions").append("<tr>"+
            		 	"<td>"+msg.datas[i].id+"</td>"+
               		 	"<td>"+msg.datas[i].category.name+"</td>"+
               			"<td>"+msg.datas[i].title+"</td>"+
               		 	"<td>"+msg.datas[i].type+"</td>"+
               		 	"<td>"+msg.datas[i].level+"</td>"+
               		 	"<td>删除</td>"+
               		" </tr>" );
 		    	 }
 		     }
 		   }
 		});
    }
    
    function fun_submit(){
    	$.ajax({
    		   type: "POST",
    		   url:  $.common.getContextPath() + "/exam/create/save",
    		   data: $("#exam_form").serialize(),
    		   success: function(msg){
    		     if(msg.code==1){
    		    	 toastr.success('操作成功');
    		    	 table.draw();
    		    	 $("input[name='eid']").val(msg.datas.id);
    		     }
    		     layer.closeAll() ;
    		   }
    		});
     }
    
    function fun_doclick(obj){
    	var qid=$(obj).val();
    
    	if($(obj).prop("checked")){
	    	$.ajax({
	 		   type: "POST",
	 		   url:  $.common.getContextPath() + "/exam/create/selectQuestion",
	 		   data: {
	 			   "qid":qid,
	 			   "eid":$("input[name='eid']").val()
	 		   },
	 		   success: function(msg){
	 		     if(msg.code==1){
	 		    	fun_reflesh_selectedquestions();
	 		     }else{
	 		    	 toastr.warning(msg.msg);
	 		     }
	 		   }
	 		});
    	}else{
    		$.ajax({
 	 		   type: "POST",
 	 		   url:  $.common.getContextPath() + "/exam/create/unselectQuestion",
 	 		   data: {
 	 			   "qid":qid,
 	 			   "eid":$("input[name='eid']").val()
 	 		   },
 	 		   success: function(msg){
 	 		     if(msg.code==1){
 	 		    	fun_reflesh_selectedquestions();
 	 		     }else{
	 		    	 toastr.warning(msg.msg);
	 		     }
 	 		   }
 	 		});
    	}
    }
    
    $(document).ready(function(){
    	$("body").addClass("gray-bg");
          
    	$("#_questions_btn").click(function(){
        		layer.open({
        			  type: 1,
        			  skin: 'layui-layer-rim', //加上边框
        			  content: $("#_questions_table"),
        			  area: "800px"
        			});
        	});
        	
    	table=$('#dt_table_view').DataTable( {
    		"dom": "rt<'row'<'col-sm-5'i><'col-sm-7'p>>",
    		 "ordering": true,
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
				"data" : "id",
			}] ,
			 "columnDefs": [
			                {
			                    "render": function ( data, type, row ) {
			                        return "<input type='checkbox' name='check' onclick='fun_doclick(this)' value='"+data+"'> ";
			                    },
			                    "targets":5
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