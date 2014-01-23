<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
{ 
"people": [
				<c:forEach var="dip" items="${words}">
                { "firstName":"${dip}" , "lastName":"McLaughlin", "email": "aaaa" }
                </c:forEach>
            ]
}