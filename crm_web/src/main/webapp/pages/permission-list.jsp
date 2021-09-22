<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 页面meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>资源权限列表</title>
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
		<div class="content-wrapper">

			<!-- 内容头部 -->
			<section class="content-header">
			<h1>
				权限管理 <small>全部权限</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="${pageContext.request.contextPath}/index.jsp"><i
						class="fa fa-dashboard"></i> 首页</a></li>
				<li><a
					href="${pageContext.request.contextPath}/role/findAll.do">权限管理</a></li>
				<li class="active">全部权限</li>
			</ol>
			</section>
			<!-- 内容头部 /-->

				<!-- 正文区域 -->
				<section class="content"> <!-- .box-body -->
				<div class="box box-primary">
					<div class="box-header with-border">
						<center>
							<h3 class="box-title">权限列表</h3>
						</center>
					</div>
					<a style="margin-left: 6px" href="${pageContext.request.contextPath}/pages/permission-add.jsp" class="btn btn-success btn-lg">添加</a>

					<div class="box-body">

						<!-- 数据表格 -->
						<div class="table-box">

							<!--数据列表-->
							<table id="dataList"
								class="table table-bordered table-striped table-hover dataTable">
								<thead>
									<tr>
										<th class="sorting_asc">ID</th>
										<th class="sorting_desc">权限名称</th>
										<th class="sorting_asc sorting_asc_disa`bled">权限 URI</th>
										<th class="text-center">操作</th>
									</tr>
								</thead>
								<tbody>

									<c:forEach items="${pageInfo.list}" var="p">
										<tr>
											<td>${p.id}</td>
											<td>${p.permissionName}</td>
											<td>${p.url}</td>
											<td class="text-center">
												<a href="${pageContext.request.contextPath}/permission/updateByIdUI.do?id=${p.id}" class="btn btn-primary btn-xs">修改</a>
												<a href="${pageContext.request.contextPath}/permission/findById.do?id=${p.id}" class="btn btn-info btn-xs">权限关联的角色</a>
												<button class="btn btn-xs btn-danger delete" onclick="deletePermissionById(${p.id})">删除</button>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<!--数据列表/-->
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
									<select class="form-control" onchange="changePageSize()" id="changePageSize">
										<c:forEach var="i" begin="1" end="5">
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
									<select class="form-control" onchange="changePageSize()" id="changePageSize">
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
										<a href="${pageContext.request.contextPath}/permission/findAll.do?pageNum=1&pageSize=${pageInfo.pageSize}" aria-label="Previous">首页</a>
									</li>
									<li><a href="${pageContext.request.contextPath}/permission/findAll.do?pageNum=${pageInfo.pageNum-1}&pageSize=${pageInfo.pageSize}">上一页</a></li>
									<c:forEach begin="1" end="${pageInfo.pages}" var="pageNum">
										<c:if test="${pageNum == pageInfo.pageNum}">
											<li><a style="background-color: cornflowerblue;" href="${pageContext.request.contextPath}/permission/findAll.do?pageNum=${pageNum}&pageSize=${pageInfo.pageSize}">${pageNum}</a></li>
										</c:if>
										<c:if test="${pageNum != pageInfo.pageNum}">
											<li><a href="${pageContext.request.contextPath}/permission/findAll.do?pageNum=${pageNum}&pageSize=${pageInfo.pageSize}">${pageNum}</a></li>
										</c:if>
									</c:forEach>
									<li><a href="${pageContext.request.contextPath}/permission/findAll.do?pageNum=${pageInfo.pageNum+1}&pageSize=${pageInfo.pageSize}">下一页</a></li>
									<li>
										<a href="${pageContext.request.contextPath}/permission/findAll.do?pageNum=${pageInfo.lastPage}&pageSize=${pageInfo.pageSize}" aria-label="Next">尾页</a>
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

		<script src="../plugins/jQuery/jquery-2.2.3.min.js"></script>
		<script src="../plugins/jQueryUI/jquery-ui.min.js"></script>
		<script>
			$.widget.bridge('uibutton', $.ui.button);
		</script>
		<script src="../plugins/bootstrap/js/bootstrap.min.js"></script>
		<script src="../plugins/raphael/raphael-min.js"></script>
		<script src="../plugins/morris/morris.min.js"></script>
		<script src="../plugins/sparkline/jquery.sparkline.min.js"></script>
		<script src="../plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
		<script src="../plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
		<script src="../plugins/knob/jquery.knob.js"></script>
		<script src="../plugins/daterangepicker/moment.min.js"></script>
		<script src="../plugins/daterangepicker/daterangepicker.js"></script>
		<script src="../plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
		<script src="../plugins/datepicker/bootstrap-datepicker.js"></script>
		<script
			src="../plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
		<script
			src="../plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
		<script src="../plugins/slimScroll/jquery.slimscroll.min.js"></script>
		<script src="../plugins/fastclick/fastclick.js"></script>
		<script src="../plugins/iCheck/icheck.min.js"></script>
		<script src="../plugins/adminLTE/js/app.min.js"></script>
		<script src="../plugins/treeTable/jquery.treetable.js"></script>
		<script src="../plugins/select2/select2.full.min.js"></script>
		<script src="../plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
		<script
			src="../plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
		<script src="../plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
		<script
			src="../plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
		<script src="../plugins/bootstrap-markdown/js/markdown.js"></script>
		<script src="../plugins/bootstrap-markdown/js/to-markdown.js"></script>
		<script src="../plugins/ckeditor/ckeditor.js"></script>
		<script src="../plugins/input-mask/jquery.inputmask.js"></script>
		<script
			src="../plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
		<script src="../plugins/input-mask/jquery.inputmask.extensions.js"></script>
		<script src="../plugins/datatables/jquery.dataTables.min.js"></script>
		<script src="../plugins/datatables/dataTables.bootstrap.min.js"></script>
		<script src="../plugins/chartjs/Chart.min.js"></script>
		<script src="../plugins/flot/jquery.flot.min.js"></script>
		<script src="../plugins/flot/jquery.flot.resize.min.js"></script>
		<script src="../plugins/flot/jquery.flot.pie.min.js"></script>
		<script src="../plugins/flot/jquery.flot.categories.min.js"></script>
		<script src="../plugins/ionslider/ion.rangeSlider.min.js"></script>
		<script src="../plugins/bootstrap-slider/bootstrap-slider.js"></script>
		<script>
			$(document).ready(function() {
				// 选择框
				$(".select2").select2();

				// WYSIHTML5编辑器
				$(".textarea").wysihtml5({
					locale : 'zh-CN'
				});
			});

			function deletePermissionById(id) {
				if (confirm("您确认删除吗？")) {
					location.href = "${pageContext.request.contextPath}/permission/deleteById.do?id=" + id;
				}
			}

			function changePageSize() {
				//获取下拉框的值
				var pageSize = $("#changePageSize").val();

				//向服务器发送请求，改变没页显示条数
				location.href = "${pageContext.request.contextPath}/permission/findAll.do?pageNum=1&pageSize="
						+ pageSize;
			}

			// 设置激活菜单
			function setSidebarActive(tagUri) {
				var liObj = $("#" + tagUri);
				if (liObj.length > 0) {
					liObj.parent().parent().addClass("active");
					liObj.addClass("active");
				}
			}

			$(document)
					.ready(
							function() {

								// 激活导航位置
								setSidebarActive("admin-datalist");

								// 列表按钮 
								$("#dataList td input[type='checkbox']")
										.iCheck(
												{
													checkboxClass : 'icheckbox_square-blue',
													increaseArea : '20%'
												});
								// 全选操作 
								$("#selall")
										.click(
												function() {
													var clicks = $(this).is(
															':checked');
													if (!clicks) {
														$(
																"#dataList td input[type='checkbox']")
																.iCheck(
																		"uncheck");
													} else {
														$(
																"#dataList td input[type='checkbox']")
																.iCheck("check");
													}
													$(this).data("clicks",
															!clicks);
												});
							});
		</script>
</body>

</html>