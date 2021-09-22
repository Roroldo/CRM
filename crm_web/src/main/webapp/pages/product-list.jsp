<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>产品管理</title>
<meta name="description" content="AdminLTE2定制版">
<meta name="keywords" content="AdminLTE2定制版">
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
	name="viewport">
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/isSelectedCheckedBox.js"></script>
  <![endif]-->
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
					产品管理 <small>产品列表</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li><a href="#">产品管理</a></li>
					<li class="active">产品列表</li>
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
										<button type="button" class="btn btn-primary" title="新建"
											onclick="location.href='${pageContext.request.contextPath}/pages/product-add.jsp'">
											<i class="fa fa-file-o"></i> 新建
										</button>
										<button type="button" class="btn btn-default" title="开启" onclick="changeStatus()">
											<i class="fa fa-check"></i> 开启
										</button>
										<button type="button" class="btn btn-default" title="关闭" onclick="changeStatus()">
											<i class="fa fa-ban"></i> 关闭
										</button>
										<button type="button" class="btn btn-default" title="刷新"
											onclick="location.reload()">
											<i class="fa fa-refresh"></i> 刷新
										</button>
									</div>
								</div>
							</div>
							<div class="box-tools pull-right">
								<div class="has-feedback">
									<form action="${pageContext.request.contextPath}/product/findAll.do?pageNum=1&pageSize=4" method="post">
										<input type="text" class="form-control input-sm"
											   placeholder="搜索产品, 回车开始搜索" value="${searchProduct}" name="searchProduct" id="searchProduct"/>
										<span class="glyphicon glyphicon-search form-control-feedback"></span>
									</form>
								</div>
							</div>
							<!--工具栏/-->

							<!--数据列表-->
							<%--表单一定要将表格包裹起来，否则后端接收不到数据--%>
							<form id="productForm" method="post">
								<table id="dataList"
								class="table table-bordered table-striped table-hover dataTable">
								<thead>
									<tr>
										<th class="" style="padding-right: 0px;"><input
											id="selall" type="checkbox" class="icheckbox_square-blue">
										</th>
										<th class="sorting_asc">ID</th>
										<th class="sorting_desc">编号</th>
										<th class="sorting_asc sorting_asc_disabled">产品名称</th>
										<th class="sorting_desc sorting_desc_disabled">出发城市</th>
										<th class="sorting">出发时间</th>
										<th class="text-center sorting">产品价格</th>
										<th class="sorting">产品描述</th>
										<th class="text-center sorting">状态</th>
										<th class="text-center">操作</th>
									</tr>
								</thead>
								<tbody>

							<c:if test="${pageInfo.list.size() > 0}">
									<c:forEach items="${pageInfo.list}" var="product">
										<tr>
											<td><input name="ids" type="checkbox" value="${product.id}" class="ids"></td>
											<td>${product.id}</td>
											<td>${product.productNum}</td>
											<td>${product.productName}</td>
											<td>${product.cityName}</td>
											<td>${product.departureTimeStr}</td>
											<td class="text-center">${product.productPrice}</td>
											<td>${product.productDesc}</td>
											<td class="text-center">${product.productStatusStr}</td>
											<td class="text-center">
												<button type="button" class="btn bg-success btn-xs" onclick="findById(${product.id})">编辑</button>
												<button type="button" class="btn bg-danger btn-xs" onclick="delProductById(${product.id})">删除</button>
											</td>
										</tr>
									</c:forEach>
								</c:if>
									<c:if test="${pageInfo.list.size() == 0}">
										<tr>
											<td colspan="10">
												<h3 style="text-align: center">没有找到搜索产品名称为${searchProduct}的结果</h3>
											</td>
										</tr>
									</c:if>
								</tbody>
							</table>
							</form>
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
										<a href="${pageContext.request.contextPath}/product/findAll.do?pageNum=1&pageSize=${pageInfo.pageSize}&searchProduct=${searchProduct}" aria-label="Previous">首页</a>
									</li>
									<li><a href="${pageContext.request.contextPath}/product/findAll.do?pageNum=${pageInfo.pageNum-1}&pageSize=${pageInfo.pageSize}&searchProduct=${searchProduct}">上一页</a></li>
									<c:forEach begin="1" end="${pageInfo.pages}" var="pageNum">
										<c:if test="${pageNum == pageInfo.pageNum}">
											<li><a style="background-color: cornflowerblue;" href="${pageContext.request.contextPath}/product/findAll.do?pageNum=${pageNum}&pageSize=${pageInfo.pageSize}&searchProduct=${searchProduct}">${pageNum}</a></li>
										</c:if>
										<c:if test="${pageNum != pageInfo.pageNum}">
											<li><a href="${pageContext.request.contextPath}/product/findAll.do?pageNum=${pageNum}&pageSize=${pageInfo.pageSize}&searchProduct=${searchProduct}">${pageNum}</a></li>
										</c:if>
									</c:forEach>
									<li><a href="${pageContext.request.contextPath}/product/findAll.do?pageNum=${pageInfo.pageNum+1}&pageSize=${pageInfo.pageSize}&searchProduct=${searchProduct}">下一页</a></li>
									<li>
										<a href="${pageContext.request.contextPath}/product/findAll.do?pageNum=${pageInfo.lastPage}&pageSize=${pageInfo.pageSize}&searchProduct=${searchProduct}" aria-label="Next">尾页</a>
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
			var pageSize = $("#changePageSize").val();
			//向服务器发送请求，改变每页显示条数
			location.href = "${pageContext.request.contextPath}/product/findAll.do?pageNum=1&pageSize="
					+ pageSize + "&searchProduct=${searchProduct}";
		}

		function findById(id) {
			location.href = "${pageContext.request.contextPath}/product/findById.do?id=" + id;
		}

		function changeStatus() {
			var flag = isSelected();
            if (flag) {
				$("#productForm").attr("action", "${pageContext.request.contextPath}/product/updateStatus.do").submit();
            } else {
                alert("请选择条目~");
            }
        }

		$(document).ready(function() {
			// 选择框
			$(".select2").select2();

			// WYSIHTML5编辑器
			$(".textarea").wysihtml5({
				locale : 'zh-CN'
			});
		});
		
		function delProductById(pid) {
			if (confirm("你确认删除该产品吗？")) {
				location.href = "${pageContext.request.contextPath}/product/deleteById.do?id=" + pid;
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