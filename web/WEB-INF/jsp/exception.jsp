<%--
  Created by IntelliJ IDEA.
  User: Hermit
  Date: 2017/6/7
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>出错啦!</title>

    <link rel="icon" type="image/png" sizes="192x192"  href="<c:url value="/img/favicon/android-icon-192x192.png"/>">
    <link rel="icon" type="image/png" sizes="32x32" href="<c:url value="/img/favicon/favicon-32x32.png"/>">
    <link rel="icon" type="image/png" sizes="96x96" href="<c:url value="/img/favicon/favicon-96x96.png"/>">
    <link rel="icon" type="image/png" sizes="16x16" href="<c:url value="/img/favicon/favicon-16x16.png"/>">
    <link rel="manifest" href="<c:url value="/img/favicon/manifest.json"/>">


    <link rel="stylesheet" type="text/css" href="<c:url value="/css/reset.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/common.css" />">

</head>
<body>
<img id="stock_title_background" src="<c:url value="/img/background/stock_title_1.png"/>">
<jsp:include page="header.jsp" flush="true"/>
<div style="height: 100vh; padding-top: 30vh">
    <h1 style="text-align: center; font-size: 40px;">出错啦，没有相关数据～</h1>
</div>
<jsp:include page="footer.jsp" flush="true"/>
</body>

<!-- Insert this line above script imports  -->
<script>if (typeof module === 'object') {window.module = module; module = undefined;}</script>

<script>
    var stockList = ${stockList != null ? stockList : null};
</script>

<script type="text/javascript" rel="script" src="<c:url value="/js/jquery-2.2.3.min.js"/>"></script>
<script type="text/javascript" rel="script" src="<c:url value="/plugin/gsap/src/uncompressed/TweenMax.js"/>"></script>
<script type="text/javascript" rel="script" src="<c:url value="/plugin/gsap/src/uncompressed/plugins/ScrollToPlugin.js"/>"></script>
<script type="text/javascript" rel="script" src="<c:url value="/plugin/scrollmagic/scrollmagic/minified/ScrollMagic.min.js"/>"></script>
<script type="text/javascript" rel="script" src="<c:url value="/plugin/scrollmagic/scrollmagic/uncompressed/plugins/animation.gsap.js"/>"></script>
<script type="text/javascript" rel="script" src="<c:url value="/js/header.js"/>"></script>

<!-- Insert this line after script imports -->
<script>if (window.module) module = window.module;</script>
</html>
