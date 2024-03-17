<div class="col-md-2">
    <div class="page-header">
        <img src="images/rodeo_cropped.png" alt="logo" class="logoImg">
    </div>
    <ul class="nav flex-column nav-pills">

        <c:choose>
            <c:when test="${empty userName}">
                <li class="nav-item">
                    <a class="nav-link" href="logIn">Log In</a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/home">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/guide">Fishing Guide</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/profile">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="logout">Log Out</a>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>