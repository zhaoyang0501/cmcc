<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <link href="${pageContext.request.contextPath}/css/plugins/jsTree/style.min.css" rel="stylesheet">
</head>
<body >
   <input id='deptid' type="hidden"/>
		                      
    <div class="wrapper wrapper-content animated fadeInRight">
       <div class="row">
            <div class="col-sm-12">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>注册用户管理 </h5>
                    </div>
                   
                    
                    <div class="ibox-content ">
                     <div class="row">
	                     <div class="col-sm-4">
	           				<div id="jstree_demo_div">
	           				</div>
	          			 </div>
          			  	
          			  	<div class="col-sm-8">
	                        <form role="form" class="form-inline">
	                            <div class="form-group">
	                                <label for="exampleInputEmail2" class="sr-only">姓名</label>
	                                <input type="text" placeholder="姓名" id="_name" class="form-control">
	                            </div>
	                            <div class="form-group">
                                   	<label class='checkbox-inline'> <input type="radio" name="isFreeze"  checked="checked"  value="false" >正常</label>
                                   	<label class='checkbox-inline'> <input type="radio" name="isFreeze" value="true" >冻结</label>
                           		 </div>
                            
	                            <button class="btn btn-primary" type="button" id='_search'>查询</button>
	                            <button class="btn btn-primary" type="button" id='_new'>登记</button>
	                        </form>
           				
           					<table ID='dt_table_view' class="table table-striped table-bordered table-hover ">
	                            <thead>
	                                <tr>
										<th>id</th>
										<th>用户名</th>
										<th>工号</th>
										<th>真实姓名</th>
										<th>性别</th>
										<th>电话</th>
										<th>部门组织</th>
										<th>冻结</th>
										<th>绑定</th>
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
		                           		<tr style="text-align: center;" ><td colspan="6" ><h3>注册用户信息<h3></h3></td></tr>
		                           		</thead>
		                           		<tbody>
		                           			<tr>
		                           				<td>姓名</td>
		                           				<td> <input name='chinesename' type="text" class="form-control"></td>
		                           			</tr>
		                           			
		                           			<tr>
		                           			<td>性别</td>
		                           				<td>
												  <label class='checkbox-inline'>
												    <input type="radio" name="sex"  value="男" checked>
												    	男
												   </label>
												   <label class='checkbox-inline'>
												    <input type="radio" name="sex"  value="女">
												 	   女
												  </label>
		                           				</td>
		                           			</tr>
		                           			
		                           				
		                           			<tr>
		                           				<td>用户名（139邮箱）</td>
		                           				<td> <input name='username' type="text" class="form-control"></td>
		                           			</tr>
		                           			<tr>
		                           				<td>工号</td>
		                           				<td> <input name='empid' type="text" class="form-control"></td>
		                           			</tr>
		                           			
		                           			<tr>
		                           				<td>电话</td>
		                           				<td> <input name='tel' type="text" class="form-control"></td>
		                           			</tr>
		                           			
		                           			
		                           			<tr>
		                           				<td>部门</td>
		                           				<td>
												<select name='deptment.id' class=" form-control">
												  	<c:forEach var="bean" items="${deptmentselects}">
												  		<option value="${bean.id }">${bean.text }</option>
												  	</c:forEach>
												  </select>
		                           				</td>
		                           			</tr>
		                           			
											<tr>
												<td>备注</td>
		                           				<td> <textarea name='remark' rows="4" cols="" style="width: 80%"></textarea></td>
		                           			</tr>
		                           			
		                           			
		                           			<tr>
		                           				<td>角色</td>
		                           				<td> 
		                           				<c:forEach items="${roles }" var="bean">
		                           				  <label class='checkbox-inline'>
														<input type="checkbox" name="role" value="${bean.id }"> ${bean.name }
												</label>
		                           				</c:forEach>
												</td>
		                           			</tr>
		                           			
		                           		
		                           			<tr>
		                           				<td>提示</td>
		                           				<td > 
		                           					 <h4>提示</h4>
		                               					 <ol>
									    					<li>初始密码为123456</li>
									    				</ol>
		                           				</td>
		                           			</tr>
		                           			<tr>
		                           				<td colspan="6"> 
		                           					 <div class="col-sm-4 col-sm-offset-2">
		                                  			  		<button class="btn btn-primary" type="button" onclick="submit_form()">提交</button>
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
    
    function submit_form(){
    	$.ajax({
    		   type: "POST",
    		   url:  $.common.getContextPath() + "/sys/user/save",
    		   data: $("form").serialize(),
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
    	layer.confirm('确定删除当前用户？', {
    		  btn: ['确定','取消'] //按钮
    		}, function(){
    			$.ajax({
    		 		   url:  $.common.getContextPath() + "/sys/user/delete?id="+id,
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
    function fun_freeze(id){
    	layer.confirm('确定冻结当前用户，冻结后的用户将无法登陆？', {
    		  btn: ['确定','取消'] //按钮
    		}, function(){
    			$.ajax({
    		 		   url:  $.common.getContextPath() + "/sys/user/freeze?id="+id,
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
    function fun_unfreeze(id){
    	layer.confirm('确定解冻当前用户？', {
    		  btn: ['确定','取消'] //按钮
    		}, function(){
    			$.ajax({
    		 		   url:  $.common.getContextPath() + "/sys/user/unfreeze?id="+id,
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
    function fun_update(id){
    	$.ajax({
 		   url:  $.common.getContextPath() + "/sys/user/get?id="+id,
 		   success: function(msg){
 		     if(msg.code==1){
 		    	$("input[name='id']").val(msg.datas.id);
 		    	$("input[name='chinesename']").val(msg.datas.chinesename);
 		    	$("radio[name='sex']").val(msg.datas.sex);
 		   		$("input[name='username']").val(msg.datas.username);
 				$("input[name='tel']").val(msg.datas.tel);
 				$("input[name='empid']").val(msg.datas.empid);
 				$("input[name='email']").val(msg.datas.email);
 				$("textarea[name='remark']").val(msg.datas.remark);
 				$("input:checkbox[name='role']").prop('checked',false); 
 				for(var i=0;i<msg.datas.roles.length;i++){
 					$("input:checkbox[value='"+msg.datas.roles[i].id+"']").prop('checked',true); 
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
    	//$("select[name='deptment.id']").select2();
    	$.ajax({
	 		   url:  $.common.getContextPath() + "/sys/user/alldeptments",
	 		   success: function(msg){
	 			  $('#jstree_demo_div').jstree({
	 			 		'core' : {
	 			 			"multiple" : false,
	 			 		  'data' : msg
	 			 		}}).on('changed.jstree', function (e, data) {
	 			 			console.info(data.node.id);
	 			 			$("#deptid").val(data.node.id);
	 			 			 table.draw();
	 			 		  });
	 			   
	 		   }
	 	});
    /*
    $('#jstree_demo_div').jstree({
 		'core' : {
 		  'data' : msg.datas
 		});
  });*/
    	
    	
        	$("#_new").click(function(){
        		$("input[name='id']").val("");
 		    	$("input[name='chinesename']").val("");
 		    	$("radio[name='sex']").val("");
 		    	$("radio[name='empid']").val("");
 		   		$("input[name='username']").val("");
 				$("input[name='tel']").val("");
 				$("input[name='email']").val("");
 				$("textarea[name='remark']").val("");
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
	                "url":  $.common.getContextPath() + "/sys/user/listall",
	                "type": "POST",
	                "dataSrc": "datas"
	              },
				"columns" : [{
					"data" : "id"
				}, {
					"data" : "username"
				}, {
					"data" : "empid"
				},{
					"data" : "chinesename",
				},{
					"data" : "sex",
				},{
					"data" : "tel",
				},{
					"data" : "deptment.name",
				},{
					"data" : "isFreeze",
				},{
					"data" : "isBind",
				},{
					"data" : "id",
				}] ,
				 "columnDefs": [
								{
								    "render": function ( data, type, row ) {
								        if(!data)
								        	return "<span class='label label-primary '>正常 </span>";
								        else
								        	return 	"<span class='label  label-danger'> 冻结</span>";
								        	
								    },
								    "targets":7
								}, 
								{
								    "render": function ( data, type, row ) {
								        if(data)
								        	return "<span class='label label-primary '>已绑定 </span>";
								        else
								        	return 	"<span class='label  label-danger'> 未绑定</span>";
								        	
								    },
								    "targets":8
								}, 
				                {
				                    "render": function ( data, type, row ) {
				                    	if(row.isFreeze){
				                    		 return "<a tager='_blank' href='javascript:void(0)' onclick='fun_delete("+data+")'>删除 </a>"+
						                        "<a tager='_blank' href='javascript:void(0)' onclick='fun_unfreeze("+data+")'>解结 </a>"+
						                        "<a tager='_blank' href='javascript:void(0)' onclick='fun_update("+data+")'>编辑 </a>";
				                    	}else{
				                   		 return "<a tager='_blank' href='javascript:void(0)' onclick='fun_delete("+data+")'>删除 </a>"+
					                        "<a tager='_blank' href='javascript:void(0)' onclick='fun_freeze("+data+")'>冻结 </a>"+
					                        "<a tager='_blank' href='javascript:void(0)' onclick='fun_update("+data+")'>编辑 </a>";
			                    
				                    	}
				                    
				                       
				                    },
				                    "targets":9
				                }
				               
				            ],
        		"initComplete": function () {
        			var api = this.api();
        			$("#_search").on("click", function(){
            		 	api.draw();
        			} );
        		} 
        	 } ).on('preXhr.dt', function ( e, settings, data ) {
		        	data.value = $("#_name").val();
		        	data.deptid = $("#deptid").val();
		        	data.isFreeze = $("input[name='isFreeze']:checked").val();;
		        	return true;
		     } ).on('xhr.dt', function ( e, settings, json, xhr ) {
		    		 $(".dataTables_processing").hide();	
		     } )
        });
    </script>
    <script src="${pageContext.request.contextPath}/js/plugins/jsTree/jstree.min.js"></script>
</body>
 
</html>
