<%--
  Created by IntelliJ IDEA.
  User: Hermit
  Date: 2017/7/7
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <title>DraFinance - 回测</title>

    <link type="text/css" rel="stylesheet" href="<c:url value="/plugin/bootstrap-3.3.5/dist/css/bootstrap.min.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/plugin/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/backtest.css"/>"/>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="<c:url value="/"/>">DraFinance 回测</a>
        </div>
    </div>
</nav>

<div class="container">
    <form id="request-form" class="col-md-3">
        <div class="row">
            <h3>回测参数</h3>
            <br>
            <div class="form-group">
                <label for="start">开始日期</label>
                <input type="text" class="form-control" id="start" name="start" placeholder="开始日期"/>
            </div>
            <div class="form-group">
                <label for="start">结束日期</label>
                <input type="text" class="form-control" id="end" name="end" placeholder="结束日期"/>
            </div>
            <div class="form-group">
                <label for="start">起始金额</label>
                <input type="text" class="form-control" id="balance" name="balance" placeholder="起始金额" value="100000"/>
            </div>
            <div class="form-group">
                <label for="pool">股票池</label>
                <%--<input type="text" class="form-control" id="pool" placeholder="默认股票池"/>--%>
                <select id="pool" class="form-control">
                    <option>默认股票池</option>
                </select>
            </div>
            <div class="form-group">
                <br>
                <button id="submit" type="button" class="btn btn-primary form-control">开始回测</button>
            </div>
        </div>
    </form>
    <div class="col-md-9">
        <h3>收益曲线</h3>
        <div id="revenue-chart" class="chart">

        </div>
    </div>
</div>

</body>
<script>

</script>
<script type="text/javascript" rel="script" src="<c:url value="/js/jquery-2.2.3.min.js"/>"></script>
<script type="text/javascript" rel="script" src="<c:url value="/js/echarts.min.js"/>"></script>
<script type="text/javascript" rel="script" src="<c:url value="/plugin/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"/>"></script>
<script type="text/javascript" rel="script" src="<c:url value="/js/backtest.js" />"></script>
</html>