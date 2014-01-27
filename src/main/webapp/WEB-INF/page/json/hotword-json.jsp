<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
{ "obj": [
<c:forEach var="dip" items="${words}" varStatus="status">
	<c:if test="${!status.last}">
          { "prefix":"${prefix}","word":"${dip.val}","num":"${dip.num}" },
    </c:if>
	<c:if test="${status.last}">
          { "prefix":"${prefix}","word":"${dip.val}","num":"${dip.num}" }
    </c:if>
</c:forEach>
] }
