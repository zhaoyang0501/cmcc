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
                                <label for="exampleInputEmail2" class="sr-only">标题</label>
                                <input type="text" placeholder="标题 "  id="_name" class="form-control">
                            </div>
                            <div class="form-group">
                                   	<label class='checkbox-inline'> <input type="radio" name="isEnable"  value="true" >启用</label>
                                   	<label class='checkbox-inline'> <input type="radio" name="isEnable" checked="checked" value="false" >未启用</label>
                            </div>
                            
                            <button class="btn btn-primary" type="button" id='_search'>查询</button>
                        </form>
                    </div>
                    
                    <div class="ibox-content ">
                         <table ID='dt_table_view' class="table table-striped table-bordered table-hover ">
                            <thead>
                                <tr>
									
									<th>类别</th>
									<th>标题</th>
									<th>状态</th>
									<th>题目总数</th>
									<th>用时</th>
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
 
   <script>
    var table=null;
    
   
    
    function fun_delete(id){
    	
    	layer.confirm('确定删除当前试卷？', {
    		  btn: ['确定','取消'] //按钮
    		}, function(){
    			$.ajax({
    		 		   url:  $.common.getContextPath() + "/exam/exam/delete?id="+id,
    		 		   success: function(msg){
    		 		     if(msg.code==1){
    		 		    	 toastr.success('操作成功');
    		 		    	 table.draw();
    		 		     }else{
    		 		    	 toastr.warning(msg.msg);
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
 		   url:  $.common.getContextPath() + "/bbs/article/get?id="+id,
 		   success: function(msg){
 		     if(msg.code==1){
 		    	$("input[name='id']").val(msg.datas.id);
 		    	$("input[name='chinesename']").val(msg.datas.chinesename);
 		    	$("radio[name='sex']").val(msg.datas.sex);
 		   		$("input[name='username']").val(msg.datas.username);
 				$("input[name='tel']").val(msg.datas.tel);
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
        	table=$('#dt_table_view').DataTable( {
        		"dom": "rt<'row'<'col-sm-5'i><'col-sm-7'p>>",
        		  "ordering": true,
	            "ajax": {
	                "url":  $.common.getContextPath() + "/exam/exam/listall",
	                "type": "POST",
	                "dataSrc": "datas"
	              },
				"columns" : [  {
					"data" : "examCategory.name",
				},{
					"data" : "title",
				},{
					"data" : "isEnable",
				},{
					"data" : "qnum",
				},{
					"data" : "minute",
				},{
					"data" : "createDate",
				},{
					"data" : "id",
				}] ,
				 "columnDefs": [
				                {
				                    "render": function ( data, type, row ) {
				                        return data+"分钟";
				                        	
				                    },
				                    "targets":4
				                },
				                {
				                    "render": function ( data, type, row ) {
				                        if(data)
				                        	return "<span class='label label-primary'>有效 </span>";
				                        else
				                        	return 	"<span class='label '>无效 </span>";
				                        	
				                    },
				                    "targets":2
				                }, 
				                {
				                    "render": function ( data, type, row ) {
				                        return "<a tager='_blank' href='javascript:void(0)' onclick='fun_delete("+data+")'>删除 </a>"+
				                        "<a tager='_blank' href='${pageContext.request.contextPath}/exam/create/index/"+data+"'>编辑 </a>"
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
		        	data.title = $("#_name").val();
		        	data.isEnable = $("input[name='isEnable']:checked").val();
		        	return true;
		     } ).on('xhr.dt', function ( e, settings, json, xhr ) {
		    		 $(".dataTables_processing").hide();	
		     } )
        });
    </script>
</body>

</html>
