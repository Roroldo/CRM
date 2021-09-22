<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<!-- 页面meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">




<title>日志管理</title>
<meta name="description" content="AdminLTE2定制版">
<meta name="keywords" content="AdminLTE2定制版">




<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
	name="viewport">


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/morris/morris.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/datepicker/datepicker3.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.theme.default.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/select2/select2.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/adminLTE/css/skins/_all-skins.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.skinNice.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap-slider/slider.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css">
	<script src="${pageContext.request.contextPath}/js/isSelectedCheckedBox.js"></script>
</head>

<body class="hold-transition skin-green sidebar-mini">

	<div class="wrapper">

		<!-- 页面头部 -->
		<jsp:include page="header.jsp"/>
		<!-- 页面头部 /-->
		<!-- 导航侧栏 -->
		<jsp:include page="aside.jsp"/>
		<!-- 导航侧栏 /-->

		<!-- 内容区域 -->
		<!-- @@master = admin-layout.html-->
		<!-- @@block = content -->

		<div class="content-wrapper">

			<!-- 内容头部 -->
			<section class="content-header">
				<h1>
					日志管理 <small>全部日志</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="${pageContext.request.contextPath}/index.jsp"><i
							class="fa fa-dashboard"></i> 首页</a></li>
					<li><a
							href="${pageContext.request.contextPath}/sysLog/findAll.do">日志管理</a></li>
					<li class="active">全部日志</li>
				</ol>
			</section>
			<!-- 内容头部 /-->

			<!-- 正文区域 -->
			<section class="content">

				<!-- .box-body -->
				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">列表</h3>
					</div>

					<div class="box-body">

						<!-- 数据表格 -->
						<div class="table-box">

							<!--工具栏-->
							<div class="pull-left">
								<div class="form-group form-inline">
									<div class="btn-group">
										<button type="button" class="btn btn-default" title="刷新" onclick="location.reload()">
											<i class="fa fa-refresh"></i> 刷新
										</button>
										<button type="button" class="btn btn-default" title="删除" onclick="confirmDeleteLog()">
											<i class="fa fa-trash-o"></i> 删除
										</button>
									</div>
								</div>
							</div>
							<div class="box-tools pull-right">
								<div class="has-feedback">
									<form method="post" action="${pageContext.request.contextPath}/sysLog/findAll.do?pageNum=1&pageSize=4">
										<input type="text" class="form-control input-sm"
											   placeholder="搜索访问用户，回车开始查询" value="${searchUser == null ? "" : searchUser}" name="searchUser"> <span
											class="glyphicon glyphicon-search form-control-feedback"></span>
									</form>
								</div>
							</div>
							<!--工具栏/-->

							<form id="sysLogForm" method="post">
								<!--数据列表-->
								<table id="dataList"
									   class="table table-bordered table-striped table-hover dataTable">
									<thead>
									<tr>
										<th class="" style="padding-right: 0px"><input id="selall"
																					   type="checkbox" class="icheckbox_square-blue"></th>
										<th class="sorting_asc">序号</th>
										<th class="sorting">访问时间</th>
										<th class="sorting">访问用户</th>
										<th class="sorting">访问IP</th>
										<th class="sorting">资源URL</th>
										<th class="sorting">执行时间</th>
										<th class="sorting">访问方法</th>
									</tr>
									</thead>
									<tbody>
									<c:forEach items="${pageInfo.list}" var="syslog" varStatus="status">
										<tr>
											<td><input class="ids" name="ids" type="checkbox" value="${syslog.id}"></td>
											<td>${status.index + 1}</td>
											<td>${syslog.visitTimeStr }</td>
											<td>${syslog.username }</td>
											<td>${syslog.ip }</td>
											<td>${syslog.url}</td>
											<td>${syslog.executionTime}毫秒</td>
											<td>${syslog.method}</td>
										</tr>
									</c:forEach>
									</tbody>

								</table>
								<!--数据列表/-->
							</form>

						</div>
						<!-- 数据表格 /-->
					</div>
					<!-- /.box-body -->

					<!-- .box-footer-->
                <div class="box-footer">
                    <div class="pull-left">
                        <div class="form-group form-inline">
							总共 <font color="#ff69b4">${pageInfo.pages} </font>页，共<font color="#ff69b4"> ${pageInfo.total} </font>条数据。 每页
							<c:if test="${pageInfo.list.size() > 0}">
								<select class="form-control changePageSize" onchange="changePageSize()">
									<c:forEach var="i" begin="5" end="10">
										<c:if test="${pageInfo.pageSize == i}">
											<option selected>${i}</option>
										</c:if>
										<c:if test="${pageInfo.pageSize != i}">
											<option>${i}</option>
										</c:if>
									</c:forEach>
								</select>
							</c:if>
							<c:if test="${pageInfo.list.size() == 0}">
								<select class="form-control" id="changePageSize">
									<option>0</option>
								</select>
							</c:if>
							条
                        </div>
                    </div>

                    <div class="box-tools pull-right">
						<c:if test="${pageInfo.list.size() > 0}">
							<ul class="pagination">
								<li>
									<a href="${pageContext.request.contextPath}/sysLog/findAll.do?pageNum=1&pageSize=${pageInfo.pageSize}&searchUser=${searchUser}" aria-label="Previous">首页</a>
								</li>
								<li><a href="${pageContext.request.contextPath}/sysLog/findAll.do?pageNum=${pageInfo.pageNum-1}&pageSize=${pageInfo.pageSize}&searchUser=${searchUser}">上一页</a></li>
								<c:forEach begin="1" end="${pageInfo.pages}" var="pageNum">
									<c:if test="${pageNum == pageInfo.pageNum}">
										<li><a style="background-color: cornflowerblue;" href="${pageContext.request.contextPath}/sysLog/findAll.do?pageNum=${pageNum}&pageSize=${pageInfo.pageSize}&searchUser=${searchUser}">${pageNum}</a></li>
									</c:if>
									<c:if test="${pageNum != pageInfo.pageNum}">
										<li><a href="${pageContext.request.contextPath}/sysLog/findAll.do?pageNum=${pageNum}&pageSize=${pageInfo.pageSize}&searchUser=${searchUser}">${pageNum}</a></li>
									</c:if>
								</c:forEach>
								<li><a href="${pageContext.request.contextPath}/sysLog/findAll.do?pageNum=${pageInfo.pageNum+1}&pageSize=${pageInfo.pageSize}&searchUser=${searchUser}">下一页</a></li>
								<li>
									<a href="${pageContext.request.contextPath}/sysLog/findAll.do?pageNum=${pageInfo.lastPage}&pageSize=${pageInfo.pageSize}&searchUser=${searchUser}" aria-label="Next">尾页</a>
								</li>
							</ul>
						</c:if>
                    </div>

                </div>
                <!-- /.box-footer-->



				</div>

			</section>
			<!-- 正文区域 /-->

		</div>
		<!-- @@close -->
		<!-- 内容区域 /-->

		<jsp:include page="/pages/footer.jsp"/>

	</div>


	<script
		src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/jQueryUI/jquery-ui.min.js"></script>
	<script>
		$.widget.bridge('uibutton', $.ui.button);
	</script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/raphael/raphael-min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/morris/morris.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/sparkline/jquery.sparkline.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/knob/jquery.knob.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/daterangepicker/moment.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/datepicker/bootstrap-datepicker.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/fastclick/fastclick.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/adminLTE/js/app.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/markdown.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/to-markdown.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/ckeditor/ckeditor.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.extensions.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/datatables/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/chartjs/Chart.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.resize.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.pie.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.categories.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-slider/bootstrap-slider.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script>
		function changePageSize() {
			//获取下拉框的值
			var pageSize = $(".changePageSize").val();

			//向服务器发送请求，改变每页显示条数
			location.href = "${pageContext.request.contextPath}/sysLog/findAll.do?pageNum=1&pageSize="
					+ pageSize + "&searchUser=${searchUser}";
		}
		$(document).ready(function() {
			// 选择框
			$(".select2").select2();

			// WYSIHTML5编辑器
			$(".textarea").wysihtml5({
				locale : 'zh-CN'
			});
		});

		function confirmDeleteLog() {
			var flag = isSelected();
			if (flag) {
				if (confirm("你确认删除吗？")) {
					$("#sysLogForm").attr("action", "${pageContext.request.contextPath}/sysLog/deleteByIds.do").submit();
				}
			} else {
				alert("请选择条目");
			}
		}

		// 设置激活菜单
		function setSidebarActive(tagUri) {
			var liObj = $("#" + tagUri);
			if (liObj.length > 0) {
				liObj.parent().parent().addClass("active");
				liObj.addClass("active");
			}
		}

		$(document).ready(function() {

			// 激活导航位置
			setSidebarActive("admin-datalist");

			// 列表按钮 
			$("#dataList td input[type='checkbox']").iCheck({
				checkboxClass : 'icheckbox_square-blue',
				increaseArea : '20%'
			});
			// 全选操作 
			$("#selall").click(function() {
				var clicks = $(this).is(':checked');
				if (!clicks) {
					$("#dataList td input[type='checkbox']").iCheck("uncheck");
				} else {
					$("#dataList td input[type='checkbox']").iCheck("check");
				}
				$(this).data("clicks", !clicks);
			});
		});
	</script>
</body>

</html>