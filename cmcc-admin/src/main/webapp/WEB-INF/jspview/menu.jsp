<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>

			<div id="sidebar" class="sidebar   responsive  ace-save-state">
				<script type="text/javascript">
					try{ace.settings.loadState('sidebar')}catch(e){}
				</script>
	
				<ul class="nav nav-list">
					<li class="active"><a href="${pageContext.request.contextPath}/workflow/tasktodo"> <i
							class="menu-icon fa fa-calendar"></i> <span class="menu-text">
								我的待办 </span>
					</a> <b class="arrow"></b></li>
	
					<li class=""><a href="${pageContext.request.contextPath}/workflow/taskdone"> <i
							class="menu-icon fa fa-briefcase"></i> <span class="menu-text">
								我的已办 </span>
					</a> <b class="arrow"></b></li>
	
					<li class=""><a href="${pageContext.request.contextPath}/workitem/create"> <i
							class="menu-icon fa  fa-pencil-square-o"></i> <span class="menu-text">
								任务申请</span>
					</a> <b class="arrow"></b></li>
	
					<li class=""><a href="${pageContext.request.contextPath}/user/index"> <i
							class="menu-icon fa fa-users"></i> <span class="menu-text">
								员工管理</span>
					</a> <b class="arrow"></b></li>
	
	
					<li class=""><a href="${pageContext.request.contextPath}/workitem/index"> <i
							class="menu-icon fa  fa-eye"></i> <span class="menu-text">任务监控</span>
					</a> <b class="arrow"></b></li>
				</ul>
				<!-- /.nav-list -->
	
				<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
					<i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>
			</div>