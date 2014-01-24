<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
{ "obj": [
<c:forEach var="dip" items="${words}" varStatus="status">
	<c:if test="${!status.last}">
          { "prefix":"${prefix}","word":"${dip}" },
    </c:if>
	<c:if test="${status.last}">
          { "word":"${dip}" }
    </c:if>
</c:forEach>
] }
